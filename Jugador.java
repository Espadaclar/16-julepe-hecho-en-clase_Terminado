import java.util.Random;
import java.util.ArrayList;
/**
 * Write a description of class Jugador here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Jugador
{
    private String nombre;
    private Carta[] cartasQueTieneEnLaMano;
    private int numeroCartasEnLaMano;
    private ArrayList<Baza> bazasGanadas;

    // private Carta cartaQueVamosAJugar;//almacena la carta que va ganando en el mt jugarCartaInteligentemente()

    /**
     * Constructor for objects of class Jugador
     */
    public Jugador(String nombreJugador)
    {
        nombre = nombreJugador;
        cartasQueTieneEnLaMano = new Carta[5];
        numeroCartasEnLaMano = 0;   
        bazasGanadas = new ArrayList<Baza>();

        // cartaQueVamosAJugar = null;
    }

    /**
     * Metodo que hace que el jugador reciba una carta
     */
    public void recibirCarta(Carta cartaRecibida)
    {
        if (numeroCartasEnLaMano < 5) {
            cartasQueTieneEnLaMano[numeroCartasEnLaMano] = cartaRecibida;
            numeroCartasEnLaMano++;
        }
    }

    /**
     * Metodo que muestra las cartas del jugador por pantalla
     */
    public void verCartasJugador()
    {
        for (Carta cartaActual : cartasQueTieneEnLaMano) {
            if (cartaActual != null) {
                System.out.println(cartaActual);
            }
        }
    }

    /**
     * Metodo que devuelve el nombre del jugador
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Metodo que devuelve la carta especificada como parametro si
     * el jugador dispone de ella y simula que se lanza a la mesa
     */    
    public Carta tirarCarta(String nombreCarta)
    {
        Carta cartaTirada = null;

        if (numeroCartasEnLaMano > 0) {

            int cartaActual = 0;
            boolean buscando = true;
            while (cartaActual < cartasQueTieneEnLaMano.length && buscando) {
                if (cartasQueTieneEnLaMano[cartaActual] != null) {                                 
                    if (nombreCarta.equals(cartasQueTieneEnLaMano[cartaActual].toString())) {
                        buscando = false;
                        cartaTirada = cartasQueTieneEnLaMano[cartaActual];
                        cartasQueTieneEnLaMano[cartaActual] = null;
                        numeroCartasEnLaMano--;
                        System.out.println(nombre + " ha tirado " + cartaTirada);
                    }
                }
                cartaActual++;
            }

        }
        return cartaTirada;
    }

    /**
     * MÃ©todo que tira una carta aleatoria 
     */
    public Carta tirarCartaAleatoria() 
    {
        Carta cartaTirada = null;        
        if (numeroCartasEnLaMano > 0) {
            Random aleatorio = new Random();            
            boolean elJugadorHaTiradoUnaCarta = false;
            while (elJugadorHaTiradoUnaCarta == false) {
                int posicionAleatoria = aleatorio.nextInt(5);
                if (cartasQueTieneEnLaMano[posicionAleatoria] != null) {
                    cartaTirada = cartasQueTieneEnLaMano[posicionAleatoria];
                    cartasQueTieneEnLaMano[posicionAleatoria] = null;
                    numeroCartasEnLaMano--;
                    System.out.println(nombre + " ha tirado " + cartaTirada);
                    elJugadorHaTiradoUnaCarta = true;
                }
            }            
        }        
        return cartaTirada;
    }

    //////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    /**
     * MÃ©todo que tira una carta "inteligentemente" ************************************************
     * 
     * *********************************************************************************************************************
     */
    public Carta tirarCartaInteligentemente(Palo paloPrimeraCartaDeLaBaza, Carta cartaQueVaGanando, Palo paloQuePinta)                                    
    {
        //la carta que empieza ganando la baza es la 1º en jugarse. la que se pasa en el parámetro.(la juega el humano.)
        // guardo su palo y su valor en dos VL.
        int valorPrimerCarta = cartaQueVaGanando.getValor();
        Palo paloPrimerCarta = cartaQueVaGanando.getPalo();
        Carta cartaQueVamosAJugar = null;//almacena la carta que va ganando.
        //Cartas que tiene el jugador en la mano. 
        int cont = 0;
        boolean encontrado = false;
        int auxiliar = 0; // ----- si cambia el valor a 777 es porque la carta que va ganando ya no es la pasada por parámetro.
        while(cont < cartasQueTieneEnLaMano.length && !encontrado){//recorre la colección de cartas para jugar una que gane si puede ser.
            
            if(cartasQueTieneEnLaMano[cont] != null){
                if(cartaQueVaGanando != null && cartasQueTieneEnLaMano[cont].ganaA(cartaQueVaGanando, paloQuePinta)){
                    cartaQueVamosAJugar = cartasQueTieneEnLaMano[cont];
                    System.out.println(" cartas de.-===================== " +nombre );//+ " " +cartasQueTieneEnLaMano[i]
                    for(int i = 0; i < 5; i ++){
                        System.out.println(cartasQueTieneEnLaMano[i]);
                    } 
                    System.out.println("=============================" +nombre+ " ha jugados.- " +cartaQueVamosAJugar.toString() );
                    System.out.println("");
                    encontrado = true;

                    auxiliar = 777;// la carta que va ganando ya no es la pasada por parámetro.
                }
                else{
                    cartaQueVamosAJugar = cartasQueTieneEnLaMano[cont]; 
                    encontrado = true;
                }
               
            }
             cartasQueTieneEnLaMano[cont] = null;
            cont ++;
        }
        //return tirarCartaAleatoria();  
        return cartaQueVamosAJugar;  
    }

    /**
     * Metodo que hace que jugador recoja una baza ganada
     */
    public void addBaza(Baza bazaGanada)
    {
        bazasGanadas.add(bazaGanada);
    }

    /**
     * Metodo que devuelve el numero de bazas ganadas por el jugador hasta
     * el momento
     */
    public int getNumeroBazasGanadas() 
    {
        return bazasGanadas.size();
    }

}

