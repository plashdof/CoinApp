package com.week2.coinapp.view.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.week2.coinapp.R
import com.week2.coinapp.dataModel.CurrentPriceResult
import com.week2.coinapp.databinding.IntroCoinItemBinding

class SelectRVAdapter(val context : Context, val coinPriceList : List<CurrentPriceResult>):RecyclerView.Adapter<SelectRVAdapter.ViewHolder>(){

    val likedCoinList = ArrayList<String>()

    inner class ViewHolder(private val viewBinding: IntroCoinItemBinding) : RecyclerView.ViewHolder(viewBinding.root){
        val coinName : TextView = viewBinding.coinName
        val coinPriceUpDown : TextView = viewBinding.coinPriceUpDown
        val likeImage : ImageView = viewBinding.likeBtn
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = IntroCoinItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.coinName.text = coinPriceList[position].coinName

        val fluctate_24H = coinPriceList[position].coinInfo.fluctate_24H

        if(fluctate_24H.contains("-")){
            holder.coinPriceUpDown.text = "하락입니다."
            holder.coinPriceUpDown.setTextColor(Color.BLUE)
        }else{
            holder.coinPriceUpDown.text = "상승입니다."
            holder.coinPriceUpDown.setTextColor(Color.RED)
        }

        val likeImage = holder.likeImage
        val currentCoin = coinPriceList[position].coinName

        // view 를 그려줄때  에러방지 처리
        if(likedCoinList.contains(currentCoin)){
            likeImage.setImageResource(R.drawable.like_red)
        }else{
            likeImage.setImageResource(R.drawable.like_grey)
        }


        likeImage.setOnClickListener{


            if(likedCoinList.contains(currentCoin)){
                // likedCoinList 에 포함하면
                likedCoinList.remove(currentCoin)
                likeImage.setImageResource(R.drawable.like_grey)

            }else{
                // likedCoinList 에 포함하지 않으면
                likedCoinList.add(currentCoin)
                likeImage.setImageResource(R.drawable.like_red)
            }



        }

    }

    override fun getItemCount(): Int {
        return coinPriceList.size
    }

}