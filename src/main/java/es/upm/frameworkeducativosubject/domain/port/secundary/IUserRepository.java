package es.upm.frameworkeducativosubject.domain.port.secundary;

import es.upm.frameworkeducativosubject.domain.model.User;

public interface IUserRepository {
    public User getUserByIdent(String ident);
}
