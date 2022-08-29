package com.leon.bilihub.parser

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.leon.bilihub.beans.partition.Partition
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

private val partitionMap: HashMap<Int, Partition> = HashMap()

/**
 * @Author Leon
 * @Time 2022/06/21
 * @Desc
 */
class PartitionParser {
    private val TAG: String = "WwwW"

    companion object {
        fun getPartitionData(partitionId: Int): Partition? {
            return partitionMap[partitionId]
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun initMemData(context: Context) {
        GlobalScope.launch {
            val gson =
                Gson().newBuilder().enableComplexMapKeySerialization().serializeNulls().create()
            val json: String? = readLocalFileContent(context, "partition.json")

            val type = object : TypeToken<ArrayList<Partition>>() {}.type
            val partitions: ArrayList<Partition> = ArrayList()

            partitions.addAll(gson.fromJson(json, type))

            for (partition in partitions) {
                partitionMap[partition.tid] = partition
            }

            Log.d(TAG, "initMemData: DONE")
        }
    }

    private fun readLocalFileContent(context: Context, fileName: String): String? {
        var bufferedReader: BufferedReader? = null
        try {
            bufferedReader =
                BufferedReader(InputStreamReader(context.assets.open(fileName)))

            var result = ""
            bufferedReader.readLines().forEach { result += it }
            return result
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            bufferedReader?.close()
        }

        return null
    }
}