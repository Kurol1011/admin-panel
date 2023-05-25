<template>
  <tr>
    <td>{{user.firstName}}</td>
    <td>{{user.lastName}}</td>
    <td>{{user.dateOfBirth}}</td>
    <td>{{user.email}}</td>
    <td>{{user.role}}</td>
  </tr>
</template>

<script>
export default {
  props:{
    user:{
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