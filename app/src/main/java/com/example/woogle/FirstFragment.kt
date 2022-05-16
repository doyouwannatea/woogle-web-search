package com.example.woogle

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
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
    private var searchResultList: MutableList<SearchResult>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        mService = Common.retrofitService
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    private fun webSearch(q: String) {
        searchResultList?.clear()
        binding.recyclerView.adapter?.notifyDataSetChanged()
        binding.textViewLoading.visibility = View.VISIBLE
        binding.textViewNotFound.visibility = View.INVISIBLE

        mService.webSearch(q, 1, 100).enqueue(object : Callback<WebSearchResponce> {
            override fun onFailure(call: Call<WebSearchResponce>, t: Throwable) {
                Log.e("webSearch", "onFailure")
                Log.e("webSearch", t.message!!)
                binding.textViewLoading.visibility = View.INVISIBLE
                binding.textViewNotFound.visibility = View.VISIBLE
            }

            override fun onResponse(
                call: Call<WebSearchResponce>,
                response: Response<WebSearchResponce>
            ) {
                Log.d("webSearch", "onResponse")
                searchResultList = response.body()?.value
                if (searchResultList != null) {
                    val adapter = WebResultAdapter(requireContext(), searchResultList!!)
                    binding.recyclerView.adapter = adapter
                    if (searchResultList!!.isEmpty()) {
                        binding.textViewNotFound.visibility = View.VISIBLE
                    }
                }
                binding.textViewLoading.visibility = View.INVISIBLE
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.wSearchEditText.setOnEditorActionListener { v, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    webSearch(v.text.toString().trim())
                    true
                }
                else -> false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}