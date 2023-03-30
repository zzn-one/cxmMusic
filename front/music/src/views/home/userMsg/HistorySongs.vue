<template>
    <div class="main-box">
        <div class="title-box">
            <div class="title-item-box song-name-box">歌名</div>
            <div class="title-item-box singer-name-box">歌手</div>
            <div class="title-item-box song-play-time-box">播放时间</div>
        </div>
        <div class="record-box" v-infinite-scroll="load">
            <el-card class="box-card" shadow="hover" v-for="record in historySongs" :body-style="{ padding: '10px' }">
                <div class="msg-box">
                    <div class="msg-item-box song-name-box">{{ record.song.name }}</div>
                    <div class="msg-item-box singer-name-box">{{ singerNameFormat(record.singerNames) }}</div>
                    <div class="msg-item-box song-play-time-box">{{ timeFormat(new Date(record.time)) }}</div>
                </div>

            </el-card>
            <div v-show="tipVisible" class="bottom-tip-box">已经到底啦~</div>
            <div v-show="!tipVisible" class="bottom-tip-box">下滑加载更多内容~</div>
        </div>
    </div>
</template>

<script>
export default {
    name: "historySongs",
    data() {
        return {
            tipVisible: false,
            currentPage: 1,
            pageSize: 10,
            maxPage: 0,
            historySongs: [],
        }
    },
    methods: {
        //无限滚动的加载方法
        load() {
            if (this.currentPage < this.maxPage) {
                this.currentPage += 1
            }
        },
        //时间显示格式
        timeFormat(date) {
            let today = new Date()
            if (today.getFullYear() === date.getFullYear() && today.getMonth() === date.getMonth() && today.getDate() === date.getDate()) {
                return "今天" + this.$moment(date).format("HH:mm")
            } else if (today.getFullYear() === date.getFullYear() && today.getMonth() === date.getMonth() && (date.getDate() - today.getDate()) === -1) {
                return "昨天" + this.$moment(date).format("HH:mm")
            } else if (today.getFullYear() === date.getFullYear()) {
                return this.$moment(date).format("M月D日 HH:mm")
            } else {
                return this.$moment(date).format("yyyy年M月D日 HH:mm")
            }

        },
        //歌手名称显示歌手
        singerNameFormat(singerNames) {
            let singerName = ''
            singerNames.forEach(element => {
                singerName += element + " "
            });
            return singerName

        },
        //获取用户的歌曲播放记录
        async getHistorySongs() {
            const resp = await this.$axios("/userPlay/song/" + this.$token().account + "/" + this.currentPage + "/" + this.pageSize)

            const code = resp.data.code

            if (code === 200) {
                this.historySongs = this.historySongs.concat(resp.data.data.records)
                this.maxPage = Math.ceil(resp.data.data.total / this.pageSize)
            }
        }

    },
    watch: {
        currentPage(val) {
            //获取播放记录
            this.getHistorySongs()
            if (val === this.maxPage) {
                this.tipVisible = true
            }
        },
        maxPage(val) {
            if (val === 1) {
                this.tipVisible = true
            }
        }
    },
    async created() {
        //获取播放记录
        await this.getHistorySongs()
    }


}
</script>
<style scoped lang="less">
.title-box {
    display: flex;
    margin: 10px 0 20px 0;
    padding: 10px;
}

.title-item-box {
    flex: 1;
}


.record-box {
    overflow: auto;
    height: 700px;
}

.record-box::-webkit-scrollbar {
    width: 8px;
    height: 8px;
}

.record-box::-webkit-scrollbar-thumb {
    border-radius: 5px;
    background-color: #b9b1b1;
}


.box-card {
    margin-bottom: 10px;
}

.msg-box {
    display: flex;
}

.msg-item-box {
    flex: 1;

}

.song-name-box {
    text-align: left;
}

.singer-name-box {
    text-align: center;
}

.song-play-time-box {
    text-align: right;
    color: rgb(126, 124, 124);
}


.bottom-tip-box {
    margin-top: 20px;
    text-align: center;
    color: rgb(126, 124, 124);
}

/deep/.el-card {
    border: 0;
}
</style>