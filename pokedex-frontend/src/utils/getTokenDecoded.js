import Cookies from "js-cookie";
import jwt_decode from "jwt-decode";
export function getTokenDecoded() {
    const currentUser = Cookies.get("currentUser");
    const token = JSON.parse(currentUser || "")?.token || "";
    return jwt_decode(token);
}
