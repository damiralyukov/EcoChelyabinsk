package com.damiralyukov.ecochelyabinsk.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.damiralyukov.ecochelyabinsk.data.Company
import com.damiralyukov.ecochelyabinsk.databinding.RowBinding

class CompanyAdapter : RecyclerView.Adapter<CompanyAdapter.ViewHolder>() {
    var companyList = emptyList<Company>()

    class ViewHolder(private val binding: RowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(company: Company) {
            binding.company = company
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(
                parent
        )
    }

    override fun getItemCount(): Int {
        return companyList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = companyList[position]

        holder.bind(currentItem)
    }

    fun setList(companies: List<Company>) {
        val companyDiffUtil = CompanyDiffUtil(companyList, companies)
        val companyDiffResult = DiffUtil.calculateDiff(companyDiffUtil)

        this.companyList = companies

        companyDiffResult.dispatchUpdatesTo(this)
    }
}