import {AuthService} from "@/services/AuthService";
import {AccountService} from "@/services/AccountService";


export const authService = new AuthService("http://localhost:8080/pokedex");
export const accountService = new AccountService("http://localhost:8080/pokedex");;
