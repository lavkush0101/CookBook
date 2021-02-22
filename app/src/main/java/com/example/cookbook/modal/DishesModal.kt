package com.example.cookbook.modal


class DishesModal {
      var name:String?=null
      var photo:String?=null
      var type:String?=null
      var prepTime:String?=null
      var cookTime:String?=null
      var commentList:String?=null
      constructor( name: String, photo: String,type: String ,prepTime:String,cookTime:String, commentList:String){
          this.name=name
          this.photo=photo
          this.type=type
          this.prepTime=prepTime
          this.cookTime=cookTime
          this.commentList=commentList
      }
    constructor()
}
