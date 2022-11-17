const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  server: {
    port: 3001,
  },
  outputDir: "../src/main/resources/static",
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:80081',
        changeOrigin: true
      }
    }
  }
})
