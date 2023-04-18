package com.cxm.cxmmusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cxm.cxmmusic.mapper.DictTagMapper;
import com.cxm.cxmmusic.mapper.SongTagMapper;
import com.cxm.cxmmusic.mapper.SonglistMapper;
import com.cxm.cxmmusic.pojo.DictTag;
import com.cxm.cxmmusic.pojo.SongTag;
import com.cxm.cxmmusic.pojo.Songlist;
import com.cxm.cxmmusic.service.RecommendService;
import com.cxm.cxmmusic.vo.mongo.UserPlaySong;
import com.cxm.cxmmusic.vo.mongo.UserPlaySonglist;
import com.cxm.cxmmusic.vo.mongo.UserStarSong;
import com.cxm.cxmmusic.vo.mongo.UserStarSonglist;
import com.cxm.cxmmusic.vo.recommend.RecommendTagList;
import com.cxm.cxmmusic.vo.recommend.SumStarNumberAndPlayNumber;
import com.cxm.cxmmusic.vo.recommend.TagWeight;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author
 * @create 2023-04-12 8:48
 */
@Service
public class RecommendServiceImpl implements RecommendService {

    @Resource
    private MongoTemplate mongoTemplate;
    @Resource
    private SonglistMapper songlistMapper;
    @Resource
    private DictTagMapper dictTagMapper;
    @Resource
    private SongTagMapper songTagMapper;

    @Override
    public RecommendTagList getTagList(String account) {
        return mongoTemplate.findById(account, RecommendTagList.class);
    }

    @Override
    public void createDefaultTagList() {
        List<SumStarNumberAndPlayNumber> sumStarNumberAndPlayNumbers = songlistMapper.sumByTag();
        //计算标签的权重
        sumStarNumberAndPlayNumbers.forEach(SumStarNumberAndPlayNumber::total);

        //获取排序后的标签列表
        List<SumStarNumberAndPlayNumber> collect =
                sumStarNumberAndPlayNumbers.stream()
                        .sorted(Comparator.comparing(SumStarNumberAndPlayNumber::getSumTotal, (o1, o2) -> (int) (o2 - o1)))
                        .collect(Collectors.toList());

        //生成推荐标签列表
        RecommendTagList recommendTagList = new RecommendTagList();
        ArrayList<DictTag> tags = new ArrayList<>();

        int size = collect.size();
        for (int i = 0; i < 6; i++) {
            if (i < size) {
                SumStarNumberAndPlayNumber sumStarNumberAndPlayNumber = collect.get(i);
                String tag = sumStarNumberAndPlayNumber.getTag();

                LambdaQueryWrapper<DictTag> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(DictTag::getText, tag);

                DictTag dictTag = dictTagMapper.selectOne(queryWrapper);

                tags.add(dictTag);
            }

        }

        recommendTagList.setTagList(tags);
        recommendTagList.setAccount("1");
        recommendTagList.setUpdatedTime(new Date());

        //清除旧的推荐标签列表
        mongoTemplate.findAndRemove(Query.query(Criteria.where("account").is("1")), RecommendTagList.class);
        //保存到mongoDb
        mongoTemplate.save(recommendTagList);

    }

    @Override
    public void createUserTagList(String account) {
        //生成用户的推荐标签列表
        RecommendTagList recommendTagList = tagWeight(account);

        //清除旧的推荐标签列表
        mongoTemplate.findAndRemove(Query.query(Criteria.where("account").is(account)), RecommendTagList.class);

        //保存到mongoDb
        mongoTemplate.save(recommendTagList);

    }

    /*
     * 用户的标签权重分配
     * */
    private RecommendTagList tagWeight(String account) {
        //播放 标签
        HashMap<String, Integer> playTags = new HashMap<>();

        HashMap<String, Integer> songTags = getSongTags(account);
        HashMap<String, Integer> songlistTags = getSonglistTags(account);
        //合并map
        songTags.forEach((k, v) -> {
            playTags.merge(k, v, Integer::sum);
        });
        songlistTags.forEach((k, v) -> {
            playTags.merge(k, v, Integer::sum);
        });

        //收藏 标签
        HashMap<String, Integer> starTags = new HashMap<>();

        HashMap<String, Integer> starSongTags = getStarSongTags(account);
        HashMap<String, Integer> starSonglistTags = getStarSonglistTags(account);

        //合并map
        starSongTags.forEach((k, v) -> {
            starTags.merge(k, v, Integer::sum);
        });
        starSonglistTags.forEach((k, v) -> {
            starTags.merge(k, v, Integer::sum);
        });

        //合并全部标签
        HashMap<String, Integer> tags = new HashMap<>();
        //播放标签权重为2倍
        playTags.forEach((k, v) -> {
            tags.merge(k, v * 2, Integer::sum);
        });
        starTags.forEach((k, v) -> {
            tags.merge(k, v, Integer::sum);
        });

        //map转换为list
        ArrayList<TagWeight> tagWeights = new ArrayList<>();

        tags.forEach((k, v) -> {
            LambdaQueryWrapper<DictTag> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(DictTag::getText, k);
            DictTag dictTag = dictTagMapper.selectOne(queryWrapper);
            TagWeight tagWeight = new TagWeight(dictTag, v);
            tagWeights.add(tagWeight);
        });
        //按照权重排序
        List<TagWeight> collect = tagWeights.stream().sorted(
                Comparator.comparing(TagWeight::getWeight).reversed())
                .collect(Collectors.toList());

        //生成推荐标签列表
        RecommendTagList recommendTagList = new RecommendTagList();
        ArrayList<DictTag> dictTags = new ArrayList<>();

        int size = collect.size();
        for (int i = 0; i < 6; i++) {
            if (i < size) {
                DictTag dictTag = collect.get(i).getDictTag();
                dictTags.add(dictTag);
            }
        }

        recommendTagList.setTagList(dictTags);
        recommendTagList.setAccount(account);
        recommendTagList.setUpdatedTime(new Date());

        return recommendTagList;
    }

    /*
     * 获取(7天内的30首歌)播放过的歌曲的标签
     * */
    private HashMap<String, Integer> getSongTags(String account) {

        HashMap<String, Integer> result = new HashMap<>();

        Date date = getDateBeforeXDay(7);
        //查询用户播放歌曲的记录
        Criteria criteria = Criteria.where("account").is(account).and("lastedPlayTime").gte(date);
        Query query = Query.query(criteria).limit(30).skip(0).with(Sort.by(Sort.Order.desc("lastedPlayTime")));

        List<UserPlaySong> userPlaySongs = mongoTemplate.find(query, UserPlaySong.class);

        for (UserPlaySong userPlaySong : userPlaySongs) {
            //查询歌曲对应的标签（多个标签）
            Integer songId = userPlaySong.getSongId();

            getSongTagMap(result, songId);
        }

        return result;
    }


    /*
     * 获取(7天内的5个歌单)播放过的歌单的标签
     * */
    private HashMap<String, Integer> getSonglistTags(String account) {
        Date time = getDateBeforeXDay(7);

        HashMap<String, Integer> result = new HashMap<>();

        //查询用户播放歌单的记录
        Criteria criteria = Criteria.where("account").is(account).and("lastedPlayTime").gte(time);
        Query query = Query.query(criteria).limit(10).skip(0).with(Sort.by(Sort.Order.desc("lastedPlayTime")));

        List<UserPlaySonglist> userPlaySonglists = mongoTemplate.find(query, UserPlaySonglist.class);

        for (UserPlaySonglist userPlaySonglist : userPlaySonglists) {
            Integer songlistId = userPlaySonglist.getSonglistId();

            getSonglistTagMap(result, songlistId);
        }

        return result;
    }


    /*
     * 获取收藏的歌曲的标签
     * */
    private HashMap<String, Integer> getStarSongTags(String account) {
        HashMap<String, Integer> result = new HashMap<>();

        Criteria criteria = Criteria.where("account").is(account);
        List<UserStarSong> userStarSongs = mongoTemplate.find(Query.query(criteria), UserStarSong.class);
        for (UserStarSong userStarSong : userStarSongs) {
            Integer songId = userStarSong.getSongId();

            getSongTagMap(result, songId);
        }


        return result;
    }

    /*
     * 获取收藏的歌单的标签
     * */
    private HashMap<String, Integer> getStarSonglistTags(String account) {
        HashMap<String, Integer> result = new HashMap<>();

        Criteria criteria = Criteria.where("account").is(account);
        List<UserStarSonglist> userStarSonglists = mongoTemplate.find(Query.query(criteria), UserStarSonglist.class);

        for (UserStarSonglist userStarSonglist : userStarSonglists) {
            Integer songlistId = userStarSonglist.getSonglistId();
            getSonglistTagMap(result, songlistId);
        }

        return result;
    }

    //获取X天前的时间对象
    private Date getDateBeforeXDay(int day) {
        Calendar now = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, now.get(Calendar.DAY_OF_YEAR) - day);

        return calendar.getTime();
    }

    //获取歌曲的标签列表
    private void getSongTagMap(HashMap<String, Integer> result, Integer songId) {
        LambdaQueryWrapper<SongTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SongTag::getSongId, songId);
        List<SongTag> songTags = songTagMapper.selectList(queryWrapper);
        for (SongTag songTag : songTags) {
            Integer tagId = songTag.getTagId();
            LambdaQueryWrapper<DictTag> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(DictTag::getId, tagId);
            DictTag dictTag = dictTagMapper.selectOne(queryWrapper1);

            //添加到map
            String key = dictTag.getText();
            //新增
            result.merge(key, 1, Integer::sum);


        }
    }

    //获取歌单的标签列表
    private void getSonglistTagMap(HashMap<String, Integer> result, Integer songlistId) {
        LambdaQueryWrapper<Songlist> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Songlist::getId, songlistId);
        Songlist songlist = songlistMapper.selectOne(queryWrapper);

        if (songlist == null) {
            return;
        }

        String tag = songlist.getTag();

        LambdaQueryWrapper<DictTag> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(DictTag::getText, tag);
        DictTag dictTag = dictTagMapper.selectOne(queryWrapper1);

        //添加到map
        String key = dictTag.getText();

        result.merge(key, 1, Integer::sum);
    }
}
