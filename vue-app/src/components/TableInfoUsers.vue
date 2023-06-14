<template>
  <div>
    <table class="table__users">
      <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Date of birth</th>
        <th>Email</th>
        <th>Role</th>
      </tr>
      <table-item
          v-for="userItem in users"
          :user="userItem"
          :key="userItem.id"
      />
    </table>
  </div>
</template>

<script>
import tableItem from "@/components/TableUserItem";
export default {
  components: {tableItem},
  props:{
    users:{
      type:Array,
      required:true
    }
  }

}
</script>

<style scoped>
.table__users{
  border: 2px solid black ;
  width: 800px;
  margin: 0 auto;
}
</style>