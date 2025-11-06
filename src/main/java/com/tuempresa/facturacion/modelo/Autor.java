package com.tuempresa.facturacion.modelo;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import org.hibernate.annotations.*;
import org.openxava.annotations.*;

import java.util.Collection;

@Entity @Getter @Setter
public class Autor {
    @Id @GeneratedValue(generator = "system-uuid")
    @Hidden
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(length = 32)
    String oid;

    @Column(length = 50)
    String nombre;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.REMOVE)
    @ListProperties("numero,descripcion,precio")
    Collection<Producto> productos;
}
