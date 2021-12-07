package fun.lianys.blogserver.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusEnum {
  NORMAL("NORMAL", "正常状态"),
  PRIVATE("PRIVATE", "仅自己可见"),
  IN_AUDIT("IN_AUDIT", "审核中"),
  AUDIT_PASS("AUDIT_PASS", "审核通过"),
  AUDIT_UNPASS("AUDIT_UNPASS", "审核拒绝"),
  DELETED("DELETED", "已删除");

  private String value;
  private String desc;
}
