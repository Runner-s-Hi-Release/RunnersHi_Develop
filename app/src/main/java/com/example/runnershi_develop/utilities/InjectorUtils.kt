package com.example.runnershi_develop.utilities

import android.content.Context
import com.example.runnershi_develop.api.RequestToServer
import com.example.runnershi_develop.data.AppDatabase
import com.example.runnershi_develop.data.BadgeDetailRepository
import com.example.runnershi_develop.data.UserRepository
import com.example.runnershi_develop.viewmodels.BadgeDetailViewModelFactory
import com.example.runnershi_develop.viewmodels.MyProfileViewModelFactory

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

    fun provideMyProfileViewModelFactory(
        context: Context
    ): MyProfileViewModelFactory {
        return MyProfileViewModelFactory(
            getUserRepository(context))
    }

    fun provideBadgeDetailViewModelFactory(
        index: Int
    ): BadgeDetailViewModelFactory {
        return BadgeDetailViewModelFactory(getBadgeDetailRepository(), index)
    }

}
