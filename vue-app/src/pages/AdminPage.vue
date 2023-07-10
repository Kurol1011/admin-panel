<template>
  <div class="container__admin__page">
    <h1 class="container__admin__page__title">Admin page</h1>
  <div class="container__table">
    <table-info-users
    :users="allUsers"
    class="table__users"
    />
  </div>
  <router-link to="/register">Register new user</router-link>
  </div>
</template>

<script>
import axios from "axios";
import tableInfoUsers from "@/components/TableInfoUsers";
export default {
  components: {tableInfoUsers},
  data(){
    return{
      allUsers:[]
    }
  },
  async created(){
    await axios.get('http://localhost:8081/users',{
      headers: {
        'Authorization': `Bearer ${localStorage.getItem('token')}`,
      }
    })
        .then(response => {
          this.allUsers = response.data;
          console.log(response.data)
        })
        //.then(response => console.log(response.data))
        .catch(error => {
          alert(error.response.data.message);
          console.log(error.response.data);
        })
  }


}
</script>

<style scoped>


.container__table{
  width: 100%;
  margin-top: 50px;
}

.container__admin__page{
  position: relative;
  width: 100%;
  margin: 0 auto;
  padding: 10px;
  background: linear-gradient(81deg, #ddb35f, #7409c7);
  background-repeat: no-repeat;
  background-size: cover;
  min-height: 100vh;
  
}


.container__admin__page a {
  position: absolute;
  display: inline-block;
  top: 550px;
  right: 550px;

  text-decoration: none;


  color: #fff;
  background: transparent;
  text-transform: uppercase;


  padding: 20px;

  border-radius: 10px;
 
}
.container__admin__page a:hover {
   /* color: rgb(8, 198, 231); */
   transition: 0.6s;
   background: #c70968;
}

.container__admin__page__title{
  color: #fff;
  text-align: center;
}

</style>