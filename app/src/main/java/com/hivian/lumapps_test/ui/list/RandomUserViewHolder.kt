package com.hivian.lumapps_test.ui.list

import androidx.recyclerview.widget.RecyclerView
import com.hivian.lumapps_test.business.model.domain.RandomUserDomain
import com.hivian.lumapps_test.databinding.RandomUserItemBinding
import com.hivian.lumapps_test.presentation.home.HomeViewModel

class RandomUserViewHolder(
    private val binding: RandomUserItemBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(viewModel: HomeViewModel, item: RandomUserDomain) {
        binding.viewModel = viewModel
        binding.article = item
        binding.executePendingBindings()
    }
}
