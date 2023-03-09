<template>
    <div>
        <el-table :data="songTable" stripe style="width: 100%" height="432px">
            <el-table-column label="歌单">
                <template slot-scope="scope">
                    <TextBtn @click.native="name_btn_click(scope.row)">
                        {{ scope.row.name }}
                    </TextBtn>
                </template>
            </el-table-column>
            <el-table-column prop="songNumber" label="曲目数" width="180">
            </el-table-column>
            <el-table-column prop="authorName" label="创建人" width="180">
            </el-table-column>
            <el-table-column prop="playNumber" label="播放量" width="180">
            </el-table-column>
        </el-table>
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
        //歌单名称被点击了
        name_btn_click(row) {
            console.log("row:" + row.name);
            //跳转到songListDetail页面

        },
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