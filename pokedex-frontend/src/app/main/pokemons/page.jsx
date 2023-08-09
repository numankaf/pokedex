"use client"
import {DataTable} from 'primereact/datatable';
import {Column} from 'primereact/column';
import {useEffect, useRef, useState} from "react";
import {catchListService, pokemonService, wishListService} from "@/services";
import {Button} from 'primereact/button';
import {Tag} from "primereact/tag";
import PokemonTypesData from "@/utils/PokemonTypesData";
import {usePathname} from "next/navigation";
import {useRouter} from "next/navigation";
import {InputText} from 'primereact/inputtext';

const PokemonsPage = () => {
    const pathname = usePathname();
    const router = useRouter();
    const [name, setName] = useState('');
    const [pokemons, setPokemons] = useState(null);
    const [loading, setLoading] = useState(false);
    const [totalRecords, setTotalRecords] = useState(0);
    const [lazyState, setlazyState] = useState({
        first: 0,
        rows: 5,
        page: 0,
        sortField: null,
        sortOrder: null,
    });
    const dt = useRef(null);
    useEffect(() => {
        loadLazyData();
    }, [lazyState,name]);

    let networkTimeout = null;

    const loadLazyData = () => {
        setLoading(true);

        if (networkTimeout) {
            clearTimeout(networkTimeout);
        }

        networkTimeout = setTimeout(() => {
            const sort = lazyState.sortField ? (lazyState.sortField + ',' + (lazyState.sortOrder === 1 ? 'asc' : 'desc')) : undefined;
            if (name !== ""){
                const searchDto = {name: name};
                pokemonService.search(searchDto, (lazyState.first / lazyState.rows), lazyState.rows, sort).then((data) => {
                    setTotalRecords(data.totalElements);
                    setPokemons(data.content);
                    setLoading(false);
                })
            }

            else{
                pokemonService.findAllPageable((lazyState.first / lazyState.rows), lazyState.rows, sort).then((data) => {
                    setTotalRecords(data.totalElements);
                    setPokemons(data.content);
                    setLoading(false);
                });
            }

        }, Math.random() * 1000);
    };


    const onPage = (event) => {
        setlazyState(event);
    };

    const onSort = (event) => {
        const lastPage = lazyState.page;
        setlazyState({
                page: lastPage,
                sortOrder: event.sortOrder,
                sortField: event.sortField,
                rows: event.rows,
                first: event.first
            }
        )
    };

    // const onFilter = (event) => {
    //     event['first'] = 0;
    //     setlazyState(event);
    // };


    const totalBodyTemplate = (rowData) => {
        const total = rowData.hp + rowData.attack + rowData.defense + rowData.speed + rowData.specialAttack + rowData.specialDefense;
        return <p>{total}</p>;
    };
    const thumbnailBodyTemplate = (rowData) => {
        return <img src={rowData.thumbnail} width={40} height={40}/>;
    };

    const catchStatusBodyTemplate = (rowData) => {
        if (rowData.inCatchList) {
            return <Tag className="mr-2" severity="success" value="In Catch List"></Tag>
        }
        if (rowData.inWishList) {
            return <Tag className="mr-2" severity="warning" value="In Wish List"></Tag>
        }
        return <div></div>;
    };
    const typesBodyTemplate = (rowData) => {
        return <div>
            {
                rowData.types.map((item, index) => (
                    <div key={index} className={"cursor-pointer"}>
                        <Tag style={{background: PokemonTypesData.get(item)}}>
                            <p> {item}</p>
                        </Tag>
                    </div>
                ))
            }
        </div>
    };

    const addToWishList = (id) => {
        wishListService.addToWishList(id);
        loadLazyData();
    }

    const addToCatchList = (id) => {
        catchListService.addToCatchList(id);
        loadLazyData();
    }

    const removeFromWishList = (id) => {
        wishListService.removeFromWishList(id);
        loadLazyData();
    }

    const removeFromCatchList = (id) => {
        catchListService.removeFromCatchList(id);
        loadLazyData();
    }
    const goToDetail = (id) => {
        router.push(pathname + "/" + id)
    }

    const operationsBodyTemplate = (rowData) => {

        return <div className={"flex flex-row gap-1"}>
            <Button size="small" icon="pi pi-search" severity="info" onClick={() => goToDetail(rowData.id)}
                    tooltip={"Go to Detail"}/>
            {!rowData.inCatchList && <Button size="small" icon="pi pi-plus" onClick={() => addToCatchList(rowData.id)}
                                             tooltip={"Add to Catch List"}/>}
            {!rowData.inCatchList && !rowData.inWishList &&
                <Button icon="pi pi-heart" onClick={() => addToWishList(rowData.id)}
                        severity="help" size="small" tooltip={"Add to Wish List"}/>}
            {rowData.inCatchList &&
                <Button size="small" icon="pi pi-minus" onClick={() => removeFromCatchList(rowData.id)}
                        severity="danger" tooltip={"Remove From Catch List"}/>}
            {rowData.inWishList && <Button icon="pi pi-heart" onClick={() => removeFromWishList(rowData.id)}
                                           severity="danger" size="small" tooltip={"Remove From Wish List"}/>}
        </div>;
    };

    return (
        <div className={"card"}>
            <h1>Complete Pokemon List</h1>
            <p className={"py-2"}>This is a full list of every Pokémon from all 9 generations of the Pokémon series, along with their main stats.

                The table is sortable by clicking a column header, and searchable by using the controls above it.</p>
            <div className={"flex flex-column  py-3 "}>
                <div className={"flex  justify-content-end"}>
                    <div className={"flex gap-3 "}>
                        <InputText placeholder={"Search By Name"} value={name} onChange={(e) => {
                            setName(e.target.value);
                        }}/>
                    </div>
                </div>

            </div>
            <DataTable value={pokemons} lazy loading={loading} filterDisplay="row" paginator
                       onPage={onPage} totalRecords={totalRecords} first={lazyState.first} rows={lazyState.rows}
                       onSort={onSort} sortField={lazyState.sortField} sortOrder={lazyState.sortOrder}
                       paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown"
                       currentPageReportTemplate="Showing {first} to {last} of {totalRecords} entries"
                       dataKey="id" rowsPerPageOptions={[5, 10, 25]}>
                <Column field="thumbnail" header="Image" style={{minWidth: '3rem'}}
                        body={thumbnailBodyTemplate}></Column>
                <Column field="types" header="Types" style={{minWidth: '4rem'}} body={typesBodyTemplate}></Column>
                <Column field="name" header="Name" sortable style={{minWidth: '5rem'}}></Column>
                <Column field="total" header="Total" style={{minWidth: '2rem'}} body={totalBodyTemplate}></Column>
                <Column field="hp" header="Hp" sortable style={{minWidth: '2rem'}}></Column>
                <Column field="attack" header="Atk" sortable style={{minWidth: '2rem'}}></Column>
                <Column field="defense" header="Def" sortable style={{minWidth: '2rem'}}></Column>
                <Column field="speed" header="Speed" sortable style={{minWidth: '2rem'}}></Column>
                <Column field="specialAttack" header="Sp. Atk" sortable style={{minWidth: '3rem'}}></Column>
                <Column field="specialDefense" header="Sp. Def" sortable style={{minWidth: '3rem'}}></Column>
                <Column field="catchStatus" header="Status" style={{minWidth: '8rem'}}
                        body={catchStatusBodyTemplate}></Column>
                <Column field="operations" header="Operations" style={{minWidth: '3rem'}}
                        body={operationsBodyTemplate}></Column>
            </DataTable>
        </div>
    )
}

export default PokemonsPage;
