"use client"
import {Button} from "primereact/button";
import Link from "next/link";
import { Carousel } from 'primereact/carousel';

const HomePage = () => {
    const items =[
        {
            content: "Complete Pokemon List",
            image:"landing1.PNG"
        },
        {
            content: "Add Pokemons in Your Catch List and Wish List",
            image:"landing2.PNG"
        },
        {
            content: "View Pokemons In Detail",
            image:"landing3.PNG"
        },
        {
            content: "Edit Your Profile",
            image:"landing4.PNG"
        },
        {
            content: "Admin Panel For System Managment",
            image:"landing5.PNG"
        },

    ]

    const carouselItemTemplate = (item) => {
        return (
            <div className="surface-border border-round m-2 text-center py-5 px-3">
                <h1 className={"m-4"}>{item.content}</h1>
                <div className="mb-3">
                    <img src={`/images/${item.image}`}  className=" shadow-2" width={840} height={450}/>
                </div>
            </div>
        );
    };
    return (
        <div className="relative overflow-hidden flex flex-column justify-content-center surface-card ">
            <div
                className="py-6 px-4 mx-0 lg:py-4 lg:mx-4 lg:px-4 flex align-items-center justify-content-between relative lg:static">
                <img src="/images/pokedexlogo.png" alt="logo" width="30">
                </img>
                <span className="px-2  mr-5 text-3xl">POKEDEX</span>
                <a className="m-0 md:ml-5 px-0 py-3 text-900 font-medium line-height-3 cursor-pointer" href="#home">
                    Home
                </a>
                <a className="m-0 md:ml-5 px-0 py-3 text-900 font-medium line-height-3 cursor-pointer" href="#apps">
                    Apps
                </a>
                {/*<a className="m-0 md:ml-5 px-0 py-3 text-900 font-medium line-height-3 cursor-pointer"*/}
                {/*   href="#development-team" style={{"width": "300px"}}>*/}
                {/*    Development Team*/}
                {/*</a>*/}
                <div className="align-items-center flex-grow-1 justify-content-between hidden lg:flex
                lg:static w-full  left-0 top-100 px-6 lg:px-0 z-2lg:shadow-none">
                    <section></section>
                    <div className="flex flex-wrap gap-3 justify-content-center ">
                        <Link href={"/auth/login"}>
                            <Button label="Log in" text/>
                        </Link>
                        <Link href={"/auth/register"}>
                            <Button label="Register" raised/>
                        </Link>

                    </div>
                </div>
            </div>

            <div style={{
                background:
                    " linear-gradient(to left top, #0e6c7f, #007a86, #008889, #139689, #34a386, #56ad87, #71b68a, #89bf8e, #a5c89d, #bdd2af, #d2dcc3, #e4e7da)",
                clipPath: "ellipse(150% 87% at 93% 13%)",
            }}>
                <div className="p-4 mx-0 lg:mx-4 lg:px-8 z-2">
                    <div id="home"
                         className="grid grid-nogutter justify-content-between align-items-center mb-6 py-6 md:mb-8 md:py-5">
                        <div
                            className="col-12 md:col-5 flex flex-column gap-4  align-items-center md:align-items-start text-center md:text-left">
                            <span className="block text-900 font-bold text-4xl">Welcome to POKEDEX</span>
                            <span className="block text-700 text-lg">Introducing Pokedex, the ultimate Pokémon Management System that brings your
                            Trainer dreams to life!
                            Catch Pokémon from various regions, from the adorable Pikachu to the majestic Dragonite,
                            and build your dream team for epic battles. Create a wishlist of sought-after Pokémon a
                                {/* eslint-disable-next-line react/no-unescaped-entities */}
                                nd receive notifications when they're within reach. With detailed Pokémon profiles and a vibrant community,
                            Pokedex is your go-to platform for immersive Pokémon adventures and strategic mastery. Get ready to embark
                            on an unforgettable journey to become the ultimate Pokémon Master with Pokedex!</span>
                        </div>
                        <div className="col-12 md:col-5  mb-6 md:mb-0 ">
                            <img alt=""
                                 className=" border-round  animation-duration-1000 animation-ease-out moveinright"
                                 src="/images/background.png"/>
                        </div>
                    </div>

                </div>

            </div>
            <div id={"apps"} className={"flex align-items-center justify-content-center p-5 m-5"}>
                <div className={"w-10"}>
                    <Carousel value={items} numVisible={1} numScroll={1} circular autoFocus={true}
                              autoplayInterval={3000} itemTemplate={carouselItemTemplate} />
                </div>
            </div>


           <div   style={{position: "fixed", left: "0", bottom:"0", textAlign:"center"}} className={"w-full shadow-2 surface-card"}>
               <div className="flex justify-content-center align-items-center my-1 pb-1">
                   <span className="text-900 block text-xl mb-2 mt-2 text-center">© 2023 Numan Kafadar. All rights reserved.</span>
               </div>
           </div>
        </div>

    );
};

export default HomePage;
