package es.upm.frameworkeducativosubject.domain.service.impl;

import es.upm.frameworkeducativosubject.domain.model.Group;
import es.upm.frameworkeducativosubject.domain.model.Subject;
import es.upm.frameworkeducativosubject.domain.port.primary.UpdateSubjectService;
import es.upm.frameworkeducativosubject.domain.port.secundary.IGroupRepository;
import es.upm.frameworkeducativosubject.domain.port.secundary.ISubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UpdateSubjectServiceImpl implements UpdateSubjectService {

    @Autowired
    ISubjectRepository subjectRepository;

    @Autowired
    IGroupRepository groupRepository;


    @Override
    public HttpStatus updateSubject (Subject subject) {
        try {
            subjectRepository.updateSubject(subject);
            updateGroups(subject);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }
    private void updateGroups(Subject subject) {
        List<Group> groups = groupRepository.getGroupBySubjectId(subject.getId_subject());

        List<Group> updatedGroups = subject.getGroups().stream()
                .filter(group -> !groups.contains(group))
                .map(group -> groupRepository.saveGroup(group.getName(), group.getId_subject()))
                .collect(Collectors.toList());

        List<Group> deleteGroups = groups.stream()
                .filter(group -> !subject.getGroups().contains(group))
                .map(group -> groupRepository.deleteGroupById(group.getId_group()))
                .collect(Collectors.toList());
    }
}
