package id.accurate.pos.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.accurate.pos.data.local.entity.UserEntity
import id.accurate.pos.databinding.ItemRowUserBinding

class MainAdapter : PagedListAdapter<UserEntity, MainAdapter.MyViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserEntity>(){
            override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MyViewHolder {
        val itemRowUserBinding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemRowUserBinding)
    }

    override fun onBindViewHolder(holder: MainAdapter.MyViewHolder, position: Int) {
        val user = getItem(position)
        if (user != null){
            holder.bind(user)
        }
    }

    inner class MyViewHolder(private val binding : ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(user : UserEntity){
            with(binding){
                tvName.text = user.name
                tvCity.text = user.city
                tvAddress.text = user.address
                tvPhone.text = user.phoneNumber
                tvEmail.text = user.email
            }
        }
    }

}