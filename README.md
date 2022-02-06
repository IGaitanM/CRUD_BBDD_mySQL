## Indicaciones

Para este proyecto se ha usado una base de datos MySql, concretamente la que viene incluida en el all-in-one del [XAMPP](https://www.apachefriends.org/es/download.html)

El driver de conexión es "mysql-connector-java-8.0.22.jar", que esta en la carpeta "lib" y añadido al classpath del proyecto. 

- El usuario interactuará con la aplicación a través de la consola de la clase situada en  **src/vista/UsuarioConsola.java** .

### Query para crear la BBDD : 
- CREATE DATABASE CONECTORES

### Query para crear las tablas:
- **TABLA PASAJEROS** : CREATE TABLE PASAJEROS(ID INTEGER PRIMARY KEY AUTO_INCREMENT,   
				NOMBRE VARCHAR(20) NOT NULL,  
				EDAD INTEGER NOT NULL,  
				PESO DOUBLE NOT NULL,  
				ID_COCHE INTEGER,  
				FOREIGN KEY (ID_COCHE) REFERENCES COCHES(ID))
				
- **TABLA COCHES**  : CREATE TABLE COCHES(   
				ID INTEGER PRIMARY KEY AUTO_INCREMENT,  
				MATRICULA VARCHAR(7) NOT NULL,  
				MODELO VARCHAR(20) NOT NULL,   
				COLOR VARCHAR(15) NOT NULL) ;  
				
## Modelo de BBDD:

![BBDD conectores](https://user-images.githubusercontent.com/78205607/152699497-971ef2d7-fb5b-4746-8999-d23293e6ad1d.png)

## REQUERIMIENTOS DE FUNCIONAMIENTO:

- Se desea hacer un CRUD completo de la entidad Coche trabajando con una BBDD mySQL usando el patrón DAO .

 
- El menú mostrado será de la siguiente forma:

    Añadir nuevo coche (El ID lo incrementará automáticamente la base de datos)
    Borrar coche por ID
    Consulta coche por ID
    Modificar coche por ID
    Listado de coches
    Terminar el programa


## Puntos importantes de la aplicación:




