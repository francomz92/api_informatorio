/* Constants and Variables */

const productsUrl = 'http://localhost:8080/api/v1/products';

const cards = document.getElementById('cards-container');
const cardsTemplate = document.getElementById('card-template').content;
const purchase = document.getElementById('cart');

const fragment = document.createDocumentFragment();

let cart = {
   details: [],
};

/* Events */

document.addEventListener('DOMContentLoaded', () => {
   fetchProduct(productsUrl);
   if (localStorage.getItem('cart')) {
      cart = JSON.parse(localStorage.getItem('cart'));
      printCartLength();
   }
});

/* Methods */

const fetchProduct = async (productsUrl) => {
   try {
      const response = await fetch(productsUrl);
      const products = await response.json();
      printCards(products);
      addToCart(products);
   } catch (error) {
      console.log(error);
   }
};

const printCards = (products) => {
   products.forEach((product) => {
      cardsTemplate.querySelector('.card__title').textContent = product.name;
      cardsTemplate.querySelector('.card__description').textContent = product.description;
      cardsTemplate.querySelector('.card__price').textContent = '$' + product.unitPrice;
      cardsTemplate.querySelector('.card__button').dataset.id = product.id;

      const clone = cardsTemplate.cloneNode(true);
      fragment.appendChild(clone);
   });
   cards.appendChild(fragment);
};

const addToCart = (products) => {
   cards.addEventListener('click', (event) => {
      if (event.target.classList.contains('card__button')) {
         let detail = {};
         let product = products.filter((product) => product.id === Number(event.target.dataset.id))[0];

         for (const item of cart.details) {
            if (item.product.id === product.id) {
               item.amount++;
               localStorage.setItem('cart', JSON.stringify(cart));
               printCartLength();
               return;
            }
         }
         detail.product = { ...product };
         detail.amount = 1;
         cart.details.push({ ...detail });
         localStorage.setItem('cart', JSON.stringify(cart));
         printCartLength();
      }
   });
};

const printCartLength = () => {
   let quantity = 0;
   cart.details.forEach((detail) => {
      quantity += detail.amount;
   });
   purchase.textContent = quantity;
};
