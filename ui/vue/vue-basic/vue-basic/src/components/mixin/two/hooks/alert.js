import { ref } from "vue";

export default function useAlert(startingVisibility = false) {
  const alertIsVisible = ref(startingVisibility); //startingVisibility is assign a defaul value
  const showAlert = () => (alertIsVisible.value = true);
  const hideAlert = () => (alertIsVisible.value = false);
  return [
        alertIsVisible, 
        showAlert,
        hideAlert];

}
