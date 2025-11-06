package com.tuempresa.facturacion.modelo;

import java.time.*;
import java.util.Collection;
import javax.persistence.*;

import com.tuempresa.facturacion.calculadores.CalculadorSiguienteNumeroParaAnyo;
import org.hibernate.annotations.GenericGenerator;

import org.openxava.annotations.*;
import org.openxava.calculators.*;
import lombok.*;

//@@View y @ReferenceView para obtener una interfaz de usuario más compacta.
@Entity @Getter @Setter
//con esto cambio ui de anyo numero y fecha de enum a horizontal tipo abajo a derechaS
@View(members = "anyo,numero,fecha;"+"cliente;"+"detalles;"+"observaciones")
public class Factura {

    @Id
    @GeneratedValue(generator="system-uuid")
    @Hidden
    @GenericGenerator(name="system-uuid", strategy="uuid")
    @Column(length=32)
    String oid;

    @Column(length=4)
    @DefaultValueCalculator(CurrentYearCalculator.class)
    int anyo;

    @Column(length=6)
    @DefaultValueCalculator(value= CalculadorSiguienteNumeroParaAnyo.class, properties=@PropertyValue(name="anyo"))
    int numero;

    @Required
    @DefaultValueCalculator(CurrentLocalDateCalculator.class)
    LocalDate fecha;

    @TextArea
    String observaciones;

    @ManyToOne(fetch=FetchType.LAZY, optional=false) // El cliente es obligatorio
    @ReferenceView("Simple")
    Cliente cliente;

    @ElementCollection
    @ListProperties("producto.numero, producto.descripcion, cantidad")
    Collection<Detalle> detalles;

}
