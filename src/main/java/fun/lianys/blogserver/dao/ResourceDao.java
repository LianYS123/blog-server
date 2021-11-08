package fun.lianys.blogserver.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import fun.lianys.blogserver.model.dto.PageParamDto;
import fun.lianys.blogserver.model.dto.ResourceDto;
import fun.lianys.blogserver.model.entity.Resource;

@Repository
public interface ResourceDao {

  public List<Resource> list(PageParamDto params);

  public List<Resource> listAll();

  public Integer add(ResourceDto resource);

  public Integer update(ResourceDto resource);

  public Integer delete(Integer id);

}
