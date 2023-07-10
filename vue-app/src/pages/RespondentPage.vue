<template>
<left-block-display></left-block-display>
  <div class="container__respondent">
    <h1 class="respondent__page__title">Respondent posts page</h1>
    <input type="text" v-model="searchName" class="search__input__respondent__post" placeholder="Enter respondent" >
    <div class="container__respondent__page__filter">
    <button type="button" @click="sortByName" >Sort by name</button>

    <button type="button" @click="sortByDate">Sort by date</button>

    <button type="button" @click="sortByAmountComputers" >Sort by amount of computers</button>

    <button type="button" @click="sortByAmountEmployee">Sort by amount od employee</button>
  </div>
    <table-info-respondent
        :respondents="searchByName"
    />

  </div>
</template>

<script>
import axios from "axios";
import LeftBlockDisplay from "@/components/LeftBlockDisplay.vue";
import TableInfoRespondent from "@/components/TableInfoRespondent";
export default {
  components: {TableInfoRespondent,LeftBlockDisplay},
  data(){
    return{
      searchName:'',
      respondents:[]
    }
  },
  methods:{
    sortByName(){
      this.respondents.sort((a,b)=>a.fullName.localeCompare(b.fullName));
    }
    ,
    sortByDate(){
      this.respondents.sort((a, b) => new Date(a.date) - new Date(b.date));
    },
    sortByAmountComputers(){
      this.respondents.sort((a,b) => a.amountComputers - b.amountComputers);
    },
    sortByAmountEmployee(){
      this.respondents.sort((a,b)=> a.amountEmployee - b.amountEmployee);
    }
  }
  ,
  computed:{
    searchByName(){
      return this.respondents.filter(post => post.fullName.toLowerCase().includes(this.searchName.toLowerCase()));
    }
  }
  ,
  async created(){
    await axios.get('http://localhost:8081/respondents',{
      headers:{
        'Authorization':`Bearer ${localStorage.getItem('token')}`
      }
    })
        .then(response => {
          this.respondents = response.data;
          console.log("success");
        })
        .catch(error => console.log(error));
  }

}
</script>

<style scoped>
.container__respondent{
  width:80%;
  margin: 0 auto;
  padding:10px;
}

.respondent__page__title{
  color: white;
  font-size: 46px;
  font-weight: 500;
  text-align: center;
  margin-bottom: 10px;
  margin-top: 10px;
}

.search__input__respondent__post{
  width: 40%;
  border-radius: 10px;
  margin: 10px auto;
  padding: 5px 10px;
  display: block;
}

.container__respondent__page__filter{
  width:70%;
  margin: 10px auto;
  display: flex;
  justify-content: center;
  grid-gap: 5px;
  padding:10px;
}

.container__respondent__page__filter button{
  border: 2px solid #0e0b54;
  padding: 7px;
  background-color: white;
  color: #0e0b54;
  font-family: Arial;
  font-size: 14px;
  font-weight: bold;
}

.container__respondent__page__filter button:hover{
  background-color: #a7d9b6;
  cursor: pointer;
}

</style>