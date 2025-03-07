import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import ProductService from '../service/ProductService';

const EditProduct = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const [product, setProduct] = useState({
        productName: "",
        productDescription: "",
        price: "",
        status: "Available"
    });

    useEffect(() => {
        ProductService.getProductById(id)
            .then((res) => setProduct(res.data))
            .catch(() => alert("Error fetching product details."));
    }, [id]);

    const handleChange = (e) => {
        setProduct({ ...product, [e.target.name]: e.target.value });
    };

    const updateProduct = (e) => {
        e.preventDefault();
        ProductService.editProduct(id, product)
            .then(() => {
                alert("Product updated successfully!");
                navigate("/");
            })
            .catch(() => alert("Error updating product."));
    };

    return (
        <div className="container mt-5">
            <div className="row justify-content-center">
                <div className="col-md-6">
                    <div className="card shadow-lg">
                        <div className="card-header bg-primary text-white text-center">
                            <h3>Edit Product</h3>
                        </div>
                        <div className="card-body">
                            <form onSubmit={updateProduct}>
                                {/* Product Name */}
                                <div className="mb-3">
                                    <label className="form-label">Product Name</label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        name="productName"
                                        value={product.productName}
                                        onChange={handleChange}
                                        required
                                    />
                                </div>


                                <div className="mb-3">
                                    <label className="form-label">Product Description</label>
                                    <textarea
                                        className="form-control"
                                        name="productDescription"
                                        rows="3"
                                        value={product.productDescription}
                                        onChange={handleChange}
                                        required
                                    ></textarea>
                                </div>

                                <div className="mb-3">
                                    <label className="form-label">Price ($)</label>
                                    <input
                                        type="number"
                                        className="form-control"
                                        name="price"
                                        value={product.price}
                                        onChange={handleChange}
                                        required
                                        min="0"
                                    />
                                </div>
                                <div className="mb-3">
                                    <label className="form-label">Status</label>
                                    <select
                                        className="form-select"
                                        name="status"
                                        value={product.status}
                                        onChange={handleChange}
                                        required
                                    >
                                        <option value="Available">Available</option>
                                        <option value="Out of Stock">Out of Stock</option>
                                        <option value="Discontinued">Discontinued</option>
                                    </select>
                                </div>

                                <div className="d-flex justify-content-between">
                                    <button type="submit" className="btn btn-success">
                                        Update Product
                                    </button>
                                    <button type="button" className="btn btn-secondary" onClick={() => navigate("/")}>
                                        Cancel
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default EditProduct;
