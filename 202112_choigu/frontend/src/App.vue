<template>
  <v-app id="inspire">
    <v-app-bar
      app
      color="white"
      flat
    >
      <v-container class="py-0 fill-height">
        <v-avatar
          class="mr-10"
          color="grey darken-1"
          size="32"
        ></v-avatar>

        <v-btn
          text
          v-on:click="changeView('product')"
        >
          상품
        </v-btn>
        <v-btn
          text
          v-on:click="changeView('order')"
        >
          주문목록
        </v-btn>
      </v-container>
    </v-app-bar>

    <v-main class="grey lighten-3">
      <v-container v-if="view == 'product'">
        <v-row>
          <v-col cols="2">
            <v-sheet rounded="lg">
              <v-list color="transparent">
                <v-list-item
                  link
                  v-on:click="getProducts('top')"
                >
                  <v-list-item-content>
                    <v-list-item-title>
                      상의
                    </v-list-item-title>
                  </v-list-item-content>
                </v-list-item>

                <v-divider class="my-2"></v-divider>

                <v-list-item
                  link
                  v-on:click="getProducts('bottom')"
                >
                  <v-list-item-content>
                    <v-list-item-title>
                      하의
                    </v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list>
            </v-sheet>
          </v-col>
          <v-col>
            <v-sheet
              min-height="70vh"
              rounded="lg"
              class="pt-1"
            >
              <Product
                v-for="product in products"
                v-bind:key="product.id"
                v-bind:product="product"
                v-on:buy="buy"
              ></Product>
            </v-sheet>
          </v-col>
        </v-row>
      </v-container>

      <v-container v-if="view == 'order'">
        <v-row>
          <v-col>
            <v-sheet
              min-height="70vh"
              rounded="lg"
              class="pt-1"
            >
              <Order
                v-for="order in orders"
                v-bind:key="order.id"
                v-bind:order="order"
              ></Order>
            </v-sheet>
          </v-col>
        </v-row>
      </v-container>

    </v-main>
  </v-app>
</template>

<script>

import Product from './components/Product'  // import components.Product;
import Order from './components/Order'  // import components.Order;

const axios = require('axios').default;
export default {    // public class App{
  name: 'App',

  components: {
    Product, Order
  },

  data: () => ({
    products: [],
    type: 'top',
    view: 'product',
    orders: []
  }),

  created() {
    this.getProducts(this.type);
  },

  methods: {
    async getProducts(type) {
      this.type = type; // 상의/하의 구분값 변경
      const response = await axios.get("http://localhost:8081/shop/" + type + "/products"); // 서버에서 상품목록 가져오기
      this.products = response.data; // 화면에 그려주기위해 data.products에 넣어준다
    },

    async getOrder() {
      const response = await axios.get("http://localhost:8081/shop/orders"); // 서버에서 주문목록 가져오기
      this.orders = response.data; // 화면에 그려주기위해 data.orders에 넣어준다
      console.log(this.orders)
    },

    async buy(order) {
      console.log(order);
      const response = await axios.get("http://localhost:8081/shop/order?productId=" + order.productId + "&quantity=" + order.quantity);
      console.log(response.data);
      alert(response.data);
      this.getProducts(this.type);
    },

    async changeView(viewName) {
      this.view = viewName;
      if (viewName == "product") {
        this.getProducts(this.type);
      } else if (viewName == "order") {
        this.getOrder();
      }
    }
  }
};
</script>
