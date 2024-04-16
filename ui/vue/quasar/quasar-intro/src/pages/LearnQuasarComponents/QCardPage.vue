<template>
  <q-page class="flex flex-center">

    <q-card
      class="shadow-1"
      style="max-width: 350px;">
      <q-card-section >

        <q-img src="http://picsum.photos/300/200"/>

        <q-card-section class="text-center">
          <div class="text-h6 text-grey-9">
            My Content
            <div class="text-subtitle2">
            </div>
            John Doe
          </div>
        </q-card-section>
        <q-separator inset/>


        <q-card-section >
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Accusamus accusantium temporibus ex eligendi quo vitae velit quibusdam quam deleniti rem? Aspernatur modi incidunt velit. Assumenda in non voluptatem et vitae?
        </q-card-section>
      </q-card-section>

      <q-card-actions vertical>
  <q-btn
          flat
          label="Podcast"

        />
<q-btn
          flat
          label="link"

        />

      </q-card-actions>

    </q-card>
  </q-page>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
export default defineComponent({
  // name: 'PageName'
});
</script>
