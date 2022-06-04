package com.github.armkrtchyan.domain.repositories

import com.github.armkrtchyan.domain.models.Config
import com.github.armkrtchyan.domain.models.DefaultConfig
import kotlinx.coroutines.flow.Flow

interface ConfigsRepository {
    suspend fun getDefaultConfig(): Flow<DefaultConfig>
    suspend fun getConfig(): Flow<Config>
}