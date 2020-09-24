package com.example.runnershi_develop.utilities

import android.content.Context
import com.example.runnershi_develop.api.RequestToServer
import com.example.runnershi_develop.data.AppDatabase
import com.example.runnershi_develop.data.BadgeDetailRepository
import com.example.runnershi_develop.data.RunningRepository
import com.example.runnershi_develop.data.UserRepository
import com.example.runnershi_develop.viewmodels.BadgeDetailViewModelFactory
import com.example.runnershi_develop.viewmodels.MyProfileViewModelFactory
import com.example.runnershi_develop.viewmodels.RunningViewModelFactory
import com.example.runnershi_develop.viewmodels.RunningDetailViewModelFactory

object InjectorUtils {

    private fun getUserRepository(context: Context): UserRepository {
        return UserRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).userDao(),
            RequestToServer.create()
        )
    }

    private fun getBadgeDetailRepository(): BadgeDetailRepository {
        return BadgeDetailRepository.getInstance(
            RequestToServer.create()
        )
    }

    private fun getRunningRepository(context: Context): RunningRepository {
        return RunningRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).runningDao
        )
    }

    fun provideMyProfileViewModelFactory(
        context: Context
    ): MyProfileViewModelFactory {
        return MyProfileViewModelFactory(
            getUserRepository(context)
        )
    }

    fun provideBadgeDetailViewModelFactory(
        index: Int
    ): BadgeDetailViewModelFactory {
        return BadgeDetailViewModelFactory(getBadgeDetailRepository(), index)
    }

    fun provideRecordViewModelFactory(
        context: Context
    ): RunningViewModelFactory {
        return RunningViewModelFactory(getRunningRepository(context))

    }

    fun provideRunningDetailViewModelFactory(
        context: Context, runIdx: Int, gameIdx: Int
    ): RunningDetailViewModelFactory {
        return RunningDetailViewModelFactory(getRunningRepository(context), runIdx, gameIdx)
    }

}
