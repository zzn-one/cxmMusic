<template>
    <div class="songList-box">
        <!-- 标签导航栏 -->
        <div class="tag-nav-box">
            <div class="tag-nav-item" v-for="item in tagsList">
                <div class="tag-title" style="color:darkgray">{{ item.title }}</div>
                <div class="tag-item">
                    <div class="tag-btns">
                        <el-button type="text" class="tag-btn" v-for="tagObj in item.tagList" :key="tagObj.id"
                            @click="addTag(tagObj)">
                            {{ tagObj.tag }}
                        </el-button>
                    </div>
                </div>
            </div>
        </div>

        <!-- 歌单推荐 -->
        <div class="songList-msg-box">
            <div>
                <div v-show="!tagIsOpen">
                    推荐歌单
                </div>
                <div v-show="tagIsOpen">
                    <el-tag v-for="tag in dynamicTags" :key="tag.id" closable :disable-transitions="true" @close="tagClose">
                        {{ tag.tag }}
                    </el-tag>
                </div>

            </div>
            <div>
                <el-card class="card-box" :body-style="{ padding: '0px' }" v-for="item in 9" :key="item" shadow="never">
                    <RouterLink :to="{ name: 'songListDetail', params: { id: item } }">
                        <img src="@/assets/1.jpg" class="card-img">

                        <div style="margin-top: 10px; ">
                            流行粤语歌曲,歌单名称最长限制为15
                        </div>

                        <div style="color:#adabab">播放量：2299万</div>
                    </RouterLink>
                </el-card>
            </div>
        </div>

    </div>
</template>
  
<script>
export default {
    name: "SongList",
    data() {
        return {
            //动态 标签 限定最多有一个
            dynamicTags: [],
            //是否选中了标签
            tagIsOpen: false,
            //供选择的标签列表
            tagsList: [],
        }
    },
    methods: {
        //标签关闭触发
        tagClose() {
            //移除标签
            this.dynamicTags.pop();
            this.tagIsOpen = false;
        },
        //添加标签 标签被点击触发
        addTag(tag) {
            if (this.dynamicTags.length < 1) {
                this.dynamicTags.push(tag);
            } else {
                this.dynamicTags.pop();
                this.dynamicTags.push(tag);
            }
            this.tagIsOpen = true

        }
    },
    watch: {
        //监听 tagIsOpen  false->获取推荐歌单  trur->获取标签相应的歌单
        tagIsOpen(newVal) {
            if (newVal) {
                //true

            } else {
                //false

            }
        }
    },
    created() {
        //整点假标签数据
        this.tagsList = [
            {
                title: "热门",
                tagList: [
                    {
                        id: 1,
                        tag: "官方歌单"
                    },
                    {
                        id: 2,
                        tag: "AI歌单"
                    },
                    {
                        id: 3,
                        tag: "免费热歌"
                    },
                    {
                        id: 4,
                        tag: "私藏"
                    },
                ]
            },
            {
                title: "场景",
                tagList: [
                    {
                        id: 5,
                        tag: "学习工作"
                    },
                    {
                        id: 6,
                        tag: "运动"
                    },
                    {
                        id: 7,
                        tag: "睡前"
                    },
                    {
                        id: 8,
                        tag: "跳舞"
                    },
                    {
                        id: 9,
                        tag: "旅行"
                    },
                    {
                        id: 10,
                        tag: "派对"
                    },
                    {
                        id: 11,
                        tag: "婚礼"
                    },
                    {
                        id: 12,
                        tag: "约会"
                    },
                ]
            },
            {
                title: "心情",
                tagList: [
                    {
                        id: 22,
                        tag: "伤感"
                    },
                    {
                        id: 212,
                        tag: "快乐"
                    },
                    {
                        id: 32,
                        tag: "治愈"
                    },
                    {
                        id: 124,
                        tag: "思念"
                    },
                ]
            },
            {
                title: "年代",
                tagList: [
                    {
                        id: 2112,
                        tag: "00年代"
                    },
                    {
                        id: 21122,
                        tag: "90年代"
                    },
                    {
                        id: 3112,
                        tag: "80年代"
                    },
                    {
                        id: 12324,
                        tag: "70年代"
                    },
                ]
            },
            {
                title: "语种",
                tagList: [
                    {
                        id: 12112,
                        tag: "国语"
                    },
                    {
                        id: 121122,
                        tag: "粤语"
                    },
                    {
                        id: 13112,
                        tag: "英语"
                    },
                    {
                        id: 112324,
                        tag: "日语"
                    },
                ]
            },
        ]
    }
}
</script>
  
  
<style scoped lang="less">
.tag-nav-box {
    margin: 30px 0;
    display: flex;
}

.tag-nav-item {
    flex: 1;
}

.tag-item {
    height: 80px;
    overflow: auto;
}

.tag-item::-webkit-scrollbar {
    width: 8px;
    height: 8px;
}

.tag-item::-webkit-scrollbar-thumb {
    border-radius: 5px;
    background-color: #b9b1b1;
}

.tag-btn {
    padding: 0;
    color: black;
    margin: 10px 5px 0px 5px;
    text-align: left;
    width: 60px;
}

/*歌单推荐*/
.songList-msg-box {}

.card-box {
    padding: 0;
    border: 0;
    height: 320px;
    width: 240px;
    float: left;
    background-color: rgba(255, 255, 255, 0);
    margin: 0 30px;
    margin-top: 30px;
}

.card-img {
    height: 240px;
    width: 240px;
    display: block;
}
</style>