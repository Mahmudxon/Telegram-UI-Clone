package uz.uniconsoft.messanger.di.app

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.uniconsoft.messanger.application.FileManger
import uz.uniconsoft.messanger.business.domain.util.IFileManager

@InstallIn(SingletonComponent::class)
@Module
class ApplicationModule {
    @Provides
    fun provideFileManger(@ApplicationContext context: Context): IFileManager = FileManger(context)
}