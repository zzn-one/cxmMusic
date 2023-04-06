<template>
    <div>
        <!-- 添加到播放列表 -->
        <el-button class="btns" type="text" @click="addToPlayListBtn">播放列表</el-button>
        <el-divider></el-divider>
        <!-- 用户的歌单 -->
        <div class="songListName-box" v-for="songList in songListNames" :key="songList.id">
            <el-button class="btns" type="text" @click="addSong(songList)">
                {{ songList.name }}
            </el-button>
        </div>


        <el-divider></el-divider>
        <!-- 新建歌单 -->
        <el-button class="btns" type="text" @click="newSongListBtn">新建歌单</el-button>

        <!-- 新建歌单表单窗口 -->
        <el-dialog title="创建新歌单" :modal="false" :visible.sync="dialogVisible" width="600px" center>
            <div class="input-box">
                <div class="input-box-item input-label">歌单名</div>
                <div class="input-box-item input-content"><el-input v-model="songListForm.name"></el-input></div>
            </div>

            <span slot="footer" class="dialog-footer">
                <el-button style="width: 160px;" @click="dialogVisible = false">取 消</el-button>
                <el-button style="width: 160px;" type="success" @click="newSongList">确 定</el-button>
            </span>
        </el-dialog>

    </div>
</template>

<script>
import play from '@/assets/js/playSong';

export default {
    name: "AddBtnPopoverContent",
    props: ["selectdSongs"],
    data() {
        return {
            dialogVisible: false,
            songListForm: {
                name: "",
                authorName: "",
                authorAccount: "",
                songs: []
            },
            //用户的歌单列表
            songListNames: [],
        }
    },
    methods: {
        //新建歌单按钮
        newSongListBtn() {
            this.dialogVisible = true
        },
        //新建歌单请求
        async newSongList() {
            this.songListForm.authorName = this.$token().name
            this.songListForm.authorAccount = this.$token().account


            this.songListForm.songs = this.selectdSongs



            if (this.songListForm.name !== null && this.songListForm.name !== '') {
                const resp = await this.$axios({
                    method: "post",
                    url: "/songList",
                    data: this.songListForm
                })
                if (resp.data.code === 200) {
                    this.$message.success("歌单创建成功")

                    this.dialogVisible = false
                    //重新请求用户的歌单列表
                    this.getSongListNames()
                } else {
                    this.$message.error("创建歌单出错")
                }

            } else {
                this.$message.error("请输入歌单名！")
            }

        },
        //获取用户的歌单列表
        async getSongListNames() {
            const account = this.$token().account
            const resp = await this.$axios("/songList/" + account)

            if (resp.data.code === 200 && resp.data.data !== null) {
                this.songListNames = resp.data.data
            }

        },
        //给歌单增加歌曲
        async addSong(songList) {
            const songlistId = songList.id

            if (this.selectdSongs.length === 0) {
                this.$message.error("请先选择歌曲")
            } else {
                const resp = await this.$axios({
                    method: "put",
                    data: this.selectdSongs,
                    url: "/songList/addSong/" + songlistId
                })

                if (resp.data.code === 200) {
                    this.$message.success("歌曲已成功添加到歌单")
                }
            }
        },
        //添加到播放列表按钮
        addToPlayListBtn() {

            if (this.selectdSongs.length > 0) {
                const account = this.$token().account
                play(this.selectdSongs, account)
            } else {
                this.$message.warning("请选择歌曲")
            }
        }
    },
    created() {
        this.getSongListNames()
    }
}
</script>

<style scoped lang="less">
.btns {
    padding: 0;
    color: black;
    font-size: 14px;
    margin: 0 0 10px 30px;
}

.input-box {
    display: flex;
}

.input-box-item {}

.input-label {
    flex: 1;
    line-height: 40px;
    font-size: 16px;
}

.input-content {
    flex: 6;
}

/deep/.el-divider--horizontal {
    margin: 5px 0;
}

.songListName-box {
    width: 150px;
    overflow: hidden;
}
</style>