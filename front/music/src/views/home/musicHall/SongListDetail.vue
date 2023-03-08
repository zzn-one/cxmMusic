<template>
    <div>
        歌单详情页{{ id }}
    </div>
</template>

<script>

export default {
    name: 'SongListDetail',
    data() {
        return {
            id: '',
        }
    },
    created() {
        //获取歌单id
        this.id = this.$route.params.id
    }
}
</script>