import React from "react";
import { Routes, Route } from "react-router-dom";
import Navbar from "./component/Navbar";
import Home from "./component/Home";
import AddProduct from "./component/AddProduct";
import EditProduct from "./component/EditProduct";

const App = () => {
    return (
        <>
            <Navbar />
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/addProduct" element={<AddProduct />} />
                <Route path="/edit-product/:id" element={<EditProduct />} />
            </Routes>
        </>
    );
};

export default App;
