package com.example.myfinanses.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfinanses.databinding.TransactionItemBinding
import com.example.myfinanses.models.Transaction

class TransactionsAdapter : RecyclerView.Adapter<TransactionsAdapter.ViewHolder>() {

    private val listTransaction = mutableListOf<Transaction>()

    @SuppressLint("NotifyDataSetChanged")
    fun addNewItems(transactions: List<Transaction>) {
        listTransaction.clear()
        listTransaction.addAll(transactions)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: TransactionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(transaction: Transaction) {
            binding.apply {
                name.text = transaction.name
                amount.text = transaction.amount.toString()
                type.text =
                    transaction.type.toString().lowercase().replaceFirstChar { it.uppercase() }
                date.text = transaction.date
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            TransactionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listTransaction[position])
    }

    override fun getItemCount(): Int = listTransaction.size
}