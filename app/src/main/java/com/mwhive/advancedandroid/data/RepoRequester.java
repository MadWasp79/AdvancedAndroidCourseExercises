package com.mwhive.advancedandroid.data;


import com.mwhive.advancedandroid.model.Contributor;
import com.mwhive.advancedandroid.model.Repo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class RepoRequester {

  private final RepoService service;

  @Inject
  RepoRequester(RepoService service) {
    this.service = service;
  }

  Single<List<Repo>> getTrendingRepos() {
    return service.getTrendingRepos()
        .map(TrendingReposResponse::repos);
  }

  Single<Repo> getRepo(String repoOwner, String repoName) {
    return service.getRepo(repoOwner, repoName);
  }

  Single<List<Contributor>> getContributors(String url) {
    return service.getContributors(url);
  }
}
