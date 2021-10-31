package fun.lianys.blogserver.utils;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ArticleUtils {

  /**
   * 获取HTML的预览信息，其中content是对象的一个属性，也就是待处理的HTML内容
   */
  public String getPreviewContent(String content, Integer count) {
    // 截取前N个字符
    String htmlStr = content; // 含html标签的字符串
    String textStr = "";
    java.util.regex.Pattern p_script;
    java.util.regex.Matcher m_script;
    java.util.regex.Pattern p_style;
    java.util.regex.Matcher m_style;
    java.util.regex.Pattern p_html;
    java.util.regex.Matcher m_html;
    try {
      String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
      String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
      String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
      p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
      m_script = p_script.matcher(htmlStr);
      htmlStr = m_script.replaceAll(""); // 过滤script标签
      p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
      m_style = p_style.matcher(htmlStr);
      htmlStr = m_style.replaceAll(""); // 过滤style标签
      p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
      m_html = p_html.matcher(htmlStr);
      htmlStr = m_html.replaceAll(""); // 过滤html标签
      textStr = htmlStr;
    } catch (Exception e) {
      log.error("Html2Text: " + e.getMessage());
    }
    // 剔除空格行
    textStr = textStr.replaceAll("[ ]+", " ");
    textStr = textStr.replaceAll("(?m)^\\s*$(\\n|\\r\\n)", "");

    if (textStr.length() > count) {
      return textStr.substring(0, count);
    }
    return textStr;// 返回文本字符串
  }
}
