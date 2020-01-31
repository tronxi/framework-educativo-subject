package es.upm.frameworkeducativosubject.domain.useCase;

import es.upm.frameworkeducativosubject.domain.model.Group;
import es.upm.frameworkeducativosubject.domain.model.Subject;
import es.upm.frameworkeducativosubject.domain.port.primary.UpdateSubject;
import es.upm.frameworkeducativosubject.domain.port.secundary.GroupRepository;
import es.upm.frameworkeducativosubject.domain.port.secundary.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UpdateSubjectUseCase implements UpdateSubject {

    private final SubjectRepository subjectRepository;
    private final GroupRepository groupRepository;

    @Override
    public void updateSubject (Subject subject) throws Exception{
        try {
            subjectRepository.updateSubject(subject);
            updateGroups(subject);
        } catch (Exception e) {
            throw new Exception();
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
