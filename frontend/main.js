import { ProductsService } from "./services/products-service.js";
import { ProductsView } from "./views/products-view.js";

function run(){
    const productView = new ProductsView;
    productView.displayProducts();
}

window.addEventListener("load", run);