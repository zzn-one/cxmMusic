<template>
    <div class="detail_box">
        <div class="msg_box">
            <!-- 歌单封面 -->
            <div class="img_box">
                <img :src="$imgPrefix + songList.imgUrl">
            </div>
            <!-- 歌单信息的简要说明 -->
            <div class="brief_box">
                <el-descriptions :title="songList.name" :column="1" class="brief_descriptions">
                    <el-descriptions-item label="作者">{{ songList.authorName }}</el-descriptions-item>
                    <el-descriptions-item label="标签">
                        <el-tag v-if="songList.tag !== null">
                            {{ songList.tag }}
                        </el-tag>

                    </el-descriptions-item>
                    <el-descriptions-item label="播放量">{{ numberFormat(songList.playNumber) }}</el-descriptions-item>
                    <el-descriptions-item label="收藏量">{{ numberFormat(songList.starNumber) }}</el-descriptions-item>
                </el-descriptions>
                <div class="brief_btns">
                    <el-button icon="el-icon-video-play" class="btns" type="success" @click="playSonglist">
                        播放全部
                    </el-button>
                    <el-button icon="el-icon-star-off" class="btns" v-show="!stared" @click="star">
                        收藏
                    </el-button>
                    <el-button icon="el-icon-star-on" class="btns"  type="danger" v-show="stared" @click="cancelStar">
                        已收藏
                    </el-button>

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
                    <el-table :data="songTable" stripe style="width: 100%" height="528px">
                        <el-table-column type="index" width="50"></el-table-column>
                        <el-table-column prop="name" :label="'歌曲(' + songList.songNumber + ')'">
                        </el-table-column>
                        <el-table-column label="歌手" width="300">
                            <template slot-scope="scope">
                                <RouterLink v-for="singer in scope.row.singerList" :key="singer.id" :to='{
                                    name: "singerDetail", query: { singerId: singer.id }
                                }'>
                                    {{ singer.name }}
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
                <!-- <div class="comment_box">
                    评论区 后面写
                </div> -->

                <!-- 占位 -->
                <div style="height: 50px;">

                </div>
            </div>
            <!-- 简介 -->
            <div class="introduce_box">
                <div class="introduce_box_header">
                    <b>简介</b>
                </div>
                <div class="introduce_box_body">{{ songList.introduction }}</div>
            </div>
        </div>
    </div>
</template>

<script>

export default {
    name: "SongListDetail",
    data() {
        return {
            id: "",
            songList: {},
            songTable: [],
            stared: false,

        };
    },
    methods: {
        //取消收藏
        async cancelStar() {

            const account = this.$token().account
            const resp = await this.$axios({
                url: "/starNumber/songlist/cancel/" + account + "/" + this.id,
                method: "delete"
            })
            const code = resp.data.code

            if (code === 200) {
                this.$message.success("歌单已取消收藏")

                //刷新用户对歌单的收藏状态
                this.starStatus()
            } else {
                this.$message.error("歌单取消收藏失败")
            }

        },
        //收藏
        async star() {
            const account = this.$token().account
            const resp = await this.$axios({
                url: "/starNumber/songlist/" + account + "/" + this.id,
                method: "post"
            })
            const code = resp.data.code

            if (code === 200) {
                this.$message.success("歌单收藏成功")
                //刷新用户对歌单的收藏状态
                this.starStatus()

            } else {
                this.$message.error("歌单收藏失败")
            }

        },
        //查询用户是否已经收藏该歌单
        async starStatus() {
            const account = this.$token().account
            const resp = await this.$axios("/starNumber/songlist/stared/" + account + "/" + this.id)
            const code = resp.data.code

            if (code === 200) {
                this.stared = resp.data.data
            }
        },

        //数量格式化
        numberFormat(number) {

            if (number >= 100000000) {
                let x = (number / 100000000).toFixed(2)
                number = x + "亿"
            } else if (number >= 10000) {
                let x = (number / 10000).toFixed(2)
                number = x + "万"
            }
            return number
        },
        //获取歌单的信息
        async getSongList() {
            //获取歌单id
            this.id = this.$route.query.id;
            const resp = await this.$axios("/songList/" + this.id)

            const code = resp.data.code

            if (code === 200) {
                this.songList = resp.data.data
            }
        },
        //获取歌曲列表
        async getSongTable() {
            const resp = await this.$axios("/songList/songs/" + this.id)

            const code = resp.data.code

            if (code === 200) {
                this.songTable = resp.data.data
            }
        },
        //播放歌单
        async playSonglist() {
            const account = this.$token().account

            const resp = await this.$axios({
                url: "/play/" + account + "/" + this.id,
                method: "put"
            })

            const code = resp.data.code

            if (code === 200) {
                //跳转到播放页
                const { href } = this.$router.resolve({
                    name: "playList"
                })

                window.open(href, href)
            }
        },
    },
    created() {

        //获取歌单的信息
        this.getSongList()
        //获取歌曲列表
        this.getSongTable()
        //查询用户是否已经收藏该歌单
        this.starStatus()

    },

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