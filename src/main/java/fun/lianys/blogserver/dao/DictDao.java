package fun.lianys.blogserver.dao;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import fun.lianys.blogserver.model.dto.AddDictDto;
import fun.lianys.blogserver.model.dto.EditDictDto;
import fun.lianys.blogserver.model.dto.PageParamDto;
import fun.lianys.blogserver.model.entity.Dict;

@Repository
public interface DictDao {

  public List<Dict> list(PageParamDto params);

  public List<Dict> listAll();

  @Insert("insert into dict set `key`=#{key}, `value`=#{value}, `description`=#{description}, `create_time`=${createTime}, `update_time`=${updateTime}")
  public Integer add(AddDictDto dict);

  @Update("update dict set `key`=#{key}, `value`=#{value}, `description`=#{description}, `update_time`=${updateTime} where id=#{id}")
  public Integer update(EditDictDto dict);

  @Delete("delete from dict where id=#{id}")
  public Integer delete(Integer id);

}
