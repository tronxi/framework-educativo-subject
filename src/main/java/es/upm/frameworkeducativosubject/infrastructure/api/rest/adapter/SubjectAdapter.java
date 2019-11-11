package es.upm.frameworkeducativosubject.infrastructure.api.rest.adapter;

import es.upm.frameworkeducativosubject.domain.model.Group;
import es.upm.frameworkeducativosubject.domain.model.Subject;
import es.upm.frameworkeducativosubject.domain.port.primary.DeleteSubjectService;
import es.upm.frameworkeducativosubject.domain.port.primary.FindSubjectService;
import es.upm.frameworkeducativosubject.domain.port.primary.LoadSubjectService;
import es.upm.frameworkeducativosubject.domain.port.primary.UpdateSubjectService;
import es.upm.frameworkeducativosubject.infrastructure.api.rest.model.GroupDTO;
import es.upm.frameworkeducativosubject.infrastructure.api.rest.model.SubjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sun.security.provider.certpath.OCSPResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectAdapter {

    @Autowired
    private LoadSubjectService loadSubjectService;

    @Autowired
    private FindSubjectService findSubjectService;

    @Autowired
    private UpdateSubjectService updateSubjectService;

    @Autowired
    private DeleteSubjectService deleteSubjectService;


    public ResponseEntity subjectLoadAdapter(SubjectDTO subjectDTO) {
        Subject subject = subjectDTOToSubject(subjectDTO);
        return new ResponseEntity(loadSubjectService.loadSubject(subject));
    }

    public ResponseEntity updateSubjectAdapter(SubjectDTO subjectDTO) {
        Subject subject = subjectDTOToSubject(subjectDTO);
        return new ResponseEntity(updateSubjectService.updateSubject(subject));
    }

    public ResponseEntity<SubjectDTO> getSubjectByIdAdapter(String id) {
        Subject subject = findSubjectService.findSubjectById(id);
        SubjectDTO subjectDTO = subjectToSubjectDTO(subject);
        return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
    }

    public ResponseEntity<SubjectDTO> getSubjectByNameYear(String name, String year) {
        Subject subject = findSubjectService.findSubjectByNameYear(name, year);
        SubjectDTO subjectDTO = subjectToSubjectDTO(subject);
        return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
    }

    public ResponseEntity deleteSubjectById(String id) {
        return new ResponseEntity(deleteSubjectService.deleteSubject(id));
    }

    private SubjectDTO subjectToSubjectDTO(Subject subject) {
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

    private Subject subjectDTOToSubject(SubjectDTO subjectDTO) {
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
