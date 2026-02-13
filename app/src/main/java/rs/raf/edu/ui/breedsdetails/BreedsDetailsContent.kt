package rs.raf.edu.ui.breedsdetails

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import rs.raf.edu.model.Breed
import rs.raf.edu.ui.theme.OliveGreen
import rs.raf.edu.ui.theme.BeigeColor
import androidx.core.net.toUri
import rs.raf.edu.ui.common.ChipGroup
import rs.raf.edu.ui.theme.CatalistBlue
import rs.raf.edu.ui.theme.RedColor


@Composable
fun BreedsDetailsContent(
    b: Breed,
    modifier: Modifier = Modifier
) {
    val ctx = LocalContext.current

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        item {
            Text(
                text = b.name,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Serif,
                textDecoration = TextDecoration.Underline,
            )
        }

        item {
            val rarityText = if (b.rare == 1) "Rare" else "Common"
            Text(
                text = "($rarityText breed)",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif,
                color = if (b.rare == 1) RedColor else CatalistBlue
            )
        }

        item {
            b.altNames?.let { alt ->
                if (alt.isNotEmpty()) {
                    Text(
                        text = "Also known as: $alt",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 16.sp,
                        fontStyle = FontStyle.Italic
                    )
                } else {
                    Text(
                        text = "No alternative names.",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 16.sp,
                        fontStyle = FontStyle.Italic
                    )
                }
            }
        }

        item {
            HorizontalDivider(thickness = 1.dp, color = OliveGreen)
        }

        item {
            AsyncImage(
                model = b.image?.url,
                contentDescription = "${b.name} image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }

        item {
            HorizontalDivider(thickness = 1.dp, color = OliveGreen)
        }

        item {
            Text(
                text = b.description,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Serif
            )
        }

        item {
            HorizontalDivider(thickness = 1.dp, color = OliveGreen)
        }

        item {
            Text(
                text = "Origin: ${b.origin ?: "Unknown"}",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 18.sp,
                fontFamily = FontFamily.Serif
            )
        }

        item {
            Text(
                text = "Life span: ${b.lifeSpan ?: "N/A"} years",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 18.sp,
                fontFamily = FontFamily.Serif
            )
        }

        item {
            Text(
                text = "Weight: ${b.weight?.metric ?: "N/A"} kg",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 18.sp,
                fontFamily = FontFamily.Serif
            )
        }

        item {
            HorizontalDivider(thickness = 1.dp, color = OliveGreen)
        }

        item {
            Text(
                text = "Temperament",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 26.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.SemiBold
            )
            ChipGroup(items = b.temperament.split(",").map { it.trim() })
        }

        item {
            HorizontalDivider(thickness = 1.dp, color = OliveGreen)
        }

        item {
            BehaviorRating("Adaptability", b.adaptability)
            BehaviorRating("Affection", b.affectionLevel)
            BehaviorRating("Energy", b.energyLevel)
            BehaviorRating("Intelligence", b.intelligence)
            BehaviorRating("Vocalisation", b.vocalisation)
        }

        item {
            HorizontalDivider(thickness = 1.dp, color = OliveGreen)
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        b.wikipediaUrl?.let {
                            ctx.startActivity(Intent(Intent.ACTION_VIEW, it.toUri()))
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = OliveGreen,
                        contentColor = BeigeColor
                    )
                ) {
                    Text(
                        text = "Learn more on Wikipedia",
                        fontSize = 18.sp,
                        fontFamily = FontFamily.Serif
                    )
                }
            }
        }
    }
}
