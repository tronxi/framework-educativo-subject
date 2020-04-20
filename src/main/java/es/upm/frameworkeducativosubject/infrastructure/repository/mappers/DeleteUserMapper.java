package es.upm.frameworkeducativosubject.infrastructure.repository.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeleteUserMapper {

    @Delete("delete from SUBJECT_USER where ID_USER = #{id}")
    void deleteTeacher(String id);

    @Delete("delete from USER_GROUP where ID_USER = #{id}")
    void deleteStudent(String id);
}
