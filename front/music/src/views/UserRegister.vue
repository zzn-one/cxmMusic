<template>
    <el-card class="box-card">
        <div slot="header">
            <RouterLink :to="{ name: '/' }">
                <img src="@/assets/logo.png" style="height: 40px; width:160px">
            </RouterLink>

        </div>
        <div class="form-box">
            <!-- 注册表单 -->
            <div class="box-card-item-title">
                欢迎注册MM音乐

            </div>
            <el-row>
                <!-- 昵称为空时 提示的信息 -->
                <span v-show="nameIsEmpty" class="box-card-item-errorTip">
                    {{ nameEmptyTip }}
                </span>
                <el-input class="box-card-item" v-model="userForm.name" placeHolder="昵称" />
            </el-row>
            <el-row>
                <el-input class="box-card-item" v-model="userForm.password" placeHolder="密码" type="password" />
            </el-row>

            <el-row>
                <!-- 密码不相同提示的信息 -->
                <span v-show="!passwordIsSame" class="box-card-item-errorTip">
                    {{ passwordErrorTip }}
                </span>
                <el-input class="box-card-item" v-model="userForm.password2" placeHolder="确认密码" type="password" />
            </el-row>
            <i class="icon ion-checkmark-circled"></i>
            <el-button class="box-card-item box-card-item-button" @click="registerUser">立即注册</el-button>
        </div>


    </el-card>
</template>

<script>
export default {
    name: 'UserRegister',
    data() {
        return {
            userForm: {
                name: '',
                password: '',
                password2: ''
            },
            passwordIsSame: true,
            passwordErrorTip: '两次密码不相同，请重新输入！',
            nameIsEmpty: false,
            nameEmptyTip: '昵称不能为空！',
        }
    },
    methods: {
        registerUser() {
            //验证昵称是否为空
            if (this.userForm.name.trim() === '' || this.userForm.name === null) {
                //昵称为空，出提示
                this.nameIsEmpty = true
                return;
            }

            // 验证两个密码是否相同
            if (this.userForm.password !== this.userForm.password2) {
                //不相同 出提示
                this.passwordIsSame = false
                return;
            }

            //表单验证通过，提交注册请求 

        },

    }
}
</script>

<style scoped lang="less">
.box-card {
    height: 967px;

    background: url("@/assets/2.jpg");
    background-size: cover;
}

.box-card-item {
    display: block;
    min-width: 340px;
    width: 340px;
    height: 30px;
    min-height: 30px;
    margin: 0 auto;
    margin-bottom: 30px;
}

.box-card-item-button {
    border: none;
    min-width: 344px;
    width: 344px;
    height: 34px;
    min-height: 34px;
    margin: 0 auto;
    margin-top: 30px;
    margin-bottom: 20px;
    font-size: 16px;
    background-color: #66b1ff;
    color: white;

}

.box-card-item-title {
    height: 60px;
    width: 340px;
    text-align: center;
    line-height: 60px;
    margin: 0 auto;
    margin-top: 200px;
    margin-bottom: 20px;
    font-size: 30px;

}

.box-card-item-errorTip {
    font-size: 12px;
    color: red;
    display: block;
    min-width: 340px;
    width: 340px;
    height: 30px;
    min-height: 30px;
    margin: 0 auto;
}

.icon {
    font-size: 50px;
}

/deep/.el-card__header {
    padding: 0;

}
</style>