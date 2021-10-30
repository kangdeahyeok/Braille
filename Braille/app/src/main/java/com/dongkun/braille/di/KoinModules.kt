package com.dongkun.braille.di

import com.dongkun.braille.Repository
import com.dongkun.braille.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val repositoryModule = module{
    single{
        Repository()
    }
}
