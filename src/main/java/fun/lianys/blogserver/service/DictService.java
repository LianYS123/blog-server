package fun.lianys.blogserver.service;

import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.lianys.blogserver.dao.DictDao;
import fun.lianys.blogserver.model.dto.AddDictDto;
import fun.lianys.blogserver.model.dto.EditDictDto;
import fun.lianys.blogserver.model.dto.PageParamDto;
import fun.lianys.blogserver.model.entity.Dict;
import fun.lianys.blogserver.model.vo.DictVO;

@Service
public class DictService {

  @Autowired
  DictDao dictDao;

  private DictVO convertDictToVO(Dict dict) {
    System.out.println(dict);
    DictVO dictVO = new DictVO(dict.getId(), dict.getKey(), dict.getValue(), dict.getDescription(),
        dict.getCreateTime(), dict.getUpdateTime());
    return dictVO;
  }

  public PageInfo<DictVO> list(PageParamDto params) {

    Page<Dict> p = PageHelper.startPage(params.getPage(), params.getPageSize());
    dictDao.list(params);
    List<DictVO> list = new ArrayList<>();
    for (Dict dict : p.getResult()) {
      list.add(convertDictToVO(dict));
    }
    return new PageInfo<DictVO>(list);
  }


  public List<DictVO> listAll() {
    List<DictVO> list = new ArrayList<>();
    for (Dict dict : dictDao.listAll()){
      list.add(convertDictToVO(dict));
    }
    return list;
  }

  public Integer add(AddDictDto dict) {
    return dictDao.add(dict);
  }

  public Integer update(EditDictDto dict) {
    return dictDao.update(dict);
  }

  public Integer delete(Integer id) {
    return dictDao.delete(id);
  }

}
