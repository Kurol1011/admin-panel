<template>
  <left-block-display></left-block-display>
  <div>
    <h1>USER PAGE</h1>
    <router-link to="/admin">admin test</router-link>
  </div>
</template>

<script>
import LeftBlockDisplay from '@/components/LeftBlockDisplay.vue';
export default {
  components:{
    LeftBlockDisplay
  }
}
</script>

<style scoped>

</style>