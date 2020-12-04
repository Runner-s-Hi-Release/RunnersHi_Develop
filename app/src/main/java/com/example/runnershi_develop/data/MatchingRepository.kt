package com.example.runnershi_develop.data

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.*
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.example.runnershi_develop.service.ForegroundService.Companion.ACTION_START_FOREGROUND_SERVICE
import com.example.runnershi_develop.service.ForegroundService.Companion.ACTION_STOP_FOREGROUND_SERVICE

@RequiresApi(Build.VERSION_CODES.O)
class MatchingRepository private constructor(
    private val intent: Intent,
    private val context: Context
) {

    fun getSuccess(): MutableLiveData<Boolean> {
        return _success
    }

    fun startForegroundService(runningStart: RunningStart) {
        intent.action = ACTION_START_FOREGROUND_SERVICE;

        val bundle = Bundle()
        bundle.putSerializable("runningStart", runningStart)
        val successReceiver = SuccessReceiver()
        bundle.putParcelable("receiver", successReceiver)
        intent.putExtras(bundle)
        context.startForegroundService(intent)
    }

    fun stopForegroundService(){
        intent.action = ACTION_STOP_FOREGROUND_SERVICE;
        context.startForegroundService(intent)
    }


    companion object {
        // For Singleton instantiation
        @Volatile private var instance: MatchingRepository? = null
        val _success: MutableLiveData<Boolean> = MutableLiveData()
        fun getInstance(intent: Intent, context: Context) =
            instance ?: synchronized(this) {
                instance ?: MatchingRepository(intent, context).also { instance = it }
            }
    }

    private class SuccessReceiver: ResultReceiver(null){
        @Override
         override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
            super.onReceiveResult(resultCode, resultData)
            when(resultCode){
                Activity.RESULT_OK -> _success.value = resultData?.getBoolean("success")
            }
        }
    }

}