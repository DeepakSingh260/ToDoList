package com.example.todolist.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.todolist.R
import com.example.todolist.activity.fragment.Game
import com.example.todolist.activity.fragment.List
import com.example.todolist.activity.fragment.profile
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar: Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView

    var bool:Boolean =false

    var previousItem : MenuItem? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (intent!=null){
            bool = intent.getBooleanExtra("task",false)
        }


        drawerLayout = findViewById(R.id.drawer)
        coordinatorLayout = findViewById(R.id.coordinator)
        toolbar = findViewById(R.id. toolbar)
        frameLayout = findViewById(R.id.frame)
        navigationView = findViewById(R.id.navigation_view)
        setuptoolbar()
        if (bool==true){
            openprofile()
            bool = false
        }else {

            opendashborad()
        }
        val actionBarDrawerToggle = ActionBarDrawerToggle(this@MainActivity , drawerLayout , R.string.open_nav,R.string.close_nav)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {
            if (previousItem!=null){
                previousItem?.isChecked = false
            }
            it.isCheckable=true
            it.isChecked=true
            previousItem = it

            when (it.itemId){
                R.id.list-> {
                    opendashborad()
                    drawerLayout.closeDrawers()

                }
                R.id.game -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frame,Game()).commit()
                    drawerLayout.closeDrawers()
                    supportActionBar?.title="Game"
                }
                R.id.profile-> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frame,
                        profile()
                    ).
                    commit()
                    drawerLayout.closeDrawers()
                    supportActionBar?.title="Profile"
                }
            }
            return@setNavigationItemSelectedListener true
        }

    }

    fun setuptoolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title = "ToDo List"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }

    fun opendashborad() {
        val fragment = List()
        supportFragmentManager.beginTransaction().replace(R.id.frame,fragment).commit()
        supportActionBar?.title = "List"
        navigationView.setCheckedItem(R.id.list)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        if(id==android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onBackPressed() {

        val frag = supportFragmentManager.findFragmentById(R.id.frame)
        when(frag){
            !is List -> opendashborad()
            else ->  super.onBackPressed()
        }

    }

    fun openprofile(){
        val fragment = profile()
        supportFragmentManager.beginTransaction().replace(R.id.frame,fragment).commit()
        supportActionBar?.title = "Profile"
        navigationView.setCheckedItem(R.id.profile)

    }
}
