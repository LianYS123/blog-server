package fun.lianys.blogserver.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.lianys.blogserver.dao.EssayDao;
import fun.lianys.blogserver.model.dto.EssayDto;
import fun.lianys.blogserver.model.dto.PageParamDto;
import fun.lianys.blogserver.model.entity.Essay;
import fun.lianys.blogserver.utils.CurrentUserUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EssayService {

  private final EssayDao essayDao;
  private final CurrentUserUtils currentUserUtils;

  public PageInfo<Essay> list(PageParamDto params) {

    Page<Essay> p = PageHelper.startPage(params.getPage(), params.getPageSize());
    List<Essay> list = essayDao.list(params);
    PageInfo pageInfo = new PageInfo(list);
    return pageInfo;
  }


  public Integer add(EssayDto essay) {
    Integer author = currentUserUtils.getId();
    essay.setAuthor(author);
    return essayDao.add(essay);
  }

  public Integer update(EssayDto essay) {
    return essayDao.update(essay);
  }

  public Integer delete(Integer id) {
    return essayDao.delete(id);
  }

}
