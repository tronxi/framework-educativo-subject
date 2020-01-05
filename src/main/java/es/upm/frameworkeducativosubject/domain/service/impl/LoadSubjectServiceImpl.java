package es.upm.frameworkeducativosubject.domain.service.impl;

import es.upm.frameworkeducativosubject.domain.model.Subject;
import es.upm.frameworkeducativosubject.domain.port.primary.LoadSubjectService;
import es.upm.frameworkeducativosubject.domain.port.secundary.ISubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoadSubjectServiceImpl implements LoadSubjectService {

    ISubjectRepository subjectRepository;

    @Autowired
    public LoadSubjectServiceImpl(ISubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public void loadSubject(Subject subject) throws Exception {
        try {
            subjectRepository.saveSubject(subject);
        } catch (Exception e) {
            throw new Exception();
        }
    }
}
