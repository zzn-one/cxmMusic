<template>
    <div class="songList-box">
        <el-card class="card-box" :body-style="{ padding: '0px' }" v-for="songlist in songlists" :key="songlist.id"
            shadow="never">
            <RouterLink :to="{ name: 'songListDetail', query: { id: songlist.id } }">
                <img :src="$imgPrefix + songlist.imgUrl" class="card-img">

                <div style="margin-top: 10px; ">
                    {{ songlist.name }}
                </div>

                <div style="color:#adabab">播放量：{{ numberFormat(songlist.playNumber) }}</div>
            </RouterLink>
        </el-card>
    </div>
</template>
<script>
export default {
    name: "UserSongList",

    data() {
        return {
            songlists: []
        }
    },
    methods: {

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
        //获取用户收藏的歌单列表
        async getSonglists() {
            const account = this.$token().account
            const resp = await this.$axios("/starNumber/songlist/" + account)

            const code = resp.data.code
            if (code === 200) {
                this.songlists = resp.data.data
            }

        }
    },

    created() {
        this.getSonglists()
    }
}
</script>

<style scoped>
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