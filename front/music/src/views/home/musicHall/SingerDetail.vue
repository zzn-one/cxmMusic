<template>
    <div class="singer-detail-box">
        <!-- 歌手信息 -->
        <div class="singer-msg-box">
            <!-- 照片 -->
            <div class="singer-img-box">
                <img :src="singer.avatar_url" class="singer-img">
            </div>
            <!-- 文字信息 -->
            <div class="singer-introduce-box">
                <!-- 歌手名称 -->
                <div class="singer-name-box">
                    {{ singer.name }}
                </div>
                <!-- 简介 -->
                <div class="introducr-box">
                    {{ singer.introduce }}
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
                <el-button class="btns-item" icon="el-icon-video-play" type="success" plain>
                    播放
                </el-button>
                <el-button class="btns-item" icon="el-icon-star-off" >
                    收藏
                </el-button>
                <el-button class="btns-item" icon="el-icon-plus">
                    添加到
                </el-button>
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
    </div>
</template>
<script>
export default {
    name: "SingerDetail",
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
        }
    },
    created() {
        this.singer = {
            name: "陈奕迅",
            avatar_url: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            introduce: "这是歌手简介啊啊啊嗷嗷嗷哈哈哈",
            songNumber: 132,
        }

        this.songList = [
            {
                name: "不要说话",
                duration: 237000,
            },
            {
                name: "红玫瑰",
                duration: 217000,
            },
            {
                name: "红玫瑰",
                duration: 217000,
            }, {
                name: "红玫瑰",
                duration: 217000,
            }, {
                name: "红玫瑰",
                duration: 217000,
            }, {
                name: "红玫瑰",
                duration: 217000,
            }, {
                name: "红玫瑰",
                duration: 217000,
            }, {
                name: "红玫瑰",
                duration: 217000,
            }, {
                name: "红玫瑰",
                duration: 217000,
            }, {
                name: "红玫瑰",
                duration: 217000,
            }, {
                name: "红玫瑰",
                duration: 217000,
            }, {
                name: "红玫瑰",
                duration: 217000,
            }, {
                name: "红玫瑰",
                duration: 217000,
            },
        ]
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