import {getAuthorizationHeader} from "@/utils/getAutherizationHeader";
import axios from "axios";

export class PokemonService {
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

    createPokemon= (pokemonData) => {
        return this.instance.post("/pokemon", pokemonData).then((res) => {
            return res.data
        }).catch(function (error) {
            throw new Error(error.response.data.message);
        })
    }

    updatePokemon = (id,pokemonData) => {
        return this.instance.post(`/pokemon/${id}`, pokemonData).then((res) => {
            return res.data
        }).catch(function (error) {
            throw new Error(error.response.data.message);
        })
    }

    getById = (id) => {
        return this.instance.get(`/pokemon/${id}`).then((res) => {
            return res.data
        }).catch(function (error) {
            throw new Error(error.response.data.message);
        })
    }

    deletePokemonById = (id) => {
        return this.instance.delete(`/pokemon/${id}`)
            .then((res) => {
                return res.data;
            })
            .catch(function (error) {
                throw new Error(error.response.data.message);
            })
    }

    findAllPageable = (page, pageSize, sort) => {
        return this.instance.get( `/pokemon?page=${page}&size=${pageSize}` + (sort ? `&sort=${sort}` : ''))
            .then((res) => {
                return res.data;
            })
            .catch(function (error) {
                throw new Error(error.response.data.message);
            })
    }

    findAll = () => {
        return this.instance.get( `/pokemon/all`)
            .then((res) => {
                return res.data;
            })
            .catch(function (error) {
                throw new Error(error.response.data.message);
            })
    }

    search = ( searchDto,page, pageSize, sort) => {
        return this.instance.post( `/pokemon/search?page=${page}&size=${pageSize}` + (sort ? `&sort=${sort}` : ''), searchDto)
            .then((res) => {
                return res.data;
            })
            .catch(function (error) {
                throw new Error(error.response.data.message);
            })
    }

    getUsersWhoCatched = (id) => {
        return this.instance.get( `/pokemon/catch/${id}`)
            .then((res) => {
                return res.data;
            })
            .catch(function (error) {
                throw new Error(error.response.data.message);
            })
    }

    getUsersWhoAddedWishList = (id) => {
        return this.instance.get( `/pokemon/wish/${id}`)
            .then((res) => {
                return res.data;
            })
            .catch(function (error) {
                throw new Error(error.response.data.message);
            })
    }
}

