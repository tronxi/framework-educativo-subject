package es.upm.frameworkeducativosubject.domain.service;

import es.upm.frameworkeducativosubject.domain.port.primary.DeleteSubject;
import es.upm.frameworkeducativosubject.domain.port.secundary.GroupRepository;
import es.upm.frameworkeducativosubject.domain.port.secundary.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteSubjectUseCase implements DeleteSubject {

    private final SubjectRepository subjectRepository;

    private final GroupRepository groupRepository;

    @Override
    public void deleteSubject(String id) throws Exception{
        try {
            subjectRepository.deleteSubjectById(id);
            groupRepository.deleteGroupsBySubjectId(id);
        } catch (Exception e) {
            throw new Exception();
        }
    }
}
