<!--

    Blank compenent to use as a template when creating new components
    (it's a basic 'hello world' compenent that has Blankynes instead of
    hello world)

-->
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
