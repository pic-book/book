<template>
  <body id="poster">
  <el-form class="login-container" label-position="left"
           label-width="0px">
    <h3 class="login_title">系统登录</h3>
    <el-form-item prop="account">
      <el-input type="text" v-model="loginForm.username"
                auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item prop="checkPass">
      <el-input type="password" v-model="loginForm.password"
                auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 100%;background: #505458;border: none" v-on:click="login">登录</el-button>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 100%;background: #505458;border: none" v-on:click="qqLoginClick('qq')">QQ第三方登录</el-button>
    </el-form-item>
  </el-form>
  </body>
</template>

<script>
  export default {
    name: 'Login',
    data () {
      return {
        rules: {},
        checked: true,
        loginForm: {
          username: 'admin',
          password: '123456'
        },
        loading: false,
        responseResult: []
      }
    },
    methods: {
      login () {
        var _this = this
        console.log(this.$store.state)
        this.$axios
          .post('/login', {
            username: this.loginForm.username,
            password: this.loginForm.password
          })
          .then(successResponse => {
            if (successResponse.data.code === 200) {
              // var data = this.loginForm
              _this.$store.commit('login', _this.loginForm)
              var path = this.$route.query.redirect
              this.$router.replace({path: path === '/' || path === undefined ? '/index' : path})
            }
          })
          .catch(failResponse => {
          })
      },
      // QQ 第三方登录
      qqLoginClick(value) {
        // 直接弹出授权页面，授权过后跳转到回调页面进行登录处理
        QC.Login.showPopup({
          appId:"101810520",
          redirectURI:"http://localhost/index"  //登录成功后会自动跳往该地址
        });
      }
    }
  }
</script>
<style>
  #poster {
    background:url("../assets/denglubg.png") no-repeat;
    height: 100%;
    width: 100%;
    background-size: auto;
    position: fixed;
  }
  body{
    margin: 0px;
  }
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 90px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }

</style>
