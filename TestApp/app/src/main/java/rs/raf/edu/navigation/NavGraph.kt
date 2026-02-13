package rs.raf.edu.navigation
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import rs.raf.edu.ui.breedsdetails.BreedsDetailsScreen
import rs.raf.edu.ui.breedslist.BreedsListScreen
import rs.raf.edu.ui.breedslist.BreedsListViewModel


sealed class Screen(val route: String) {
    object BreedsList: Screen("breeds_list")
    object BreedDetails : Screen("breed_details/{breedId}") {
        fun createRoute(breedId: String) = "breed_details/$breedId"
    }
}

@Composable
fun AppNavGraph(navController: NavController) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.BreedsList.route
    ) {
        composable(Screen.BreedsList.route) {
            val listViewModel: BreedsListViewModel = hiltViewModel()
            BreedsListScreen(
                navController = navController,
                viewModel = listViewModel
            )
        }

        composable(
            route = Screen.BreedDetails.route,
            arguments = listOf(
                navArgument("breedId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val breedId = backStackEntry.arguments?.getString("breedId") ?: return@composable
            BreedsDetailsScreen(
                breedId = breedId,
                navController = navController
            )
        }
    }
}
