package com.playgroundagc.cleanarch.framework.di

import android.app.Application
import dagger.Module
import dagger.Provides

/**
 * Created by Amadou on 28/06/2022, 21:20
 */

@Module
class ApplicationModule( val app: Application) {
    @Provides
    fun providesApp() = app
}