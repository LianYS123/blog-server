package fun.lianys.blogserver.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import fun.lianys.blogserver.model.dto.EssayDto;
import fun.lianys.blogserver.model.dto.PageParamDto;
import fun.lianys.blogserver.model.entity.Essay;

@Repository
public interface EssayDao {
    public List<Essay> list(PageParamDto param);

    @Insert("insert into essay set html=#{html}, raw=#{raw}, create_time=#{createTime}, update_time=#{updateTime}, author=#{author}")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public Integer add(EssayDto essay);

    @Update("update essay set html=#{html}, raw=#{raw}, update_time=#{updateTime} where id=#{id}")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public Integer update(EssayDto essay);

    @Delete("delete from essay where id=#{id}")
    public Integer delete(Integer id);

}
