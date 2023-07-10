<template>
  <div class="regmain">
    <h1 class="part__register">Regsitration</h1>
    <div class="container__register">
      <form @submit.prevent="sendRegisterRequest()" class="register__form__container">
        <label for="firstName" class="register__label">First name:</label>
        <input type="text" class="register__input" v-model="userRegister.firstName"/>
        <div v-for="error in v$.userRegister.firstName.$errors" :key="error.$uid" class="register__error__message">{{error.$message}}</div>

        <label for="lastName" class="register__label">Last name:</label>
        <input type="text" class="register__input" v-model="userRegister.lastName">
        <div v-for="error in v$.userRegister.lastName.$errors" :key="error.$uid" class="register__error__message">{{error.$message}}</div>

        <label for="lastName" class="register__label2">Date Of birth:</label>
        <input type="date" class="register__input" v-model="userRegister.dateOfBirth">
        <div v-for="error in v$.userRegister.dateOfBirth.$errors" :key="error.$uid" class="register__error__message">{{error.$message}}</div>

        <label for="email" class="register__label3">Email:</label>
        <input type="text" class="register__input" v-model="userRegister.email">
        <div v-for="error in v$.userRegister.email.$errors" :key="error.$uid" class="register__error__message">{{error.$message}}</div>

        <label for="password" class="register__label4">Password:</label>
        <input type="password" class="register__input" v-model="userRegister.password">
        <div v-for="error in v$.userRegister.password.$errors" :key="error.$uid" class="register__error__message">{{error.$message}}</div>


        <label for="password" class="register__label5">Role:</label>
        <select class="register__input" v-model="userRegister.role">
          <option
              v-for="item in items"
              :value="item"
              :text="item"
          ></option>
        </select>

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
    return {
      v$: useVuelidate(),
      items: ['USER','ADMIN','DEVELOPER','MANAGER'],
    }
  },
  data() {
    return {
      registerApiUrl: 'http://localhost:8081/create-user',
      message:'',
      userRegister:{
        firstName:'',
        lastName:'',
        dateOfBirth:'',
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
        dateOfBirth:{
          required:helpers.withMessage("Please date fill date of birth",required),
          //date: helpers.withMessage('Invalid date format', date),
          //dateOfBirthFormat:helpers.withMessage("The date should be on next format ",helpers.regex(/\b[A-Z][a-z]+\b/)),
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
            alert(error.response.data.message);
            console.log(error.response.data);
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
/* .auth__status__block{
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
} */
/* 
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
} */
.regmain{
  background: linear-gradient(90deg, #ee5c87, #f1a4b5, #d587b3);
  background-repeat: no-repeat;
  background-size: cover;
  min-height: 100vh;
}
.part__register{
  color: #fff;
  text-align: center;
  margin: 10px 0;
  font-size: 29px;
  font-family: 'Ubuntu', sans-serif;
  font-weight: bold;
  
}

.container__register{
  width: 20%;
  

  padding:20px;
  margin:10px auto;

  border: 2px solid #0e0b54;
  border-radius: 20px; 
  box-shadow: 2px 2px 7px black;

  flex-direction: column;
  align-items: center;
}

.register__label{
  color: #fff;
  display: inline-block;
  
  margin: 10px;
  margin-right: 32px;
  
  font-size: 20px;
  font-family: 'Ubuntu', sans-serif;
  font-weight: bold;
}
.register__label2{
  display: inline-block;
  color: #fff;
 

  margin: 10px;
  
  font-size: 20px;
  font-family: 'Ubuntu', sans-serif;
  font-weight: bold;
}
.register__label3{
  display: inline-block;
  color: #fff;
 

  margin: 10px;
  margin-right: 77px;
  
  font-size: 20px;
  font-family: 'Ubuntu', sans-serif;
  font-weight: bold;
}
.register__label4{
  display: inline-block;
  color: #fff;

  margin: 10px;
  margin-right: 35px;
  
  font-size: 20px;
  font-family: 'Ubuntu', sans-serif;
  font-weight: bold;
}
.register__label5{
  display: inline-block;
  color: #fff;
 

  margin: 10px;
  margin-right: 85px;
  
  font-size: 20px;
  font-family: 'Ubuntu', sans-serif;
  font-weight: bold;
}

.register__input{
  display: inline-block;
 

  border: 2px solid #565454;
  border-radius: 20px;
  padding:5px 10px;
  line-height: 25px;
  width:130px;

  font-size: 14px;
  font-family: 'Ubuntu', sans-serif;
  font-weight:700;
  color: #0e0b54;
   background: transparent;
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
  background-color: rgb(141, 247, 127);
  margin: 10px auto;
  margin-right: 40px;
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