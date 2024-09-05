package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            // Iniciar una transacción
            entityManager.getTransaction().begin();

            // Crear nuevas categorías
            Categoria categoriaTecnologia = Categoria.builder().denominacion("Tecnología").build();
            Categoria categoriaHogar = Categoria.builder().denominacion("Hogar").build();
            Categoria categoriaJuguetes = Categoria.builder().denominacion("Juguetes").build();
            Categoria categoriaBelleza = Categoria.builder().denominacion("Belleza").build();

            // Crear nuevos artículos
            Articulo articulo1 = Articulo.builder().denominacion("Laptop").cantidad(20).precio(120000).build();
            Articulo articulo2 = Articulo.builder().denominacion("Aspiradora").cantidad(15).precio(30000).build();
            Articulo articulo3 = Articulo.builder().denominacion("Muñeca Barbie").cantidad(50).precio(8000).build();
            Articulo articulo4 = Articulo.builder().denominacion("Maquillaje").cantidad(100).precio(5000).build();
            Articulo articulo5 = Articulo.builder().denominacion("Televisor LED").cantidad(10).precio(95000).build();

            // Asociar categorías con artículos
            articulo1.getCategorias().add(categoriaTecnologia);
            articulo2.getCategorias().add(categoriaHogar);
            articulo3.getCategorias().add(categoriaJuguetes);
            articulo4.getCategorias().add(categoriaBelleza);
            articulo5.getCategorias().add(categoriaTecnologia);
            articulo5.getCategorias().add(categoriaHogar);

            // Añadir artículos a las categorías
            categoriaTecnologia.getArticulos().add(articulo1);
            categoriaTecnologia.getArticulos().add(articulo5);
            categoriaHogar.getArticulos().add(articulo2);
            categoriaHogar.getArticulos().add(articulo5);
            categoriaJuguetes.getArticulos().add(articulo3);
            categoriaBelleza.getArticulos().add(articulo4);

            // Crear detalles de factura
            DetalleFactura detalle1 = DetalleFactura.builder().cantidad(2).subtotal(240000).articulo(articulo1).build();
            DetalleFactura detalle2 = DetalleFactura.builder().cantidad(1).subtotal(30000).articulo(articulo2).build();
            DetalleFactura detalle3 = DetalleFactura.builder().cantidad(3).subtotal(24000).articulo(articulo3).build();
            DetalleFactura detalle4 = DetalleFactura.builder().cantidad(5).subtotal(25000).articulo(articulo4).build();
            DetalleFactura detalle5 = DetalleFactura.builder().cantidad(1).subtotal(95000).articulo(articulo5).build();

            // Crear una nueva factura
            Factura factura1 = Factura.builder().numero(25).fecha("05/09/2024").build();
            factura1.getDetalles().add(detalle1);
            factura1.getDetalles().add(detalle2);
            factura1.getDetalles().add(detalle3);
            factura1.getDetalles().add(detalle4);
            factura1.getDetalles().add(detalle5);

            // Calcular el total de la factura
            factura1.setTotal(detalle1.getSubtotal() + detalle2.getSubtotal() + detalle3.getSubtotal() + detalle4.getSubtotal() + detalle5.getSubtotal());

            // Crear un nuevo cliente y domicilio
            Domicilio domicilio1 = Domicilio.builder().nombreCalle("San Martín").numero(123).build();
            Cliente cliente1 = Cliente.builder().nombre("Carlos").apellido("Gutiérrez").dni(40123456).domicilio(domicilio1).build();

            // Asociar cliente con factura
            factura1.setCliente(cliente1);

            // Persistir la factura (esto también persistirá el cliente y los detalles debido a las relaciones)
            entityManager.persist(factura1);

            // Limpiar la conexión
            entityManager.flush();

            // Terminar de persistir el objeto en la BD
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            // Manejar errores
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println("No se pudo grabar.");
        }

        // Cerrar el EntityManager y el EntityManagerFactory
        entityManager.close();
        entityManagerFactory.close();
    }
}
