package com.example.myfinanses.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfinanses.R
import com.example.myfinanses.databinding.TransactionItemBinding
import com.example.myfinanses.models.Transaction
import com.example.myfinanses.models.TypeTransaction
import com.example.myfinanses.ui.extensions.setColor

class TransactionsAdapter(val deleteTransaction: (Transaction) -> Unit) :
    RecyclerView.Adapter<TransactionsAdapter.ViewHolder>() {

    private val listTransaction = mutableListOf<Transaction>()

    @SuppressLint("NotifyDataSetChanged")
    fun addNewItems(transactions: List<Transaction>) {
        listTransaction.clear()
        listTransaction.addAll(transactions)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: TransactionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(transaction: Transaction) {
            binding.apply {
                name.text = transaction.name
                amount.text = "${transaction.amount} $CURRENCY"
                type.text =
                    transaction.type.toString().lowercase().replaceFirstChar { it.uppercase() }
                date.text = transaction.date
                when (transaction.type) {
                    TypeTransaction.INCOME -> incomeStyle(this)
                    TypeTransaction.EXPENSE -> expenseStyle(this)
                }
            }
        }

        private fun incomeStyle(binding: TransactionItemBinding) {
            binding.apply {
                root.setColor(R.color.incomes)
                amount.setColor(R.color.green)
            }
        }

        private fun expenseStyle(binding: TransactionItemBinding) {
            binding.apply {
                root.setColor(R.color.expense)
                amount.setColor(R.color.red)
            }
        }

        companion object {
            private const val CURRENCY = "zł"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            TransactionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listTransaction[position])

        holder.binding.delete.setOnClickListener {
            deleteTransaction(listTransaction[position])
        }
    }

    override fun getItemCount(): Int = listTransaction.size
}