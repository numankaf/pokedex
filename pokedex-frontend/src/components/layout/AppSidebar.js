"use client"
import Link from "next/link";
import {usePathname} from "next/navigation";

const AppSidebar = ({isOpen, menuItem}) => {
    const currentRoute = usePathname();


    return (
        <div className={isOpen ? "sidebar-open" : "sidebar-close"}>
            <div className="flex sidebar-container ">
                <div style={{width: isOpen ? "250px" : "0px"}} className="sidebar shadow-2 ">

                    {
                        menuItem.map((item, index) => (

                            <Link href={item.path} key={index}>
                                <div
                                     className={currentRoute=== item.path ? "link-active flex gap-2 flex-row sidebar-item" : "flex gap-2 flex-row sidebar-item"}>
                                    <div className="icon">{item.icon}</div>
                                    <div className="">{item.name}</div>
                                </div>
                            </Link>
                        ))
                    }
                </div>
            </div>
        </div>
    )
}


export default AppSidebar;
