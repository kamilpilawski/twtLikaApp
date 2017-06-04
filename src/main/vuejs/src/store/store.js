import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    showNavigation: true,
    showSettings: false,
    showRegistration: false,
    showLogin: false,
    userName: null,
    title: 'Twitter Like App',
    navigationMenu: [
      { id: 1, title: 'Home', icon: 'dashboard', href: '/#/' },
      { id: 2, title: 'Favorite', icon: 'favorite', href: '/#/liked' }
    ]
  },
  getters: {
    getMenu (state) {
      return state.navigationMenu
    },
    getUserName (state) {
      return state.userName
    }
  },
  mutations: {
    changeNavigationState (state) {
      state.showNavigation = !state.showNavigation
    },
    changeSettingsState (state) {
      state.showSettings = !state.showSettings
    },
    changeRegistrationState (state) {
      state.showRegistration = !state.showRegistration
    },
    changeLoginState (state) {
      state.showLogin = !state.showLogin
    },
    changeTitle (state, newTitle) {
      state.title = newTitle
    },
    loginUser (state, userName) {
      state.userName = userName
    },
    logoutUser (state) {
      state.userName = null
    }
  }
})
