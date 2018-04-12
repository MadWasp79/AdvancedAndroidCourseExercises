package com.mwhive.advancedandroid.details;


import android.annotation.SuppressLint;
import com.mwhive.advancedandroid.data.RepoRepository;
import com.mwhive.advancedandroid.di.ScreenScope;
import javax.inject.Inject;
import javax.inject.Named;

@ScreenScope
public class RepoDetailsPresenter {

  @SuppressLint("CheckResult")
  @Inject
  RepoDetailsPresenter(
      @Named("repo_owner") String repoOwner,
      @Named("repo_name") String repoName,
      RepoRepository repoRepository,
      RepoDetailsViewModel viewModel) {

    repoRepository.getRepo(repoOwner, repoName)
        .doOnSuccess(viewModel.processRepo())
        .doOnError(viewModel.detailsError())
        .flatMap(repo -> repoRepository.getContributors(repo.contributorsUrl())
            .doOnError(viewModel.contributorsError()))
        .subscribe(viewModel.processContributors(), throwable -> {
          //we handle logging in the view model
        });

  }

}
