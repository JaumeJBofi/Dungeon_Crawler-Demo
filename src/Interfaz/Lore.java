/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.util.*;

/**
 *
 * @author SERGIO
 */
public class Lore {
    private String input;
    private Scanner lectura;
    public Lore(){
        lectura = new Scanner(System.in);
    }
    public String nacer(){
        String nombre = "Rou";
        lectura = new Scanner(System.in);
        System.out.print("Te encuentras en un pequeño cuarto oscuro y calido.");
        lectura.nextLine();
        System.out.print("Las paredes son blandas y no tienes necesidad de buscar alimento alguno.");
        lectura.nextLine();
        System.out.print("Se puede decir que estas en un sitio de lo más agradable.");
        lectura.nextLine();
        System.out.println("De repente un dia vez como una luz ilumina el pequño cuarto y sales de este.");
        lectura.nextLine();
        System.out.print("...");
        lectura.nextLine();
        System.out.print("......");
        lectura.nextLine();
        System.out.println(".........");
        lectura.nextLine();
        System.out.print("Lo primero que ves es la cara de un anciano con piel color verde y orejas puntiagudas.");
        lectura.nextLine();
        System.out.print("A tu alrededor ves un monton de bebes con el mismo color de piel.");
        lectura.nextLine();
        System.out.println("Al ver tus manos ves que estas tambien son de color verde.");
        lectura.nextLine();
        System.out.println("Comprendes que eres de la misma especie que las criaturas que estan a tu alrededor.");
        lectura.nextLine();
        System.out.println("El anciano te mira y murmura algo.");
        lectura.nextLine();
        while(true){
            System.out.println("\n\nEscribe tu nombre o pon enter para tener el nombre default.");
            input = lectura.nextLine();
            if( input.length() > 0 )
                System.out.println("Estas seguro que tu nombre es \"" + input + "\"?\n1.- Si\n2.- No");
            else
                System.out.println("Estas seguro que quieres ir con el nombre Default?\n1.- Si\n2.- No");
            int rpt = lectura.nextInt();
            if(rpt == 1)
                break;
            lectura.nextLine();
        }
        if( input.length() > 0 )
            nombre = input;
        System.out.print("\n\n\n\"Tu te llamaras " + nombre + "\" dice el anciano.");
        lectura.nextLine();
        lectura.nextLine();
        System.out.println(nombre + "?");
        lectura.nextLine();
        System.out.println("Te gana el cansancio y duermes");
        lectura.nextLine();
        return nombre;
    }
    public void primera_salida(){
        System.out.print("Han pasado 3 dias desde que naciste.");
        lectura.nextLine();
        System.out.print("Eres un goblin, un demi-humano y vives en una cueva en medio de un bosque.");
        lectura.nextLine();
        System.out.print("A pesar de tener 3 dias de edad ya mides 110 centimetros.");
        lectura.nextLine();
        System.out.print("Esto se debe más que nada a un rasgo racial.");
        lectura.nextLine();
        System.out.print("Cuando un goblin madura ya no recibe alimento de la comuna, sino debe cazar su propia comida.");
        lectura.nextLine();
        System.out.print("Ese es el principal motivo por el cual sales de la cueva.");
        lectura.nextLine();
        System.out.print("Al salir de la cueva ves una gran cantidad de arboles alrededor de la cueva.\n\n\n");
        lectura.nextLine();
    }
    public void volver_cueva(){
        System.out.print("\n\n\nVes la puerta de la cueva.");
        input = lectura.nextLine();
        System.out.print("Aun tienes hambre, asi que decides estar fuera un tiempo más.");
        input = lectura.nextLine();
    }
    public void fin_juego(){
        System.out.print("Has vencido (y comido) varios enemigos.");
        input = lectura.nextLine();
        System.out.print("Antes de darte cuenta estas fuera del bosque.");
        input = lectura.nextLine();
        System.out.print("A lo lejos ves una aldea de humanos.");
        input = lectura.nextLine();
        System.out.print("Tu estomago hace un sonido que te da a entender que todavia no estas satisfecho.");
        input = lectura.nextLine();
    }
    public void error_comando(){
        System.out.print("Comando no valido.");
        input = lectura.nextLine();
    }
    public void camino_bloqueado(){
        System.out.print("Algo bloquea tu camino.");
        input = lectura.nextLine();
    }
    public void winGame(Scanner in){
        
        writeNLines(20);
         System.out.print("                                   ||`-.___\n" +
"                                   ||    _.>\n" +
"                                   ||_.-'\n" +
"               ==========================================\n" +
"                `.:::::::.       `:::::::.       `:::::::.\n" +
"                  \\:::::::.        :::::::.        :::::::\\\n" +
"                   L:::::::         :::::::         :::::::L\n" +
"                   J::::::::        ::::::::        :::::::J\n" +
"                    F:::::::        ::::::::        ::::::::L\n" +
"                    |:::::::        ::::::::        ::::::::|\n" +
"                    |:::::::        ::::::::        ::::::::|     .---.\n" +
"                    |:::::::        ::::::::        ::::::::|    /(@  o`.\n" +
"                    |:::::::        ::::::::        ::::::::|   |    /^^^\n" +
"     __             |:::::::        ::::::::        ::::::::|    \\ . \\vvv\n" +
"   .'_ \\            |:::::::        ::::::::        ::::::::|     \\ `--'\n" +
"   (( ) |           |:::::::        ::::::::        ::::::::|      \\ `.\n" +
"    `/ /            |:::::::        ::::::::        ::::::::|       L  \\\n" +
"    / /             |:::::::        ::::::::        ::::::::|       |   \\\n" +
"   J J              |:::::::        ::::::::        ::::::::|       |    L\n" +
"   | |              |:::::::        ::::::::        ::::::::|       |    |\n" +
"   | |              |:::::::        ::::::::        ::::::::|       F    |\n" +
"   | J\\             F:::::::        ::::::::        ::::::::F      /     |\n" +
"   |  L\\           J::::::::       .::::::::       .:::::::J      /      F\n" +
"   J  J `.     .   F:::::::        ::::::::        ::::::::F    .'      J\n" +
"    L  \\  `.  //  /:::::::'      .::::::::'      .::::::::/   .'        F\n" +
"    J   `.  `//_..---.   .---.   .---.   .---.   .---.   <---<         J\n" +
"     L    `-//_=/  _  \\=/  _  \\=/  _  \\=/  _  \\=/  _  \\=/  _  \\       /\n" +
"     J     /|  |  (_)  |  (_)  |  (_)  |  (_)  |  (_)  |  (_)  |     /\n" +
"      \\   / |   \\     //\\     //\\     //\\     //\\     //\\     /    .'\n" +
"       \\ / /     `---//  `---//  `---//  `---//  `---//  `---'   .'\n" +
"________/_/_________//______//______//______//______//_________.'_________\n" +
"##VK######################################################################");
        System.out.print("Abres la puerta, esperando lo mismo que ha sucedido antes... Una prueba mas, un castigo mas por lo que\nhemos ignorado tanto tiempo.");
        System.out.printf(" Sin embargo, no encuentras mas.\nNo encuentras mas oposición, ahora que has aceptado los pecados que antes ignoraste puedes enfrentar\n"
                + "las verdaderas pruebas de la vida\n");
        System.out.printf("Te preguntas si sientes felicidad o remordimiento. ¿Saber que tan cruel el mundo puede ser es una bedición o maldición? Solo el tiempo"
                + " lo dirá...\n\nPero por ahora te dices a ti mismo un pequeño y melancolico 'Felicitaciones'");       
        System.out.println("\n\nPresione cualquier tecla para terminar\n");
       
        lectura.nextLine();
        System.exit(0);
    }
    
    public String IntroMenu(Scanner in){
        System.out.println("Bienvenidos a Rings of Sins\n");
        System.out.println("               ...\n" +
"             ;::::;\n" +
"           ;::::; :;\n" +
"         ;:::::'   :;\n" +
"        ;:::::;     ;.\n" +
"       ,:::::'       ;           OOO\\\n" +
"       ::::::;       ;          OOOOO\\\n" +
"       ;:::::;       ;         OOOOOOOO\n" +
"      ,;::::::;     ;'         / OOOOOOO\n" +
"    ;:::::::::`. ,,,;.        /  / DOOOOOO\n" +
"  .';:::::::::::::::::;,     /  /     DOOOO\n" +
" ,::::::;::::::;;;;::::;,   /  /        DOOO\n" +
";`::::::`'::::::;;;::::: ,#/  /          DOOO\n" +
":`:::::::`;::::::;;::: ;::#  /            DOOO\n" +
"::`:::::::`;:::::::: ;::::# /              DOO\n" +
"`:`:::::::`;:::::: ;::::::#/               DOO\n" +
" :::`:::::::`;; ;:::::::::##                OO\n" +
" ::::`:::::::`;::::::::;:::#                OO\n" +
" `:::::`::::::::::::;'`:;::#                O\n" +
"  `:::::`::::::::;' /  / `:#\n" +
"   ::::::`:::::;'  /  /   `#");
        System.out.println("\n\"Si tuvieramos que abreviar las cosas acabas de morir\"\n");
        lectura.nextLine();
        System.out.println("\n\"No me mires asi, la gente muere todo el tiempo\"\n"+
                            "\"Tú no eres la excepcion, solo te toco tu hora antes de tiempo\"\n");
        lectura.nextLine();
        System.out.println("\n\"Me siento algo curioso y me gustaria saber tu nombre\"\n");
        lectura.nextLine();
        System.out.println("\nEscribe tu nombre:\n");
        input = lectura.nextLine();
        if( input.length() > 0 )
            System.out.println("\n\n\"Bienvenido "+input+"\" --- Presiona Enter para comenzar\"");
        else{
            input = "Rou";
            System.out.println("\n\"Veo que no eres de los que habla\"\n");
            lectura.nextLine();
            System.out.println("\n\n\"Te llamare "+input+"..., de momento\" --- Presiona Enter para comenzar\"");
        }
        lectura.nextLine();
        return input;
    }
    public void listaAcciones(){
        System.out.println("\nAcciones Posibles:\n1.-Mover + direccion\n2.-Interactuar\n3.-Equip\n4.-Atacar\n");
    }
    public void writeNLines(int n)
    {
        for(int i =0;i<n;i++) System.out.print(" \n");
    }
}
