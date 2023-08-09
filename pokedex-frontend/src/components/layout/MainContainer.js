"use client"
import {useState} from "react";
import AppTopbar from "@/components/layout/AppTopbar";
import AppSidebar from "@/components/layout/AppSidebar";

const MainContainer =({children, menuItem}) =>{
    const[isSidebarOpen ,setIsSidebarOpen] = useState(true);

    const toggle = () =>{
        setIsSidebarOpen(!isSidebarOpen);
    }

    return (
        <div>
            <AppTopbar toggle={toggle}></AppTopbar>
            <div className={"flex flex-row "} style={{paddingTop:"5rem"}}>
                <AppSidebar menuItem={menuItem} isOpen={isSidebarOpen}  ></AppSidebar>
                <div style={{"padding" :"1rem 0rem"}} className= {isSidebarOpen ? " main-wrapper-open" : "w-full px-3 "}>
                    {children}
                </div>
            </div>
        </div>
    )
}

export default MainContainer;
