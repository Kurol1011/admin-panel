<template>
<div class="page-container">
  <div class="container">
    <div>

      <h1>{{message}}</h1>
      
    </div>
  
    <div class="container__login">
        <h2>Авторизация</h2>
      <form @submit.prevent="sendLoginRequest()" class="login__form__container" autocomplete="on">
        <label for="email" class="login__label">Email:</label>
        <input
            id="email"
            autocomplete="email"
            type="text"
            v-model.trim="User.email"
            class="login__input">
        <div v-for="error in v$.User.email.$errors" :key="error.$uid" class="login__error__message">{{error.$message}}</div>
        <label for="password" class="login__label">Password:</label>
        <input
            id="password"
            autocomplete="password"
            type="password"
            v-model.trim="User.password"
            class="login__input">

        <button class="btn__auth">Войти</button>
      </form>
    </div>
  </div>
  </div>
</template>

<script>
import useVuelidate from '@vuelidate/core'
import { required, email,maxLength,minLength,helpers} from '@vuelidate/validators'
import axios from "axios";
import router from "@/router/router";
export default {
  setup () {
    return { v$: useVuelidate() }
  },
  data(){
    return{
      message:null,
      LoginURL:'http://localhost:8081/api/auth/login',
      UserInfoURL:'http://localhost:8081/info',
      User:{
        email:'',
        password:''
      },
      isInvalid:true
    };
  },
  methods:{
    getHello() {
      return axios.get('http://localhost:8081/hello',{
        headers: {
          'Authorization': `Bearer ${localStorage.getItem('token')}`,
        }
      })
          .then(response => this.message = response.data)
          .catch(error => console.log(error))
    },
    async sendLoginRequest(){
      const isFormCorrect = await this.v$.$validate()
      if (!isFormCorrect) return

      this.isInvalid = true;
      const login = await axios.post(this.LoginURL, this.User, {
        headers: {
          'Content-Type': 'application/json'
        }
      })
          .then(response => {
            localStorage.setItem('token', response.data.token);
            console.log("success")
            //this.$store.commit('auth_data/setJwtToken',response.data.token);
            //this.$store.state.auth_data.authHeaders
            //this.$store.commit('auth_data/setUser',);
            this.getUserData();
          })
          .catch(error => {
            console.log(error);
          })
    },

    async getUserData(){
      const userInfo = await axios.get(this.UserInfoURL,{
        headers:{
          'Authorization': `Bearer ${localStorage.getItem('token')}`,
        }
      })
          .then(response => {
            console.log(response.data);
            console.log(response.data.role)
            this.$store.commit("auth_data/setUser",response.data);
            console.log(this.$store.state.auth_data.user);
            let userRole = this.$store.state.auth_data.user.role;
            if(userRole === "ADMIN"){
              router.push('/admin');
            }
            else if(userRole === "USER"){
              router.push('/user')
            }
            else if(userRole === "DEV"){
              router.push('/dev')
            }
            else if(userRole === "MANAGER"){
              router.push('/manager')
            }
          })
          .catch(error =>{
            console.log(error.response.data);
            alert(error.response.data.message);
          })
    }
  },
  computed:{
    myGetter() {
      return this.$store.getters['myModule/getData'];
    }
  },
  validations(){
    return {
      User:{
        email:{
          required:helpers.withMessage("Please enter your email",required),
          email:helpers.withMessage("Your email should be valid",email)
        }
        ,
        password:{

          minLength:helpers.withMessage("Your password must be more than 4 characters",minLength(4)),
          maxLength:helpers.withMessage("Your password must be less than 128 characters",maxLength(128))

        }
      }
    }
  }
  ,

}
</script>

<style scoped>
.page-container {
  position: relative;
  background-image: linear-gradient(to bottom, #2980b9, #6dd5fa);
  background-repeat: no-repeat;
  background-size: cover;
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

h2 {
  text-align: center;
  margin-right: 40px;
  margin-bottom: 20px;
}

.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.container__login {
  width: 300px;
  margin: 0 auto;
  padding: 20px;
  padding-left: 60px;
  background-color: #f7f7f7;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.login__form__container{
  width:80%
}

label {
  display: block;
  font-weight: bold;
  margin-bottom: 5px;
}

.login__label{
  margin-bottom: 15px;
}

.login__input{
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 3px;
}

.login__error__message{
  color:red;
  font-size: 14px;
  font-family: 'Ubuntu', sans-serif;
  font-weight:normal;
  margin: 0 auto;
  display: block;
  text-align: center;
}

.btn__auth {
  margin: 10px auto 0;
  padding: 10px;
  background-color: #4caf50;
  color: #fff;
  border: none;
  border-radius: 3px;
  cursor: pointer;
  transition: background-color .5s ease;
}

.btn__auth:hover {
  background-color: #FFA500;
}

</style>