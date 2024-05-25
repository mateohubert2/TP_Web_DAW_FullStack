import { ProductsService } from "./services/products-service.js";
import { ProductsView } from "./views/products-view.js";
import { SSEClient } from "./lib/sse-client.js"

function run(){
    const productView = new ProductsView;
    const sseClient = new SSEClient("localhost:8080");
    sseClient.connect();
    sseClient.subscribe("bids", productView.updateBid);
    productView.displayProducts();
}

window.addEventListener("load", run);