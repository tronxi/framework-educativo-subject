package es.upm.frameworkeducativosubject.infrastructure.repository;

import es.upm.frameworkeducativosubject.domain.model.Subject;
import es.upm.frameworkeducativosubject.domain.port.secundary.SubjectRepository;
import es.upm.frameworkeducativosubject.infrastructure.repository.mappers.SubjectMapper;
import es.upm.frameworkeducativosubject.infrastructure.repository.model.SubjectEntity;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class SubjectRepositoryAdapter implements SubjectRepository {

    private final SubjectMapper subjectMapper;

    @Override
    public Subject getSubjectById(String id) {
        return subjectEntityToSubject(subjectMapper.getSubjectById(id));
    }

    @Override
    public Subject getSubjectByNameYear(String name, String year) {
        return subjectEntityToSubject(subjectMapper.getSubjectByNameYear(name, year));
    }

    @Override
    public List<Subject> getSubjectByStudentId(String studentId) {
        List<SubjectEntity> subjectEntityList = subjectMapper.getSubjectByStudentId(studentId);
        return subjectEntityList.stream()
                .map(this::subjectEntityToSubject)
                .collect(Collectors.toList());
    }

    @Override
    public List<Subject> getSubjectByTeacherId(String teacherId) {
        List<SubjectEntity> subjectEntityList = subjectMapper.getSubjectByTeacherId(teacherId);
        return subjectEntityList.stream()
                .map(this::subjectEntityToSubject)
                .collect(Collectors.toList());
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

    private Subject subjectEntityToSubject(SubjectEntity subjectEntity) {
        return Subject.builder()
                .id_subject(subjectEntity.getIdSubject())
                .name(subjectEntity.getName())
                .year(subjectEntity.getYear())
                .build();
    }
}
