<template>
  <v-card
    class="ma-2"
  >
    <v-card-title>
      {{ order.product.name }}
    </v-card-title>
    <v-card-text>
      주문 수량: {{ order.quantity }}개 <br/>
      주문 금액: {{ order.amount }}원 <br/>
    </v-card-text>
  </v-card>
</template>

<script>
  export default {
    name: 'Order',

    props: {
      order: Object
    },

    created() {
      console.log(this.order)
    },

    data: () => ({
    }),

    methods:{
    }
  }
</script>
