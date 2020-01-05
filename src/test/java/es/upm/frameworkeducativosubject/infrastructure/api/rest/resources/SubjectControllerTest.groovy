package es.upm.frameworkeducativosubject.infrastructure.api.rest.resources

import es.upm.frameworkeducativosubject.infrastructure.api.rest.mapper.SubjectMapperInfrastructure
import es.upm.frameworkeducativosubject.infrastructure.api.rest.model.SubjectDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Shared
import spock.lang.Specification

class SubjectControllerTest extends Specification {
    @Shared
    SubjectMapperInfrastructure subjectMapperInfrastructure

    @Shared
    SubjectController subjectController

    def setup() {
        subjectMapperInfrastructure = Mock(SubjectMapperInfrastructure)
        subjectController = new SubjectController(subjectMapperInfrastructure)
    }

    def "load subject" () {
        given:
        SubjectDTO subjectDTO = SubjectDTO.builder().build()
        subjectMapperInfrastructure.subjectLoadAdapter(subjectDTO) >> new ResponseEntity(HttpStatus.OK)
        when:
        ResponseEntity res = subjectController.loadSubject(subjectDTO)
        then:
        res.statusCode == HttpStatus.OK
    }

    def "get subject by id" () {
        given:
        String id = "1"
        SubjectDTO subjectDTO = SubjectDTO.builder().idSubject(id).build()
        subjectMapperInfrastructure.getSubjectByIdAdapter(id) >> ResponseEntity.ok(subjectDTO)
        when:
        ResponseEntity res = subjectController.getSubject(id)
        then:
        res.statusCode == HttpStatus.OK
        res.getBody() == subjectDTO
    }

    def "get subject by name and year" () {
        given:
        String name = "name"
        String year = "2019"
        SubjectDTO subjectDTO = SubjectDTO.builder().name(name).year(year).build()
        subjectMapperInfrastructure.getSubjectByNameYear(name, year) >> ResponseEntity.ok(subjectDTO)
        when:
        ResponseEntity res = subjectController.getSubject(name, year)
        then:
        res.statusCode == HttpStatus.OK
        res.getBody() == subjectDTO
    }

    def "update subject" () {
        given:
        String name = "name"
        String year = "2019"
        SubjectDTO subjectDTO = SubjectDTO.builder().name(name).year(year).build()
        subjectMapperInfrastructure.updateSubjectAdapter(subjectDTO) >> new ResponseEntity(HttpStatus.OK)
        when:
        ResponseEntity res = subjectController.updateSubject(subjectDTO)
        then:
        res.statusCode == HttpStatus.OK
    }

    def "set teacher" () {
        given:
        String idSubject = "id"
        String ident = "ident"
        String header = "header"
        subjectMapperInfrastructure.setTeacher(idSubject, ident, header) >> new ResponseEntity(HttpStatus.OK)
        when:
        ResponseEntity res = subjectController.setTeacher(idSubject, ident, header)
        then:
        res.statusCode == HttpStatus.OK
    }

    def "delete subject" () {
        given:
        String idSubject = "id"
        subjectMapperInfrastructure.deleteSubjectById(idSubject) >> new ResponseEntity(HttpStatus.OK)
        when:
        ResponseEntity res = subjectController.deleteSubject(idSubject)
        then:
        res.statusCode == HttpStatus.OK
    }
}
