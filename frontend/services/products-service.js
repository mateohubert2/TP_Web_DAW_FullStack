export class ProductsService {
    static async findAll() {
        const response = await fetch("http://localhost:8080/products");
        if(response.status === 200) {
            const data = await response.json();
            return data;
        }
    }
    static async bid(id){
        const response = await fetch("http://localhost:8080/bid/"+id, {method: "POST"});
        if(response.status === 200){
            //const data2 = await response.json();
            return true;
        }
        else{
            return false;
        }
    }
}