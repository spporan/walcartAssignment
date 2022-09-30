package poran.cse.walcartassignment.presentation.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed
import poran.cse.walcartassignment.R
import poran.cse.walcartassignment.data.dto.Parent
import poran.cse.walcartassignment.domain.model.Category

val cardBgColors = listOf<CardColorData>(
    CardColorData(  Color(0xFFE3EBFA), Color(0xFFD2DFF5)),
    CardColorData(  Color(0xFFD6C9E5), Color(0xFFCDBCE1)),
    CardColorData(  Color(0xFFFDF7E0), Color(0xFFFFF1C0)),
)

data class CardColorData(
    val backgroundColor: Color,
    val surfaceColor: Color
)

@Composable
fun CategoryMoreItems(categories: LazyPagingItems<Category>) {

    var selectedId by remember {
        mutableStateOf("C-C4PAEU")
    }
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 15.dp, vertical = 4.dp),
    ) {
        itemsIndexed(
            items = categories,
            key = { _, category ->
                category.uid
            }
        ) { index,  category ->
            if (category != null) {
                CategoryExpandableItem(category = category,  selectedId == category.uid, index) {
                    selectedId = if (selectedId == category.uid) {
                        "-1"
                    } else {
                        category.uid
                    }
                }
            }
        }

    }

}

@Composable
fun CategoryExpandableItem(category: Category, isExpanded: Boolean = false, index: Int,  onClick: ()-> Unit ) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 20.dp,
                    horizontal = 5.dp
                )
                .wrapContentHeight()
                .selectable(
                    selected = isExpanded,
                    onClick = {
                        Log.e("click", "click item ${category.uid}")
                        onClick()
                    }
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = category.enName ?: "Fashion",
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Normal,
                color = if (!isExpanded) Color.Black else Color(0xFF5486F8),
                textAlign = TextAlign.Center
            )

            Icon(
                 if (!isExpanded) painterResource(id = R.drawable.ic_collapse_arrow) else painterResource(id = R.drawable.ic_expand_arrow),
                contentDescription = "Expandable icon",
                tint = if (!isExpanded) Color.Gray else Color(0xFF5486F8)
            )

        }

        if (isExpanded && (category.parents?.size ?:  0) > 0) {
            // todo level 3 items
            LazyRow(
                contentPadding = PaddingValues(horizontal = 4.dp, vertical = 4.dp),
            ) {
                category.parents?.let {
                    items(
                        it,
                        key = { parent ->
                            parent.uid ?: ""
                        }
                    ) { parent ->
                        HorizontalListItem(parent)
                    }
                }

            }

        }
        Divider(thickness = 1.dp,color = Color.Gray.copy(.2f))

    }

}

@Composable
fun HorizontalListItem(parent: Parent, cardColor: CardColorData = cardBgColors.random()) {
    Card(
        elevation = 0.dp,
        backgroundColor = Color.Transparent,
        modifier = Modifier.padding(start = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .wrapContentHeight(),
            horizontalAlignment  =  Alignment.CenterHorizontally
        ) {

            Card(
                elevation = 4.dp,
                backgroundColor = cardColor.backgroundColor,
                modifier = Modifier.size(height = 120.dp, width = 100.dp),
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(cardColor.surfaceColor)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_dress),
                        contentDescription = null,
                        modifier = Modifier
                            .width(80.dp)
                            .height(100.dp),
                        // colorFilter = ColorFilter.tint( if (!isSelected) Color.Gray else Color(0xFF5486F8))
                    )
                }

            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = parent.enName ?: "Fashion",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Normal,
                //color = if (!isSelected) Color.Gray else Color(0xFF5486F8),
                textAlign = TextAlign.Center
            )

        }
    }
}
