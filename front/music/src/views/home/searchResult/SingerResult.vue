<template>
    <div class="search-singer-box">
        <el-table ref="multipleTable" :data="singerList" tooltip-effect="dark" style="width: 100%">
            <el-table-column type="index" width="55">
            </el-table-column>
            <el-table-column  width="120">
                <template slot-scope="scope">
                    <img class="avatal-img" :src="$imgPrefix + scope.row.avatarUrl" >
                </template>
            </el-table-column>
            <el-table-column prop="name" label="歌手" width="200">
                <template slot-scope="scope">
                    <router-link :to="{
                        name: 'singerDetail',
                        query: {
                            singerId: scope.row.id
                        }

                    }">
                        {{ scope.row.name }}
                    </router-link>
                </template>
            </el-table-column>
            <el-table-column prop="introduction" label="简介">
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
export default {
    name: 'SingerResult',
    data() {
        return {
            key: "",
            singerList: [],
        }
    },
    methods: {
        //初始化
        init() {
            this.key = this.$route.query.searchKey
        },
        //获取歌手列表 根据key模糊查询
        async getSingerList() {

            if (this.key === '') {
                return;
            }

            const resp = await this.$axios("/singer/list/" + this.key)

            const respCode = resp.data.code
            if (respCode === 200) {
                this.singerList = resp.data.data
            }
        },

    },
    async created() {
        await this.init()

        this.getSingerList()
    }

}
</script>

<style scoped lang="less">
.avatal-img{
    height: 100px;
    width: 100px;
    border-radius: 50px;
}
.search-singer-box{
    height: 800px;
    overflow: auto;
}
</style>