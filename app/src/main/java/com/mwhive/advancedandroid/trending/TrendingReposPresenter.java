package com.mwhive.advancedandroid.trending;


import android.annotation.SuppressLint;

import com.mwhive.advancedandroid.data.RepoRepository;
import com.mwhive.advancedandroid.di.ForScreen;
import com.mwhive.advancedandroid.di.ScreenScope;
import com.mwhive.advancedandroid.lifecycle.DisposableManager;
import com.mwhive.advancedandroid.model.Repo;

import com.mwhive.advancedandroid.ui.ScreenNavigator;
import javax.inject.Inject;

/**
 * Created by MadWasp79 on 22-Feb-18.
 */


@ScreenScope
class TrendingReposPresenter implements RepoAdapter.RepoClickedListener {

  private final TrendingReposViewModel viewModel;
  private final RepoRepository repoRepository;
  private final ScreenNavigator screenNavigator;
  private final DisposableManager disposableManager;


  @Inject
  TrendingReposPresenter(
      TrendingReposViewModel viewModel,
      RepoRepository repoRepository,
      ScreenNavigator screenNavigator,
      @ForScreen DisposableManager disposableManager) {

    this.viewModel = viewModel;
    this.repoRepository = repoRepository;
    this.screenNavigator = screenNavigator;
    this.disposableManager = disposableManager;

    loadRepos();

  }

  @SuppressLint("CheckResult")
  private void loadRepos() {
    disposableManager.add(repoRepository.getTrendingRepos()
        .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
        .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false))
        .subscribe(viewModel.reposUpdated(), viewModel.onError()));
  }

  @Override
  public void onRepoClicked(Repo repo) {
    screenNavigator.goToRepoDetails(repo.owner().login(), repo.name());
  }
}
