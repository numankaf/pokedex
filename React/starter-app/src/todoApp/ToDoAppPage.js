
import ListItem from "./ListItem";
import {useEffect, useState} from "react";

export default function ToDoAppPage() {
    const [listItems, setListItems] = useState(["Todo 1", "Todo 2"])

    const [value, setValue] = useState('');
    const addItem = () =>{
        setListItems(listItems.concat(value))
        setValue("");
    }

    const deleteItem = () =>{
       setListItems((prevItems) => prevItems.slice(0, prevItems.length-1));
    }

    return (
        <>
            <h1>To Do List</h1>
            <div>
                <input  type="text" value={value} onChange={(e) => setValue(e.target.value)}/>
                <button onClick={addItem}> Add Todo</button>
                <button onClick={deleteItem}>Delete Todo</button>
            </div>
            <div>
                <ul>
                    {listItems.map((item,index) => {

                        return <ListItem key={index} name={item} index={index+1}></ListItem>
                    })}

                </ul>
            </div>
        </>
    )
}
