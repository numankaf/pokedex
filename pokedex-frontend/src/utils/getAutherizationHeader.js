import Cookies from "js-cookie";

export function getAuthorizationHeader() {
  const currentUser = Cookies.get("currentUser");
  if(currentUser){
    return {
      Authorization: `${JSON.parse(currentUser || "")?.token || ""}`,
    };
  }
}
