<template>
  <BaseCard>
    <h2>Available Badges</h2>
    <BaseBadge role="admin" caption="admin" />
    <BaseBadge role="author" caption="author" />
  </BaseCard>
</template>

<script>
import BaseBadge from "./BaseBadge.vue";
import BaseCard from "./BaseCard.vue";

export default {
  components: {
    BaseBadge,
    BaseCard,
  },
  data() {
    return {};
  },
};
</script>

<style scoped>
.admin {
  color: red;
}

.author {
  color: blue;
}
</style>
