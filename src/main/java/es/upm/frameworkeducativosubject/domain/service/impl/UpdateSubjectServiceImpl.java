package es.upm.frameworkeducativosubject.domain.service.impl;

import es.upm.frameworkeducativosubject.domain.model.Subject;
import es.upm.frameworkeducativosubject.domain.port.primary.UpdateSubjectService;
import es.upm.frameworkeducativosubject.domain.port.secundary.ISubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UpdateSubjectServiceImpl implements UpdateSubjectService {

    @Autowired
    ISubjectRepository subjectRepository;

    @Override
    public HttpStatus updateSubject (Subject subject) {
        try {
            subjectRepository.updateSubject(subject);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
