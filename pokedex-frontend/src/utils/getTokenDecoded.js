import jwt_decode from "jwt-decode";
import {getCookie} from "cookies-next";
export function getTokenDecoded() {
    const currentUser = getCookie("currentUser");
    const token = JSON.parse(currentUser || "")?.token || "";
    return jwt_decode(token);
}
