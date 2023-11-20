<script setup lang="ts">
import { ref } from 'vue'

const newTodoText = ref('')
const todos = ref([
    {
        id: 1,
        title: 'Do the dishes'
    },
    {
        id: 2,
        title: 'Take out the trash'
    },
    {
        id: 3,
        title: 'Mow the lawn'
    }
])

let nextTodoId = 4

function addNewTodo() {
    todos.value.push({
        id: nextTodoId++,
        title: newTodoText.value
    })
    newTodoText.value = ''
}
</script>

<template>
    <form v-on:submit.prevent="addNewTodo">
        <label for="new-todo">Add a todo</label>
        <input v-model="newTodoText" id="new-todo" placeholder="E.g. Feed the cat" />
        <button>Add</button>
    </form>

    <ul>
        <li v-for="todo in todos" :key="todo.id" :title="todo.title">
            {{ todo.title }}:{{ todo.id }}
            <button @click="todos.splice(todo.id, 1)">Remove</button>
        </li>
    </ul>
</template>