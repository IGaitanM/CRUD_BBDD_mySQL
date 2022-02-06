package vista;

import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.negocio.ControladorCoche;

public class UsuarioConsola {

	public static void main(String[] args) {
		ControladorCoche cc = new ControladorCoche();

		try (Scanner sc = new Scanner(System.in)) {
			boolean continuar = true;
			String opcion;

			while (continuar) {

				System.out.println("-----MENÚ PARA LA GESTIÓN DE COCHES-----");
				System.out.println("_________________________________________");
				System.out.println("----> 1. Añadir nuevo coche \n" + "----> 2. Borrar coche por id. \n"
						+ "----> 3. Consultar coche por id. \n" + "----> 4. Modificar coche por id \n"
						+ "----> 5. Listado de coches \n" + "----> 6. Terminar el programa");

				opcion = sc.nextLine();

				switch (opcion) {
				case "1":
					Coche coche = new Coche();
					System.out.println("Añade la matrícula");
					coche.setMatricula(sc.nextLine());
					System.out.println("Añade el modelo");
					coche.setModelo(sc.nextLine());
					System.out.println("Añade el color");
					coche.setColor(sc.nextLine());
					int alta = cc.alta(coche);
					if (alta == 0) {
						System.out.println("Coche dado de alta");
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
					int id = Integer.parseInt(sc.nextLine());
					while (cc.consultarPorId(id) == null) {
						System.out.println("No hay ningun coche con esa id");
						System.out.println("prueba con otra id");
						id = Integer.parseInt(sc.nextLine());
					}

					System.out.println(cc.consultarPorId(id));

					break;
				case "4":
					System.out.println("¿Que ID de coche quieres modificar?");
					int id1 = Integer.parseInt(sc.nextLine());
					while (cc.consultarPorId(id1) == null) {
						System.out.println("No hay ningun coche con esa id");
						System.out.println("prueba con otra id");
						id1 = Integer.parseInt(sc.nextLine());
					}
					Coche coche1 = new Coche();
					coche1.setId(id1);
					System.out.println("Añade la matrícula");
					coche1.setMatricula(sc.nextLine());
					System.out.println("Añade el modelo");
					coche1.setModelo(sc.nextLine());
					System.out.println("Añade el color");
					coche1.setColor(sc.nextLine());

					if (!cc.modificarCoche(coche1))
						System.out.println("No se ha modificado por problema con la BBDD");
					else
						System.out.println("coche con id " +id1+ " modificado con éxito");

					break;
				case "5":
					// cc.listarCoches();
					break;
				case "6":

					continuar = false;
					break;
				default:
					System.out.println("Elige la opción escribiendo un número del menú, por favor \n");
				}
			}
		}

	}

}
