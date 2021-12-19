<template>
  <v-card
    class="ma-2"
  >
    <v-card-title>
      {{ product.name }}
    </v-card-title>
    <v-card-text>
      {{ product.price }}원 /
      <span v-if="product.rate > 0">(10% 할인) / </span>
      <span>재고: {{ product.quantity }}개</span>
    </v-card-text>
    <v-card-actions>
      <v-row>
        <v-col cols="2">
          <v-text-field
            label="수량"
            type="number"
            v-model="quantity"
          ></v-text-field>
        </v-col>
        <v-col>
          <v-btn
            v-on:click="buy"
          >
            구매하기
          </v-btn>
        </v-col>
      </v-row>
    </v-card-actions>
  </v-card>
</template>

<script>
  export default {
    name: 'Product',

    props: {
      product: Object
    },

    data: () => ({
      quantity: 1
    }),

    methods:{
      buy() {
        this.$emit('buy', { productId: this.product.id, quantity: this.quantity });
      },
    }
  }
</script>
