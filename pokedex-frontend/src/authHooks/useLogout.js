import Cookies from "js-cookie";
import {deleteCookie} from "cookies-next";

export const useLogout = () => {
    const logout = () => {
        deleteCookie("currentUser");
    };

    return { logout };
};
