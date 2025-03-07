import axios from 'axios';

const API_URL = 'http://localhost:8080';

class ProductService {
    saveProduct(product) {
        return axios.post(`${API_URL}/saveProduct`, product);
    }

    getAllProducts() {
        return axios.get(`${API_URL}/getAllProduct`);
    }

    getProductById(id) {
        return axios.get(`${API_URL}/getProductById/${id}`);
    }

    deleteProduct(id) {
        return axios.delete(`${API_URL}/deleteProduct/${id}`);
    }

    editProduct(id, product) {
        return axios.put(`${API_URL}/editProduct/${id}`, product);
    }
}

export default new ProductService();
