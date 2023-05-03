<template>
    <div class="search-main-box">
        <!-- 搜索输入框 -->
        <div class="search-input-box">
            <div class="search-input">
                <el-input placeholder="搜索歌曲、歌单、歌手" v-model="searchKey">
                    <el-button slot="append" icon="el-icon-search" @click="searchBtn"></el-button>
                </el-input>
            </div>
        </div>
        <!-- 被图片遮挡部分 -->
        <div class="under-img"></div>
        <!-- 搜索结果 -->
        <div class="search-result-box">

            <!-- 搜索结果的导航栏 -->
            <div class="search-result-nav-box">

                <router-link active-class="active" class="nav-item-box" v-for="item in navList" :key="item.name" :to="{
                    name: item.url, query: {
                        'searchKey': searchKey
                    }
                }">
                    {{ item.name }}
                </router-link>

            </div>

            <!-- 搜索结果展示区 -->
            <div class="search-result-display-box">
                <router-view>

                </router-view>
            </div>


        </div>
    </div>
</template>

<script>


export default {
    name: 'SearchResult',
    data() {
        return {
            searchKey: "",
            navList: [
                {
                    url: "songResult",
                    name: "歌曲"
                },
                {
                    url: "singerResult",
                    name: "歌手"
                },
                {
                    url: "songListResult",
                    name: "歌单"
                }
            ]
        }
    },
    methods: {
        //初始化
        init() {
            this.searchKey = this.$route.query.searchKey
        },
        searchBtn() {
            this.$router.push({
                name: "songResult",
                query: {
                    'searchKey': this.searchKey
                }
            })
        }

    },
    created() {
        this.init()
    }
}
</script>

<style scoped lang="less">
.search-input-box {
    background-image: url(../../assets/1.jpg);
    background-size: cover;
    width: 100%;
    height: 260px;
    position: absolute;

}

.search-input {
    display: block;
    width: 500px;
    margin: 110px auto;
}

.search-result-box {
    width: 1200px;
    margin: 0 auto;
}

.under-img {
    height: 260px;
    width: 100%;
}

.active {
    color: rgb(240, 99, 18);
}

.nav-item-box {
    text-align: left;
    width: 100px;

    margin-right: 20px;
}

.search-result-display-box {
    margin-top: 20px;
}
</style>