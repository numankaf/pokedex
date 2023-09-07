import {deleteCookie} from "cookies-next";

export const useLogout = () => {
    const logout = () => {
        deleteCookie("currentUser");
    };

    return { logout };
};
