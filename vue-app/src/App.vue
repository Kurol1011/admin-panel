<template>
  <div class="container">
    <router-view> </router-view>
    <div class="menu">
      <ul>
        <li><router-link to="/admin">Go to admin page</router-link></li>
        <li><router-link to="/respondent">Go to respondent page</router-link></li>
      </ul>
      <button type="submit" @click="logout()">Logout</button>
    </div>
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

<style >
*,body {
  margin: 0;
  padding: 0;
  overflow:hidden;
  overflow-y:hidden;  
  overflow-x:hidden;
}
.containter{
  position: relative;
  
} 

.menu {
  position: absolute;
  top: 0;
  left: 0;
  width: 250px;
  min-height: 100vh;
  padding: 20px;
  background: linear-gradient(135deg, #e55d87 0%, #5fc3e4 100%);
  color: #fff;
}

.menu ul {
  
  padding-top: 20px;
  padding-left: 20px;
  margin: 0;
  list-style: none;
 
}

.menu li {
  padding-bottom: 5px;
  margin-bottom: 5px;
  color: #4caf50;
}

.menu li:last-child {
  border-bottom: none;
  margin-bottom: 0;
}

.menu a {
  display: block;
  color: #fff;
  text-decoration: none;
  text-transform: uppercase;
}
.menu a:hover {
  color: rgb(255, 255, 255);
   background: #ddd5ff;
  transition:  .5s ease;;
}

.menu router-link {
  display: block;
  color: #fff;
  text-decoration: none;
}

button {
  margin: 10px auto 0;
  padding: 10px;
  margin-left: 20px;
  background-color: #FFA500;
  color: #fff;
  border: none;
  border-radius: 3px;
  cursor: pointer;
  transition: .5s ease;
  width: 50%;
}

button:hover {
  background-color: #4caf50;
  
}
</style>