<template>
    <div class="search-songlist-box">
        <el-table :data="songlists" stripe style="width: 100%">
            <el-table-column type="index" width="55">
            </el-table-column>
            <el-table-column prop="name" label="歌单名" width="200">
                <template slot-scope="scope">
                    <router-link :to="{
                        name: 'songListDetail',
                        query: {
                            id: scope.row.id
                        }

                    }">
                        {{ scope.row.name }}
                    </router-link>
                </template>
            </el-table-column>
            <el-table-column prop="authorName" label="创建人" width="180">
            </el-table-column>
            <el-table-column prop="introduction" label="简介">
            </el-table-column>
            <el-table-column label="收听" width="100">
                <template slot-scope="scope">
                    {{ numberFormat(scope.row.playNumber) }}
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
export default {
    name: 'SonglistResult',
    data() {
        return {
            key: "",
            songlists: []
        }
    },
    methods: {
        //初始化
        init() {
            this.key = this.$route.query.searchKey
        },
        //获取歌单列表
        async getSonglists() {

            if (this.key === '') {
                return;
            }

            const resp = await this.$axios("/songList/list/key/" + this.key)

            const respCode = resp.data.code
            if (respCode === 200) {
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

    },
    async created() {
        await this.init()

        this.getSonglists()
    }
}
</script>

<style scoped lang="less">
.search-songlist-box{
    height: 800px;
    overflow: auto;
}
</style>