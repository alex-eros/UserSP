package alex.eros.usersp

import alex.eros.usersp.databinding.ActivityMainBinding
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity(),OnClickListener {

    private lateinit var userAdapter:UserAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var linearLayoutManager:RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userAdapter = UserAdapter(getUsers(),this)
        linearLayoutManager = LinearLayoutManager(this)

        val preferences = getPreferences(Context.MODE_PRIVATE)
        /*Se crea un instancia de SP*/

        val isFirstTime = preferences.getBoolean(getString(R.string.sp_first_time),true)
        /*Así se recupera un valor almacenado en SP*/

        Log.i("SP","${getString(R.string.sp_first_time)}= $isFirstTime")
        Log.i("SP","${getString(R.string.sp_username)}= ${preferences.getString(getString(R.string.sp_username),"NA")}")
        if(isFirstTime) {
            val dialogView = layoutInflater.inflate(R.layout.dialog_register,null)
            MaterialAlertDialogBuilder(this)
                .setTitle(R.string.dialog_tittle)
                .setView(dialogView)
                .setPositiveButton(R.string.dialog_confirm) { dialogInterface, i ->
                    val username = dialogView.findViewById<TextInputEditText>(R.id.editText_UserName).text.toString()
                    with(preferences.edit()) {
                        putBoolean(getString(R.string.sp_first_time), false)
                        putString(getString(R.string.sp_username), username)
                            .apply()
                    }
                    Toast.makeText(this, R.string.succes, Toast.LENGTH_SHORT).show()
                }
                .show()
        }else{
            val username = preferences.getString(getString(R.string.sp_username),getString(R.string.hint_userName))
            Toast.makeText(this,"Welcome $username",Toast.LENGTH_SHORT).show()
        }

        binding.RVUSER.apply {
            setHasFixedSize(true)
            layoutManager=linearLayoutManager
            adapter=userAdapter
        }
    }

    private fun getUsers():MutableList<User>{

        val users= mutableListOf<User>()

        val user1 = User(1,"DIDIER","EROSA","https://www.galeriabat.com/image/mudZri4x46tS4cEKY/0/didier-lourenco.jpg")
        val user2 = User(2,"NAOMI","EROSA","https://imgwoman.elperiodico.com/45/50/2d/gettyimages-927381664-1-1200.jpg")
        val user3 = User(3,"Victor","Tellez","https://img.a.transfermarkt.technology/portrait/header/67867-1553769218.jpg?lm=1")
        val user4= User(4,"Llily","Perez","https://images.reporteindigo.com/wp-content/uploads/2021/01/105985909_3220393008186598_237791461424613817_n.jpg")

        users.add(user1)
        users.add(user2)
        users.add(user3)
        users.add(user4)
        users.add(user1)
        users.add(user2)
        users.add(user3)
        users.add(user4)
        users.add(user1)
        users.add(user2)
        users.add(user3)
        users.add(user4)

        return users
    }

    override fun Onclick(user: User,position:Int) {
        Toast.makeText(this,"${position+1} ${user.getFullname()}",Toast.LENGTH_SHORT).show()
    }

}