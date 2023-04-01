<template>
  <div id="app">
    <!-- 播放页 或者 主页  或者 注册页面-->
    <RouterView></RouterView>
  </div>
</template>

<script>

export default {
  name: "App",
}
</script>


<style lang="less">
a {
  text-decoration: none;
  color: black;
}
a:hover{
  color: #f06312;
}
body{
  margin: 0;
  padding: 0;
  border: 0;

}

</style>
