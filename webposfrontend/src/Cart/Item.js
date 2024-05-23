import React, {useEffect, useState} from 'react';

function Item(props) {
    // const [quantity, setQuantity] = useState(parseInt(props.quantity));
    // const [name, setName] = useState(props.name);
    // const [price, setPrice] = useState(props.price);

    const handleDelete = () =>{
        props.onRemove(props.name);
    }
    if (props.quantity === 0) {
        props.onRemove(props.name);
        return null;
    }

    const handleQuantityChange = (value) => {
        props.addToCart(props.name,props.price,value)
    };

    return (
        <tr>
            <td>
                <figure class="media">
                    <div class="img-wrap">
                        <img src="assets/images/items/4.jpg" class="img-thumbnail img-xs" />
                    </div>
                    <figcaption class="media-body">
                        <h6 class="title text-truncate">{props.name}</h6>
                    </figcaption>
                </figure>
            </td>
            <td class="text-center">
                <div class="m-btn-group m-btn-group--pill btn-group mr-2" role="group" aria-label="...">
                    <button type="button" class="m-btn btn btn-default" onClick={() => handleQuantityChange(-1)}>
                        <i class="fa fa-minus"></i>
                    </button>
                    <button type="button" class="m-btn btn btn-default" disabled>
                        {props.quantity}
                    </button>
                    <button type="button" class="m-btn btn btn-default" onClick={() => handleQuantityChange(1)}>
                        <i class="fa fa-plus"></i>
                    </button>
                </div>
            </td>
            <td>
                <div class="price-wrap">
                    <var class="price">${props.price}</var>
                </div>
            </td>
            <td class="text-right">
                <a href="#" className="btn btn-outline-danger btn-round" onClick={handleDelete}>
                    <i class="fa fa-trash"></i>
                </a>
            </td>
        </tr>
    );
}

export default Item;
