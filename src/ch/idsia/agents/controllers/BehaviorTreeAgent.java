package ch.idsia.agents.controllers;

import ch.idsia.agents.Agent;
import ch.idsia.agents.btcompiler.*;

import ch.idsia.benchmark.mario.engine.sprites.Mario;
import ch.idsia.benchmark.mario.engine.sprites.Sprite;
import ch.idsia.benchmark.mario.environments.Environment;
import ch.idsia.benchmark.mario.environments.MarioEnvironment;

import java.util.ArrayList;

public class BehaviorTreeAgent extends BasicMarioAIAgent implements Agent {
    Tree bTree;
    public boolean coinAbove;
    public BehaviorTreeAgent() {
        super("BehaviorTreeAgent");
        reset();
        Sequence s1 = new Sequence();
        Sequence s2 = new Sequence();
        Sequence s3 = new Sequence();
        Sequence s4 = new Sequence();
        Sequence s5 = new Sequence();
        Sequence s6 = new Sequence();
        bTree = new Tree(this);
        TreeCreator convenience = new TreeCreator();

        ArrayList<String> xmlBTree= convenience.convertXML();
        if(xmlBTree.size()>0){
            for (String s : xmlBTree) {
                System.out.println(s);
                switch (s) {

                    case "CanGrabCoin":
                        s1.setChildren(new CanGrabCoin());
                    case "GrabCoin":
                        s1.setChildren(new GrabCoin());
                    case "CanShootEnemy":
                        s2.setChildren(new CanShootEnemy());
                    case "ShootEnemy":
                        s2.setChildren(new ShootEnemy());
                    case "CanMoveForward":
                        s3.setChildren(new CanMoveForward());
                    case "MoveForward":
                        s3.setChildren(new MoveForward());
                    case "CanMoveForwardJump":
                        s4.setChildren(new CanMoveForwardJump());
                    case "MoveForwardJump":
                        s4.setChildren(new MoveForwardJump());
                    case "CanMoveBackward":
                        s5.setChildren(new CanMoveBackward());
                    case "MoveBackward":
                        s5.setChildren(new MoveBackward());
                    case "CanMoveBackwardJump":
                        s6.setChildren(new CanMoveBackwardJump());
                    case "MoveBackwardJump":
                        s6.setChildren(new MoveBackwardJump());
                }
            }
            bTree.addTask(s1);
            bTree.addTask(s2);
            bTree.addTask(s3);
            bTree.addTask(s4);
            bTree.addTask(s5);
            bTree.addTask(s6);
        }

    }
    public boolean isEnemy(int c)
    {
        if(c==Sprite.KIND_GOOMBA || c==Sprite.KIND_RED_KOOPA || c==Sprite.KIND_RED_KOOPA_WINGED || c==Sprite.KIND_GREEN_KOOPA_WINGED || c==Sprite.KIND_GREEN_KOOPA){
            return true;
        }
        return false;
    }
    //Create functions that need the BasicMarioAI functions
    public boolean CanGrabCoin(){
        int a = marioEgoCol;
        int b = marioEgoRow;
        coinAbove=false;
        if(getReceptiveFieldCellValue(a+2,b)==1){
            coinAbove=false;
            return forward();

        }
        else if(getReceptiveFieldCellValue(a+2,b+1)==1 ){
            coinAbove=true;
            return forwardJump();
        }
        else{
            return false;
        }

    }

    public boolean isEnemyInRange(){
        boolean inRange=false;
        boolean isFireMode=false;
        int a = marioEgoCol;
        int b = marioEgoRow;
        for(int i = 0; i<3; i++){
            if(isEnemy(enemies[a+i][b]) || isEnemy(enemies[a][b+i]) || isEnemy(enemies[a+i][b+i]) /*|| isEnemy(enemies[a+i][b-1]) || isEnemy(enemies[a+i][b-2])*/ ){
                inRange=true;
                break;
            }
        }
        //System.out.println(MarioEnvironment.getInstance().getMarioMode());
        if(MarioEnvironment.getInstance().getMarioMode()==2){
            isFireMode=true;
        }
        return inRange && isMarioAbleToShoot && isFireMode;
    }

    public boolean forward() {
        boolean isEnemyNear=false;
        int a = marioEgoCol;
        int b = marioEgoRow;
        for(int i =0; i<2; i++){
            if(isEnemy(enemies[a+i][b])) {
                isEnemyNear = true;
                break;
            }
        }
        boolean isBlockFront=false;
        for(int i =0; i<3; i++){
            if(levelScene[a+i][b]!=0){
                isBlockFront=true;
                break;
            }
        }
        return !isBlockFront && !isEnemyNear;
    }

    public boolean forwardJump() {
        int a = marioEgoCol;
        int b = marioEgoRow;
        boolean isEnemyHaxor= false;
        boolean isBlockFront=false;
        //assuming jump distance and height of 3, just deal with outer value
        if((isEnemy(enemies[a][b+3]) || isEnemy(enemies[a+3][b])) && levelScene[a+1][b+1]!=0){
            isEnemyHaxor=true;
        }
        /*boolean isEnemyHaxor= false;
        for(int i=0; i<3;i++) {
            if (enemies[marioEgoRow][marioEgoCol + i] != 0 || enemies[marioEgoRow + i][marioEgoCol] != 0 || enemies[marioEgoRow + i][marioEgoCol + i] != 0) {
                isEnemyHaxor = true;
                break;
            }
        }

         */

        return (!isMarioOnGround || isMarioAbleToJump) && !isEnemyHaxor;
    }

    public boolean backward() {
        int a = marioEgoCol;
        int b = marioEgoRow;
        boolean isEnemyBehind=false;
        boolean isBlockBehind=false;
        if(isEnemy(enemies[a-1][b])){
                isEnemyBehind=true;
            }
        if(levelScene[a-1][b]!=0) {
            isBlockBehind = true;
            }
        else{
            isBlockBehind=false;
        }
        return !isBlockBehind && !isEnemyBehind;
    }

    /*public boolean backwardJump() {
        boolean isEnemyBehindJump=false;
        int a = marioEgoCol;
        int b = marioEgoRow;
        if(enemies[a-1][b+1]!=0){
            isEnemyBehindJump=true;
        }
          return (!isMarioOnGround || isMarioAbleToJump) && !isEnemyBehindJump;
    }

     */


    public void shoot(){
        action[Mario.KEY_SPEED] = true;
    }

    public void jump(){
        action[Mario.KEY_JUMP] = true;
    }

    public void right(){
        action[Mario.KEY_RIGHT] = true;
    }

    public void left(){
        action[Mario.KEY_LEFT] = true;
    }

    public boolean[] getAction(){

        reset();
        bTree.runLeTree();

        return action;
    }

    public void reset(){
        action = new boolean[Environment.numberOfKeys];
    }
}
