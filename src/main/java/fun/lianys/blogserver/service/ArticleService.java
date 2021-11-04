package fun.lianys.blogserver.service;

import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.lianys.blogserver.dao.ArticleDao;
import fun.lianys.blogserver.exception.BaseException;
import fun.lianys.blogserver.model.dto.AddArticleDto;
import fun.lianys.blogserver.model.dto.ArticleQueryDto;
import fun.lianys.blogserver.model.dto.EditArticleDto;
import fun.lianys.blogserver.model.dto.TagDto;
import fun.lianys.blogserver.model.entity.Article;
import fun.lianys.blogserver.model.entity.JwtUser;
import fun.lianys.blogserver.model.entity.Tag;
import fun.lianys.blogserver.model.vo.ArticleDetailVO;
import fun.lianys.blogserver.model.vo.ArticleVO;
import fun.lianys.blogserver.utils.ArticleUtils;
import fun.lianys.blogserver.utils.Utils;

@Service
public class ArticleService {

  @Autowired
  ArticleDao articleDao;

  @Autowired
  ArticleUtils articleUtils;

  @Autowired
  Utils utils;

  private TagDto convertTagToDto(Tag tag) {
    if (tag == null) {
      return null;
    }
    TagDto tagDto = new TagDto(tag.getId(), tag.getTagName(), tag.getColor(), tag.getDescription(), tag.getCreateTime(),
        tag.getUpdateTime());
    return tagDto;
  }

  private ArticleVO convertArticleToArticleVO(Article a) {

    String cover = a.getCover();
    List<TagDto> tags = a.getTags().stream().map((Tag tag) -> convertTagToDto(tag)).toList();
    // 约束图片的大小
    // 参考 https://cloud.tencent.com/document/product/436/44893
    if (cover != null) {
      cover += "?imageView2/1/w/320/h/192/q/80";
    }
    Boolean hasAuthor = null != a.getAuthor();
    ArticleVO vo = new ArticleVO(a.getId(), a.getArticleName(), articleUtils.getPreviewContent(a.getHtml(), 500), cover,
        hasAuthor ? a.getAuthor().getId() : null, hasAuthor ? a.getAuthor().getUsername() : null, a.getCreateTime(),
        a.getUpdateTime(), tags);
    return vo;
  }

  private ArticleDetailVO convertArticleToArticleDetail(Article a) {
    Boolean hasAuthor = null != a.getAuthor();
    List<TagDto> tags = a.getTags().stream().map((Tag tag) -> convertTagToDto(tag)).toList();
    ArticleDetailVO vo = new ArticleDetailVO(a.getId(), a.getArticleName(), a.getHtml(), a.getRaw(), a.getCover(),
        hasAuthor ? a.getAuthor().getId() : null, hasAuthor ? a.getAuthor().getUsername() : null, a.getCreateTime(),
        a.getUpdateTime(), tags);
    return vo;
  }

  public PageInfo<ArticleVO> list(ArticleQueryDto article) {
    Page<Article> p = PageHelper.startPage(article.getPage(), article.getPageSize());
    articleDao.list(article);
    List<ArticleVO> voList = new ArrayList<>();
    for (Article a : p.getResult()) {
      voList.add(convertArticleToArticleVO(a));
    }
    return new PageInfo<ArticleVO>(voList);
  }

  public ArticleDetailVO findOne(Integer id) {
    Article article = articleDao.findOne(id);
    return convertArticleToArticleDetail(article);
  }

  public Integer add(AddArticleDto article) {
    articleDao.add(article);
    return article.getId();
  }

  public Integer update(EditArticleDto article) {
    articleDao.update(article);
    return article.getId();
  }

  public Integer delete(Integer id) {
    Article article = articleDao.findOne(id);
    JwtUser user = utils.getCurrentUser();
    if (user.getId() != article.getAuthor().getId()) {
      // 当前用户不是作者
      throw new BaseException("1005", "权限不足");
    }
    articleDao.delete(id);
    return id;
  }
}
