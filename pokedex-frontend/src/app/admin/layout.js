import AppTopbar from "@/components/layout/AppTopbar";
import MainContainer from "@/components/layout/MainContainer";

export default function AdminLayout({children}) {
    const menuItem = [
        {
            path: "/admin/users",
            name: "Users",
            icon: <img src="/images/pokemonwishlist.png" alt="logo" width="25"/>
        },
        {
            path: "/admin/pokemons",
            name: "Pokemons",
            icon: <img src="/images/pokemoncatchlist.png" alt="logo" width="25"/>
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
