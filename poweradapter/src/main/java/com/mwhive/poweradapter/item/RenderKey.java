package com.mwhive.poweradapter.item;


import dagger.MapKey;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by MadWasp79 on 05-May-18.
 */

@MapKey
@Target(ElementType.METHOD)
public @interface RenderKey {

  String value();

}
