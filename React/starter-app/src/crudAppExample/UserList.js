import {useEffect, useState} from "react";

const UserList = () => {

    const [users, setUsers] = useState([]);
    const [loading, setLoading] = useState(false);

    const onSubmit = async () => {
        const res = fetch("http://localhost:8080/pokedex/auth/login", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username : "ashketchum",
                password : "password"
            }),
        }).then(res => { return res.json()});
        const response = await res;
        console.log(response);
        console.log(response);

    }
    async function getUsers() {
        setLoading(true);
        const response = await fetch("https://randomuser.me/api/?results=5");
        const users = await response.json();
        setUsers(users.results);
        setLoading(false);
    }


    useEffect(() => {
        getUsers()
    }, []);


    return (
        <>
            <div style={{"padding": "1rem 0rem"}}>
                <button onClick={onSubmit}  disabled={loading}> Reload</button>
            </div>
            {loading && <p>Loading...</p>}
            {!loading && <div>
                {users.map(user => {
                    return (
                        <div key={user.email}>
                            <div style={{"display": "flex", "gap" :"2rem", "padding" :"1rem"}}>
                                <img  src={user.picture.large} width={150} height={150}/>
                                <div>
                                    <p>Name : <span> {user.name.title} {user.name.first} {user.name.last} </span></p>
                                    <p>Gender : <span> {user.gender}</span></p>
                                    <p>Email : <span> {user.email}</span></p>
                                </div>

                            </div>

                        </div>
                    )
                })}
            </div>}

        </>
    )
}

export default UserList;
