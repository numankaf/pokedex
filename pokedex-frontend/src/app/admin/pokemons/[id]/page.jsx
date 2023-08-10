"use client"

import {useEffect, useState} from "react";
import {pokemonService} from "@/services";
import {Tag} from "primereact/tag";
import PokemonTypesData from "@/utils/PokemonTypesData";
import {ProgressBar} from 'primereact/progressbar';
import {useRouter} from "next/navigation";
import { Button } from 'primereact/button';
import { Dialog } from 'primereact/dialog';
import { DataView } from 'primereact/dataview';
const PokemonDetailPage = ({params}) => {
    const id = params.id;
    const router = useRouter()
    const [pokemon, setPokemon] = useState({types: []});
    const [users, setUsers] = useState([]);
    const [header, setHeader] = useState('');
    const [visible, setVisible] = useState(false);
    useEffect(() => {
        getPokemon();
    }, []);

    const getPokemon = async () => {
        await pokemonService.getById(id).then((data) => setPokemon(data))
    }

    const getWhoCatched = () =>{
        pokemonService.getUsersWhoCatched(id).then((data) =>{
            setUsers(data);
            setHeader("Users Who Catched This Pokemon")
            setVisible(true);
        }).catch((e) =>{
            alert(e.message)
        })
    }

    const getWhoWished = () =>{
        pokemonService.getUsersWhoAddedWishList(id).then((data) =>{
            setUsers(data);
            setHeader("Users Who Wishlisted This Pokemon")
            setVisible(true);
        }).catch((e) =>{
            alert(e.message)
        })
    }

    const itemTemplate = (user) => {
        return (
            <div className="col-12">
                <div className="flex flex-column xl:flex-row xl:align-items-start p-4 gap-4">
                    <img className="border-circle" width={50} height={50}
                         src={user.thumbnail} alt={user.name} />
                    <div className="flex flex-column sm:flex-row justify-content-between align-items-center xl:align-items-start flex-1 gap-4">
                        <div className="flex flex-column align-items-center sm:align-items-start gap-1">
                            <div className="text-xl font-semibold text-900">{user.name+" "+ user.surname}</div>
                            <div>{user.email}</div>
                        </div>
                    </div>
                </div>
            </div>
        );
    };

    return (
        <div className={"card"}>
            <Dialog header={header} visible={visible} style={{ width: '50vw' }} onHide={() => setVisible(false)}>
                <DataView value={users} itemTemplate={itemTemplate} paginator rows={5} />
            </Dialog>
            <a className={"underline-link text-primary cursor-pointer"} onClick={()=>router.back()}>Back to List</a>
            <h1 className={"text-center"}> {pokemon.name}</h1>
            <div className={"flex flex-row gap-3 justify-content-end"}>
                <Button label="Get users who catched this pokemon"  severity={"success"} onClick={() =>getWhoCatched()} ></Button>
                <Button label="Get users who wishlisted this pokemon"   severity={"help"} onClick={() =>getWhoWished()}></Button>
            </div>
            <div className={"flex gap-5 p-3 justify-content-between"}>
                <div>
                    <img src={pokemon.thumbnail} width={360} height={360}/>
                </div>
                <div className={"flex flex-column gap-2 p-5"}>
                    <div className={"flex align-items-center gap-3"}>
                        <h3>
                            Types:
                        </h3>
                        <div className={"flex gap-2"}>
                            {
                                pokemon.types.map((item, index) => (
                                    <div key={index} className={"cursor-pointer"}>
                                        <Tag style={{background: PokemonTypesData.get(item)}}>
                                            <p> {item}</p>
                                        </Tag>
                                    </div>
                                ))
                            }
                        </div>
                    </div>
                    <div className={"flex align-items-center gap-3"}>
                        <h3>Specie:</h3>
                        <p>{pokemon.specie}</p>
                    </div>
                    <div className={"flex align-items-center gap-3"}>
                        <h3>Weight :</h3>
                        <p>{pokemon.weight} kg</p>
                    </div>
                    <div className={"flex align-items-center gap-3"}>
                        <h3>Height :</h3>
                        <p>{pokemon.height} m</p>
                    </div>
                    <div className={"flex align-items-center gap-3"}>
                        <h3>Abilities:</h3>
                        <p>{pokemon.abilities}</p>
                    </div>
                    <div className={"pt-2"}>
                        <h2 className={"py-2"}>System Status</h2>
                        <div className={"flex align-items-center gap-3"}>
                            <h3 className={"font-semibold"}>Created By:</h3>
                            <p>{pokemon.createdBy}</p>
                        </div>
                        <div className={"flex align-items-center gap-3"}>
                            <h3 className={"font-semibold"}>Created Date :</h3>
                            <p>{pokemon.createdDate} </p>
                        </div>
                        <div className={"flex align-items-center gap-3"}>
                            <h3 className={"font-semibold"}>Last Modified By :</h3>
                            <p>{pokemon.lastModifiedBy} </p>
                        </div>
                        <div className={"flex align-items-center gap-3"}>
                            <h3 className={"font-semibold"}>Last Modified Date :</h3>
                            <p>{pokemon.lastModifiedDate}</p>
                        </div>
                    </div>

                </div>
                <div>
                    <h2 className={"py-3"}>Base Stats</h2>
                    <table cellSpacing="10" cellPadding="0">
                        <tbody>
                        <tr>
                            <td><h3> Hp: </h3></td>
                            <td><p> {pokemon.hp}</p></td>
                            <td>
                                <div className={"w-20rem"}>
                                    <ProgressBar value={pokemon.hp / 260 * 100} showValue={false}
                                                 color={"var(--orange-500)"}></ProgressBar>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td><h3> Attack: </h3></td>
                            <td><p> {pokemon.attack}</p></td>
                            <td>
                                <div className={"w-20rem"}>
                                    <ProgressBar value={pokemon.attack / 200 * 100} showValue={false}
                                                 color={"var(--orange-500)"}></ProgressBar>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td><h3> Defense: </h3></td>
                            <td><p> {pokemon.defense}</p></td>
                            <td>
                                <div className={"w-20rem"}>
                                    <ProgressBar value={pokemon.defense / 250 * 100} showValue={false}
                                                 color={"var(--orange-500)"}></ProgressBar>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td><h3> Special Attack: </h3></td>
                            <td><p> {pokemon.specialAttack}</p></td>
                            <td>
                                <div className={"w-20rem"}>
                                    <ProgressBar value={pokemon.specialAttack / 200 * 100} showValue={false}
                                                 color={"var(--yellow-500)"}></ProgressBar>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td><h3> Special Defense: </h3></td>
                            <td><p> {pokemon.specialDefense}</p></td>
                            <td>
                                <div className={"w-20rem"}>
                                    <ProgressBar value={pokemon.specialDefense / 250 * 100} showValue={false}
                                                 color={"var(--yellow-500)"}></ProgressBar>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td><h3> Speed: </h3></td>
                            <td><p> {pokemon.speed}</p></td>
                            <td>
                                <div className={"w-20rem"}>
                                    <ProgressBar value={pokemon.speed / 200 * 100} showValue={false}
                                                 color={"var(--orange-500)"}></ProgressBar>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>

                </div>

            </div>
        </div>
    )
}

export default PokemonDetailPage
