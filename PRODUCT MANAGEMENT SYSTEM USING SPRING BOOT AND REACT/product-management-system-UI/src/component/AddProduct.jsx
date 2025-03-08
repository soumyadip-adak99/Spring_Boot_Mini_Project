import React, { useState } from 'react';
import ProductService from '../service/ProductService';

const AddProduct = () => {
    const [product, setProduct] = useState({
        productName: "",
        productDescription: "",
        price: "",
        status: "Available"
    });

    const [message, setMessage] = useState("");
    const [messageType, setMessageType] = useState("");

    const handleChange = (e) => {
        setProduct({ ...product, [e.target.name]: e.target.value });
    };

    const ProductRegister = (e) => {
        e.preventDefault();

        if (product.price <= 0) {
            setMessage("Price must be a positive number.");
            setMessageType("error");
            return;
        }

        ProductService.saveProduct(product)
            .then(() => {
                setMessage("Product added successfully!");
                setMessageType("success");

                setProduct({
                    productName: "",
                    productDescription: "",
                    price: "",
                    status: "Available"
                });

                setTimeout(() => setMessage(""), 2000);
            })
            .catch((error) => {
                const errorMessage = error.response?.data?.message || "Something went wrong!";
                setMessage(errorMessage);
                setMessageType("error");

                setTimeout(() => setMessage(""), 2000);
            });
    };

    const clearAllBox = () => {
        setProduct({
            productName: "",
            productDescription: "",
            price: "",
            status: "Available"
        });
    };

    return (
        <div className='container'>
            <div className='row'>
                <div className="col-md-6 offset-md-3">
                    <div className="card mt-4">
                        <div className="card-header fs-3 text-center">
                            Add Product
                        </div>
                        <div className="card-body">
                            {message && (
                                <div
                                    className={`alert ${messageType === "success" ? "alert-success" : "alert-danger"} text-center`}
                                    role="alert"
                                >
                                    {message}
                                </div>
                            )}
                            <form onSubmit={ProductRegister}>
                                <div className="mb-3">
                                    <label>Enter Product Name</label>
                                    <input
                                        type='text'
                                        name='productName'
                                        className='form-control'
                                        value={product.productName}
                                        onChange={handleChange}
                                        required
                                    />
                                </div>

                                <div className="mb-3">
                                    <label>Enter Product Description</label>
                                    <textarea
                                        name='productDescription'
                                        className='form-control'
                                        value={product.productDescription}
                                        onChange={handleChange}
                                        required
                                    />
                                </div>

                                <div className="mb-3">
                                    <label>Enter Product Price</label>
                                    <input
                                        type='number'
                                        name='price'
                                        className='form-control'
                                        value={product.price}
                                        onChange={handleChange}
                                        required
                                        min="0"
                                    />
                                </div>

                                <div className="mb-3">
                                    <label>Select Product Status</label>
                                    <select
                                        name='status'
                                        className='form-control'
                                        value={product.status}
                                        onChange={handleChange}
                                        required
                                    >
                                        <option value="Available">Available</option>
                                        <option value="Out of Stock">Out of Stock</option>
                                        <option value="Discontinued">Discontinued</option>
                                    </select>
                                </div>

                                <button type='submit' className='btn btn-primary col-md-12'>Submit</button>
                                <button onClick={clearAllBox} className='btn btn-danger col-md-12 mt-2'>Clear</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default AddProduct;
