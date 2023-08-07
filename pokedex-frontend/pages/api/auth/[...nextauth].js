import NextAuth from "next-auth"
import CredentialsProvider from "next-auth/providers/credentials";
export const authOptions = {
    providers: [
        CredentialsProvider({
            name: "Credentials",

            credentials: {
                username: { label: "Username", type: "text", placeholder: "jsmith" },
                password: { label: "Password", type: "password" }
            },
            async authorize(credentials, req) {

                const {username, password} = credentials;
                const res = await fetch("http://localhost:8080/pokedex/auth/login", {
                    method: "POST",
                    headers: {
                        "Content-Type":"application/json",
                    },
                    body: JSON.stringify({
                        username,password
                    }),
                });
                console.log(res);
                const user = await res.json();
                    if(res.ok && user){
                        console.log(user);
                        console.log("Success")
                        return user;
                    }
                    else{
                        console.log("this area is null")
                        return null;
                    }
            }
        })
    ],

    session: {
        strategy: "jwt"
    },

    pages: {
        signIn :"/auth/login"
    }
}

export default NextAuth(authOptions)
