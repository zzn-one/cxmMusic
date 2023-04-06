<template>
    <div class="edit_box">
        <div class="msg-box">
            <div class="img-box msg-box-item">
                <img src="@/assets/2.jpg" alt="">
            </div>
            <div class="text-box msg-box-item">
                <div class="text-msg-box">
                    <div class="text-box-item text-name-box">

                        {{ songlist.name }}
                    </div>
                    <div class="text-box-item text-author-box">
                        <i class="el-icon-user"></i>
                        {{ songlist.authorName }}
                    </div>
                    <div v-show="songlist.tag !== null" class="text-box-item text-tag-box">
                        <el-tag type="success">{{ songlist.tag }}</el-tag>
                    </div>
                    <div class="text-box-item text-introduction-box">
                        {{ songlist.introduction }}
                    </div>
                </div>
                <div>
                    <el-button class="msg-btns" type="primary" plain>
                        <i class="el-icon-video-play"></i>
                        播放
                    </el-button>
                    <el-button class="msg-btns" type="success" plain>
                        <i class="el-icon-star-off"></i>
                        收藏
                    </el-button>
                    <el-button class="msg-btns" type="warning" plain>
                        <i class="el-icon-edit"></i>
                        编辑
                    </el-button>
                </div>
            </div>

        </div>
        <div class="table-box">
            <div class="table-nav-box">
                <router-link :to="{}">
                    歌曲 {{ }}
                </router-link>
                <router-link :to="{}">
                    最近收藏
                </router-link>
                <router-link :to="{}">
                    评论
                </router-link>
            </div>
            <div class="table-data-box">
                <router-view></router-view>
            </div>

        </div>
    </div>
</template>

<script>
export default {
    name: "SongListEdit",
    data() {
        return {
            songlist: {},
            songlistId: '',
        }
    },
    methods: {
        //获取歌单对象
        async getSonglist() {
            const resp = await this.$axios("/songList/" + this.songlistId)

            const code = resp.data.code

            if (code === 200) {
                this.songlist = resp.data.data
            }
        }
    },
    created() {
        this.songlistId = this.$route.query.id

        this.getSonglist()
    }
}
</script>

<style scoped lang="less">
.edit_box {
    width: 1200px;
    margin: 0 auto;
}

.msg-box {
    display: flex;
}

.msg-box-item {}

.img-box {
    flex: 1;
    margin: 10px;
}

.img-box img {
    width: 200px;
    height: 200px;
    border-radius: 10px;
}

.text-box {
    flex: 4;
}

.table-nav-box {
    margin: 10px;
}

.text-box-item {
    margin: 10px 0;
}

.text-name-box {
    font-size: 24px;
}

.msg-btns {
    margin-top: 20px;
    width: 100px;
}

.text-msg-box {
    height: 140px;
}

.text-introduction-box {
    overflow: hidden;
    height: 42px;
    color: rgb(124, 124, 124);
}
</style>