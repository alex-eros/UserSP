package alex.eros.usersp

import alex.eros.usersp.databinding.UserListCardviewBinding
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class UserAdapter(private val users:List<User>, private val listener:OnClickListener) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private lateinit var context:Context

    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val binding = UserListCardviewBinding.bind(view)

        fun setListener(user:User,position: Int){
            binding.root.setOnClickListener{listener.Onclick(user,position)}
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.user_list_cardview,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]

        with(holder){
            setListener(user,position)
            binding.TxtVOrder.text = (position+1).toString()
            binding.TxtVName.text= user.getFullname()
            Glide.with(context)
                .load(user.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop()
                .into(binding.ImageUser)
        }
    }

    override fun getItemCount(): Int = users.size
}