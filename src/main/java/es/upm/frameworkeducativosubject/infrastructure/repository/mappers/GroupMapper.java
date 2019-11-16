package es.upm.frameworkeducativosubject.infrastructure.repository.mappers;

import es.upm.frameworkeducativosubject.infrastructure.repository.model.GroupDAO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GroupMapper {

    @Select("select ID_GROUP, ID_SUBJECT, NAME_GROUP " +
            "FROM GROUPS WHERE ID_SUBJECT = #{subject_id}")
    List<GroupDAO> getGroupBySubjectId(String subject_id);

    @Delete("delete from GROUPS where ID_GROUP = #{group_id}")
    void deleteGroupById(String group_id);

    @Delete("delete from GROUPS where ID_SUBJECT = #{subject_id}")
    void deleteGroupBySubjectId(String subject_id);

    @Select("insert into GROUPS (NAME, ID_SUBJECT) VALUES " +
            "(#{name}, #{year})")
    void loadGroup(String name, String subject_id);
}
