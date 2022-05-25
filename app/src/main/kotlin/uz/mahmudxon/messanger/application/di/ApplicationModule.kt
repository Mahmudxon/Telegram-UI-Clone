package uz.mahmudxon.messanger.application.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.mahmudxon.messanger.application.storage.FileManger
import uz.mahmudxon.messanger.business.domain.util.IFileManager

@InstallIn(SingletonComponent::class)
@Module
class ApplicationModule {
    @Provides
    fun provideFileManger(@ApplicationContext context: Context): IFileManager = FileManger(context)
}