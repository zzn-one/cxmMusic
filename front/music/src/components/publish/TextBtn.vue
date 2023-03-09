<template>
    <button class="btn" @mousemove="hoverColor" @mouseout="restoreColor">
        <slot></slot>
    </button>
</template>
<script>
export default{
    name:"TextBtn",
    methods:{
        // 鼠标悬浮变色
        hoverColor(e){
            e.target.style.color='#f06312'
        },
        //鼠标移开恢复颜色
        restoreColor(e){
            e.target.style.color='black'
        }
    }
}
</script>
<style scoped>
.btn {
    color: black;
    border: none;
    background-color: rgba(255, 255, 255,0);
}

</style>