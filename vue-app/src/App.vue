<template>
  <div class="container">
    <div>
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
        <div v-for="error in v$.User.password.$errors" :key="error.$uid" class="login__error__message"> {{error.$message}}</div>
        <button class="btn__auth">Login</button>
      </form>
    </div>


  </div>
</template>

<script>
import useVuelidate from '@vuelidate/core'
import { required, email,maxLength,minLength,helpers} from '@vuelidate/validators'
import axios from "axios";
export default {
  setup () {
    return { v$: useVuelidate() }
  },
  data(){
    return{
      message:null,
      LoginURL:'http://localhost:8081/api/auth/login',
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
         //this.$store.state.auth_data.authHeaders
         'Authorization': `Bearer ${localStorage.getItem('token')}`,
       }
     })
          .then(response => this.message = response.data)
          //.then(response => console.log(response.data))
          .catch(error => console.log(error))
    },
    async sendLoginRequest(){
      const isFormCorrect = await this.v$.$validate()
      // you can show some extra alert to the user or just leave the each field to show it's `$errors`.
      if (!isFormCorrect) return
      // actually submit form

      this.isInvalid = true;
      const login = axios.post(this.LoginURL, this.User, {
        //config.headers['X-CSRF-Token'] = csrfToken;
        headers: {
          //this.$store.state.auth_data.authHeaders
          'Content-Type': 'application/json'
        }
      })
          //.then(response => { this.$store.commit('auth_data/setJwtToken',response.data.token);})
          .then(response => {
            localStorage.setItem('token', response.data.token);
          })
          .catch(error => {
            console.log(error);
          })
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
          required,
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
*,body{
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}


</style>