import { ProductsService } from "../services/products-service.js";

export class ProductsView {
    constructor(){

    }
    displayProducts(){
        const productsService = new ProductsService();
        ProductsService.findAll().then((data) => {
        if (data) {
            this.#displayProduct(data);
        }
        }).catch(error => {
            console.log("Aucun produit trouvé", error);
    });
    }
    #displayProduct(data){
        const products = document.querySelector(".products");
        let name_div = document.createElement("div");
        let owner_div = document.createElement("div");
        let bid_div = document.createElement("div");
        let button_div = document.createElement("div");
        products.style = "display: flex; justify-content : space-between;";
        let temp_enfant_name = document.createElement("div");
        let temp_enfant_owner = document.createElement("div");
        let temp_enfant_bid = document.createElement("div");
        let temp_enfant_button = document.createElement("div");
        temp_enfant_button.style = "display: grid;";
        for(let i = 0; i < data.length; i++){
            let temp_name = document.createElement("div");
            temp_name.innerHTML = data[i].name;
            let temp_owner = document.createElement("div");
            temp_owner.innerHTML = data[i].owner;
            let temp_bid = document.createElement("div");
            temp_bid.innerHTML = data[i].bid + " €";
            temp_bid.dataset.id = data[i].id;
            let temp_button = document.createElement("button");
            temp_button.dataset.id = data[i].id;
            temp_button.addEventListener("click", ()=>{
                const id = temp_button.dataset.id;
                ProductsService.bid(id)
            });
            temp_button.innerHTML = "encherir";
            temp_enfant_name.appendChild(temp_name);
            temp_enfant_owner.appendChild(temp_owner);
            temp_enfant_bid.appendChild(temp_bid);
            temp_enfant_button.appendChild(temp_button);
        }
        products.appendChild(temp_enfant_name);
        products.appendChild(temp_enfant_owner);
        products.appendChild(temp_enfant_bid);
        products.appendChild(temp_enfant_button);
    }
    updateBid(data){
        const bid = document.querySelectorAll("div");
        for(let i = 0; i < bid.length; i++){
            if(bid[i].dataset.id == data.id){
                bid[i].innerHTML = data.bid + " €";
            }
        }
    }
}