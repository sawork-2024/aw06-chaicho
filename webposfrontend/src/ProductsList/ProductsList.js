import React, { useEffect, useState } from 'react';

function ProductsList(props) {
    const [products, setProducts] = useState([]);
    const [selectedTab,setSelectedTab] = useState('');
    const [productTypes,setProductTypes] = useState([ 'All','Entertainment',
        'Activity',
        'Pets',
        'Beauty',
        'Health',
        'Automotive',
        'Electronics',
        'Home',
        'Clothing'])

    useEffect(() => {
        fetch('http://localhost:8080/api/products')
            .then((response) => response.json())
            .then((data) => {
                console.log(data._embedded.productList);
                setProducts(data._embedded.productList);
            });
    }, []);


    useEffect(() => {
        if(selectedTab ===""){
            return;
        }
        // var url = "products"
        // if(selectedTab !== "All"){
        //     url = "products/category/"+selectedTab;
        // }
        // console.log(url)
        // fetch(url)
        //     .then((response) => response.json())
        //     .then((data) => {
        //         // console.log('products/category/'+props.type);
        //         // console.log(data._embedded.productList);
        //         setProducts(data._embedded.productList);
        //     });

    }, [selectedTab]);

    return (
        <React.Fragment>
            <ul
                className="nav bg radius nav-pills nav-fill mb-3 bg"
                role="tablist"
            >
                {productTypes.map((category, index) => (
                    <li className="nav-item" key={index}>
                        <a className={`nav-link ${index === 0 ? 'active show' : ''}`} data-toggle="pill" href={`#nav-tab-${category}`} onClick={()=>{setSelectedTab(category)}}>
                            <i className="fa fa-tags"></i> {category}
                        </a>
                    </li>
                ))}
            </ul>
            <span id="items">

        <div id="itemrow" className="row">
            {products.map((product) => (
                <div className="col-md-3" key={product.id}>
                    <figure className="card card-product">
                        <span className="badge-new"> NEW </span>
                        <div className="img-wrap">
                            {/*<img src="assets/images/placeholder.png" />*/}
                            <a className="btn-overlay" href="#">
                                <i className="fa fa-search-plus"></i> Quick view
                            </a>
                        </div>
                        <figcaption className="info-wrap">
                            <a href="#" className="title">
                                {product.name}
                            </a>
                            <div className="action-wrap">
                                <a href="#" className="btn btn-primary btn-sm float-right"
                                   onClick={() => props.addToCart(product.name,product.price,1)}>
                                    <i className="fa fa-cart-plus"></i> Add
                                </a>
                                <div className="price-wrap h5">
                                    <span className="price-new">${product.price}</span>
                                </div>
                            </div>
                        </figcaption>
                    </figure>
                </div>
            ))}
        </div>
            </span>
          </React.Fragment>
    );
}

export default ProductsList;
