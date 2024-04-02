package com.example.data.di

import com.example.data.product.repo.ProductRepositoryImpl
import com.example.data.section.repo.SectionRepositoryImpl
import com.example.domain.product.repo.ProductRepository
import com.example.domain.section.repo.SectionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindsSectionRepository(sectionRepositoryImpl: SectionRepositoryImpl): SectionRepository

    @Binds
    fun bindsProductRepository(productRepositoryImpl: ProductRepositoryImpl): ProductRepository
}