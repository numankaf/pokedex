"use client"

import {useEffect, useState} from "react";
import {pokemonService} from "@/services";
import {Tag} from "primereact/tag";
import PokemonTypesData from "@/utils/PokemonTypesData";
import {ProgressBar} from 'primereact/progressbar';
import {useRouter} from "next/navigation";
import { Button } from 'primereact/button';
const PokemonDetailPage = ({params}) => {
    const id = params.id;
    const router = useRouter()
    const [pokemon, setPokemon] = useState({types: []});
    useEffect(() => {
        getPokemon();
    }, []);

    const getPokemon = async () => {
        await pokemonService.getById(id).then((data) => setPokemon(data))
    }
    return (
        <div className={"card"}>
            <a className={"underline-link text-primary cursor-pointer"} onClick={()=>router.back()}>Back to List</a>
            <h1 className={"text-center"}> {pokemon.name}</h1>
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
                        <h3>Abilities :</h3>
                        <p>{pokemon.abilities}</p>
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
                                    <ProgressBar value={pokemon.specialAttack / 194 * 100} showValue={false}
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
