<template>
    <div class="player-box">
        <!-- 1.按钮区 -->
        <div class="play-btns-box">
            <div class="play-btns-body">
                <!-- 上一首 -->
                <el-button type="text" class="play-btns-item" @click="previousSong">
                    <img src="https://img.icons8.com/ios/20/null/left-squared--v1.png" />
                </el-button>


                <!-- 播放 或 暂停-->
                <el-button type="text" class="play-btns-item" @click="play" v-show="paused">
                    <img src="https://img.icons8.com/ios/20/null/play--v1.png" />
                </el-button>

                <el-button type="text" class="play-btns-item" @click="play" v-show="!paused">
                    <img src="https://img.icons8.com/ios/20/null/pause--v1.png" />
                </el-button>


                <!-- 下一首 -->
                <el-button type="text" class="play-btns-item" @click="nextSong">
                    <img src="https://img.icons8.com/ios/20/null/right-squared--v1.png" />
                </el-button>
            </div>
        </div>
        <!-- 2.进度条与歌曲信息 -->
        <div class="play-progress-box">
            <!-- 歌曲信息 -->
            <div class="play-song-msg-box">
                <!-- 歌曲名-歌手 -->
                <div class="play-song-msg-name">
                    {{ songName }} -
                    <span v-for="singer in singers " :key="singer.id">
                        {{ singer.name }}
                    </span>
                </div>
                <!-- 已播放时间/总时间 -->
                <div class="play-song-msg-time">
                    {{ $moment(this.playedTime * 1000).format("mm:ss") }} / {{ $moment(this.duration * 1000).format("mm:ss")
                    }}
                </div>
            </div>
            <!-- 进度条 -->
            <div>
                <el-slider class="play-slider" v-model="percentage" :format-tooltip="v => {
                    return this.$moment(this.playedTime).format('mm:ss')
                }"></el-slider>
            </div>

        </div>
        <!-- 3.其他功能 -->
        <div class="play-other-box">
            <!-- 音量 -->
            <el-slider v-model="volume" vertical height="50px" :max="1" :step="0.01">
            </el-slider>
            <img src="https://img.icons8.com/ios/20/null/medium-volume--v1.png" style="margin-left:10px ;" />
        </div>
        <!-- 播放控件 -->
        <audio id="audio" @ended="endPlay" @canplaythrough="canPlay">
            <source :src="songUrl" type="audio/ogg">
            <source :src="songUrl" type="audio/mpeg">
        </audio>
    </div>
</template>
    
<script>
import io from 'socket.io-client';
export default {
    name: "MusicPlayer",
    data() {
        return {
            //播放列表
            songList: [],
            //当前歌曲索引
            currentIndex: 0,

            songName: '',
            singers: [],

            // 当前已播放时长
            playedTime: 0,

            //歌曲时长
            duration: 0,

            //歌曲播放状态 暂停/正在播放
            paused: false,

            // 歌曲的url
            songUrl: '',

            //音量
            volume: 0.3,

            socket: ""

        }
    },
    computed: {
        // 进度条进度
        percentage: {
            get() {
                return this.playedTime / this.duration * 100
            },
            set(value) {
                //防止报错
            }

        },


    },
    methods: {
        // 播放按钮 和 暂停按钮
        play() {
            this.paused = !this.paused
            const audioDOM = document.getElementById("audio");
            if (audioDOM.paused == true) {  //如果是暂停状态
                audioDOM.play();  //播放音频
            } else {              //如果正在播放
                audioDOM.pause(); //暂停
            }
        },
        //上一首
        previousSong() {
            let songNumber = this.songList.length
            if (this.currentIndex > 0) {
                this.currentIndex = (this.currentIndex - 1) % songNumber
            } else {
                this.currentIndex = (songNumber - 1) % songNumber
            }
            //播放 延迟一秒
            setTimeout(() => {
                this.play()
            }, 1000)
        },
        //下一首
        nextSong() {
            let songNumber = this.songList.length
            if (this.currentIndex < songNumber) {
                this.currentIndex = parseInt(1) + parseInt(this.currentIndex) % songNumber

            }
            //播放 延迟一秒
            setTimeout(() => {
                this.play()
            }, 1000)

        },

        //获取播放列表
        async getSongs() {
            const resp = await this.$axios("/play/" + this.$token().account)

            const code = resp.data.code
            if (code === 200) {
                this.songList = resp.data.data
            }
        },
        //歌曲索引变动 改变当前播放器的歌曲信息
        changeSongMsg() {
            this.songUrl = this.songList[this.currentIndex].sourceUrl
            this.songName = this.songList[this.currentIndex].name
            this.singers = this.songList[this.currentIndex].singerList
            //重新加载
            document.getElementById("audio").load()
        },
        //获取歌曲当前播放进度
        getCurrentTime() {
            const audioDOM = document.getElementById("audio");

            setInterval(() => {
                if (!audioDOM.ended) {
                    if (isNaN(audioDOM.currentTime)) {
                        console.log("歌曲未加载3");
                    } else {
                        this.playedTime = audioDOM.currentTime
                    }
                }
            }, 1000)
        },
        //歌曲播放结束事件
        endPlay() {
            //下一首
            this.nextSong()
        },
        //发送请求，修改歌曲的播放量
        async updatePlayNumber(songId) {
            let account = this.$token().account
            const resp = await this.$axios({
                method: "put",
                url: "/playNumber/" + account + "/" + songId,

            })
        },
        //修改播放索引
        async updatePlayIndex() {
            let account = this.$token().account
            const resp = await this.$axios({
                method: "put",
                url: "/play/playIndex/" + account + "/" + this.currentIndex,
            })
        },
        //播放器初始化
        async initPlayer() {
            //播放引导
            this.$alert("由于您的浏览器设置，音乐无法自动播放，请手动点击播放。", "MM音乐提醒您", {
                confirmButtonText: '我知道了',
                callback: action => {
                    this.play()
                }
            })

            await this.getSongs()
            this.changeSongMsg()
            let song = this.songList[this.currentIndex]
            let songId = song.id
            //发送请求，修改歌曲的播放量
            this.updatePlayNumber(songId)

            //获取歌曲当前播放进度
            this.getCurrentTime()

        },

        //歌曲可以完全播放事件
        canPlay() {
            const audioDOM = document.getElementById("audio");
            //获取歌曲时长
            this.duration = audioDOM.duration
            //获取歌曲播放状态
            this.paused = audioDOM.paused
        },
        //socket绑定事件
        socketInit() {
            const prefix = "player:"
            const account = this.$token().account
            this.socket = io("http://127.0.0.1:9999", {
                reconnectionDelayMax: 10000,
                query: {
                    "account": prefix + account
                }
            })
            //监听索引更新事件
            this.socket.on("index_update", (data) => {
                this.currentIndex = parseInt(data)
            })

            //监听播放列表更新事件
            this.socket.on("play_song_update", (data) => {
                this.songList = JSON.parse(data)
            })
        }
    },
    watch: {
        currentIndex() {
            this.changeSongMsg()
            //修改播放索引
            this.updatePlayIndex()

            let song = this.songList[this.currentIndex]
            let songId = song.id
            //发送请求，修改歌曲的播放量
            this.updatePlayNumber(songId)
        },
        //音量
        volume(newValue) {
            const audioDOM = document.getElementById("audio");
            if (!isNaN(audioDOM.volume)) {
                audioDOM.volume = newValue;
            }
        },
    },
    async created() {

        await this.initPlayer()

        //连接socket服务器
        await this.socketInit()
        this.socket.connect()
    },
    beforeDestroy() {
        //关闭socket服务器
        this.socket.disconnect()
    }
}
</script>

<style scoped lang="less">
.player-box {
    display: flex;
}

.play-btns-item {
    margin-right: 20px;
}

.play-btns-body {
    height: 100px;
    line-height: 100px;
}

.play-btns-box {
    flex: 3;
    min-width: 180px;
}

.play-progress-box {
    height: 100px;
    flex: 16;
}

.play-other-box {
    height: 100px;
    flex: 7;
}

/*歌曲信息*/
.play-song-msg-box {
    height: 50px;
    line-height: 50px;
}

/*进度条上面的信息*/
.play-song-msg-name {
    position: absolute;
    float: left;
}

.play-song-msg-time {
    float: right;
}

/*进度条*/
/deep/.el-slider__bar {
    background-color: rgb(195, 200, 204);
}

/*音量进度条的按钮*/
/deep/.el-slider__button {
    width: 0;
    height: 0;
    border: none;

}
</style>