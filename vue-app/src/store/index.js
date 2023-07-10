import { createStore } from 'vuex';
import {authModule} from "@/store/authModule";
import createPersistedState from 'vuex-persistedstate';
export default createStore({
   modules:{
       auth_data: authModule
   },
    plugins: [
        createPersistedState({
            paths: ['auth_data.user'] // Указываем путь к состоянию user внутри модуля auth
        })
    ]

});
