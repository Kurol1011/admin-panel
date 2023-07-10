<template>

<LeftBlockDisplay></LeftBlockDisplay>

<div class="registration-form">
  <h1 class="part__register">Register</h1>
    <form @submit.prevent="sendRegisterRequest()" class="register__form__container">
      <div class="form-group">
        <label for="firstName">First name:</label>
        <input type="text" id="firstName" v-model="userRegister.firstName" required>
        <div v-for="error in v$.userRegister.firstName.$errors" :key="error.$uid" class="register__error__message">{{error.$message}}</div>
      </div>
      <div class="form-group">
        <label for="lastName">Last name:</label>
        <input type="text" id="lastName" v-model="userRegister.lastName" required>
        <div v-for="error in v$.userRegister.lastName.$errors" :key="error.$uid" class="register__error__message">{{error.$message}}</div>
      </div>
      <div class="form-group">
        <label for="dateOfBirth">Date of Birth:</label>
        <input type="date" id="dateOfBirth" v-model="userRegister.dateOfBirth" required>
        <div v-for="error in v$.userRegister.dateOfBirth.$errors" :key="error.$uid" class="register__error__message">{{error.$message}}</div>
      </div>
      <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" v-model="userRegister.email" required>
        <div v-for="error in v$.userRegister.email.$errors" :key="error.$uid" class="register__error__message">{{error.$message}}</div>
      </div>
      <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="userRegister.password" required>
        <div v-for="error in v$.userRegister.password.$errors" :key="error.$uid" class="register__error__message">{{error.$message}}</div>
      </div>
      <div class="form-group">
        <label for="role">Role:</label>
        <select id="role" v-model="userRegister.role" required>
          <option
              v-for="item in items"
              :value="item"
              :text="item"
          ></option>
        </select>
        <span class="role-label">Select role</span>
      </div>
      <button type="submit" class="submit-button">Registration</button>
    </form>
  </div>   
</template>

<script>
import LeftBlockDisplay from '@/components/LeftBlockDisplay.vue';
import useVuelidate from '@vuelidate/core'
import { required, email,maxLength,minLength,helpers} from '@vuelidate/validators'
import axios from "axios";

//const nameSurnameFormat = helpers.regex('nameSurnameFormat', /\b[A-Z][a-z]+\b/);

export default {
  components:{
    LeftBlockDisplay
  },
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


.part__register{
  color: white;
  font-size: 46px;
  font-weight: 500;
  text-align: center;
  margin-bottom: 10px;
  margin-top: 10px;
}

.registration-form {
  width: 80%;
  float: right;
}
.register__form__container{
  width:500px;
  background-color: rgb(173, 200, 254);
  margin: 0 auto;
  padding: 15px 25px;
  border-radius: 5px;
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


.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  font-weight: bold;
  margin-bottom: 5px;
}

.form-group input[type="text"],
.form-group input[type="email"],
.form-group input[type="password"],
.form-group select {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

.form-group select {
  height: 40px;
}

.form-group .role-label {
  display: block;
  margin-top: 10px;
  font-size: 14px;
}

.submit-button {
  background-color: #4CAF50;
  color: #fff;
  border: none;
  padding: 10px 20px;
  border-radius: 4px;
  cursor: pointer;
}
</style>