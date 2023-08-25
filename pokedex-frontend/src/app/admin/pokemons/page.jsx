"use client"
import {DataTable} from 'primereact/datatable';
import {Column} from 'primereact/column';
import {useEffect, useRef, useState} from "react";
import {pokemonService} from "@/services";
import {Button} from 'primereact/button';
import {Tag} from "primereact/tag";
import PokemonTypesData from "@/utils/PokemonTypesData";
import {usePathname,useRouter} from "next/navigation";
import {InputText} from 'primereact/inputtext';
import {ConfirmDialog, confirmDialog} from 'primereact/confirmdialog';
import {Toast} from 'primereact/toast';

const PokemonsPage = () => {
    const toast = useRef(null);
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
    useEffect(() => {
        loadLazyData();
    }, [lazyState, name]);

    let networkTimeout = null;

    const loadLazyData = () => {
        setLoading(true);

        if (networkTimeout) {
            clearTimeout(networkTimeout);
        }

        networkTimeout = setTimeout(() => {
            const sort = lazyState.sortField ? (lazyState.sortField + ',' + (lazyState.sortOrder === 1 ? 'asc' : 'desc')) : undefined;
            if (name !== "") {
                const searchDto = {name: name};
                pokemonService.search(searchDto, (lazyState.first / lazyState.rows), lazyState.rows, sort).then((data) => {
                    setTotalRecords(data.totalElements);
                    setPokemons(data.content);
                    setLoading(false);
                })
            } else {
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


    const totalBodyTemplate = (rowData) => {
        const total = rowData.hp + rowData.attack + rowData.defense + rowData.speed + rowData.specialAttack + rowData.specialDefense;
        return <p>{total}</p>;
    };
    const thumbnailBodyTemplate = (rowData) => {
        return <img src={rowData.thumbnail} width={40} height={40}/>;
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


    const goToDetail = (id) => {
        router.push(pathname + "/" + id)
    }
    const goToEdit = (id) => {
        router.push(pathname + "/edit/"+id)
    }
    const goToCreate = () => {
        router.push(pathname + "/create")
    }

    const operationsBodyTemplate = (rowData) => {

        return <div className={"flex flex-row gap-1"}>
            <Button size="small" icon="pi pi-search" severity="info" onClick={() => goToDetail(rowData.id)}
                    tooltip={"Detail"}/>
            <Button size="small" icon="pi pi-pencil" severity="warning"  onClick={() => goToEdit(rowData.id)}
                    tooltip={"Edit"}/>
            <Button size="small" icon="pi pi-times" severity="danger" onClick={() => confirmDelete(rowData.id)}
                    tooltip={"Delete"}/>
        </div>;
    };

    const confirmDelete = (id) => {

        confirmDialog({
            message: 'Do you want to delete this pokemon?',
            header: 'Delete Confirmation',
            icon: 'pi pi-info-circle',
            acceptClassName: 'p-button-danger',
            accept: () => accept(id),
        });
    };

    const accept = (id) => {
        pokemonService.deletePokemonById(id).then((res) => {
            toast.current.show({
                severity: 'success',
                summary: 'Success',
                detail: 'You have deleted the pokemon',
                life: 3000
            });
            loadLazyData();
        }).catch((e) => {
            toast.current.show({
                severity: 'danger',
                summary: 'Error',
                detail: 'Error occured on delete',
                life: 3000
            });
        })

    }


    return (
        <div className={"card"}>
            <Toast ref={toast}/>
            <ConfirmDialog/>
            <h1>Pokemon List</h1>
            <div className={"flex flex-column  py-3 "}>
                <div className={"flex  justify-content-between"}>
                    <div>
                        <Button size="small" icon="pi pi-plus" severity="success" label={"Create a Pokemon"}
                                onClick={() => goToCreate()}
                                tooltip={"Create a Pokemon"}/>
                    </div>
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
                <Column field="operations" header="Operations" style={{minWidth: '3rem'}}
                        body={operationsBodyTemplate}></Column>
            </DataTable>
        </div>
    )
}

export default PokemonsPage;
