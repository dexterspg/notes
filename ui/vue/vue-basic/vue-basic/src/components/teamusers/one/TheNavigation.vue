<template>
    <div>
        <router-link to="/teams">Teams</router-link>
    </div>
    <div>
        <router-link to="/users">Users</router-link>
    </div>
        
</template>

<script>
    export default{
        data(){
            return{
                
            };
        }
    }

</script>

<style scoped>

.router-link-active {
    color : red
}

</style>
