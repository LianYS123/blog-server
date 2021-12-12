package fun.lianys.blogserver.dao;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import fun.lianys.blogserver.model.dto.RoleDto;
import fun.lianys.blogserver.model.dto.PageParamDto;
import fun.lianys.blogserver.model.entity.Role;

@Repository
public interface RoleDao {

  public List<Role> list(PageParamDto params);

  @Insert("insert into role set `role_code`=#{roleCode}, `role_name`=#{roleName}, `role_desc`=#{roleDesc}, `create_time`=${createTime}, `update_time`=${updateTime}")
  public Integer add(RoleDto role);

  @Update("update role set `role_code`=#{roleCode}, `role_name`=#{roleName}, `role_desc`=#{roleDesc}, `update_time`=${updateTime} where id=#{id}")
  public Integer update(RoleDto role);

  @Delete("delete from role where id=#{id}")
  public Integer delete(Integer id);

}
