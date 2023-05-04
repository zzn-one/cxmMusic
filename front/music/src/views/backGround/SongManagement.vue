<template>
    <div>
        <el-card class="box-card">

            <!-- 搜索框 -->
            <div class="search-box">
                <!-- 其他搜索条件 -->
                <div class="other-key-box">

                </div>
                <div class="search-input-box">
                    <el-input placeholder="搜索歌曲" v-model="searchKey">
                        <el-button slot="append" icon="el-icon-search" @click="searchBtnClick"></el-button>
                    </el-input>
                </div>

            </div>

            <!-- 按钮 -->
            <div class="btns-box">
                <el-button type="danger" class="btn" @click="batchDelBtnClick">批量删除</el-button>
                <el-button type="warning" class="btn" @click="addBtnClick">添加</el-button>
            </div>

            <!-- 表格 -->
            <el-table :data="tableData" border style="width: 100%" height="700" @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="55">
                </el-table-column>
                <el-table-column type="index" width="55" label="序号" :index="indexMethod">
                </el-table-column>
                <el-table-column prop="song.imgUrl" label="图片" width="80">
                    <template slot-scope="scope">
                        <img :src="$imgPrefix + scope.row.song.imgUrl" class="img-avatar">
                    </template>
                </el-table-column>
                <el-table-column prop="song.name" label="歌名" show-overflow-tooltip>
                </el-table-column>
                <el-table-column label="歌手" show-overflow-tooltip>
                    <template slot-scope="scope">
                        <span v-for="singer in scope.row.singerList" :key="singer.id">
                            {{ singer.name }}
                        </span>
                    </template>
                </el-table-column>
                <el-table-column label="标签" width="350px" show-overflow-tooltip>
                    <template slot-scope="scope">
                        <el-tag v-for="tag in scope.row.tagList" :key="tag.id">
                            {{ tag.text }}
                        </el-tag>
                    </template>
                </el-table-column>
                <el-table-column prop="song.duration" label="时长">
                    <template slot-scope="scope">
                        {{ $moment(scope.row.song.duration).format("mm:ss") }}
                    </template>
                </el-table-column>
                <el-table-column prop="song.playNumber" label="播放量">
                    <template slot-scope="scope">
                        {{ numberFormat(scope.row.song.playNumber) }}
                    </template>
                </el-table-column>
                <el-table-column prop="song.starNumber" label="收藏量">
                    <template slot-scope="scope">
                        {{ numberFormat(scope.row.song.starNumber) }}
                    </template>
                </el-table-column>

                <el-table-column fixed="right" label="操作" width="400">
                    <template slot-scope="scope">
                        <el-button @click="editBtnClick(scope.row)" type="primary" class="btn">编辑</el-button>
                        <el-button @click="delBtnClick(scope.row)" type="danger" class="btn">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <!-- 分页 -->
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
                :page-sizes="pageSizeList" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
                :total="total">
            </el-pagination>
        </el-card>
        <!-- 编辑窗口 -->
        <el-dialog title="修改歌曲信息" :visible.sync="editFormVisible">
            <el-form :model="editForm" label-position="left" label-width="100px">
                <el-form-item label="图片">

                    <el-upload class="avatar-uploader" :action="actionUrl" :auto-upload="false" :show-file-list="false"
                        ref="editUpload" :on-change="editHandleAvatarChange" :before-upload="editBeforeAvatarUpload"
                        :data="{ id: editForm.id }" :headers="header">

                        <img v-if="temporaryUrl" :src="temporaryUrl" class="avatar">
                        <img v-else-if="editForm.imgUrl" :src="$imgPrefix + editForm.imgUrl" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>


                </el-form-item>
                <el-form-item label="歌名">
                    <el-input v-model="editForm.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="歌手">

                    <el-transfer :titles="['歌手列表', '演唱歌手']" :button-texts="['移除 选中的 歌手', '添加 选中的 歌手']"
                        v-model="editSingerIdList" :data="singerTransferDataFormat(singerList)">
                    </el-transfer>

                </el-form-item>
                <el-form-item label="标签">

                    <el-transfer :titles="['未拥有的标签', '已拥有的标签']" :button-texts="['移除 选中的 标签', '添加 选中的 标签']"
                        v-model="editTagIdList" :data="transferDataFormat(tagList)">
                    </el-transfer>

                </el-form-item>
                <el-form-item label="时长(毫秒)">
                    <el-input v-model="editForm.duration" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="资源路径">
                    <el-input v-model="editForm.sourceUrl" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="editFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="editSubmitBtnClick">确认提交</el-button>
            </div>
        </el-dialog>
        <!-- 添加窗口 -->
        <el-dialog title="添加窗口" :visible.sync="addFormVisible">
            <el-form :model="addForm" label-position="left" label-width="100px">
                <el-form-item label="图片">

                    <el-upload class="avatar-uploader" :action="actionUrl" :auto-upload="false" :show-file-list="false"
                        ref="addUpload" :on-change="addHandleAvatarChange" :before-upload="addBeforeAvatarUpload"
                        :data="{ id: addForm.id }" :headers="header">

                        <img v-if="addForm.imgUrl" :src="addForm.imgUrl" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>


                </el-form-item>
                <el-form-item label="歌名">
                    <el-input v-model="addForm.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="歌手">

                    <el-transfer :titles="['歌手列表', '演唱歌手']" :button-texts="['移除 选中的 歌手', '添加 选中的 歌手']"
                        v-model="addSingerIdList" :data="singerTransferDataFormat(singerList)">
                    </el-transfer>

                </el-form-item>
                <el-form-item label="标签">

                    <el-transfer :titles="['未拥有的标签', '已拥有的标签']" :button-texts="['移除 选中的 标签', '添加 选中的 标签']"
                        v-model="addTagIdList" :data="transferDataFormat(tagList)">
                    </el-transfer>

                </el-form-item>
                <el-form-item label="时长(毫秒)">
                    <el-input v-model="addForm.duration" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="资源路径">
                    <el-input v-model="addForm.sourceUrl" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="addFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="addSubmitBtnClick">确认添加</el-button>
            </div>
        </el-dialog>

    </div>
</template>
<script>
export default {
    data() {
        return {
            tagList: [],
            singerList: [],

            //分页
            currentPage: 1,
            pageSize: 10,
            pageSizeList: [5, 10, 15, 20],
            total: 100,

            // 表格数据
            tableData: [],
            //被选中的数据
            multipleSelection: [],
            //搜索关键字
            searchKey: "",

            //编辑窗口
            header: {},
            editFormVisible: false,
            editForm: {},
            editTagIdList: [],
            editSingerIdList: [],
            temporaryUrl: "",
            //歌手图片上传的url
            actionUrl: this.$imgPrefix + "song/upload/img",

            //编辑窗口
            addFormVisible: false,
            addForm: {
                id: "",
                imgUrl: "",
                createTime: "",
            },
            addTagIdList: [],
            addSingerIdList: [],

        }
    },
    methods: {
        //表格索引方法
        indexMethod(index) {
            return (this.currentPage - 1) * this.pageSize + index + 1
        },
        //选中事件触发
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },

        //pageSize改变
        handleSizeChange(val) {
            this.pageSize = val
        },
        //currentPage改变
        handleCurrentChange(val) {
            this.currentPage = val
        },
        //数量格式化
        numberFormat(number) {

            if (number >= 100000000) {
                let x = (number / 100000000).toFixed(2)
                number = x + "亿"
            } else if (number >= 10000) {
                let x = (number / 10000).toFixed(2)
                number = x + "万"
            }
            return number
        },
        //标签选择框的数据格式化
        transferDataFormat(tagList) {
            let tagListFormat = []

            tagList.forEach(val => {
                tagListFormat.push({
                    label: val.text,
                    key: val.id
                })
            })

            return tagListFormat
        },
        //歌手选择框的数据格式化
        singerTransferDataFormat(singerList) {
            let singerListFormat = []

            singerList.forEach(val => {
                singerListFormat.push({
                    label: val.name,
                    key: val.id
                })
            })

            return singerListFormat
        },

        //添加按钮点击
        addBtnClick() {
            this.addForm = {
                id: "",
                imgUrl: "",
                createTime: "",
            }
            this.editTagIdList = []
            this.editSingerIdList = []
            this.addFormVisible = true
        },
        //搜索按钮点击
        searchBtnClick() {
            this.pageData()
        },
        //编辑按钮点击
        editBtnClick(row) {
            this.temporaryUrl = ''

            this.editForm = row.song

            this.editTagIdList = []
            row.tagList.forEach(val => {
                this.editTagIdList.push(val.id)
            })

            this.editSingerIdList = []
            row.singerList.forEach(val => {
                this.editSingerIdList.push(val.id)
            })

            this.editFormVisible = true
        },
        //删除按钮点击
        delBtnClick(row) {
            this.$confirm('此操作将永久删除这条数据, 是否继续?', '警告', {
                confirmButtonText: '确认删除',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                //发送删除请求
                this.delOne(row.song.id)
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '删除操作已取消'
                });
            });
        },
        //批量删除按钮点击
        batchDelBtnClick() {
            this.$confirm('此操作将永久删除您所选中的数据, 是否继续?', '批量删除警告', {
                confirmButtonText: '确认删除',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                //发送批量删除请求
                this.delBatch()
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '批量删除操作已取消'
                });
            });
        },

        //-----------------------------编辑窗口-------------------------
        //确认提交按钮点击
        async editSubmitBtnClick() {
            await this.updateSong()

            //上传图片
            this.$refs.editUpload.submit()
            this.editFormVisible = false

            this.pageData()
        },

        editHandleAvatarChange(file) {
            this.editForm.imgUrl = URL.createObjectURL(file.raw);
            this.temporaryUrl = URL.createObjectURL(file.raw);
        },
        editBeforeAvatarUpload(file) {
            const isJPG = file.type === 'image/jpeg';
            const isLt2M = file.size / 1024 / 1024 < 2;

            if (!isJPG) {
                this.$message.error('上传头像图片只能是 JPG 格式!');
            }
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 2MB!');
            }
            return isJPG && isLt2M;
        },
        //设置请求头
        setHeader() {

            this.header = {
                Authorization: localStorage.getItem("token")
            }
        },


        //-----------------------------添加窗口-------------------------
        //确认添加按钮点击
        async addSubmitBtnClick() {
            await this.addSong()
            //上传图片
            this.$refs.addUpload.submit()
        },
        addHandleAvatarChange(file) {
            this.addForm.imgUrl = URL.createObjectURL(file.raw);
        },
        addBeforeAvatarUpload(file) {
            const isJPG = file.type === 'image/jpeg';
            const isLt2M = file.size / 1024 / 1024 < 2;

            if (!isJPG) {
                this.$message.error('上传头像图片只能是 JPG 格式!');
            }
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 2MB!');
            }
            return isJPG && isLt2M;
        },

        //-----------------------------发送请求-------------------------


        //新增歌曲信息
        async addSong() {
            this.addForm.createTime = new Date()
            const resp = await this.$axios({
                url: "/song",
                method: "post",
                data: {
                    song: this.addForm,
                    tagIdList: this.addTagIdList,
                    singerIdList: this.addSingerIdList
                }
            })

            const code = resp.data.code

            if (code === 200) {

                //给addForm赋值songId
                this.addForm.id = resp.data.data.id
                //重新请求数据
                this.pageData()
                this.$message.success("新增歌曲成功")

                this.addFormVisible = false
            }
        },
        //获取歌手列表
        async getSingerList() {
            const resp = await this.$axios("/singer/list")

            const code = resp.data.code
            if (code === 200) {
                this.singerList = resp.data.data
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
        //修改歌曲信息
        async updateSong() {
            const resp = await this.$axios({
                url: "/song",
                method: "put",
                data: {
                    song: this.editForm,
                    tagIdList: this.editTagIdList,
                    singerIdList: this.editSingerIdList
                }
            })

            const code = resp.data.code
            if (code === 200 && resp.data.data === true) {
                this.$message.success("歌曲信息已修改")
            }
        },
        //获取歌曲列表
        async pageData() {

            let url = "/song/page/" + this.currentPage + "/" + this.pageSize

            const resp = await this.$axios({
                method: "post",
                url: url,
                data: { key: this.searchKey }
            })

            const code = resp.data.code

            if (code === 200) {
                this.tableData = resp.data.data.records
                this.total = resp.data.data.total
            }
        },
        //删除歌曲
        async delOne(id) {
            const resp = await this.$axios.delete("/song/" + id)

            const code = resp.data.code

            console.log(resp);
            if (code === 200) {
                //重新请求数据
                this.pageData()
                this.$message.success("歌曲已删除")
            }
        },
        //删除歌曲 批量
        async delBatch() {

            let songList = []
            this.multipleSelection.forEach(val => {
                songList.push(val.song)
            })

            const resp = await this.$axios({
                url: "/song/",
                method: "delete",
                data: songList
            })

            const code = resp.data.code
            if (code === 200) {
                //重新请求数据
                this.pageData()
                this.$message.success("歌曲已删除")
            }
        },


        init() {
            this.setHeader()
            this.getTagList()
            this.getSingerList()

            this.pageData()
        }



    },
    watch: {
        currentPage() {
            //重新请求数据
            this.pageData()
        },
        pageSize() {
            //重新请求数据
            this.pageData()
        }
    },
    created() {

        this.init()

    }
}

</script>

<style scoped lang="less">
.box-card {
    height: 897px;
}

.search-box {
    margin: 10px 0;
}

.other-key-box {
    display: inline-block;
    max-width: 500px;
    height: 1px;
}

.search-input-box {
    display: inline-block;
    width: 400px;
}

.btns-box {
    margin: 10px 0;
}

.btn {
    width: 100px;
}

.img-avatar {
    width: 50px;
    height: 50px;
}

/deep/.el-form .el-select,
/deep/.el-form .el-input {
    width: 800px;
}

.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}

.avatar-uploader .el-upload:hover {
    border-color: #409EFF;
}

.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
}

.avatar {
    width: 178px;
    height: 178px;
    display: block;
}
</style>