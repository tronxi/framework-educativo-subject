package es.upm.frameworkeducativosubject.domain.service.impl;

import es.upm.frameworkeducativosubject.domain.model.Subject;
import es.upm.frameworkeducativosubject.domain.port.primary.LoadSubjectService;
import es.upm.frameworkeducativosubject.domain.port.secundary.ISubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoadSubjectServiceImpl implements LoadSubjectService {

    @Autowired
    ISubjectRepository subjectRepository;

    @Override
    public void loadSubject(Subject subject) {
        try {
            subjectRepository.saveSubject(subject);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
