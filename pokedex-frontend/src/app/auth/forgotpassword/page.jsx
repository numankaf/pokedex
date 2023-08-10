"use client";
import {InputText} from "primereact/inputtext";
import {Button} from "primereact/button";
import Link from "next/link";
import React, {useRef, useState} from "react";
import {authService} from "@/services";
import {Toast} from "primereact/toast";
import { Dialog } from 'primereact/dialog';
const ForgotPasswordPage = () => {
    const toast = useRef(null);
    const [loading, setLoading] = useState(false);
    const [visible, setVisible] = useState(false);
    const [input, setInput] = useState({
        email: ''
    });
    const [error, setError] = useState({
        email: '  '
    });
    const onInputChange = (e) => {
        const {name, value} = e.target;
        setInput((prev) => ({
            ...prev,
            [name]: value,
        }));
        validateInput(e);
    };

    const validateInput = e => {
        let { name, value } = e.target;
        setError(prev => {
            const stateObj = { ...prev, [name]: "" };
            switch (name) {
                case "email":
                    if (!value) {
                        stateObj[name] = "Please enter your email.";
                    }else if( !(/\S+@\S+\.\S+/.test(input.email))){
                        stateObj[name] = "Email is invalid.";
                    }
                    break;
                default:
                    break;
            }
            return stateObj;
        });
    }

    const onSubmit = (e) => {
        setLoading(true)
        e.preventDefault();
        authService.forgotPassword(input)
            .then((res) => {
                setVisible(true);
                setLoading(false);
            })
            .catch((e) => {
                toast.current.show({
                    severity: 'error',
                    summary: 'Error',
                    detail: e.message,
                    life: 3000
                });
            });
        setLoading(false);

    };


    return (

        <div className={"flex align-items-center justify-content-center w-screen h-screen"} >
            <Dialog header="Check your Email" visible={visible} style={{ width: '50vw' }} onHide={() => setVisible(false)} closable={false}>
               <div className={"flex flex-column gap-3 align-items-center justify-content-center"}>
                   <img  src="/images/checkmark.png" width={300} height={300}/>
                   <p className="m-0">
                       A new password has been generated and sent to your registered email address.
                       Please check your inbox and follow the provided instructions to securely reset
                       your password and regain access to your account.
                   </p>
                   <Link href={"/auth/login"}>
                       <p className="text-primary cursor-pointer text-lg underline-link">Back to Login</p>
                   </Link>
               </div>
            </Dialog>
            <div className="w-4 ">
                <Toast ref={toast}/>
                <div className={"card"}>
                    <form>
                        <div className="flex flex-column lg:px-5 lg:mx-5 ">
                            <div className="flex flex-column align-items-center justify-content-center">
                                <div className="mb-4 pt-2  " style={{"width": "90%"}}>
                                    <h1 className={"font-bold text-2xl mt-0 mb-2"}>Forgot Password?</h1>
                                    <p className={"text-color-secondary mb-4"}>We need your email address for you can access your account,
                                        then we’ll send your new password to your email.</p>
                                    <label htmlFor="email" className="block text-base font-medium mb-2">Enter your Email</label>
                                    <div className="p-input-icon-left inline">
                                        {/* eslint-disable-next-line react/jsx-no-undef */}
                                        <i className="pi pi-envelope pr-2"></i>
                                        <InputText id="email" placeholder="Email"
                                                   name={"email"}
                                                   value={input.email}
                                                   onBlur={validateInput}
                                                   onChange={onInputChange}
                                                   aria-describedby="username-help" className="w-full py-3 "/>
                                    </div>
                                    {error.email && <span className={"text-red-500"}>{error.email}</span>}
                                </div>
                            </div>
                        </div>

                        <div className="flex align-items-center justify-content-center">
                            <Button type={"submit"} style={{"width": "70%"}} label="Send Email"
                                    onClick={onSubmit} disabled={loading || error.email}
                                    className="mb-4 p-3"/>
                        </div>
                        <div className="flex justify-content-center flex-wrap" style={{"width": "100%"}}>
                            <span className="mb-4">
                                    <div className=" inline">
                                      <Link href={"/auth/login"}>
                                          <p className="text-primary cursor-pointer text-lg underline-link">Back to Login</p>
                                      </Link>
                                        </div>
                                  </span>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default ForgotPasswordPage;
