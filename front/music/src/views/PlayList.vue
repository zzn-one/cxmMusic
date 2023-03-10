<template>
    <div class="playList-box">
        <!-- 透明遮罩 -->
        <div class="playList-mask-box">
            <!-- 头部 -->
            <div class="header-box">
                <div class="header-logo">
                    <a href="/" style="color: rgba(3, 3, 3, 0.616)" @mouseover="$event.target.style.color = 'white'"
                        @mouseout="$event.target.style.color = 'black'">
                        {{ logo }}
                    </a>
                </div>
                <div class="header-avatar">
                    <a href="/#/home/userMusic/userSong">

                        <div class="header-name-box">
                            疯原万叶
                        </div>

                        <div class="header-img-box">
                            <img src="@/assets/avatar1.jpg" class="header-img-body">
                        </div>
                    </a>
                </div>
            </div>

            <!-- 播放器主体 -->
            <div class="play-body-box">
                <!-- 歌曲列表 + 歌曲信息展示 -->
                <div class="song-box">
                    <!-- 歌曲列表 -->
                    <div class="song-list-box">
                        <div class="song-btns-box">
                            <el-button icon="el-icon-star-off" class="song-btn">收藏</el-button>
                            <el-button icon="el-icon-plus" class="song-btn">添加到</el-button>
                            <el-button icon="el-icon-close" class="song-btn">删除</el-button>
                            <el-button icon="el-icon-delete" class="song-btn">清空列表</el-button>
                        </div>
                        <div class="song-table-box">
                            <el-table ref="multipleTable" :data="songsTableData" tooltip-effect="dark" style="width: 100%;"
                                height="528px" @selection-change="handleSelectionChange">
                                <el-table-column type="selection" width="55">
                                </el-table-column>
                                <el-table-column type="index" width="50">
                                </el-table-column>
                                <el-table-column label="歌曲">
                                    <template slot-scope="scope">{{ scope.row.name }}</template>
                                </el-table-column>
                                <el-table-column prop="songerName" label="歌手" width="120">
                                </el-table-column>
                                <el-table-column prop="duration" label="时长" width="120" show-overflow-tooltip>
                                </el-table-column>
                            </el-table>
                        </div>

                    </div>
                    <!-- 歌曲信息展示 -->
                    <div class="song-msg-box">
                        <div class="song-info-box">
                            <img class="song-info-img" src="@/assets/篮球鸡.webp" >
                            <div class="song-info-item">
                                歌曲名：只因你太霉
                            </div>
                            <div class="song-info-item">
                                歌手：鸡哥
                            </div>

                        </div>

                    </div>
                </div>
                <!-- 底部 播放组件 -->
                <div class="play-box">
                    <MusicPlayer>
                        
                    </MusicPlayer>
                </div>
            </div>


        </div>
    </div>
</template>
  
<script>
import MusicPlayer from '@/components/playList/MusicPlayer.vue';
export default {
    name: "PlayList",
    components:{
        MusicPlayer,
    },
    data() {
        return {
            logo: "MM音乐",
            songsTableData: [],
            multipleSelection: []
        }
    },
    methods: {
        //选择变动事件  val 被选中的对象
        handleSelectionChange(val) {
            this.multipleSelection = val;
        }

    },
    created() {
        for (let i = 0; i < 20; i++) {
            this.songsTableData.push({
                name: "只因你太霉",
                songerName: "鸡哥",
                duration: '3:08'
            })
        }
    }
}
</script>
  
<style lang="less" scoped>
.playList-box {
    width: 100%;
    height: 969px;
    background: url('@/assets/2.jpg');
    background-size: 200%;
    color: rgb(77, 76, 76);


}

.playList-mask-box {
    width: 100%;
    height: 969px;
    background-color: rgba(255, 255, 255, 0.8);
}


.header-box {
    display: flex;
    color: rgba(3, 3, 3, 0.616);
}

.header-logo {

    flex: 1;
    float: left;
    margin-left: 40px;
    height: 50px;
    line-height: 50px;
    font-size: 20px;
    margin-top: 10px;
}

.header-avatar {
    margin-top: 10px;
    flex: 1;
}

.header-img-box {
    float: right;
    height: 50px;
    width: 50px;
}

.header-img-body {
    height: 50px;
    width: 50px;
    border-radius: 60px;

}

.header-name-box {
    float: right;
    height: 50px;
    line-height: 50px;
    margin-right: 20px;
}

.play-body-box {
    margin: 10px 140px;
}

.song-box {
    display: flex;
}

.song-list-box {
    flex: 3;
}

.song-msg-box {
    flex: 1;
    background-color: rgba(255, 255, 255, 0);
}

.song-btn {
    margin: 10px 10px 10px 0;
    width: 120px;
    background-color: rgba(255, 255, 255, 0);
}

/*修改el-table背景色*/
.song-table-box{
    width: 100%;
    margin: auto;
}

.song-table-box /deep/ .el-table--fit{
    padding: 0px;
}
.song-table-box /deep/  .el-table, .el-table__expanded-cell {
    background-color: transparent;
}

.song-table-box /deep/ .el-table tr {
    background-color: transparent!important;
}
.song-table-box /deep/ .el-table th {
    background-color: transparent!important;
}
.song-table-box /deep/  .el-table--enable-row-transition .el-table__body td, .el-table .cell{
   background-color: transparent;
}
.el-table::before {
	 left: 0;
	 bottom: 0;
	 width: 100%;
	 height: 0px;
}

/*歌曲信息*/
.song-info-box{
    margin-left: 40px;
}
.song-info-img{
    margin: 0 auto;
    width: 300px;
    height:300px;
}
.song-info-item{
    font-size: 18px;
    text-align: center;
    margin: 10px 0;
}
</style>