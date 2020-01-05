package es.upm.frameworkeducativosubject.infrastructure.repository;

import es.upm.frameworkeducativosubject.domain.model.User;
import es.upm.frameworkeducativosubject.domain.port.secundary.IUserRepository;
import es.upm.frameworkeducativosubject.infrastructure.repository.mappers.UserMapper;
import es.upm.frameworkeducativosubject.infrastructure.repository.model.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements IUserRepository {

    private UserMapper userMapper;

    @Autowired
    public UserRepository(UserMapper userMapper) {
        this.userMapper = userMapper;

    }

    @Override
    public User getUserByIdent(String ident, String header) {
        return userDAOtoUser(userMapper.getUserByIdent(ident, header).getBody());
    }

    private User userDAOtoUser(UserDAO userDAO) {
        return User.builder()
                .email(userDAO.getEmail())
                .id_user(userDAO.getId_user())
                .ident(userDAO.getIdent())
                .isChanged(userDAO.getIsChanged())
                .name(userDAO.getName())
                .password(userDAO.getName())
                .surnames(userDAO.getSurnames())
                .roles(userDAO.getRoles()).build();
    }
}
