import {ShoppingTitle} from "./ShoppingTitle";
import {ShoppingList} from "./ShoppingList";

export function ShoppingCart(props) {
    let count = 0;
    for (let t of props.data.content) {
        count += t.data.length;
    }
    return (
        <>
            <ShoppingTitle title={props.data.title} count={count}/>
            {props.data.content.map(item => {
                return <ShoppingList key={item.name} name={item.name} items={item.data}></ShoppingList>
            })}

        </>
    )
}
