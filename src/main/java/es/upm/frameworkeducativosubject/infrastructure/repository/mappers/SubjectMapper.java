package es.upm.frameworkeducativosubject.infrastructure.repository.mappers;

import es.upm.frameworkeducativosubject.infrastructure.repository.model.SubjectEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface SubjectMapper {

    @Select("select ID_SUBJECT, NAME, YEAR  " +
            "FROM SUBJECT WHERE ID_SUBJECT = #{id}")
    SubjectEntity getSubjectById(String id);

    @Select("SELECT ID_SUBJECT, NAME, YEAR  from SUBJECT where id_subject in " +
            "(SELECT ID_SUBJECT FROM GROUPS WHERE ID_GROUP IN (" +
            "SELECT ID_GROUP FROM USER_GROUP WHERE ID_USER = #{studentId})" +
            ")")
    List<SubjectEntity> getSubjectByStudentId(String studentId);

    @Select("SELECT ID_SUBJECT, NAME, YEAR  from SUBJECT where id_subject in " +
            "(SELECT ID_SUBJECT FROM SUBJECT_USER WHERE ID_USER = #{studentId})")
    List<SubjectEntity> getSubjectByTeacherId(String studentId);

    @Select("select ID_SUBJECT, NAME, YEAR  " +
            "FROM SUBJECT WHERE NAME = #{name} and YEAR = #{year}")
    SubjectEntity getSubjectByNameYear(String name, String year);

    @Select("insert into SUBJECT (NAME, YEAR) VALUES" +
            "(#{name}, #{year})")
    void saveSubject(String name, String year);

    @Update("update SUBJECT set NAME = #{name}, YEAR = #{year} " +
            "where ID_SUBJECT = #{id}")
    void updateSubject(String id, String name, String year);

    @Delete("delete from SUBJECT where ID_SUBJECT = #{id}")
    void deleteSubjectById(String id);

}
