import {createRouter, createWebHistory} from "vue-router";
import LoginPage from "@/pages/LoginPage";
import AdminPage from "@/pages/AdminPage";
import store from "@/store";
import AccessDenied from "@/pages/AccessDenied";
import UserPage from "@/pages/UserPage";
import RegisterPage from "@/pages/RegisterPage";
import DevPage from "@/pages/DevPage";
import ManagerPage from "@/pages/ManagerPage";
import RespondentPage from "@/pages/RespondentPage";

const routes = [

    {
        path:'/',
        component: LoginPage,
        meta: {
            requiresAuth: false,
            allowedRoles: []
        }
    },
    {
        path:'/admin',
        component: AdminPage,
        meta: {
            requiresAuth: true,
            allowedRoles: ['ADMIN'] // Разрешенные роли (пустой список означает доступ для всех ролей)
        }
    },
    {
        path:'/register',
        component: RegisterPage,
        meta: {
            requiresAuth: true,
            allowedRoles: ['ADMIN'] // Разрешенные роли (пустой список означает доступ для всех ролей)
        }
    },
    {
        path: '/access-denied',
        component: AccessDenied,
        meta: {
            requiresAuth: false,
            allowedRoles: []
        }
    },
    {
        path: '/user',
        component: UserPage,
        meta: {
            requiresAuth: true,
            allowedRoles: ['USER']
        }
    },
    {
        path: '/dev',
        component: DevPage,
        meta: {
            requiresAuth: true,
            allowedRoles: ['DEV']
        }
    },
    {
        path: '/manager',
        component: ManagerPage,
        meta: {
            requiresAuth: true,
            allowedRoles: ['MANAGER']
        }
    },
    {
        path: '/respondent',
        component: RespondentPage,
        meta: {
            requiresAuth: true,
            allowedRoles: ['MANAGER','ADMIN','DEV','USER']
        }
    }

]



const router = createRouter( {
    routes,
    history: createWebHistory(process.env.BASE_URL)
})

router.beforeEach((to, from, next) => {
    const requiresAuth = to.meta.requiresAuth;
    const allowedRoles = to.meta.allowedRoles;

    const isAuthenticated = store.getters['auth_data/isAuthenticated'];
    const userRole = store.getters['auth_data/userRole'];

    if (requiresAuth && !isAuthenticated) {
        // Если требуется аутентификация, но пользователь не аутентифицирован, перенаправь на страницу входа
        next('/');
    } else if (requiresAuth && !allowedRoles.includes(userRole)) {
        // Если требуется аутентификация и пользователь не имеет разрешенную роль, перенаправь на страницу с сообщением об ошибке доступа
        next('/access-denied');
    } else {
        // Доступ разрешен
        next();
    }
});

export default router;

//для подключения роутов
//1 создаем router js routes,router(createrouter(config-прописываем))
//2 регаем наш роутер в main js .use(router)
//3 router-view прописываем в app