fun main() {


    val myCar = Car("Toyota","corolla")

     println(myCar.run())

}


class  Car(private val make:String,private val model:String){

    fun run():String{
        return "$make $model is Running"
    }

}
