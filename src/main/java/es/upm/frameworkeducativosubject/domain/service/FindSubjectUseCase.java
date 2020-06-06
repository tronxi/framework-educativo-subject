package es.upm.frameworkeducativosubject.domain.service;

import es.upm.frameworkeducativosubject.domain.model.Group;
import es.upm.frameworkeducativosubject.domain.model.Subject;
import es.upm.frameworkeducativosubject.domain.port.primary.FindSubject;
import es.upm.frameworkeducativosubject.domain.port.secundary.GroupRepository;
import es.upm.frameworkeducativosubject.domain.port.secundary.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindSubjectUseCase implements FindSubject {

    private final SubjectRepository subjectRepository;

    private final GroupRepository groupRepository;

    @Override
    public Subject findSubjectById(String id) {
        Subject subject = subjectRepository.getSubjectById(id);
        List<Group> group = groupRepository.getGroupBySubjectId(subject.getId_subject());
        subject.setGroups(group);
        return subject;
    }

    @Override
    public Subject findSubjectByNameYear(String name, String year) {
        Subject subject = subjectRepository.getSubjectByNameYear(name, year);
        List<Group> group = groupRepository.getGroupBySubjectId(subject.getId_subject());
        subject.setGroups(group);
        return subject;
    }

    @Override
    public List<Subject> findSubjectByStudentId(String studentId) {
        List<Subject> subjectList = subjectRepository.getSubjectByStudentId(studentId);
        subjectList.forEach(subject -> {
            List<Group> groupList = groupRepository.getGroupByStudentIdAndSubjectId(studentId, subject.getId_subject());
            subject.setGroups(groupList);
        });
        return subjectList;
    }
}
