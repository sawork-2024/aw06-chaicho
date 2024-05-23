import React, { useState } from 'react';
import Item from './Item';

export default function Cart(props){
    function calculateTotalPrice() {
        let totalPrice = 0;
        props.cartItems.forEach((item) => {
            totalPrice += item.price * item.quantity;
        });
        return totalPrice.toFixed(2);
    }
    return (
        <React.Fragment>
            <div className="card">
                <span id="cart">
                    <table className="table table-hover shopping-cart-wrap">
                        <thead className="text-muted">
                            <tr>
                                <th scope="col">Item</th>
                                <th scope="col" width="120">Qty</th>
                                <th scope="col" width="120">Price</th>
                                <th scope="col" className="text-right" width="200">Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                            props.cartItems.map(item => (
                                <Item key={item.name} name={item.name} price={item.price} quantity={item.quantity} onRemove={props.onRemove}
                                addToCart ={props.addToCart}/>
                             ))
                            }
                        </tbody>
                    </table>
                </span>
            </div>
            <div className="box">
                <dl className="dlist-align">
                    <dt>Tax:</dt>
                    <dd className="text-right">12%</dd>
                </dl>
                <dl className="dlist-align">
                    <dt>Discount:</dt>
                    <dd className="text-right"><a href="#">0%</a></dd>
                </dl>
                <dl className="dlist-align">
                    <dt>Sub Total:</dt>
                    <dd className="text-right">${calculateTotalPrice()}</dd>
                </dl>
                <dl className="dlist-align">
                    <dt>Total:</dt>
                    <dd className="text-right h4 b"> ${calculateTotalPrice()}</dd>
                </dl>
                <div className="row">
                    <div className="col-md-6">
                        <a href="#" className="btn  btn-default btn-error btn-lg btn-block" onClick={props.emptyCart}><i
                            className="fa fa-times-circle "></i> Cancel </a>
                    </div>
                    <div className="col-md-6">
                        <a href="#" className="btn  btn-primary btn-lg btn-block" onClick={props.emptyCart}><i className="fa fa-shopping-bag"></i>
                            Charge </a>
                    </div>
                </div>
            </div>
        </React.Fragment>
    )
}
