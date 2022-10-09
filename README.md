# Prueba de Conocimiento Desarrollador Java

Este repostorio contiene el desarrollo de la prueba técnica de conocimiento para desarrollador Java de la empresa Nexos.

El repositorio contiene:

- [Presentación de la prueba](#prueba)
- [Base de datos](#base-datos)
- [Desarrollo Backend](#backend)
- [Desarrollo Frontend](#frontend)
- [Evidencias del desarrollo final](#evidencias)

<hr>

### Presentación de la prueba {#prueba}

La prueba técnica consiste en la siguiente propuesta:

La empresa Nexos Software requiere desarrollar un sistema de inventario para el sector automotriz donde se controle la mercancía que ingresa y la que sale. El sistema debe permitir registrar nueva mercancía, editar y eliminar.

Para registrar mercancía nueva se requiere tener en cuenta los siguientes datos: Nombre producto, cantidad, fecha de ingreso, usuario que realiza registro. Restricciones: no puede haber mas de una mercancía con el mismo nombre, la cantidad debe ser un número entero, la fecha de ingreso debe ser menor o igual a la fecha actual.

Para editar mercancía se deben tener las mismas condiciones de cuando se registra una nueva, aparte hay que registrar el usuario que hace la modificación y la fecha.

Para eliminar mercancía, solo lo puede hacer el usuario que la registró.

El sistema también debe permitir mostrar por pantalla la mercancía registrada, los filtros de búsqueda pueden ser por fecha, usuario y/o nombre (se debe buscar mínimo por un filtro).

Los usuarios que pueden ejecutar las acciones deben estar registrados con su nombre, edad, cargo y fecha de ingreso a la compañía.

Los posibles cargos son, Asesor de ventas, administrador y soporte, con la posibilidad de que se creen nuevos cargos a futuro.

Nota: No es necesario contar con un login para saber que usuario está realizando las acciones, en cada acción se puede elegir el usuario (previamente creados en BD) en un menú desplegable.

Para el desarrollo del BackEnd se deben usar las siguientes tecnologías:
- Java 8 o superior
- Spring framework, Spring data, Spring web
- BD PostgreSQL
- Maven o Gradle

Para el desarrollo FrontEnd:

- Angular

Se evaluarán los siguientes conceptos:
- Buenas prácticas de programación
- Manejo de excepciones (fundamental)
- Pruebas de unidad (fundamental)
- Manejo de DTO’S y entidades
- Manejo de API’S y controladores
- Estructuración de paquetes y capas
- Inyección de dependencias
- Normalización de base de datos


### Base de datos {#base-datos}

Como lo menciona la propuesta de la prueba técnica, es necesario que la base de datos esté en __PostgreSQL__ se realiza en esta.

A de tenerse en cuenta, que para estandarizar o facilitar la replicación de todo el desarrollo, se usa la base de datos predeterminada en __PostgreSQL__ que es `postgres`, creando un esquema sobre el cual se trabaja _(crean tablas, relaciones, insertan datos iniciales)_.

Igualmente, los datos de acceso son los predeterminados:
- Usuario: `postgres`
- Contraseña: `postgres`
- Puerto: `5432`

Toda esta información se encuentra en el script [create_schema.sql](/data_base/create_schema.sql) en los comentarios, así como lo necesario para correrlo.

También, en la carpeta [data_base](/data_base) se puede encontrar el diagrama relacional de las tablas usadas.

[![Diagrama relacional](/data_base/postgres%20-%20nexos_inv_automotriz.png)](https://github.com/hopkeinst/nexos_test_java/blob/main/data_base/postgres%20-%20nexos_inv_automotriz.png)


### Desarrollo Backend {#backend}


### Desarrollo Frontend {#frontend}


### Evidencias del Desarrollo {#evidencias}

