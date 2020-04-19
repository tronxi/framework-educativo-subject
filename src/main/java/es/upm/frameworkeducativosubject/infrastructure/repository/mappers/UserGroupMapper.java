package es.upm.frameworkeducativosubject.infrastructure.repository.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserGroupMapper {

    @Insert("insert into USER_GROUP (ID_GROUP, ID_USER) VALUES " +
            "(#{idGroup}, #{idStudent})")
    void saveStudent(String idGroup, String idStudent);
}
