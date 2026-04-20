package com.example.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.app.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ViewPagerAdapter(this)
        binding.edLiViewPager.adapter = adapter

        //ჩემი გვარი იწება დ ასოთი ამიტომ VERTICAL რეჟიმს ვიყენებ
        binding.edLiViewPager.orientation = ViewPager2.ORIENTATION_VERTICAL

        // TabLayout-ის და ViewPager2-ის დაკავშირება
        TabLayoutMediator(binding.edLiTabLayout, binding.edLiViewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Input"       //მონაცემების შეყვანის გვერდი
                1 -> "Analytics"   //ანალიტიკის გვერდი
                else -> "Profile"  //პროფილის გვერდი
            }
        }.attach()
    }
}
