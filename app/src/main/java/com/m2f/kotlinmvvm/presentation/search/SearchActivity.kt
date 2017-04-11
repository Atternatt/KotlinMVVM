package com.m2f.kotlinmvvm.presentation.search

import android.os.Bundle
import com.m2f.kotlinmvvm.BR
import com.m2f.kotlinmvvm.R
import com.m2f.kotlinmvvm.databinding.ActivitySearchBinding
import com.m2f.kotlinmvvm.main.extensions.appComponent
import com.m2f.kotlinmvvm.presentation.BindableBaseActivity
import com.m2f.kotlinmvvm.presentation.viewmodel.ViewModel
import javax.inject.Inject

class SearchActivity : BindableBaseActivity<SearchComponent, ActivitySearchBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @Inject lateinit var vieWModel: SearchConcertsViewModel

    override val component: SearchComponent = DaggerSearchComponent.builder()
            .applicationComponent(appComponent)
            .build()

    override fun viewModel(): ViewModel {
        return this.vieWModel
    }

    override fun layout(): Int {
        return R.layout.activity_search
    }

    override fun id(): Int {
        return BR.viewModel
    }
}
