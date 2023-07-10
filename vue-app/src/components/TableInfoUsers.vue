<template>


<div class="table-container">
    <table>
      <thead>
      <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Date of birth</th>
        <th>Email</th>
        <th>Role</th>
      </tr>
    </thead>
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

</style>