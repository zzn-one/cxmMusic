<template>
    <div class="homeDiv">
        <!-- 网站顶部导航 -->
        <HeaderNav class="navDiv"></HeaderNav>
        <hr class="sp_hr">
        <!-- 网站主要内容 -->
        <RouterView class="bodyDiv"></RouterView>
    </div>
</template>
  
<script>
import HeaderNav from '@/components/home/header/HeaderNav.vue';
export default {
    name: "Home",
    components: {
        HeaderNav,
    }
}
</script>
  
  
<style lang="less">
.homeDiv {
    font-size: 16px;
}

.navDiv {
    width: 1200px;
    min-width: 1200px;
    margin: 0 auto;
}

.bodyDiv {
    background: #ffffff;
    /* fallback for old browsers */
    background: -webkit-linear-gradient(to bottom, #f5f4f5, #ffffff);
    /* Chrome 10-25, Safari 5.1-6 */
    background: linear-gradient(to bottom, #f5f4f5, #ffffff);
    /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
    min-width: 1200px;
}
.sp_hr{
    border: #f5f4f5 1px solid;
    width: 1200px;
}
</style>