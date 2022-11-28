package hu.aut.bme.treasurehuntingfrontend

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import hu.aut.bme.treasurehuntingfrontend.domain.User
import hu.aut.bme.treasurehuntingfrontend.network.Interactor

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent: Intent = intent
        Interactor.TOKEN = intent.getStringExtra("token").toString()
        User.ID = intent.getStringExtra("user_id")!!.toLong()
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_leaderboard,
            R.id.navigation_map,
            R.id.navigation_profile,
            R.id.navigation_quests)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}