package com.example.myfinanses.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfinanses.data.Invest
import com.example.myfinanses.databinding.InvestItemBinding

class InvestAdapter(val deleteInvest: (Invest) -> Unit) :
    RecyclerView.Adapter<InvestAdapter.ViewHolder>() {

    private val listInvest = mutableListOf<Invest>()

    @SuppressLint("NotifyDataSetChanged")
    fun addNewItems(invests: List<Invest>) {
        listInvest.clear()
        listInvest.addAll(invests)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: InvestItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
            @SuppressLint("SetTextI18n")
            fun bind(invest: Invest) {
                binding.apply {
                    investItemName.text = invest.assetName.name
                    investItemAmount.text = "${invest.amount}"
                    investItemDate.text = invest.date
                    investItemPrice.text = "${invest.buyPrice} $CURRENCY"
                }
            }

            companion object {
                private const val CURRENCY = "$"
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = InvestItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listInvest[position])

        holder.binding.investItemDelete.setOnClickListener {
            deleteInvest(listInvest[position])
        }
    }

    override fun getItemCount() = listInvest.size
}