package fun.lianys.blogserver.dao;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import fun.lianys.blogserver.model.dto.PageParamDto;
import fun.lianys.blogserver.model.dto.TagDto;
import fun.lianys.blogserver.model.entity.Tag;

@Repository
public interface TagDao {

  public List<Tag> list(PageParamDto params);

  public List<Tag> listAll();

  @Insert("insert into tag set `tag_name`=#{tagName}, `color`=#{color}, `description`=#{description}, `create_time`=${createTime}, `update_time`=${updateTime}")
  public Integer add(TagDto tag);

  @Update("update tag set `tag_name`=#{tagName}, `color`=#{color}, `description`=#{description}, `update_time`=${updateTime} where id=#{id}")
  public Integer update(TagDto tag);

  @Delete("delete from tag where id=#{id}")
  public Integer delete(Integer id);

}
