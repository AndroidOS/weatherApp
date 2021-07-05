package com.manuelcarvalho.weatherapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.manuelcarvalho.weatherapp.R
import com.manuelcarvalho.weatherapp.databinding.FragmentFirstBinding
import com.manuelcarvalho.weatherapp.viewmodel.ListViewModel

private const val TAG = "FirstFragment"
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private lateinit var viewModel: ListViewModel

    private lateinit var textView: TextView
    private lateinit var txt_desc: TextView
    private lateinit var image: ImageView


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textView = view.findViewById(R.id.textview_first)
        txt_desc = view.findViewById(R.id.txt_clouds)
        image = view.findViewById(R.id.imageView)

        image.setImageDrawable(
            context?.let {
                ContextCompat.getDrawable(
                    it, // Context
                    R.drawable.rain // Drawable
                )
            }
        )
        viewModel = activity?.run {
            ViewModelProviders.of(this)[ListViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun observeViewModel() {

        viewModel.weatherEvent.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                Log.d(TAG, "weatherEvent changed")
                val a = it.main?.feels_like?.minus(273.15)
                val maxTemp = it.main?.temp_max?.minus(273.15)
                val description = it.weather?.get(0)?.description
                if (it.weather?.size != null){
                    if (it.weather!!.size > 1){
                        val num = it.weather!!.size
                        Log.d(TAG,"Size = $num")
                    }

                }
                Log.d(TAG, "${description}")
                textView.text = maxTemp.toString()
                txt_desc.text = description.toString()
            }

        })
    }
}