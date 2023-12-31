import {getAuthorizationHeader} from "@/utils/getAutherizationHeader";
import axios from "axios";

export class UserService {
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

    createUser = (userData) => {
        return this.instance.post("/users", userData).then((res) => {
            return res.data
        }).catch(function (error) {
            throw new Error(error.response.data.message);
        })
    }

    updateUser = (id,userData) => {
        return this.instance.post(`/users/${id}`, userData).then((res) => {
            return res.data
        }).catch(function (error) {
            throw new Error(error.response.data.message);
        })
    }

    getById = (id) => {
        return this.instance.get(`/users/${id}`).then((res) => {
            return res.data
        }).catch(function (error) {
            throw new Error(error.response.data.message);
        })
    }

    deleteUserByID = (id) => {
        return this.instance.delete(`/users/${id}`)
            .then((res) => {
                return res.data.message;
            })
            .catch(function (error) {
                throw new Error(error.response.data.message);
            })
    }

    findAllPageable = (page, pageSize, sort) => {
        return this.instance.get( `/users?page=${page}&size=${pageSize}` + (sort ? `&sort=${sort}` : ''))
            .then((res) => {
                return res.data;
            })
            .catch(function (error) {
                throw new Error(error.response.data.message);
            })
    }

    findAll = () => {
        return this.instance.get( `/users/all`)
            .then((res) => {
                return res.data;
            })
            .catch(function (error) {
                throw new Error(error.response.data.message);
            })
    }

    search = ( searchDto,page, pageSize, sort) => {
        return this.instance.post( `/users/search?page=${page}&size=${pageSize}` + (sort ? `&sort=${sort}` : ''), searchDto)
            .then((res) => {
                return res.data;
            })
            .catch(function (error) {
                throw new Error(error.response.data.message);
            })
    }

}

