"use client"

import React, {useEffect, useRef, useState} from 'react';
import {OverlayPanel} from 'primereact/overlaypanel';
import {Button} from 'primereact/button';
import Link from "next/link";
import {accountService} from "@/services";

const AppTopbar = () => {

    const [user, setUser] = useState(null)

    useEffect(() => {
        accountService.getAccountDetailTopbar().then((res) => res.json())
            .then((data) => {
                console.log(data);
                setUser(data)
            }).catch((e) => alert(e.message));

    }, [])

    const op = useRef(null);
    if (!user) return <p>No profile data</p>
    return (
        <div>
            <div
                className="py-3 px-3 mx-0 surface-card flex align-items-center justify-content-between relative lg:static">
                <img src="/images/pokedexlogo.png" alt="logo" width="30">
                </img>
                <span className="px-2  mr-5 text-3xl">POKEDEX</span>
                <div className="align-items-center flex-grow-1 justify-content-between hidden lg:flex
                lg:static w-full  left-0 top-100 px-6 lg:px-0 z-2lg:shadow-none">
                    <section></section>
                    <div onClick={(e) => op.current.toggle(e)}>
                        <img alt="avatar" src={user.thumbnail}
                             className="profile-avatar cursor-pointer"/>
                    </div>
                    <OverlayPanel ref={op}>
                        <img src={'https://primefaces.org/cdn/primereact/images/product/bamboo-watch.jpg'}
                             alt="Bamboo Watch"></img>
                    </OverlayPanel>
                </div>
            </div>

        </div>
    )
}

export default AppTopbar;
