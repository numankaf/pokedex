"use client"

import React, {useEffect, useRef, useState} from "react";
import {accountService} from "@/services";
import {Button} from "primereact/button";
import {Toast} from "primereact/toast";
import {InputText} from "primereact/inputtext";
import {InputTextarea} from 'primereact/inputtextarea';

const AccountPage = () => {
    const toast = useRef(null);
    const [edit, setEdit] = useState(false);
    const [user, setUser] = useState({});
    const [editData, setEditData] = useState({
        name: '',
        surname: '',
        about: '',
        thumbnail: ''
    })
    useEffect(() => {
        getPokemon();
    }, []);

    const getPokemon = async () => {
        await accountService.getAccountDetail().then((data) => {
            setUser(data);
            setEditData({
                name: data.name,
                surname: data.surname,
                about: data.about,
                thumbnail: data.thumbnail
            })
        })
    }

    const uploadedImage = React.useRef(null);
    const imageUploader = React.useRef(null);

    const handleImageUpload = e => {
        const [file] = e.target.files;
        if (file) {
            const reader = new FileReader();
            const {current} = uploadedImage;
            current.file = file;
            reader.onload = e => {
                current.src = e.target.result;
                setEditData({...editData, thumbnail: current.src})
            };
            reader.readAsDataURL(file);
        }
    };

    const onSubmit =() =>{
       accountService.update(editData).then((res) =>{
           window.location.reload();
       }).catch((e) =>{
           toast.current.show({
               severity: 'error',
               summary: 'Error',
               detail: e.message,
               life: 3000
           });
       })
    }

    return (
        <div className={"card"}>
            <Toast ref={toast}/>
            <div className={"flex gap-5"}>
                <div className={"my-5 py-5"}>
                    {edit && <input
                        type="file" accept="image/*" onChange={handleImageUpload} ref={imageUploader}
                        style={{
                            display: "none"
                        }}
                    />}
                    <div className={"w-15rem h-15rem"}
                         onClick={() => edit ? imageUploader.current.click() : ""}>
                        <img ref={uploadedImage}
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
                    <div className={"flex justify-content-end"}>
                        {!edit && <Button label="Edit" icon={"pi pi-pencil"} severity={"info"}
                                          onClick={() => setEdit(true)}/>}
                        {edit && <div className={"flex flex-row gap-2"}>
                            <Button label="Cancel" severity={"danger"} icon={"pi pi-times"}
                                    onClick={() => {
                                        setEdit(false)
                                        window.location.reload();
                                    }}/>
                            <Button label="Save" severity={"success"} icon={"pi pi-check" }
                                    onClick={()=>onSubmit()}
                            />
                        </div>}
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
                                                       disabled={!edit}
                                                       value={editData.name || ''}
                                                       onChange={(e) => setEditData({
                                                           ...editData,
                                                           name: e.target.value
                                                       })}
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
                                                       disabled={!edit}
                                                       value={editData.surname || ''}
                                                       onChange={(e) => setEditData({
                                                           ...editData,
                                                           surname: e.target.value
                                                       })}
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
                                                       name={"email"}
                                                       disabled={true}
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
                                           name={"about"}
                                           disabled={!edit}
                                           value={editData.about || ''}
                                           onChange={(e) => setEditData({...editData, about: e.target.value})}
                                           className="w-full py-2 "/>
                        </div>


                    </div>
                </div>
            </div>
        </div>
    )
}

export default AccountPage;
