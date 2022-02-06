package vista;

import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.negocio.ControladorCoche;
import modelo.negocio.ControladorPasajero;

public class UsuarioConsola {

	public static void main(String[] args) {

		menuPrincipal();

	}

	public static void menuPrincipal() {
		ControladorCoche cc = new ControladorCoche();
		Coche coche;
		int id;
		try (Scanner sc = new Scanner(System.in)) {
			boolean continuar = true;
			String opcion;

			while (continuar) {

				System.out.println("\n-----MENÚ PARA LA GESTIÓN DE COCHES-----");
				System.out.println("_________________________________________");
				System.out.println("----> 1. Añadir nuevo coche \n" + "----> 2. Borrar coche por id. \n"
						+ "----> 3. Consultar coche por id. \n" + "----> 4. Modificar coche por id \n"
						+ "----> 5. Listado de coches \n" + "----> 6. Menú gestión de PASAJEROS \n"
						+ "----> 7. Terminar el programa");

				opcion = sc.nextLine();

				switch (opcion) {
				case "1":
					coche = new Coche();
					System.out.println("Añade la matrícula");
					coche.setMatricula(sc.nextLine());
					System.out.println("Añade el modelo");
					coche.setModelo(sc.nextLine());
					System.out.println("Añade el color");
					coche.setColor(sc.nextLine());
					int alta = cc.alta(coche);
					if (alta == 0) {
						System.out.println("Pasajero dado de alta");
					} else if (alta == 1) {
						System.out.println("Error de conexión con la BBDD");
					} else if (alta == 2) {
						System.out.println("No se ha dado de alta por matrícula repetida");
					}
					break;
				case "2":
					System.out.println("¿Que ID quieres borrar?");
					cc.borrarPorId(Integer.parseInt(sc.nextLine()));
					break;
				case "3":
					System.out.println("¿Que ID de coche quieres consultar?");
					id = Integer.parseInt(sc.nextLine());
					while (cc.consultarPorId(id) == null) {
						System.out.println("No hay ningun coche con esa id");
						System.out.println("prueba con otra id");
						id = Integer.parseInt(sc.nextLine());
					}

					System.out.println(cc.consultarPorId(id));

					break;
				case "4":
					System.out.println("¿Que ID de coche quieres modificar?");
					id = Integer.parseInt(sc.nextLine());
					while (cc.consultarPorId(id) == null) {
						System.out.println("No hay ningun coche con esa id");
						System.out.println("prueba con otra id");
						id = Integer.parseInt(sc.nextLine());
					}
					coche = new Coche();
					coche.setId(id);
					System.out.println("Añade la matrícula");
					coche.setMatricula(sc.nextLine());
					System.out.println("Añade el modelo");
					coche.setModelo(sc.nextLine());
					System.out.println("Añade el color");
					coche.setColor(sc.nextLine());

					if (!cc.modificarCoche(coche))
						System.out.println("Error de conexión con la BBDD");
					else
						System.out.println("coche con id " + id + " modificado con éxito");

					break;
				case "5":
					System.out.println(cc.listar());
					break;
				case "6":

					submenuPasajeros();
					break;
				case "7":

					continuar = false;
					break;
				default:
					System.out.println("Elige la opción escribiendo un número del menú, por favor \n");
				}
			}
		}
	}

	public static void submenuPasajeros() {
		ControladorPasajero cp = new ControladorPasajero();
		Pasajero pasajero;
		int id;
		try (Scanner sc = new Scanner(System.in)) {
			boolean continuar = true;
			String opcion;

			while (continuar) {

				System.out.println("\n-----MENÚ PARA LA GESTIÓN DE PASAJEROS-----");
				System.out.println("_________________________________________");
				System.out.println("----> 1. Añadir nuevo pasajero \n" + "----> 2. Borrar pasajero por id. \n"
						+ "----> 3. Consultar pasajero por id. \n" + "----> 4. Listar todos los pasajeros \n"
						+ "----> 5. Añadir pasajero a coche \n" + "----> 6. Eliminar pasajero de un coche \n"
						+ "----> 7. Listar los pasajeros de un coche \n"
						+ "----> 8. volver al menú de gestión de COCHES \n" + "----> 9. Salir del programa");
				opcion = sc.nextLine();

				switch (opcion) {
				case "1":
					pasajero = new Pasajero();
					System.out.println("Introduzca nombre");
					pasajero.setNombre(sc.nextLine());
					System.out.println("Introduzca Edad");
					pasajero.setEdad(Integer.parseInt(sc.nextLine()));
					System.out.println("Introzca Peso");
					pasajero.setPeso(Double.parseDouble(sc.nextLine()));
					int alta = cp.alta(pasajero);
					if (alta == 0)
						System.out.println("Pasajero dado de alta");
					else
						System.out.println("Error de conexión con la BBDD");

					break;
				case "2":
					System.out.println("¿Que ID de pasajero quieres borrar?");
					id = Integer.parseInt(sc.nextLine());
					cp.borrarPorId(id);

					break;
				case "3":
					System.out.println("¿Que ID de pasajero quieres consultar?");
					id = Integer.parseInt(sc.nextLine());
					while (cp.consultarPorId(id) == null) {
						System.out.println("No hay ningun pasajero con esa id");
						System.out.println("prueba con otra id");
						id = Integer.parseInt(sc.nextLine());
					}

					System.out.println(cp.consultarPorId(id));

					break;

				case "4":

					break;
				case "5":

					break;
				case "6":

					break;
				case "7":

					break;
				case "8":
					menuPrincipal();

					break;
				case "9":

					continuar = false;
					break;
				default:
					System.out.println("Elige la opción escribiendo un número del menú, por favor \n");
				}
			}
		}
	}

}
