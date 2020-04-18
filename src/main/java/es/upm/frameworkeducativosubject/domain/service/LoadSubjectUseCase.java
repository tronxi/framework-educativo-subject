package es.upm.frameworkeducativosubject.domain.service;

import es.upm.frameworkeducativosubject.domain.model.Subject;
import es.upm.frameworkeducativosubject.domain.port.primary.LoadSubject;
import es.upm.frameworkeducativosubject.domain.port.secundary.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoadSubjectUseCase implements LoadSubject {

    private final SubjectRepository subjectRepository;

    @Override
    public void loadSubject(Subject subject) throws Exception {
        try {
            subjectRepository.saveSubject(subject);
        } catch (Exception e) {
            throw new Exception();
        }
    }
}
