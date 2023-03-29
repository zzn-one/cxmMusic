<template>
    <div class="record-box" v-infinite-scroll="load">
        <el-card class="box-card" shadow="hover" v-for="singer in singerList" :body-style="{ padding: '5px' }">
            <div class="msg-box">

                <div class="msg-item-box song-name-box">{{ singer.name }}</div>
                <div class="msg-item-box singer-name-box">{{ singer.gender }}</div>
                <div class="msg-item-box song-duration-box">03:42</div>
            </div>

            <div class="time-box">{{ $moment(new Date()).format("yyyy-MM-DD hh:mm:ss") }}</div>
        </el-card>
        <div v-show="tipVisible" class="bottom-tip-box">已经到底啦~</div>
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
            singerList: []
        }
    },
    methods: {
        //无限滚动的加载方法
        load() {
            if (this.currentPage < this.maxPage) {
                this.currentPage += 1
            }
        },
        async getSingerList() {
            const resp = await this.$axios("/singer/list/" + this.currentPage + "/" + this.pageSize)

            console.log(resp);

            const code = resp.data.code
            if (code === 200) {
                // this.singerList = resp.data.data.records
                this.singerList = this.singerList.concat(resp.data.data.records)
                this.maxPage = resp.data.data.pages
            }
        }
    },
    watch: {
        currentPage(val) {
            this.getSingerList()
            if (val === this.maxPage) {
                this.tipVisible = true
            }
        }
    },
    created() {
        this.getSingerList()
    }


}
</script>
<style scoped lang="less">
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

.song-duration-box {
    text-align: right;
}

.time-box {
    color: rgb(126, 124, 124);
    margin-top: 10px;
    text-align: right;
}

.bottom-tip-box {
    margin-top: 20px;
    text-align: center;
    color: rgb(126, 124, 124);
}
</style>