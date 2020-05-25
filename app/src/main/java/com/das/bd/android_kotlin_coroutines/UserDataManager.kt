package com.das.bd.android_kotlin_coroutines

import kotlinx.coroutines.*

class UserDataManager {
    suspend fun getTotalUserCount() : Int {

        var count = 0
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            count = 50
        }
         var delay = CoroutineScope(Dispatchers.IO).async {
           delay(3000)
           return@async
       }
       return count+delay.await()
    }
}

private operator fun Number.plus(await: Unit): Int {
    return 70
}


