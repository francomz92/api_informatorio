/* Constants and Variables */

const cartPost = document.getElementById('cart');

const POST_URL = 'http://localhost:8080/api/v1/users/1/carts';

/* Events */

cartPost.addEventListener('click', () => {
   if (cart.details.length > 0) {
      fetchCart(POST_URL);
   }
});

/* Methods */

const fetchCart = async (url) => {
   const data = {
      method: 'POST',
      headers: {
         'Content-Type': 'application/json',
      },
      body: JSON.stringify(cart),
   };
   try {
      const response = await fetch(url, data);
      const createdCart = await response.json();
      cart.details = [];
      localStorage.removeItem('cart');

      document.location.reload();
   } catch (error) {
      console.log(error);
   }
};
