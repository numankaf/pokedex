import {ShoppingItem} from "./ShoppingItem";


export function ShoppingList(props) {
    return (
        <>
            <h4>{props.name}</h4>
            <ul>
                {props.items.map(item => {
                    return <ShoppingItem key={item.name} name={item.name}></ShoppingItem>
                })}

            </ul>

        </>
    )
}
