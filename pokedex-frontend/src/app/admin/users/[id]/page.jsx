"use client"

import React, {useEffect, useState} from "react";
import {userService} from "@/services";
import {InputText} from "primereact/inputtext";
import {InputTextarea} from 'primereact/inputtextarea';
import {useRouter} from "next/navigation";

const UserDetailPage = ({params}) => {
    const router = useRouter()
    const id = params.id;
    const [user, setUser] = useState({});

    useEffect(() => {
        getUser();
    }, []);

    const getUser = async () => {
        await userService.getById(id).then((data) => {
            setUser(data);

        })
    }

    return (
        <div className={"card"}>
            <a className={"underline-link text-primary cursor-pointer"} onClick={()=>router.back()}>Back to List</a>
            <div className={"flex gap-5"}>
                <div className={"my-5 py-5"}>
                    <div className={"w-15rem h-15rem"}>
                        <img
                             src={user.thumbnail}
                             className={"border-circle img-input"}
                             style={{
                                 width: "inherit",
                                 height: "inherit",
                                 position: "absolute"
                             }}
                        />
                    </div>
                </div>
                <div className={"w-full"}>
                    <div className=" pt-2  " style={{"width": "90%"}}>
                        <div className="py-3 flex justify-content-between flex-wrap">
                                    <span style={{"width": "24%"}}>
                                        <label htmlFor="createdby"
                                                   className="block text-base font-medium mb-2">Created By</label>
                                        <div className=" inline">
                                            <InputText id="createdby" placeholder="createdby"
                                                       type={"text"}
                                                       name={"createdby"}
                                                       disabled={true}
                                                       value={user.createdBy || ''}
                                                       className="w-full py-2 "/>
                                        </div>
                                    </span>
                            <span style={{"width": "24%"}}>
                                        <label htmlFor="createdDate"
                                               className="block text-base font-medium mb-2">Created Date</label>
                                        <div className=" inline">
                                            <InputText id="createdDate" placeholder="Surname"
                                                       type={"text"}
                                                       name={"createdDate"}
                                                       disabled={true}
                                                       value={user.createdDate || ''}
                                                       className="w-full py-2 "/>
                                        </div>
                                    </span>
                            <span style={{"width": "24%"}}>
                                        <label htmlFor="lastModifiedBy"
                                               className="block text-base font-medium mb-2">Last Modified By</label>
                                        <div className=" inline">
                                            <InputText id="lastModifiedBy" placeholder="lastModifiedBy"
                                                       type={"text"}
                                                       name={"lastModifiedBy"}
                                                       disabled={true}
                                                       value={user.lastModifiedBy || ''}
                                                       className="w-full py-2 "/>
                                        </div>
                                    </span>
                            <span style={{"width": "24%"}}>
                                        <label htmlFor="lastModifiedDate"
                                               className="block text-base font-medium mb-2">Last Modified Date</label>
                                        <div className=" inline">
                                            <InputText id="lastModifiedDate" placeholder="lastModifiedDate"
                                                       type={"text"}
                                                       name={"lastModifiedDate"}
                                                       disabled={true}
                                                       value={user.lastModifiedDate || ''}
                                                       className="w-full py-2 "/>
                                        </div>
                                    </span>
                        </div>

                    </div>
                    <div className=" pt-2  " style={{"width": "90%"}}>
                        <div className="py-3 flex justify-content-between flex-wrap">
                                    <span style={{"width": "33%"}}>
                                        <label htmlFor="name"
                                               className="block text-base font-medium mb-2">Name</label>
                                        <div className=" inline">
                                            <InputText id="name" placeholder="Name"
                                                       type={"text"}
                                                       name={"name"}
                                                       disabled={true}
                                                       value={user.name || ''}
                                                       className="w-full py-2 "/>
                                        </div>
                                    </span>
                            <span style={{"width": "33%"}}>
                                        <label htmlFor="surname"
                                               className="block text-base font-medium mb-2">Surname</label>
                                        <div className=" inline">
                                            <InputText id="surname" placeholder="Surname"
                                                       type={"text"}
                                                       name={"surname"}
                                                       disabled={true}
                                                       value={user.surname || ''}
                                                       className="w-full py-2 "/>
                                        </div>
                                    </span>
                            <span style={{"width": "33%"}}>
                                        <label htmlFor="role"
                                               className="block text-base font-medium mb-2">Role</label>
                                        <div className=" inline">
                                            <InputText id="surname" placeholder="Role"
                                                       type={"text"}
                                                       name={"role"}
                                                       disabled={true}
                                                       value={user.role || ''}
                                                       className="w-full py-2 "/>
                                        </div>
                                    </span>
                        </div>

                    </div>
                    <div className=" pt-2  " style={{"width": "90%"}}>
                        <div className="py-3 flex justify-content-between flex-wrap">
                                    <span style={{"width": "49%"}}>
                                        <label htmlFor="username"
                                               className="block text-base font-medium mb-2">Username</label>
                                        <div className=" inline">
                                            <InputText id="username" placeholder="Username"
                                                       type={"text"}
                                                       name={"name"}
                                                       disabled={true}
                                                       value={user.username || ''}
                                                       className="w-full py-2 "/>
                                        </div>
                                    </span>
                            <span style={{"width": "49%"}}>
                                        <label htmlFor="email"
                                               className="block text-base font-medium mb-2">Email</label>
                                        <div className=" inline">
                                            <InputText id="email" placeholder="Email"
                                                       type={"text"}
                                                       name={"email"}  disabled={true}
                                                       value={user.email || ''}
                                                       className="w-full py-2 "/>
                                        </div>
                                    </span>
                        </div>

                    </div>
                    <div className=" pt-2  " style={{"width": "90%"}}>

                        <label htmlFor="about"
                               className="block text-base font-medium mb-2">About</label>
                        <div className=" inline">
                            <InputTextarea id="about"
                                           type={"text"} rows={5} cols={30}
                                           name={"about"}  disabled={true}
                                           value={user.about || ''}
                                           className="w-full py-2 "/>
                        </div>


                    </div>
                </div>
            </div>
        </div>
    )
}

export default UserDetailPage;
