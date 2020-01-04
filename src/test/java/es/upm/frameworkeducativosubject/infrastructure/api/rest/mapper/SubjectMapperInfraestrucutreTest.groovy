package es.upm.frameworkeducativosubject.infrastructure.api.rest.mapper

import es.upm.frameworkeducativosubject.domain.model.Group
import es.upm.frameworkeducativosubject.domain.model.Subject
import es.upm.frameworkeducativosubject.domain.port.primary.DeleteSubjectService
import es.upm.frameworkeducativosubject.domain.port.primary.FindSubjectService
import es.upm.frameworkeducativosubject.domain.port.primary.LoadSubjectService
import es.upm.frameworkeducativosubject.domain.port.primary.TeacherService
import es.upm.frameworkeducativosubject.domain.port.primary.UpdateSubjectService
import es.upm.frameworkeducativosubject.domain.service.impl.DeleteSubjectServiceImpl
import es.upm.frameworkeducativosubject.domain.service.impl.FindSubjectServiceImpl
import es.upm.frameworkeducativosubject.domain.service.impl.LoadSubjectServiceImpl
import es.upm.frameworkeducativosubject.domain.service.impl.TeacherServiceImpl
import es.upm.frameworkeducativosubject.domain.service.impl.UpdateSubjectServiceImpl
import es.upm.frameworkeducativosubject.infrastructure.api.rest.model.GroupDTO
import es.upm.frameworkeducativosubject.infrastructure.api.rest.model.SubjectDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Shared
import spock.lang.Specification

class SubjectMapperInfrastructureTest extends Specification {
    @Shared SubjectMapperInfrastructure subjectMapperInfrastructure
    @Shared LoadSubjectService loadSubjectService
    @Shared FindSubjectService findSubjectService
    @Shared UpdateSubjectService updateSubjectService
    @Shared DeleteSubjectService deleteSubjectService
    @Shared TeacherService teacherService

    def setup() {
        loadSubjectService = Mock(LoadSubjectServiceImpl)
        findSubjectService = Mock(FindSubjectServiceImpl)
        updateSubjectService = Mock(UpdateSubjectServiceImpl)
        deleteSubjectService = Mock(DeleteSubjectServiceImpl)
        teacherService = Mock(TeacherServiceImpl)
        subjectMapperInfrastructure =
                new SubjectMapperInfrastructure(loadSubjectService,
                        findSubjectService,
                        updateSubjectService,
                        deleteSubjectService,
                        teacherService)
    }

    def "subject load adapter" () {
        given:
            SubjectDTO subjectDTO = SubjectDTO.builder().idSubject('2').name('a').groups(new ArrayList<GroupDTO>()).build()
        when:
            ResponseEntity res = subjectMapperInfrastructure.subjectLoadAdapter(subjectDTO)
        then:
            1 * loadSubjectService.loadSubject(_)
            res.getStatusCode() == HttpStatus.OK
    }

    def "subject load adapter with exception" () {
        given:
        SubjectDTO subjectDTO = SubjectDTO.builder().idSubject('2').name('a').groups(new ArrayList<GroupDTO>()).build()
        when:
        ResponseEntity res = subjectMapperInfrastructure.subjectLoadAdapter(subjectDTO)
        then:
        loadSubjectService.loadSubject(_ as Subject) >> {throw new Exception()}
        res.getStatusCode() == HttpStatus.BAD_REQUEST
    }

    def "update load adapter" () {
        given:
        SubjectDTO subjectDTO = SubjectDTO.builder().idSubject('2').name('a').groups(new ArrayList<GroupDTO>()).build()
        when:
        ResponseEntity res = subjectMapperInfrastructure.updateSubjectAdapter(subjectDTO)
        then:
        1 * updateSubjectService.updateSubject(_)
        res.getStatusCode() == HttpStatus.OK
    }

    def "update load adapter with exception" () {
        given:
        SubjectDTO subjectDTO = SubjectDTO.builder().idSubject('2').name('a').groups(new ArrayList<GroupDTO>()).build()
        when:
        ResponseEntity res = subjectMapperInfrastructure.updateSubjectAdapter(subjectDTO)
        then:
        updateSubjectService.updateSubject(_ as Subject) >> {throw new Exception()}
        res.getStatusCode() == HttpStatus.BAD_REQUEST
    }

    def "get subject by id" () {
        given:
        String id = "2"
        SubjectDTO subjectDTO = SubjectDTO.builder().idSubject(id).groups(new ArrayList<GroupDTO>()).build()
        findSubjectService.findSubjectById(id) >> Subject.builder().id_subject(id).groups(new ArrayList<Group>()).build()
        when:
        ResponseEntity<SubjectDTO> res = subjectMapperInfrastructure.getSubjectByIdAdapter(id)
        then:
        res.getStatusCode() == HttpStatus.OK
        res.getBody() == subjectDTO
    }

    def "get subject by name year" () {
        given:
        String name = "poo"
        String year = "2019"
        SubjectDTO subjectDTO = SubjectDTO.builder()
                .name(name)
                .year(year)
                .groups(new ArrayList<GroupDTO>())
                .build()
        findSubjectService.findSubjectByNameYear(name, year) >> Subject.builder()
                .name(name)
                .year(year)
                .groups(new ArrayList<Group>())
                .build()
        when:
        ResponseEntity<SubjectDTO> res = subjectMapperInfrastructure.getSubjectByNameYear(name, year)
        then:
        res.getStatusCode() == HttpStatus.OK
        res.getBody() == subjectDTO
    }

    def "delete subject by id" () {
        given:
        String id = "2"
        when:
        ResponseEntity<SubjectDTO> res = subjectMapperInfrastructure.deleteSubjectById(id)
        then:
        res.getStatusCode() == HttpStatus.OK
    }

    def "delete subject by id with exception" () {
        given:
        String id = "2"
        when:
        ResponseEntity<SubjectDTO> res = subjectMapperInfrastructure.deleteSubjectById(id)
        then:
        deleteSubjectService.deleteSubject(_ as String) >> {throw new Exception()}
        res.getStatusCode() == HttpStatus.BAD_REQUEST
    }

    def "set teacher" () {
        given:
        String idSubject = "2"
        String ident = "ident"
        String header = "header"
        when:
        ResponseEntity res = subjectMapperInfrastructure.setTeacher(idSubject, ident, header)
        then:
        res.getStatusCode() == HttpStatus.OK
    }
}
