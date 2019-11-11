package es.upm.frameworkeducativosubject.domain.service.impl;

import es.upm.frameworkeducativosubject.domain.port.primary.DeleteSubjectService;
import es.upm.frameworkeducativosubject.domain.port.secundary.IGroupRepository;
import es.upm.frameworkeducativosubject.domain.port.secundary.ISubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class DeleteSubjectServiceImpl implements DeleteSubjectService {

    @Autowired
    ISubjectRepository subjectRepository;

    @Autowired
    IGroupRepository groupRepository;

    @Override
    public HttpStatus deleteSubject(String id) {
        try {
            subjectRepository.deleteSubjectById(id);
            groupRepository.deleteGroupsBySubjectId(id);
        } catch (Exception e) {
            return  HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.OK;
    }
}
