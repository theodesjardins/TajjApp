package fr.isen.desjardins.tajjapp.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import fr.isen.desjardins.tajjapp.Model.StatusModel
import fr.isen.desjardins.tajjapp.R
import kotlinx.android.synthetic.main.status_cell_view.view.*

class StatusAdapter (val users : List<StatusModel>?) : RecyclerView.Adapter<StatusAdapter.UserCellViewHolder> () {
    override fun onBindViewHolder(viewHolder: UserCellViewHolder, index: Int) {
        val user = users?.get(index) ?: StatusModel()
        viewHolder.bind(user)
    }

    override fun getItemCount() = users?.count() ?: 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserCellViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.status_cell_view, parent, false) as View

        return UserCellViewHolder(view)
    }

    class UserCellViewHolder(val view: View): RecyclerView.ViewHolder(view){

        val userName: TextView = view.nameField
        val text: TextView = view.textField
        val image: ImageView = view.userPicture

        fun bind(user: StatusModel){
            userName.text = "${user.name.first} ${user.name.last}"
            text.text = "${user.text.text}"
            Picasso.get().load(user.picture.medium).into(image)
        }

    }

}