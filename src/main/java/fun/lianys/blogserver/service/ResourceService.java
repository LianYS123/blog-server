package fun.lianys.blogserver.service;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.lianys.blogserver.dao.ResourceDao;
import fun.lianys.blogserver.model.dto.ResourceDto;
import fun.lianys.blogserver.model.dto.PageParamDto;
import fun.lianys.blogserver.model.entity.Resource;

@Service
public class ResourceService {

  @Autowired
  ResourceDao resourceDao;

  private ResourceDto convertResourceToDto(Resource resource) {
    System.out.println(resource);
    ResourceDto resourceDto = new ResourceDto(resource.getId(), resource.getResourceName(), resource.getSrc(),
        resource.getType(), resource.getSize(), resource.getDescription(), resource.getCreateTime(),
        resource.getUpdateTime());
    return resourceDto;
  }

  public PageInfo<ResourceDto> list(PageParamDto params) {
    PageHelper.startPage(params.getPage(), params.getPageSize());
    List<Resource> resourceList = resourceDao.list(params);
    PageInfo pageInfo = new PageInfo(resourceList);
    List<ResourceDto> list = resourceList.stream().map((Resource resource) -> convertResourceToDto(resource)).toList();
    pageInfo.setList(list);
    return pageInfo;
  }

  public Integer add(ResourceDto resource) {
    return resourceDao.add(resource);
  }

  public Integer update(ResourceDto resource) {
    return resourceDao.update(resource);
  }

  public Integer delete(Integer id) {
    return resourceDao.delete(id);
  }

}
