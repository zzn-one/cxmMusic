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
                    <li v-for="item of tagList" class="tag-li" :key="item.id">
                        <el-button type="text" class="tag-li-button" @click="tag_btn">
                            {{ item.tag }}
                        </el-button>
                    </li>
                </ul>
            </div>
        </div>

        <!-- 歌单轮播 -->
        <div class="playList-box">
            <div class="playlist-body">
                <el-carousel indicator-position="outside" :autoplay="false" height="400px" @change="changeCarousel">
                    <el-carousel-item v-for="item in 4" :key="item">
                        <div style="display: flex;">

                            <el-card class="card-box" :body-style="{ padding: '0px' }" v-for="item in 4" :key="item"
                                shadow="never">
                                <RouterLink :to="{ name: 'songListDetail', params: { id: item } }">
                                    <img src="@/assets/1.jpg" class="card-img">

                                    <div style="margin-top: 10px; ">
                                        流行粤语歌曲,歌单名称最长限制为15
                                    </div>

                                    <div style="color:#adabab">播放量：2299万</div>
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
            tagList: [],
            tagCurrentNav: '',
        }
    },
    methods: {
        tag_btn(e) {
            //被点击的导航标签变色
            this.tagCurrentNav = e.target
            //发送请求，获取标签对应的歌单

        },
        //轮播图的切换事件
        changeCarousel(index) {
            console.log("幻灯片索引" + index);
        }
    },
    watch: {
        //导航标签变色
        tagCurrentNav(tag, oldTag) {
            if (oldTag !== '') {
                oldTag.style.color = 'black';
            }
            tag.style.color = 'rgb(240, 99, 18)';
        }


    },
    created() {
        this.tagList = [
            {
                id: 1,
                tag: '为你推荐'
            },
            {
                id: 2,
                tag: '官方歌单'
            },
            {
                id: 3,
                tag: '节奏控'
            },
            {
                id: 4,
                tag: '轻音乐'
            },
            {
                id: 5,
                tag: '粤语'
            },
            {
                id: 6,
                tag: '影视'
            },
        ]
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