"use client";
import {InputText} from "primereact/inputtext";
import {Password} from 'primereact/password';
import {Button} from "primereact/button";
import Link from "next/link";
import React, {useState} from "react";
import {authService} from "@/services";

const RegisterPage = () => {
    const [input, setInput] = useState({
        name: '',
        surname: '',
        username: '',
        email: '',
        password: '',
    });

    const onInputChange = (e) => {
        const {name, value} = e.target;
        setInput((prev) => ({
            ...prev,
            [name]: value,
        }));
        console.log(input);
    };

    const onSubmit = () => {
        authService.register(input)
            .then((res) =>
                window.location.href = "/main")
            .catch((e) => alert(e.message));

    };
    return (

        <div className="flex flex-wrap" style={{"width": "100vw", "height": "100vh"}}>
            <div className="w-full lg:w-5"
                 style={{backgroundImage: "url('/images/background.jpg')", backgroundSize: "100% "}}>

                <div className="p-3">

                    <div className="flex align-items-center justify-content-center cursor-pointer"
                         style={{"width": "min-content"}}>
                        <img
                            src="/images/pokedexlogo.png"
                            alt="Pokedex Logo"
                            width="35"
                            height="35"
                            className="ml-2"
                        />
                        <Link href={"/"}><span className="px-2 text-3xl text-white">POKEDEX</span></Link>
                    </div>

                </div>

            </div>
            <div className="w-full lg:w-7 px-4 lg:px-7 surface-card">
                <div style={{"position": "relative", "top": " 50%", "transform": "translateY(-50%)"}}>
                    <div className="text-center font-medium text-2xl pt-5 pb-3">Sign Up</div>
                    <div className="text-center text-lg"> {"Already have an account? "}
                        <Link href={"/auth/login"}><p
                            className="text-primary cursor-pointer underline-link"> Login </p></Link>

                    </div>
                    <form>
                        <div className="flex flex-column lg:px-5 lg:mx-5 ">
                            <div className="flex flex-column align-items-center justify-content-center">
                                <div className="mb-3 pt-2  " style={{"width": "90%"}}>
                                    <div className="py-3 flex justify-content-between flex-wrap">
                                    <span style={{"width": "49%"}}>
                                        <label htmlFor="name"
                                               className="block text-base font-medium mb-2">Name</label>
                                        <div className=" inline">
                                            <InputText id="name" placeholder="Name"
                                                       type={"text"}
                                                       name={"name"}
                                                       value={input.name}
                                                       onChange={onInputChange}
                                                       className="w-full py-2 "/>
                                        </div>
                                    </span>
                                        <span style={{"width": "49%"}}>
                                        <label htmlFor="surname"
                                               className="block text-base font-medium mb-2">Surname</label>
                                        <div className=" inline">
                                            <InputText id="surname" placeholder="Surname"
                                                       name={"surname"}
                                                       value={input.surname}
                                                       onChange={onInputChange}
                                                       className="w-full py-2 "/>
                                        </div>
                                    </span>
                                    </div>
                                    <label htmlFor="username"
                                           className="block text-base font-medium mb-2">Username</label>
                                    <div className="inline">
                                        <InputText id="username" placeholder="Username"
                                                   name={"username"}
                                                   value={input.username}
                                                   onChange={onInputChange}
                                                   aria-describedby="username-help" className="w-full py-2 "/>
                                    </div>
                                </div>
                                <div className="mb-3 pt-2  " style={{"width": "90%"}}>
                                    <label htmlFor="email" className="block text-base font-medium mb-2">Email</label>
                                    <div className="p-input-icon-left inline">
                                        {/* eslint-disable-next-line react/jsx-no-undef */}
                                        <i className="pi pi-envelope pr-2"></i>
                                        <InputText id="email" placeholder="Email"
                                                   name={"email"}
                                                   value={input.email}
                                                   onChange={onInputChange} className="w-full py-2 "/>
                                    </div>
                                </div>
                                <div className="mb-4 pt-2  " style={{"width": "90%"}}>
                                    <label htmlFor="password"
                                           className="block text-base font-medium mb-2">Password</label>
                                    <div className="p-input-icon-left inline">
                                        {/* eslint-disable-next-line react/jsx-no-undef */}
                                        <Password inputStyle={{width: "100%", "padding": "0.6rem"}}
                                                  name={"password"}
                                                  value={input.password}
                                                  onChange={onInputChange}
                                                  style={{width: "100%"}} toggleMask feedback={false}
                                                  className="w-full " placeholder="Password"/>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div className="flex align-items-center justify-content-center">
                            <Button type={"submit"} style={{"width": "65%"}} label="Register"
                                    onClick={onSubmit}
                                    className="mb-4 p-3"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default RegisterPage;
