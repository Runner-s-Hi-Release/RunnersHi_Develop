package com.example.runnershi_develop.utilities

import android.content.Context
import com.example.runnershi_develop.data.*
import com.example.runnershi_develop.viewmodels.*

object InjectorUtils {

    private fun getUserRepository(context: Context): UserRepository {
        return UserRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).userDao()
        )
    }

    private fun getBadgeDetailRepository(): BadgeDetailRepository {
        return BadgeDetailRepository.getInstance()
    }

    private fun getRunningRepository(context: Context): RunningRepository {
        return RunningRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).runningDao
        )
    }

    private fun getRankingRepository(): RankingRepository {
        return RankingRepository.getInstance()
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

    fun provideRankingViewModelFactory(
    ): RankingViewModelFactory {
        return RankingViewModelFactory(getRankingRepository())
    }
}
