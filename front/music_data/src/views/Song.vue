<template>
    <div>
        <el-form ref="form" :model="form" label-width="200px">
            <el-form-item label="歌曲名称">
                <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="封面图片路径">
                <el-input v-model="form.imgUrl"></el-input>
            </el-form-item>
            <el-form-item label="时长">
                <el-input v-model.number="form.duration"></el-input>
            </el-form-item>
            <el-form-item label="资源路径">
                <el-input v-model="form.sourceUrl"></el-input>
            </el-form-item>
            <el-form-item label="标签">
                <el-checkbox-group v-model="form.tagIdList">
                    <el-checkbox v-for="tag in tagList" :label="tag.id" name="tagIdList">{{ tag.text }}</el-checkbox>
                </el-checkbox-group>
            </el-form-item>

            <el-form-item label="歌手">

                <el-radio v-for="singer in singerList" v-model="form.singerId" :label="singer.id" :key="singer.id">{{
                    singer.name }}</el-radio>

            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="onSubmit">新增</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
export default {
    name: "Song",
    data() {
        return {
            form: {
                name: "",
                imgUrl: '',
                duration: '',
                sourceUrl: "",
                tagIdList: [],
                singerId: '',
            },
            tagList: [],
            singerList: []

        }
    },
    methods: {
        //提交表单
        async onSubmit() {
            const resp = await this.$axios({
                method: "post",
                data: this.form,
                url: "/song"
            })

            if (resp.data.code === 200 && resp.data.data === true) {
                this.$message({
                    message: "新增歌曲成功",
                    type: "success"
                })
            } else {
                this.$message({
                    message: "新增歌曲失败",
                    type: "error"
                })
            }
        },
        //获取标签列表
        async getTagList() {
            const resp = await this.$axios("/dict/tag")

            if (resp.data.code === 200) {
                this.tagList = resp.data.data
            }
        },
        //获取歌手列表
        async getSingerList() {
            const resp = await this.$axios("/singer/list")

            if (resp.data.code === 200) {
                this.singerList = resp.data.data
            }
        }

    },

    created() {
        this.getSingerList()
        this.getTagList()
    }


}
</script>