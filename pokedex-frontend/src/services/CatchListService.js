import {getAuthorizationHeader} from "@/utils/getAutherizationHeader";
import axios from "axios";

export class CatchListService {
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


    addToCatchList = (id) => {
        return this.instance.post(`/catch-list/add/${id}`,null).then((res) => {
            return res.data
        }).catch(function (error) {
            throw new Error(error.response.data.message);
        })
    }

    removeFromCatchList = (id) => {
        return this.instance.post(`/catch-list/remove/${id}`,null).then((res) => {
            return res.data
        }).catch(function (error) {
            throw new Error(error.response.data.message);
        })
    }


    getAllInCatchList = () => {
        return this.instance.get( `/catch-list/all`)
            .then((res) => {
                return res.data;
            })
            .catch(function (error) {
                throw new Error(error.response.data.message);
            })
    }

    getAllInCatchListPageable = (page, pageSize, sort) => {
        return this.instance.get( `/catch-list?page=${page}&size=${pageSize}` + (sort ? `&sort=${sort}` : ''))
            .then((res) => {
                return res.data;
            })
            .catch(function (error) {
                throw new Error(error.response.data.message);
            })
    }

}

