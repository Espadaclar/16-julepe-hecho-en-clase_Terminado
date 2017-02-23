import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase Juego que simula el juego del Julepe.
 * 
 * @author Miguel Bayon
 */
public class Juego
{
    private Jugador[] jugadores;
    private Mazo mazo;
    private Palo paloQuePinta;
    private static final int NUMERO_DE_RONDAS = 5;

    private Palo paloPrimeraCartaDeLaBaza;// palo de la carta que 1º se juega en cada baza.
    //private Carta carT; // carta que tira el jugador en jugar2()********************

    /**
     * Constructor de la clase Juego
     *
     * @param numeroJugadores El número de jugadores que van a jugar
     * @param nombreJugadorHumano El nombre del jugador humano
     */
    public Juego(int numeroJugadores, String nombreJugadorHumano)
    {
        mazo = new Mazo();
        jugadores = new Jugador[numeroJugadores];

        ArrayList<String> posiblesNombres = new ArrayList<String>();
        posiblesNombres.add("Pepe");
        posiblesNombres.add("Maria");
        posiblesNombres.add("Juan");
        posiblesNombres.add("Luis");
        posiblesNombres.add("Marcos");
        posiblesNombres.add("Omar"); 
        posiblesNombres.add("Carlos");
        posiblesNombres.add("Azahara");  

        Jugador jugadorHumano = new Jugador(nombreJugadorHumano);
        jugadores[0] = jugadorHumano;
        System.out.println("Bienvenido a esta partida de julepe, " + nombreJugadorHumano);

        Random aleatorio = new Random();
        for (int i = 1; i < numeroJugadores; i++) {
            int posicionNombreElegido = aleatorio.nextInt(posiblesNombres.size());
            String nombreAleatorioElegido = posiblesNombres.get(posicionNombreElegido);
            posiblesNombres.remove(posicionNombreElegido);

            Jugador jugador = new Jugador(nombreAleatorioElegido);
            jugadores[i] = jugador;

        }

        System.out.println("Tus rivales son: ");
        for (int i = 1; i < jugadores.length; i++) {
            System.out.println(jugadores[i].getNombre());
        }
        System.out.println();

        jugar2();

        paloPrimeraCartaDeLaBaza = null;// palo de la carta que 1º se juega.***************************
        // carT = null;// carta que tira el jugador en jugar2()****************************
    }

    /**
     * Método que reparte 5 cartas a cada uno de los jugadores presentes en
     * la partida y elige un palo para que pinte.
     *
     * @return El palo que pinta tras repartir
     */
    private Palo repartir() 
    {
        mazo.barajar();

        Carta nuevaCarta = null;
        for (int cartaARepartir = 0; cartaARepartir < 5; cartaARepartir++) {            
            for (int jugadorActual = 0; jugadorActual < jugadores.length; jugadorActual++) {
                nuevaCarta = mazo.sacarCarta();
                jugadores[jugadorActual].recibirCarta(nuevaCarta);
            }
        }

        paloQuePinta = nuevaCarta.getPalo();
        switch (paloQuePinta) {
            case OROS:
            System.out.println("Pintan oros");
            break;
            case COPAS:
            System.out.println("Pintan copas");
            break;
            case ESPADAS:
            System.out.println("Pintan espadas");
            break;
            case BASTOS:
            System.out.println("Pintan bastos");
            break;
        }

        return paloQuePinta;           
    }

    /**
     * Devuelve la posición del jugador cuyo nombre se especifica como
     * parámetro.
     *
     * @param nombre El nombre del jugador a buscar
     * @return La posición del jugador buscado o -1 en caso de no hallarlo.
     */
    private int encontrarPosicionJugadorPorNombre(String nombre)
    {
        int posicionEncontrada = -1;

        int jugadorActual = 0;
        while (jugadorActual < jugadores.length && posicionEncontrada == -1) {
            if (jugadores[jugadorActual].getNombre().equals(nombre)) {
                posicionEncontrada = jugadorActual;
            }            
            jugadorActual++;
        } 

        return posicionEncontrada;
    }

    /**
     * Desarrolla una partida de julepe teniendo en cuenta que el mazo y los
     * jugadores ya han sido creados. 
     * 
     * La partida se desarrolla conforme a las normas del julepe con la
     * excepción de que es el usuario humano quien lanza cada vez la primera
     * carta, independientemente de qué usuario haya ganado la baza anterior y,
     * además, los jugadores no humanos no siguen ningún criterio a la hora
     * de lanzar sus cartas, haciéndolo de manera aleatoria.
     * 
     * En concreto, el método de se encarga de:
     * 1. Repartir las cartas a los jugadores.
     * 2. Solicitar por teclado la carta que quiere lanzar el jugador humano.
     * 3. Lanzar una carta por cada jugador no humano.
     * 4. Darle la baza al jugador que la ha ganado.
     * 5. Informar de qué jugador ha ganado la baza.
     * 6. Repetir el proceso desde el punto 2 hasta que los jugadores hayan
     *    tirado todas sus cartas.
     * 7. Informar de cuántas bazas ha ganado el jugador humano.
     * 8. Indicar si el jugador humano "es julepe" (ha ganado menos de dos
     *    bazas) o "no es julepe".
     *
     */
    
    private void jugar2()
    {
        // 1 Repartir las cartas a los jugadores.
        repartir();

        String nameJugadorGanadorDeLaBaza = "";
        String nameJugadorHumano = jugadores[0].getNombre();
        int bazasGanadasDelHumano = 0;
        int numCartas = 0;
        // Para poder comprobar si juegan las cartas correctamente se muestra las cartas de los jugadores noH.
        for(int i = 1; i < jugadores.length; i ++){
            System.out.println(" \ncartas de " +jugadores[i].getNombre());
            jugadores[i].verCartasJugadorConPrint();
        }
        int cont = 0;
        while(cont < NUMERO_DE_RONDAS){

            System.out.println("");
            System.out.println("\n ============== Cartas de.- " +nameJugadorHumano);

            Carta carT = null; // carta que tira el jugador humano. ********************
            while(carT == null){

                jugadores[0].verCartasJugador(); //  muestro en pantalla las cartas que tiene.
                // 2 Solicitar por teclado la carta que quiere lanzar el jugador humano.
                Scanner entrada = new Scanner(System.in);
                System.out.println("---------------------- " +nameJugadorHumano+ ",Teclee el nombre de la carta que juega.");
                String nameCarta = entrada.nextLine();

                // Para poder comprobar si juegan las cartas correctamente se muestra las cartas de los jugadores noH.
                for(int i = 1; i < jugadores.length; i ++){
                    System.out.println(" \n   [ cartas de " +jugadores[i].getNombre()+ " ]");
                    jugadores[i].verCartasJugadorConPrint();
                }

                carT = jugadores[0].tirarCarta(nameCarta);
                if (carT == null) {
                    System.out.println("ErrorRRR  NO TENEMOS ESA CARTA!!");
                }
                System.out.println("");
            }

            System.out.println("************************************ CARTAS DE LA BAZA Nº.- " +(cont +1));
            //********
            // 4 Darle la baza al jugador que la ha ganado.
            Baza bazaGanada = new Baza(jugadores.length, paloQuePinta);
            bazaGanada.addCarta(carT, nameJugadorHumano);
            if(bazaGanada.getPaloPrimeraCartaDeLaBaza() != null){
                paloPrimeraCartaDeLaBaza = bazaGanada.getPaloPrimeraCartaDeLaBaza();//// palo de la carta que 1º se juega en cada baza.
            }
            // 3 Lanzar una carta por cada jugador no humano.

            if(carT != null){
                for(int i = 1; i < jugadores.length; i ++){
                    Jugador jugadorActual = jugadores[i];

                    Carta cartaTiradaBot = jugadorActual.tirarCartaInteligentemente(bazaGanada.getPaloPrimeraCartaDeLaBaza(),
                            bazaGanada.cartaQueVaGanandoLaBaza(), paloQuePinta);
                    bazaGanada.addCarta(cartaTiradaBot, jugadorActual.getNombre());

                    paloPrimeraCartaDeLaBaza = bazaGanada.getPaloPrimeraCartaDeLaBaza();
                }
            }

            // 4 Darle la baza al jugador que la ha ganado.
            jugadores[encontrarPosicionJugadorPorNombre(bazaGanada.nombreJugadorQueVaGanandoLaBaza())].addBaza(bazaGanada);
            // 5. Informar de quÃ© jugador ha ganado la baza.
            nameJugadorGanadorDeLaBaza = jugadores[encontrarPosicionJugadorPorNombre(bazaGanada.nombreJugadorQueVaGanandoLaBaza())].getNombre();
            System.out.println("*************************************** BAZA GANADA POR.- " +nameJugadorGanadorDeLaBaza);
            System.out.println("");
            cont ++;
        }
        // 7. Informar de cuÃ¡ntas bazas ha ganado el jugador humano.
        bazasGanadasDelHumano = jugadores[0].getNumeroBazasGanadas();

        System.out.println( nameJugadorHumano+ " tiene.- " +bazasGanadasDelHumano+ " bazas ganadas. ");
        if(bazasGanadasDelHumano >= 2){
            System.out.println(nameJugadorHumano+ " no ha sido julepe por tener " +bazasGanadasDelHumano+ " bazas ganadas. ");
        }
        else{
            System.out.println(nameJugadorHumano+ " si ha sido julepe por tener " +bazasGanadasDelHumano+ " bazas ganadas. ");
        }
    }
}

