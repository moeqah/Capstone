package com.littlelemon.littlelemonapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.littlelemon.littlelemonapp.ui.theme.LittleLemonAppTheme
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


    class MainActivity : ComponentActivity() {

        private val httpClient = HttpClient(Android) {
            install(ContentNegotiation) {
                json(contentType = ContentType("text", "plain"))
            }
        }

        private val database by lazy {
            Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database").build()
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)


            val sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE)

            fun readPreferences() : Map<String, String> {
                val savedFirstName = sharedPreferences.getString("firstName", "")!!
                val savedLastName = sharedPreferences.getString("lastName", "")!!
                val savedEmail = sharedPreferences.getString("email", "")!!

                return mapOf("firstName" to savedFirstName, "lastName" to savedLastName, "email" to savedEmail)
            }

            fun savePreferences(firstName : String, lastName : String, email : String) {
                sharedPreferences.edit()
                    .putString("firstName", firstName)
                    .putString("lastName", lastName)
                    .putString("email", email)
                    .commit()
            }

            lifecycleScope.launch(Dispatchers.IO) {
                if (database.menuItemDao().isEmpty()) {
                    val menuItemsNetwork = fetchMenu()
                    saveMenuToDatabase(menuItemsNetwork)
                }
            }

            setContent {
                LittleLemonAppTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        val databaseMenuItems by database.menuItemDao().getAll().observeAsState(emptyList())
                        val navController = rememberNavController()

                        MyNavigationComposable(navController = navController, ::readPreferences, ::savePreferences, databaseMenuItems)
                    }
                }
            }
        }

        private suspend fun fetchMenu(): List<MenuNetwork.MenuItemNetwork> {
            return httpClient
                .get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
                .body<MenuNetwork>()
                .menu
        }

        private fun saveMenuToDatabase(menuItemsNetwork: List<MenuNetwork.MenuItemNetwork>) {
            val menuItemsRoom = menuItemsNetwork.map{ it.toMenuItemRoom() }
            database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
        }
    }




