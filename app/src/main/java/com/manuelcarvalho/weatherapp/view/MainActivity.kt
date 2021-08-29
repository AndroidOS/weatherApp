package com.manuelcarvalho.weatherapp.view

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import com.manuelcarvalho.weatherapp.R
import com.manuelcarvalho.weatherapp.databinding.ActivityMainBinding
import com.manuelcarvalho.weatherapp.utils.sendEmail
import com.manuelcarvalho.weatherapp.utils.weatherEmail
import com.manuelcarvalho.weatherapp.viewmodel.ListViewModel

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        viewModel = ViewModelProviders.of(this)[ListViewModel::class.java]

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        viewModel.refresh()

        binding.fab.setOnClickListener { view ->
            //viewModel.refresh()
            Log.d(TAG, weatherEmail)
            sendEmail(this, weatherEmail)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true

            R.id.action_email -> {
                //viewModel.menu_email.value = true
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun makeList(): String{
        return ""
    }

    private fun dialogueQuery(items: Array<CharSequence>): String {

        val manu: List<String> = listOf("1","2")
        val dialogueItems: Array<CharSequence?>? =
            manu.size.let { it1 -> Array(it1, { i -> manu.get(i) }) }


        var manu2 = ""
        val alertDialog: AlertDialog? = this.let {
            val builder = AlertDialog.Builder(it)

            builder.apply {
                setPositiveButton(
                    R.string.ok,
                    DialogInterface.OnClickListener { dialog, id ->
                        // User clicked OK button
                    })
                setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                    })

                setTitle("Choose Cartridge")


                builder.setItems(
                    dialogueItems,

                    DialogInterface.OnClickListener { dialog, which ->
                        manu2 = dialogueItems?.get(which) as String
                        Log.d(TAG, " onClick $manu2   $which")


                    })

            }

            builder.create()
        }

        alertDialog?.show()

        return manu2

    }
}