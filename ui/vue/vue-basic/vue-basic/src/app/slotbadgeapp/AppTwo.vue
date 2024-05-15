<template>
  <TheHeader title="Badge App" />
  <BadgeList />
  <UserInfo />
  <SlotWithData #default="slotProps">
    <ul v-for="goal in slotProps.items" :key="goal">
      {{
        goal.name
      }}
    </ul>
    {{ slotProps.anotherProp }}
    <button @click="slotProps.addItem">add item</button>
  </SlotWithData>

  <SlotWithData>
    <template #nameSlot="slpr">
      <p>{{ slpr.item }}</p>
    </template>

    <template #increment="{ count, increment }">
            {{ count }}
            <button  @click="increment">Add 1</button>
        </template>
  </SlotWithData>
</template>

<script>
import TheHeader from "../../components/TheHeader.vue";
import BadgeList from "../../components/badge/two/BadgeList.vue";
import UserInfo from "../../components/badge/two/UserInfo.vue";
import SlotWithData from "../../components/badge/two/SlotWithData.vue";

export default {
  components: {
    TheHeader,
    BadgeList,
    UserInfo,
    SlotWithData,
  },
  provide() {
    return {
      users: this.users,
    };
  },
  data() {
    return {
      users: [
        {
          id: 1,
          name: "Bob",
          description: "an aspiring author",
          role: "author",
        },
        {
          id: 1,
          name: "Jane",
          description: "IT admin",
          role: "admin",
        },
      ],
    };
  },
};
</script>

<style scoped></style>
