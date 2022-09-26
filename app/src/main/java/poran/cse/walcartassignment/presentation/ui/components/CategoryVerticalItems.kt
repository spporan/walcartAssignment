package poran.cse.walcartassignment.presentation.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import androidx.paging.compose.itemsIndexed
import poran.cse.walcartassignment.R
import poran.cse.walcartassignment.domain.model.Category

@Composable
fun CategoryVerticalItem(category: Category, isSelected: Boolean = false, index: Int,  onClick: ()-> Unit ) {
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
                .wrapContentHeight()
                .selectable(
                    selected = isSelected,
                    onClick = {
                        Log.e("click", "click item ${category.uid}")
                        onClick()
                    }
                ),
            horizontalAlignment  =  Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_dress),
                contentDescription = null,
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp),
                colorFilter = ColorFilter.tint( if (!isSelected) Color.Gray else Color(0xFF5486F8))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = category.enName ?: "Fashion",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Normal,
                color = if (!isSelected) Color.Gray else Color(0xFF5486F8),
                textAlign = TextAlign.Center
            )
            Divider(thickness = 1.dp,color = if (!isSelected) Color.Gray else Color(0xFF5486F8))

        }
    }
    
}

@Composable
fun CategorySidebar(categories: LazyPagingItems<Category>) {
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