package rs.raf.edu.ui.breedsdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import rs.raf.edu.ui.theme.BeigeColor
import rs.raf.edu.ui.theme.OliveGreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedsDetailsScreen(
    breedId: String,
    navController: NavController,
    viewModel: BreedsDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(breedId) {
        viewModel.onEvent(BreedsDetailsEvent.LoadBreed(breedId))
    }

//    val ctx = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Details",
                        style = MaterialTheme.typography.titleLarge,
                        color = BeigeColor,
                        fontSize = 32.sp,
                        fontFamily = FontFamily.Monospace
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = BeigeColor
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = OliveGreen
                )
            )
        }
    ) { innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            when (state) {
                is BreedsDetailsState.Loading -> CircularProgressIndicator()
                is BreedsDetailsState.Error ->
                    Text((state as BreedsDetailsState.Error).message, color = Color.Red)
                is BreedsDetailsState.Success ->
                    BreedsDetailsContent(
                        b = (state as BreedsDetailsState.Success).breed,
                        modifier = Modifier
                            .fillMaxSize()
                    )
            }
        }
    }
}

