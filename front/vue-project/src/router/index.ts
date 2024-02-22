import {createRouter, createWebHistory} from 'vue-router'

import HomeView from '../views/HomeView.vue'
import AboutView from '../views/AboutView.vue'
import LoginView from '../views/LoginView.vue'
import TestView from '../views/TestView.vue'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: HomeView
        }
        , {
            path: '/about',
            name: 'about',
            component: AboutView
        }
        , {
            path: '/login',
            name: 'login',
            component: LoginView
        }
        , {
            path: '/test',
            name: 'test',
            // route level code-splitting
            // this generates a separate chunk (About.[hash].js) for this route
            // which is lazy-loaded when the route is visited.
            component: TestView
        }
        , {
            path: '/todoItem',
            name: 'todoItem',
            // route level code-splitting
            // this generates a separate chunk (About.[hash].js) for this route
            // which is lazy-loaded when the route is visited.
            component: () => import('../views/TodoItemView.vue')
        }

    ]
})

export default router