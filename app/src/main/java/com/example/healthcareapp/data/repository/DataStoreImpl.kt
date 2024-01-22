package com.example.healthcareapp.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.healthcareapp.core.utils.Constants.DATASTORE_NAME
import com.example.healthcareapp.core.utils.toNonNull
import com.example.healthcareapp.domain.repository.DataStoreRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreImpl @Inject constructor(@ApplicationContext val context: Context) :
    DataStoreRepository {

    private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)

    companion object {
        val ON_BOARDING = booleanPreferencesKey("ON_BOARDING")

    }

    override suspend fun saveOnBoarding() {
        context.datastore.edit { datastore ->
            datastore[ON_BOARDING] = true
        }    }

    override suspend fun getOnBoarding() = context.datastore.data.map { datastore -> datastore[ON_BOARDING].toNonNull() }

}