<template>
  <div>
    <h1 class="part__register">Register</h1>
    <div class="container__register">
      <form @submit.prevent="sendRegisterRequest()" class="register__form__container">
        <label for="firstName" class="register__label">Name:</label>
        <input type="text" class="register__input" v-model="userRegister.firstName"/>
        <div v-for="error in v$.userRegister.firstName.$errors" :key="error.$uid" class="register__error__message">{{error.$message}}</div>

        <label for="lastName" class="register__label">Surname:</label>
        <input type="text" class="register__input" v-model="userRegister.lastName">
        <div v-for="error in v$.userRegister.lastName.$errors" :key="error.$uid" class="register__error__message">{{error.$message}}</div>

        <label for="email" class="register__label">Email:</label>
        <input type="text" class="register__input" v-model="userRegister.email">
        <div v-for="error in v$.userRegister.email.$errors" :key="error.$uid" class="register__error__message">{{error.$message}}</div>

        <label for="password" class="register__label">Password:</label>
        <input type="password" class="register__input" v-model="userRegister.password">
        <div v-for="error in v$.userRegister.password.$errors" :key="error.$uid" class="register__error__message">{{error.$message}}</div>

        <label for="password" class="register__label">Role:</label>
        <input type="text" class="register__input" v-model="userRegister.role">

        <button class="btn__auth" >Register</button>
      </form>
    </div>


  </div>
</template>

<script>
import useVuelidate from '@vuelidate/core'
import { required, email,maxLength,minLength,helpers} from '@vuelidate/validators'
import axios from "axios";

//const nameSurnameFormat = helpers.regex('nameSurnameFormat', /\b[A-Z][a-z]+\b/);

export default {
  setup () {
    return { v$: useVuelidate() }
  },
  data() {
    return {
      registerApiUrl: 'http://localhost:8081/create-user',
      message:'',
      userRegister:{
        firstName:'',
        lastName:'',
        email:'',
        password:'',
        role:''
      },
      hasAuthSuccess:false,
      hasAuthError: false,
    };
  },
  validations(){
    return {
      userRegister:{
        firstName:{
          required:helpers.withMessage("Please enter your name",required),
          firstNameLastNameFormat:helpers.withMessage("The name must contain only letters and must start with a capital letter ",helpers.regex(/\b[A-Z][a-z]+\b/)),
        },
        lastName:{
          required:helpers.withMessage("Please enter your surname",required),
          firstNameLastNameFormat:helpers.withMessage("The surname must contain only letters and must start with a capital letter ",helpers.regex(/\b[A-Z][a-z]+\b/)),
        },
        email:{
          required:helpers.withMessage("Please enter your email",required),
          email:helpers.withMessage("Your email should be valid",email)
        }
        ,
        password:{
          required,
          minLength:helpers.withMessage("Your password must be more than 5 characters",minLength(5)),
          maxLength:helpers.withMessage("Your password must be less than 128 characters",maxLength(128))

        }
      }
    }
  },
  methods: {
    async sendRegisterRequest(){

      const isFormCorrect = await this.v$.$validate()
      // you can show some extra alert to the user or just leave the each field to show it's `$errors`.
      if (!isFormCorrect) return
      // actually submit form

      axios.post(this.registerApiUrl, this.userRegister,{
        headers: {
          //this.$store.state.auth_data.authHeaders
          'Authorization': `Bearer ${localStorage.getItem('token')}`,
          'Content-Type': 'application/json'
        }
      })
          .then(response =>{
            //this.$store.commit('auth_data/setJwtToken',response.data.token);
            console.log("user created")
            this.$router.push('/admin');
            this.hasAuthSuccess = true;
          })
          .catch(error => {
            console.log(error);
            this.hasAuthError = true;
          });
    },
  },
};
</script>

<style>
.register__form__container{
  width:80%;
}
.auth__status__block{
  width:200px;
  height:70px;
  position: absolute;
  right:0;
  padding: 10px;
  font-size: 17px;
  font-family: 'Ubuntu', sans-serif;
  font-weight: bold;
  background-color: #a7d9b6;
  box-shadow: 3px 3px 3px black;
}
.auth__status__block{
  width:200px;
  height:70px;
  position: absolute;
  right:0;
  padding: 10px;
  color:#4fb416;
  font-size: 17px;
  font-family: 'Ubuntu', sans-serif;
  font-weight: bold;
  background-color: #a7d9b6;
  box-shadow: 3px 3px 3px black;
}

.slide-fade-enter-active {
  transition: all 0.3s ease-out;
}

.slide-fade-leave-active {
  transition: all 0.8s cubic-bezier(1, 0.5, 0.8, 1);
}

.slide-fade-enter-from,
.slide-fade-leave-to {
  transform: translateX(20px);
  opacity: 0;
}

.part__register{
  text-align: center;
  margin: 10px 0;
  font-size: 29px;
  font-family: 'Ubuntu', sans-serif;
  font-weight: bold;
}

.container__register{
  display: flex;
  width: 90%;
  padding:20px;
  border: 2px solid #0e0b54;
  border-radius: 20px;
  margin:10px auto;
  display: flex;
  box-shadow: 2px 2px 7px black;
  flex-direction: column;
  align-items: center;
}

.register__label{
  display: inline-block;
  margin:15px 0 5px 30%;
  font-size: 20px;
  font-family: 'Ubuntu', sans-serif;
  font-weight: bold;
}

.register__input{
  display: block;
  border: 2px solid #565454;
  border-radius: 20px;
  padding:5px 10px;
  width:250px;
  margin:10px auto 0;
  font-size: 14px;
  font-family: 'Ubuntu', sans-serif;
  font-weight:700;
  color: #0e0b54;
}

.register__error__message{
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