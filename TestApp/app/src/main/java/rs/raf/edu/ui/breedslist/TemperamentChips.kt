package rs.raf.edu.ui.breedslist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TemperamentChips(temperament: String) {
    val traits = temperament.split(",").map { it.trim() }.take(3)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        traits.forEach { trait ->
            Chip(text = trait)
        }
    }
}