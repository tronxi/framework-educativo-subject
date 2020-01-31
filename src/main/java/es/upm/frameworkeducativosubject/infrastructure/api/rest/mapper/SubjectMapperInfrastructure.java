package es.upm.frameworkeducativosubject.infrastructure.api.rest.mapper;

import es.upm.frameworkeducativosubject.domain.model.Group;
import es.upm.frameworkeducativosubject.domain.model.Subject;
import es.upm.frameworkeducativosubject.infrastructure.api.rest.model.GroupDTO;
import es.upm.frameworkeducativosubject.infrastructure.api.rest.model.SubjectDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectMapperInfrastructure {

    public SubjectDTO subjectToSubjectDTO(Subject subject) {
        List<GroupDTO> groupsDTO = subject.getGroups().stream()
                .map(this::groupToGroupDTO)
                .collect(Collectors.toList());

        return SubjectDTO.builder()
                .idSubject(subject.getId_subject())
                .name(subject.getName())
                .year(subject.getYear())
                .groups(groupsDTO)
                .build();
    }

    public Subject subjectDTOToSubject(SubjectDTO subjectDTO) {
        List<Group> groups = subjectDTO.getGroups().stream()
                .map(this::groupDTOtoGroup)
                .collect(Collectors.toList());

        return Subject.builder()
                .id_subject(subjectDTO.getIdSubject())
                .name(subjectDTO.getName())
                .year(subjectDTO.getYear())
                .groups(groups)
                .build();
    }

    private GroupDTO groupToGroupDTO(Group group) {
        return GroupDTO.builder()
                .id_group(group.getId_group())
                .id_subject(group.getId_subject())
                .name(group.getName())
                .build();
    }

    private Group groupDTOtoGroup(GroupDTO groupDTO) {
        return Group.builder()
                .id_group(groupDTO.getId_group())
                .id_subject(groupDTO.getId_subject())
                .name(groupDTO.getName())
                .build();
    }
}
