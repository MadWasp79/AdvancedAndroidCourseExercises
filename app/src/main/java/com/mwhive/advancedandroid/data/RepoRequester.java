package com.mwhive.advancedandroid.data;


import com.mwhive.advancedandroid.model.Repo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by MadWasp79 on 15-Feb-18.
 */

public class RepoRequester {

    private final RepoService service;

    @Inject
    RepoRequester(RepoService service) {

        this.service = service;
    }

    Single<List<Repo>> getTrendingRepos() {
        return service.getTrendingRepos()
                .map(TrendingReposResponse::repos)
                .subscribeOn(Schedulers.io());
    }
}
