package com.mwhive.advancedandroid.data;

import com.mwhive.advancedandroid.model.Contributor;
import com.mwhive.advancedandroid.model.Repo;
import io.reactivex.Single;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface RepoService {

  @GET("search/repositories?q=language:java&order=desc&sort=stars")
  Single<TrendingReposResponse> getTrendingRepos();

  @GET("repos/{owner}/{name}")
  Single<Repo> getRepo(@Path("owner") String repoOwner, @Path("name") String repoName);

  @GET
  Single<List<Contributor>> getContributors(@Url String url);
}
