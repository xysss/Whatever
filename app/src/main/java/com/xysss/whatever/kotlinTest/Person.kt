package com.sin.kotlintest

open class Person (val name:String,val age:Int){  //open 开发集继承
    fun eat(){
        println(name+"is eating. He is "+age+" years old.")
    }
}