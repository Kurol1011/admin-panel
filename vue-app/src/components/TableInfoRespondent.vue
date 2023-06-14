<template>
  <div>
    <table class="table__respondents">

      <tr>
        <th>Full name</th>
        <th>Number of computers</th>
        <th>Number of employees</th>
        <th>Date</th>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="respondent.fullName">
        </td>
        <td>
          <input type="number" v-model="respondent.amountComputers">
        </td>
        <td>
          <input type="number" v-model="respondent.amountEmployee">
        </td>
        <td>
          <input type="date" v-model="respondent.date">
        </td>
        <td>
          <button type="submit" @click="createRespondentPost()">Save</button>
        </td>
      </tr>
      <tr v-for="respondentItem in respondents" :key="respondentItem.id">
        <td>
          <input type="text" v-model="respondentItem.fullName">
        </td>
        <td>
          <input type="number" v-model="respondentItem.amountComputers">
        </td>
        <td>
          <input type="number" v-model="respondentItem.amountEmployee">
        </td>
        <td>
          <input type="date" v-model="respondentItem.date">
        </td>
        <td>
          <button type="submit" @click="submitForm(respondentItem)">Update</button>
        </td>
      </tr>

    </table>
  </div>
</template>

<script>
import axios from "axios";
export default {
  components: {},
  props:{
    respondents:{
      type:Array,
      required:true
    }
  },
  data(){
    return{

      apiUrlCreate:'http://localhost:8081/create-respondent-post',
      respondent:{
        id:null,
        fullName:'',
        amountComputers:0,
        amountEmployee:0,
        date:null
      },
    }
  }
  ,
  methods: {
    submitForm(respondent) {
      axios.post(this.apiUrl, respondent,{
        headers: {
          //this.$store.state.auth_data.authHeaders
          'Authorization': `Bearer ${localStorage.getItem('token')}`,
          'Content-Type': 'application/json'
        }
      })
          .then(response =>{
            location.reload();
            console.log(response.data);
          })
          .catch(error => {
            console.log(error);
          });
    },
    createRespondentPost(){
      console.log(this.respondent);
      axios.post(this.apiUrlCreate, this.respondent,{
        headers: {
          //this.$store.state.auth_data.authHeaders
          'Authorization': `Bearer ${localStorage.getItem('token')}`,
          'Content-Type': 'application/json'
        }
      })
          .then(response =>{
            location.reload();
            console.log(response.data);
          })
          .catch(error => {
            console.log(error);
          });
    }
  }
  ,
  created() {
    axios.get("")
  }

}
</script>

<style scoped>
.table__respondents{
  border: 2px solid black ;
  width: 800px;
  margin: 0 auto;
}
td{
  border: 1px solid silver;
}
input {
  width: 100%;
  box-sizing: border-box;
}
</style>