<template>
    <div>
        <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="歌手名称">
                <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="性别">
                <el-select v-model="form.gender" placeholder="请选择歌手性别">
                    <el-option v-for="gender in genderList" :label="gender.text" :value="gender.text" :key="gender.id">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="歌手类型">
                <el-select v-model="form.type" placeholder="请选择歌手类型">
                    <el-option v-for="type in typeList" :label="type.text" :value="type.text" :key="type.id">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="头像路径">
                <el-input v-model="form.avatarUrl"></el-input>
            </el-form-item>
            <el-form-item label="歌手简介">
                <el-input v-model="form.introduction"></el-input>

            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit">新增</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
export default {
    name: "Singer",
    data() {
        return {
            form: {
                name: "",
                gender: "",
                type: '',
                avatarUrl: '',
                introduction: '',
            },
            genderList: [],
            typeList: [],
        }
    },
    methods: {
        //提交表单
        async onSubmit() {
            const resp = await this.$axios({
                method: "post",
                data: this.form,
                url: "/singer"
            })

            if (resp.data.code === 200 && resp.data.data === true) {
                this.$message({
                    message:"新增成功",
                    type:"success"
                })
            }else{
                this.$message({
                    message:"新增失败",
                    type:"error"
                })
            }
        },
        //获取性别列表
        async getGenderList() {
            const resp = await this.$axios("/dict/gender")

            if (resp.data.code === 200) {
                this.genderList = resp.data.data
            }
        },
        //获取类型列表
        async getTypeList() {
            const resp = await this.$axios("/dict/singerType")

            if (resp.data.code === 200) {
                this.typeList = resp.data.data
            }
        }

    },

    created() {
        this.getGenderList()
        this.getTypeList()
    }

}
</script>