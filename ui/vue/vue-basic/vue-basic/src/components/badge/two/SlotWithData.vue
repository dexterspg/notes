<template>
  <div>
    <slot :items="goals" anotherProp="other scoped slots" :addItem="addItem">
      Default Slot
    </slot>
  </div>
  <div>
    <ul v-for="goal in goals" :key="goal">
      <slot :item="goal" name="nameSlot">
        <!-- Name Slot -->
      </slot>
    </ul>
  </div>

  <div>
    <slot name="increment" :increment="increment" :count=count> </slot>
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

      count: 0,
    };
  },
  methods: {
    addItem() {
      console.log("item added");
      this.goals.push({ id: 4, name: "Add Item" });
    },
    increment() {
      this.count++;
    },
  },
};
</script>

<style scoped></style>
