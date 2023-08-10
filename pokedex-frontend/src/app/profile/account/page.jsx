"use client"

import React, {useEffect, useState} from "react";
import {accountService} from "@/services";

const AccountPage = () =>{
    const [edit, setEdit] = useState(false);
    const [user, setUser] = useState({types: []});
    useEffect(() => {
        getPokemon();
    }, []);

    const getPokemon = async () => {
        await accountService.getAccountDetail().then((data) => setUser(data))
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
            };
            reader.readAsDataURL(file);
        }
    };
    return (
        <div className={"card"}>
            <div className={"flex gap-3"}>
                <div>
                    {edit &&<input
                        type="file" accept="image/*" onChange={handleImageUpload} ref={imageUploader}
                        style={{
                            display: "none"
                        }}
                    />}
                    <div className={"w-15rem h-15rem"}
                         onClick={() => edit ? imageUploader.current.click():""}>
                        <img ref={uploadedImage}
                             src={user.thumbnail}
                             className={"border-circle"}
                             style={{
                                 width: "inherit",
                                 height: "inherit",
                                 position: "absolute"
                             }}
                        />
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AccountPage;
