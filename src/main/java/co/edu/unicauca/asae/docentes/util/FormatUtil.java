package co.edu.unicauca.asae.docentes.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : Ruben
 **/
public class FormatUtil {

    /**
     * Convierte objeto de tipo BindingResult con errores de validación de un cuerpo de objeto a
     * mapa donde el nombre del campo es la llave y la descripcion del error es el valor.
     *
     * @param errorData Binding result con errores en los datos de una entidad
     * @return mapa con campo y error asociado
     */
    public static Map<String, String> getErrorMessages(BindingResult errorData) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : errorData.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return errors;
    }

}
