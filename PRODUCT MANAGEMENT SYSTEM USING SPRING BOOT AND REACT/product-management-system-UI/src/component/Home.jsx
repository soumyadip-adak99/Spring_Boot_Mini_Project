import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import ProductService from '../service/ProductService';

const Home = () => {
    const [productList, setProductList] = useState([]);
    const [message, setMessage] = useState("");

    useEffect(() => {
        setMessage("");
        ProductService.getAllProducts()
            .then((res) => {
                if (res.data && res.data.length > 0) {
                    setProductList(res.data);
                } else {
                    setMessage("No products found.");
                }
            })
            .catch(() => {
                setMessage("Error fetching products. Please try again.");
            });
    }, []);

    const deleteProduct = (id) => {
        if (window.confirm("Are you sure you want to delete this product?")) {
            ProductService.deleteProduct(id)
                .then(() => {
                    alert("Product Deleted Successfully");
                    setProductList(prevList => prevList.filter(product => product.id !== id));
                })
                .catch((error) => {
                    alert("Failed to delete product: " + error.message);
                });
        }
    };

    return (
        <div className='container mt-4'>
            <div className="row">
                <div className="col-md-12">
                    <div className="card">
                        <div className="card-header fs-3 text-center">
                            <h2>All Product List</h2>
                        </div>
                        <div className="card-body">
                            {message ? (
                                <p className="text-center text-danger">{message}</p>
                            ) : (
                                <table className="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>SL NO</th>
                                            <th>Product Name</th>
                                            <th>Product Description</th>
                                            <th>Product Price</th>
                                            <th>Product Status</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {productList.length > 0 ? (
                                            productList.map((product, index) => (
                                                <tr key={product.id}>
                                                    <td>{index + 1}</td>
                                                    <td>{product.productName}</td>
                                                    <td>{product.productDescription}</td>
                                                    <td>{product.price}</td>
                                                    <td>{product.status}</td>
                                                    <td>
                                                        <Link to={`/edit-product/${product.id}`} className='btn btn-sm btn-primary me-2'>Edit</Link>
                                                        <button onClick={() => deleteProduct(product.id)} className='btn btn-sm btn-danger'>Delete</button>
                                                    </td>
                                                </tr>
                                            ))
                                        ) : (
                                            <tr>
                                                <td colSpan="6" className="text-center">No products available.</td>
                                            </tr>
                                        )}
                                    </tbody>
                                </table>
                            )}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Home;
