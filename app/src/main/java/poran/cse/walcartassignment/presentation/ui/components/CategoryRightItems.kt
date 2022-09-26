package poran.cse.walcartassignment.presentation.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed
import poran.cse.walcartassignment.data.dto.Parent
import poran.cse.walcartassignment.domain.model.Category


@Composable
fun CategoryMoreItems(categories: LazyPagingItems<Category>) {
    val scrollState = rememberScrollState()
    var selectedId by remember {
        mutableStateOf("C-C4PAEU")
    }
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 4.dp),
    ) {
        itemsIndexed(
            items = categories,
            key = { _, category ->
                category.uid
            }
        ) { index,  category ->
            if (category != null) {
                CategoryVerticalItem(category = category,  selectedId == category.uid, index) {
                    selectedId = category.uid
                }
            }
        }

    }

}

@Composable
fun CategoryExpandableItem(category: Category, isExpanded: Boolean = false, index: Int,  onClick: ()-> Unit ) {
    Column() {
        Row(
            modifier = Modifier
                .width(100.dp)
                .padding(
                    vertical = 10.dp,
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
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = category.enName ?: "Fashion",
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Normal,
                color = if (!isExpanded) Color.Black else Color(0xFF5486F8),
                textAlign = TextAlign.Center
            )

            Icon(
                imageVector = if (!isExpanded) Icons.Default.ArrowDropDown else Icons.Default.KeyboardArrowDown,
                contentDescription = "Expandable icon",
                tint = if (!isExpanded) Color.Gray else Color(0xFF5486F8)
            )

        }

        if (isExpanded) {
            // todo level 3 items
        }
        Divider(thickness = 1.dp,color = Color(0xFFF3F3F3))


    }

}

@Composable
fun HorizontalListItem(parent: Parent) {
    Card(
        elevation = 0.dp,
        backgroundColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .width(100.dp)
                .padding(
                    vertical = 10.dp,
                    horizontal = 5.dp
                )
                .wrapContentHeight(),
            horizontalAlignment  =  Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = poran.cse.walcartassignment.R.drawable.ic_dress),
                contentDescription = null,
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp),
               // colorFilter = ColorFilter.tint( if (!isSelected) Color.Gray else Color(0xFF5486F8))
            )
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
