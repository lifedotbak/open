<script setup lang="ts">

import { ref } from 'vue';

const count = ref(0);

function increment() {
    count.value++
}

</script>

<template>
    <div>
        <div class="login">
            <h1>测试页面</h1>
        </div>
        <div id="testApp">
            <button @click="increment">测试:{{ count }}</button>
        </div>
    </div>
</template>

<style></style>
