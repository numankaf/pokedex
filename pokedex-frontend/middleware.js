import {NextResponse} from "next/server";
import {getTokenDecoded} from "@/utils/getTokenDecoded";
import {protectedRoutes, publicRoutes} from "@/utils/routes";


export function middleware(request) {
    const currentUser = request.cookies.get("currentUser")?.value;
    const decodedToken = getTokenDecoded();

    if (
        protectedRoutes.includes(request.nextUrl.pathname) &&
        (!currentUser || Date.now() > decodedToken.exp)
    ) {
        request.cookies.delete("currentUser");
        const response = NextResponse.redirect(new URL("/auth/login", request.url));
        response.cookies.delete("currentUser");

        return response;
    }
    if (publicRoutes.includes(request.nextUrl.pathname) && currentUser) {
        if(decodedToken.auth.authority ==='TRAINER'){
            return NextResponse.redirect(new URL("/main", request.url));
        }
        if(decodedToken.auth.authority ==='ADMIN'){
            return NextResponse.redirect(new URL("/admin", request.url));
        }

    }
}
