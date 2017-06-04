// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Vuetify from 'vuetify'
import Vuex from 'vuex'
import axios from 'axios'
import routes from './routes'
import store from './store/store'
import App from './App'

Vue.use(Vuetify, axios)
Vue.use(Vuex)
Vue.config.productionTip = false
Vue.prototype.serverIp = 'http://localhost:8080'

routes.beforeEach(function (to, from, next) {
  window.scrollTo(0, 0)
  next()
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store: store,
  router: routes,
  render: h => h(App)
})
