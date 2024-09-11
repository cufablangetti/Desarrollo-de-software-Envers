package org.example;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "articulo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Audited

public class Articulo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cantidad;
    private String denominacion;
    private int precio;

    //Si quisieramos bidireccional
//    @OneToMany(mappedBy = "articulo", cascade = CascadeType.PERSIST)
//    @Builder.Default
//    private Set<DetalleFactura> detalles = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "articulo_categoria",
            joinColumns = @JoinColumn(name = "articulo_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    @Builder.Default
    private Set<Categoria> categorias = new HashSet<>();
}