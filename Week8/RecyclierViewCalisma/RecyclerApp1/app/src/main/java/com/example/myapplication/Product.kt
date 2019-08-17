package com.example.myapplication

class Product {

    var name:String
    var price:Int

    var photo:Int//we're going to upload our fotos to drawable

    constructor(name:String,price:Int,photo:Int)
    {

        this.name=name
        this.price=price
        this.photo=photo

    }
}