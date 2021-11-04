package fun.lianys.blogserver.service;

import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.lianys.blogserver.dao.TagDao;
import fun.lianys.blogserver.model.dto.TagDto;
import fun.lianys.blogserver.model.dto.PageParamDto;
import fun.lianys.blogserver.model.entity.Tag;

@Service
public class TagService {

  @Autowired
  TagDao tagDao;

  private TagDto convertTagToDto(Tag tag) {
    System.out.println(tag);
    TagDto tagDto = new TagDto(tag.getId(), tag.getTagName(), tag.getColor(), tag.getDescription(),
        tag.getCreateTime(), tag.getUpdateTime());
    return tagDto;
  }

  public PageInfo<TagDto> list(PageParamDto params) {

    Page<Tag> p = PageHelper.startPage(params.getPage(), params.getPageSize());
    tagDao.list(params);
    List<TagDto> list = new ArrayList<>();
    for (Tag tag : p.getResult()) {
      list.add(convertTagToDto(tag));
    }
    return new PageInfo<TagDto>(list);
  }


  public List<TagDto> listAll() {
    List<TagDto> list = new ArrayList<>();
    for (Tag tag : tagDao.listAll()){
      list.add(convertTagToDto(tag));
    }
    return list;
  }

  public Integer add(TagDto tag) {
    return tagDao.add(tag);
  }

  public Integer update(TagDto tag) {
    return tagDao.update(tag);
  }

  public Integer delete(Integer id) {
    return tagDao.delete(id);
  }

}
