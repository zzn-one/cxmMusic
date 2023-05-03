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
                            {{ tagObj.text }}
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
                        {{ tag.text }}
                    </el-tag>
                </div>

            </div>
            <div>
                <el-card class="card-box" :body-style="{ padding: '0px' }" v-for="songList in songlists" :key="songList.id"
                    shadow="never">
                    <RouterLink :to="{ name: 'songListDetail', query: { id: songList.id } }">
                        <img :src="$imgPrefix + songList.imgUrl" class="card-img">

                        <div style="margin-top: 10px; ">
                            {{ songList.name }}
                        </div>

                        <div style="color:#adabab">播放量：{{ numberFormat(songList.playNumber) }}</div>
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
            //歌单列表
            songlists: []
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


        },
        //获取标签列表
        async getTagsList() {
            const resp = await this.$axios("/dict/tag/order")
            const code = resp.data.code

            if (code === 200) {
                this.tagsList = resp.data.data
            }
        },
        //获取歌单列表（未选择标签）
        async getSonglist() {
            const resp = await this.$axios("/songList/list")

            const code = resp.data.code

            if (code === 200) {
                this.songlists = resp.data.data
            }
        },
        //数量格式化
        numberFormat(number) {

            if (number >= 100000000) {
                let x = (number / 100000000).toFixed(2)
                number = x + "亿"
            } else if (number >= 10000) {
                let x = (number / 10000).toFixed(2)
                number = x + "万"
            }
            return number
        },
        //获取歌单列表
        async getSonglistByTag(tagId) {
            const resp = await this.$axios({
                url: "/songList/list/tag/" + tagId,
            })

            const code = resp.data.code

            if (code === 200) {
                this.songlists = resp.data.data

            }
        },
    },
    watch: {
        //监听 dynamicTags数组  
        dynamicTags: {
            deep: true,
            handler(val) {
                if (val.length === 0) {
                    //获取默认推荐歌单
                    this.getSonglist()
                } else {
                    const tag = val[0].id
                    //获取歌单列表  根据标签
                    this.getSonglistByTag(tag)
                }
            }
        },
    },
    created() {
        this.getTagsList()

        this.getSonglist()
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