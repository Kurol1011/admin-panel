<template>
  <left-block-display></left-block-display>
  <div class="container__admin__page">
    <h1 class="container__admin__page__title">Admin page</h1>
  <div class="container__table">
    <table-info-users
    :users="allUsers"
    class="table__users"
    />
  </div>
  <router-link to="/register" class="btn__create__user">Register new user</router-link>
  </div>
</template>

<script>
import axios from "axios";
import tableInfoUsers from "@/components/TableInfoUsers";
import LeftBlockDisplay from "@/components/LeftBlockDisplay.vue";
export default {
  components: {tableInfoUsers, LeftBlockDisplay},
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

<style>

.container__table{
  width: 100%;
  margin-top: 30px;
}

.container__admin__page{
  width: 80%;
  margin: 0 auto;
  padding: 10px;
  float: right;
}

.container__admin__page__title{
  color: white;
  font-size: 46px;
  font-weight: 500;
  text-align: center;
}

.btn__create__user{
  width: 200px;;
  display: block;
  padding: 10px;
  border: 5px solid white;
  color: white;
  font-size: 16px;
  font-weight: 500;
  text-align: center;
  text-decoration: none;
  margin: 20px auto;
}

.btn__create__user:hover{
 background-color: white;
  color: #9f01ea;
}

</style>