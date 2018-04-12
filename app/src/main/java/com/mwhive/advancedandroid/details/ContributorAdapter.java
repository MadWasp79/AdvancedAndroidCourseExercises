package com.mwhive.advancedandroid.details;


import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.mwhive.advancedandroid.R;
import com.mwhive.advancedandroid.details.ContributorAdapter.ContributorViewHolder;
import com.mwhive.advancedandroid.model.Contributor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MadWasp79 on 13-Apr-18.
 */
class ContributorAdapter extends RecyclerView.Adapter<ContributorViewHolder>{

  private final List<Contributor> data = new ArrayList<>();

  ContributorAdapter(){
    setHasStableIds(true);
  }

  @NonNull
  @Override
  public ContributorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.view_user_list_item, parent, false);
    return new ContributorViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(@NonNull ContributorViewHolder holder, int position) {
    holder.bind(data.get(position));
  }

  @Override
  public int getItemCount() {
    return data.size();
  }

  @Override
  public long getItemId(int position) {
    return data.get(position).id();
  }

  void setData(List<Contributor> contributors) {
    if(contributors != null) {
      DiffUtil.DiffResult diffResult = DiffUtil
          .calculateDiff(new ContributorDiffCallback(data, contributors));
      data.clear();
      data.addAll(contributors);
      diffResult.dispatchUpdatesTo(this);
    } else {
      data.clear();
      notifyDataSetChanged();
    }
  }

  static final class ContributorViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.tv_user_name) TextView usernameText;
    @BindView(R.id.iv_avatar) ImageView avatarImageView;

    public ContributorViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    void bind(Contributor contributor) {
      usernameText.setText(contributor.login());
      Glide.with(avatarImageView.getContext())
          .load(contributor.avatarUrl())
          .into(avatarImageView);
    }
  }

}
