<template>
    <div>
        <div class="btn-box">
            <el-button icon="el-icon-video-play" class="btns" type="success" plain @click="playBtn">
                播放
            </el-button>

            <el-button icon="el-icon-delete" class="btns" style="margin-left: 10px;" type="danger" plain
                @click="starCancelBtn">
                取消收藏
            </el-button>
            <el-popover placement="right" width="150" trigger="click">
                <AddBtnPopoverContent :selectdSongs="multipleSelection"></AddBtnPopoverContent>
                <el-button style="margin-left: 10px;" slot="reference" class="btns" type="primary" plain
                    icon="el-icon-plus">添加到</el-button>
            </el-popover>
        </div>

        <!-- 收藏的歌曲列表 -->
        <div class="table-box">
            <el-table :data="songTable" stripe style="width: 100%" height="532px" @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="55">
                </el-table-column>
                <el-table-column type="index" width="50">
                </el-table-column>
                <el-table-column :label="'歌曲(' + songTable.length + ')'">
                    <template slot-scope="scope">
                        {{ scope.row.song.name }}
                    </template>
                </el-table-column>
                <el-table-column label="歌手" width="300">
                    <template slot-scope="scope">

                        <a :href="'/#/home/musicHall/singerDetail?singerId=' + singer.id"
                            v-for="singer in scope.row.singerList" target="_blank" rel="noopener noreferrer"
                            :key="singer.id">
                            {{ singer.name }}
                        </a>

                    </template>
                </el-table-column>
                <el-table-column prop="duration" label="时长" width="80">
                    <template slot-scope="scope">
                        {{ $moment(scope.row.song.duration).format("mm:ss") }}
                    </template>
                </el-table-column>
            </el-table>
        </div>

        <div style="height: 50px; width:100%"></div>
    </div>
</template>
<script>
import play from '@/assets/js/playSong';
import starCancel from '@/assets/js/starCancelSong';
import AddBtnPopoverContent from '@/components/home/other/AddBtnPopoverContent.vue';

export default {
    name: "UserSong",
    components: {
        AddBtnPopoverContent
    },
    data() {
        return {
            popoverVisible: false,
            songTable: [],

            multipleSelection: []
        }
    },
    methods: {

        handleSelectionChange(val) {

            if (val.length > 0) {
                //取出歌曲列表
                let songList = []
                val.forEach(item => {
                    songList.push(item.song)
                })

                this.multipleSelection = songList;
            }

        },
        //获取收藏的歌曲列表
        async getSongTable() {
            const resp = await this.$axios("/starNumber/song/" + this.$token().account)
            const code = resp.data.code
            if (code === 200) {
                this.songTable = resp.data.data
            }
        },
        //播放按钮
        playBtn() {
            const account = this.$token().account
            if (this.multipleSelection.length > 0) {
                
                play(this.multipleSelection, account)
            } else {
                this.$message({
                    message: "请先选择歌曲！",
                    type: "error"
                })
            }
        },
        //取消收藏按钮  
        async starCancelBtn() {
            const account = this.$token().account
            if (this.multipleSelection.length > 0) {
                

                const resp = await starCancel(this.multipleSelection, account)

                if (resp.data.code === 200) {
                    //重新请求收藏的歌曲列表
                    this.getSongTable()
                }

            } else {
                this.$message({
                    message: "请先选择歌曲！",
                    type: "error"
                })
            }

        }

    },
    created() {
        this.getSongTable()
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