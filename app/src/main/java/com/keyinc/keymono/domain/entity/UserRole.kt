package com.keyinc.keymono.domain.entity

enum class UserRole(val description: String) {
    Student("Студент"),
    Teacher("Преподаватель"),
    Admin("Администратор"),
    Dean("Деканат")
}