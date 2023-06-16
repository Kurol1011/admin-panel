
export const authModule = {
    state:()=>({
        user:null,
        msg:'hello555'
    }),
    mutations: {
        setUser(state, user) {
            state.user = user;
        }
    },
    actions: {

    },
    getters: {
        isAuthenticated(state) {
            return state.user !== null;
        },
        userRole(state) {
            return state.user ? state.user.role : null;
        },
        userId(state){
            return state.user ? state.user.id :null;
        }
    },
    namespaced: true
}
