import {authService} from "@/services";
import {setCookie} from "cookies-next";

export const useLogin = () =>{
    const login  = async (credentials) =>{
        const user = await authService.login(credentials);
        alert("here")
        if (user) {
            setCookie("currentUser", JSON.stringify(user));
        }
        return user;
    };

    return { login };

}
