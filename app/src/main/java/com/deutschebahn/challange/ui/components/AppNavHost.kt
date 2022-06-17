package com.deutschebahn.challange.ui.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import com.deutschebahn.challange.ui.detail.MovieDetailViewModel
import com.deutschebahn.challange.ui.detail.MovieDetailScreen
import com.deutschebahn.challange.ui.screen.MainScreen
import com.deutschebahn.challange.ui.screen.MainViewModel
import com.deutschebahn.challange.ui.search.MovieSearchScreen
import com.deutschebahn.challange.ui.search.MovieSearchViewModel
import org.koin.core.context.GlobalContext

enum class NavPath(
    val route: String,
) {
    MovieLatest(route = "movie_latest"),
    SearchView(route = "movie_search"),
    MovieDetail(route = "movie_details")
}

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun AppNavHost(
    navHostController: NavHostController,
    scaffoldState: ScaffoldState
) {
    val koin by lazy { GlobalContext.get() }

     NavHost(
        navController = navHostController,
        startDestination = NavPath.MovieLatest.route
    ) {
        composable(NavPath.MovieLatest.route) {
            val mainViewModel = koin.get<MainViewModel>()

            MainScreen(
                router = { navHostController.navigate(it) },
                mainViewModel = mainViewModel
            )
        }
        composable("${NavPath.MovieDetail.route}?id={id}", arguments = listOf(
            navArgument("id") {
                type = NavType.LongType
            })) {
            val id = it.arguments?.getLong("id")
            val movieDetailViewModel = koin.get<MovieDetailViewModel>()
            movieDetailViewModel.start(requireNotNull(id))

            MovieDetailScreen(
                onBackClick = { navHostController.popBackStack() },
                detailViewModel = movieDetailViewModel
            )
        }
        composable(NavPath.SearchView.route) {
            val movieSearchViewModel = koin.get<MovieSearchViewModel>()

            MovieSearchScreen(
                onBack = { navHostController.popBackStack() },
                movieSearchViewModel = movieSearchViewModel
            )
        }
    }
}