package co.edu.unicauca.asae.docentes.service;

import co.edu.unicauca.asae.docentes.entity.Docente;
import co.edu.unicauca.asae.docentes.repository.DocenteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DocenteServiceImpl implements DocenteService{

    private static final Logger LOGGER = LoggerFactory.getLogger(DocenteServiceImpl.class);

    @Autowired
    private DocenteRepository docenteRepository;


    @Override
    public List<Docente> findAll() {
        try {
            LOGGER.info("Listando todos los docentes");
            return docenteRepository.findAll();
        } catch (Exception ex) {
            LOGGER.error("Error al listar docentes: {}", ex.getMessage());
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Docente> createTeacher(Docente teacher) {
        try {
            LOGGER.info("Creando docente {}", teacher.getNombres());
            return Optional.of(docenteRepository.save(teacher));
        } catch (Exception ex) {
            LOGGER.error("Error al crear docente: {}", ex.getMessage());
            ex.printStackTrace();
            return Optional.empty();
        }

    }

    @Override
    public Optional<Docente> updateTeacher(String teacherId, Docente teacher) {
        try {
            LOGGER.info("Actualizando docente {}", teacher.getNombres());

            Optional<Docente> optFoundTeacher = this.docenteRepository.findById(teacher.getIdentificacion());
            optFoundTeacher.ifPresent(docente -> docenteRepository.save(teacher));
            return optFoundTeacher;
        } catch (Exception ex) {
            LOGGER.error("Error al actualizar docente: {}", ex.getMessage());
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Docente> deleteTeacher(String teacherId) {
        try {
            LOGGER.info("Eliminando docente {}", teacherId);

            Optional<Docente> optFoundteacher = docenteRepository.findById(teacherId);
            optFoundteacher.ifPresent(teacher -> docenteRepository.delete(teacher));
            return optFoundteacher;
        } catch (Exception ex) {
            LOGGER.error("Error al eliminar docente: {}", ex.getMessage());
            ex.printStackTrace();
            return Optional.empty();
        }
    }
}
