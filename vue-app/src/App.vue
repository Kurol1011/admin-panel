<template>
  <div class="container">
    <router-view></router-view>
    <br>
    <router-link to="/admin">go to admin page</router-link>
    <br>
    <router-link to="/respondent">Go to respondent page</router-link>
    <br>
    <button type="submit" @click="logout()">Logout</button>
  </div>
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
*,body{
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}


</style>