package com.mwhive.advancedandroid.data;


import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by MadWasp79 on 15-Feb-18.
 */

public interface RepoService {

    @GET("search/repositories?q=language:java&order=desc&sort=stars")
    Single<TrendingReposResponse> getTrendingRepos();
}
