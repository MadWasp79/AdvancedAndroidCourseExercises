package com.mwhive.poweradapter.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.mwhive.poweradapter.item.ItemRenderer;
import com.mwhive.poweradapter.item.RecyclerItem;

/**
 * Created by MadWasp79 on 07-May-18.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

  private final ItemRenderer<RecyclerItem> renderer;

  public RecyclerViewHolder(ViewGroup parent, ItemRenderer<RecyclerItem> renderer) {
    super(renderer.createView(parent));
    this.renderer = renderer;
  }


  void bind(RecyclerItem item) {
    renderer.render(itemView, item);
  }
}
