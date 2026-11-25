package Examen_2;

import java.util.Scanner;

//Manda una excepción en caso de que se cumplan las condiciones en "compruebaPromocion"
class ParkingGratisException extends Exception {
	public ParkingGratisException(String mensaje) {
		super(mensaje);
	}
}

public class ud3_examen {
	// Elimina todos los huecos innecesarios en ambos lados y convierte todo a
	// mayúsculas para una mejor inspección
	public static String FormateaMatricula(String matricula) {
		return matricula.trim().toUpperCase();
	}

	// Si la matrícula ya formateada coincide con el formato indicado devolverá true
	// (válido), de lo contrario false (inválido)
	public static Boolean ValidaMatricula(String matricula) {
		if (FormateaMatricula(matricula).matches("^\\d{4}[A-Z]{3}")) {
			return true;
		} else {
			return false;
		}
	}

	// Pilla cada número de la matrícula y los suma posteriormente
	public static Integer SumaDigitos(String matricula) {
		// Variables
		int n1;
		int n2;
		int n3;
		int n4;

		// Código
		n1 = Integer.parseInt(FormateaMatricula(matricula.substring(0, 1)));
		n2 = Integer.parseInt(FormateaMatricula(matricula.substring(1, 2)));
		n3 = Integer.parseInt(FormateaMatricula(matricula.substring(2, 3)));
		n4 = Integer.parseInt(FormateaMatricula(matricula.substring(3, 4)));
		Integer suma = n1 + n2 + n3 + n4;
		return suma;
	}


	/* public static Boolean EsCapicua(String matriucla) {
		 StringBuilder sb = new StringBuilder(); 
	 
		 if (sb.equals(matricula)) {
	 
	 }/*/
	 

	// Extrae la última letra de la matrícula, la pone al principio y añade la suma
	// de los números de la matrícula ya calculados anteriormente en el método
	// "sumaDigitos"
	public static String AsignaPlaza(String matricula) {
		// Variables
		String ultimaLetra = "";

		// Código
		ultimaLetra = FormateaMatricula(matricula).substring(6, 7);
		return ultimaLetra + SumaDigitos(matricula).toString();
	}

	// En caso de que la suma de los dígitos de la matrícula sea 20, o bien, la
	// matrícula contenga "DAW" se lanzará la excepción
	public static void CompruebaPromocion(String matricula) throws ParkingGratisException {
		if (SumaDigitos(matricula) == 20 || FormateaMatricula(matricula).substring(4, 7).matches("^DAW")) {
			throw new ParkingGratisException("Vehículo agraciado con aparcamiento gratuito.");
		} else {
			System.out.println("El vehículo no tiene aparcamiento gratuito.");
		}
	}

	//
	/*
	 * public static String GeneraCodigoAcceso(StringBuilder matricula) { 
	 * matricula = FormateaMatricula(matricula); }
	 */

	// Código principal (main)
	public static void main(String[] args) {
		// Variables
		final int PLAZAS = 2700;
		int plazasLibres = 0;
		String matricula;
		//Integer plazasActualmenteOcupadas;

		// Código
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca matrícula (o 'fin' para terminar):");
		matricula = sc.nextLine();
		while (!matricula.equals("fin") || plazasLibres != PLAZAS) {
			if (matricula.equals("fin")) {
				break;
			}
			if (ValidaMatricula(matricula)) {
				System.out.println("Plaza asignada: " + AsignaPlaza(matricula));
				System.out.println("Matrícula introducida: " + ValidaMatricula(matricula));
				System.out.println("Matrícula introducida formateada: " + FormateaMatricula(matricula));
				System.out.println("Plaza asignada: " + AsignaPlaza(matricula));
				try {
					CompruebaPromocion(matricula);
				} catch (ParkingGratisException e) {
					System.out.println(e.getMessage());
				}
			}	else {
					System.out.println("La matrícula introducida es inválida.");
				}
			System.out.println("\nIntroduzca matrícula (o 'fin' para terminar):");
			matricula = sc.nextLine();
		}
		sc.close();
	}
}
