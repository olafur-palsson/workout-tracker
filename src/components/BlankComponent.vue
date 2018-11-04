
<template>
  <div class="blank">
    <h1> This is blank </h1>
    {{ msg }}
  </div>
</template>

<script>
export default {
  name: 'Blank',
  data () {
    return {
      msg: 'Blankyness'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
