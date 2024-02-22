<script lang="ts" setup>
defineProps(['title'])
defineEmits(['remove'])
</script>

<template>
  <li>
    {{ title }}
    <button @click="$emit('remove')">Remove</button>
  </li>
</template>