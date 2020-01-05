package es.upm.frameworkeducativosubject.infrastructure.repository;

import es.upm.frameworkeducativosubject.domain.port.secundary.ITeacherRepository;
import es.upm.frameworkeducativosubject.infrastructure.repository.mappers.SubjectUserMapper;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherRepository implements ITeacherRepository {

    private SubjectUserMapper subjectUserMapper;

    @Autowired
    public TeacherRepository(SubjectUserMapper subjectUserMapper) {
        this.subjectUserMapper = subjectUserMapper;
    }
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
