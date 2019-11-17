package es.upm.frameworkeducativosubject.infrastructure.repository.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubjectUserMapper {

    @Insert("insert into SUBJECT_USER (ID_SUBJECT, ID_USER) VALUES " +
            "(#{idSubject}, #{idTeacher})")
    void setTeacher(String idSubject, String idTeacher);

    @Delete("delete from SUBJECT_USER where ID_SUBJECT = #{idSubject} " +
            "and ID_USER = #{idTeacher}")
    void deleteTeacher(String idSubject, String idTeacher);
}
