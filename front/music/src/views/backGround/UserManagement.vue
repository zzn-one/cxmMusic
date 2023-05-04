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
                <!-- <el-button type="warning" class="btn" @click="addBtnClick">添加</el-button> -->
            </div>

            <!-- 表格 -->
            <el-table :data="tableData" border style="width: 100%" height="700" @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="55">
                </el-table-column>
                <el-table-column type="index" width="55" label="序号" :index="indexMethod">
                </el-table-column>
                <el-table-column label="头像" width="122">
                    <template slot-scope="scope">
                        <img :src="$imgPrefix + scope.row.avatarUrl" class="img-avatar">
                    </template>
                </el-table-column>
                <el-table-column prop="account" label="账号">
                </el-table-column>
                <el-table-column prop="name" label="昵称" show-overflow-tooltip>
                </el-table-column>
                <el-table-column prop="gender" label="性别">
                </el-table-column>
                <el-table-column prop="signature" label="签名" show-overflow-tooltip>
                </el-table-column>
                <el-table-column label="类型">
                    <template slot-scope="scope">
                        {{ userTypeFormat(scope.row.type) }}
                    </template>
                </el-table-column>
                <el-table-column label="最新登录时间" show-overflow-tooltip>
                    <template slot-scope="scope">
                        <span v-if="scope.row.lastedLoginTime">
                            {{ $moment(scope.row.lastedLoginTime).format("yyyy年M月D日 h:m:s") }}
                        </span>
                    </template>
                </el-table-column>
                <el-table-column label="账号注册时间" show-overflow-tooltip>
                    <template slot-scope="scope">
                        <span v-if="scope.row.registerTime">
                            {{ $moment(scope.row.registerTime).format("yyyy年M月D日 h:m:s") }}
                        </span>
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
        <el-dialog title="修改用户信息" :visible.sync="editFormVisible">
            <el-form :model="editForm" label-position="left" label-width="100px">
                <el-form-item label="账号">
                    <el-input v-model="editForm.account" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="昵称">
                    <el-input v-model="editForm.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="用户类型">
                    <el-select v-model="editForm.type" placeholder="请选择用户类型">
                        <el-option v-for="type in typeList" :key="type.value" :label="type.key" :value="type.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="性别">
                    <el-select v-model="editForm.gender" placeholder="请选择用户类型">
                        <el-option v-for="gender in genderList" :key="gender.text" :label="gender.text"
                            :value="gender.text">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="签名">
                    <el-input v-model="editForm.signature" autocomplete="off"></el-input>
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
            genderList: [],
            typeList: [],
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
            editFormVisible: false,
            editForm: "",

            //添加窗口
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


        // //添加按钮点击
        // addBtnClick() {
        //     this.addFormVisible = true
        // },
        //搜索按钮点击
        searchBtnClick() {
            this.pageData()
        },
        //编辑按钮点击
        editBtnClick(row) {
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
        //用户类型格式转换
        userTypeFormat(type) {
            let result = ""
            if (type == "1") {
                result = "管理员"
            } else {
                result = "普通用户"
            }

            return result
        },

        //-----------------------------编辑窗口-------------------------
        //确认提交按钮点击
        editSubmitBtnClick() {
            this.updateMsg()
            this.editFormVisible = false
        },


        //-----------------------------添加窗口-------------------------
        //确认添加按钮点击
        // addSubmitBtnClick() {

        // },
        //-----------------------------发送请求-------------------------
        async axios111() {
            const resp = await this.$axios("")

            const code = resp.data.code
            if (code === 200) {

            }
        },
        //修改用户信息
        async updateMsg() {
            const resp = await this.$axios({
                url: "user/update/msg",
                method: 'put',
                data: this.editForm
            })

            const code = resp.data.code
            if (code === 200) {
                this.pageData()
                this.$message.success("用户信息已修改")
            }
        },
        async getGenderList() {
            const resp = await this.$axios("dict/gender")

            const code = resp.data.code
            if (code === 200) {
                this.genderList = resp.data.data
            }
        },
        async delOne(id) {
            const resp = await this.$axios.delete("user/" + id)

            const code = resp.data.code

            if (code === 200 && resp.data.data === true) {
                this.pageData()
                this.$message.success("账号已删除")
            }
        },
        async delBatch() {
            const resp = await this.$axios({
                url: "user",
                data: this.multipleSelection,
                method: "delete"
            })

            const code = resp.data.code
            if (code === 200 && resp.data.data === true) {
                this.pageData()
                this.$message.success("账号已删除")
            }
        },

        //获取表格数据
        async pageData() {
            const resp = await this.$axios({
                url: "/user/list/" + this.currentPage + "/" + this.pageSize,
                method: "post",
                data: { key: this.searchKey }
            })

            const code = resp.data.code
            if (code === 200) {
                this.tableData = resp.data.data.records
                this.total = resp.data.data.total
            }
        },


        init() {
            this.pageData()

            this.getGenderList()

            this.typeList.push(
                {
                    key: "管理员",
                    value: 1
                },
                {
                    key: "普通用户",
                    value: 0
                }
            )
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

.img-avatar {

    width: 100px;
    height: 100px;
    border-radius: 50px;

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