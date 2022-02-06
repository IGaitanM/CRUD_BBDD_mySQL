## Indicaciones

Para este proyecto se ha usado una base de datos MySql, concretamente la que viene incluida en el all-in-one del [XAMPP](https://www.apachefriends.org/es/download.html)

El driver de conexi�n es "mysql-connector-java-8.0.22.jar", que esta en la carpeta "lib" y a�adido al classpath del proyecto. 

- El usuario interactuar� con la aplicaci�n a trav�s de la consola de la clase situada en  **src/vista/UsuarioConsola.java** .

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

- Se desea hacer un CRUD completo de la entidad Coche trabajando con una BBDD mySQL usando el patr�n DAO .

 
- El men� mostrado para la gesti�n de coches ser� ver� de la siguiente forma:


    1. A�adir nuevo coche (El ID lo incrementar� autom�ticamente la base de datos)
    2. Borrar coche por ID
    3. Consulta coche por ID
    4. Modificar coche por ID
    5. Listado de coches
    6. Men� para la gesti�n de PASAJEROS
    7. Terminar el programa
    
- Y el submen� para la gesti�n de pasajeros:


    1. A�adir nuevo pasajero
    2. Borrar pasajero por id.
    3. Consultar pasajero por id.
    4. Listar todos los pasajeros
    5. A�adir pasajero a coche
    6. Eliminar pasajero de un coche
    7. Listar los pasajeros de un coche
    8. volver al men� de gesti�n de COCHES
    9. Salir del programa

    

## Puntos importantes de la aplicaci�n:




