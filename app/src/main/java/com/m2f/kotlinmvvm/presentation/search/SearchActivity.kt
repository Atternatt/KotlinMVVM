package com.m2f.kotlinmvvm.presentation.search

import android.graphics.drawable.AnimationDrawable
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

    override val component: SearchComponent by lazy {
        DaggerSearchComponent.builder()
                .applicationComponent(appComponent)
                .build()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        val animationDrawable = viewBindig.container.background as? AnimationDrawable
        animationDrawable?.let {
            it.setEnterFadeDuration(2500)
            it.setExitFadeDuration(5000)
            it.start()
        }

        viewBindig.toolbar.inflateMenu(R.menu.menu_search)
        viewBindig.searchView.setMenuItem(viewBindig.toolbar.menu.findItem(R.id.action_search))
    }

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
