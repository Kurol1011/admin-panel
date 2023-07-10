<template>

<div class="left__display__block">
    <h1>Admin panel</h1>
    <router-link to="/admin" v-if=" this.$store.state.auth_data.user  && this.$store.state.auth_data.user.role === 'ADMIN'">Admin page</router-link>
    
    <router-link to="/respondent">Respondent page</router-link>
    
    <button type="submit" @click="logout()">Logout</button>
</div>

</template>
  <script>
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
  
  <style>
  .left__display__block{
    float: left;
    width: 20%;
    height: 100vh;
    padding:10px 5px;
    background-color: rgb(54, 110, 205);
  }

  .left__display__block h1{
    font-size: 24px;
    font-weight: bold;
    color: #ffffff;
    text-align: center;
    margin: 15px 0;
  }

  .left__display__block a{
    margin-top:5px;
    padding: 5px 15px;
    text-decoration: none;
    display: block;
    font-size: 17px;
    font-weight: 400;
    border-radius: 5px;
    color: rgb(240, 242, 247);
    background-color: rgb(50, 55, 152);
    border: 1px solid rgb(47, 42, 42);
  }

  .left__display__block a:hover{
    color: black;
    background-color: rgb(186, 188, 239);
    transition: 0.7s all;
  }

  .left__display__block button{
    margin: 10px auto;
    width: 100%;
    background-color: brown;
    color: white;
    border-radius: 5px;
    padding: 10px;
    display: block;
    cursor: pointer;
  }
  </style>