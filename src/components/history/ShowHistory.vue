
<template>
  <div class="blank">
    <h1> This is blank </h1>
    {{ msg }}
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'ShowHistory',
  data () {
    return {
      msg: 'ShowHistory'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
