package com.mwhive.advancedandroid.trending;


import com.mwhive.advancedandroid.data.RepoRequester;
import com.mwhive.advancedandroid.di.ScreenScope;

import javax.inject.Inject;

/**
 * Created by MadWasp79 on 22-Feb-18.
 */


@ScreenScope
public class TrendingReposPresenter {
    private final TrendingReposViewModel mViewModel;
    private final RepoRequester mRepoRequester;

    @Inject
    TrendingReposPresenter(TrendingReposViewModel viewModel, RepoRequester repoRequester) {

        mViewModel = viewModel;
        mRepoRequester = repoRequester;
        loadRepos();

    }

    private void loadRepos() {
        mRepoRequester.getTrendingRepos()
                .doOnSubscribe(__ -> mViewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> mViewModel.loadingUpdated().accept(false))
                .subscribe(mViewModel.reposUpdated(), mViewModel.onError());
    }

}
