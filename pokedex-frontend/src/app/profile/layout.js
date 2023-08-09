"use client"
import MainContainer from "@/components/layout/MainContainer";
import {getTokenDecoded} from "@/utils/getTokenDecoded";
import {useCurrentUser} from "@/authHooks/useCurrentUser";
import {getCookie} from "cookies-next";

export default function ProfileLayout({children}) {
    const user =  JSON.parse(getCookie("currentUser"));


    const menuItem = [
        {
            path: "/profile/account",
            name: "Account",
            icon: <img src="/images/pokemons.png" alt="logo" width="25"/>
        },
        {
            path: "/profile/changepassword",
            name: "Change Password",
            icon: <img src="/images/pokemonwishlist.png" alt="logo" width="25"/>
        },
        {
            path: user.role ==='ADMIN' ? "/admin/users" :"/main/pokemons",
            name: "Back ",
            icon: <img src="/images/pokemonwishlist.png" alt="logo" width="25"/>
        },
    ]
    return (
        <>
            <MainContainer menuItem={menuItem}>
                {children}
            </MainContainer>
        </>
    )
}
