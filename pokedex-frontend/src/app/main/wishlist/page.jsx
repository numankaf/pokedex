"use client"

import {Button} from 'primereact/button';
import {DataView, DataViewLayoutOptions} from 'primereact/dataview';
import {Rating} from 'primereact/rating';
import {Tag} from 'primereact/tag';
import {catchListService, pokemonService, wishListService} from "@/services";
import {useEffect, useState} from "react";
import PokemonTypesData from "@/utils/PokemonTypesData";
import {usePathname, useRouter} from "next/navigation";

const WishListPage = () => {
    const pathname = usePathname();
    const router = useRouter();
    const [totalRecords, setTotalRecords] = useState(0);
    const [loading, setLoading] = useState(false);
    const [pokemons, setPokemons] = useState(null);
    const [lazyState, setlazyState] = useState({
        first: 0,
        rows: 6,
        page: 0,
    });
    useEffect(() => {
        loadLazyData();
    }, [lazyState]);

    let networkTimeout = null;
    const loadLazyData = () => {
        setLoading(true);

        if (networkTimeout) {
            clearTimeout(networkTimeout);
        }

        networkTimeout = setTimeout(() => {
            wishListService.getAllInWishListPageable((lazyState.first / lazyState.rows), lazyState.rows, null).then((data) => {
                setTotalRecords(data.totalElements);
                setPokemons(data.content);
                setLoading(false);
            });


        }, Math.random() * 1000);
    };

    const removeFromWishList = (id) => {
        setLoading(true);
        wishListService.removeFromWishList(id);
        loadLazyData();
    }
    const goToDetail = (id) => {
        const pt = pathname.replace("/wishlist", "");
        router.push(pt + "/pokemons/" + id)
    }

    const gridItem = (pokemon) => {
        return (
            <div className="col-12 sm:col-6 lg:col-12 xl:col-4 p-3">
                <div className="p-4 border-2 shadow-2 border-round-xl surface-border surface-card border-round">
                    <div className="flex flex-column align-items-center gap-2">
                        <img className="border-round"
                             src={pokemon.thumbnail} width={220} height={220}
                             alt={pokemon.name}/>
                        <div className="text-2xl font-bold">{pokemon.name}</div>
                        <div className={"flex flex-row gap-2"}>
                            {
                                pokemon.types.map((item, index) => (
                                    <div key={index} className={"cursor-pointer"}>
                                        <Tag style={{background: PokemonTypesData.get(item)}}>
                                            <p> {item}</p>
                                        </Tag>
                                    </div>
                                ))
                            }
                        </div>
                    </div>
                    <div className="flex align-items-end justify-content-end gap-2">
                        <Button icon="pi pi-search" tooltip={"Go To Detail"} severity="info"
                                onClick={() => goToDetail(pokemon.id)}></Button>
                        <Button icon="pi pi-minus" tooltip={"Remove From Wish List"} severity="danger"
                                onClick={() => removeFromWishList(pokemon.id)}></Button>
                    </div>

                </div>
            </div>
        );
    };

    const onPage = (event) => {
        setlazyState(event);
    };


    return (
        <div className="card">
            <h1>Wish List</h1>
            {/* eslint-disable-next-line react/no-unescaped-entities */}

            <DataView value={pokemons} paginator rows={lazyState.rows} lazy first={lazyState.first}
                      rowsPerPageOptions={[3, 6, 12, 24]} loading={loading}
                      paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown"
                      currentPageReportTemplate="Showing {first} to {last} of {totalRecords} entries"
                      onPage={onPage} itemTemplate={gridItem} totalRecords={totalRecords}/>
        </div>
    )
}

export default WishListPage;
