<template>
    <div class="userMusic-box">
        <!-- 背景图与头像 -->
        <div class="user-msg-box">
            <div class="user-msg-body">
                <img :src="user.avatarUrl" alt="加载失败" class="user-msg-avatar-img">
                <div class="user-msg-name">{{ user.name }}</div>
            </div>
        </div>
        <!-- 歌曲与歌单 -->
        <div class="songs-box">
            <!-- 导航栏 -->
            <div class="songs-nav-box">
                <ul class="nav-ul">
                    <li v-for="item in navList" :key="item.id" class="nav-li">
                        <TextBtn :value="item.id" @click.native="nav_btn" class="nav-btn">
                            {{ item.name }}
                        </TextBtn>
                    </li>
                </ul>

            </div>
            <!-- 歌曲/歌单 的table -->
            <RouterView class="song-table-box"></RouterView>
        </div>

    </div>
</template>

<script>
import TextBtn from '@/components/publish/TextBtn.vue';
export default {
    name: "UserMusic",
    components: {
        TextBtn,
    },
    data() {
        return {
            user: {},
            navList: [],
            currentNav: "",
        };
    },
    methods: {
        nav_btn(e) {
            //被点击的导航标签变色
            this.currentNav = e.target;

            console.log(e.target.parentNode);

            //导航到对应页面
            if (e.target.value == 1) {
                //歌曲页面
                this.$router.push({
                    name: "userSong"
                })
            } else {
                //歌单页面
                this.$router.push({
                    name: 'userSongList'
                })

            }
        },
    },
    watch: {
        //导航标签变色
        currentNav(t, ot) {
            if (ot !== "") {
                ot.style.color = "black";
            }
            t.style.color = "rgb(240, 99, 18)";
        }
    },
    created() {
        this.user = {
            name: "疯原万叶",
            avatarUrl: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
        };
        this.navList = [
            {
                id: 1,
                name: "歌曲"
            },
            {
                id: 2,
                name: "歌单"
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
</style>