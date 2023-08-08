export  class AuthService {
    constructor(url) {
        this.baseUrl = url;
    }
    login = async (credentials) => {
        const res = await fetch(`${this.baseUrl}/auth/login`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(credentials),
        })
        const data = await res.json();
        console.log(data)
        if (!res.ok) {

            throw new Error(data.message);
        }
        return {
            username: data.username,
            token: data.token,
            role : data.role
        }
    }

    register = async (credentials) => {
        const res = await fetch(`${this.baseUrl}/auth/register`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(credentials),
        })
        const data = await res.json();
        if (!res.ok) {
            throw new Error(data.message);
        }
        return data;
    }


}

