/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


/**
 *
 * @author alulab14
 */
public class realizarAccion {
    
    public realizarAccion(){
        
    }
    
    public int modificar(Dibujador pantalla, String scanner){
        //Termina el programa
        if( scanner.toLowerCase().equalsIgnoreCase("salir") ){
            System.out.println("\n\nSesionFinalizada");
            System.exit(0); 
        }
        if( scanner.toLowerCase().toLowerCase().startsWith("mover") ){
            return modificarMover(pantalla, scanner);
        }
        else if( scanner.toLowerCase().startsWith("interactuar") ){
            if( pantalla.getDireccion() == '0')
                System.out.println("\n\nNo le prestas atencion a nada en especifico");
            else{
                interactuarObjeto(pantalla);
            }
        }
        else
            System.out.println("\n\nAccion no disponible");
        return 2;
    }
    
    public int modificarMover(Dibujador pantalla, String scanner){
        if( scanner.toLowerCase().endsWith("derecha") ){
            pantalla.setDireccion('e');
            if( pantalla.modificarPos( 0, 1 ) )
                return 1;
        }
        else if( scanner.toLowerCase().endsWith("izquierda") ){
            pantalla.setDireccion('o');
            if( pantalla.modificarPos( 0, -1 ) )
                return 1;
        }
        else if( scanner.toLowerCase().endsWith("abajo") ){
            pantalla.setDireccion('s');
            if( pantalla.modificarPos( 1, 0 ) )
                return 1;
        }
        else if( scanner.toLowerCase().endsWith("arriba") ){
            pantalla.setDireccion('n');
            if( pantalla.modificarPos( -1, 0 ) )
                return 1;
        }
        System.out.println("\n\nAlgo bloquea el camino");
        return 2;
    }
    
    private void interactuarObjeto(Dibujador pantalla){
        char direccion =  pantalla.getDireccion();
        switch ( direccion ){
            case 'n':
                realizarAccion( pantalla, pantalla.objetoMapa( -1, 0 ) , direccion );
                break;
            case 's':
                realizarAccion( pantalla, pantalla.objetoMapa( 1, 0 ) , direccion );
                break;
            case 'e':
                realizarAccion( pantalla, pantalla.objetoMapa( 0, 1 ) , direccion );
                break;
            case 'o':
                realizarAccion( pantalla, pantalla.objetoMapa( 0, -1 ) , direccion );
                break;
            default:
                break;
        }
    }
    
    private void realizarAccion( Dibujador pantalla, int objeto, char direccion ){
        switch (objeto){
            case 0:
                System.out.println("\n\nVes un arbol, nada fuera de lo comun");
                break;
            case 1:
                System.out.println("\n\nNo ves nada fuera de lo comun");
                break;
            case 2:
                encontrarObjeto( pantalla, direccion );
                break;
            case 3:
                peleaEnemigo( pantalla, direccion );
                break;
            case 4:
                break;
        }
    }
    private void encontrarObjeto( Dibujador pantalla, char direccion ){
        System.out.println("\n\nEncontraste un palito :D");
        eliminarObjeto( pantalla, direccion );
        System.out.println("Cogiste el palito, ahora hay un defecit de palitos :(");
        System.out.println("Monstruo");
    }
    
    private void eliminarObjeto( Dibujador pantalla, char direccion ){
        switch ( direccion ){
            case 'n':
                pantalla.destruirObjMapa( -1, 0 );
                break;
            case 's':
                pantalla.destruirObjMapa( 1, 0 );
                break;
            case 'e':
                pantalla.destruirObjMapa( 0, 1 );
                break;
            case 'o':
                pantalla.destruirObjMapa( 0, -1 );
                break;
            default:
                break;
        }
    }
    
    private void peleaEnemigo( Dibujador pantalla, char direccion ){
        System.out.println("\n\nUn enemigo...");
        eliminarObjeto( pantalla, direccion );
        System.out.println("Murio de un paro cardiaco o_o");
        interactuarBotin( pantalla, direccion );
    }
    private void interactuarBotin( Dibujador pantalla, char direccion ){
        
    }
}
