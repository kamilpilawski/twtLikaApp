import Vue from 'vue'
import VueRouter from 'vue-router'

// == Components ==
import Home from './components/views/HomeView'

Vue.use(VueRouter)

export default new VueRouter({
  routes: [
    { path: '/', name: 'Home', component: Home, props: { viewName: 'home' } },
    { path: '/user/:userId', name: 'User', component: Home, props: { viewName: 'user' } },
    { path: '/tag/:tagTitle', name: 'Tag', component: Home, props: { viewName: 'tag' } },
    { path: '/liked/', name: 'Liked', component: Home, props: { viewName: 'like' } }
  ]
})
