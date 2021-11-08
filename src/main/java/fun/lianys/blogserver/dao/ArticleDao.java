package fun.lianys.blogserver.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import fun.lianys.blogserver.model.dto.AddArticleDto;
import fun.lianys.blogserver.model.dto.ArticleQueryDto;
import fun.lianys.blogserver.model.dto.EditArticleDto;
import fun.lianys.blogserver.model.entity.Article;

@Repository
public interface ArticleDao {
    public List<Article> list(ArticleQueryDto article);

    public Article findOne(Integer id);

    @Insert("insert into article set article_name=#{articleName}, html=#{html}, raw=#{raw}, cover=#{cover}, create_time=#{createTime}, update_time=#{updateTime}, author=#{author}")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public Integer add(AddArticleDto article);

    @Update("update article set article_name=#{articleName}, html=#{html}, raw=#{raw}, cover=#{cover}, update_time=#{updateTime} where id=#{id}")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public Integer update(EditArticleDto article);

    @Delete("delete from article where id=#{id}")
    public Integer delete(Integer id);

    public Integer updateTagBatch(Integer articleId, Integer[] tags);
}
