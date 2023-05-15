<template>
    <div>
        <el-card class="box-card">

            <!-- 搜索框 -->
            <div class="search-box">
                <!-- 其他搜索条件 -->
                <div class="other-key-box">
                </div>
                <div class="search-input-box">
                    <el-input placeholder="搜索歌单" v-model="searchKey">
                        <el-button slot="append" icon="el-icon-search" @click="searchBtnClick"></el-button>
                    </el-input>
                </div>

            </div>

            <!-- 按钮 -->
            <div class="btns-box">
                <el-button type="danger" class="btn" @click="batchDelBtnClick">批量删除</el-button>
                <!-- <el-button type="warning" class="btn" @click="addBtnClick">添加</el-button> -->
            </div>

            <!-- 表格 -->
            <el-table :data="tableData" border style="width: 100%" height="700" @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="55">
                </el-table-column>
                <el-table-column type="index" width="55" label="序号" :index="indexMethod">
                </el-table-column>

                <el-table-column label="封面" width="80">
                    <template slot-scope="scope">
                        <img :src="$imgPrefix + scope.row.imgUrl" class="img-avatar">
                    </template>
                </el-table-column>
                <el-table-column prop="name" label="歌单名" show-overflow-tooltip>
                </el-table-column>
                <el-table-column prop="authorName" label="作者">
                </el-table-column>
                <el-table-column prop="authorAccount" label="作者账号">
                </el-table-column>
                <el-table-column label="标签">
                    <template slot-scope="scope">
                        <el-tag>{{ scope.row.tag }}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column prop="introduction" label="简介" show-overflow-tooltip>
                </el-table-column>
                <el-table-column prop="songNumber" label="歌曲数量">
                </el-table-column>
                <el-table-column label="播放量">
                    <template slot-scope="scope">
                        {{ numberFormat(scope.row.playNumber) }}
                    </template>
                </el-table-column>
                <el-table-column label="收藏量">
                    <template slot-scope="scope">
                        {{ numberFormat(scope.row.starNumber) }}
                    </template>
                </el-table-column>
                <el-table-column prop="createdTime" label="创建时间" show-overflow-tooltip>
                    <template slot-scope="scope">
                        {{ $moment(scope.row.createdTime).format("yyyy年M月D日 h:m:s") }}
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
        <el-dialog title="修改歌单信息" :visible.sync="editFormVisible">
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
                <el-form-item label="歌单名称">
                    <el-input v-model="editForm.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="标签">
                    <el-select v-model="editForm.tag" placeholder="请选择">
                        <el-option v-for="tag in tagList" :key="tag.id" :label="tag.text" :value="tag.text">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="简介">

                    <el-input v-model="editForm.introduction" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="editFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="editSubmitBtnClick">确认提交</el-button>
            </div>
        </el-dialog>
        <!-- 添加窗口 -->
        <!-- <el-dialog title="添加窗口" :visible.sync="addFormVisible">
            <el-form :model="addForm">
                <el-form-item label="属性1">
                    <el-input v-model="addForm.name" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="addFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="addSubmitBtnClick">确认添加</el-button>
            </div>
        </el-dialog> -->

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
            actionUrl: this.$imgPrefix + "songList/upload/img",

            //编辑窗口
            // addFormVisible: false,
            // addForm: "",

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

        //添加按钮点击
        // addBtnClick() {
        //     this.addFormVisible = true
        // },
        //搜索按钮点击
        searchBtnClick() {
            this.pageData()
        },
        //编辑按钮点击
        editBtnClick(row) {
            this.temporaryUrl = ''
            this.editForm = row
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
                this.delOne(row.id)
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
            await this.updateSonglist()

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
                this.$message.error('上传图片只能是 JPG 格式!');
            }
            if (!isLt2M) {
                this.$message.error('上传图片大小不能超过 2MB!');
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
        // addSubmitBtnClick() {

        // },

        //-----------------------------发送请求-------------------------
        async updateSonglist() {
            const resp = await this.$axios({
                url: "songList",
                method: "put",
                data: this.editForm
            })

            const code = resp.data.code
            if (code === 200 && resp.data.data === true) {
                this.$message.success("用户信息已更改")
            }
        },
        //删除歌单
        async delOne(id) {
            const resp = await this.$axios.delete("songList/" + id)

            const code = resp.data.code
            if (code === 200 && resp.data.data === true) {

                this.pageData()

                this.$message.success("歌单已删除")
            }
        },

        //删除歌单 批量
        async delBatch() {
            const resp = await this.$axios({
                url: "songList/",
                method: 'delete',
                data: this.multipleSelection
            })

            const code = resp.data.code
            if (code === 200 && resp.data.data === true) {

                this.pageData()

                this.$message.success("歌单批量删除成功")
            }
        },
        //获取表格数据
        async pageData() {
            const resp = await this.$axios({
                url: "/songList/page/" + this.currentPage + "/" + this.pageSize,
                method: "post",
                data: { key: this.searchKey }
            })

            const code = resp.data.code
            if (code === 200) {
                this.tableData = resp.data.data.records
                this.total = resp.data.data.total

                console.log(this.tableData);

            }
        },
        //获取标签列表
        async getTagList() {
            const resp = await this.$axios("/dict/tag/all")

            const code = resp.data.code
            if (code === 200) {
                this.tagList = resp.data.data
            }
        },

        init() {
            this.setHeader()
            this.getTagList()
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

.img-avatar {
    width: 50px;
    height: 50px;
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