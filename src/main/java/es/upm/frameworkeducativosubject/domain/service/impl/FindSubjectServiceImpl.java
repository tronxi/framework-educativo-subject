package es.upm.frameworkeducativosubject.domain.service.impl;

import es.upm.frameworkeducativosubject.domain.model.Group;
import es.upm.frameworkeducativosubject.domain.model.Subject;
import es.upm.frameworkeducativosubject.domain.port.primary.FindSubjectService;
import es.upm.frameworkeducativosubject.domain.port.secundary.IGroupRepository;
import es.upm.frameworkeducativosubject.domain.port.secundary.ISubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindSubjectServiceImpl implements FindSubjectService {

    ISubjectRepository subjectRepository;

    IGroupRepository groupRepository;

    @Autowired
    public FindSubjectServiceImpl(ISubjectRepository subjectRepository,
                                  IGroupRepository groupRepository) {
        this.subjectRepository = subjectRepository;
        this.groupRepository = groupRepository;
    }

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
}
