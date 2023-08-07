import './App.css';
import {ShoppingCart} from "./shopping-app/ShoppingCart";
import ToDoAppPage from "./todoApp/ToDoAppPage";
import UserList from "./crudAppExample/UserList";

function App() {
  const  shoppingCartData = {
    title: "My Shopping Cart",
    content:[
      {
        name: "Apples",
        data: [{name: "Apple 1"}, {name: "Apple 2 "}, {name: "Apple 3 "}, {name: "Apple 4 "}, {name: "Apple 5"}]
      },
      {
        name: "Oranges",
        data: [{name: "Orange 1"}, {name: "Orange 2 "}]
      },
      {
        name: "Strawberries",
        data: [{name: "Strawberry 1"}, {name: "Strawberry 2 "}, {name: "Strawberry 3 "},{name: "Strawberry 4 "}]
      },
      {
        name: "Bananas",
        data: [{name: "Banana 1"}, {name: "Banana 2 "}, {name: "Banana 3 "}]
      },
      {
        name: "Cherries",
        data: [{name: "Cherry 1"}, {name: "Cherry 2 "}]
      }
    ]
  }
  return (
    <UserList></UserList>
  );
}

export default App;
