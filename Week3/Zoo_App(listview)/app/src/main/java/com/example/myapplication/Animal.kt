package com.example.myapplication

class Animal {

    var name:String?=null
    var des:String?= null
    var image:Int?=null
    var isKiller:Boolean?=null
    constructor(name:String,des:String,image:Int,killer:Boolean)
    {
        this.name=name
        this.des=des
        this.image=image
        this.isKiller=killer
    }
}