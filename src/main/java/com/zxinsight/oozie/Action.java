package com.zxinsight.oozie;
public interface Action<T> {
  //
  public boolean execute(T t) throws Exception;

}
