<template>
  <q-page padding>
    <q-table
      :columns="columns"
      :rows="data"
    >
    <template #body-cell-id="props">
        <q-td :props="props">
        {{ props.row.id }}
        </q-td>
      </template>
      <template #body-cell-name="props">
        <q-td :props="props">
          <q-input
          v-model="props.row.name"
          color="primary"
          borderless
          dense
          />
        </q-td>
      </template>

      <template #body-cell-email="props">
        <q-td :props="props">
          <q-chip
          class="text-white"
          color="primary">
         {{ props.row.email }}
          </q-chip>
        </q-td>
      </template>

      <template #top>
        <q-btn
          round
          color="primary"
          icon="add"
          @click="showDialog = true"
        >
        </q-btn>

      <q-dialog v-model="showDialog"
        >
          <q-card
            class="full-width"
            style="max-width:450px"
          >
            <q-toolbar>
              <q-toolbar-title>Create User</q-toolbar-title>
              <q-btn
                v-close-popup
                flat
                round
                icon="close"
                size="sm"
              />
            </q-toolbar>
            <q-card-section>
              <q-input
                v-model="createUserForm.name"
                class="q-mb-md"
                filled
                label="name"
              />

              <q-input
                v-model="createUserForm.email"
                filled
                label="email"/>

            </q-card-section>
        <q-btn
            class="full-width"
            label="Create"
            color="primary"
            @click="createUser"

          />
          </q-card>

        </q-dialog>

      </template>

    </q-table>
  </q-page>
</template>

<script  setup>

import { ref } from 'vue';

const data = ref([
  {
    id: 1,
    name: 'John Doe',
    email: 'johndoe@gmail.com',
  },
  {
    id: 2,
    name: 'Bob Builder',
    email: 'bobbuilder@gmail.com',
  },
  {
    id: 3,
    name: 'Jane Doe',
    email: 'janedoe@gmail.com',
  },
  {
    id: 4,
    name: 'Van Auger',
    email: 'vanauger@gmail.com',
  },
  {
    id: 5,
    name: 'Trafalgar Law',
    email: 'trafalgarlaw@gmail.com',
  },
]);


const columns = [
  {
    field: 'id',
    name: 'id',
    align: 'center',
    label: 'ID',
  },
  {
    field: 'name',
    name: 'name',
    align: 'left',
    label: 'Name',
  },
  {
    field: 'email',
    // field: (row) => 'email: ' + row.email,
    name: 'email',
    align: 'left',
    label: 'Primary Email',
  },
];

const showDialog = ref(true);

const createUser =() => {
  showDialog.value =false;
}

const createUserForm= ref({
  email: '',
  name: ''
})


</script>
