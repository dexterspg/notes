<template>
  <BaseContainer title="Vuex">
    <div>
      <!-- not good practice -->
      <p>{{ $store.state.count }}</p>
      <button @click="addOne">Add 1</button>
    </div>
    <TheCounter />
    <ChangeCounter />

    <p>
      Global module state (not accesible) :{{
        $store.state.globalStatusMessage
      }}
    </p>
    <p>Global module getter : {{ globalStateMessage }}</p>
    <p>
      Global module getter using mapper (comment other mapper SAME NAME to work)
      : {{ getGlobalStateMessage }}
    </p>

    <p>Global module getter with name space : {{ globalModWithNameSpace }}</p>
    <p>
      Global module getter with name space using mapper:
      {{ getGlobalStateMessage }}
    </p>
  </BaseContainer>

  <p>{{ products }}</p>

  <BaseContainer title="Vuex restructure module">
    <p>example Module getter : {{ getExampleMessage }}</p>
    <button @click="mutateExample">mutate example message</button>
    <button @click="setExampleMessageByAction">
      set example message by action
    </button>

    <button @click="setMessage({ addedMessage: 'Hello  (mapActions)' })">
      set Message by mapActions
    </button>
  </BaseContainer>
</template>

<script>
import BaseContainer from "../../components/vuex/BaseContainer.vue";
import TheCounter from "../../components/vuex/one/TheCounter.vue";
import ChangeCounter from "../../components/vuex/one/ChangeCounter.vue";
import { mapState } from "vuex";
import { mapGetters } from "vuex";
import { mapActions } from "vuex";

export default {
  components: {
    BaseContainer,
    TheCounter,
    ChangeCounter,
  },
  data() {
    return {};
  },
  methods: {
    addOne() {
      this.$store.state.count++; //not good practice
    },
    mutateExample() {
      this.$store.commit("exampleModule/mutateExampleMessage", {
        addedMessage: "Hello (mutations) ",
      });
    },

    setExampleMessageByAction() {
      this.$store.dispatch("exampleModule/setActionExampleMessage", {
        addedMessage: "Hello (actions) ",
      });
    },

    ...mapActions("exampleModule", {setMessage:  "setActionExampleMessage" }),
  },
  computed: {
    ...mapState(["products"]),
    globalStateMessage() {
      return this.$store.getters.getGlobalStateMessage;
    },

    ...mapGetters(["getGlobalStateMessage"]), //will not work because same getters
    //with globModNmSpc

    globalModWithNameSpace() {
      return this.$store.getters["globModNmSpc/getGlobalStateMessage"];
    },
    ...mapGetters("globModNmSpc", ["getGlobalStateMessage"]), //comment line to make other work
    ...mapGetters("exampleModule", ["getExampleMessage"]),
  },
};
</script>

<style scoped></style>
