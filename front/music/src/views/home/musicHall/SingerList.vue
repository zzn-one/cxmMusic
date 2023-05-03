<template>
    <div class="singerList-box" v-infinite-scroll="load">
        <!-- 带图片的歌手信息 -->
        <div class="singer-msg-img-box">
            <div class="singer-msg-box">
                <div class="singer-msg-item" v-for="singer in singerListWithImg">
                    <el-button type="text" @click="btn_click(singer)" :key="singer.id">
                        <img :src="$imgPrefix + singer.avatarUrl" class="singer-msg-item-img">
                        <div class="singer-msg-item-name">{{ singer.name }}</div>
                    </el-button>
                </div>
            </div>
        </div>

        <!-- 无图片的歌手信息 -->
        <div class="singer-msg-no-img-box">
            <el-button type="text" v-for="singer in singerListNoImg" class="singer-msg-no-img-btn"
                onmouseover="this.style.color='rgb(240, 99, 18)'" onmouseleave="this.style.color='black'"
                @click="btn_click(singer)" :key="singer.id">
                {{ singer.name }}
            </el-button>
        </div>

        <div v-show="tipVisible" class="bottom-tip-box">已经到底啦~</div>
        <div v-show="!tipVisible" class="bottom-tip-box">下滑加载更多内容~</div>


    </div>
</template>
  
<script>
export default {
    name: "SingerList",
    data() {
        return {
            //总数据
            singerList: [],
            //显示带头像歌手的个数
            imgNumber: 10,
            //带头像的数据
            singerListWithImg: [],
            //无头像的数据
            singerListNoImg: [],
            tipVisible: false,
            currentPage: 1,
            pageSize: 20,
            maxPage: 0,
        }
    },
    methods: {
        //歌手信息 被点击
        btn_click(singer) {
            this.$router.push({
                name: "singerDetail",
                query: {
                    singerId: singer.id,
                }
            })
        },
        //数据分发
        singerListSeparator() {
            let index = 0
            this.singerListWithImg = []
            this.singerListNoImg = []
            this.singerList.forEach(singer => {
                if (index < this.imgNumber) {
                    this.singerListWithImg.push(singer)
                } else {
                    this.singerListNoImg.push(singer)
                }
                index++
            })
        },
        //获取歌手列表
        async getSingerList() {
            const resp = await this.$axios("/singer/list/" + this.currentPage + "/" + this.pageSize)

            const code = resp.data.code
            if (code === 200) {
                this.maxPage = resp.data.data.pages
                this.singerList = this.singerList.concat(resp.data.data.records)
                this.singerListSeparator()

            }
        },
        //下滑加载
        load() {
            if (this.currentPage < this.maxPage) {
                this.currentPage += 1
            }
        },

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
  
  
<style lang="less" scoped>
.singerList-box {
    overflow: auto;
    height: 720px;
}
.singerList-box::-webkit-scrollbar {
    width: 0;
    height: 0;
}

.singerList-box::-webkit-scrollbar-thumb {
    border-radius: 5px;
    background-color: #b9b1b1;
}

.singer-msg-no-img-btn {
    margin: 0;
    width: 240px;
    color: black;
    background-color: #fff;
}

.singer-msg-img-btn {
    margin: 0;
    width: 240px;
    color: black;
}

.singer-msg-item {
    display: inline-block;
    height: 240px;
    width: 240px;
    margin-bottom: 60px;
}

.singer-msg-item-img {
    margin: 40px 0 0 40px;
    height: 160px;
    width: 160px;
    border-radius: 80px;
}

.singer-msg-item-name {
    color: black;
    margin: 20px 0 0 60px;
    font-size: 16px;
}

.more-singer {
    margin-top: 20px;
    text-align: center;
}

.bottom-tip-box {
    margin-top: 20px;
    text-align: center;
    color: rgb(126, 124, 124);
}
</style>