"use client"
import {DataTable} from 'primereact/datatable';
import {Column} from 'primereact/column';
import React, {useEffect, useRef, useState} from "react";
import {userService} from "@/services";
import {Button} from 'primereact/button';
import {Dialog} from 'primereact/dialog';
import {usePathname} from "next/navigation";
import {useRouter} from "next/navigation";
import {InputText} from 'primereact/inputtext';
import {ConfirmDialog, confirmDialog} from 'primereact/confirmdialog';
import {Toast} from 'primereact/toast';
import {Password} from "primereact/password";
import {Dropdown} from "primereact/dropdown";

const UsersPage = () => {
    const toast = useRef(null);
    const pathname = usePathname();
    const router = useRouter();
    const [visible, setVisible] = useState(false);
    const [users, setUsers] = useState(null);
    const [loading, setLoading] = useState(false);
    const [totalRecords, setTotalRecords] = useState(0);
    const roles = [{name: "ADMIN"}, {name: "TRAINER"}]
    const [lazyState, setlazyState] = useState({
        first: 0,
        rows: 5,
        page: 0,
        sortField: null,
        sortOrder: null,
        filters: {
            name: {value: ''},
            surname: {value: ''},
            email: {value: ''},
            username: {value: ''}
        }
    });

    const [createUser, setCreateUser] = useState({
        username: '',
        password: '',
        email: '',
        name: '',
        surname: '',
        role: ''
    })

    const dt = useRef(null);
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
            const sort = lazyState.sortField ? (lazyState.sortField + ',' + (lazyState.sortOrder === 1 ? 'asc' : 'desc')) : undefined;

            const isFilterTrue = lazyState.filters && ((lazyState.filters.name && lazyState.filters.name.value && lazyState.filters.name.value !== '')
            || (lazyState.filters.surname && lazyState.filters.surname.value && lazyState.filters.surname.value !== '')
            || (lazyState.filters.email && lazyState.filters.email.value && lazyState.filters.email.value !== '')
            || (lazyState.filters.username && lazyState.filters.username.value && lazyState.filters.username.value !== ''));
            if (isFilterTrue) {
                const searchDto = {
                    name: lazyState.filters.name.value || '',
                    surname: lazyState.filters.surname.value || '',
                    email: lazyState.filters.email.value || '',
                    username: lazyState.filters.username.value || '',
                };
                userService.search(searchDto, (lazyState.first / lazyState.rows), lazyState.rows, sort).then((data) => {
                    setTotalRecords(data.totalElements);
                    setUsers(data.content);
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
                first: event.first,
                filters: {}
            }
        )
    };

    const onFilter = (event) => {
        event['first'] = 0;
        setlazyState(event);
    };


    const thumbnailBodyTemplate = (rowData) => {
        return <img className={"border-circle"} src={rowData.thumbnail} width={40} height={40}/>;
    };


    const goToDetail = (id) => {
        router.push(pathname + "/" + id)
    }
    const goToEdit = (id) => {
        router.push(pathname + "/edit/" + id)
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
    const onUserCreate = () => {
        userService.createUser({
            username: createUser.username,
            password: createUser.password,
            email: createUser.email,
            name: createUser.name,
            surname: createUser.surname,
            role: createUser.role.name
        }).then((res) => {
            toast.current.show({
                severity: 'success',
                summary: 'Success',
                detail: 'You have created a user',
                life: 3000
            });
            loadLazyData();
            setVisible(false);
        }).catch((e) => {
            setVisible(false);
            toast.current.show({
                severity: 'error',
                summary: 'Error',
                detail: e.message,
                life: 3000
            });
        })
    }

    const dialogFooterContent = (
        <div>
            <Button label="Cancel" icon="pi pi-times" severity={"danger"} onClick={() => setVisible(false)}
                    className="p-button-text"/>
            <Button label="Save" icon="pi pi-check" severity={"success"} onClick={onUserCreate} autoFocus/>
        </div>
    );

    return (
        <div className={"card"}>
            <Toast ref={toast}/>
            <ConfirmDialog/>
            <Dialog header="Create User" visible={visible} style={{width: '50vw'}} onHide={() => setVisible(false)}
                    footer={dialogFooterContent}>
                <form>
                    <div className="flex flex-column lg:px-5 lg:mx-5 ">
                        <div className="flex flex-column align-items-center justify-content-center">
                            <div className="mb-4 pt-2  " style={{"width": "90%"}}>
                                <div className="py-3 flex justify-content-between flex-wrap">
                                    <span style={{"width": "49%"}}>
                                        <label htmlFor="name"
                                               className="block text-base font-medium mb-2">Name</label>
                                        <div className=" inline">
                                            <InputText id="name" placeholder="Name"
                                                       type={"text"}
                                                       name={"name"}
                                                       value={createUser.name}
                                                       onChange={(e) => setCreateUser({
                                                           ...createUser,
                                                           name: e.target.value
                                                       })}
                                                       className="w-full py-2 "/>
                                        </div>
                                    </span>
                                    <span style={{"width": "49%"}}>
                                        <label htmlFor="surname"
                                               className="block text-base font-medium mb-2">Surname</label>
                                        <div className=" inline">
                                            <InputText id="surname" placeholder="Surname"
                                                       name={"surname"}
                                                       value={createUser.surname}
                                                       onChange={(e) => setCreateUser({
                                                           ...createUser,
                                                           surname: e.target.value
                                                       })}
                                                       className="w-full py-2 "/>
                                        </div>
                                    </span>
                                </div>
                                <label htmlFor="username" className="block text-base font-medium mb-2">Username </label>
                                <div className="inline">
                                    {/* eslint-disable-next-line react/jsx-no-undef */}
                                    <InputText id="username" placeholder="Username"
                                               value={createUser.username}
                                               onChange={(e) => setCreateUser({
                                                   ...createUser,
                                                   username: e.target.value
                                               })}
                                               aria-describedby="username-help" className="w-full  "/>
                                </div>
                                <label htmlFor="email" className="block text-base font-medium mb-2 pt-3">Email </label>
                                <div className="p-input-icon-left inline">
                                    {/* eslint-disable-next-line react/jsx-no-undef */}
                                    <i className="pi pi-envelope pr-2"></i>
                                    <InputText id="email" placeholder="Email"
                                               value={createUser.email}
                                               onChange={(e) => setCreateUser({...createUser, email: e.target.value})}
                                               aria-describedby="username-help" className="w-full  "/>
                                </div>

                                <label htmlFor="role" className="block text-base font-medium mb-2 pt-3">Role </label>
                                <div className="inline">
                                    {/* eslint-disable-next-line react/jsx-no-undef */}
                                    <Dropdown value={createUser.role}
                                              onChange={(e) => setCreateUser({...createUser, role: e.target.value})}
                                              options={roles} optionLabel="name"
                                              placeholder="Select a role" className="w-full "/>
                                </div>
                            </div>
                            <div className="mb-4 pt-2  " style={{"width": "90%"}}>
                                <label htmlFor="password"
                                       className="block text-base font-medium mb-2">Password</label>
                                <div className="p-input-icon-left inline">
                                    {/* eslint-disable-next-line react/jsx-no-undef */}
                                    <Password inputStyle={{width: "100%"}}
                                              value={createUser.password}
                                              onChange={(e) => setCreateUser({...createUser, password: e.target.value})}
                                              style={{width: "100%"}} toggleMask feedback={false}
                                              className="w-full " placeholder="Password"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </Dialog>
            <h1>User List</h1>
            <div className={"flex flex-column  py-3 "}>
                <div className={"flex  justify-content-between"}>
                    <div></div>
                    <div>
                        <Button size="small" icon="pi pi-plus" severity="success" label={"Create a User"}
                                onClick={() => setVisible(true)}
                                tooltip={"Create a User"}/>
                    </div>
                </div>

            </div>
            <DataTable value={users} lazy loading={loading} filterDisplay="row" paginator
                       onPage={onPage} totalRecords={totalRecords} first={lazyState.first} rows={lazyState.rows}
                       onSort={onSort} sortField={lazyState.sortField} sortOrder={lazyState.sortOrder}
                       onFilter={onFilter} filters={lazyState.filters}
                       paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown"
                       currentPageReportTemplate="Showing {first} to {last} of {totalRecords} entries"
                       dataKey="id" rowsPerPageOptions={[5, 10, 25]}>
                <Column field="thumbnail" header="Image" style={{minWidth: '3rem'}}
                        body={thumbnailBodyTemplate}></Column>
                <Column field="username" header="Username" filter showFilterMenu={false}
                        filterPlaceholder="Search by username" sortable
                        style={{minWidth: '7rem'}}></Column>
                <Column field="email" header="Email" filter showFilterMenu={false} filterPlaceholder="Search by email"
                        sortable
                        style={{minWidth: '7rem'}}></Column>
                <Column field="name" header="Name" filter showFilterMenu={false} filterPlaceholder="Search by name"
                        sortable
                        style={{minWidth: '5rem'}}></Column>
                <Column field="surname" header="Surname" showFilterMenu={false} filterPlaceholder="Search by surname"
                        filter sortable
                        style={{minWidth: '5rem'}}></Column>
                <Column field="role" header="Role" sortable style={{minWidth: '5rem'}}></Column>
                <Column field="operations" header="Operations" style={{minWidth: '3rem'}}
                        body={operationsBodyTemplate}></Column>
            </DataTable>
        </div>
    )
}

export default UsersPage;
