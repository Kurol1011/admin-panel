<template>
  <tr table__respondent__item>
    <td :respondent="respondent.fullName"><input type="text" name="fullname" :value="respondent.fullName" form="update_form" ></td>
    <td :respondent="respondent.amountComputers"><input type="text" name="computers" :value="respondent.amountComputers" form="update_form"></td>
    <td :respondent="respondent.amountEmployee"><input type="text" name="employees" :value="respondent.amountEmployee" form="update_form"></td>
    <td :respondent="respondent.date"><input type="text" name="date" :value="respondent.date" form="update_form" ></td>
    <td><input type="submit" value="Update" form="update_form"></td>
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

</style>