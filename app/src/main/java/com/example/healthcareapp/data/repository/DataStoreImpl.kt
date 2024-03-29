package com.example.healthcareapp.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.healthcareapp.core.utils.Constants.DATASTORE_NAME
import com.example.healthcareapp.core.utils.toNonNull
import com.example.healthcareapp.domain.model.User
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
        val TOKEN = stringPreferencesKey("TOKEN")
        val NAME = stringPreferencesKey("NAME")
        val ROLE = intPreferencesKey("ROLE")
    }

    override suspend fun saveToken(token : String) {
        context.datastore.edit { datastore ->
            datastore[TOKEN] = token
        }    }

    override suspend fun getToken() = context.datastore.data.map { datastore -> datastore[TOKEN].toNonNull() }

    override suspend fun saveUser(user: User) {
        context.datastore.edit { datastore ->
            datastore[NAME] = user.name
            datastore[ROLE] = user.role
        }
    }

    override suspend fun getUser() = context.datastore.data.map { datastore ->
        User(
            name = datastore[NAME].toNonNull(),
            role = datastore[ROLE].toNonNull()
        )
    }
}