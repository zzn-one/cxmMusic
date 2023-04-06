<template>
    <div class="singer-detail-box">
        <!-- 歌手信息 -->
        <div class="singer-msg-box">
            <!-- 照片 -->
            <div class="singer-img-box">
                <img :src="singer.avatarUrl" class="singer-img">
            </div>
            <!-- 文字信息 -->
            <div class="singer-introduce-box">
                <!-- 歌手名称 -->
                <div class="singer-name-box">
                    {{ singer.name }}
                </div>
                <!-- 简介 -->
                <div class="introducr-box">
                    {{ singer.introduction }}
                </div>
                <!-- 歌曲数量 -->
                <div class="singer-song-number-box">
                    单曲<span style="font-size: 24px;"> {{ singer.songNumber }} </span>
                </div>
            </div>
        </div>
        <!-- 歌曲列表 -->
        <div class="songList-box">
            <!-- 标题 -->
            <div class="songList-title-box">
                歌曲
            </div>
            <!-- 按钮 -->
            <div class="songList-btns">
                <el-button class="btns-item" icon="el-icon-video-play" @click="addToPlayList">
                    播放
                </el-button>
                <el-button class="btns-item" icon="el-icon-star-off" @click="starBtn">
                    收藏
                </el-button>

                <el-popover placement="right" width="150" trigger="click">
                    <AddBtnPopoverContent :selectdSongs="multipleSelection"></AddBtnPopoverContent>
                    <el-button style="margin-left: 10px;" slot="reference" class="btns-item" icon="el-icon-plus" @click="addSongToBtn">添加到</el-button>
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

                    <el-table-column label="时长" width="180">
                        <template slot-scope="scope">
                            {{ $moment(scope.row.duration).format("mm:ss") }}
                        </template>

                    </el-table-column>
                </el-table>
            </div>
        </div>
        <!-- 占位 -->
        <div style="height: 50px;"></div>
    </div>
</template>
<script>
import play from '@/assets/js/playSong';
import star from '@/assets/js/starSong';
import AddBtnPopoverContent from '@/components/home/other/AddBtnPopoverContent.vue';
export default {
    name: "SingerDetail",
    components:{
        AddBtnPopoverContent
    },
    data() {
        return {
            singer: {},
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
            const id = this.$route.query.singerId
            const _this = this

            //获取歌手信息
            this.$axios("/singer/" + id).then(resp => {
                const code = resp.data.code

                if (code === 200) {
                    this.singer = resp.data.data
                } else {
                    this.message({
                        message: resp.data.msg,
                        type: "error"
                    })
                }
            })


            //获取歌手的歌曲列表
            this.$axios("/song/list/" + id).then(resp => {
                const code = resp.data.code

                if (code === 200) {
                    this.songList = resp.data.data
                }
            })
        },
        //播放按钮
        addToPlayList() {
            const account = this.$token().account
            if (this.multipleSelection.length > 0) {
                play(this.multipleSelection, account)
            } else {
                this.$message({
                    message: "需要先选择歌曲哟~",
                    type: "warning"
                })
            }
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
        //添加到按钮
        addSongToBtn() {
        }

    },
    created() {
        this.init()

    }
}
</script>

<style scoped lang="less">
.singer-msg-box {
    display: flex;
    margin: 40px 0;

}

.singer-img-box {
    flex: 1;
}

.singer-img {
    height: 250px;
    width: 250px;
    border-radius: 125px;
}

.singer-introduce-box {
    flex: 3;
}

.singer-name-box {
    font-size: 32px;
    margin: 50px 0 20px 0;
}

.introducr-box {
    margin-bottom: 20px;
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
    font-size: 16px;
    width: 150px;

}
</style>