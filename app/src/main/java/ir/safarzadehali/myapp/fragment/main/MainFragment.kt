package ir.safarzadehali.myapp.fragment.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ir.safarzadehali.myapp.R

class MainFragment : Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNav = view.findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        // استفاده از NavHostFragment.findNavController
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.main_nav) as? NavHostFragment

        if (navHostFragment != null) {
            val navController = navHostFragment.navController
            bottomNav.setupWithNavController(navController)
        } else {
            Log.e("MainFragment", "NavHostFragment هنوز ساخته نشده یا آی‌دی اشتباه است")
        }
    }
}
