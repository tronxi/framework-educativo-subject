package es.upm.frameworkeducativosubject.domain.service.impl;

import es.upm.frameworkeducativosubject.domain.model.Subject;
import es.upm.frameworkeducativosubject.domain.port.primary.LoadSubjectService;
import es.upm.frameworkeducativosubject.domain.port.secundary.ISubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LoadSubjectServiceImpl implements LoadSubjectService {

    @Autowired
    ISubjectRepository subjectRepository;

    @Override
    public HttpStatus loadSubject(Subject subject) {
        try {
            subjectRepository.saveSubject(subject);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
