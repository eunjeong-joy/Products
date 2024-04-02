package com.example.presentation.product.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.presentation.R
import com.example.presentation.product.model.Product
import com.example.presentation.sections.viewmodel.SectionsViewModel
import java.text.NumberFormat

@Preview
@Composable
fun PreviewBigProduct() {
    BigProduct(product = Product.dummy())
}

@Composable
fun BigProduct(
    modifier: Modifier = Modifier,
    product: Product,
    viewModel: SectionsViewModel = hiltViewModel()
) {
    var bookmarkState by rememberSaveable { mutableStateOf(product.isBookmarked) }

    Column(modifier = modifier) {
        Box {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(
                        ratio = 1.5f
                    ),
                model = product.image,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Image(
                painter = if (bookmarkState) {
                    painterResource(id = R.drawable.ic_btn_heart_on)
                } else {
                    painterResource(id = R.drawable.ic_btn_heart_off)
                },
                contentDescription = "heart",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp)
                    .clickable {
                        if (bookmarkState) {
                            viewModel.deleteBookmark(product.id)
                        } else {
                            viewModel.updateBookmark(product.id)
                        }
                        bookmarkState = !bookmarkState
                    }
            )
        }
        Text(
            text = product.name,
            fontSize = 20.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(3.dp))
        Row(verticalAlignment = Alignment.Bottom) {
            if (product.discountedRate != null) {
                Text(
                    text = "${NumberFormat.getIntegerInstance().format(product.discountedRate)}%",
                    fontSize = 18.sp,
                    color = Color(0xFFFA622F)
                )
                Spacer(modifier = Modifier.width(6.dp))
            }
            Text(
                text = "${NumberFormat.getIntegerInstance().format(product.originalPrice)}원",
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold
            )
            if (product.discountedPrice != null) {
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "${NumberFormat.getIntegerInstance().format(product.discountedPrice)}원",
                    fontSize = 14.sp,
                    style = TextStyle(textDecoration = TextDecoration.LineThrough),
                    color = Color(0xFF949494)
                )
            }
        }
    }
}