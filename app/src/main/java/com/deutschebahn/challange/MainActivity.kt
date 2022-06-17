package com.deutschebahn.challange

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.deutschebahn.challange.ui.components.AppNavHost
import com.deutschebahn.challange.ui.screen.MainViewModel
import com.deutschebahn.challange.ui.theme.ChallengeComposeTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val scaffoldState = rememberScaffoldState()
            val navController = rememberNavController()

            ChallengeComposeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    AppNavHost(navController, scaffoldState)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChallengeComposeTheme {
        // TBD
    }
}