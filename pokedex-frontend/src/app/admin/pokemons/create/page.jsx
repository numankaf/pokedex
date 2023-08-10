"use client"
import {FileUpload} from 'primereact/fileupload';
import React, {useRef, useState} from 'react';
import {InputText} from "primereact/inputtext";
import {Button} from "primereact/button";
import {InputNumber} from "primereact/inputnumber";
import PokemonTypesData from "@/utils/PokemonTypesData";
import {Tag} from "primereact/tag";
import {MultiSelect} from "primereact/multiselect";
import {pokemonService} from "@/services";
import {Toast} from "primereact/toast";
import {useRouter} from "next/navigation";
const CreatePokemonPage = () => {
    const router = useRouter();
    const toast = useRef(null);
    const [loading , setLoading] = useState(false);
    const [input, setInput] = useState({
        name: '',
        thumbnail: '',
        types: [],
        weight: 0,
        height: 0,
        specie: '',
        abilities: '',
        hp: 0,
        attack: 0,
        defense: 0,
        speed: 0,
        specialAttack: 0,
        specialDefense: 0,
    });

    const types = [{name : "NORMAL"}, {name :"FIRE"}, {name :"WATER"}, {name :"ELECTRIC"}, {name :"GRASS"},
        {name : "ICE"}, {name :"FIGHTING"},{name : "POISON"},{name : "GROUND"},{name : "FLYING"}, {name :"PSYCHIC"},
        {name :"BUG"}, {name :"ROCK"}, {name :"GHOST"}, {name :"DRAGON"}, {name :"DARK"}, {name :"STEEL"}, {name :"FAIRY"}]

    const typeOptionTemplate = (option) => {
        return (
            <Tag style={{background: PokemonTypesData.get(option.name)}}>
                <p> {option.name}</p>
            </Tag>
        );
    };



    const onSubmit = () => {
        let pokTypes =[];
        for (const t of input.types) {
            pokTypes.push(t.name);
        }
        const pokCreateData = {
            name: input.name,
            thumbnail: input.thumbnail,
            types: pokTypes,
            weight: input.weight,
            height: input.height,
            specie: input.specie,
            abilities: input.abilities,
            hp: input.hp,
            attack: input.attack,
            defense: input.defense,
            speed: input.speed,
            specialAttack: input.specialAttack,
            specialDefense: input.specialDefense,
        }
        pokemonService.createPokemon(pokCreateData).then( (res) =>{
            setLoading(true)
            toast.current.show({
                severity: 'success',
                summary: 'Success',
                detail: 'You have created a pokemon. Redirecting to back...',
                life: 3000
            });
            setTimeout(() => {  router.back()}, 2000);
        }).catch((e) => {
            toast.current.show({
                severity: 'error',
                summary: 'Error',
                detail: "Error occured",
                life: 3000
            });
        } )

    };
    const uploadedImage = React.useRef(null);
    const imageUploader = React.useRef(null);

    const handleImageUpload = e => {
        const [file] = e.target.files;
        if (file) {
            const reader = new FileReader();
            const { current } = uploadedImage;
            console.log(uploadedImage)
            current.file = file;

            reader.onload = e => {
                current.src = e.target.result;
                setInput({...input, thumbnail: current.src})
            };
            reader.readAsDataURL(file);
        }
    };
    return (
        <div className={"card"}>
            <Toast ref={toast}/>
            <h1 className={"text-center"}> Create Pokemon</h1>

            <div>

                {/*<FileUpload ref={fileUploadRef} name="demo[]" url="/api/upload"  accept="image/*" mode={"basic"}*/}
                {/*           itemTemplate={itemTemplate}*/}
                {/*             />*/}
                <form>
                    <div className="flex flex-column  ">
                        <div className="flex flex-column align-items-center justify-content-center">
                            <label
                                   className="block text-base font-medium mb-2">Image</label>
                            <div >

                                <input
                                    type="file" accept="image/*" onChange={handleImageUpload} ref={imageUploader}
                                    style={{
                                        display: "none"
                                    }}
                                />
                                <div className={"shadow-2 w-15rem h-15rem "}
                                     onClick={() => imageUploader.current.click()}>
                                    <img ref={uploadedImage} className={"img-input"}
                                         src={"https://www.generationsforpeace.org/wp-content/uploads/2018/03/empty.jpg"}
                                         style={{
                                        width: "inherit",
                                        height: "inherit",
                                        position: "absolute"
                                    }}
                                    />
                                </div>
                            </div>
                            <div className=" pt-2  " style={{"width": "90%"}}>
                                <div className="py-3 flex justify-content-between flex-wrap">
                                    <span style={{"width": "24%"}}>
                                        <label htmlFor="name"
                                               className="block text-base font-medium mb-2">Name</label>
                                        <div className=" inline">
                                            <InputText id="name" placeholder="Name"
                                                       type={"text"}
                                                       name={"name"}
                                                       value={input.name}
                                                       onChange={(e) => setInput({...input, name: e.target.value})}
                                                       className="w-full py-2 "/>
                                        </div>

                                    </span>
                                    <span style={{"width": "24%"}}>
                                        <label htmlFor="types"
                                               className="block text-base font-medium mb-2">Types</label>
                                         <MultiSelect value={input.types} options={types} onChange={(e) => setInput({...input, types: e.target.value})}
                                                      optionLabel="name"
                                                      placeholder="Select Types" itemTemplate={typeOptionTemplate}  className="w-full md:w-20rem"
                                                      display="chip" />

                                    </span>
                                    <span style={{"width": "24%"}}>
                                        <label htmlFor="weight"
                                               className="block text-base font-medium mb-2">Weight</label>
                                        <div className=" inline">
                                            <InputNumber id="weight"
                                                         name={"weight"}
                                                         minFractionDigits={2} maxFractionDigits={5}
                                                         value={input.weight}
                                                         onChange={(e) => setInput({...input, weight: e.value})}
                                                         className="w-full"
                                            />
                                        </div>

                                    </span>
                                    <span style={{"width": "24%"}}>
                                        <label htmlFor="height"
                                               className="block text-base font-medium mb-2">Height</label>
                                        <div className=" inline">
                                            <InputNumber id="height"
                                                         name={"height"}
                                                         minFractionDigits={2} maxFractionDigits={5}
                                                         value={input.height}
                                                         onChange={(e) => setInput({...input, height: e.value})}
                                                         className="w-full"/>
                                        </div>

                                    </span>

                                </div>

                            </div>
                            <div className=" pt-2  " style={{"width": "90%"}}>
                                <div className="py-3 flex justify-content-between flex-wrap">
                                    <span style={{"width": "49%"}}>
                                        <label htmlFor="specie"
                                               className="block text-base font-medium mb-2">Specie</label>
                                        <div className=" inline">
                                            <InputText id="specie" placeholder="Specie"
                                                       type={"text"}
                                                       name={"specie"}
                                                       value={input.specie}
                                                       onChange={(e) => setInput({...input, specie: e.target.value})}
                                                       className="w-full py-2 "/>
                                        </div>

                                    </span>
                                    <span style={{"width": "49%"}}>
                                        <label htmlFor="abilities"
                                               className="block text-base font-medium mb-2">Abilities</label>
                                         <InputText id="abilities" placeholder="Abilities"
                                                    type={"text"}
                                                    name={"abilities"}
                                                    value={input.abilities}
                                                    onChange={(e) => setInput({...input, abilities: e.target.value})}
                                                    className="w-full py-2 "/>

                                    </span>
                                </div>

                            </div>
                            <div className=" pt-2  " style={{"width": "90%"}}>
                                <div className="py-3 flex justify-content-between flex-wrap">
                                    <span style={{"width": "33%"}}>
                                        <label htmlFor="hp"
                                               className="block text-base font-medium mb-2">Hp</label>
                                        <div className=" inline">
                                            <InputNumber id="hp"
                                                         name={"hp"}
                                                         value={input.hp}  min={0} max={260}
                                                         onChange={(e) => setInput({...input, hp: e.value})}
                                                         className="w-full"
                                            />
                                        </div>

                                    </span>
                                    <span style={{"width": "33%"}}>
                                        <label htmlFor="attack"
                                               className="block text-base font-medium mb-2">Attack</label>
                                        <div className=" inline">
                                            <InputNumber id="attack"
                                                         name={"height"}
                                                         value={input.attack}  min={0} max={200}
                                                         onChange={(e) => setInput({...input, attack: e.value})}
                                                         className="w-full"/>
                                        </div>

                                    </span>
                                    <span style={{"width": "33%"}}>
                                        <label htmlFor="defense"
                                               className="block text-base font-medium mb-2">Defense</label>
                                        <div className=" inline">
                                            <InputNumber id="defense"
                                                         name={"height"}   min={0} max={250}
                                                         value={input.defense}
                                                         onChange={(e) => setInput({...input, defense: e.value})}
                                                         className="w-full"/>
                                        </div>

                                    </span>

                                </div>

                            </div>
                            <div className=" pt-2  " style={{"width": "90%"}}>
                                <div className="py-3 flex justify-content-between flex-wrap">
                                    <span style={{"width": "33%"}}>
                                        <label htmlFor="specialAttack"
                                               className="block text-base font-medium mb-2">Special Attack</label>
                                        <div className=" inline">
                                            <InputNumber id="specialAttack"
                                                         name={"specialAttack"}  min={0} max={200}
                                                         value={input.specialAttack}
                                                         onChange={(e) => setInput({...input, specialAttack: e.value})}
                                                         className="w-full"
                                            />
                                        </div>

                                    </span>
                                    <span style={{"width": "33%"}}>
                                        <label htmlFor="specialDefense"
                                               className="block text-base font-medium mb-2">Special Defense</label>
                                        <div className=" inline">
                                            <InputNumber id="specialDefense"
                                                         name={"specialDefense"}  min={0} max={250}
                                                         value={input.specialDefense}
                                                         onChange={(e) => setInput({...input, specialDefense: e.value})}
                                                         className="w-full"/>
                                        </div>

                                    </span>
                                    <span style={{"width": "33%"}}>
                                        <label htmlFor="speed"
                                               className="block text-base font-medium mb-2">Speed</label>
                                        <div className=" inline">
                                            <InputNumber id="speed"
                                                         name={"speed"}  min={0} max={200}
                                                         value={input.speed}
                                                         onChange={(e) => setInput({...input, speed: e.value})}
                                                         className="w-full"/>
                                        </div>

                                    </span>

                                </div>

                            </div>

                        </div>

                    </div>
                </form>
                <div className="flex align-items-center justify-content-end px-8 gap-3">
                    <Button label="Cancel" severity={"danger"} icon={"pi pi-times"}
                            onClick={() => router.back()} disabled={loading}
                    />
                    <Button label="Save" icon={"pi pi-check"} disabled={loading}
                            onClick={onSubmit}/>
                </div>
            </div>
        </div>
    )
}

export default CreatePokemonPage;
