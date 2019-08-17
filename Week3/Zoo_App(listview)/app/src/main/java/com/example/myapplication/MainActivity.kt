package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.animal_ticket.view.*

class MainActivity : AppCompatActivity() {


    var ListOfAnimal=ArrayList<Animal>()
    var animaladapter:AnimalsAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ListOfAnimal.add(Animal("Baboon","Babbon lives in  hole",R.drawable.baboon,false))
        ListOfAnimal.add(Animal("Bulldog","Bulldog lives in thome",R.drawable.bulldog,true))
        ListOfAnimal.add(Animal("Panda","panda lives in  mountain",R.drawable.panda,false))
        ListOfAnimal.add(Animal("Swallow","Swallow lives in  tree",R.drawable.swallow_bird,false))
        ListOfAnimal.add(Animal("Tigger","Tigger lives in  forest",R.drawable.white_tiger,true))


        animaladapter= AnimalsAdapter(this,listofAnimal = ListOfAnimal)

        listviewmain.adapter=animaladapter
    }

    fun delete(index:Int)
    {
        ListOfAnimal.removeAt(index)
        animaladapter!!.notifyDataSetChanged()
    }
    fun add(index: Int)
    {
        ListOfAnimal.add(ListOfAnimal[index])
    }

    inner class AnimalsAdapter:BaseAdapter{

        var listOfAnimal=ArrayList<Animal>()
        var context: Context?=null
        constructor(context:Context,listofAnimal:ArrayList<Animal>):super()
        {
            this.context=context
            this.listOfAnimal=listofAnimal
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            val animal=listOfAnimal[position]
            if(animal.isKiller==false)
            {

                var intflator=context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView=intflator.inflate(R.layout.animal_ticket,null)
                myView.textView.text=listOfAnimal[position].name!!
                myView.textView2.text=listOfAnimal[position].des!!
                myView.imageViewname.setImageResource(animal.image!!)

                myView.buttondel.setOnClickListener {
                    delete(position)
                }
                myView.imageViewname.setOnClickListener {

                    val intent=Intent(context,Animal_Info::class.java)

                    intent.putExtra("name",animal.name!!)
                    intent.putExtra("des",animal.des!!)
                    intent.putExtra("image",animal.image!!)
                    context!!.startActivity(intent)


                }

                return myView

            }
            else
            {

                var intflator=context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView=intflator.inflate(R.layout.animal_killer_ticket,null)
                myView.textView.text=listOfAnimal[position].name!!
                myView.textView2.text=listOfAnimal[position].des!!
                myView.imageViewname.setImageResource(animal.image!!)
                myView.imageViewname.setOnClickListener {

                    val intent=Intent(context,Animal_Info::class.java)

                    intent.putExtra("name",animal.name!!)
                    intent.putExtra("des",animal.des!!)
                    intent.putExtra("image",animal.image!!)

                    context!!.startActivity(intent)
                }
                return myView

            }




        }

        override fun getItem(position: Int): Any {
            return 0
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getCount(): Int {
            return listOfAnimal.size
        }
    }
}
