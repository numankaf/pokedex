"use client";
import {InputText} from "primereact/inputtext";
import {Password} from 'primereact/password';
import {Button} from "primereact/button";
import Link from "next/link";
import {useState} from "react";
import {useLogin} from "@/authHooks/useLogin";

const LoginPage = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const { login } = useLogin();
    const onSubmit = () => {
        if (!username || !password) {
            alert("Please enter information");
        } else {
            login( { username: username, password: password})
                .then((res) => {
                    if(res.role ==='TRAINER'){
                        console.log(res.role)
                        window.location.href = "/main"
                    }
                    if(res.role ==='ADMIN'){
                        window.location.href = "/admin"
                    }
                })
                .catch((e) => alert(e.message));
        }
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
                    <div className="text-center font-medium text-2xl pt-5 pb-3">Login</div>
                    <div className="text-center text-lg"> {"Don't have an account? "}
                        <Link href={"/auth/register"}><p className="text-primary cursor-pointer underline-link">Sign
                            up </p></Link>

                    </div>
                    <form>
                        <div className="flex flex-column lg:px-5 lg:mx-5 ">
                            <div className="flex flex-column align-items-center justify-content-center">
                                <div className="mb-4 pt-2  " style={{"width": "90%"}}>
                                    <label htmlFor="email" className="block text-base font-medium mb-2">Username or
                                        Email</label>
                                    <div className="p-input-icon-left inline">
                                        {/* eslint-disable-next-line react/jsx-no-undef */}
                                        <i className="pi pi-envelope pr-2"></i>
                                        <InputText id="username" placeholder="Username or Email"
                                                   value={username}
                                                   onChange={(e) => setUsername(e.target.value)}
                                                   aria-describedby="username-help" className="w-full py-3 "/>
                                    </div>
                                </div>
                                <div className="mb-4 pt-2  " style={{"width": "90%"}}>
                                    <label htmlFor="password" className="block text-base font-medium mb-2">Password</label>
                                    <div className="p-input-icon-left inline">
                                        {/* eslint-disable-next-line react/jsx-no-undef */}
                                        <Password inputStyle={{width: "100%", "padding": "1rem"}}
                                                  value={password}
                                                  onChange={(e) => setPassword(e.target.value)}
                                                  style={{width: "100%"}} toggleMask feedback={false}
                                                  className="w-full " placeholder="Password"/>
                                    </div>
                                </div>
                            </div>
                            <div className="flex justify-content-between flex-wrap" style={{"width": "95%"}}>
                                <div></div>
                                <span className="mb-4">
                                    <div className=" inline">
                                      <Link href={"/auth/forgotpassword"}>
                                          <p className="text-primary cursor-pointer text-lg underline-link">Forgot Password?</p>
                                      </Link>
                                        </div>
                                  </span>
                            </div>

                        </div>
                        <div className="flex align-items-center justify-content-center">
                            <Button type={"submit"} style={{"width": "65%"}} label="Log in"
                                    onClick={onSubmit}
                                    className="mb-4 p-3"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default LoginPage;
