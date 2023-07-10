<template>
  <div>
    <table class="table__respondents">

      <tr>
        <th>Respondent</th>
        <th>Number of computers</th>
        <th>Number of employees</th>
        <th>Date</th>
      </tr>
      <tr>
        <td>
          <input   type="text" v-model="respondent.fullName" readonly disabled>
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
          <button class="savebtn" type="submit" @click="createRespondentPost()">Save</button>
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
          <input type="date" v-model="respondentItem.date">
        </td>
        <td>
          <button class="savebtn" type="submit" @click="updateRespondentPost(respondentItem)"  > Update </button>
        </td>
        <td>
          <button class="savebtn"  type="submit" @click="removeRespondentPost(respondentItem)">Delete</button>
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
.table__respondents{
  /* border: 2px solid black ; */
  width: 800px;
  margin: 10px auto;
  color: #fff;
}

input {
  width: 100%;
  box-sizing: border-box;
}



.savebtn {
  margin: 10px auto 0;
  margin-right: 10px;
  padding: 5px;
  background-color: #928264;
  color: #fff;
  border-radius: 3px;
  cursor: pointer;
  transition: .5s ease;
  width: 100%;
}

.savebtn:hover {
  background-color: #4caf50;
  
}
</style>