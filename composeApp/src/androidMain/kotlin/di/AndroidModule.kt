package di

import io.ktor.client.engine.android.Android
import org.koin.dsl.module


val androidAppModule = module {
    single { Android.create() }

}