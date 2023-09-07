import {getCookie} from "cookies-next";

export function getAuthorizationHeader() {
  const currentUser = getCookie("currentUser");
  if(currentUser){
    return {
      Authorization: `${JSON.parse(currentUser)?.token || ""}`,
    };
  }
}
