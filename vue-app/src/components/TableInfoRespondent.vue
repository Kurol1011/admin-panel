<template>

<div class="table-container">
    <table>
      <thead>
      <tr>
        <th>Respondent</th>
        <th>Number of computers</th>
        <th>Number of employees</th>
        <th>Date</th>
        <th>update</th>
        <th>delete</th>
      </tr>
    </thead>
    <tr>
        <td>
          <input type="text" v-model="respondent.fullName" readonly disabled>
        </td>
        <td>
          <input type="number" v-model="respondent.amountComputers">
        </td>
        <td>
          <input type="number" v-model="respondent.amountEmployee">
        </td>
        <td>
          <input type="date" v-model="respondent.date" >
        </td>
        <td>
          <button type="submit" @click="createRespondentPost()">Save</button>
        </td>
      </tr>
      <tr v-for="respondentItem in respondents" :key="respondentItem.id">
        <td>
          <input type="text" v-model="respondentItem.fullName" readonly disabled>
        </td>
        <td>
          <input type="number" v-model="respondentItem.amountComputers">
        </td>
        <td>
          <input type="number" v-model="respondentItem.amountEmployee">
        </td>
        <td>
          <input type="date" v-model="respondentItem.date" class="date">
        </td>
        <td>
          <button type="submit" @click="updateRespondentPost(respondentItem)"  > Update </button>
        </td>
        <td>
          <button type="submit" @click="removeRespondentPost(respondentItem)">Delete</button>
        </td>
      </tr>
    </table>
  </div>

 
</template>

<script>
import axios from "axios";
import store from "@/store";
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
      apiURL:'http://localhost:8081',
      apiURLCreate: this.apiURL + '/create-respondent-post',
      apiURLUpdate: this.apiURL + '/update-respondent-post',
      apiURLDelete: this.apiURL + '/remove-respondent-post',
      apiURLInfoUser: this.apiURL + '/info',
      respondent:{
        id:null,
        fullName:'',
        amountComputers:0,
        amountEmployee:0,
        date:null,
        ownerId:null
      },
    }
  }
  ,
  methods: {
    updateRespondentPost(respondent) {
      axios.post(this.apiURLUpdate, respondent,{
        headers: {
          //this.$store.state.auth_data.authHeaders
          'Authorization': `Bearer ${localStorage.getItem('token')}`,
          'Content-Type': 'application/json'
        }
      })
          .then(response =>{
            location.reload();
          })
          .catch(error => {
            location.reload();
            console.log(error.response.data);
            alert(error.response.data.message);
          });
    },
    removeRespondentPost(respondent) {
      axios.post(this.apiURLDelete, respondent,{
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
            location.reload();
            console.log(error.response.data);
            alert(error.response.data.message);
          });
    },
    createRespondentPost(){
      console.log(this.respondent);
      axios.post(this.apiURLCreate, this.respondent,{
        headers: {
          //this.$store.state.auth_data.authHeaders
          'Authorization': `Bearer ${localStorage.getItem('token')}`,
          'Content-Type': 'application/json'
        }
      })
          .then(response =>{
            location.reload();
          })
          .catch(error => {
            location.reload();
            console.log(error.response.data);
            alert(error.response.data.message);
          });
    }
  }
  ,
  beforeCreate() {
    this.apiURL= 'http://localhost:8081';
  }
  ,
 async created() {
    await axios.get(this.apiURLInfoUser,{
      headers: {
        'Authorization': `Bearer ${localStorage.getItem('token')}`,
      }
    }).then(response => {
      this.respondent.fullName = response.data.firstName + ' ' + response.data.lastName;
    })
        .catch(error=>{
          console.log(error);
        })
  }

}
</script>

<style scoped>
.table-container {
  height: 400px;
  overflow-y: scroll;
}


.table-container table {
  width: 80%;
  margin: 0 auto;
  border-collapse: collapse;
  border-top-left-radius: 20px; 
  border-top-right-radius: 20px;
  overflow: hidden; 
}

.table-container th{
  padding: 10px;
  text-align: center;
  height: 20px;
  background-color: #f2f2f2;
  color: #333;
  position: sticky;
  top: 0;
}

.table-container input{
  width: 100%;
  padding: 7px;
  text-align: center;
}
.table-container .date{
  padding:7px;
  border: none;
}
.table-container button{
  width: 100%;
  padding: 7px 10px;
  border-radius: 3px;
  background-color: bisque;
}
</style>