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
  width: 80%;
  margin: 0 auto;
  padding: 10px;
}

.container__admin__page__title{
  text-align: center;
}

</style>