
import MainContainer from "@/components/layout/MainContainer";

export default function MainLayout({children}) {
    const menuItem = [
        {
            path: "/main",
            name: "Pokemons",
            icon: <img src="/images/pokemons.png" alt="logo" width="25"/>
        },
        {
            path: "/main/wishlist",
            name: "Wish List",
            icon: <img src="/images/pokemonwishlist.png" alt="logo" width="25"/>
        },
        {
            path: "/main/catchlist",
            name: "Catch List",
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
