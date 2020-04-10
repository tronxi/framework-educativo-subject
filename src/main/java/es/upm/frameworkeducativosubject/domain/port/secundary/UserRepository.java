package es.upm.frameworkeducativosubject.domain.port.secundary;

import es.upm.frameworkeducativosubject.domain.model.User;

import java.util.List;

public interface UserRepository {
    User getUserByIdent(String ident, String header);
    List<User> getUserListByIdUser(List<String> idUserList, String header);
}
