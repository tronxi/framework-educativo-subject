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

    @Autowired
    ISubjectRepository subjectRepository;

    @Autowired
    IGroupRepository groupRepository;

    @Override
    public Subject findSubjectById(String id) {
        //return subjectRepository.getSubjectById(id);
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
