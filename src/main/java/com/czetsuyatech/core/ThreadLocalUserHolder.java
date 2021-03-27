package com.czetsuyatech.core;

public class ThreadLocalUserHolder {

  private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

  public static void set(String user) {
    threadLocal.set(user);
  }

  public static String get() {
    return threadLocal.get();
  }

  public static void remove() {
    threadLocal.remove();
  }
}
