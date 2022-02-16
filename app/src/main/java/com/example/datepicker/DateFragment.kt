package com.example.datepicker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StyleRes
import com.example.datepicker.databinding.DateFragmentBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*


class DateFragment : Fragment() {

    lateinit var binding: DateFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = DateFragmentBinding.inflate(layoutInflater)

        binding.dialogBtn.setOnClickListener {
            showDataRangePicker()
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()

    }

    private fun showDataRangePicker() {

        val dateRangePicker =
            MaterialDatePicker
                .Builder.dateRangePicker()
                .setTitleText("Select Date")
                .setTitleText("Date Dialog")

                .build()

        activity?.let {
            dateRangePicker.show(
                it.supportFragmentManager,
                "date_range_picker"
            )
        }

        dateRangePicker.addOnPositiveButtonClickListener { dateSelected ->

            val startDate = dateSelected.first
            val endDate = dateSelected.second

            if (startDate != null && endDate != null) {
                binding.rangeDateTv.text =
                    "Reserved\nStartDate: " + convertLongToTime(startDate) + "\n" +
                            " EndDate: ${convertLongToTime(endDate)}"
            }
        }

    }


    private fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat(
            "dd.MM.yyyy",
            Locale.getDefault()
        )
        return format.format(date)
    }
}