/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

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
        String input;
        Scanner lectura;
        lectura = new Scanner(System.in);
        System.out.print("Te encuentras en un pequeño cuarto oscuro y calido.");
        input = lectura.nextLine();
        System.out.print("Las paredes son blandas y no tienes necesidad de buscar alimento alguno.");
        input = lectura.nextLine();
        System.out.print("Se puede decir que estas en un sitio de lo más agradable.");
        input = lectura.nextLine();
        System.out.println("De repente un dia vez como una luz ilumina el pequño cuarto y sales de este.");
        input = lectura.nextLine();
        System.out.print("...");
        input = lectura.nextLine();
        System.out.print("......");
        input = lectura.nextLine();;
        System.out.println(".........");
        input = lectura.nextLine();
        System.out.print("Lo primero que ves es la cara de un anciano con piel color verde y orejas puntiagudas.");
        input = lectura.nextLine();
        System.out.print("A tu alrededor ves un monton de bebes con el mismo color de piel.");
        input = lectura.nextLine();
        System.out.println("Al ver tus manos ves que estas tambien son de color verde.");
        input = lectura.nextLine();
        System.out.println("Comprendes que eres de la misma especie que las criaturas que estan a tu alrededor.");
        input = lectura.nextLine();
        System.out.println("El anciano te mira y murmura algo.");
        input = lectura.nextLine();
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
            input = lectura.nextLine();
        }
        if( input.length() > 0 )
            nombre = input;
        System.out.print("\n\n\n\"Tu te llamaras " + nombre + "\" dice el anciano.");
        input = lectura.nextLine();
        input = lectura.nextLine();
        System.out.println(nombre + "?");
        input = lectura.nextLine();
        System.out.println("Te gana el cansancio y duermes");
        input = lectura.nextLine();
        return nombre;
    }
    public void primera_salida(){
        System.out.print("Han pasado 3 dias desde que naciste.");
        input = lectura.nextLine();
        System.out.print("Eres un goblin, un demi-humano y vives en una cueva en medio de un bosque.");
        input = lectura.nextLine();
        System.out.print("A pesar de tener 3 dias de edad ya mides 110 centimetros.");
        input = lectura.nextLine();
        System.out.print("Esto se debe más que nada a un rasgo racial.");
        input = lectura.nextLine();
        System.out.print("Cuando un goblin madura ya no recibe alimento de la comuna, sino debe cazar su propia comida.");
        input = lectura.nextLine();
        System.out.print("Ese es el principal motivo por el cual sales de la cueva.");
        input = lectura.nextLine();
        System.out.print("Al salir de la cueva ves una gran cantidad de arboles alrededor de la cueva.\n\n\n");
        input = lectura.nextLine();
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
       
        in.nextLine();
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
        System.out.println("\nIngresa tu nombre para poder continuar:\n");
        String name = in.nextLine();
        System.out.println("\n\nBienvenido "+name+" --- Presiona Enter para comenzar");
        in.nextLine();
        return name;
    }
    
    public void writeNLines(int n)
    {
        for(int i =0;i<n;i++) System.out.print(" \n");
    }
}
