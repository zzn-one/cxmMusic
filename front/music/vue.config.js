const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    // 修改 dev 期间的端口号
    port: 3000,
    // proxy:"http://localhost:10000"
  }
})
