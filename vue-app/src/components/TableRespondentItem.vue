<template>
  <tr>
    <td>{{respondent.fullName}}</td>
    <td>{{respondent.amountComputers}}</td>
    <td>{{respondent.amountEmployee}}</td>
    <td>{{respondent.date}}</td>
  </tr>
</template>

<script>
export default {
  props:{
    respondent:{
      type:Object,
      required:true
    }
  }
}
</script>

<style scoped>

td{
  border: 1px solid silver;
}
</style>