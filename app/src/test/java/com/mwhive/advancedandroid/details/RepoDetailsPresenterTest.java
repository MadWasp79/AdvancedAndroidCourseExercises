package com.mwhive.advancedandroid.details;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import com.mwhive.advancedandroid.data.RepoRepository;
import com.mwhive.advancedandroid.lifecycle.DisposableManager;
import com.mwhive.advancedandroid.model.Contributor;
import com.mwhive.advancedandroid.model.Repo;
import com.mwhive.advancedandroid.testutils.TestUtils;
import com.squareup.moshi.Types;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import java.io.IOException;
import java.util.List;
import javax.annotation.Nullable;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


/**
 * Created by MadWasp79 on 12-Apr-18.
 */
public class RepoDetailsPresenterTest {

  private static final String OWNER = "owner";
  private static final String NAME = "name";

  @Mock RepoRepository repoRepository;
  @Mock RepoDetailsViewModel viewModel;
  @Mock Consumer<Repo> repoConsumer;
  @Mock Consumer<List<Contributor>> contributorConsumer;
  @Mock Consumer<Throwable> detailErrorConsumer;
  @Mock Consumer<Throwable> contributorErrorConsumer;

  private Repo repo = TestUtils.loadJson("mock/repos/get_repo.json", Repo.class);
  private List<Contributor> contributors = TestUtils
      .loadJson("mock/repos/contributors/get_contributors.json",
          Types.newParameterizedType(List.class, Contributor.class));
  private String contributorsUrl = repo.contributorsUrl();

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    when(viewModel.processRepo()).thenReturn(repoConsumer);
    when(viewModel.processContributors()).thenReturn(contributorConsumer);
    when(viewModel.detailsError()).thenReturn(detailErrorConsumer);
    when(viewModel.contributorsError()).thenReturn(contributorErrorConsumer);

    when(repoRepository.getRepo(OWNER, NAME)).thenReturn(Single.just(repo));
    when(repoRepository.getContributors(contributorsUrl)).thenReturn(Single.just(contributors));

  }

  private void initPresenter() {
    new RepoDetailsPresenter(OWNER, NAME, repoRepository, viewModel,
        Mockito.mock(DisposableManager.class));
  }

  @Test
  public void repoDetails() throws Exception {
    initPresenter();

    verify(repoConsumer).accept(repo);
  }

  @Test
  public void repoDetailsError() throws Exception {
    Throwable t = new IOException();
    when(repoRepository.getRepo(OWNER, NAME)).thenReturn(Single.error(t));
    initPresenter();

    verify(detailErrorConsumer).accept(t);
  }

  @Test
  public void repoContributors() throws Exception {
    initPresenter();

    verify(contributorConsumer).accept(contributors);
  }

  @Test
  public void repoContributorsError() throws Exception {
    Throwable t = new IOException();
    when(repoRepository.getContributors(contributorsUrl)).thenReturn(Single.error(t));
    initPresenter();

    verify(contributorErrorConsumer).accept(t);
    //Verify that our repo details were still processed even though the contributors failed to load
    verify(repoConsumer).accept(repo);

  }


}