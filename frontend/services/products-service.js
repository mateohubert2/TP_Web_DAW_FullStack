export class ProductsService {
    async findAll() {
        const response = await fetch("http://localhost:8080/products");
        if (response.status === 200) {
            const data = await response.json();
            return data;
        }
    }
}