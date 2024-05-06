<template>
  <div>
    <slot :items="goals" anotherProp="other scoped slots"> </slot>
  </div>
  <div>
    <ul v-for="goal in goals" :key="goal">
      <slot :item="goal" name="nameSlot"> </slot>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      goals: [
        {
          id: 1,
          name: "Study Java",
        },
        {
          id: 2,
          name: "Learn Spring Boot",
        },
        {
          id: 3,
          name: "Familiarize with Vue",
        },
      ],
    };
  },
};
</script>

<style scoped></style>
