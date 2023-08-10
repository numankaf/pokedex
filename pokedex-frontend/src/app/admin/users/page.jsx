"use client"
import {DataTable} from 'primereact/datatable';
import {Column} from 'primereact/column';
import {useEffect, useRef, useState} from "react";
import {catchListService, pokemonService, userService, wishListService} from "@/services";
import {Button} from 'primereact/button';
import {Tag} from "primereact/tag";
import PokemonTypesData from "@/utils/PokemonTypesData";
import {usePathname} from "next/navigation";
import {useRouter} from "next/navigation";
import {InputText} from 'primereact/inputtext';
import {ConfirmDialog, confirmDialog} from 'primereact/confirmdialog';
import {Toast} from 'primereact/toast';

const UsersPage = () => {
    const toast = useRef(null);
    const pathname = usePathname();
    const router = useRouter();
    const [name, setName] = useState('');
    const [users, setUsers] = useState(null);
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
                userService.search(searchDto, (lazyState.first / lazyState.rows), lazyState.rows, sort).then((data) => {
                    setTotalRecords(data.totalElements);
                    setPokemons(data.content);
                    setLoading(false);
                })
            } else {
                userService.findAllPageable((lazyState.first / lazyState.rows), lazyState.rows, sort).then((data) => {
                    setTotalRecords(data.totalElements);
                    setUsers(data.content);
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


    const thumbnailBodyTemplate = (rowData) => {
        return  <img className={"border-circle"} src={rowData.thumbnail} width={40} height={40}/>;
    };


    const goToDetail = (id) => {
        router.push(pathname + "/" + id)
    }
    const goToEdit = (id) => {
        router.push(pathname + "/edit/" + id)
    }
    const goToCreate = () => {
        router.push(pathname + "/create")
    }

    const operationsBodyTemplate = (rowData) => {

        return <div className={"flex flex-row gap-1"}>
            <Button size="small" icon="pi pi-search" severity="info" onClick={() => goToDetail(rowData.id)}
                    tooltip={"Detail"}/>
            <Button size="small" icon="pi pi-pencil" severity="warning" onClick={() => goToEdit(rowData.id)}
                    tooltip={"Edit"}/>
            <Button size="small" icon="pi pi-times" severity="danger" onClick={() => confirmDelete(rowData.id)}
                    tooltip={"Delete"}/>
        </div>;
    };

    const confirmDelete = (id) => {

        confirmDialog({
            message: 'Do you want to delete this user?',
            header: 'Delete Confirmation',
            icon: 'pi pi-info-circle',
            acceptClassName: 'p-button-danger',
            accept: () => accept(id),
        });
    };

    const accept = (id) => {
        userService.deleteUserByID(id).then((res) => {
            toast.current.show({
                severity: 'success',
                summary: 'Success',
                detail: 'You have deleted the user',
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
            <h1>User List</h1>
            <div className={"flex flex-column  py-3 "}>
                <div className={"flex  justify-content-between"}>
                    <div>
                        <Button size="small" icon="pi pi-plus" severity="success" label={"Create a User"}
                                onClick={() => goToCreate()}
                                tooltip={"Create a User"}/>
                    </div>
                    <div className={"flex gap-3 "}>
                        <InputText placeholder={"Search By Name"} value={name} onChange={(e) => {
                            setName(e.target.value);
                        }}/>
                    </div>
                </div>

            </div>
            <DataTable value={users} lazy loading={loading} filterDisplay="row" paginator
                       onPage={onPage} totalRecords={totalRecords} first={lazyState.first} rows={lazyState.rows}
                       onSort={onSort} sortField={lazyState.sortField} sortOrder={lazyState.sortOrder}
                       paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown"
                       currentPageReportTemplate="Showing {first} to {last} of {totalRecords} entries"
                       dataKey="id" rowsPerPageOptions={[5, 10, 25]}>
                <Column field="thumbnail" header="Image" style={{minWidth: '3rem'}}
                        body={thumbnailBodyTemplate}></Column>
                <Column field="username" header="Username" sortable style={{minWidth: '7rem'}}></Column>
                <Column field="email" header="Email" sortable style={{minWidth: '7rem'}}></Column>
                <Column field="name" header="Name" sortable style={{minWidth: '5rem'}}></Column>
                <Column field="surname" header="Surname" sortable style={{minWidth: '5rem'}}></Column>
                <Column field="role" header="Role" sortable style={{minWidth: '5rem'}}></Column>
                <Column field="operations" header="Operations" style={{minWidth: '3rem'}}
                        body={operationsBodyTemplate}></Column>
            </DataTable>
        </div>
    )
}

export default UsersPage;
