package es.upm.frameworkeducativosubject.infrastructure.repository.mappers;

import es.upm.frameworkeducativosubject.infrastructure.repository.model.GroupDAO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GroupMapper {
    @Select("select ID_GROUP, ID_SUBJECT, NAME_GROUP " +
            "FROM GROUPS WHERE ID_SUBJECT = #{subject_id}")
    List<GroupDAO> getGroupBySubjectId(String subject_id);
}
