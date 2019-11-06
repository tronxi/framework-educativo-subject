package es.upm.frameworkeducativosubject.domain.service.impl;

import es.upm.frameworkeducativosubject.domain.model.Subject;
import es.upm.frameworkeducativosubject.domain.port.primary.FindSubjectService;
import es.upm.frameworkeducativosubject.domain.port.secundary.ISubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindSubjectServiceImpl implements FindSubjectService {

    @Autowired
    ISubjectRepository subjectRepository;

    @Override
    public Subject findSubjectById(String id) {
        return subjectRepository.getSubjectById(id);
    }
}
