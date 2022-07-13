package com.leon.biuvideo.parser

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.leon.biuvideo.beans.partition.Partition
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

lateinit var partitionMap: Map<String, ArrayList<Partition>>

/**
 * @Author Leon
 * @Time 2022/06/21
 * @Desc
 */
class PartitionParser {
    private val TAG: String = "WwwW"

    companion object {
        fun getPartitionData(partitionName: String): ArrayList<Partition>? {
            return partitionMap[partitionName]
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun initMemData(context: Context) {
        GlobalScope.launch {
            val gson =
                Gson().newBuilder().enableComplexMapKeySerialization().serializeNulls().create()
            val json: String? = readLocalFileContent(context, "partition.json")
            val type = object : TypeToken<Map<String, ArrayList<Partition>>>() {}.type

            partitionMap = gson.fromJson(json, type)
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