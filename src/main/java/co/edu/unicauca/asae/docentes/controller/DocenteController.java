package co.edu.unicauca.asae.docentes.controller;

import co.edu.unicauca.asae.docentes.entity.Docente;
import co.edu.unicauca.asae.docentes.service.DocenteService;

import javax.validation.Valid;

import co.edu.unicauca.asae.docentes.util.FormatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins =  {"http://localhost:4200"})
@RestController
@RequestMapping("/docentes")
public class DocenteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocenteController.class);

    @Autowired
    private DocenteService docenteService;

    @GetMapping("listar")
    public ResponseEntity<List<Docente>> findAll() {
        LOGGER.info("listando docentes...");
        final List<Docente> allTeachers = docenteService.findAll();

        if (allTeachers == null || allTeachers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(allTeachers);
    }

    @PostMapping("crear")
    public ResponseEntity<?> create(@Valid @RequestBody Docente teacher, final BindingResult result) {
        LOGGER.info("Creando docente con id {}...", teacher.getIdentificacion());

        // Errores en validación del formulario
        if (result.hasErrors()) {
            final String message = "Error al crear docente";
            return this.getFormErrorsResponse(message, result);
        }
        final Optional<Docente> optCreatedTeacher = docenteService.createTeacher(teacher);

        return optCreatedTeacher
                .map(this::getOkMessageResponseBody)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PutMapping("editar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String teacherId,
                                                      @Valid @RequestBody Docente teacher,
                                                      final BindingResult result) {

        LOGGER.info("Actualizando docente con id {}...", teacherId);

        // Errores en validación del formulario
        if (result.hasErrors()) {
            final String message = "Error al actualizar docente";
            return this.getFormErrorsResponse(message, result);
        }
        // Servicio de actualizar
        final Optional<Docente> optUpdatedTeacher = docenteService.updateTeacher(teacherId, teacher);

        return optUpdatedTeacher
                .map(this::getOkMessageResponseBody)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping(value = "eliminar/{id}")
    public ResponseEntity<Docente> delete(@PathVariable("id") String teacherId) {
        LOGGER.info("Eliminando docente con id {}...", teacherId);
        final Optional<Docente> optDeletedTeacher = docenteService.deleteTeacher(teacherId);

        /* OK si se pudo encontrar y eliminar el docente, 404 si no se pudo encontrar para eliminar */
        return optDeletedTeacher.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    private ResponseEntity<Map<String, Object>> getFormErrorsResponse(final String message,
                                                                      final BindingResult result) {
        final Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("message", message);
        errorResponse.put("errors", FormatUtil.getErrorMessages(result));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    private ResponseEntity<Docente> getOkMessageResponseBody(final Docente docente) {
        return ResponseEntity.status(HttpStatus.OK).body(docente);
    }

}
