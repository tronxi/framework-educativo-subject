package es.upm.frameworkeducativosubject.infrastructure.repository;

import es.upm.frameworkeducativosubject.domain.model.User;
import es.upm.frameworkeducativosubject.infrastructure.repository.mappers.UserMapper;
import es.upm.frameworkeducativosubject.infrastructure.repository.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements es.upm.frameworkeducativosubject.domain.port.secundary.UserRepository {

    private final UserMapper userMapper;

    @Override
    public User getUserByIdent(String ident, String header) {
        return userDAOtoUser(userMapper.getUserByIdent(ident, header).getBody());
    }

    private User userDAOtoUser(UserEntity userEntity) {
        return User.builder()
                .email(userEntity.getEmail())
                .id_user(userEntity.getId_user())
                .ident(userEntity.getIdent())
                .isChanged(userEntity.getIsChanged())
                .name(userEntity.getName())
                .password(userEntity.getName())
                .surnames(userEntity.getSurnames())
                .roles(userEntity.getRoles()).build();
    }
}
