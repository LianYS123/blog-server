package fun.lianys.blogserver.utils;

public class Utils {

  public static Integer getCurrentTime() {
    Long time = System.currentTimeMillis() / 1000;
    Integer currentTime = time.intValue();
    return currentTime;
  }

}
