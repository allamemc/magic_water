CREATE TABLE Usuario (
    NIF VARCHAR(255) PRIMARY KEY,
    categoria VARCHAR(255),
    nombre VARCHAR(255),
    apellidos VARCHAR(255),
    email VARCHAR(255),
    tlf VARCHAR(255),
    password VARCHAR(255),
    activo BOOLEAN,
    permiso VARCHAR(255)
);
CREATE TABLE Proyecto (
    idproyecto INT PRIMARY KEY,
    nombre VARCHAR(255),
    descripcion VARCHAR(255),
    zona VARCHAR(255),
    fecha VARCHAR(255)
);
CREATE TABLE Tarea (
    idtarea INT PRIMARY KEY,
    nif VARCHAR(255),
    idproyecto INT,
    titulo VARCHAR(255),
    descripcion VARCHAR(255),
    inicioPrevisto DATE,
    finPrevisto DATE,
    inicioReal DATE,
    finReal DATE,
    estado VARCHAR(255),
    FOREIGN KEY (nif) REFERENCES Usuario(NIF),
    FOREIGN KEY (idproyecto) REFERENCES Proyecto(idproyecto)
);

ALTER TABLE Tarea MODIFY idtarea INT AUTO_INCREMENT;
ALTER TABLE Proyecto MODIFY idproyecto INT AUTO_INCREMENT;
ALTER TABLE Proyecto MODIFY fecha DATE;
ALTER TABLE Tarea DROP FOREIGN KEY tarea_ibfk_2;
ALTER TABLE Proyecto MODIFY idproyecto INT AUTO_INCREMENT;
ALTER TABLE Tarea ADD CONSTRAINT tarea_ibfk_2 FOREIGN KEY (idproyecto) REFERENCES Proyecto(idproyecto);