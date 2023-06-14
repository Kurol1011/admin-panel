<template>
  <div class="container__table">
    <table-info-users
    :users="allUsers"
    class="table__users"
    />
  </div>
  <router-link to="/register">Register new user</router-link>
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
        .catch(error => console.log(error))
  }


}
</script>

<style scoped>
.container__table{
  width: 100%;
  margin-top: 50px;
}


</style>