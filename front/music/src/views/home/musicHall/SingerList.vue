<template>
    <div class="singerList-box">
        <!-- 带图片的歌手信息 -->
        <div class="singer-msg-img-box">
            <div class="singer-msg-box">
                <div class="singer-msg-item" v-for="singer in singerListWithImg">
                    <el-button type="text" @click="btn_click(singer)">
                        <img :src="singer.avatar_url" class="singer-msg-item-img">
                        <div class="singer-msg-item-name">{{ singer.name }}</div>
                    </el-button>
                </div>
            </div>
        </div>

        <!-- 无图片的歌手信息 -->
        <div class="singer-msg-no-img-box">
            <el-button type="text" v-for="singer in singerListNoImg" class="singer-msg-no-img-btn"
                onmouseover="this.style.color='rgb(240, 99, 18)'" onmouseleave="this.style.color='black'"
                @click="btn_click(singer)">
                {{ singer.name }}
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
        }
    },
    methods: {
        //歌手信息 被点击
        btn_click(singer) {
            console.log(singer);

            this.$router.push({
                name: "singerDetail",
                params: {
                    singer: singer,
                }
            })
        },
        //数据分发
        singerListSeparator() {
            let index = 0
            this.singerList.forEach(singer => {
                if (index < this.imgNumber) {
                    this.singerListWithImg.push(singer)
                } else {
                    this.singerListNoImg.push(singer)
                }
                index++
            })
        }
    },
    created() {
        //假数据
        this.singerList = [
            {
                id: 1,
                name: "陈奕迅",
                avatar_url: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            },
            {
                id: 2,
                name: "陈奕迅",
                avatar_url: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            },
            {
                id: 3,
                name: "陈奕迅",
                avatar_url: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            },
            {
                id: 4,
                name: "陈奕迅",
                avatar_url: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            },
            {
                id: 5,
                name: "陈奕迅",
                avatar_url: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            },
            {
                id: 6,
                name: "陈奕迅",
                avatar_url: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            },
            {
                id: 7,
                name: "陈奕迅",
                avatar_url: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            }, {
                id: 8,
                name: "陈奕迅",
                avatar_url: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            }, {
                id: 9,
                name: "陈奕迅",
                avatar_url: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            }, {
                id: 10,
                name: "陈奕迅",
                avatar_url: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            },
            {
                id: 10,
                name: "陈奕迅",
                avatar_url: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            }, {
                id: 10,
                name: "陈奕迅",
                avatar_url: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            }, {
                id: 10,
                name: "陈奕迅",
                avatar_url: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            }, {
                id: 10,
                name: "陈奕迅",
                avatar_url: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            }, {
                id: 10,
                name: "陈奕迅",
                avatar_url: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            }, {
                id: 10,
                name: "陈奕迅",
                avatar_url: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            }, {
                id: 10,
                name: "陈奕迅",
                avatar_url: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            }, {
                id: 10,
                name: "陈奕迅",
                avatar_url: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            }, {
                id: 10,
                name: "陈奕迅",
                avatar_url: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            }, {
                id: 10,
                name: "陈奕迅",
                avatar_url: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            }, {
                id: 10,
                name: "陈奕迅",
                avatar_url: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            }, {
                id: 10,
                name: "陈奕迅",
                avatar_url: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            }, {
                id: 10,
                name: "陈奕迅",
                avatar_url: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            }, {
                id: 10,
                name: "陈奕迅",
                avatar_url: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            },
        ]

        this.singerListSeparator()
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
</style>