import axios from "axios";
import Cookies from "js-cookie";
import {deleteCookie, setCookie} from "cookies-next";

export class AuthService {

    constructor(url) {
        this.instance = axios.create({
            baseURL: url,
            timeout: 30000,
            timeoutErrorMessage: "Time out"
        })
    }

    login = (credentials) => {
        return this.instance.post("/auth/login", credentials)
            .then((res) => {
                const user = {
                    username: res.data.username,
                    token: res.data.token,
                    role: res.data.role
                };
                setCookie("currentUser", JSON.stringify(user));
                return user;
            }).catch(function (error) {
                throw new Error(error.response.data.message);
            })


    }

    register = (credentials) => {
        return this.instance.post("/auth/register", credentials)
            .then((res) => {
                return res.data.message;
            })
            .catch(function (error) {
                throw new Error(error.response.data.message);
            })
    }

    logout =() =>{
        deleteCookie("currentUser");
    }


}

