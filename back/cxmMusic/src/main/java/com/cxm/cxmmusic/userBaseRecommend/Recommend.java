package com.cxm.cxmmusic.userBaseRecommend;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class Recommend {
    /**
     * 在给定account的情况下，计算其他用户和它的距离并排序
     *
     * @param account   目标用户账号
     * @param userSongs 用户列表
     * @return
     */
    private List<Relevancy> computeNearestNeighbor(String account, List<UserSongs> userSongs) {

        ArrayList<Relevancy> relevancies = new ArrayList<>();

        UserSongs u1 = new UserSongs(account, null);

        //找到 用户自己
        for (UserSongs userSong : userSongs) {
            if (account.equals(userSong.getAccount())) {
                u1 = userSong;

                //听歌记录少于50条 推荐算法不执行
                if (u1.getSongs().size() < 50) {
                    return relevancies;
                }
            }
        }

        //计算相关系数
        for (UserSongs u2 : userSongs) {
            if (!u2.getAccount().equals(account)) {

                double distance = pearson_dis(u2.getSongs(), u1.getSongs());

                Relevancy relevancy = new Relevancy(u2.getAccount(), distance);
                relevancies.add(relevancy);
            }
        }

        return relevancies;
    }


    /**
     * 计算2个打分序列间的pearson距离
     * 皮尔森(pearson)相关系数公式
     * 选择公式四进行计算
     *
     * @param rating1 其他用户的听歌列表
     * @param rating2 “我”的听歌列表
     * @return
     */
    private double pearson_dis(List<SongTimes> rating1, List<SongTimes> rating2) {
        int n = rating1.size();

        List<Long> rating1ScoreCollect = rating1.stream().map(SongTimes::getTimes).collect(Collectors.toList());
        List<Long> rating2ScoreCollect = rating2.stream().map(SongTimes::getTimes).collect(Collectors.toList());

        double Ex = rating1ScoreCollect.stream().mapToDouble(x -> x).sum();
        double Ey = rating2ScoreCollect.stream().mapToDouble(y -> y).sum();
        double Ex2 = rating1ScoreCollect.stream().mapToDouble(x -> Math.pow(x, 2)).sum();
        double Ey2 = rating2ScoreCollect.stream().mapToDouble(y -> Math.pow(y, 2)).sum();
        double Exy = IntStream.range(0, n).mapToDouble(i -> rating1ScoreCollect.get(i) * rating2ScoreCollect.get(i)).sum();
        double numerator = Exy - Ex * Ey / n;
        double denominator = Math.sqrt((Ex2 - Math.pow(Ex, 2) / n) * (Ey2 - Math.pow(Ey, 2) / n));
        if (denominator == 0) return 0.0;
        return numerator / denominator;
    }

    /*
     * 找到最近邻听过，但是“我”没听过的歌曲，计算推荐、
     * @param account 目标用户账号
     * @param userSongs 用户列表
     * @return 未听过的歌曲的id列表
     * */
    public List<Integer> recommend(String account, List<UserSongs> userSongs) {
        //获取邻居集
        List<Relevancy> relevancyList = computeNearestNeighbor(account, userSongs);

        //按照 相关度进行排序
        List<Relevancy> relevancySorted = relevancyList.stream()
                .sorted(
                        Comparator.comparing(Relevancy::getDistance)
                                .reversed()
                ).collect(Collectors.toList());


        //最近邻 取最多10个
        ArrayList<UserSongs> neighbors = new ArrayList<>();
        //取相关度最高的（最多）10个用户
        for (Relevancy relevancy : relevancySorted) {
            if (neighbors.size() >= 10) {
                break;
            }

            Double distance = relevancy.getDistance();

            if (distance.isNaN() || distance.compareTo(0.0) < 0) {
                continue;
            }

            String relevancyAccount = relevancy.getAccount();

            for (UserSongs userSong : userSongs) {
                if (relevancyAccount.equals(userSong.getAccount())) {
                    UserSongs neighbor = new UserSongs(relevancyAccount, userSong.getSongs());
                    neighbors.add(neighbor);
                }
            }
        }

        //"我"
        UserSongs user = new UserSongs();

        for (UserSongs userSong : userSongs) {
            if (account.equals(userSong.getAccount())) {
                user = userSong;
            }
        }


        //根据自己和邻居的歌曲计算推荐的歌曲
        ArrayList<Integer> recommendationSongIds = new ArrayList<>();

        //遍历每一个邻居
        for (UserSongs neighbor : neighbors) {
            //遍历邻居的听歌列表
            for (SongTimes song : neighbor.getSongs()) {
                //对比 “我”和邻居的听歌列表
                for (SongTimes songTimes : user.getSongs()) {
                    Integer userSongId = songTimes.getSongId();
                    //收集 “我” 没听过的歌曲的id
                    if (!userSongId.equals(song.getSongId())) {
                        //去除重复id
                        recommendationSongIds.remove(song.getSongId());
                        //添加id
                        recommendationSongIds.add(song.getSongId());
                    }
                }
            }
        }


        return recommendationSongIds;
    }
}
