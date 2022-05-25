package uz.mahmudxon.messanger.business.domain.util

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.os.SystemClock
import android.util.Log
import java.util.concurrent.CountDownLatch


open class DispatchQueue @JvmOverloads constructor(threadName: String?, start: Boolean = true) :
    Thread() {
    @Volatile
    private var handler: Handler? = null
    private val syncLatch = CountDownLatch(1)
    var lastTaskTime: Long = 0
        private set
    val index = indexPointer++
    fun sendMessage(msg: Message?, delay: Int) {
        try {
            syncLatch.await()
            if (delay <= 0) {
                handler!!.sendMessage(msg!!)
            } else {
                handler!!.sendMessageDelayed(msg!!, delay.toLong())
            }
        } catch (ignore: Exception) {
        }
    }

    fun cancelRunnable(runnable: Runnable?) {
        try {
            syncLatch.await()
            handler!!.removeCallbacks(runnable!!)
        } catch (e: Exception) {
            Log.e(TAG, "postRunnable: ${e.message}")
        }
    }

    fun cancelRunnables(runnables: Array<Runnable?>) {
        try {
            syncLatch.await()
            for (i in runnables.indices) {
                handler!!.removeCallbacks(runnables[i]!!)
            }
        } catch (e: Exception) {
            Log.e(TAG, "postRunnable: ${e.message}")
        }
    }

    fun postRunnable(runnable: Runnable?): Boolean {
        lastTaskTime = SystemClock.elapsedRealtime()
        return postRunnable(runnable, 0)
    }

    fun postRunnable(runnable: Runnable?, delay: Long): Boolean {
        try {
            syncLatch.await()
        } catch (e: Exception) {
            Log.e(TAG, "postRunnable: ${e.message}")
        }
        return if (delay <= 0) {
            handler!!.post(runnable!!)
        } else {
            handler!!.postDelayed(runnable!!, delay)
        }
    }

    fun cleanupQueue() {
        try {
            syncLatch.await()
            handler!!.removeCallbacksAndMessages(null)
        } catch (e: Exception) {
            Log.e(TAG, "postRunnable: ${e.message}")
        }
    }

    fun handleMessage(inputMessage: Message?) {}
    fun recycle() {
        handler!!.looper.quit()
    }

    override fun run() {
        Looper.prepare()
        handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                this@DispatchQueue.handleMessage(msg)
            }
        }
        syncLatch.countDown()
        Looper.loop()
    }

    companion object {
        private var indexPointer = 0
    }

    init {
        name = threadName
        if (start) {
            start()
        }
    }
}
