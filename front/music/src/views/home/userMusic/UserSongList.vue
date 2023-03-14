<template>
    <div class="songList-box">
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
</template>
<script>
import TextBtn from '@/components/publish/TextBtn.vue';
export default {
    name: "UserSongList",
    components: {
        TextBtn,
    },
    data() {
        return {
            songTable: []
        }
    },
    methods: {
        // 播放量处理
        playNumberFormat() {
            this.songTable.forEach(item => {
                let result;
                //亿
                result = item.playNumber / 100000000
                if (result > 1) {
                    item.playNumber = result.toFixed(1) + "亿"
                    return;
                }
                //万
                result = item.playNumber / 10000
                if (result > 1) {
                    item.playNumber = result.toFixed(1) + "万"
                    return;
                }
                //千
                result = item.playNumber / (1000)
                if (result > 1) {
                    item.playNumber = result.toFixed(1) + "千"
                    return;
                }


            })
        }
    },

    created() {
        this.songTable.push({
            name: '歌单名称几点睡觉导航',
            songNumber: 28,
            authorName: '疯原万叶',
            playNumber: 723323232
        })

        this.playNumberFormat()
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