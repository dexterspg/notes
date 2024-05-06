<template>
  <ul v-for="user in users" :key="user.id">
    <BaseCard>
      {{ user.name }}
      {{ user.description }}
      <BaseBadge :role="user.role"
                :caption="user.role.toUpperCase()" />
    </BaseCard>
  </ul>
</template>

<script>
import BaseCard from "./BaseCard.vue";
import BaseBadge from "./BaseBadge.vue";

export default {
  components: {
    BaseCard,
    BaseBadge,
  },
  inject: ["users"],
  data() {
    return {};
  },
};
</script>

<style scoped></style>
