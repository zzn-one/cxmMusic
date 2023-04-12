<template>
    <div class="edit_box">
        <div class="msg-box">
            <div class="img-box msg-box-item">
                <img src="@/assets/2.jpg" alt="">
            </div>
            <div class="text-box msg-box-item">
                <div class="text-msg-box">
                    <div class="text-box-item text-name-box">
                        {{ songlist.name }}
                    </div>
                    <div class="text-box-item text-author-box">
                        <i class="el-icon-user"></i>
                        {{ songlist.authorName }}
                    </div>
                    <div v-show="songlist.tag !== null" class="text-box-item text-tag-box">
                        <el-tag type="success">{{ songlist.tag }}</el-tag>
                    </div>
                    <div class="text-box-item text-introduction-box">
                        {{ songlist.introduction }}
                    </div>
                </div>
                <div>
                    <el-button class="msg-btns" type="primary" plain @click="playSonglist">
                        <i class="el-icon-video-play"></i>
                        播放
                    </el-button>

                    <el-button class="msg-btns" type="warning" plain @click="msgFormVisible = true">
                        <i class="el-icon-edit"></i>
                        编辑
                    </el-button>

                    <el-button class="msg-btns" type="danger" plain @click="deleteBtn">
                        <i class="el-icon-delete"></i>
                        删除
                    </el-button>
                </div>
            </div>

        </div>
        <div class="table-box">
            <div class="table-nav-box">
                <router-link :to="{ name: 'songTableEdit', query: { id: songlistId } }" active-class="active">
                    歌曲({{ songlist.songNumber }})
                </router-link>
                <router-link :to="{ name: 'starEdit', query: { id: songlistId } }" active-class="active">
                    最近收藏
                </router-link>
                <!-- <router-link :to="{ name: 'commentEdit', query: { id: songlistId } }" active-class="active">
                    评论
                </router-link> -->
            </div>
            <div class="table-data-box">
                <router-view></router-view>
            </div>
            <!-- 占位 -->
            <div style="height: 50px;">

            </div>
        </div>


        <!-- 编辑窗口 -->
        <el-dialog title="歌单信息编辑" :visible.sync="msgFormVisible" width="800px">
            <el-form :model="songlistForm" label-position="left">
                <el-form-item label="歌单名称" label-width="100px">
                    <el-input v-model="songlistForm.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="标签" label-width="100px">
                    <el-select v-model="songlistForm.tag" placeholder="请选择标签">
                        <el-option v-for="item in tagList" :key="item.id" :label="item.text" :value="item.text">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="简介" label-width="100px">
                    <el-input v-model="songlistForm.introduction" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="创建时间" label-width="100px">
                    <el-input :value="$moment(songlistForm.createdTime).format('yyyy-MM-DD hh:mm:ss')" autocomplete="off"
                        disabled></el-input>
                </el-form-item>
                <el-form-item label="歌曲数量" label-width="100px">
                    <el-input v-model="songlistForm.songNumber" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="播放量" label-width="100px">
                    <el-input v-model="songlistForm.playNumber" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="收藏量" label-width="100px">
                    <el-input v-model="songlistForm.starNumber" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item class="form-btns-div">
                    <el-button type="success" class="form-btns" @click="saveEdit">保存</el-button>
                    <el-button type="warning" class="form-btns" @click="msgFormVisible = false">取消</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>

    </div>
</template>

<script>

export default {
    name: "SongListEdit",
    data() {
        return {
            songlist: {},
            tagList: [],
            songlistId: '',
            msgFormVisible: false,
            songlistForm: {}
        }
    },
    methods: {
        //删除按钮
        deleteBtn() {

            this.$confirm('歌单及其相关信息将会被永久删除, 是否继续?', '警告', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'error'
            }).then(() => {
                this.$axios({
                    url: "/songList/" + this.songlistId,
                    method: "delete"
                }).then(resp => {
                    const code = resp.data.code

                    if (code === 200) {
                        this.$message({
                            type: 'success',
                            message: '歌单已被永久删除!即将跳转到主页'
                        });

                        //跳转到主页
                        setTimeout(() => {
                            this.$router.push({ "name": "/" })
                        }, 2000)
                    }
                })
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });



        },
        //获取歌单对象
        async getSonglist() {
            const resp = await this.$axios("/songList/" + this.songlistId)

            const code = resp.data.code

            if (code === 200) {
                this.songlist = resp.data.data
            }
        },
        //获取标签列表
        async getTagList() {
            const resp = await this.$axios("/dict/tag")

            const code = resp.data.code

            if (code === 200) {
                this.tagList = resp.data.data
            }
        },
        //修改歌单信息(保存按钮)
        async saveEdit() {
            const resp = await this.$axios({
                url: "/songList",
                data: this.songlistForm,
                method: "put"
            })

            const code = resp.data.code

            if (code === 200) {
                this.$message.success("歌单信息已保存！")
                this.msgFormVisible = false
                //刷新歌单信息
                await this.getSonglist()
                this.songlistForm = structuredClone(this.songlist)
            } else {
                this.$message.error(resp.data.msg)
            }
        },
        //播放歌单
        async playSonglist() {
            const account = this.$token().account

            const resp = await this.$axios({
                url: "/play/" + account + "/" + this.songlistId,
                method: "put"
            })

            const code = resp.data.code

            if (code === 200) {
                //跳转到播放页
                const { href } = this.$router.resolve({
                    name: "playList"
                })

                window.open(href, href)
            }
        },
        //初始化
        async init() {

            //获取标签
            this.getTagList()
            //获取歌单信息
            this.songlistId = this.$route.query.id

            await this.getSonglist()
            this.songlistForm = structuredClone(this.songlist)
        }
    },
    created() {
        this.init()

    }
}
</script>

<style scoped lang="less">
.edit_box {
    width: 1200px;
    margin: 0 auto;
}

.msg-box {
    display: flex;
}

.msg-box-item {}

.img-box {
    flex: 1;
    margin: 10px;
}

.img-box img {
    width: 200px;
    height: 200px;
    border-radius: 10px;
}

.text-box {
    flex: 4;
}

.table-nav-box {
    margin: 10px;
}

.text-box-item {
    margin: 10px 0;
}

.active {
    color: rgb(240, 99, 18);
}

.text-name-box {
    font-size: 24px;
}

.msg-btns {
    margin-top: 20px;
    width: 100px;
}

.text-msg-box {
    height: 140px;
}

.text-introduction-box {
    overflow: hidden;
    height: 42px;
    color: rgb(124, 124, 124);
}

.form-btns {
    margin-top: 20px;
    width: 250px;
    margin-right: 20px;
}

.form-btns-div {

    margin-left: 110px;
}

/deep/.el-input {
    width: 600px;
}

/deep/.el-select {
    width: 600px;
}
</style>