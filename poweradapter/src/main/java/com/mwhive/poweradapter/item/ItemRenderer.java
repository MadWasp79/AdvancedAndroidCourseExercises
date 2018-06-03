package com.mwhive.poweradapter.item;


import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by MadWasp79 on 05-May-18.
 */

public interface ItemRenderer<T extends RecyclerItem> {

  @LayoutRes
  int layoutRes();

  View createView(@NonNull ViewGroup parent);

  void render(@NonNull View itemView, @NonNull T item);
}
