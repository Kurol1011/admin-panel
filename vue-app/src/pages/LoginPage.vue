<template>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
 
    <div class="container">
            
            <div class="text">
               Login Form
            </div>
            <form @submit.prevent="sendLoginRequest()" autocomplete="on">
               <div class="data">
                  <label for="email">Email</label>
                  <input id="email"
            autocomplete="email"
            type="text"
            v-model.trim="User.email">
            <div v-for="error in v$.User.email.$errors" :key="error.$uid" class="login__error__message">{{error.$message}}</div>
               </div>
               <div class="data">
                  <label for="password">Password</label>
                  <input id="password"
            autocomplete="password"
            type="password"
            v-model.trim="User.password">
               </div>
               <div class="btn">
                  <div class="inner"></div>
                  <button type="submit">login</button>
               </div>
            </form>
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
            else if(userRole === "DEVELOPER"){
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

<style>
@import url('https://fonts.googleapis.com/css?family=Poppins:400,500,600,700&display=swap');


*{
  margin: 0;
  padding: 0;
  outline: none;
  box-sizing: border-box;
  font-family: 'Poppins', sans-serif;
}
body{
  height: 100vh;
  width: 100%;
  background: linear-gradient(115deg, #56d8e4 10%, #9f01ea 90%);
}

.show-btn{
  background: #fff;
  padding: 10px 20px;
  font-size: 20px;
  font-weight: 500;
  color: #3498db;
  cursor: pointer;
  box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
}
.show-btn, .container{
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

input[type="checkbox"]{
  display: none;
}
.container{
  display: block;
  background: #fff;
  width: 410px;
  padding: 30px;
  box-shadow: 0 0 8px rgba(0,0,0,0.1);
}

.container .close-btn{
  position: absolute;
  right: 20px;
  top: 15px;
  font-size: 18px;
  cursor: pointer;
}
.container .close-btn:hover{
  color: #3498db;
}
.container .text{
  font-size: 35px;
  font-weight: 600;
  text-align: center;
}
.container form{
  margin-top: -20px;
}
.container form .data{
  height: 45px;
  width: 100%;
  margin: 40px 0;
}
form .data label{
  font-size: 18px;
}
form .data input{
  height: 100%;
  width: 100%;
  padding-left: 10px;
  font-size: 17px;
  border: 1px solid silver;
}
form .data input:focus{
  border-color: #3498db;
  border-bottom-width: 2px;
}
form .forgot-pass{
  margin-top: -8px;
}
form .forgot-pass a{
  color: #3498db;
  text-decoration: none;
}
form .forgot-pass a:hover{
  text-decoration: underline;
}
form .btn{
  margin: 30px 0;
  height: 45px;
  width: 100%;
  position: relative;
  overflow: hidden;
}
form .btn .inner{
  height: 100%;
  width: 300%;
  position: absolute;
  left: -100%;
  z-index: -1;
  background: -webkit-linear-gradient(right, #56d8e4, #9f01ea, #56d8e4, #9f01ea);
  transition: all 0.4s;
}
form .btn:hover .inner{
  left: 0;
}
form .btn button{
  height: 100%;
  width: 100%;
  background: none;
  border: none;
  color: #fff;
  font-size: 18px;
  font-weight: 500;
  text-transform: uppercase;
  letter-spacing: 1px;
  cursor: pointer;
}
form .signup-link{
  text-align: center;
}
form .signup-link a{
  color: #3498db;
  text-decoration: none;
}
form .signup-link a:hover{
  text-decoration: underline;
}


</style>