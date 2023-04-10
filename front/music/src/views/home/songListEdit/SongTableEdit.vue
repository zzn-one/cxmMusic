<template>
    <div>
        <div class="songList-box">
            <!-- 按钮 -->
            <div class="songList-btns">

                <el-button class="btns-item" icon="el-icon-star-off" type="warning" plain @click="starBtn">
                    收藏
                </el-button>
                <el-button class="btns-item" icon="el-icon-delete" type="danger" plain @click="deleteBtn">
                    移除歌曲
                </el-button>

                <el-popover placement="right" width="150" trigger="click">
                    <AddBtnPopoverContent :selectdSongs="multipleSelection"></AddBtnPopoverContent>
                    <el-button style="margin-left: 10px;" slot="reference" class="btns-item" type="primary" plain
                        icon="el-icon-plus">添加到</el-button>
                </el-popover>


            </div>
            <!-- 歌曲表格 -->
            <div class="songList-table-box">
                <el-table :data="songList" stripe style="width: 100%" height="520px"
                    @selection-change="handleSelectionChange">
                    <el-table-column type="selection" width="55"></el-table-column>
                    <el-table-column type="index" width="50"></el-table-column>
                    <el-table-column label="歌名">
                        <template slot-scope="scope">
                            {{ scope.row.name }}
                        </template>
                    </el-table-column>

                    <el-table-column label="歌手">
                        <template slot-scope="scope">

                            <span v-for="singer in scope.row.singerList" :key="singer.id">
                                {{ singer.name }}
                            </span>

                        </template>
                    </el-table-column>

                    <el-table-column label="时长" width="180">
                        <template slot-scope="scope">
                            {{ $moment(scope.row.duration).format("mm:ss") }}
                        </template>

                    </el-table-column>
                </el-table>
            </div>
        </div>
    </div>
</template>
<script>
import star from '@/assets/js/starSong';
import AddBtnPopoverContent from '@/components/home/other/AddBtnPopoverContent.vue';
export default {
    name: "SongTableEdit",
    components: {
        AddBtnPopoverContent
    },
    data() {
        return {
            songList: [],

            //被选中的数据（歌曲）
            multipleSelection: []
        }
    },
    methods: {
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        //页面初始化
        init() {
            const songlistId = this.$route.query.id
            this.getSonglist(songlistId)

        },

        //收藏按钮
        async starBtn() {
            const account = this.$token().account
            if (this.multipleSelection.length > 0) {
                const resp = await star(this.multipleSelection, account)

                const code = resp.data.code
                if (code === 200) {
                    this.$message({
                        message: "已添加到收藏列表",
                        type: "success"
                    })
                }

            } else {
                this.$message({
                    message: "需要先选择歌曲哟~",
                    type: "warning"
                })
            }
        },
        //移除按钮
        async deleteBtn() {
            const songlistId = this.$route.query.id
            if (this.multipleSelection.length > 0) {
                //发送请求
                const resp = await this.$axios({
                    method: "delete",
                    data: this.multipleSelection,
                    url: "/songList/songs/" + songlistId
                })

                const code = resp.data.code
                if (code === 200) {
                    this.$message({
                        message: "已移除部分歌曲",
                        type: "success"
                    })
                    //刷新歌曲数据
                    this.init()
                } else {
                    this.$message.error(resp.data.msg)
                }

            } else {
                this.$message({
                    message: "需要先选择歌曲哟~",
                    type: "warning"
                })
            }
        },
        //获取歌单对象
        async getSonglist(songlistId) {
            const resp = await this.$axios("/songList/songs/" + songlistId)

            const code = resp.data.code

            if (code === 200) {
                this.songList = resp.data.data

            }
        },

    },
    created() {
        this.init()
    }
}

</script>

<style scoped lang="less">
.songList-box {
    background-color: #fff;

}

.songList-title-box {
    font-size: 24px;
    margin-bottom: 10px;
}

.songList-btns {
    margin: 20px 0;
}

.btns-item {
    color: rgb(97, 95, 95);
    font-size: 14px;
    width: 120px;

}
</style>