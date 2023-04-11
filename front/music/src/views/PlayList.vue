<template>
    <div class="playList-box" id="bgImg">
        <!-- 透明遮罩 -->
        <div class="playList-mask-box">
            <!-- 头部 -->
            <div class="header-box">
                <div class="header-logo">
                    <a href="/" style="color: rgba(3, 3, 3, 0.616)"
                        @mouseover="$event.target.style.color = 'rgb(156, 193, 224)'"
                        @mouseout="$event.target.style.color = 'black'" target="_blank">
                        {{ logo }}
                    </a>
                </div>
                <div class="header-avatar">
                    <a href="/#/home/userMusic/userSong" target="_blank">

                        <div class="header-name-box">
                            {{ $token().name }}
                        </div>

                        <div class="header-img-box">
                            <img :src="$token().avatarUrl" class="header-img-body">
                        </div>
                    </a>
                </div>
            </div>

            <!-- 播放器主体 -->
            <div class="play-body-box">
                <!-- 歌曲列表 + 歌曲信息展示 -->
                <div class="song-box">
                    <!-- 歌曲列表 -->
                    <div class="song-list-box">
                        <div class="song-btns-box">
                            <el-button icon="el-icon-star-off" class="song-btn" @click="starSongs" type="warning" >收藏</el-button>
                            <el-button icon="el-icon-close" class="song-btn" @click="deleteSongs" type="danger" >删除</el-button>
                            <el-button icon="el-icon-delete" class="song-btn" @click="deleteAll" type="danger" >清空列表</el-button>

                            <el-popover placement="right" width="150" trigger="click">
                                <AddBtnPopoverContent :selectdSongs="multipleSelection"></AddBtnPopoverContent>
                                <el-button slot="reference" class="song-btn" type="primary" 
                                    icon="el-icon-plus">添加到</el-button>
                            </el-popover>
                        </div>
                        <div class="song-table-box">
                            <el-table ref="multipleTable" :data="songsTableData" tooltip-effect="dark" style="width: 100%;"
                                height="528px" @selection-change="handleSelectionChange">
                                <el-table-column type="selection" width="55">
                                </el-table-column>
                                <el-table-column type="index" width="50">
                                </el-table-column>
                                <el-table-column label="歌曲">
                                    <template slot-scope="scope">
                                        <el-button type="text" @click="songNameClick(scope)" style="color: black;">
                                            <span class="playingSong-span" v-if="currentIndex === scope.$index">
                                                <img style="display: flex:1;"
                                                    src="https://img.icons8.com/color/24/null/audio-wave2.png" />
                                                <div style="display: inline-block;flex:1">
                                                    &nbsp&nbsp&nbsp{{ scope.row.name }}
                                                </div>

                                            </span>

                                            <span v-else>{{ scope.row.name }}</span>
                                        </el-button>
                                    </template>
                                </el-table-column>
                                <el-table-column label="歌手" width="300">
                                    <template slot-scope="scope">
                                        <a :href="'/#/home/musicHall/singerDetail?singerId=' + singer.id"
                                            v-for="singer in scope.row.singerList" target="_blank" rel="noopener noreferrer"
                                            :key="singer.id">
                                            {{ singer.name }}
                                        </a>
                                    </template>
                                </el-table-column>
                                <el-table-column prop="duration" label="时长" width="80" show-overflow-tooltip>
                                    <template slot-scope="scope">
                                        {{ $moment(scope.row.duration).format("mm:ss") }}
                                    </template>
                                </el-table-column>
                            </el-table>
                        </div>

                    </div>
                    <!-- 歌曲信息展示 -->
                    <div class="song-msg-box">
                        <div class="song-info-box">
                            <img class="song-info-img" :src="songsTableData[currentIndex].imgUrl">
                            <div class="song-info-item">
                                歌曲名：{{ songsTableData[currentIndex].name }}
                            </div>
                            <div class="song-info-item">
                                歌手：
                                <span v-for="singer in songsTableData[currentIndex].singerList">
                                    {{ singer.name }}
                                </span>

                            </div>

                        </div>

                    </div>
                </div>
                <!-- 底部 播放组件 -->
                <div class="play-box">
                    <MusicPlayer>

                    </MusicPlayer>
                </div>
            </div>


        </div>
    </div>
</template>
  
<script>
import MusicPlayer from '@/components/playList/MusicPlayer.vue';
import star from '@/assets/js/starSong';
import io from 'socket.io-client';
import AddBtnPopoverContent from '@/components/home/other/AddBtnPopoverContent.vue';


export default {
    name: "PlayList",
    components: {
        MusicPlayer,
        AddBtnPopoverContent,
    },
    data() {
        return {
            logo: "MM音乐",
            songsTableData: [{
                singer: {
                    imgUrl: "defaultAvatar",
                    name: "歌名"
                }
            }],
            multipleSelection: [],
            currentIndex: 0,
            socket: ""
        }
    },
    methods: {
        //选择变动事件  val 被选中的对象
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        //获取播放列表
        async getSongs() {
            const resp = await this.$axios("/play/" + this.$token().account)

            const code = resp.data.code
            if (code === 200) {
                this.songsTableData = resp.data.data
            }
        },
        //删除部分歌曲
        deleteSongs() {
            if (this.multipleSelection.length === 0) {
                this.$message({
                    message: "请先选择歌曲！",
                    type: "error"
                })
            }
            this.$axios({
                method: "delete",
                data: this.multipleSelection,
                url: "/play/" + this.$token().account
            })
        },
        deleteAll() {
            this.$axios({
                method: "delete",
                url: "/play/all/" + this.$token().account
            })
        },
        //收藏按钮
        async starSongs() {
            const account = this.$token().account
            if (this.multipleSelection.length > 0) {


                const resp = await star(this.multipleSelection, account)

                const code = resp.data.code
                if (code === 200) {
                    this.$message({
                        message: "已添加到收藏列表",
                        type: "success"
                    })
                }

            } else {
                this.$message({
                    message: "需要先选择歌曲哟~",
                    type: "warning"
                })
            }
        },
        //socket绑定事件
        socketInit() {
            const account = this.$token().account
            this.socket = io("http://127.0.0.1:9999", {
                reconnectionDelayMax: 10000,
                query: {
                    "account": account
                }
            })
            //监听索引更新事件
            this.socket.on("index_update", (data) => {
                this.currentIndex = parseInt(data)
            })

            //监听播放列表更新事件
            this.socket.on("play_song_update", (data) => {
                this.songsTableData = JSON.parse(data)
            })
        },
        //背景图更换
        changeBgImg() {
            const divNode = document.getElementById("bgImg")
            let url = "url(" + this.songsTableData[this.currentIndex].imgUrl + ")"
            divNode.style.backgroundImage = url
        },
        //歌名点击事件
        songNameClick(scope) {
            //修改播放索引
            this.updatePlayIndex(scope.$index)
        },
        //修改播放索引
        async updatePlayIndex(index) {
            let account = this.$token().account
            const resp = await this.$axios({
                method: "put",
                url: "/play/playIndex/" + account + "/" + index,
            })
        },

    },
    async created() {


        await this.getSongs()

        await this.socketInit()
        this.socket.connect()

        //初始化背景图
        this.changeBgImg()
    },
    beforeDestroy() {
        this.socket.disconnect()
    },
    watch: {
        currentIndex() {
            this.changeBgImg()
        },
        "songsTableData.length"(val) {
            if (val === 0) {
                this.$message.warning("播放列表已被清空,播放页将在3秒后关闭")

                setTimeout(() => { window.close() }, 3000)

            }
        }
    }
}
</script>
  
<style lang="less" scoped>
.playList-box {
    width: 100%;
    height: 969px;
    background-size: 100%;
    color: rgb(77, 76, 76);
}

.playList-mask-box {
    width: 100%;
    height: 969px;
    background-color: rgba(211, 205, 205, 0.7);
    backdrop-filter: blur(40px);
}

.playingSong-span {

    display: flex;
    color: #ffffff;
    font-size: 15px;
    height: 24px;
    line-height: 24px;
}

.header-box {
    display: flex;
    color: rgba(3, 3, 3, 0.616);
    margin-bottom: 90px;
}

.header-logo {

    flex: 1;
    float: left;
    margin-left: 40px;
    height: 50px;
    line-height: 50px;
    font-size: 20px;
    margin-top: 10px;
}

.header-avatar {
    margin-top: 10px;
    flex: 1;
}

.header-img-box {
    float: right;
    height: 50px;
    width: 50px;
}

.header-img-body {
    height: 50px;
    width: 50px;
    border-radius: 60px;

}

.header-name-box {
    float: right;
    height: 50px;
    line-height: 50px;
    margin-right: 20px;
}

.play-body-box {
    margin: 10px 140px;
}

.song-box {
    display: flex;
}

.song-list-box {
    flex: 3;
}

.song-msg-box {
    flex: 1;
    background-color: rgba(255, 255, 255, 0);
}

.song-btn {
    margin: 10px 10px 10px 0;
    width: 120px;
}

/*修改el-table背景色*/
.song-table-box {
    width: 100%;
    margin: auto;
}

.song-table-box /deep/ .el-table--fit {
    padding: 0px;
}

.song-table-box /deep/ .el-table,
.el-table__expanded-cell {
    background-color: transparent;
}

.song-table-box /deep/ .el-table tr {
    background-color: transparent !important;
}

.song-table-box /deep/ .el-table th {
    background-color: transparent !important;
}

.song-table-box /deep/ .el-table--enable-row-transition .el-table__body td,
.el-table .cell {
    background-color: transparent;
}

.el-table::before {
    left: 0;
    bottom: 0;
    width: 100%;
    height: 0px;
}

/*歌曲信息*/
.song-info-box {
    margin-left: 40px;
}

.song-info-img {
    margin: 0 auto;
    width: 300px;
    height: 300px;
}

.song-info-item {
    font-size: 18px;
    text-align: center;
    margin: 10px 0;
}

.play-box {
    margin-top: 50px;
    min-width: 1200px;
}


/deep/.el-table__body-wrapper::-webkit-scrollbar {
    width: 8px;
    height: 8px;
}

/deep/.el-table__body-wrapper::-webkit-scrollbar-thumb {
    border-radius: 5px;
    background-color: #ffffff;
}
</style>