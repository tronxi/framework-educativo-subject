package es.upm.frameworkeducativosubject.infrastructure.repository;

import es.upm.frameworkeducativosubject.domain.model.User;
import es.upm.frameworkeducativosubject.domain.port.secundary.UserRepository;
import es.upm.frameworkeducativosubject.infrastructure.repository.mappers.SubjectUserMapper;
import es.upm.frameworkeducativosubject.infrastructure.repository.model.SubjectUserEntity;
import es.upm.frameworkeducativosubject.infrastructure.repository.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static io.micrometer.shaded.org.pcollections.Empty.map;

@Repository
@RequiredArgsConstructor
public class TeacherRepositoryAdapter implements es.upm.frameworkeducativosubject.domain.port.secundary.TeacherRepository {

    private final SubjectUserMapper subjectUserMapper;
    private final UserRepository userRepository;

    @Override
    public void setTeacher(String idSubject, String idTeacher)  throws Exception{
        try {
            subjectUserMapper.setTeacher(idSubject, idTeacher);
        } catch (PersistenceException e) {
            throw new Exception("ex");
        }
    }

    @Override
    public void deleteTeacher(String idSubject, String idTeacher) throws Exception{
        try {
            subjectUserMapper.deleteTeacher(idSubject, idTeacher);
        } catch (PersistenceException e) {
            throw new Exception("ex");
        }
    }

    @Override
    public List<User> getTeachers(String idSubject, String header) throws Exception {
        try {
            List<SubjectUserEntity> subjectUser = subjectUserMapper.getTeachers(idSubject);
            return subjectUser.stream()
                    .map(user -> userRepository.getUserByIdUser(user.getIdTeacher(), header))
                    .collect(Collectors.toList());
        } catch (PersistenceException e) {
            throw new Exception("ex");
        }
    }


    private User toDomain(UserEntity userEntity) {
        return User.builder()
                .email(userEntity.getEmail())
                .id_user(userEntity.getId_user())
                .ident(userEntity.getIdent())
                .isChanged(userEntity.getIsChanged())
                .name(userEntity.getName())
                .roles(userEntity.getRoles())
                .surnames(userEntity.getSurnames())
                .build();
    }
}
