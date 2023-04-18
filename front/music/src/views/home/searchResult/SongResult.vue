<template>
    <div class="search-song-box">
        <!-- 按钮 -->
        <div class="songList-btns">
            <el-button class="btns-item" icon="el-icon-video-play" @click="addToPlayList" type="success" plain>
                播放
            </el-button>
            <el-button class="btns-item" icon="el-icon-star-off" @click="starBtn" type="primary" plain>
                收藏
            </el-button>

            <el-popover placement="right" width="150" trigger="click">
                <AddBtnPopoverContent :selectdSongs="multipleSelection"></AddBtnPopoverContent>
                <el-button style="margin-left: 10px;" slot="reference" class="btns-item" icon="el-icon-plus">添加到</el-button>
            </el-popover>
        </div>
        <div class="search-song-table-box">
            <el-table ref="multipleTable" :data="songList" tooltip-effect="dark" style="width: 100%" stripe
                @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="55">
                </el-table-column>
                <el-table-column type="index" width="55">
                </el-table-column>

                <el-table-column prop="song.name" label="歌名" width="400">
                </el-table-column>
                <el-table-column label="歌手" show-overflow-tooltip>
                    <template slot-scope="scope">
                        <router-link v-for="singer in scope.row.singerList" :to="{
                            name: 'singerDetail',
                            query: {
                                singerId: singer.id
                            }

                        }" :key="singer.id">
                            {{ singer.name }}
                        </router-link>

                    </template>

                </el-table-column>

                <el-table-column prop="song.name" label="时长" width="120">
                    <template slot-scope="scope">
                        {{ $moment(scope.row.song.duration).format("mm:ss") }}
                    </template>
                </el-table-column>
            </el-table>
        </div>

    </div>
</template>

<script>
import play from '@/assets/js/playSong';
import star from '@/assets/js/starSong';
import AddBtnPopoverContent from '@/components/home/other/AddBtnPopoverContent.vue';

export default {
    name: 'SongResult',
    data() {
        return {
            key: "",
            songList: [],
            multipleSelection: []
        }
    },
    components: {
        AddBtnPopoverContent
    },
    methods: {
        //初始化
        init() {
            this.key = this.$route.query.searchKey
        },
        //获取歌曲列表
        async getSongList() {
            if (this.key === '') {
                return;
            }

            const resp = await this.$axios("/song/list/key/" + this.key)

            const respCode = resp.data.code
            if (respCode === 200) {
                this.songList = resp.data.data
            }
        },
        handleSelectionChange(val) {

            let songList = []

            val.forEach(element => {
                songList.push(element.song)
            });

            this.multipleSelection = songList;
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
    },
    async created() {
        await this.init()

        this.getSongList()

        //定时轮询路由参数
        setInterval(() => {
            let key = this.$route.query.searchKey

            if (key === this.key) {
                return;
            }
            this.key = key

        }, 10);
    },

    watch: {
        key(val) {
            if (val === '') {
                return;
            }
            this.getSongList()

        }
    }


}
</script>

<style scoped lang="less"> .search-song-table-box {
     height: 800px;
     overflow: auto;
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