<template>
    <div class="hall_box">
        <!-- 音乐馆导航 -->
        <div class="hall_nav_box">
            <div class="hall_nav_body">

                <div class="hall_nav_item">
                </div>

                <div class="hall_nav_item">
                    <RouterLink to="/home/musicHall/hallHome" active-class="active">首页</RouterLink>
                </div>
                <div class="hall_nav_item">
                    <RouterLink to="/home/musicHall/singerList" active-class="active">歌手</RouterLink>
                </div>
                <div class="hall_nav_item">
                    <RouterLink to="/home/musicHall/songList" active-class="active">分类歌单</RouterLink>
                </div>

                <div class="hall_nav_item">
                </div>
            </div>
        </div>
        <!-- 路由 -->
        <RouterView class="box_body"></RouterView>
    </div>
</template>

<script>

export default {
    name: "MusicHall",
    data() {
        return {};
    },
    methods: {},
}
</script>
<style scoped>
.hall_box {}

.box_body {
    width: 1200px;
    margin: 0 auto;
}

.hall_nav_box {
    background-color: #fff;
}
.hall_nav_body{
    width: 1200px;
    display: flex;
    margin: 0 auto;
    text-align: center;
    height: 50px;
    line-height: 50px;
}
.hall_nav_item {
    flex: 1;
}

.active {
    color: #f06312;
}
</style>