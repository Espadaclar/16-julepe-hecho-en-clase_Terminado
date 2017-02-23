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

    private Carta cartaQueVamosAJugar;//almacena la carta que va ganando en el mt jugarCartaInteligentemente()

    /**
     * Constructor for objects of class Jugador
     */
    public Jugador(String nombreJugador)
    {
        nombre = nombreJugador;
        cartasQueTieneEnLaMano = new Carta[5];
        numeroCartasEnLaMano = 0;   
        bazasGanadas = new ArrayList<Baza>();

        cartaQueVamosAJugar = null;
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
                System.out.println(cartaActual+ ", ");
            }
        }
    }

    public void verCartasJugadorConPrint()
    {
        // System.out.println("Cartas de " +name);
        for (Carta cartaActual : cartasQueTieneEnLaMano) {
            if (cartaActual != null) {
                System.out.print(cartaActual+ ", ");
            }
        }
    }

    /**
     * Metodo que devuelve el nombre del jugador
     */
    public String getNombre()
    {
        return " " +nombre+ "; ";
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
        // ---- EL PARÁMETRO cartaQueVaGanando, ES LA 1º CARTA EN JUGARSE Y LA 1º QUE VA GANANDO.
        //Carta cartaQueVamosAJugar = null;//ES LA CARTA QUE SE TIRA.
        int iteracionesTotales = cartasQueTieneEnLaMano.length;
        //-------------------BUCLE PARA RECORRER TODAS LAS CARTAS QUE TIENE EL JUGADOR----------------

        boolean encontrada = false;//--- para, el bucle while si encuentra la carta.
        int cuentaCartas = 0;//--cuenta las cartas del jugadorNoH

        //------ RECORRO LAS CARTAS QUE TIENE EL JUGADOR noHumano Y TIRO LA úlima QUE LA SUPERE EN VALOR---------
        while(cuentaCartas <  iteracionesTotales && !encontrada){
            for(Carta cartaG: cartasQueTieneEnLaMano){
                if(cartaG != null && cartaG.getPalo() == paloPrimeraCartaDeLaBaza && cartaG.getValor()
                > cartaQueVaGanando.getValor()){
                    cartaQueVamosAJugar = cartaG;
                    encontrada = true;                            
                }
            }

            // 2º ---- SI EL noHumano TIENE CARTAS PARA ASISTIR AL PALO DE LA 1º CARTA JUGADA POR EL humano, Y NO LA GANA¡¡¡¡¡.
            if(!encontrada){                         
                for(Carta cartaG: cartasQueTieneEnLaMano){
                    if(cartaG != null && cartaG.getPalo() == paloPrimeraCartaDeLaBaza && cartaG.getValor()
                    < cartaQueVaGanando.getValor()){
                        cartaQueVamosAJugar = cartaG;
                        encontrada = true;                               
                    }
                }
            }

            // 3º--------- SI EL noHumano ¡¡¡NO TIENE CARTAS PARA ASISTIR AL PALO DE LA 1º CARTA, Y ÉSTA NO ES TRIUNFO.(y el nHum tiene triunfo)
            if(!encontrada){
                for(Carta cartaG: cartasQueTieneEnLaMano){
                    if(cartaG != null &&  cartaG.getPalo() != paloPrimeraCartaDeLaBaza && paloPrimeraCartaDeLaBaza != paloQuePinta){
                        if(cartaG.getPalo() ==  paloQuePinta){
                            cartaQueVamosAJugar = cartaG;
                            encontrada = true;                            
                        }  
                    }
                }

                // 4º---------- SI EL noHumano ¡¡¡NO TIENE CARTAS PARA ASISTIR AL PALO DE LA 1º CARTA Y ÉSTA ES TRIUNFO. 
                if(!encontrada){
                    for(Carta cartaG: cartasQueTieneEnLaMano){
                        if(cartaG != null &&  cartaG.getPalo() != paloPrimeraCartaDeLaBaza && paloPrimeraCartaDeLaBaza == paloQuePinta){

                            if(cartaG.getPalo() !=  paloQuePinta){
                                cartaQueVamosAJugar = cartaG;
                                encontrada = true;                              
                            }

                        }
                    }  
                }
            }
            cuentaCartas ++;
        }

        int anularElemento = 0;
        String anularCarta = cartaQueVamosAJugar.toString();
        for(int pi = 0; pi < cartasQueTieneEnLaMano.length; pi++){
            if(cartasQueTieneEnLaMano[pi] != null && cartasQueTieneEnLaMano[pi].toString().equals(anularCarta)){
                anularElemento = pi;
            }
        }
        cartasQueTieneEnLaMano[anularElemento] = null;

        System.out.println(nombre+ " juega el " +cartaQueVamosAJugar.toString());
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

    public void muestraArrayCartasQueTieneEnLaMano(){
        for(int i = 0; i < cartasQueTieneEnLaMano.length; i++){
            System.out.println(cartasQueTieneEnLaMano[i]);
        }
    }

    public void zzzEliminaUnElemento(int num){
        int cartaAJugar = cartasQueTieneEnLaMano.length - num;
        if(cartasQueTieneEnLaMano[cartaAJugar] != null){
            cartasQueTieneEnLaMano[cartaAJugar] = null;
        }
    }
}

