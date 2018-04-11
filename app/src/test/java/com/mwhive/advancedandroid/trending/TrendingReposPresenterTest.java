package com.mwhive.advancedandroid.trending;

import com.mwhive.advancedandroid.data.RepoRepository;
import com.mwhive.advancedandroid.data.TrendingReposResponse;
import com.mwhive.advancedandroid.model.Repo;
import com.mwhive.advancedandroid.testutils.TestUtils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.functions.Consumer;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@SuppressWarnings("WeakerAccess")
public class TrendingReposPresenterTest {

  @Mock
  RepoRepository repoRepository;
  @Mock
  TrendingReposViewModel mViewModel;
  @Mock
  Consumer<Throwable> onErrorConsumer;
  @Mock
  Consumer<List<Repo>> onSuccessConsumer;
  @Mock
  Consumer<Boolean> loadingConsumer;

  private TrendingReposPresenter presenter;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    when(mViewModel.loadingUpdated()).thenReturn(loadingConsumer);
    when(mViewModel.onError()).thenReturn(onErrorConsumer);
    when(mViewModel.reposUpdated()).thenReturn(onSuccessConsumer);
  }

  @Test
  public void reposLoaded() throws Exception {
    List<Repo> mRepos = setUpSuccess();
    initializePresenter();

    verify(repoRepository).getTrendingRepos();
    verify(onSuccessConsumer).accept(mRepos);
    verifyZeroInteractions(onErrorConsumer);
  }

  @Test
  public void reposLoadedError() throws Exception {
    Throwable error = setUpError();
    initializePresenter();

    verify(onErrorConsumer).accept(error);
    verifyZeroInteractions(onSuccessConsumer);
  }

  @Test
  public void loadingSuccess() throws Exception {
    setUpSuccess();
    initializePresenter();

    InOrder inOrder = Mockito.inOrder(loadingConsumer);
    inOrder.verify(loadingConsumer).accept(true);
    inOrder.verify(loadingConsumer).accept(false);
  }

  @Test
  public void loadingError() throws Exception {
    //noinspection ThrowableNotThrown
    setUpError();
    initializePresenter();

    InOrder inOrder = Mockito.inOrder(loadingConsumer);
    inOrder.verify(loadingConsumer).accept(true);
    inOrder.verify(loadingConsumer).accept(false);
  }

  @Test
  public void onRepoClicked() {
    //TODO

  }

  private List<Repo> setUpSuccess() {
    TrendingReposResponse mResponse = TestUtils
        .loadJson("mock/get_trending_repos.json", TrendingReposResponse.class);
    List<Repo> mRepos = mResponse.repos();

    when(repoRepository.getTrendingRepos()).thenReturn(Single.just(mRepos));

    return mRepos;
  }

  private Throwable setUpError() {
    Throwable error = new IOException();
    when(repoRepository.getTrendingRepos()).thenReturn(Single.error(error));

    return error;
  }

  private void initializePresenter() {
    presenter = new TrendingReposPresenter(mViewModel, repoRepository);
  }
}