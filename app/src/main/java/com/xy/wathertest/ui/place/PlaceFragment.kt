package com.xy.wathertest.ui.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.xy.wathertest.R
import kotlinx.android.synthetic.main.fragment_place.*

class PlaceFragment: Fragment() {
    val viewModule by lazy { ViewModelProvider(this).get(PlaceViewModule::class.java) }

    private lateinit var adapter: PlaceAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_place, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        recycleView.layoutManager = layoutManager
        adapter = PlaceAdapter(this, viewModule.placeList)
        recycleView.adapter = adapter
        searchPlaceEdit.addTextChangedListener { editable ->
            val content = editable.toString()
            if (content.isNotEmpty()) {
                viewModule.searchPlaces(content)
            } else {
                recycleView.visibility = View.GONE
                bgImageView.visibility = View.VISIBLE
                viewModule.placeList.clear()
                adapter.notifyDataSetChanged()
            }
        }

        viewModule.placeLiveData.observe(viewLifecycleOwner, Observer { result ->
            val places = result.getOrNull()
            if (places != null) {
                recycleView.visibility = View.VISIBLE
                bgImageView.visibility = View.GONE
                viewModule.placeList.clear()
                viewModule.placeList.addAll(places)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(activity, "未能查询到任何地点", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
    }
}