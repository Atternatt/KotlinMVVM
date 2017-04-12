/*
 * Copyright (c) 2016 Marc Moreno
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.m2f.kotlinmvvm.presentation.search

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.m2f.kotlinmvvm.R
import com.m2f.kotlinmvvm.databinding.RowConcertBinding
import com.m2f.kotlinmvvm.domain.concert.Concert

/**
 * @author Marc Moreno
 * @version 1.0
 */
class ConcertListAdapter(private val concertList: List<Concert>) : RecyclerView.Adapter<ConcertListAdapter.ConcertViewHolder>() {

    private val concerts: MutableList<Concert> = concertList.toMutableList()

    operator fun plusAssign(newList: List<Concert>) {
        concerts.apply { clear(); addAll(newList); notifyDataSetChanged() }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConcertViewHolder {
        val binding = DataBindingUtil.inflate<RowConcertBinding>(LayoutInflater.from(parent.context), R.layout.row_concert,
                parent, false)
        return ConcertViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ConcertViewHolder, position: Int) {
        holder.bind(concerts[position])
    }

    override fun getItemCount(): Int {
        return concerts.size
    }

    inner class ConcertViewHolder(private val binding: RowConcertBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(event: Concert) {
            binding.event = event
        }
    }
}
