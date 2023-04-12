<template>
    <div>
        <!-- 大标题 -->
        <div class="title-box">
            <div class="title-body">
                {{ title }}
            </div>


        </div>

        <!-- 条件过滤标签 -->
        <div class="tag-box">
            <div class="tag-body">
                <ul class="tag-ul">
                    <li v-for="tag of tagList" class="tag-li" :key="tag.id">
                        <el-button type="text" class="tag-li-button" @click="tag_btn($event, tag)">
                            {{ tag.text }}
                        </el-button>
                    </li>
                </ul>
            </div>
        </div>

        <!-- 歌单轮播 -->
        <div class="playList-box">
            <div class="playlist-body">
                <el-carousel indicator-position="outside" :autoplay="false" height="400px" v-if="songList.length > 0">
                    <el-carousel-item v-for="index in carouselItemNumber" :key="index">
                        <!-- 如果刚好 歌单都是满页 -->
                        <div style="display: flex;" v-if="carouselItemRemainingNumber === 0">
                            <el-card class="card-box" :body-style="{ padding: '0px' }" v-for="index2 in carouselItemSize"
                                :key="index2" shadow="never">

                                <RouterLink :to="{
                                    name: 'songListDetail',
                                    params: {
                                        id: songList[(index - 1) * carouselItemSize + (index2 - 1)].id
                                    }
                                }
                                ">
                                    <!--  -->
                                    <img :src="songList[(index - 1) * carouselItemSize + (index2 - 1)].imgUrl"
                                        class="card-img">

                                    <div style="margin-top: 10px; ">
                                        {{ songList[(index - 1) * carouselItemSize + (index2 - 1)].name }}
                                    </div>

                                    <div style="color:#adabab">
                                        播放量：{{ songList[(index - 1) * carouselItemSize + (index2 - 1)].playNumber }}
                                    </div>
                                </RouterLink>
                            </el-card>
                        </div>
                        <!-- 如果不是 -->
                        <div style="display: flex;" v-else-if="index <= carouselItemNumber - 1">
                            <el-card class="card-box" :body-style="{ padding: '0px' }" v-for="index2 in carouselItemSize"
                                :key="index2" shadow="never">

                                <RouterLink :to="{
                                    name: 'songListDetail',
                                    params: {
                                        id: songList[(index - 1) * carouselItemSize + (index2 - 1)].id
                                    }
                                }
                                ">
                                    <img :src="songList[(index - 1) * carouselItemSize + (index2 - 1)].imgUrl"
                                        class="card-img">

                                    <div style="margin-top: 10px; ">
                                        {{ songList[(index - 1) * carouselItemSize + (index2 - 1)].name }}
                                    </div>

                                    <div style="color:#adabab">
                                        播放量：{{ songList[(index - 1) * carouselItemSize + (index2 - 1)].playNumber }}
                                    </div>
                                </RouterLink>
                            </el-card>
                        </div>
                        <!-- 处理最后一页  余下的歌单 -->
                        <div style="display: flex;" v-else>
                            <el-card class="card-box" :body-style="{ padding: '0px' }"
                                v-for="index2 in carouselItemRemainingNumber " :key="index2" shadow="never">

                                <RouterLink :to="{
                                    name: 'songListDetail',
                                    params: {
                                        id: songList[(index - 1) * carouselItemSize + (index2 - 1)].id
                                    }
                                }
                                ">
                                    <!--  -->
                                    <img :src="songList[(index - 1) * carouselItemSize + (index2 - 1)].imgUrl"
                                        class="card-img">

                                    <div style="margin-top: 10px; ">
                                        {{ songList[(index - 1) * carouselItemSize + (index2 - 1)].name }}
                                    </div>

                                    <div style="color:#adabab">
                                        播放量：{{ songList[(index - 1) * carouselItemSize + (index2 - 1)].playNumber }}
                                    </div>
                                </RouterLink>
                            </el-card>
                        </div>
                    </el-carousel-item>
                </el-carousel>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'HallHome',

    data() {
        return {
            title: '歌  单  推  荐',
            account: "1",
            tagList: [],
            tagCurrentNav: '',

            songList: [],
            //一个轮播图页面展示歌单的数量(最大是4)
            carouselItemSize: 4,
            // 有多少个轮播图页面
            carouselItemNumber: 0,
            //轮播图最后一页剩下的歌单数量
            carouselItemRemainingNumber: 0,
        }
    },
    methods: {
        tag_btn(e, tag) {
            //被点击的导航标签变色
            this.tagCurrentNav = e.target
            //获取歌单列表
            this.getSongList(tag)
        },

        //获取标签列表
        async getTagList() {
            const resp = await this.$axios("/recommend/" + this.account)

            const code = resp.data.code

            if (code === 200) {
                this.tagList = resp.data.data.tagList
            }
        },
        //获取歌单列表
        async getSongList(tag) {
            const resp = await this.$axios("/songList/list/tag/limit/" + tag.id)

            const code = resp.data.code

            if (code === 200) {
                this.songList = []
                this.songList = resp.data.data.records
            }
        },
        //初始化
        async init() {
            //获取用户账户
            const account = this.$token().account
            if (account !== '', account !== null) {
                this.account = account
            }

            // 获取标签列表
            await this.getTagList()

            //获取歌单列表
            await this.getSongList(this.tagList[0])

            //计算轮播图页面数量
            this.carouselItemNumber = Math.ceil(this.songList.length / this.carouselItemSize)

            //计算轮播图最后一页剩下的歌单数量
            this.carouselItemRemainingNumber = Math.floor(this.songList.length % this.carouselItemSize)
        }

    },
    watch: {

        tagCurrentNav(tag, oldTag) {
            //导航标签变色
            if (oldTag !== '') {
                oldTag.style.color = 'black';
            }
            tag.style.color = 'rgb(240, 99, 18)';
        }


    },
    created() {
        this.init()
    }
}
</script>
<style scoped lang="less">
.title-box {
    width: 1200px;
    height: 100px;
    display: flex;

}

.title-body {
    font-size: 32px;
    line-height: 100px;
    text-align: center;
    flex: 1;
}

.tag-box {
    height: 50px;
}

.tag-body {
    line-height: 20px;
    text-align: center;
}

.tag-ul {
    list-style-type: none;
}

.tag-li {
    display: inline-block;
    margin: 0 auto;
    margin-right: 50px;
}

.tag-li-button {
    color: black;
}

.tag-li-button :hover {
    color: #f06312;
}

.card-box {

    margin: 0 auto;
    padding: 0;
    border: 0;
    height: 320px;
    width: 240px;
    float: left;
    background-color: rgba(255, 255, 255, 0);
    margin-top: 30px;
}

.card-img {
    height: 240px;
    width: 240px;
    display: block;
}


/* 轮播图两边的按钮*/
/deep/ .el-carousel__arrow {

    margin: 0 -16px;

    font-size: 20px;
}
</style>