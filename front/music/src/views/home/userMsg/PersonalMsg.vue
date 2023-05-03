<template>
    <div class="personalMsg-box">
        <!-- 头像区 -->
        <div class="avatar-box">


            <el-upload class="avatar-uploader avatar-box-item" :action="actionUrl" :auto-upload="true"
                :show-file-list="false" ref="editUpload" :on-error="errorAvatarUpload" :before-upload="beforeAvatarUpload"
                :on-success="successAvatarUpload" :data="{ id: user.id }" :headers="header">

                <img v-if="temporaryUrl" :src="temporaryUrl" class="avatar">
                <img v-else-if="user.avatarUrl" :src="$imgPrefix + user.avatarUrl" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>

            <div class="name-box avatar-box-item">
                <div class="name-text">
                    {{ user.name }}
                </div>
                <div class="signature-text">
                    {{ user.signature }}
                </div>
                <div class="registerTime-text">
                    注册时间：{{ $moment(user.registerTime).format("yyyy-MM-DD") }}
                </div>
            </div>
        </div>
        <!-- 个人信息详情区 -->
        <div class="msg-box">
            <div class="msg-box-title">
                <div class="msg-box-title-msg msg-box-title-item">
                    个人信息
                </div>
                <div class="msg-box-title-btns msg-box-title-item">
                    <el-button v-if="user.type === 1" @click="openBackGround">进入后台</el-button>
                    <el-button @click="msgFormVisible = true">修改信息</el-button>
                    <el-button @click="passwordFormVisible = true">修改密码</el-button>
                </div>
            </div>
            <ul class="msg-box-ul">
                <li class="msg-box-li">
                    <!-- label -->
                    <div class="msg-box-label msg-box-item">
                        昵&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp称
                    </div>
                    <!-- content -->
                    <div class="msg-box-content msg-box-item">
                        {{ user.name }}
                    </div>
                </li>
                <li class="msg-box-li">
                    <!-- label -->
                    <div class="msg-box-label msg-box-item">
                        账&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp号
                    </div>
                    <!-- content -->
                    <div class="msg-box-content msg-box-item">
                        {{ user.account }}
                    </div>
                </li>
                <li class="msg-box-li">
                    <!-- label -->
                    <div class="msg-box-label msg-box-item">
                        性&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp别
                    </div>
                    <!-- content -->
                    <div class="msg-box-content msg-box-item">
                        {{ user.gender }}
                    </div>
                </li>
                <li class="msg-box-li">
                    <!-- label -->
                    <div class="msg-box-label msg-box-item">
                        个性签名
                    </div>
                    <!-- content -->
                    <div class="msg-box-content msg-box-item">
                        {{ user.signature }}
                    </div>
                </li>
            </ul>
        </div>

        <!-- 修改密码对话框 -->
        <el-dialog title="修改密码" :visible.sync="passwordFormVisible" width="550px">
            <el-form :model="passwordForm" label-position="left" label-width="100px" :rules="rules" ref="passwordForm">
                <el-form-item label="旧密码" prop="oldPassword">
                    <el-input v-model="passwordForm.oldPassword" autocomplete="off" type="password"></el-input>
                </el-form-item>
                <el-form-item label="确认旧密码" prop="oldPassword2">
                    <el-input v-model="passwordForm.oldPassword2" autocomplete="off" type="password"></el-input>
                </el-form-item>
                <el-form-item label="新密码" prop="newPassword">
                    <el-input v-model="passwordForm.newPassword" autocomplete="off" type="password"></el-input>
                </el-form-item>
                <el-form-item label="确认新密码" prop="newPassword2">
                    <el-input v-model="passwordForm.newPassword2" autocomplete="off" type="password"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer">
                <el-button @click="submitPasswordForm('passwordForm')">提交</el-button>
            </div>
        </el-dialog>

        <!-- 修改信息对话框 -->
        <el-dialog title="修改信息" :visible.sync="msgFormVisible" width="550px">
            <el-form :model="msgForm" label-position="left" label-width="100px" :rules="msgRules" ref="msgForm">
                <el-form-item label="昵称" prop="name">
                    <el-input v-model="msgForm.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="性别">
                    <el-select v-model="msgForm.gender" placeholder="请选择性别">
                        <el-option v-for="gender in genderList" :label="gender.text" :value="gender.text"
                            :key="gender.id"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="个性签名">
                    <el-input v-model="msgForm.signature" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer">
                <el-button @click="submitMsgForm('msgForm')">提交</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>

import token from '@/assets/js/token';
export default {
    name: "personalMsg",
    data() {
        // 密码校验规则
        var validateOldPassword = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入旧密码'));
            } else {
                if (this.passwordForm.oldPassword !== '') {
                    this.$refs.passwordForm.validateField('oldPassword2');
                }
                callback();
            }
        };
        var validateOldPassword2 = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请再次输入旧密码'));
            } else if (value !== this.passwordForm.oldPassword) {
                callback(new Error('两次输入的旧密码不一致!'));
            } else {
                callback();
            }
        };
        var validateNewPassword = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入新密码'));
            } else {
                if (this.passwordForm.newPassword !== '') {
                    this.$refs.passwordForm.validateField('newPassword2');
                }
                callback();
            }
        };
        var validateNewPassword2 = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请再次输入新密码'));
            } else if (value !== this.passwordForm.newPassword) {
                callback(new Error('两次输入的新密码不一致!'));
            } else {
                callback();
            }
        };


        return {
            //用户信息
            user: {},
            //修改信息对话框
            msgFormVisible: false,
            msgForm: {},
            //修改信息对话框
            passwordFormVisible: false,
            passwordForm: {
                id: "",
                account: "",
                oldPassword: '',
                oldPassword2: '',
                newPassword: '',
                newPassword2: '',
            },
            //性别数据字典
            genderList: [],

            //密码表单的校验规则
            rules: {
                oldPassword: [
                    { validator: validateOldPassword, trigger: 'blur' }
                ],
                oldPassword2: [
                    { validator: validateOldPassword2, trigger: 'blur' }
                ],
                newPassword: [
                    { validator: validateNewPassword, trigger: 'blur' }
                ],
                newPassword2: [
                    { validator: validateNewPassword2, trigger: 'blur' }
                ],
            },
            //个人信息表单的校验规则
            msgRules: {
                name: [
                    { required: true, message: '请输入账号昵称', trigger: 'blur' },
                    { min: 1, max: 16, message: '长度在 1 到 16 个字符', trigger: 'blur' }
                ],
            },

            header: {},
            temporaryUrl: "",
            //用户图片上传的url
            actionUrl: this.$imgPrefix + "user/upload/img",
        }
    },
    methods: {
        //提交密码表单
        submitPasswordForm(formName) {
            const _this = this
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    //校验通过

                    //设置用户的id和account
                    _this.passwordForm.id = _this.user.id
                    _this.passwordForm.account = _this.user.account

                    //发生请求
                    _this.$axios({
                        method: "put",
                        data: this.passwordForm,
                        url: "/user/update/password"
                    }).then(resp => {
                        const code = resp.data.code
                        if (code === 200) {
                            this.$message({
                                message: "密码已修改！",
                                type: "success"
                            })

                            //关闭密码表单窗口
                            this.passwordFormVisible = false

                        } else {
                            this.$message({
                                message: resp.data.msg,
                                type: "error"
                            })
                        }
                    })


                } else {
                    return false;
                }
            });
        },

        //提交个人信息表单
        submitMsgForm(formName) {
            const _this = this
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    //校验通过
                    _this.$axios({
                        method: "put",
                        data: this.msgForm,
                        url: "/user/update/msg"
                    }).then(resp => {
                        const code = resp.data.code

                        if (code === 200) {
                            this.$message({
                                message: "信息已修改！",
                                type: "success"
                            })

                            //关闭表单窗口 并 刷新个人信息
                            this.msgFormVisible = false
                            this.getUserMsg()

                        } else {
                            this.$message({
                                message: resp.data.msg,
                                type: "error"
                            })
                        }
                    })
                } else {
                    return false;
                }
            });
        },
        openBackGround() {
            this.$router.push("/backGround");
        },

        //设置请求头
        setHeader() {

            this.header = {
                Authorization: localStorage.getItem("token")
            }
        },
        errorAvatarUpload(err) {
            this.$message.error("更换头像失败，请稍后再试")
        },
        beforeAvatarUpload(file) {
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
        successAvatarUpload(response, file, fileList) {
            this.temporaryUrl = URL.createObjectURL(file.raw);

            if (response.code === 200) {
                this.$message.success("头像已更换")
            }

        },

        //获得用户个人信息
        async getUserMsg() {
            const account = token().account
            const resp = await this.$axios("/user/one/" + account)

            const code = resp.data.code
            if (code === 200) {
                this.user = resp.data.data
                //对象深拷贝
                this.msgForm = structuredClone(this.user)
            } else if (code === 10021) {
                this.$message({
                    message: resp.data.msg,
                    type: "error"
                })
            } else {
                this.$message({
                    message: resp.data.msg,
                    type: "error"
                })
            }
        },
        //获取性别列表
        async getGenderList() {

            const resp = await this.$axios("/dict/gender")


            const code = resp.data.code
            if (code === 200) {
                this.genderList = resp.data.data
            }

        }
    },
    created() {

        this.getGenderList()
        this.getUserMsg()
        this.setHeader()



    }

}
</script>
<style scoped lang="less">
.personalMsg-box {
    background-color: #fff;
}

.avatar-box-item {
    display: inline-block;
    margin: 30px;
}

.avatar-img {
    width: 100px;
    height: 100px;
    border-radius: 50px;
    margin-left: 30px;
}

.name-box {
    height: 100px;
    position: absolute;
}

.name-text {
    margin: 10px 0 0 30px;
    font-size: 24px;
}

.signature-text {
    margin: 5px 0 0 30px;
}

.registerTime-text {
    margin: 5px 0 0 30px;
}

.msg-box {
    margin-top: 30px;
}

.msg-box-title {
    font-size: 24px;
    height: 50px;
}

.msg-box-title-item {
    display: inline-block;
}

.msg-box-title-btns {
    float: right;
}

.msg-box-title-msg {
    line-height: 50px;
}




.msg-box-ul {
    list-style-type: none;
    margin: 20px 0;
}

.msg-box-item {
    display: inline-block;
    font-size: 18px;
    color: rgb(73, 69, 69);
    margin: 10px 0;
}

.msg-box-label {
    width: 200px;
}


/deep/.el-input__inner {
    width: 400px;
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
    width: 100px;
    height: 100px;
    line-height: 100px;
    text-align: center;
    border-radius: 50px;
}

.avatar {
    width: 100px;
    height: 100px;
    display: block;
    border-radius: 50px;
}
</style>