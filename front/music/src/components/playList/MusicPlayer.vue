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
                    {{ songName }} - {{ singerName }}
                </div>
                <!-- 已播放时间/总时间 -->
                <div class="play-song-msg-time">
                    {{ $moment(this.playedTime * 1000).format("mm:ss") }} / {{ $moment(this.duration * 1000).format("mm:ss") }}
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
        <audio id="audio">
            <source :src="songUrl" type="audio/ogg">
            <source :src="songUrl" type="audio/mpeg">
        </audio>
    </div>
</template>
    
<script>
export default {
    name: "MusicPlayer",
    data() {
        return {
            //播放列表
            songList: [],
            //当前歌曲索引
            currentIndex: 0,

            songName: '',
            singerName: '',

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
                this.currentIndex = (this.currentIndex + 1) % songNumber
            }
            //播放 延迟一秒
            setTimeout(() => {
                this.play()
            }, 1000)

        },



        //假数据
        testData() {
            this.songList.push(
                {
                    name: "泡沫",
                    singerName: "邓紫棋",
                    sourceUrl: "http://music.163.com/song/media/outer/url?id=233931.mp3"
                },
                {
                    name: "关于本人生活状态的报告",
                    singerName: "天然卷夏祭,Sx",
                    sourceUrl: "http://music.163.com/song/media/outer/url?id=1447792630.mp3"
                },
                {
                    name: "请吃饭的关系",
                    singerName: "不靠谱组合",
                    sourceUrl: "http://ws.stream.qqmusic.qq.com/C400000aZWZr4TuUOi.m4a?guid=878565261&vkey=F128FB7C34D46ECB841B8E2DD23FE06015C8FBDB0C5DF8E60FEE868613EEF896793FB48A1270FDDED989CAF12F5ED1BCAD423B7430CF98AA&uin=&fromtag=120032"
                },
                {
                    name: "差不多姑娘",
                    singerName: "邓紫棋",
                    sourceUrl: "http://ws.stream.qqmusic.qq.com/C400004VYbhm18YnBH.m4a?guid=124560908&vkey=7721D4102DA6B179C0EEC02ABE56C991E966604577D9C0335C17E3B3262A5B9D441654766D6996E3294E01D40F704DDB92C8DFF2E386B016&uin=&fromtag=120032"
                },
                {
                    name: "给你一瓶魔法药水",
                    singerName: "告五人",
                    sourceUrl: "http://music.163.com/song/media/outer/url?id=1959667345.mp3"
                },

            )
        },
        //歌曲索引变动 改变当前播放器的歌曲信息
        changeSongMsg() {
            this.songUrl = this.songList[this.currentIndex].sourceUrl
            this.songName = this.songList[this.currentIndex].name
            this.singerName = this.songList[this.currentIndex].singerName
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
                } else {
                    this.nextSong()
                }
            }, 1000)
        },

    },
    watch: {
        songUrl(n) {
            const audioDOM = document.getElementById("audio");
            //更新歌曲信息
            if (n !== '') {
                setTimeout(() => {
                    //获取歌曲时长
                    if (isNaN(audioDOM.duration)) {
                        console.log("歌曲未加载1");
                    } else {
                        this.duration = audioDOM.duration
                    }

                    //获取歌曲播放状态
                    if (isNaN(audioDOM.paused)) {
                        console.log("歌曲未加载2");

                    } else {
                        this.paused = audioDOM.paused
                    }
                }, 1000)
            }
        },
        currentIndex() {
            this.changeSongMsg()
        },
        //音量
        volume(newValue) {
            const audioDOM = document.getElementById("audio");
            if (!isNaN(audioDOM.volume)) {
                audioDOM.volume = newValue;
            }
        }
    },
    async created() {
        //播放引导
        this.$alert("由于您的浏览器设置，音乐无法自动播放，请手动点击播放。", "MM音乐提醒您", {
            confirmButtonText: '我知道了',
            callback: action => {
                this.play()
            }
        })

        await this.testData()
        this.changeSongMsg()

        //获取歌曲当前播放进度
        this.getCurrentTime()

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