<template>
    <div>
        <div class="btn-box">
            <el-button icon="el-icon-video-play" class="btns" type="success">
                播放全部
            </el-button>

            <el-popover placement="right" width="180" trigger="manual" v-model="popoverVisible">
                <div>
                    临时内容
                </div>
                <el-button slot="reference" class="btns" icon="el-icon-circle-plus-outline"
                    @click="popoverVisible = !popoverVisible" style="margin-left: 10px;">添加到</el-button>
            </el-popover>

            <el-button icon="el-icon-delete" class="btns" style="margin-left: 10px;" type="danger">
                删除
            </el-button>

        </div>

        <!-- 收藏的歌曲列表 -->
        <div class="table-box">
            <el-table :data="songTable" stripe style="width: 100%" height="432px" @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="55">
                </el-table-column>
                <el-table-column type="index" width="50">
                </el-table-column>
                <el-table-column prop="name" label="歌曲">
                </el-table-column>
                <el-table-column prop="songerName" label="歌手" width="180">
                </el-table-column>
                <el-table-column prop="duration" label="时长" width="180">
                    <template slot-scope="scope">
                        {{ $moment(scope.row.duration).format("mm:ss") }}
                    </template>
                </el-table-column>
            </el-table>
        </div>
    </div>
</template>
<script>
export default {
    name: "UserSong",
    data() {
        return {
            popoverVisible: false,
            songTable: [],

            multipleSelection: []
        }
    },
    methods: {

        handleSelectionChange(val) {
            this.multipleSelection = val;
        }

    },
    created() {

        // 假数据

        for (let i = 1; i <= 100; i++) {
            this.songTable.push({
                name: "半岛铁盒",
                songerName: "周杰伦",
                duration: 239000,
            },)
        }


    }
}
</script>

<style scoped>
.btns {
    width: 150px;
    font-size: 16px;
}

.btn-box {
    margin-bottom: 30px;
}
</style>