import { ProductsService } from "./services/products-service.js";

function run(){
    const productsService = new ProductsService();
    productsService.findAll().then((data) => {
        if (data) {
            console.log("Tous les produits:", data);
        }
    }).catch(error => {
        console.log("Aucun produit trouvé", error);
    });
}

window.addEventListener("load", run);