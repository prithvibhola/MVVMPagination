package prithvi.io.mvvmpagination.ui.characters

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_character.*
import prithvi.io.mvvmpagination.R
import prithvi.io.mvvmpagination.data.models.Response
import prithvi.io.mvvmpagination.ui.base.BaseActivity
import prithvi.io.mvvmpagination.utility.extentions.observe
import prithvi.io.mvvmpagination.utility.extentions.setScreenTitle
import javax.inject.Inject

class CharacterActivity : BaseActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mAdapter: CharactersAdapter
    private lateinit var viewModel: CharactersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        setSupportActionBar(toolbar)
        supportActionBar?.setScreenTitle(getString(R.string.marvel_characters))

        viewModel = ViewModelProviders.of(this, viewModelFactory)[CharactersViewModel::class.java]
        mAdapter = CharactersAdapter(this) { viewModel.retry() }
        rvCharacters.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = mAdapter
        }

        observe(viewModel.characters) { mAdapter.submitList(it) }
        observe(viewModel.getNetworkState()) { mAdapter.setNetworkState(it) }
        observe(viewModel.getRefreshState()) {
            it ?: return@observe
            when (it.status) {
                Response.Status.LOADING -> activityState.showLoading()
                Response.Status.SUCCESS -> {
                    it.data ?: return@observe
                    if (it.data.isEmpty())
                        activityState.showEmpty(R.drawable.ic_golf_course_black_24dp,
                                "Thanos is here!!",
                                "Every Marvel character is trying to save the galaxy from Thanos. Will take a while!!")
                    else
                        activityState.showContent()
                }
                Response.Status.ERROR -> activityState.showError(R.drawable.ic_golf_course_black_24dp,
                        "Smash!!",
                        "Couldn't get Marvel characters. Please try again.",
                        "Retry") { viewModel.retry() }
            }
        }
    }
}
