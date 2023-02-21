package ar.fusion.peluqueriacanina.logica;

import ar.fusion.peluqueriacanina.logica.Duenio;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-02-18T18:31:22", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Mascotas.class)
public class Mascotas_ { 

    public static volatile SingularAttribute<Mascotas, String> raza;
    public static volatile SingularAttribute<Mascotas, String> color;
    public static volatile SingularAttribute<Mascotas, String> alergico;
    public static volatile SingularAttribute<Mascotas, String> observaciones;
    public static volatile SingularAttribute<Mascotas, Duenio> unDuenio;
    public static volatile SingularAttribute<Mascotas, String> atencion_especial;
    public static volatile SingularAttribute<Mascotas, Integer> numCliente;
    public static volatile SingularAttribute<Mascotas, String> nombre;

}