package es.upm.frameworkeducativosubject.infrastructure.api.rest.mapper;

import es.upm.frameworkeducativosubject.domain.model.Group;
import es.upm.frameworkeducativosubject.domain.model.Subject;
import es.upm.frameworkeducativosubject.domain.port.primary.*;
import es.upm.frameworkeducativosubject.infrastructure.api.rest.model.GroupDTO;
import es.upm.frameworkeducativosubject.infrastructure.api.rest.model.SubjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectMapperInfrastructure {

    private LoadSubjectService loadSubjectService;
    private FindSubjectService findSubjectService;
    private UpdateSubjectService updateSubjectService;
    private DeleteSubjectService deleteSubjectService;
    private TeacherService teacherService;

    @Autowired
    public SubjectMapperInfrastructure(LoadSubjectService loadSubjectService,
                                       FindSubjectService findSubjectService,
                                       UpdateSubjectService updateSubjectService,
                                       DeleteSubjectService deleteSubjectService,
                                       TeacherService teacherService) {
        this.loadSubjectService = loadSubjectService;
        this.findSubjectService = findSubjectService;
        this.updateSubjectService = updateSubjectService;
        this.deleteSubjectService = deleteSubjectService;
        this.teacherService = teacherService;
    }


    public ResponseEntity subjectLoadAdapter(SubjectDTO subjectDTO) {
        Subject subject = subjectDTOToSubject(subjectDTO);
        try {
            loadSubjectService.loadSubject(subject);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity updateSubjectAdapter(SubjectDTO subjectDTO) {
        Subject subject = subjectDTOToSubject(subjectDTO);
        try {
            updateSubjectService.updateSubject(subject);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
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
        try {
            deleteSubjectService.deleteSubject(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity setTeacher(String idSubject, String ident, String header) {
        try {
            teacherService.setTeacher(idSubject, ident, header);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
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
