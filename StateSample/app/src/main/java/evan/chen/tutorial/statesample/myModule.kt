package evan.chen.tutorial.statesample

import androidx.lifecycle.SavedStateHandle
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModule = module {

    viewModel { MainViewModel() }
    viewModel { ProductViewModel(get(), get()) }
    single { ProductRepository() }
}