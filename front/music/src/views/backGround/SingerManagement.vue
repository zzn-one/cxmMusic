<template>
    <div>
        <el-card class="box-card">

            <!-- 搜索框 -->
            <div class="search-box">
                <!-- 其他搜索条件 -->
                <div class="other-key-box">
                    
                </div>
                <div class="search-input-box">
                    <el-input placeholder="请输入关键字" v-model="searchKey">
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
                <el-table-column label="头像" width="80">
                    <template slot-scope="scope">
                        <img :src="$imgPrefix + scope.row.avatarUrl" class="img-avatar">
                    </template>
                </el-table-column>
                <el-table-column prop="name" label="歌手名称">
                </el-table-column>
                <el-table-column prop="gender" label="性别">
                </el-table-column>
                <el-table-column prop="type" label="类型">
                </el-table-column>
                <el-table-column prop="introduction" label="简介" show-overflow-tooltip>
                </el-table-column>
                <el-table-column prop="songNumber" label="发表歌曲数量">
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
        <el-dialog title="编辑歌手信息" :visible.sync="editFormVisible">
            <el-form :model="editForm" label-position="left" label-width="100px">
                <el-form-item label="歌手头像">

                    <el-upload class="avatar-uploader" :action="actionUrl" :auto-upload="false" :show-file-list="false"
                        ref="editUpload" :on-change="editHandleAvatarChange" :before-upload="editBeforeAvatarUpload"
                        :data="{ id: editForm.id }" :headers="header">


                        <img v-if="temporaryUrl" :src="temporaryUrl" class="avatar">
                        <img v-else-if="editForm.avatarUrl" :src="$imgPrefix + editForm.avatarUrl" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>

                </el-form-item>
                <el-form-item label="歌手名称">
                    <el-input v-model="editForm.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="性别">
                    <el-select v-model="editForm.gender" placeholder="请选择性别">
                        <el-option v-for="gender in genderList" :key="gender.id" :label="gender.text" :value="gender.text">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="类型">

                    <el-select v-model="editForm.type" placeholder="请选择性别">
                        <el-option v-for="type in typeList" :key="type.id" :label="type.text" :value="type.text">
                        </el-option>
                    </el-select>

                </el-form-item>
                <el-form-item label="简介">
                    <el-input v-model="editForm.introduction" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="发表歌曲数量">
                    <el-input v-model="editForm.songNumber" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="editFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="editSubmitBtnClick">确认提交</el-button>
            </div>
        </el-dialog>


        <!-- 添加窗口 -->
        <el-dialog title="添加歌手" :visible.sync="addFormVisible">
            <el-form :model="addForm" label-position="left" label-width="100px">
                <el-form-item label="歌手头像">

                    <el-upload class="avatar-uploader" :action="actionUrl" :auto-upload="false" :show-file-list="false"
                        ref="addUpload" :on-change="addHandleAvatarChange" :before-upload="addBeforeAvatarUpload"
                        :data="{ id: addForm.id }" :headers="header">

                        <img v-if="addForm.avatarUrl" :src="addForm.avatarUrl" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>

                </el-form-item>
                <el-form-item label="歌手名称">
                    <el-input v-model="addForm.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="性别">
                    <el-select v-model="addForm.gender" placeholder="请选择性别">
                        <el-option v-for="gender in genderList" :key="gender.id" :label="gender.text" :value="gender.text">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="类型">

                    <el-select v-model="addForm.type" placeholder="请选择性别">
                        <el-option v-for="type in typeList" :key="type.id" :label="type.text" :value="type.text">
                        </el-option>
                    </el-select>

                </el-form-item>
                <el-form-item label="简介">
                    <el-input v-model="addForm.introduction" autocomplete="off"></el-input>
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
            //分页
            currentPage: 1,
            pageSize: 10,
            pageSizeList: [5, 10, 15, 20],
            total: 100,


            //性别列表
            genderList: [],
            //歌手类型列表
            typeList: [],

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
            temporaryUrl: "",
            //歌手图片上传的url
            actionUrl: this.$imgPrefix +"singer/upload/avatar",

            //添加窗口
            addFormVisible: false,
            addForm: { id: "" },


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

        //设置请求头
        setHeader() {

            this.header = {
                Authorization: localStorage.getItem("token")
            }
        },


        //添加按钮点击
        addBtnClick() {
            this.addFormVisible = true
        },
        //搜索按钮点击
        searchBtnClick() {
            this.pageSingerList()
        },
        //编辑按钮点击
        editBtnClick(row) {
            this.editForm = Object.assign({}, row)
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
                this.delSinger(row.id)


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
                this.batchDelSinger()

            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '批量删除操作已取消'
                });
            });
        },

        //-----------------------------编辑窗口-------------------------
        //确认提交按钮点击
        editSubmitBtnClick() {
            //上传图片
            this.$refs.editUpload.submit()

            //提交修改请求
            this.updateMsg()
        },

        editHandleAvatarChange(file) {
            this.editForm.avatarUrl = URL.createObjectURL(file.raw);
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


        //-----------------------------添加窗口-------------------------
        //确认添加按钮点击
        addSubmitBtnClick() {
            //发送添加歌手请求
            this.addSinger()
        },

        addHandleAvatarChange(file) {
            this.addForm.avatarUrl = URL.createObjectURL(file.raw);
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
        //设置添加表单的id
        setAddFormId(id) {
            this.addForm.id = id
        },
        // --------------------------发送请求-----------------------------
        //1.获取歌手列表 分页查询
        async pageSingerList() {
            const resp = await this.$axios("/singer/list/" + this.currentPage + "/" + this.pageSize + "/" + this.searchKey)
            const code = resp.data.code

            if (code === 200) {

                this.tableData = resp.data.data.records

                this.total = resp.data.data.total
                this.pageSize = resp.data.data.size
                this.currentPage = resp.data.data.current
            }
        },
        //2.获取性别列表
        async getGenderList() {
            const resp = await this.$axios("/dict/gender")
            const code = resp.data.code

            if (code === 200) {
                this.genderList = resp.data.data
            }
        },
        //3.获取歌手类型列表
        async getTypeList() {
            const resp = await this.$axios("/dict/singerType")
            const code = resp.data.code

            if (code === 200) {
                this.typeList = resp.data.data
            }
        },
        //4.修改歌手信息
        async updateMsg() {
            const resp = await this.$axios(
                {
                    url: "/singer",
                    method: "put",
                    data: this.editForm
                }
            )
            const code = resp.data.code

            if (code === 200) {

                this.$message.success("歌手信息已修改")

                this.editFormVisible = false
            }
        },
        //5.新增歌手
        async addSinger() {

            this.addForm.id = ""

            const resp = await this.$axios(
                {
                    url: "/singer",
                    method: "post",
                    data: this.addForm
                }
            )
            const code = resp.data.code

            if (code === 200) {
                await this.setAddFormId(resp.data.data.id)

                this.$refs.addUpload.submit()

                this.addFormVisible = false

                this.$message.success("歌手添加成功")
            }
        },
        //6.删除歌手
        async delSinger(id) {
            const resp = await this.$axios(
                {
                    url: "/singer/" + id,
                    method: "delete",
                }
            )
            const code = resp.data.code

            if (code === 200) {

                this.$message.success("歌手信息删除成功")
                //重新请求数据
                this.pageSingerList()
            }
        },
        //7.删除歌手 批量
        async batchDelSinger() {
            const resp = await this.$axios(
                {
                    url: "/singer/",
                    method: "delete",
                    data: this.multipleSelection
                }
            )
            const code = resp.data.code

            if (code === 200) {

                this.$message.success("歌手信息删除成功")
                //重新请求数据
                this.pageSingerList()
            }
        },

        // 初始化方法
        init() {

            this.setHeader()

            this.pageSingerList()

            this.getGenderList()

            this.getTypeList()
        },


    },
    watch: {
        currentPage() {
            //重新请求数据
            this.pageSingerList()
        },
        pageSize() {
            //重新请求数据
            this.pageSingerList()
        },
        editFormVisible(val) {
            if (val === false) {
                //重新请求数据
                this.pageSingerList()
            }
        },
        addFormVisible(val) {
            if (val === false) {
                //重新请求数据
                this.pageSingerList()
            }
        },
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
    max-width: 620px;
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