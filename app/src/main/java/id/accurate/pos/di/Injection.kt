package id.accurate.pos.di

import android.content.Context
import id.accurate.pos.data.local.LocalDataSource
import id.accurate.pos.data.local.room.ContentDatabase
import id.accurate.pos.data.remote.RemoteDataSource
import id.accurate.pos.repository.ContentRepository
import id.accurate.pos.utils.AppExecutors

object Injection {

    fun provideRepository(context : Context) : ContentRepository {
        val database = ContentDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.contentDao())
        val appExecutors = AppExecutors()
        return ContentRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}