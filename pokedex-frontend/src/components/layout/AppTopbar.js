"use client"

import React, {useContext, useEffect, useRef, useState} from 'react';
import {OverlayPanel} from 'primereact/overlaypanel';
import {Button} from 'primereact/button';
import Link from "next/link";
import {accountService, authService} from "@/services";
import {InputSwitch} from "primereact/inputswitch";


const AppTopbar = ({toggle}) => {
    const [user, setUser] = useState(null)

    useEffect(() => {
        accountService.getAccountDetailTopbar()
            .then((res) => {
                setUser(res.data)
            }).catch((e) => alert(e.message));

    }, [])


    const onLogout = () => {
        authService.logout()
        window.location.href = "/auth/login"

    };

    const op = useRef(null);
    if (!user) return <p>No profile data</p>
    return (
        <>
            <div
                className="py-3 px-3 mx-0 surface-card flex align-items-center justify-content-between relative lg:static">
                <img src="/images/pokedexlogo.png" alt="logo" width="30">
                </img>
                <span className="px-2  mr-5 text-3xl">POKEDEX</span>
                <div className="px-6">
                    <div  className="bars">
                        <Button icon="pi pi-bars"  aria-label="Sidebar" onClick={toggle} />
                    </div>
                </div>
                <div className="align-items-center  justify-content-between  lg:flex
                lg:static w-full  px-6 lg:px-0 z-2">
                    <section></section>
                    <div>
                        <div className={"flex gap-3 align-items-center"}>
                            <div className={"flex flex-column justify-content-end align-items-end "}>
                                <span className={"text-color text-base font-semibold"}>{user.username}</span>
                                <span className="text-color text-base ">{user.role}</span>
                            </div>
                            <div onClick={(e) => op.current.toggle(e)}>
                                <img alt="avatar" src={user.thumbnail}
                                     className="profile-avatar cursor-pointer"/>
                            </div>


                        </div>
                    </div>

                </div>
            </div>
            <OverlayPanel ref={op}>
                <div className={"flex flex-column align-items-start justify-content-start"}>
                    <div className={"overlay-item pr-5 pl-2 py-2 w-full"}>
                        <Link href={"/profile"}>
                            <span className="pi pi-user pr-2"></span>
                            <span className="text-color text-base"> Profile</span>
                        </Link>
                    </div>
                    {/*<div*/}
                    {/*    className={"cursor-pointer overlay-item pl-2 pr-2  py-2 w-full flex align-items-center justify-content-center"}>*/}
                    {/*    <span className="pi pi-moon  pr-2"></span>*/}
                    {/*    <span className="text-color text-base pr-5"> Dark Mode</span>*/}
                    {/*    <InputSwitch checked={checked} onChange={(e) => setChecked(e.value)}/>*/}
                    {/*</div>*/}
                    <div onClick={onLogout} className={"cursor-pointer overlay-item pl-2 pr-5 py-2 w-full"}>
                        <span className="pi pi-sign-out pr-2"></span>
                        <span className="text-color text-base"> Logout</span>
                    </div>
                </div>
            </OverlayPanel>

        </>
    )
}

export default AppTopbar;
