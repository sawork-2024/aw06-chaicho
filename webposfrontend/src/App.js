import React, { useState } from "react";
import ProductsList from "./ProductsList/ProductsList";
import Cart from "./Cart/Cart";

function App() {
  const [cartItems, setCartItems] = useState([]);

  function emptyCart(){
    setCartItems([]);
  }

  function addToCartById(id, name, price, quantity) {
    // Send request to add item to cart
    fetch('http://localhost:8080/api/cart/'+id, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        }
        }
        )
    addToCart(name, price, quantity);
  }
  function addToCart(name, price,quantity) {
    console.log(name,price,quantity);
    console.log(cartItems);
    const existingItem = cartItems.find(item => item.name === name);
    if (existingItem) {
      setCartItems(
          cartItems.map(item => {
            if (item.name === name) {
              return { ...item, quantity: item.quantity + quantity };
            }
            return item;
          })
      );
    } else {
      setCartItems([...cartItems, { name: name,price: price, quantity: quantity}]);
    }
  }

  function removeFromCart(name) {
    setCartItems(cartItems.filter(item => item.name !== name));
  }

  return (
      <section className="section-content padding-y-sm bg-default">
        <div className="container-fluid">
          <div className="row">
            <div className="col-md-8 card padding-y-sm card ">
              <ProductsList addToCart={addToCartById}/>
            </div>
            <div className="col-md-4">
              <Cart cartItems={cartItems} onRemove={removeFromCart} addToCart ={addToCartById} emptyCart={emptyCart}/>
            </div>
          </div>
        </div>
      </section>
  );
}

export default App;
