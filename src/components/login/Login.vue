<!--

    Login compenent, uses LoginForm and SignupForm

-->
<template>
  <div class="login">
    <div class="login__main">
      <router-view v-bind:class="{ transparency: transparent, container: true }"/>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      transparent: true
    }
  },
  created () {
    setTimeout(() => {
      this.transparent = false
    }, 1500)
  },
  methods: {

    transition (routeName) {
      this.transparent = true
      setTimeout(() => {
        this.$router.push({ name: routeName })
        setTimeout(() => {
          this.transparent = false
        }, 50)
      }, 1500)
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.login {
  width: 100%;
  display: flex;
  align-items: center;
}

@media screen and (max-width: 600px) {
  .header {
    font-size: 1.5rem;
  }
}

.login__main {
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 100%;
  align-items: center;
  height: 90vh;
  background-image: url('../../assets/login_jumbo2.jpg');
  background-size: cover;
  background-position: center;
}

.transparency {
  opacity: 0;
}

.login__form {
  display: flex;
  flex-direction: column;
  background-color: #151f26;
  border: 2px solid #5BB0D8;
  width: 400px;
  padding: 2rem;
  border-radius: 3px;
  transition: opacity 0.5s ease-in;
}

@media screen and (max-width: 600px) {
  .login__form {
    width: 90%;
    margin: 10px;
  }
}
</style>
