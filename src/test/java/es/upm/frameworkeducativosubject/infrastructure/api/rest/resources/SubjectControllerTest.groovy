package es.upm.frameworkeducativosubject.infrastructure.api.rest.resources

import es.upm.frameworkeducativosubject.domain.model.Group
import es.upm.frameworkeducativosubject.domain.model.Subject
import es.upm.frameworkeducativosubject.domain.service.*
import es.upm.frameworkeducativosubject.infrastructure.api.rest.mapper.SubjectMapperInfrastructure
import es.upm.frameworkeducativosubject.infrastructure.api.rest.mapper.UserMapperInfrastructure
import es.upm.frameworkeducativosubject.infrastructure.api.rest.model.GroupDTO
import es.upm.frameworkeducativosubject.infrastructure.api.rest.model.SubjectDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Shared
import spock.lang.Specification

class SubjectControllerTest extends Specification {
    @Shared
    SubjectMapperInfrastructure subjectMapperInfrastructure
    @Shared
    UserMapperInfrastructure userMapperInfrastructure

    @Shared
    SubjectController subjectController
    @Shared
    LoadSubjectUseCase loadSubject
    @Shared
    FindSubjectUseCase findSubject
    @Shared
    UpdateSubjectUseCase updateSubject
    @Shared
    DeleteSubjectUseCase deleteSubject
    @Shared
    TeacherServiceUseCase teacherService

    def setup() {
        loadSubject = Mock(LoadSubjectUseCase)
        findSubject = Mock(FindSubjectUseCase)
        updateSubject = Mock(UpdateSubjectUseCase)
        deleteSubject = Mock(DeleteSubjectUseCase)
        teacherService = Mock(TeacherServiceUseCase)
        userMapperInfrastructure = Mock(UserMapperInfrastructure)
        subjectMapperInfrastructure = Mock(SubjectMapperInfrastructure)
        subjectController = new SubjectController(subjectMapperInfrastructure,
                userMapperInfrastructure,
                loadSubject,
                findSubject,
                updateSubject,
                deleteSubject,
                teacherService)
    }

    def "load subject"() {
        given:
        List<GroupDTO> groupsDTO = new ArrayList<>()
        groupsDTO.add(GroupDTO.builder().name("grupo").build())

        SubjectDTO subjectDTO = SubjectDTO.builder().idSubject('2').name('a').groups(groupsDTO).build()
        when:
        ResponseEntity res = subjectController.loadSubject(subjectDTO)
        then:
        1 * loadSubject.loadSubject(_)
        res.getStatusCode() == HttpStatus.OK
    }

    def "subject load with exception"() {
        given:
        SubjectDTO subjectDTO = SubjectDTO.builder().idSubject('2').name('a').groups(new ArrayList<GroupDTO>()).build()
        subjectMapperInfrastructure.subjectDTOToSubject(subjectDTO) >> Subject.builder().build()
        when:
        ResponseEntity res = subjectController.loadSubject(subjectDTO)
        then:
        loadSubject.loadSubject(_ as Subject) >> { throw new Exception() }
        res.getStatusCode() == HttpStatus.BAD_REQUEST
    }

    def "get subject by id"() {
        given:
        String id = "2"
        SubjectDTO subjectDTO = SubjectDTO.builder()
                .idSubject(id)
                .groups(new ArrayList<GroupDTO>())
                .build()
        Subject subject = Subject.builder()
                .id_subject(id)
                .groups(new ArrayList<Group>()).build()
        findSubject.findSubjectById(id) >> subject
        subjectMapperInfrastructure.subjectToSubjectDTO(subject) >> subjectDTO
        when:
        ResponseEntity res = subjectController.getSubject(id)
        then:
        res.statusCode == HttpStatus.OK
        res.getBody() == subjectDTO
    }

    def "get subject by name and year"() {
        given:
        String name = "name"
        String year = "2019"
        SubjectDTO subjectDTO = SubjectDTO.builder().name(name).year(year).build()
        Subject subject = Subject.builder().name(name).year(year).build()
        findSubject.findSubjectByNameYear(name, year) >> subject
        subjectMapperInfrastructure.subjectToSubjectDTO(subject) >> subjectDTO
        when:
        ResponseEntity res = subjectController.getSubject(name, year)
        then:
        res.statusCode == HttpStatus.OK
        res.getBody() == subjectDTO
    }

    def "update subject"() {
        given:
        String name = "name"
        String year = "2019"
        SubjectDTO subjectDTO = SubjectDTO.builder().name(name).year(year).build()
        when:
        ResponseEntity res = subjectController.updateSubject(subjectDTO)
        then:
        res.statusCode == HttpStatus.OK
    }

    def "update subject with exception"() {
        given:
        String name = "name"
        String year = "2019"
        SubjectDTO subjectDTO = SubjectDTO.builder().name(name).year(year).build()
        Subject subject = Subject.builder().name(name).year(year).build()
        subjectMapperInfrastructure.subjectDTOToSubject(subjectDTO) >> subject
        updateSubject.updateSubject(subject) >> { throw new Exception() }
        when:
        ResponseEntity res = subjectController.updateSubject(subjectDTO)
        then:
        res.statusCode == HttpStatus.BAD_REQUEST
    }

    def "set teacher"() {
        given:
        String idSubject = "id"
        String ident = "ident"
        String header = "header"
        when:
        ResponseEntity res = subjectController.setTeacher(idSubject, ident, header)
        then:
        res.statusCode == HttpStatus.OK
    }

    def "set teacher with exception"() {
        given:
        String idSubject = "id"
        String ident = "ident"
        String header = "header"
        teacherService.setTeacher(idSubject, ident, header) >> { throw new Exception() }
        when:
        ResponseEntity res = subjectController.setTeacher(idSubject, ident, header)
        then:
        res.statusCode == HttpStatus.BAD_REQUEST
    }

    def "delete subject"() {
        given:
        String idSubject = "id"
        when:
        ResponseEntity res = subjectController.deleteSubject(idSubject)
        then:
        res.statusCode == HttpStatus.OK
    }

    def "delete subject with exception"() {
        given:
        String idSubject = "id"
        deleteSubject.deleteSubject(idSubject) >> { throw new Exception() }
        when:
        ResponseEntity res = subjectController.deleteSubject(idSubject)
        then:
        res.statusCode == HttpStatus.BAD_REQUEST
    }
}
