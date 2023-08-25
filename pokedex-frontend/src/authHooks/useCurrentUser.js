import {useEffect, useState} from "react";
import {getCookie} from "cookies-next";
export const useCurrentUser = () =>{
    const [user, setUser] = useState(null);
    useEffect(() => {
        const currentUser = getCookie("currentUser");
        if (currentUser) {
            setUser(JSON.parse(currentUser));
        }
    }, []);


    return user;

}
