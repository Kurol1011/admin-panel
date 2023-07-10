<template>
  
  <!-- <div class="container__app"> -->
    <div class="main__container">
      <router-view></router-view>
    </div>
    
    <!-- <br>
    <router-link to="/admin">go to admin page</router-link>
    <br>
    <router-link to="/respondent">Go to respondent page</router-link>
    <br>
    <button type="submit" @click="logout()">Logout</button> -->
  <!-- </div> -->
</template>

<script>
import axios from "axios";
import router from "@/router/router";
export default {
  methods:{
    logout(){
      localStorage.removeItem('token');
      this.$store.commit("auth_data/setUser",null);
      router.push('/');
    }
  }
}
</script>

<style scoped>

*{
  margin: 0;
  padding: 0;
  outline: none;
  box-sizing: border-box;
  font-family: 'Poppins', sans-serif;
}
body{
  height: 100vh;
  background: linear-gradient(115deg, #56d8e4 10%, #9f01ea 90%);
}
</style>