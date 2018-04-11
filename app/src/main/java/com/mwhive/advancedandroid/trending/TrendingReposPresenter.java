package com.mwhive.advancedandroid.trending;


import android.annotation.SuppressLint;

import com.mwhive.advancedandroid.data.RepoRepository;
import com.mwhive.advancedandroid.di.ScreenScope;
import com.mwhive.advancedandroid.model.Repo;

import javax.inject.Inject;

/**
 * Created by MadWasp79 on 22-Feb-18.
 */


@ScreenScope
class TrendingReposPresenter implements RepoAdapter.RepoClickedListener {
    private final TrendingReposViewModel viewModel;
    private final RepoRepository repoRepository;


    @Inject
    TrendingReposPresenter(TrendingReposViewModel viewModel, RepoRepository repoRepository) {

        this.viewModel = viewModel;
        this.repoRepository = repoRepository;

        loadRepos();

    }

    @SuppressLint("CheckResult")
    private void loadRepos() {
        repoRepository.getTrendingRepos()
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.reposUpdated(), viewModel.onError());
    }

    @Override
    public void onRepoClicked(Repo repo) {

    }
}
