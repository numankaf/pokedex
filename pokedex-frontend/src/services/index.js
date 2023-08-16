import {AuthService} from "@/services/AuthService";
import {AccountService} from "@/services/AccountService";
import {UserService} from "@/services/UserService";
import {PokemonService} from "@/services/PokemonService";
import {CatchListService} from "@/services/CatchListService";
import {WishListService} from "@/services/WishListService";

const baseUrl = "http://localhost:9080/pokedex";


export const authService = new AuthService(baseUrl);
export const accountService = new AccountService(baseUrl);;
export const userService = new UserService(baseUrl);
export const pokemonService = new PokemonService(baseUrl);
export const catchListService = new CatchListService(baseUrl);
export const wishListService = new WishListService(baseUrl);

