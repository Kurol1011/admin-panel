<template>
  <div>
    <table class="table__respondents">
      <tr>
        <th>Full name</th>
        <th>Number of computers</th>
        <th>Number of employees</th>
        <th>Date</th>
      </tr>
      <table-item
          v-for="respondentItem in respondents"
          :respondent="respondentItem"
          :key="respondentItem.id"
      />
    </table>
  </div>
</template>

<script>
import TableRespondentItem from "@/components/TableRespondentItem";
export default {
  components: {TableRespondentItem},
  props:{
    respondents:{
      type:Array,
      required:true
    }
  }

}
</script>

<style scoped>
.table__respondents{
  border: 2px solid black ;
  width: 800px;
  margin: 0 auto;
}
</style>