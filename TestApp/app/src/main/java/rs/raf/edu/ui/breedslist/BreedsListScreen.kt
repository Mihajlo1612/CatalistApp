package rs.raf.edu.ui.breedslist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import rs.raf.edu.model.Breed
import rs.raf.edu.navigation.Screen
import rs.raf.edu.ui.theme.BeigeColor
import rs.raf.edu.ui.theme.OliveGreen
import rs.raf.edu.ui.common.SimpleSearchBar

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BreedsListScreen(
    navController: NavController,
    viewModel: BreedsListViewModel = hiltViewModel()
) {
    val breedsState by viewModel.state.collectAsState()
    var searchQuery by rememberSaveable { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val allBreeds = (breedsState as? BreedsListState.Success)?.breeds ?: emptyList()
    var showSearch by remember { mutableStateOf(true) }

    val listState = rememberSaveable(saver = LazyListState.Saver) {
        LazyListState()
    }

    val displayBreeds = if (searchQuery.isNotBlank()) {
        allBreeds.filter {
            it.name.startsWith(searchQuery, ignoreCase = true)
        }
    } else allBreeds

    val showScorllToTopButton by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 1 || (listState.firstVisibleItemIndex == 1 && listState.firstVisibleItemScrollOffset > 100)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.onEvent(BreedsListEvent.FetchBreeds)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding())
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(OliveGreen),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Catalist",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily.Monospace,
                        color = BeigeColor,
                        modifier = Modifier.padding(vertical = 12.dp)
                    )
                }
            }

            if(showSearch) {
                item {
                    SimpleSearchBar(
                        query = searchQuery,
                        onQueryChange = { searchQuery = it },
                        onSearch = {
                            viewModel.onEvent(BreedsListEvent.FetchBreeds)
                        },
                        modifier = Modifier
                            .padding(8.dp)
                    )
                }
            }

            items(displayBreeds) { breed ->
                BreedListItem(breed) {
                    navController.navigate(Screen.BreedDetails.createRoute(breed.id))
                }
            }
        }

        if(showScorllToTopButton) {
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(0)
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                containerColor = OliveGreen,
                contentColor = BeigeColor
            ) {
                Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "Scroll to top")
            }
        }
    }
}

@Composable
fun BreedListItem(breed: Breed, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = BeigeColor)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = breed.name,
                style = MaterialTheme.typography.headlineMedium,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
            )

            if (!breed.altNames.isNullOrBlank()) {
                Text(
                    text = "Also known as ${breed.altNames}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    fontStyle = FontStyle.Italic,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = breed.description.take(250),
                style = MaterialTheme.typography.bodyMedium,

            )

            Spacer(modifier = Modifier.height(8.dp))

            TemperamentChips(breed.temperament)
        }
    }
}
