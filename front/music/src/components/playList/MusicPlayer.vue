<template>
    <div class="player-box">
        <!-- 1.按钮区 -->
        <div class="play-btns-box">
            <div class="play-btns-body">
                <!-- 上一首 -->
                <el-button type="text" class="play-btns-item">
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
                <el-button type="text" class="play-btns-item">
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
                    {{ timeFormat(playedTime) }} / {{ timeFormat(duration) }}
                </div>
            </div>
            <!-- 进度条 -->
            <div>
                <el-slider class="play-slider" v-model="percentage"
                    :format-tooltip="v => { return timeFormat(this.playedTime) }"></el-slider>
            </div>

        </div>
        <!-- 3.占位 -->
        <div class="play-placeHolder-box">

        </div>
        <!-- 播放控件 -->
        <audio id="audio" preload="auto" :src="songUrl">
        </audio>
    </div>
</template>
    
<script>
export default {
    name: "MusicPlayer",
    data() {
        return {
            //当前歌曲索引
            currentIndex: 0,

            songName: '',
            singerName: '',
            playedTime: 0,
            duration: 0,

            paused: false,

            // 歌曲的url
            songUrl: '',
        }
    },
    computed: {
        // 进度条进度
        percentage() {
            return this.playedTime / this.duration * 100
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

        //下一首

        //时间的格式转换
        timeFormat(v) {
            let minute = Math.floor(v / 60)
            let second = (v % 60).toFixed(0)
            return minute + ":" + second
        }
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

                    //获取歌曲当前播放时长
                    if (!audioDOM.ended) {
                        setInterval(() => {
                            if (isNaN(audioDOM.currentTime)) {
                                console.log("歌曲未加载3");
                            } else {
                                this.playedTime = audioDOM.currentTime
                            }
                        }, 1000)
                    }

                }, 1000)
            }
        }
    },
    created() {
        this.songUrl = 'http://music.163.com/song/media/outer/url?id=233931.mp3'

        this.songName = '明明就'
        this.singerName = '周杰伦'

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
    flex: 17;
}

.play-placeHolder-box {
    height: 100px;
    flex: 6;
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
</style>