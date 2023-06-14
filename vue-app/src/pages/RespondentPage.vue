<template>
  <div class="container__respondent">
    <h1>Respondent page</h1>
    <table-info-respondent
        :respondents="respondents"
    />
  </div>
</template>

<script>
import axios from "axios";
import TableInfoRespondent from "@/components/TableInfoRespondent";
export default {
  components: {TableInfoRespondent},
  data(){
    return{
      respondents:[{id:5,fullName:'John Makalister',amountComputers:25,amountEmployee:24,date:'02/02/2022',formAction:"/awdw"}]
    }
  },
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

</style>