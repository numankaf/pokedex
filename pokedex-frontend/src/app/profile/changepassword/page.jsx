"use client"

import {useRef, useState} from "react";
import {accountService} from "@/services";

import {Password} from "primereact/password";

import {Button} from "primereact/button";
import {Toast} from 'primereact/toast';
const ChangePasswordPage = () => {
    const toast = useRef(null);
    const [currentPassword, setCurrentPassword] = useState("");
    const [newPassword, setNewPassword] = useState("");
    const onSubmit = (e) => {
        e.preventDefault();
        accountService.changePassword({currentPassword: currentPassword, newPassword: newPassword})
            .then((res) => {
                toast.current.show({
                    severity: 'success',
                    summary: 'Success',
                    detail: 'You have changed your password',
                    life: 3000
                });

            })
            .catch((e) => {  toast.current.show({
                severity: 'error',
                summary: 'Error',
                detail: e.message,
                life: 3000
            });
            });

    };
    return (
        <div className={"card"}>
            <Toast ref={toast}/>
            <form>
                <div className="flex flex-column lg:px-5 lg:mx-5 ">
                    <div className="flex flex-column align-items-center justify-content-center">
                        <div className="mb-4 pt-2  " style={{"width": "40%"}}>
                            <label htmlFor="currentPassword" className="block text-base font-medium mb-2">Current Password</label>
                            <div className="p-input-icon-left inline">
                                {/* eslint-disable-next-line react/jsx-no-undef */}
                                <i className="pi pi-envelope pr-2"></i>
                                <Password inputStyle={{width: "100%", "padding": "1rem"}}
                                          value={currentPassword}
                                          onChange={(e) => setCurrentPassword(e.target.value)}
                                          style={{width: "100%"}} toggleMask feedback={false}
                                          className="w-full " placeholder="Current Password"/>
                            </div>
                        </div>
                        <div className="mb-4 pt-2  " style={{"width": "40%"}}>
                            <label htmlFor="newPassword" className="block text-base font-medium mb-2">New Password</label>
                            <div className="p-input-icon-left inline">
                                {/* eslint-disable-next-line react/jsx-no-undef */}
                                <Password inputStyle={{width: "100%", "padding": "1rem"}}
                                          value={newPassword}
                                          onChange={(e) => setNewPassword(e.target.value)}
                                          style={{width: "100%"}} toggleMask feedback={false}
                                          className="w-full " placeholder="New Password"/>
                            </div>
                        </div>
                    </div>

                </div>
                <div className="flex align-items-center justify-content-center">
                    <Button  style={{"width": "25%"}} label="Submit"
                             onClick={onSubmit}
                             className="mb-4 p-3"/>
                </div>
            </form>

        </div>

    )
}
export default ChangePasswordPage;
