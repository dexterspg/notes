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
    <p>Global module getter using mapper (comment other mapper SAME NAME to work) : {{ getGlobalStateMessage }}</p>

    
    <p>Global module getter with name space : {{    globalModWithNameSpace }}</p>
    <p>Global module getter with name space  using mapper: {{    getGlobalStateMessage  }}</p>
  </BaseContainer>

  <p>{{ products }}</p>
</template>

<script>
import BaseContainer from "../../components/vuex/BaseContainer.vue";
import TheCounter from "../../components/vuex/one/TheCounter.vue";
import ChangeCounter from "../../components/vuex/one/ChangeCounter.vue";
import { mapState } from "vuex";
import { mapGetters } from "vuex";

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
  },
  computed: {
    ...mapState(["products"]),
    globalStateMessage() {
      return this.$store.getters.getGlobalStateMessage;
    },

    ...mapGetters(['getGlobalStateMessage']), //will not work because same getters
        //with globModNmSpc

    globalModWithNameSpace() {
      return this.$store.getters["globModNmSpc/getGlobalStateMessage"];
    },
    ...mapGetters("globModNmSpc",["getGlobalStateMessage"])  //comment line to make other work
  },
};
</script>

<style scoped></style>
