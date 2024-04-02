package com.example.data.product.repo

import com.example.data.db.dao.BookmarkDao
import com.example.data.db.entity.Bookmark
import com.example.data.network.Apiservice
import com.example.data.product.model.ProductDto.Companion.convertTo
import com.example.domain.product.model.ProductEntity
import com.example.domain.product.repo.ProductRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val apiservice: Apiservice,
    private val bookmarkDao: BookmarkDao
) : ProductRepository {
    override fun fetchProducts(sectionId: Int): Single<List<ProductEntity>> =
        apiservice.getProducts(sectionId).map { response ->
            response.data?.let { products ->
                products.convertTo()
            }
        }.subscribeOn(Schedulers.io())

    override fun updateProductBookmark(productId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            bookmarkDao.insert(Bookmark(productId))
        }
    }

    override fun deleteProductBookmark(productId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            bookmarkDao.delete(Bookmark(productId))
        }
    }

    override suspend fun getProductBookmarks(): List<Int> = withContext(Dispatchers.IO) {
        bookmarkDao.getAll().map {
            it.id
        }
    }
}