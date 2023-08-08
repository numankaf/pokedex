import {getAuthorizationHeader} from "@/utils/getAutherizationHeader";

export class AccountService {
    constructor(url) {
        this.baseUrl = url;
    }

    getAccountDetailTopbar = async () => {
        console.log(getAuthorizationHeader().Authorization)
        const res = await fetch(`${this.baseUrl}/account/topbar`, {
            method: "GET",
            headers: {
                'Content-Type': 'application/json',
                ...getAuthorizationHeader(),
            },
        })
        const data = await res.json();
        if (!res.ok) {
            throw new Error(data.message);
        }
        return data;
    }


}

