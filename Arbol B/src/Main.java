import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("===========================================");
        System.out.println("   SISTEMA DE GESTIÓN DE ÁRBOL B+ (Java)");
        System.out.println("===========================================");

        int orden = 0;
        boolean ordenValido = false;


        while (!ordenValido) {
            System.out.print("Ingrese el orden del Árbol B+: ");
            if (sc.hasNextInt()) {
                orden = sc.nextInt();
                if (orden >= 3) {
                    ordenValido = true;
                } else {
                    System.out.println("El orden debe ser mayor o igual a 3.");
                }
            } else {
                System.out.println("Error: debe ingresar un número entero.");
                sc.next(); // limpiar buffer
            }
        }

        ArbolBMas arbol = new ArbolBMas(orden);
        int opcion = -1; // <-- inicializar para evitar error de compilación

        do {
            System.out.println("\n========= MENÚ PRINCIPAL =========");
            System.out.println("1. Insertar clave");
            System.out.println("2. Buscar clave");
            System.out.println("3. Eliminar clave");
            System.out.println("4. Recorrer n elementos");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");


            if (!sc.hasNextInt()) {
                System.out.println("Opción inválida. Ingrese un número entre 1 y 5.");
                sc.next();
                continue;
            }

            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese clave a insertar: ");
                    if (sc.hasNextInt()) {
                        int clave = sc.nextInt();
                        arbol.insertar(clave);
                        System.out.println("Clave insertada correctamente.");
                    } else {
                        System.out.println("Error: debe ingresar un número entero.");
                        sc.next();
                    }
                }

                case 2 -> {
                    System.out.print("Ingrese clave a buscar: ");
                    if (sc.hasNextInt()) {
                        int clave = sc.nextInt();
                        boolean encontrada = arbol.buscar(clave);
                        if (encontrada) {
                            System.out.println("Clave encontrada en el árbol.");
                        } else {
                            System.out.println("Clave no encontrada.");
                        }
                    } else {
                        System.out.println("Error: debe ingresar un número entero.");
                        sc.next();
                    }
                }

                case 3 -> {
                    System.out.print("Ingrese clave a eliminar: ");
                    if (sc.hasNextInt()) {
                        int clave = sc.nextInt();
                        arbol.eliminar(clave);
                        System.out.println("Clave eliminada (si existía).");
                    } else {
                        System.out.println("Error: debe ingresar un número entero.");
                        sc.next();
                    }
                }

                case 4 -> {
                    System.out.print("Ingrese cantidad de elementos a recorrer: ");
                    if (sc.hasNextInt()) {
                        int n = sc.nextInt();
                        System.out.println("Recorrido de los primeros " + n + " elementos:");
                        arbol.recorrer(n);
                    } else {
                        System.out.println("Error: debe ingresar un número entero.");
                        sc.next();
                    }
                }

                case 5 -> System.out.println("Saliendo del programa...");

                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }

        } while (opcion != 5);

        sc.close();
    }
}
