import {authService} from "@/services";
import Cookies from "js-cookie";
export const useLogin = () =>{
    const login  = async (credentials) =>{
        const user = await authService.login(credentials);
        if (user) {
            Cookies.set("currentUser", JSON.stringify(user));
        }
        return user;
    };

    return { login };

}
