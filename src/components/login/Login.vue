
<template>
  <div class="login">
    <h1> WHAAAAAAA </h1>
    {{ msg }}
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      msg: 'This is something'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
