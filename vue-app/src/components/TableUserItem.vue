<template>
  <tr class="table__user__item">
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

<style >
.table__user__item td {
  padding: 10px;
  text-align: left;
  border-bottom: 1px solid #ddd;
  background-color: rgb(173, 200, 254);
}


</style>