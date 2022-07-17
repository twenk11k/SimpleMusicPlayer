package com.twenk11k.simplemusicplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.twenk11k.simplemusicplayer.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setNavigationController()
    }

    private fun setNavigationController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_host_container) as NavHostFragment
        navController = navHostFragment.navController
    }
}
