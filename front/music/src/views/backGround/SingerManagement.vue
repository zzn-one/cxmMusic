<template>
    <div>
        <el-card class="box-card">

            <!-- 搜索框 -->
            <div class="search-box">
                <!-- 其他搜索条件 -->
                <div class="other-key-box">
                    其他搜索条件
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
                        <img :src="scope.row.avatarUrl" class="img-avatar">
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
            <el-form :model="editForm">
                <el-form-item label="歌手头像">
                    <el-input v-model="editForm.avatarUrl" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="歌手名称">
                    <el-input v-model="editForm.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="性别">
                    <el-input v-model="editForm.gender" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="类型">
                    <el-input v-model="editForm.type" autocomplete="off"></el-input>
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
        <el-dialog title="添加窗口" :visible.sync="addFormVisible">
            <el-form :model="addForm">
                <el-form-item label="歌手名称">
                    <el-input v-model="addForm.name" autocomplete="off"></el-input>
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

            // 表格数据
            tableData: [],
            //被选中的数据
            multipleSelection: [],
            //搜索关键字
            searchKey: "",

            //编辑窗口
            editFormVisible: false,
            editForm: "",

            //编辑窗口
            addFormVisible: false,
            addForm: "",

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


        //添加按钮点击
        addBtnClick() {
            this.addFormVisible = true
        },
        //搜索按钮点击
        searchBtnClick() {

        },
        //编辑按钮点击
        editBtnClick(row) {
            this.editForm = row

            console.log(this.editForm);
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

        },


        //-----------------------------添加窗口-------------------------
        //确认添加按钮点击
        addSubmitBtnClick() {

        },


        // --------------------------发送请求-----------------------------
        //1.获取歌手列表 分页查询
        async pageSingerList() {
            const resp = await this.$axios("/singer/list/" + this.currentPage + "/" + this.pageSize)
            const code = resp.data.code

            if (code === 200) {

                this.tableData = resp.data.data.records

                this.total = resp.data.data.total
                this.pageSize = resp.data.data.size
                this.currentPage = resp.data.data.current
            }
        },


        // 初始化方法
        init() {
            this.pageSingerList()
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
    width: 500px;
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
    border-radius: 25px;
}
</style>