package com.project.livecoinwatch.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.project.livecoinwatch.utils.convertTimestampToTime
import com.project.livecoinwatch.utils.cutNumbers
import com.project.livecoinwatch.R
import com.project.livecoinwatch.data.api.ApiFactory.BASE_IMAGE_URL
import com.project.livecoinwatch.databinding.ItemCoinInfoBinding
import com.project.livecoinwatch.domain.CryptoEntity
import com.squareup.picasso.Picasso


class CryptoAdapter(
    private val context: Context
) : ListAdapter<CryptoEntity, CryptoViewHolder>(CryptoDiffCallback) {

    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val binding = ItemCoinInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CryptoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        val coin = getItem(position)
        with(holder.binding) {
            with(coin) {
                val symbolsTemplate = context.resources.getString(R.string.symbols_template)
                tvSymbols.text = String.format(symbolsTemplate, fromSymbol, toSymbol)
                tvPrice.text = cutNumbers(price)
                tvLastUpdate.text = convertTimestampToTime(lastUpdate)
                Picasso.get().load(BASE_IMAGE_URL+ imageUrl).into(ivLogoCoin)
                root.setOnClickListener {
                    onCoinClickListener?.onCoinClick(this)
                }
            }
        }
    }

    interface OnCoinClickListener {
        fun onCoinClick(coinPriceInfo: CryptoEntity)
    }
}