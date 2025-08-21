<script setup lang="ts">
const props = defineProps({
  kittingRate: {
    required: true
  }
})
</script>

<template>
  <div
    class="success"
    v-if="props.kittingRate >= 100.00 "
  >
    100%
  </div>
  <div
    class="warning"
    v-else-if="props.kittingRate >= 80 "
    key="props.kittingRate +'%'"
  >
    {{ props.kittingRate }}%
  </div>
  <div
    v-else-if="props.kittingRate >= 30 " class="warning"
  >
    {{ props.kittingRate }}%
  </div>
  <div
    v-else :key="props.kittingRate +'%'" class="danger"
  >
    {{ props.kittingRate }}%
  </div>
</template>

<style scoped lang="scss">

div.success {
  font-weight: bold;
  width: 60px;
  text-align: center;
  color: green;
}

div.warning {
  width: 60px;
  font-weight: bold;
  text-align: center;
  color: burlywood;
}

div.warning_max {
  width: 60px;
  font-weight: bold;
  text-align: center;
  color: deeppink;
}

div.danger {
  width: 60px;
  font-weight: bold;
  text-align: center;
  color: red;
}
</style>
