package es.upm.frameworkeducativosubject.infrastructure.repository.mappers;

import es.upm.frameworkeducativosubject.infrastructure.repository.model.SubjectUserEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SubjectUserMapper {

    @Insert("insert into SUBJECT_USER (ID_SUBJECT, ID_USER) VALUES " +
            "(#{idSubject}, #{idTeacher})")
    void setTeacher(String idSubject, String idTeacher);

    @Delete("delete from SUBJECT_USER where ID_SUBJECT = #{idSubject} " +
            "and ID_USER = #{idTeacher}")
    void deleteTeacher(String idSubject, String idTeacher);

    @Select("select ID_SUBJECT, ID_USER from SUBJECT_USER " +
            "where ID_SUBJECT = #{idSubject}")
    List<SubjectUserEntity> getTeachers(String idSubject);
}
