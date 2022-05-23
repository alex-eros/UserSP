package alex.eros.usersp

data class User(var id: Long,var name:String,var lastName:String,var url:String){

    fun getFullname():String = "$name $lastName"

}

