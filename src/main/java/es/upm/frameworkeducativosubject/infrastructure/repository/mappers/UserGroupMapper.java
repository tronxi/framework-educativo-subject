package es.upm.frameworkeducativosubject.infrastructure.repository.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserGroupMapper {

    @Insert("insert into USER_GROUP (ID_GROUP, ID_USER) VALUES " +
            "(#{idGroup}, #{idStudent})")
    void saveStudent(String idGroup, String idStudent);

    @Delete("delete from USER_GROUP where ID_GROUP = #{idGroup} " +
            "and ID_USER = #{idStudent}")
    void deleteStudent(String idGroup, String idStudent);

    @Select("select ID_USER from USER_GROUP where ID_GROUP = #{idGroup}")
    List<String> getIdUser(String idGroup);
}
