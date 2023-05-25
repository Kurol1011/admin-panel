import {createRouter, createWebHistory} from "vue-router";
import Login from "@/pages/LoginPage";


const routes = [

    {
        path:'/login',
        component: Login
    },

]

const router = createRouter( {
    routes,
    history: createWebHistory(process.env.BASE_URL)
})

export default router;

//для подключения роутов
//1 создаем router js routes,router(createrouter(config-прописываем))
//2 регаем наш роутер в main js .use(router)
//3 router-view прописываем в app