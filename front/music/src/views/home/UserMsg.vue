<template>
    <div class="userMsg-box">
        <!-- 左侧导航栏 -->
        <div class="nav-box">
            <ul class="nav-ul">
                <li v-for="nav in navList" class="nav-li">
                    <i :class="nav.icon"></i>
                    <RouterLink :to="nav.router" active-class="active">
                        {{ nav.name }}
                    </RouterLink>
                </li>
            </ul>

        </div>
        <!-- 右侧内容显示 -->
        <div class="msg-box">
            <RouterView></RouterView>
        </div>
    </div>
</template>

<script>
import { RouterLink, RouterView } from 'vue-router';

export default {
    name: "UserMSg",
    data() {
        return {
            navList: []
        };
    },
    created() {
        this.navList = [
            {
                name: "个人信息",
                router: { name: "personalMsg" },
                icon:"el-icon-user"
            },
            {
                name: "我的歌单",
                router: { name: "personalSongList" },
                icon:"el-icon-tickets"

            },
            {
                name: "听歌记录",
                router: { name: "historySongs" },
                icon:"el-icon-headset"
            },
        ];
    },
    components: { RouterLink, RouterView }
}
</script>

<style scoped lang="less">
.userMsg-box {
    width: 1200px;
    margin: 0 auto;
    display: flex;
}
.nav-box{
    flex: 1;
    height: 800px;
    background-color: #fff;
}
.nav-ul {
    list-style-type: none;
    padding: 0;
    margin: 0;
}

.nav-li {
    height: 50px;
    line-height: 50px;
    background-color: #fff;
}

.msg-box{
    flex: 4;
    height: 800px;
    background-color: #fff;

}
.active {
    color: rgb(240, 99, 18);
}
</style>