<template>
    <div class="detail_box">
        <div class="msg_box">
            <!-- 歌单封面 -->
            <div class="img_box">
                <img src="@/assets/bg.jpg">
            </div>
            <!-- 歌单信息的简要说明 -->
            <div class="brief_box">
                <el-descriptions :title="songList.name" :column="1" class="brief_descriptions">
                    <el-descriptions-item label="作者">{{ songList.authorName }}</el-descriptions-item>
                    <el-descriptions-item label="标签：">{{ songList.tag }}</el-descriptions-item>
                    <el-descriptions-item label="播放量：">{{ songList.playNumber }}万</el-descriptions-item>
                    <el-descriptions-item label="收藏量">{{ songList.favoritesNumber }}万</el-descriptions-item>
                </el-descriptions>
                <div class="brief_btns">
                    <el-button icon="el-icon-video-play" class="btns" type="success">
                        播放全部
                    </el-button>
                    <el-button icon="el-icon-star-off" class="btns">
                        收藏
                    </el-button>
                    <el-popover placement="right" width="180" trigger="manual" v-model="popoverVisible">
                        <div>
                            临时内容
                        </div>
                        <el-button slot="reference" class="btns" icon="el-icon-circle-plus-outline"
                            @click="popoverVisible = !popoverVisible" style="margin-left: 10px;">添加到</el-button>
                    </el-popover>

                </div>
            </div>
            <!-- 占位 -->
            <div class="placeHolder_box"></div>
        </div>
        <!-- 歌曲列表和简介 -->
        <div class="song_box">
            <!-- 歌曲列表table与评论区 -->
            <div class="list_comment_box">
                <div class="table_box">
                    <el-table :data="songTable" stripe style="width: 100%" height="528px"
                        @selection-change="handleSelectionChange">
                        <el-table-column type="selection" width="55"></el-table-column>
                        <el-table-column type="index" width="50"></el-table-column>
                        <el-table-column prop="name" label="歌曲">
                        </el-table-column>
                        <el-table-column label="歌手" width="300">
                            <template slot-scope="scope">
                                <RouterLink :to='{
                                    name: "singerDetail", params: { id: 1 }
                                }'>
                                    {{ scope.row.songerName }}
                                </RouterLink>
                            </template>
                        </el-table-column>
                        <el-table-column label="时长" width="80">
                            <template slot-scope="scope">
                                {{ $moment(scope.row.duration).format("mm:ss") }}
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
                <div class="comment_box">
                    评论区 后面写
                </div>
            </div>
            <!-- 简介 -->
            <div class="introduce_box">
                <div class="introduce_box_header">
                    <b>简介</b>
                </div>
                <div class="introduce_box_body">{{ songList.introduce }}</div>
            </div>
        </div>
    </div>
</template>

<script>
import { RouterLink } from 'vue-router';



export default {
    name: "SongListDetail",
    data() {
        return {
            id: "",
            songList: {},
            popoverVisible: false,
            songTable: [],
            //被选中的数据
            multipleSelection: []
        };
    },
    methods: {
        //表格选中数据
        handleSelectionChange(val) {
            this.multipleSelection = val;
        }
    },
    created() {
        //获取歌单id
        this.id = this.$route.params.id;
        // 假数据
        this.songList = {
            name: "流行粤语歌曲,歌单名称最长限制为15",
            authorName: "疯原万叶",
            tag: "流行",
            playNumber: 8392.2,
            favoritesNumber: 22.3,
            introduce: "“996”“007”已成当代打工人的日常， 下班了即兴唱两句歌， 对于释放压力缓解疲劳， 有着很好的辅助作用， 快来唱歌吧！ 把烦恼紧张压抑都丢掉， 重新拾起生活的快乐！"
        };
        for (let i = 1; i <= 100; i++) {
            this.songTable.push({
                name: "半岛铁盒",
                songerName: "周杰伦",
                duration: 239000,
            });
        }
    },
    components: { RouterLink }
}
</script>

<style scoped lang="less">
.msg_box {
    display: flex;
    margin: 50px 0;

}

.img_box {
    flex: 1;
}

.img_box>img {
    width: 270px;
    height: 270px;
}

.brief_box {
    flex: 2;
    margin-left: 30px;
}

.placeHolder_box {
    flex: 1;
}

::v-deep .el-descriptions__body {
    background-color: rgba(255, 255, 255, 0);
    font-size: 16px;
}

/deep/ .el-descriptions__title {
    margin-left: 20px;
    font-size: 32px;
    font-weight: normal;
}

.btns {
    width: 150px;
    font-size: 16px;
}

/*下半部分*/
.song_box {
    display: flex;
}

.list_comment_box {
    flex: 2;
}

.introduce_box {
    flex: 1;
    margin-left: 40px;
}
</style>