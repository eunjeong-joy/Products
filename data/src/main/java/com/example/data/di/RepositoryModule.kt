package com.example.data.di

import com.example.data.section.repo.SectionRepositoryImpl
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
}