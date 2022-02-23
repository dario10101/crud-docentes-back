package co.edu.unicauca.asae.docentes.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Entity
@Table(name = "docentes")
@Data @AllArgsConstructor @NoArgsConstructor
@Builder
@ToString
public class Docente {

    @Id
    @Column(name = "identificacion")
    @Size(min = 5, max = 15, message="{docente.field.size}")
    @NotEmpty(message = "{docente.field.empty}")
    private String identificacion;

    @Column(name = "tipo_identificacion")
    @NotEmpty(message = "{docente.field.empty}")
    private String tipoIdentificacion;

    @NotNull
    @Column(name = "nombres")
    @Size(min = 2, max = 100, message="{docente.field.size}")
    @NotEmpty(message = "{docente.field.empty}")
    private String nombres;

    @Column(name = "apellidos")
    @NotEmpty(message = "{docente.field.empty}")
    @Size(min = 2, max = 100, message="{docente.field.size}")
    private String apellidos;

    @Column(name = "genero")
    @NotEmpty(message = "{docente.field.empty}")
    private String genero;

    @Pattern(regexp = "[3][0-9]{9}", message="{docente.telefono.pattern}")
    @Column(name = "telefono")
    private String telefono;

    @Column(name = "correo")
    @NotEmpty(message = "{docente.field.empty}")
    @Email(message = "{docente.correo.email}")
    private String correo;

    @Column(name = "titulo")
    @NotEmpty(message = "{docente.field.empty}")
    private String titulo;

    @Column(name = "abreviatura_titulo")
    @NotEmpty(message = "{docente.field.empty}")
    private String abreviaturaTitulo;

    @Column(name = "universidad_titulo")
    @NotEmpty(message = "{docente.field.empty}")
    private String universidadTitulo;

    @Column(name = "categoria_minciencias")
    private String categoriaMinciencias;

    @Column(name = "linkcvlac")
    private String linkCvLac;

    @Column(name = "facultad")
    @NotEmpty(message = "{docente.field.empty}")
    private String facultad;

    @Column(name = "deparmamento")
    @NotEmpty(message = "{docente.field.empty}")
    private String deparmamento;

    @Column(name = "grupo_investigacion")
    private String grupoInvestigacion;

    @Column(name = "linea_investigacion")
    private String lineaInvestigacion;

    @Column(name = "tipo_vinculacion")
    @NotEmpty(message = "{docente.field.empty}")
    private String tipoVinculacion;

    @Column(name = "escalafon")
    private String escalafon;

    @Column(name = "observacion")
    @Size(max = 250, message = "docente.field.size")
    private String observacion;

}
