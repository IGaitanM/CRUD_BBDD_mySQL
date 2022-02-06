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

				System.out.println("-----MEN� PARA LA GESTI�N DE COCHES-----");
				System.out.println("_________________________________________");
				System.out.println("----> 1. A�adir nuevo coche \n" + "----> 2. Borrar coche por id. \n"
						+ "----> 3. Consultar coche por id. \n" + "----> 4. Modificar coche por id \n"
						+ "----> 5. Listado de coches \n" + "----> 6. Terminar el programa");

				opcion = sc.nextLine();

				switch (opcion) {
				case "1":
					Coche coche = new Coche();
					System.out.println("A�ade la matr�cula");
					coche.setMatricula(sc.nextLine());
					System.out.println("A�ade el modelo");
					coche.setModelo(sc.nextLine());
					System.out.println("A�ade el color");
					coche.setColor(sc.nextLine());
					int alta = cc.alta(coche);
						if (alta == 0) {
							System.out.println("Coche dado de alta");
						} else if (alta == 1) {
							System.out.println("Error de conexi�n con la BBDD");
						} else if (alta == 2) {
							System.out.println("No se ha dado de alta por matr�cula repetida");
						}
					break;
				case "2":
					System.out.println("�Que ID quieres borrar?");
					int id = sc.nextInt();
					sc.nextLine();
					cc.BorrarPorId(id);
					break;
				case "3":
					System.out.println("�Que ID quieres consultar?");
					int id1 = sc.nextInt();
					sc.nextLine();
					// cc.consultaPorId(id1);
					break;
				case "4":

					break;
				case "5":
					// cc.listarCoches();
					break;
				case "6":

					continuar = false;
					break;
				default:
					System.out.println("Elige la opci�n escribiendo un n�mero del men�, por favor \n");
				}
			}
		}

	}

}
