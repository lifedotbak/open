<script setup lang="ts">

import { ref, reactive, computed } from 'vue'

const count = ref(0);

function increment() {
    count.value++
}

const count_object = { id: ref(1) }

const obj = ref({
    nested: { count: 0 },
    arr: ['foo', 'bar']
})

function mutateDeeply() {
    // 以下都会按照期望工作
    obj.value.nested.count++
    obj.value.arr.push('baz')
}


const author = reactive({
    name: 'John Doe',
    books: [
        'Vue 2 - Advanced Guide',
        'Vue 3 - Basic Guide',
        'Vue 4 - The Mystery'
    ]
})

// 一个计算属性 ref
const publishedBooksMessage = computed(() => {
    return author.books.length > 0 ? 'Yes' : 'No'
})

const now = computed(() => Date.now())

const firstName = ref('John')
const lastName = ref('Doe')

const fullName = computed({
    // getter
    get() {
        return firstName.value + ' ' + lastName.value
    },
    // setter
    set(newValue) {
        // 注意：我们这里使用的是解构赋值语法
        [firstName.value, lastName.value] = newValue.split(' ')
    }
})


const isActive = ref(true)
const hasError = ref(false)


const classObject = reactive({
    active: true,
    'text-danger': false
})

const activeClass = ref('active')
const errorClass = ref('text-danger')

const awesome = ref(true)

const type = ref('c')

const ok = ref('c')


const items = ref([{ message: 'Foo' }, { message: 'Bar' }])

const myObject = reactive({
    title: 'How to do lists in Vue',
    author: 'Jane Doe',
    publishedAt: '2016-04-10'
})


function warn(message, event) {
    // 这里可以访问原生事件
    if (event) {
        event.preventDefault()
    }
    alert(message)
}


</script>

<template>
    <div>




        <li v-for="n in 10" :key="n">{{ n }}</li>

        <ul>
            <li v-for="book in myObject" :key="book">
                {{ book }}
            </li>
        </ul>

        <li v-for="item in items" :key="item.message">
            {{ item.message }}
        </li>

        <h1 v-show="ok">Hello!</h1>

        <div class="login">
            <h1>测试页面</h1>
        </div>

        <button @click="awesome = !awesome">Toggle</button>
        <button @click="warn('Form cannot be submitted yet.', $event)">xxx</button>

        <h1 v-if="awesome">Vue is awesome!</h1>
        <h1 v-else>Oh no 😢</h1>

        <div :class="{ active: isActive }">======>active: isActive is true</div>
        <div class="static" :class="{ active: isActive, 'text-danger': hasError }">======>active: isActive is
            true，hasError===>{{ hasError }}</div>

        <div :class="classObject">=======>classObject</div>
        <div :class="[activeClass, errorClass]">array</div>

        <p>Has published books:</p>
        <span>{{ author.books.length > 0 ? 'Yes' : 'No' }}</span><br>
        <span>{{ publishedBooksMessage }}</span><br>
        <span>{{ now }}</span><br>
        <span>{{ fullName }}</span><br>


        <div v-if="type === 'A'">
            A
        </div>
        <div v-else-if="type === 'B'">
            B
        </div>
        <div v-else-if="type === 'C'">
            C
        </div>
        <div v-else>
            Not A/B/C
        </div>


        <div id="testApp">
            <button @click="increment">测试Increment=======>{{ count }}</button>
        </div>
        <div id="testDeeply">
            <button @click="mutateDeeply">测试MutateDeeply=======>{{ obj.arr }}</button>
        </div>
        <div id="count_object">
            <button>{{ count_object.id }}</button>
        </div>
    </div>
</template>

<style></style>
