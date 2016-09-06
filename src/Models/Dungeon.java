/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;
import Foundation.CellInformation;
import Foundation.Coordinate;
import Controllers.ObjectGenerator;
import java.util.List;
import Models.Enemy;
import Controllers.EnemyGenerator;
import Foundation.DIRECTIONS;
import java.util.ArrayList;

/**
 *
 * @author Jauma
 */
public class Dungeon implements IDibujable{
    
    private int M;
    private int N;
    
    private int minshowY;
    private int maxshowY;
    private int minshowX; 
    private int maxshowX;
    
    private double prcEnemies;
    private int lvlEnemies;
    private double prcItems;
    
    private Coordinate antPos;
    private Coordinate sigPos;
    
    private int numEnemies;
    
    //private CellInformation dungeonStatus;    
    
    private Chamber[][] layOutChamber;
    private CellInformation[][] dungeonAccess;    
    private ObjectGenerator objManager;
    
    private List<Enemy> lista_enemigos;
    private EnemyGenerator enemygen;
    
    
    public Dungeon(double varprcEnemies,int varlvlEnemies,double varPrcItems){
        // Momentaneamente el Laberinto no posee dimensiones
        M = 0;
        N = 0;
        SetLvlEnemies(varlvlEnemies);
        SetPrcEnemies(varprcEnemies);
        SetPrcItems(varPrcItems);
        objManager = new ObjectGenerator(varlvlEnemies);
        
        enemygen = new EnemyGenerator();
        lista_enemigos = new ArrayList();
    }
     
    public int GetM(){
        return M;
    }
       
    
    // Recordar que las dimensiones deben ser impares
    final public void SetM(int varM){
        if(varM%2==0){
            M = varM +1;
        }else{
            M = varM;
        }
    }
    
    final public int GetN(){
        return N;
    }
    
    final public void SetN(int varN){
        if(varN%2==0){
            N = varN +1;
        }else
        {
            N = varN;
        }
    }
    
    final public double GetPrcEnemies(){
        return prcEnemies;
    }
    
    
    final public void SetPrcEnemies(double varPrcEnemies){
        if(varPrcEnemies>=1){
            // Lanzamos exepcion, la cantiddad del porcentajes de enemigos
            // no puede ser mas del 100%
            throw new UnknownError();
        }else{
            prcEnemies = varPrcEnemies;
        }
    }
    
    final public int GetLvlEnemies(){
        return lvlEnemies;
    }
    
      final public double GetLvlEnemies(double worldLvl){
          // Implementamos un nivel que dependiendo del nivel del mundo aumente
          // el nivel que le corresponde
        return lvlEnemies;
    }
    
    final public void SetLvlEnemies(int varLvlEnemies){
        //Logica de validacion para nivel de Enemigos
        lvlEnemies = varLvlEnemies;
    }
    
    final public void SetPrcItems(double varPrcItems){
        prcItems = varPrcItems;
    }
    
    public double GetPrcItem(){
        return prcItems;
    }
    
    public int GetNumEnemies(){
        return numEnemies;
    }
    
    public void SetNumEnemies(int varNumEnemies){
        if(varNumEnemies<0){
            numEnemies = varNumEnemies;
        }
    }
        
    public CellInformation GetCellInformation(int x,int y){
        return dungeonAccess[x][y];
    }
    
    public Coordinate GetAntPos()
    {
        return antPos;
    }
    
    public void SetAntPos(Coordinate varAntPos){
        //Me entrega memoria
        antPos = varAntPos;
    }
    
    public Coordinate GetSigPos(){
        return sigPos;
    }
    
    public void SetSigPos(Coordinate varSigPos){
        sigPos = varSigPos;
    }
    
    public void SetAccess(CellInformation access[][]){
        dungeonAccess = access;
    }
    
    public Artefacto getObject(int x,int y)
    {
        dungeonAccess[x][y].SetType(CellInformation.CELLTYPE.ADENTRO);
        switch(dungeonAccess[x][y].GetObject())
        {
            case WEAPON:
            {
                return objManager.generar_arma();
            }
            case ARMOR:
            {
                return objManager.generar_armadura();
            }
            case POTION:
            {
                return objManager.generar_pocion();
            }
            default:
            {
                return objManager.generar_pocion();
            }
        }        
        // Deberiamos liberar la memoria de layout
    }
    
    private void inicializarDatosMostrarMapa(int posY,int posX,int tamShowX,int tamShowY){
        tamShowX = (int)tamShowX/2;
        tamShowY = (int)tamShowY/2;
        
        if( (posY - tamShowY) > 0 )
            minshowY = posY - tamShowY;
        else
            minshowY = 0;
        
        if( (posX - tamShowX) > 0 )
            minshowX = posX - tamShowX;
        else
            minshowX = 0;
        
        if( (posY + tamShowY) < N )
            maxshowY = posY + tamShowY;
        else
            maxshowY = N;
        
        if( (posX + tamShowX) < M )
            maxshowX = posX + tamShowX;
        else
            maxshowX = M;
    }
    
    public void printDebugInfo()
    {
        System.out.format("Numero de enemigos = %d\n",numEnemies);
        System.out.format("Nivel de los enemigos = %d\n",lvlEnemies);
        System.out.print("Informacion de puntos:\nAnterior:\n");
        antPos.PrintCoordinate();
        System.out.print("Siguiente:\n");
        sigPos.PrintCoordinate();
        System.out.println("");
    }
    
        /// agregado ////}
    public void Interactuar(Avatar player, DIRECTIONS path) {
        int xFactor = player.GetX(), yFactor = player.GetY();
        switch (path) {
            case BOT: {
                yFactor += 1;
            }
            break;
            case TOP: {
                yFactor -= 1;
            }
            break;
            case LEFT: {
                xFactor -= 1;
            }
            break;
            case RIGHT: {
                xFactor += 1;
            }
        }
        ///// deberia actualizar el access y borrar el objeto de la matriz
        player.AddArtefact(getObject(xFactor,yFactor));
    }
    
    public void Battle(int x,int y,DIRECTIONS path){
        int xFactor = x, yFactor = y;
        switch (path) {
            case BOT: {
                yFactor += 1;
            }
            break;
            case TOP: {
                yFactor -= 1;
            }
            break;
            case LEFT: {
                xFactor -= 1;
            }
            break;
            case RIGHT: {
                xFactor += 1;
            }
        }
        dungeonAccess[xFactor][yFactor].SetType(CellInformation.CELLTYPE.ADENTRO);
    }

    
    public void TeleportPlayer(Avatar player,int x,int y)
    {
        player.SetPosition(x, y);        
    }
    
    public void Render(){
        String space20 = new String(new char[40]).replace('\0', ' ');
        for(int j=0;j<N;j++)
        {
            System.out.print(space20);
            for(int i=0;i<M;i++)
            {                
                CellInformation factor = dungeonAccess[i][j];                    
                switch (factor.GetMode()){
                    case SIGUENTE:
                        System.out.print("+");
                        break;
                    case ANTERIOR:
                        System.out.print("-");
                        break;
                    default:{                            
                        switch (factor.GetType()){
                            case PARED:
                                System.out.print("#");
                            break;
                            case ADENTRO:
                                System.out.print(" ");
                            break;
                            case ARTIFACT:
                            System.out.print("A");
                            break;
                            case ENEMY:
                                System.out.print("E");
                            break;                            
                        }                            
                    }break;                                                     
                }                                                            
            }
            System.out.print("\n");
        }        
    }

    public void Render(int posX,int posY,int tamShowX,int tamShowY){   
        
        String space20 = new String(new char[35]).replace('\0', ' ');
        inicializarDatosMostrarMapa(posY, posX, tamShowX, tamShowY);
        for(int j = minshowY;j<maxshowY;j++){
            System.out.print(space20);
            for(int i = minshowX;i<maxshowX;i++){                
                
                if( (i == posX) && (j == posY) ){
                    System.out.print("H");
                }
                else{
                    CellInformation factor = dungeonAccess[i][j];                    
                    switch (factor.GetMode()){
                        case SIGUENTE:
                            System.out.print("+");
                            break;
                        case ANTERIOR:
                            System.out.print("-");
                            break;
                        default:{                            
                            switch (factor.GetType()){
                                case PARED:
                                    System.out.print("#");
                                break;
                                case ADENTRO:
                                    System.out.print(" ");
                                break;
                                case ARTIFACT:
                                System.out.print("A");
                                break;
                                case ENEMY:
                                    System.out.print("E");
                                break;
                            }                          
                        }break;                                                     
                    }                                                            
                }  
            }
            System.out.println(" ");
        }       
    }
    
    public void LoadComponents(){
        
    }
    
    public void Dispose(){
        for(int i = 0;i<M;i++){
            for(int j = 0;j<N;j++){
                dungeonAccess[i][j] = null;
                layOutChamber[i][j] = null;
            }
        }
    }
    
     //Modif
    public void addenemy(Coordinate pos) {
        Enemy enemigo = enemygen.generar_enemigo();
        enemigo.SetPosition(pos);
        lista_enemigos.add(enemigo);
    }
}
