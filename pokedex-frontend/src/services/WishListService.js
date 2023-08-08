import {getAuthorizationHeader} from "@/utils/getAutherizationHeader";
import axios from "axios";

export class WishListService {
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


    addToWishList = (id) => {
        return this.instance.post(`/wish-list/add/${id}`,null).then((res) => {
            return res.data
        }).catch(function (error) {
            throw new Error(error.response.data.message);
        })
    }

    removeFromWishList = (id) => {
        return this.instance.post(`/wish-list/remove/${id}`,null).then((res) => {
            return res.data
        }).catch(function (error) {
            throw new Error(error.response.data.message);
        })
    }


    getAllInWishList = () => {
        return this.instance.get( `/wish-list/all`)
            .then((res) => {
                return res.data;
            })
            .catch(function (error) {
                throw new Error(error.response.data.message);
            })
    }

    getAllInWishListPageable = (page, pageSize, sort) => {
        return this.instance.get( `/wish-list?page=${page}&size=${pageSize}` + (sort ? `&sort=${sort}` : ''))
            .then((res) => {
                return res.data;
            })
            .catch(function (error) {
                throw new Error(error.response.data.message);
            })
    }

}

