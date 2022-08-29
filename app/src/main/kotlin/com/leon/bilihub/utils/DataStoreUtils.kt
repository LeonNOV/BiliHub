package com.leon.bilihub.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import java.io.IOException
import java.lang.IllegalArgumentException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "config")

object DataStoreUtils {

    /**
     *
     * 异步获取数据
     * */
    @Suppress("UNCHECKED_CAST")
    fun <Value> getData(context: Context, key: String, defaultValue: Value): Value {
        val result = when (defaultValue) {
            is Int -> readIntData(context, key, defaultValue)
            is Float -> readFloatData(context, key, defaultValue)
            is Double -> readDoubleData(context, key, defaultValue)
            is Boolean -> readBoolean(context, key, defaultValue)
            is String -> readString(context, key, defaultValue)
            is Long -> readLong(context, key, defaultValue)

            else -> throw IllegalArgumentException("can not find the $key type")
        }

        return result as Value
    }

    /**
     * 同步获取数据
     * */
    @Suppress("UNCHECKED_CAST")
    fun <Value> getSyncData(context: Context, key: String, defaultValue: Value): Flow<Value> {
        val result = when (defaultValue) {
            is Int -> readSyncIntData(context, key, defaultValue)
            is Long -> readSyncLongData(context, key, defaultValue)
            is Float -> readSyncFloatData(context, key, defaultValue)
            is Double -> readSyncDoubleData(context, key, defaultValue)
            is Boolean -> readSyncBooleanData(context, key, defaultValue)
            is String -> readSyncStringData(context, key, defaultValue)
            else -> throw IllegalArgumentException("can Not find the $key type")

        }

        return result as Flow<Value>
    }

    /**
     * 异步输入数据
     */
    fun <Value> putData(context: Context, key: String, value: Value) {
        when (value) {
            is Int -> saveIntData(context, key, value)
            is Long -> saveLongData(context, key, value)
            is Float -> saveFloatData(context, key, value)
            is Double -> saveDoubleData(context, key, value)
            is Boolean -> saveBoolean(context, key, value)
            is String -> saveString(context, key, value)
            else -> throw IllegalArgumentException("unSupport $value type !!!")
        }
    }

    /**
     * 同步输入数据
     */
    suspend fun <Value> putSyncData(context: Context, key: String, value: Value) {
        when (value) {
            is Int -> saveSyncIntData(context, key, value)
            is Long -> saveSyncLongData(context, key, value)
            is Float -> saveSyncFloatData(context, key, value)
            is Double -> saveSyncDoubleData(context, key, value)
            is Boolean -> saveSyncBoolean(context, key, value)
            is String -> saveSyncString(context, key, value)
            else -> throw IllegalArgumentException("unSupport $value type !!!")
        }
    }

    private fun saveString(context: Context, key: String, value: String) = runBlocking {
        saveSyncString(context, key, value)
    }

    private suspend fun saveSyncString(context: Context, key: String, value: String) {
        context.dataStore.edit { mutablePreferences ->
            mutablePreferences[stringPreferencesKey(key)] = value
        }
    }

    private fun saveBoolean(context: Context, key: String, value: Boolean) =
        runBlocking { saveSyncBoolean(context, key, value) }

    private suspend fun saveSyncBoolean(context: Context, key: String, value: Boolean) {
        context.dataStore.edit { mutablePreferences ->
            mutablePreferences[booleanPreferencesKey(key)] = value
        }
    }

    private fun saveDoubleData(context: Context, key: String, value: Double) = runBlocking {
        saveSyncDoubleData(context, key, value)
    }

    private suspend fun saveSyncDoubleData(context: Context, key: String, value: Double) {
        context.dataStore.edit { mutablePreferences ->
            mutablePreferences[doublePreferencesKey(key)] = value
        }
    }

    private fun saveFloatData(context: Context, key: String, value: Float) =
        runBlocking { saveSyncFloatData(context, key, value) }

    private suspend fun saveSyncFloatData(context: Context, key: String, value: Float) {
        context.dataStore.edit { mutablePreferences ->
            mutablePreferences[floatPreferencesKey(key)] = value
        }
    }

    private fun saveLongData(context: Context, key: String, value: Long) =
        runBlocking { saveSyncLongData(context, key, value) }

    private suspend fun saveSyncLongData(context: Context, key: String, value: Long) {
        context.dataStore.edit { mutablePreferences ->
            mutablePreferences[longPreferencesKey(key)] = value
        }
    }

    private fun saveIntData(context: Context, key: String, value: Int) =
        runBlocking { saveSyncIntData(context, key, value) }

    private suspend fun saveSyncIntData(context: Context, key: String, value: Int) {
        context.dataStore.edit { mutablePreferences ->
            mutablePreferences[intPreferencesKey(key)] = value
        }
    }

    private fun readSyncStringData(
        context: Context,
        key: String,
        defaultValue: String
    ): Flow<String> =
        context.dataStore.data.catch {
            checkCollectorAction(it, this)

        }.map { it[stringPreferencesKey(key)] ?: defaultValue }

    private fun readSyncBooleanData(
        context: Context,
        key: String,
        defaultValue: Boolean
    ): Flow<Boolean> =
        context.dataStore.data.catch {

            checkCollectorAction(it, this)

        }.map { it[booleanPreferencesKey(key)] ?: defaultValue }

    private fun readSyncDoubleData(
        context: Context,
        key: String,
        defaultValue: Double
    ): Flow<Double> =
        context.dataStore.data.catch {
            checkCollectorAction(it, this)
        }.map { it[doublePreferencesKey(key)] ?: defaultValue }

    private fun readSyncFloatData(context: Context, key: String, defaultValue: Float): Flow<Float> =
        context.dataStore.data.catch {

            checkCollectorAction(it, this)

        }.map { it[floatPreferencesKey(key)] ?: defaultValue }

    private fun readSyncLongData(context: Context, key: String, defaultValue: Long): Flow<Long> =
        context.dataStore.data.catch {

            checkCollectorAction(it, this)

        }.map { it[longPreferencesKey(key)] ?: defaultValue }

    private fun readSyncIntData(context: Context, key: String, defaultValue: Int): Flow<Int> =
        context.dataStore.data.catch {

            checkCollectorAction(it, this)

        }.map { it[intPreferencesKey(key)] ?: defaultValue }

    private fun readIntData(context: Context, key: String, defaultValue: Int): Int {
        var resultValue = defaultValue

        runBlocking {
            context.dataStore.data.first {
                resultValue = it[intPreferencesKey(key)] ?: resultValue
                true
            }
        }

        return resultValue
    }

    private fun readFloatData(context: Context, key: String, defaultValue: Float): Float {
        var resultValue = defaultValue

        runBlocking {
            context.dataStore.data.first {
                resultValue = it[floatPreferencesKey(key)] ?: resultValue
                true
            }
        }

        return resultValue
    }

    private fun readDoubleData(context: Context, key: String, defaultValue: Double): Double {
        var resultValue = defaultValue

        runBlocking {
            context.dataStore.data.first {
                resultValue = it[doublePreferencesKey(key)] ?: resultValue
                true
            }
        }

        return resultValue
    }

    private fun readBoolean(context: Context, key: String, defaultValue: Boolean): Boolean {
        var resultValue = defaultValue

        runBlocking {
            context.dataStore.data.first {
                resultValue = it[booleanPreferencesKey(key)] ?: resultValue
                true
            }
        }

        return resultValue
    }

    private fun readString(context: Context, key: String, defaultValue: String): String {
        var resultValue = defaultValue

        runBlocking {
            context.dataStore.data.first {
                resultValue = it[stringPreferencesKey(key)] ?: defaultValue

                true
            }
        }

        return resultValue
    }

    private fun readLong(context: Context, key: String, defaultValue: Long): Long {
        var resultValue = defaultValue

        runBlocking {
            context.dataStore.data.first {
                resultValue = it[longPreferencesKey(key)] ?: resultValue
                true
            }
        }

        return resultValue

    }

    fun readSetString(
        context: Context,
        key: String,
        defaultValue: Set<String> = HashSet()
    ): Set<String> {
        var resultValue = defaultValue

        runBlocking {
            context.dataStore.data.first {
                resultValue = it[stringSetPreferencesKey(key)] ?: defaultValue
                true
            }
        }

        return resultValue
    }

    fun readSyncSetString(
        context: Context,
        key: String,
        defaultValue: Set<String> = HashSet()
    ): Flow<Set<String>> =
        context.dataStore.data.catch { e ->
            checkCollectorAction(e, this)

        }.map { it[stringSetPreferencesKey(key)] ?: defaultValue }

    fun writeSetString(context: Context, key: String, value: Set<String>) = runBlocking {
        writeSyncSetString(context, key, value)
    }

    private suspend fun writeSyncSetString(context: Context, key: String, value: Set<String>) {
        context.dataStore.edit { mutablePreferences ->
            mutablePreferences[stringSetPreferencesKey(key)] = value
        }
    }

    suspend fun clear(context: Context) {
        context.dataStore.edit { it.clear() }
    }

    fun clearSync(context: Context) {
        runBlocking {
            clear(context)
        }
    }

    private suspend fun checkCollectorAction(e: Throwable, collector: FlowCollector<Preferences>) {
        if (e is IOException) {
            e.printStackTrace()
            collector.emit(emptyPreferences())
        } else {
            throw  e
        }
    }
}