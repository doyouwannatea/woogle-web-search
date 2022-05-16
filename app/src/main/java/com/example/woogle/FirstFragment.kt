package com.example.woogle

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.woogle.adapters.WebResultAdapter
import com.example.woogle.databinding.FragmentFirstBinding
import com.example.woogle.models.SearchResult
import com.example.woogle.models.WebSearchResponce
import com.example.woogle.retrofit.common.Common
import com.example.woogle.retrofit.interfaces.RetrofitServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!
    private lateinit var mService: RetrofitServices

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        mService = Common.retrofitService
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        webSearch()
        return binding.root
    }

    private fun webSearch() {
        mService.webSearch("putin", 1, 10).enqueue(object : Callback<WebSearchResponce> {
            override fun onFailure(call: Call<WebSearchResponce>, t: Throwable) {
                Log.e("webSearch", "onFailure")
                Log.e("webSearch", t.message!!)
            }

            override fun onResponse(
                call: Call<WebSearchResponce>,
                response: Response<WebSearchResponce>
            ) {
                Log.d("webSearch", "onResponse")
                val searchResultList = response.body()?.value
                if (searchResultList != null) {
                    val adapter = WebResultAdapter(requireContext(), searchResultList)
                    adapter.notifyDataSetChanged()
                    binding.recyclerView.adapter = adapter
                }
            }
        })
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}