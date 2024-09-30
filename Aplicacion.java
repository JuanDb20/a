import java.util.Scanner;

/**
 * Clase principal que gestiona la aplicación de Interacción de Rutas Ecológicas
 * y la Gestión de Lugares Biodiversos para la COP 16 en Cali, Colombia.
 * 
 * Esta aplicación permite al usuario registrar y consultar lugares biodiversos,
 * así como gestionar rutas ecológicas. Los lugares registrados están limitados a un máximo de 30.
 * 
 * @author Juan Diego Balanta Molina A00407538
 */
public class Aplicacion {

    private static final int MAX_LUGARES = 30; // Limitar a 30 lugares
    private static String[] nombresLugares = new String[MAX_LUGARES];
    private static String[] departamentos = new String[MAX_LUGARES];
    private static int[] areas = new int[MAX_LUGARES];
    private static int contadorLugares = 0;

    /**
     * Método principal que ejecuta la aplicación.
     * 
     * El método presenta un menú principal al usuario y ejecuta las opciones
     * correspondientes basadas en la selección del usuario. Las opciones incluyen
     * iniciar la gestión de rutas o la gestión de lugares biodiversos, o salir de la aplicación.
     * 
     * @param args 
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenuPrincipal();
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    iniciarRutas(scanner);
                    break;
                case 2:
                    gestionarLugaresBiodiversos(scanner);
                    break;
                case 3:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 3);

        scanner.close();
    }

    /**
     * Muestra el menú principal de la aplicación.
     */
    private static void mostrarMenuPrincipal() {
        System.out.println("# Bienvenido a la aplicación principal.");
        System.out.println("# Te presentamos las siguientes opciones, ingresa:");
        System.out.println("# 1. Para la aplicación de Interacción de Rutas Ecológicas COP 16");
        System.out.println("# 2. Para la aplicación de Gestión de Lugares Biodiversos COP 16");
        System.out.println("# 3. Para salir del programa.");
    }

    /**
     * Gestiona la interacción de rutas ecológicas.
     * 
     * Solicita al usuario su nombre, cédula y detalles de la ruta ecológica seleccionada,
     * calcula el total de personas involucradas y muestra la cantidad de buses necesarios.
     * También verifica las condiciones meteorológicas.
     * 
     * @param scanner Scanner para leer la entrada del usuario.
     */
    private static void iniciarRutas(Scanner scanner) {
        System.out.println("# Bienvenido voluntario a la aplicación de Interacción de Rutas Ecológicas COP 16 Cali - Colombia. ¿Cuál es tu nombre?");
        String nombre = scanner.nextLine();

        System.out.println("# Por favor, digita tu cédula.");
        String cedula = scanner.nextLine();

        System.out.println("# ¡Bienvenido, " + nombre + "!");

        String[] detallesRuta = seleccionarRuta(scanner);
        mostrarDetallesRuta(detallesRuta);

        int totalPersonas = calcularTotalPersonas(scanner);

        solicitarYVerificarCondicionesMeteorologicas(scanner);

        calcularYMostrarBuses(totalPersonas);
    }

    /**
     * Selecciona la ruta ecológica y devuelve los detalles de la misma.
     * 
     * @param scanner El Scanner para leer la entrada del usuario.
     * @return Un arreglo de String con el punto de encuentro, hora de inicio y hora de fin.
     */
    private static String[] seleccionarRuta(Scanner scanner) {
        System.out.println("# ¿Qué ruta registrarás el día de hoy?");
        System.out.println("1. Ruta de los Farallones");
        System.out.println("2. Ruta del Oriente");
        System.out.println("3. Ruta de Ladera");
        int ruta = scanner.nextInt();
        scanner.nextLine();

        String puntoEncuentro = "";
        String horaInicio = "";
        String horaFin = "";

        switch (ruta) {
            case 1:
                puntoEncuentro = "Calle 16 - Universidad del Valle";
                horaInicio = "6:40 am";
                horaFin = "4:00 pm";
                break;
            case 2:
                puntoEncuentro = "Bulevar del Río";
                horaInicio = "7:00 am";
                horaFin = "1:00 pm";
                break;
            case 3:
                puntoEncuentro = "Bulevar del Río";
                horaInicio = "7:00 am";
                horaFin = "1:30 pm";
                break;
            default:
                System.out.println("# Opción de ruta inválida.");
                return new String[]{"", "", ""};
        }

        return new String[]{puntoEncuentro, horaInicio, horaFin};
    }

    /**
     * Muestra los detalles de la ruta seleccionada.
     * 
     * @param detallesRuta Un arreglo de String con el punto de encuentro, hora de inicio y hora de fin.
     */
    private static void mostrarDetallesRuta(String[] detallesRuta) {
        System.out.println("# La Ruta seleccionada tiene como punto de encuentro " + detallesRuta[0]
                + ", iniciando a las " + detallesRuta[1] + ", y termina a las " + detallesRuta[2] + ".");
    }

    /**
     * Calcula el total de personas involucradas en la ruta.
     * 
     * @param scanner El Scanner para leer la entrada del usuario.
     * @return El total de personas que incluyen participantes y guías.
     */
    private static int calcularTotalPersonas(Scanner scanner) {
        System.out.println("# ¿Cuántos participantes acudirán a la caminata el día de hoy?");
        int participantes = scanner.nextInt();
        System.out.println("# ¿Cuántos guías acudirán a la caminata el día de hoy?");
        int guias = scanner.nextInt();
        return participantes + guias;
    }

    /**
     * Solicita y verifica las condiciones meteorológicas para la caminata.
     * 
     * @param scanner Scanner para leer la entrada del usuario.
     */
    private static void solicitarYVerificarCondicionesMeteorologicas(Scanner scanner) {
        System.out.println("# Ingresar la temperatura en grados centígrados °C");
        float temperatura = scanner.nextFloat();
        
        System.out.println("# Ingresar el porcentaje de humedad relativa");
        float humedad = scanner.nextFloat();

        if (temperatura >= 20 && temperatura <= 25 && humedad >= 40 && humedad <= 60) {
            System.out.println("# ¡Hace un buen día para caminar por Cali!");
        }
    }

    /**
     * Calcula y muestra la cantidad de buses necesarios para la caminata.
     * 
     * @param totalPersonas El total de personas que participarán en la caminata.
     */
    private static void calcularYMostrarBuses(int totalPersonas) {
        int busesNecesarios = (totalPersonas + 24) / 25;
        System.out.println("# Al ser un total de " + totalPersonas
                + " personas que harán parte de la actividad, se necesitarán un total de: " + busesNecesarios
                + " buses para llevarla a cabo de manera exitosa. ¡Nos vemos en la COP16!");
    }

    /**
     * Gestiona la aplicación de lugares biodiversos.
     * 
     * Presenta un menú al usuario para registrar lugares o consultar el departamento con más lugares registrados.
     * También se encarga de validar la cantidad de lugares para no superar el límite de 30.
     * 
     * @param scanner Scanner para leer la entrada del usuario.
     */
    private static void gestionarLugaresBiodiversos(Scanner scanner) {
        int opcion;

        do {
            mostrarMenuGestionLugares();
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarLugar(scanner);
                    break;
                case 2:
                    consultarDepartamentoConMasLugares();
                    break;
                case 3:
                    System.out.println("Regresando al menú principal.");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 3);
    }

    /**
     * Muestra el menú de gestión de lugares biodiversos.
     */
    private static void mostrarMenuGestionLugares() {
        System.out.println("# Bienvenido a la aplicación de Gestión de Lugares Biodiversos COP 16 Cali - Colombia.");
        System.out.println("# Te presentamos las siguientes opciones, ingresa:");
        System.out.println("# 1. Para registrar un lugar con diversidad biológica");
        System.out.println("# 2. Para consultar el departamento que tiene más lugares con diversidad biológica registrados hasta el momento.");
        System.out.println("# 3. Para salir al menú principal.");
    }

    /**
     * Registra un nuevo lugar biodiverso.
     * 
     * Solicita al usuario el nombre, departamento y área del lugar. Si ya se han registrado 30 lugares,
     * no permite agregar más.
     * 
     * @param scanner Scanner para leer la entrada del usuario.
     */
    private static void registrarLugar(Scanner scanner) {
        if (contadorLugares >= MAX_LUGARES) {
            System.out.println("# Se ha alcanzado el límite de 30 lugares registrados.");
            return;
        }

        System.out.println("# Ingresa el nombre del lugar biodiverso:");
        nombresLugares[contadorLugares] = scanner.nextLine();

        System.out.println("# Ingresa el departamento del lugar:");
        departamentos[contadorLugares] = scanner.nextLine();

        System.out.println("# Ingresa el área del lugar (en hectáreas):");
        areas[contadorLugares] = scanner.nextInt();
        scanner.nextLine(); 

        contadorLugares++;
        System.out.println("# Lugar registrado exitosamente.");
    }

    /**
     * Consulta el departamento que tiene más lugares biodiversos registrados.
     * 
     * Recorre el arreglo de departamentos para contar la cantidad de lugares en cada uno y determina
     * cuál departamento tiene más lugares registrados.
     */
    private static void consultarDepartamentoConMasLugares() {
        if (contadorLugares == 0) {
            System.out.println("# No se han registrado lugares.");
            return;
        }

        String[] departamentosUnicos = new String[MAX_LUGARES];
        int[] conteoDepartamentos = new int[MAX_LUGARES];
        int numDepartamentos = 0;

        // Contar lugares por departamento
        for (int i = 0; i < contadorLugares; i++) {
            String deptoActual = departamentos[i];
            boolean encontrado = false;

            for (int j = 0; j < numDepartamentos; j++) {
                if (departamentosUnicos[j].equals(deptoActual)) {
                    conteoDepartamentos[j]++;
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                departamentosUnicos[numDepartamentos] = deptoActual;
                conteoDepartamentos[numDepartamentos] = 1;
                numDepartamentos++;
            }
        }

        // Encontrar el departamento con más lugares
        int maxLugares = 0;
        String deptoMaxLugares = "";

        for (int i = 0; i < numDepartamentos; i++) {
            if (conteoDepartamentos[i] > maxLugares) {
                maxLugares = conteoDepartamentos[i];
                deptoMaxLugares = departamentosUnicos[i];
            }
        }

        System.out.println("# El departamento con más lugares biodiversos registrados es: " + deptoMaxLugares + " con " + maxLugares + " lugares.");
    }
}
