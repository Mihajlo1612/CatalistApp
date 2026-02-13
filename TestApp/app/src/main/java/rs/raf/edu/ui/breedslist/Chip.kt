package rs.raf.edu.ui.breedslist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import rs.raf.edu.ui.theme.BeigeColor
import rs.raf.edu.ui.theme.OliveGreen

@Composable
fun Chip(text: String) {
    Surface(
        modifier = Modifier
            .padding(end = 4.dp)
            .height(32.dp),
        shape = MaterialTheme.shapes.small,
        color = OliveGreen
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
        ) {
            Text(
                text = text,
                fontSize = 14.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                color = BeigeColor,
                softWrap = false,
            )
        }
    }
}