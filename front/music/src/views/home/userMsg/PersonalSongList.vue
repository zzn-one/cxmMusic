<template>
    <div>
        <el-card class="card-box" :body-style="{ padding: '0px' }" v-for="songList in songListNames" :key="songList.id"
            shadow="never">
            <RouterLink :to="{ name: 'songListEdit', query: { id: songList.id } }">
                <img :src="songList.imgUrl" class="card-img">

                <div style="margin-top: 10px; ">
                    {{ songList.name }}
                </div>
            </RouterLink>
        </el-card>
    </div>
</template>

<script>
export default {
    name: "personalSongList",

    data() {
        return {
            songListNames: []
        }
    },
    methods: {
        //获取用户的歌单列表
        async getSongListNames() {
            const account = this.$token().account
            const resp = await this.$axios("/songList/list/" + account)

            if (resp.data.code === 200 && resp.data.data !== null) {
                this.songListNames = resp.data.data
            }

        },
    },
    created() {
        this.getSongListNames()
    }
}
</script>
<style scoped lang="less">
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