package com.example.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.app.databinding.FragmentInputBinding

class InputFragment : Fragment() {

    private var _binding: FragmentInputBinding? = null
    private val binding get() = _binding!!

    // ლოგიკის გატანა
    private val wealthManager = WealthManager()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.edLiBtnSave.setOnClickListener {
            val incomeStr = binding.edLiEtIncome.text.toString()
            val expensesStr = binding.edLiEtExpenses.text.toString()

            //ვალიდაცი
            if (validate(incomeStr, expensesStr)) {
                val income = incomeStr.toDouble()
                val expenses = expensesStr.toDouble()
                
                //გამოთვლა WealthManager-ის მეშვეობით
                val finalSavings = wealthManager.calculateFinalSavings(income, expenses)
                
                //მონაცემების გადაცემა სხვადასხვა გასაღებით, რომ ორივე ფრაგმენტმა მიიღოს
                setFragmentResult("analyticsKey", bundleOf("bundleKey" to finalSavings))
                setFragmentResult("profileKey", bundleOf("bundleKey" to finalSavings))
                
                //ავტომატური გადასვლა  მეორე გვერძე (
                val viewPager = requireActivity().findViewById<androidx.viewpager2.widget.ViewPager2>(R.id.ed_li_view_pager)
                viewPager.setCurrentItem(1, true)
                
                Toast.makeText(requireContext(), "Calculated Successfully!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // ვალიდაციის ლოგიკა
    private fun validate(income: String, expenses: String): Boolean {
        var isValid = true
        if (income.isEmpty()) {
            binding.edLiEtIncome.error = "გთხოვთ შეიყვანოთ შემოსავალი"
            isValid = false
        }
        if (expenses.isEmpty()) {
            binding.edLiEtExpenses.error = "გთხოვთ შეიყვანოთ ხარჯები"
            isValid = false
        }
        return isValid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Memory Leak-ის პრევენცია
        _binding = null
    }
}
