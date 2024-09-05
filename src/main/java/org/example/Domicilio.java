package org.example;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "domicilio")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Domicilio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreCalle;
    private int numero;

    //Si quisiera tener una relación bidireccional
//    @OneToOne(mappedBy = "domicilio")
//    private Cliente cliente;
}