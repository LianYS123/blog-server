package fun.lianys.blogserver.service;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.lianys.blogserver.dao.RoleDao;
import fun.lianys.blogserver.model.dto.RoleDto;
import fun.lianys.blogserver.model.dto.PageParamDto;
import fun.lianys.blogserver.model.entity.Role;

@Service
public class RoleService {

  @Autowired
  RoleDao roleDao;

  public PageInfo<Role> list(PageParamDto params) {
    PageHelper.startPage(params.getPage(), params.getPageSize());
    List<Role> roleList = roleDao.list(params);
    PageInfo<Role> pageInfo = new PageInfo<>(roleList);
    return pageInfo;
  }

  public Integer add(RoleDto role) {
    return roleDao.add(role);
  }

  public Integer update(RoleDto role) {
    return roleDao.update(role);
  }

  public Integer delete(Integer id) {
    return roleDao.delete(id);
  }

}
