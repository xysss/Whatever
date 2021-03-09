package com.sin.kotlintest

import javax.crypto.KeyAgreement

class Student(val sno: String, val grade: Int, name: String, age: Int) : Person(name, age),Study {

    init {
        println("name: " + name + " age: " + age)
    }
    constructor(name: String,age: Int):this("",0,name,age){  //次构造函数

    }
    constructor():this("",0){
    }

    override fun readBooks() {
        println(name+"is reading")
    }

    override fun doHomework() {
        println(name+"is doingHomework")
    }
}