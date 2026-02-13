package rs.raf.edu.ui.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import rs.raf.edu.ui.theme.BeigeColor
import rs.raf.edu.ui.theme.OliveGreen

@OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class)
@Composable
fun ChipGroup(
    items: List<String>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 14.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            maxItemsInEachRow = 3,
            maxLines = 2
        ) {
            items.forEach { trait ->
                Surface(
                    color = OliveGreen,
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = trait,
                        color = BeigeColor,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        fontSize = 16.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }
        }
    }
}