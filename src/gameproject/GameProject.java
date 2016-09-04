/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameproject;
import Controllers.Dibujador;
import Controllers.DungeonManager;
import Foundation.CellInformation;
import Models.Avatar;
import Foundation.Options;
import Foundation.Options.ACTION;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Jauma
 */
public class GameProject {

    /**
     * @param args the command line arguments
     */
    
    static public void writeNLines(int n)
    {
        for(int i =0;i<n;i++) System.out.print(" \n");
    }
    
    static public void winGame(Scanner in){
        
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
                + " lo dirá...\n\nPero por ahora te dices a ti mismo un pequeño y melancolico 'Felicitaciones");       
        System.out.println("\n\nPresione cualquier tecla para terminar\n");
       
        in.nextLine();
        System.exit(0);
    }
    
    
    public static void main(String[] args) {                                                   
        // Aca hacemos random de las dimensiones
        //Tambien podemos ya ir creando los otros laberintos
        Random randManager = new Random();
         Scanner in = new Scanner(System.in);
        
        DungeonManager myManager = new DungeonManager(randManager.nextInt(7-3)+3);
        //DungeonManager myManager = new DungeonManager(2);
        
        int varM = randManager.nextInt(35-25)+25;
        int varN = randManager.nextInt(35-25)+25;                
        
        Avatar player = new Avatar(myManager.CreateDungeonDistribution(varM,varN,0.15,5,0.3),10,6,"Jaume Bofi");
        //Avatar player = new Avatar(myManager.CreateDungeonDistribution(40,5,0.5,0),40,10);
        
        Dibujador Renderer = new Dibujador();
        
        Options choiceTaken = new Options(ACTION.INTERACT);
        
        CellInformation nextCellInformation;
        
        Renderer.mostrarLaberinto(myManager.GetActiveDungeon(), player);
        
        while((ACTION.EXIT!=((Renderer.mostrarMenu(choiceTaken)).taken)))
        {                        
            switch(choiceTaken.taken){
                case MOVE:{
                    if(!(nextCellInformation = myManager.ValidMoveAndChange(player.GetPosition(),choiceTaken.path)).isWall()&&nextCellInformation.GetType()==CellInformation.CELLTYPE.ADENTRO)
                            {
                                if(nextCellInformation.isNext()||nextCellInformation.isNext())
                                {
                                   if(myManager.ChangeDungeon(player,nextCellInformation.isNext())==1){
                                       winGame(in);                                       
                                   }
                                }else{
                                    player.Move(choiceTaken.path, 1);                                        
                                }                       
                        writeNLines(20);
                    }else{
                        writeNLines(20);
                        switch(nextCellInformation.GetType())
                        {
                            case PARED:
                                System.out.println("\n\nNo puedes atravezar paredes....aún...");
                            break;
                            case ARTIFACT:
                                System.out.println("\n\nUn misterioso objeto bloquea tu camino. Puedes intentar cogerlo");
                            break;
                            case ENEMY:
                                System.out.printf("\n\nCuidado! Un enemigo? Preparate para una posible batalla");
                            break;                            
                        }         
                        System.out.println("");
                    }                    
                }break;
                case INTERACT:{  
                    if(!(nextCellInformation = myManager.ValidMoveAndChange(player.GetPosition(),choiceTaken.path)).isWall()){
                        
                        
                        switch(nextCellInformation.GetType())
                        {
                            case ARTIFACT:
                            {                                
                                myManager.Interactuar(player,choiceTaken.path); // borra el artefacto de la matriz                                
                                writeNLines(20);                              
                                System.out.println("\n\n INTERACT CORRECTO");
                                
                            }break;
                            default:
                            {
                                writeNLines(20);                              
                                System.out.println("\n\nNo puedes interactuar con esa casilla");                                
                            }break;                            
                        }                        
                        System.out.println("");
                    }else{
                        writeNLines(20);
                        System.out.println("\n\nNo puedes interactuar con esa pared");
                        System.err.println("");                        
                    }
                }case DEBUG:
                {
                    writeNLines(20);                    
                    myManager.printDebugInfo(player);
                    System.err.println("");
                }break;
                case TELEPORT:
                {                   
                    int x = in.nextInt();
                    int y = in.nextInt();
                    myManager.GetActiveDungeon().TeleportPlayer(player, x, y);                            
                }
                default:{
                    writeNLines(20);
                    System.out.println("\n\nAcción no definida");
                    System.out.println("");
                    
                }break;
            }            
            Renderer.mostrarLaberinto(myManager.GetActiveDungeon(), player);
        }
    }
    
}
