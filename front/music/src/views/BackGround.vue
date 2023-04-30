<template>
    <div>
        <div class="title-box">
            <router-link :to="{ name: 'home' }">
                <img src="../assets/logo.png" class="img-box">
            </router-link>
        </div>

        <el-container>
            <el-aside width="200px" class="nav-box">

                <el-menu default-active="1" class="el-menu-vertical-demo" background-color="#D3DCE6" text-color="#000"
                    active-text-color="#f06312">

                    <el-menu-item index="1">
                        <i class="el-icon-user-solid"></i>
                        <span slot="title">
                            <router-link :to="{ name: 'userManagement' }" active-class="active">
                                用户管理
                            </router-link>
                        </span>
                    </el-menu-item>

                    <el-menu-item index="2">
                        <i class="el-icon-user"></i>
                        <span slot="title">
                            <router-link :to="{ name: 'singerManagement' }" active-class="active">
                                歌手管理
                            </router-link>
                        </span>
                    </el-menu-item>

                    <el-menu-item index="3">
                        <i class="el-icon-headset"></i>
                        <span slot="title">
                            <router-link :to="{ name: 'songManagement' }" active-class="active">
                                歌曲管理
                            </router-link>
                        </span>
                    </el-menu-item>

                    <el-menu-item index="4">
                        <i class="el-icon-s-grid"></i>
                        <span slot="title">
                            <router-link :to="{ name: 'songlistManagement' }" active-class="active">
                                歌单管理
                            </router-link>
                        </span>
                    </el-menu-item>
                </el-menu>

            </el-aside>
            <el-main class="main-box">
                <router-view></router-view>
            </el-main>
        </el-container>
    </div>
</template>

<script>
export default {
    data() {
        return {

        }
    }
}
</script>

<style scoped lang="less">
.img-box {
    width: 200px;
    margin: 10px 0;
}

.active {
    color: #f06312;
}

.title-box {
    width: 100%;
    background-color: #B3C0D1;

}

.nav-box {
    background: #D3DCE6;
    height: 899px;
}

.main-box {
    background: #E9EEF3;
}

.el-main {
    padding: 0;
}
</style>
