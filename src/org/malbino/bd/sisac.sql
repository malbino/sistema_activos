
CREATE TABLE custodios (
                id_custodio INT AUTO_INCREMENT NOT NULL,
                nombre VARCHAR(1024) NOT NULL,
                observaciones VARCHAR(2048),
                PRIMARY KEY (id_custodio)
);


CREATE TABLE ubicaciones (
                id_ubicacion INT AUTO_INCREMENT NOT NULL,
                nombre VARCHAR(1024) NOT NULL,
                observaciones VARCHAR(2048),
                PRIMARY KEY (id_ubicacion)
);


CREATE TABLE categorias (
                id_categoria INT AUTO_INCREMENT NOT NULL,
                nombre VARCHAR(1024) NOT NULL,
                observaciones VARCHAR(2048),
                PRIMARY KEY (id_categoria)
);


CREATE TABLE activos (
                id_activo INT AUTO_INCREMENT NOT NULL,
                codigo VARCHAR(1024) NOT NULL,
                codigo_antiguo VARCHAR(1024) NOT NULL,
                descripcion VARCHAR(4096) NOT NULL,
                estado VARCHAR(1024) NOT NULL,
                observaciones VARCHAR(2048),
                foto VARCHAR(1024),
                id_categoria INT NOT NULL,
                id_ubicacion INT NOT NULL,
                id_custodio INT NOT NULL,
                PRIMARY KEY (id_activo)
);


ALTER TABLE activos ADD CONSTRAINT custodios_activos_fk
FOREIGN KEY (id_custodio)
REFERENCES custodios (id_custodio)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE activos ADD CONSTRAINT ubicaciones_activos_fk
FOREIGN KEY (id_ubicacion)
REFERENCES ubicaciones (id_ubicacion)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE activos ADD CONSTRAINT categorias_activos_fk
FOREIGN KEY (id_categoria)
REFERENCES categorias (id_categoria)
ON DELETE NO ACTION
ON UPDATE NO ACTION;
