package com.mwhive.advancedandroid.details;


import com.google.auto.value.AutoValue;
import com.mwhive.advancedandroid.model.Contributor;
import java.util.List;
import javax.annotation.Nullable;

/**
 * Created by MadWasp79 on 11-Apr-18.
 */
@AutoValue
abstract class ContributorState {

  abstract boolean loading();

  @Nullable
  abstract List<Contributor> contributors();

  @Nullable
  abstract Integer errorRes();

  boolean isSuccess(){
    return errorRes() == null;
  }

  static Builder builder(){
    return new AutoValue_ContributorState.Builder();
  }

  @AutoValue.Builder
  abstract static class Builder{

    abstract Builder loading(boolean loading);

    abstract Builder contributors(List<Contributor> contributors);

    abstract Builder errorRes(Integer errorRes);

    abstract ContributorState build();
  }

}
