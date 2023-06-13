<template>
  <div class="container">
    <div>
<!--      <h1>{{$store.getters['auth_data/isAuthenticated']}}</h1>-->
      <h1>{{message}}</h1>
    </div>
    <button @click="getHello">Press</button>
    <div class="container__login">
      <form @submit.prevent="sendLoginRequest()" class="login__form__container">
        <label for="email" class="login__label">Email:</label>
        <input
            type="text"
            v-model.trim="User.email"
            class="login__input">
        <div v-for="error in v$.User.email.$errors" :key="error.$uid" class="login__error__message">{{error.$message}}</div>
        <label for="password" class="login__label">Password:</label>
        <input
            type="password"
            v-model.trim="User.password"
            class="login__input">
<!--        <div v-for="error in v$.User.password.$errors" :key="error.$uid" class="login__error__message"> {{error.$message}}</div>-->
        <button class="btn__auth">Login</button>
      </form>
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
          //.then(response => console.log(response.data))
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
            console.log(error);
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
.part__login{
  text-align: center;
  margin: 10px 0;
  font-size: 29px;
  font-family: 'Ubuntu', sans-serif;
  font-weight: bold;
}

.container__login{
  display: flex;
  width: 30%;
  padding:20px;
  border: 2px solid #0e0b54;
  border-radius: 20px;
  margin:10px auto;
  display: flex;
  box-shadow: 2px 2px 7px black;
  flex-direction: column;
  align-items: center;
}

.login__form__container{
  width:80%
}

.login__label{
  display: block;
  margin:15px 0 5px 30%;
  font-size: 20px;
  font-family: 'Ubuntu', sans-serif;
  font-weight: bold;
}

.login__input{
  display: block;
  border: 2px solid #565454;
  border-radius: 20px;
  padding:5px 10px;
  width:90%;
  margin:3px auto 0;
  font-size: 14px;
  font-family: 'Ubuntu', sans-serif;
  font-weight:700;
  color: #0e0b54;
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


.btn__auth{
  color: #565454;
  padding: 5px 10px;
  background-color: white;
  margin: 10px auto;
  font-size: 17px;
  font-family: 'Ubuntu', sans-serif;
  font-weight: normal;
  box-shadow: 2px 2px 7px black;
  display: block;
}
.btn__auth:hover{
  color: white;
  background-color: #565454;
  cursor: pointer;
}


</style>