package co.edu.unicauca.asae.docentes.service;

import co.edu.unicauca.asae.docentes.entity.Docente;

import java.util.List;
import java.util.Optional;

public interface DocenteService {

    /**
     * @return All teachers
     */
    public List<Docente> findAll();

    /**
     * Save new techer
     * @return teacher saved
     */
    public Optional<Docente> createTeacher(Docente teacher);

    /**
     * Update teacher with teacher identification
     * @param teacherId teacher id to update
     * @param teacher new Data
     * @return
     */
    public Optional<Docente> updateTeacher(String teacherId, Docente teacher);

    /**
     * delete teacher by identification
     * @return deleted teacher, null if teacher was not found
     */
    public Optional<Docente> deleteTeacher(String teacherId);

}
