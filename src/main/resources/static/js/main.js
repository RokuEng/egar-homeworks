var userApi = Vue.resource('/user{/id}');
var personApi = Vue.resource('/person{/id}');

Vue.component('user-row', {
    props: ['user'],
    template:
        '<tr>' +
        '   <td>{{ user.id }}</td>' +
        '   <td>{{ user.nickname }}</td>' +
        '   <td>{{ user.firstname }}</td>' +
        '   <td>{{ user.lastname }}</td>' +
        '</tr>'
});

Vue.component('user-form', {
    props: ['users'],
    data: function () {
        return {
            nickname: '',
            firstname: '',
            lastname: ''
        }
    },
    template:
        '<div>' +
            '<input type="text" placeholder="Введите ник" v-model="nickname">' +
            '<input type="text" placeholder="Введите имя" v-model="firstname">' +
            '<input type="text" placeholder="Введите фамилию" v-model="lastname">' +
            '<input type="button" value="save" @click="save">' +
        '</div>',
    methods: {
        save: function () {
            var user = { nickname: this.nickname, firstname: this.firstname, lastname: this.lastname};

            console.log(user)

            userApi.save({}, user).then(result =>
                result.json().then(data => {
                    console.log(data)
                    this.users.push(data);
                    this.nickname = ''
                    this.firstname = ''
                    this.lastname = ''
                })
            )
        }
    }
});

Vue.component('person-form', {
    props: ['persons'],
    data: function () {
        return {
            nickname: ''
        }
    },
    template:
        '<div>' +
        '<input type="text" placeholder="Введите ник" v-model="nickname">' +
        '<input type="button" value="save" @click="save">' +
        '</div>',
    methods: {
        save: function () {
            var person = { nickname: this.nickname };

            personApi.save({}, person).then(result =>
                result.json().then(data => {
                    this.persons.push(data);
                    this.nickname = ''
                })
            )
        }
    }
});

Vue.component('users-list', {
    props: ['users'],
    template:
        '<div>' +
            '<user-form :users="users" />' +
            '<hr/>' +
            '<table border="1" >' +
                '<tr>' +
                    '<td>Айди</td>' +
                    '<td>Ник</td>' +
                    '<td>Имя</td>' +
                    '<td>Фамилия</td>' +
                '</tr>' +
                '<user-row v-for="user in users" :key="user.id" :user="user"/>' +
            '</table>' +
        '</div>',
    created: function () {
        userApi.get().then(result =>
            result.json().then(data =>
                data.forEach(user => this.users.push(user))
            )
        )
    }
});

Vue.component('person-row', {
    props: ['person'],
    template:
        '<div> ' +
        '   <i> ({{ person.id }}) </i> ' +
        '   {{ person.nickname }}' +
        '</div>'
});

Vue.component('persons-list', {
    props: ['persons'],
    template:
        '<div>' +
            '<person-form :persons="persons" />' +
        '</div>',
    created: function () {
        personApi.get().then(result =>
            result.json().then(data =>
                data.forEach(person => this.persons.push(person))
            )
        )
    }
});

var personsVue = new Vue({
    el: '#persons',
    template:
        '<persons-list :persons="persons" />'
    ,
    data: {
        persons: []
    }
});

var usersVue = new Vue({
    el: '#users',
    template:
        '<users-list :users="users" />'
    ,
    data: {
        users: []
    }
});

