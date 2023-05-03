<template>
    <div class="userMusic-box">
        <!-- 背景图与头像 -->
        <div class="user-msg-box">
            <div class="user-msg-body">
                <img :src="$imgPrefix + user.avatarUrl" alt="加载失败" class="user-msg-avatar-img">
                <div class="user-msg-name">{{ user.name }}</div>
            </div>
        </div>


        <!-- 歌曲与歌单 -->
        <div class="songs-box">
            <!-- 导航栏 -->
            <div class="songs-nav-box">
                <ul class="nav-ul">
                    <li v-for="item in navList" class="nav-li">
                        <RouterLink :to="{ name: item.routerName }" active-class="active">{{ item.name }}</RouterLink>
                    </li>
                </ul>

            </div>
            <!-- 歌曲/歌单 的table -->
            <RouterView class="song-table-box"></RouterView>
        </div>

    </div>
</template>

<script>

export default {
    name: "UserMusic",

    data() {
        return {
            user: {},
            navList: [],
            currentNav: "",
        };
    },
    methods: {

    },

    created() {
        this.user = {
            name: this.$token().name,
            avatarUrl: this.$token().avatarUrl,
        };
        this.navList = [
            {
                name: "歌曲",
                routerName: "userSong"
            },
            {

                name: "歌单",
                routerName: "userSongList"
            }
        ];
    },
}
</script>
<style scoped>
.user-msg-box {
    height: 220px;
    width: 100%;
    background: url('@/assets/1.jpg');
    background-size: 100%;
}

.user-msg-body {
    margin: 0 auto;
    width: 100px;
    height: 100px;
    text-align: center;
}

.user-msg-avatar-img {
    position: absolute;
    margin-top: 50px;
    display: block;
    width: 100px;
    height: 100px;
    border-radius: 60px;
}

.user-msg-name {
    position: absolute;
    width: 100px;
    margin-top: 160px;

}



.songs-box {
    margin: 0 auto;
    width: 1200px;
    height: 200px;
}

.songs-nav-box {
    margin-bottom: 60px;
}

.nav-ul {
    list-style-type: none;
    padding: 0;
}

.nav-li {
    display: inline-block;
    margin: 0 auto;
    margin-right: 50px;
}

.nav-btn {
    font-size: 16px;
}

.active {
    color: rgb(240, 99, 18);
}
</style>