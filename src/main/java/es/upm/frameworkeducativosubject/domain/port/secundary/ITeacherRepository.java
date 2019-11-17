package es.upm.frameworkeducativosubject.domain.port.secundary;

public interface ITeacherRepository {

    public void setTeacher(String idSubject, String idTeacher) throws Exception;
    public void deleteTeacher(String idSubject, String idTeacher) throws Exception;
}
