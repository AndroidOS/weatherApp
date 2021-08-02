package com.manuelcarvalho.weatherapp.view

import android.icu.number.NumberFormatter.with
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.manuelcarvalho.weatherapp.R
import com.manuelcarvalho.weatherapp.databinding.FragmentFirstBinding
import com.manuelcarvalho.weatherapp.viewmodel.ListViewModel
import com.squareup.picasso.Picasso
import java.math.BigDecimal
import java.math.RoundingMode


private const val TAG = "FirstFragment"
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private lateinit var viewModel: ListViewModel

    private lateinit var textView: TextView
    private lateinit var txt_desc: TextView
    private lateinit var txt_bar: TextView
    private lateinit var image: ImageView
    private lateinit var txt_hum: TextView

    private lateinit var imageIcon: ImageView

    private var weatherString = ""


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
        txt_bar = view.findViewById(R.id.txt_bar)
        txt_hum = view.findViewById(R.id.txt_hum_num)
        image = view.findViewById(R.id.imageView)
        imageIcon = view.findViewById(R.id.imageIcon)

//        imageIcon.setImageDrawable(
//            context?.let {
//                ContextCompat.getDrawable(
//                    it, // Context
//                    R.drawable.rain // Drawable
//                )
//            }
//        )
        viewModel = activity?.run {
            ViewModelProviders.of(this)[ListViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }

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
                val tmpTemp = it.main?.temp_max?.minus(273.15)
                val maxTemp = tmpTemp?.let { it1 -> BigDecimal(it1).setScale(2, RoundingMode.HALF_EVEN) }
                val description = it.weather?.get(0)?.description
                val barometric = it.main?.pressure
                val humidity = it.main?.humidity
                if (it.weather?.size != null){
                    if (it.weather!!.size > 1){
                        val num = it.weather!!.size
                        Log.d(TAG,"Size = $num")
                    }

                }
                Log.d(TAG, "${barometric}")
                textView.text = maxTemp.toString()
                txt_desc.text = description.toString()
                txt_bar.text = barometric.toString()
                txt_hum.text = humidity.toString() + "%"
            }

        })

        viewModel.weatherIcon.observe(viewLifecycleOwner, Observer { icon ->
            icon?.let {
                var url = "http://openweathermap.org/img/wn/" + "${it}" + "@2x.png"
                Log.d(TAG, "weatherIcon changed $url")
                //  http://openweathermap.org/img/wn/10d@2x.png

                val imageUri = "https://i.imgur.com/tGbaZCY.jpg"

                Picasso.get().load(url).into(imageIcon);


            Picasso.get().load(url).into(imageIcon)
            }
        })
    }
}