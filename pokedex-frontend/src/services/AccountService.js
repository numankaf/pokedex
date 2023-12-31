import {getAuthorizationHeader} from "@/utils/getAutherizationHeader";
import axios from "axios";

export class AccountService {
    constructor(url) {
        this.instance = axios.create({
            baseURL: url,
            timeout: 30000,
            timeoutErrorMessage: "Time out",
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin' : '*' ,
                ...getAuthorizationHeader()
            }
        })
    }

    getAccountDetail = () => {
        return this.instance.get("/account", {
        }).then((res) => {
            return res.data
        }).catch(function (error) {
            throw new Error(error.response.data.message);
        })
    }

    getAccountDetailTopbar = () => {
        return this.instance.get("/account/topbar", {
        }).then((res) => {
            return res
        }).catch(function (error) {
            throw new Error(error.response.data.message);
        })
    }

    changePassword = (credentials) => {
        return this.instance.post("/account/change-password", credentials)
            .then((res) => {
                return res.data.message;
            })
            .catch(function (error) {
                throw new Error(error.response.data.message);
            })
    }

    update = (credentials) => {
        return this.instance.post("/account/update", credentials)
            .then((res) => {
                return res.data.message;
            })
            .catch(function (error) {
                throw new Error(error.response.data.message);
            })
    }
}

