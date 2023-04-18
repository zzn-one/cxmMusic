<template>
    <div>
        <el-row class="headerNav">
            <!-- 网站名称与图片标签 -->
            <el-col :span="6">
                <div class="navItem">
                    <RouterLink to="/home/mMusic" active-class="active">
                        <!-- 跳转到音乐馆 -->
                        <img src="@/assets/logo.png" class="logo">
                        <!-- <span style="font-size: 32px;">MM音乐</span> -->
                    </RouterLink>

                </div>
            </el-col>
            <!-- 音乐馆 -->

            <el-col :span="3">
                <div class="navItem">
                    <RouterLink to="/home/musicHall" active-class="active">
                        音乐馆
                    </RouterLink>

                </div>
            </el-col>
            <!-- 我的音乐 -->

            <el-col :span="3">
                <div class="navItem">
                    <RouterLink to="/home/userMusic" active-class="active">
                        我的音乐
                    </RouterLink>

                </div>
            </el-col>
            <!-- 搜索框 -->
            <el-col :span="9">
                <div class="navItem">
                    <el-input placeholder="搜索音乐、歌单、歌手" v-model="searchKey">
                        <el-button slot="append" icon="el-icon-search" @click="searchMusic"></el-button>
                    </el-input>
                </div>
            </el-col>
            <!-- 登录按钮 / 我的音乐 -->
            <el-col :span="3">
                <!-- 未登录  -->
                <div class="navItem" v-show="!isLogin">
                    <el-button type="text" @click="openLoginDialog">
                        <span class="login_btn">登录</span>
                    </el-button>
                </div>
                <!-- 已有用户登录(jwt) -->
                <div class="navItem" v-show="isLogin">
                    <div>
                        <el-popover placement="top-start" width="150" trigger="hover">
                            <div>

                                <div>
                                    <img class="avatar-img" :src="user.avatarUrl" style="display: inline-block;">
                                    <div
                                        style="display: inline-block; margin-top:25px;margin-left:10px;position:absolute;font-size:16px">
                                        {{ user.name }}
                                    </div>
                                </div>

                                <RouterLink :to="{
                                    name: 'userMsg', params: {
                                        account: user.account
                                    }
                                }" class="avatar-btn">
                                    个人中心
                                </RouterLink>

                                <el-button class="avatar-btn" type="text" @click="logout()">
                                    退出登录
                                </el-button>

                            </div>

                            <el-button type="text" slot="reference">
                                <img class="avatar-img" :src="user.avatarUrl">
                            </el-button>
                        </el-popover>
                    </div>
                </div>
            </el-col>

            <!-- 登录窗口 -->
            <el-dialog :visible.sync="loginDialogVisible" width="500px" center>
                <el-card class="box-card">
                    <!-- 登录表单 -->
                    <div class="box-card-item-title">
                        密码登录
                    </div>
                    <el-row>
                        <el-input class="box-card-item" v-model="userForm.account" placeHolder="请输入账号" />
                    </el-row>
                    <el-row>
                        <el-input class="box-card-item" v-model="userForm.password" placeHolder="请输入密码" type="password" />
                    </el-row>
                    <el-button class="box-card-item box-card-item-button" @click="login">登 录</el-button>
                    <div class="box-card-item">
                        <el-button style="float: left;color:black" type="text">找回密码</el-button>
                        <el-button style="float: right;color:black" type="text" @click="openRegister">注册账号</el-button>
                    </div>

                </el-card>

            </el-dialog>
        </el-row>
    </div>
</template>
<script>
import token from '@/assets/js/token';
export default {
    name: "HeaderNav",
    data() {
        return {
            searchKey: "",
            loginDialogVisible: false,
            isLogin: false,
            userForm: {
                account: "",
                password: ""
            },
            user: {
                account: "",
                name: "",
                avatarUrl: ""
            }
        };
    },
    methods: {
        // 搜索音乐、歌单
        searchMusic() {
            //携带搜索关键字 跳转到搜索结果页面
            this.$router.push({
                name: "searchResult",
                query: {
                    searchKey: this.searchKey,
                }
            });
        },
        //打开登录页面
        openLoginDialog() {
            this.loginDialogVisible = true;
        },
        //打开注册页面
        openRegister() {
            this.$router.push("/userRegister");
        },
        //退出登录
        async logout() {
            //发请求
            this.$axios({
                method: "post",
                url: "/user/logout",
                data: this.user
            }).then((resp) => {
                const code = resp.data.code


                if (code === 200 && resp.data.data === true) {
                    //成功退出登录

                    //删除本地token
                    localStorage.removeItem("token")

                    this.isLogin = false
                }
            }).catch(error => {
                localStorage.removeItem("token")
            })

            this.$router.push("/")
        },
        //登录
        async login() {
            //发请求
            const resp = await this.$axios({
                method: "post",
                url: "/user/login",
                data: this.userForm
            })

            const code = resp.data.code
            if (code === 200) {
                //登录成功
                //1.用户本地存token（身份认证）
                localStorage.setItem("token", resp.data.data)
                //2.展示头像(页面标记已有用户登录)
                this.isLogin = true

                //3.给点提示信息
                this.$message({
                    message: "登录成功",
                    type: "success"
                })
                //4.清空输入的密码
                this.userForm.password = ''
                //5.关闭登录窗口
                this.loginDialogVisible = false


                this.$router.push({
                    name: "hallHome",
                    query: {
                        isLogin: true
                    }
                })


            }
            else if (code === 10001) {
                //账号或密码错误
                this.$message({
                    message: resp.data.msg,
                    type: "error"
                })
            } else if (code === 10002) {
                //账号不存在
                this.$message({
                    message: resp.data.msg,
                    type: "error"
                })
            } else if (code === 10003) {
                //账号未登录
                this.$message({
                    message: resp.data.msg,
                    type: "error"
                })
            } else {
                //系统异常
                this.$message({
                    message: "系统异常",
                    type: "error"
                })
            }
        }
    },
    watch: {
        //监听用户登录状态
        isLogin(newValue) {
            if (newValue === true) {
                //初始化用户信息
                this.user.account = token().account
                this.user.name = token().name
                this.user.avatarUrl = token().avatarUrl
            }

        }
    },
    created() {
        //查看有无本地token
        if (localStorage.getItem("token") !== null) {
            this.isLogin = true
        }

    }
}
</script>
<style scoped lang="less">
.headerNav {
    min-height: 110px;
    height: 110px;
}

.navItem {
    line-height: 90px;
    text-align: center;
}

.active {
    color: rgb(240, 99, 18);
}

.logo {
    margin-top: 30px;
    height: 40px;
}


.login_btn {
    color: black;
    font-size: 16px;
}


.box-card-item {
    display: block;
    min-width: 240px;
    width: 240px;
    height: 30px;
    min-height: 30px;
    margin: 0 auto;
    margin-bottom: 20px;
}

.box-card-item-button {
    border: none;
    min-width: 244px;
    width: 244px;
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
    width: 240px;
    font-size: 20px;
    text-align: center;
    line-height: 60px;
    margin: 0 auto;
    margin-bottom: 20px;
}

.avatar-img {
    margin-top: 10px;
    height: 50px;
    width: 50px;
    border-radius: 60px;

}

.avatar-btn {
    display: block;
    font-size: 16px;
    margin: 8px 0;
    color: black;
}
</style>