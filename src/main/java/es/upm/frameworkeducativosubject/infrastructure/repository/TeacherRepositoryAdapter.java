package es.upm.frameworkeducativosubject.infrastructure.repository;

import es.upm.frameworkeducativosubject.infrastructure.repository.mappers.SubjectUserMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TeacherRepositoryAdapter implements es.upm.frameworkeducativosubject.domain.port.secundary.TeacherRepository {

    private final SubjectUserMapper subjectUserMapper;

    @Override
    public void setTeacher(String idSubject, String idTeacher)  throws Exception{
        try {
            subjectUserMapper.setTeacher(idSubject, idTeacher);
        } catch (PersistenceException e) {
            throw new Exception("ex");
        }
    }

    @Override
    public void deleteTeacher(String idSubject, String idTeacher) throws Exception{
        try {
            subjectUserMapper.deleteTeacher(idSubject, idTeacher);
        } catch (PersistenceException e) {
            throw new Exception("ex");
        }
    }
}