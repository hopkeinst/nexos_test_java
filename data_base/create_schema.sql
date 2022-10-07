-- User postgres
-- Password postgres
-- Port 5432
-- Usar la base de datos por defecto: postgres

-- Crear el esquema que se usará para trabajar
CREATE SCHEMA nexos_inv_automotriz;

-- Tabla que almacenará los cargos de los empleados / usuarios del sistema de inventarios
CREATE TABLE nexos_inv_automotriz.user_cargos (
    id                  int     primary key not null,
    nombre              varchar(20)         not null,
    fecha_creacion      date                not null,
    id_user_crea        int                 not null,
    fecha_edicion       date,
    id_user_edita       int,
    fecha_eliminacion   date,
    id_user_elimina     int
);
-- Comentarios para dar información adicional de la tabla de cargos
COMMENT ON TABLE nexos_inv_automotriz.user_cargos IS 'Tabla que contiene los cargos de los usuarios / empleados que usan el sistema de inventario';
COMMENT ON COLUMN nexos_inv_automotriz.user_cargos.nombre IS 'Nombre del cargo del empleado';
COMMENT ON COLUMN nexos_inv_automotriz.user_cargos.fecha_creacion IS 'Fecha en que se crea registro del cargo en el sistema / base de datos';
COMMENT ON COLUMN nexos_inv_automotriz.user_cargos.id_user_crea IS 'ID del usuario que crea el registro del cargo en el sistema / base de datos';
COMMENT ON COLUMN nexos_inv_automotriz.user_cargos.fecha_edicion IS 'Fecha en que se edita el registro del cargo en el sistema / base de datos';
COMMENT ON COLUMN nexos_inv_automotriz.user_cargos.id_user_edita IS 'ID del usuario que edita el el registro del cargo en el sistema / base de datos';
COMMENT ON COLUMN nexos_inv_automotriz.user_cargos.fecha_eliminacion IS 'Fecha en que se da eliminar al cargo; esto para no eliminarlo realmente sino conservar una trazabilidad y estado de activo/inactivo';
COMMENT ON COLUMN nexos_inv_automotriz.user_cargos.id_user_elimina IS 'ID del usuario que elimina el cargo';

-- Tabla que almacenará los empleados / usuarios del sistema de inventarios
CREATE TABLE nexos_inv_automotriz.users (
    id                  int     primary key not null,
    nombre              varchar(100)        not null,
    fecha_nacimiento    date                not null,
    id_cargo            int                 not null,
    fecha_ingreso       date                not null,
    fecha_creacion      date                not null,
    id_user_crea        int                 not null,
    fecha_edicion       date,
    id_user_edita       int,
    fecha_eliminacion   date,
    id_user_elimina     int
);
-- Comentarios tabla usuarios
COMMENT ON TABLE nexos_inv_automotriz.users IS 'Tabla que contiene los usuarios / empleados que usan el sistema de inventario';
COMMENT ON COLUMN nexos_inv_automotriz.users.nombre IS 'Nombre del empleado';
COMMENT ON COLUMN nexos_inv_automotriz.users.fecha_nacimiento IS 'Fecha de nacimiento del empleado, para calcular su edad';
COMMENT ON COLUMN nexos_inv_automotriz.users.id_cargo IS 'ID del cargo que tiene el usuario';
COMMENT ON COLUMN nexos_inv_automotriz.users.fecha_ingreso IS 'Fecha en que ingresa el usuario a la empresa';
COMMENT ON COLUMN nexos_inv_automotriz.users.fecha_creacion IS 'Fecha en que se crea registro del usuario';
COMMENT ON COLUMN nexos_inv_automotriz.users.id_user_crea IS 'ID del usuario que crea el registro del usuario';
COMMENT ON COLUMN nexos_inv_automotriz.users.fecha_edicion IS 'Fecha en que se edita el registro del usuario';
COMMENT ON COLUMN nexos_inv_automotriz.users.id_user_edita IS 'ID del usuario que edita el registro del usuario';
COMMENT ON COLUMN nexos_inv_automotriz.users.fecha_eliminacion IS 'Fecha en que se da eliminar al usuario; esto para no eliminarlo realmente sino conservar una trazabilidad y estado de activo/inactivo';
COMMENT ON COLUMN nexos_inv_automotriz.users.id_user_elimina IS 'ID del usuario que elimina el usuario';

-- Tabla que almacenará los productos de la empresa
CREATE TABLE nexos_inv_automotriz.products (
    id                  int     primary key not null,
    nombre              varchar(50)         not null,
    cantidad            int                 not null,
    fecha_ingreso       date                not null,
    fecha_creacion      date                not null,
    id_user_crea        int                 not null,
    fecha_edicion       date,
    id_user_edita       int,
    fecha_eliminacion   date,
    id_user_elimina     int
);
-- Comentarios tabla productos
COMMENT ON TABLE nexos_inv_automotriz.products IS 'Tabla que contiene los productos en el sistema de inventario';
COMMENT ON COLUMN nexos_inv_automotriz.products.nombre IS 'Nombre del producto';
COMMENT ON COLUMN nexos_inv_automotriz.products.cantidad IS 'Cantidad de productos actualmente';
COMMENT ON COLUMN nexos_inv_automotriz.products.fecha_ingreso IS 'Fecha en que ingresa el producto a la empresa';
COMMENT ON COLUMN nexos_inv_automotriz.products.fecha_creacion IS 'Fecha en que se crea registro del producto en el sistema / base de datos';
COMMENT ON COLUMN nexos_inv_automotriz.products.id_user_crea IS 'ID del usuario que crea el registro del producto en el sistema / base de datos';
COMMENT ON COLUMN nexos_inv_automotriz.products.fecha_edicion IS 'Fecha en que se edita el registro del producto en el sistema / base de datos';
COMMENT ON COLUMN nexos_inv_automotriz.products.id_user_edita IS 'ID del usuario que edita el registro del producto en el sistema / base de datos';
COMMENT ON COLUMN nexos_inv_automotriz.products.fecha_eliminacion IS 'Fecha en que se da eliminar al producto; esto para no eliminarlo realmente sino conservar una trazabilidad y estado de activo/inactivo';
COMMENT ON COLUMN nexos_inv_automotriz.products.id_user_elimina IS 'ID del usuario que elimina el producto';

-- Creación de las relaciones
-- Relación de usuarios con sus cargos
ALTER TABLE nexos_inv_automotriz.users
ADD CONSTRAINT fk_user_cargo
FOREIGN KEY(id_cargo)
REFERENCES nexos_inv_automotriz.user_cargos(id);

-- Relación de usuarios con el usuario que lo crea
ALTER TABLE nexos_inv_automotriz.users
ADD CONSTRAINT fk_user_user_crea
FOREIGN KEY(id_user_crea)
REFERENCES nexos_inv_automotriz.users(id);

-- Relación de usuarios con el usuario que lo edita
ALTER TABLE nexos_inv_automotriz.users
ADD CONSTRAINT fk_user_user_edita
FOREIGN KEY(id_user_edita)
REFERENCES nexos_inv_automotriz.users(id);

-- Relación de usuarios con el usuario que lo elimina
ALTER TABLE nexos_inv_automotriz.users
ADD CONSTRAINT fk_user_user_elimina
FOREIGN KEY(id_user_elimina)
REFERENCES nexos_inv_automotriz.users(id);

-- Relación de usuarios con el usuario que lo crea
ALTER TABLE nexos_inv_automotriz.user_cargos
ADD CONSTRAINT fk_cargo_user_crea
FOREIGN KEY(id_user_crea)
REFERENCES nexos_inv_automotriz.users(id);

-- Relación de usuarios con el usuario que lo edita
ALTER TABLE nexos_inv_automotriz.user_cargos
ADD CONSTRAINT fk_cargo_user_edita
FOREIGN KEY(id_user_edita)
REFERENCES nexos_inv_automotriz.users(id);

-- Relación de usuarios con el usuario que lo elimina
ALTER TABLE nexos_inv_automotriz.user_cargos
ADD CONSTRAINT fk_cargo_user_elimina
FOREIGN KEY(id_user_elimina)
REFERENCES nexos_inv_automotriz.users(id);

-- Relación de productos con el usuario que lo crea
ALTER TABLE nexos_inv_automotriz.products
ADD CONSTRAINT fk_prod_user_crea
FOREIGN KEY(id_user_crea)
REFERENCES nexos_inv_automotriz.users(id);

-- Relación de usuarios con el usuario que lo edita
ALTER TABLE nexos_inv_automotriz.products
ADD CONSTRAINT fk_prod_user_edita
FOREIGN KEY(id_user_edita)
REFERENCES nexos_inv_automotriz.users(id);

-- Relación de usuarios con el usuario que lo elimina
ALTER TABLE nexos_inv_automotriz.products
ADD CONSTRAINT fk_prod_user_elimina
FOREIGN KEY(id_user_elimina)
REFERENCES nexos_inv_automotriz.users(id);
