<template>
    <div class="singerList-box">
        <!-- 带图片的歌手信息 -->
        <div class="singer-msg-img-box">
            <div class="singer-msg-box">
                <div class="singer-msg-item" v-for="singer in singerListWithImg">
                    <el-button type="text" @click="btn_click(singer)" :key="singer.id">
                        <img :src="singer.avatarUrl" class="singer-msg-item-img">
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

        <!-- 显示更多歌手 -->
        <div class="more-singer">

            <el-button type="text" @click="moreSinger()">
                显示更多
            </el-button>


        </div>
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
            currentPage: 0,
            pageSize: 20
        }
    },
    methods: {
        //歌手信息 被点击
        btn_click(singer) {
            this.$router.push({
                name: "singerDetail",
                query: {
                    singer: singer,
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
                this.singerList = resp.data.data.records
                this.singerListSeparator()
            }
        },
        //更多歌手
        moreSinger() {
            this.pageSize += 20
        }
    },
    watch: {
        pageSize() {
            this.getSingerList()
        }
    },
    created() {
        this.getSingerList()
    }
}
</script>
  
  
<style lang="less" scoped>
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
</style>