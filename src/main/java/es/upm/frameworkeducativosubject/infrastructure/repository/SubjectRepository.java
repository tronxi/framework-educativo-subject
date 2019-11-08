package es.upm.frameworkeducativosubject.infrastructure.repository;

import es.upm.frameworkeducativosubject.domain.model.Subject;
import es.upm.frameworkeducativosubject.domain.port.secundary.ISubjectRepository;
import es.upm.frameworkeducativosubject.infrastructure.repository.mappers.SubjectMapper;
import es.upm.frameworkeducativosubject.infrastructure.repository.model.SubjectDAO;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectRepository implements ISubjectRepository {

    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public Subject getSubjectById(String id) {
        return subjectDAOToSubject(subjectMapper.getSubjectById(id));
    }

    @Override
    public Subject getSubjectByNameYear(String name, String year) {
        return subjectDAOToSubject(subjectMapper.getSubjectByNameYear(name, year));
    }


    @Override
    public void saveSubject(Subject subject) throws Exception {
        try {
            subjectMapper.saveSubject(
                    subject.getName(), subject.getYear());
        } catch (PersistenceException e) {
            throw new Exception("ex");
        }

    }

    @Override
    public void updateSubject(Subject subject) throws Exception {
        try {
            subjectMapper.updateSubject(
                    subject.getId_subject(), subject.getName(),
                    subject.getYear());
        } catch (PersistenceException e) {
            throw new Exception("ex");
        }
    }

    @Override
    public void deleteSubjectById(String id) {
        subjectMapper.deleteSubjectById(id);
    }

    private Subject subjectDAOToSubject(SubjectDAO subjectDAO) {
        return Subject.builder()
                .id_subject(subjectDAO.getIdSubject())
                .name(subjectDAO.getName())
                .year(subjectDAO.getYear())
                .build();
    }
}
