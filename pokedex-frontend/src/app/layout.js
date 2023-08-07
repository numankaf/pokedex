import './globals.css'
import "primereact/resources/primereact.css";
import "primeflex/primeflex.css";
import "primeicons/primeicons.css";
import {SessionProvider} from "next-auth/react";

export const metadata = {
    title: 'Pokedex',
    description: 'Pokemon Management Application',
}

export default function RootLayout({children, session}) {
    return (
        <html lang="en" suppressHydrationWarning>
            <head>
                <link
                    id="theme-css"
                    href={`/themes/light.css`}
                    rel="stylesheet"
                ></link>

                <link rel="icon" href="/images/favicon.ico"/>
            </head>
            <body style={{"margin": "0"}}>{children}</body>
        </html>
    )
}
