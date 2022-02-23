package co.edu.unicauca.asae.docentes.repository;

import co.edu.unicauca.asae.docentes.entity.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, String> {

}
