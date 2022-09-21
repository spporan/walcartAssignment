package poran.cse.walcartassignment.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import poran.cse.walcartassignment.R
import poran.cse.walcartassignment.domain.model.Category

@Composable
fun CategoryVerticalItem(category: Category, isSelected: Boolean = false,  onClick: ()-> Unit ) {
    Card(
        elevation = 0.dp,
        backgroundColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .width(50.dp)
                .padding(
                    vertical = 10.dp,
                    horizontal = 5.dp
                )
                .wrapContentHeight()
                .clickable {
                    onClick.invoke()
                }
            ,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_dress),
                contentDescription = null,
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = category.enName ?: "Fashion",
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Normal,
                color = if (!isSelected) Color.Gray else Color(0xFF5486F8)
            )
            Divider(thickness = 1.dp,color = if (!isSelected) Color.Gray else Color(0xFF5486F8))

        }
    }
    
}

@Composable
fun CategorySidebar(categories: LazyPagingItems<Category>) {
    val scrollState = rememberScrollState()
    var isSelectable  = false
    LazyColumn(
        modifier = Modifier.width(60.dp),
        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 4.dp),
    ) {
        items(
            items = categories,
            key = { category ->
                category.uid
            }
        ) { category ->
            if (category != null) {
                CategoryVerticalItem(category = category, isSelectable) {
                    isSelectable = !isSelectable
                }
            }
        }
    }

}