package dk.sdu.mmmi.cbse;

import dk.sdu.mmmi.cbse.common.data.Entity;
import java.util.Random;

public class Enemy extends Entity{

    private double randomMovement;

    private double randomShooting;

    private int modeDuration = 1;

    private int modeTime;

    public double getRandomMovement(){
        return randomMovement;
    }

    public int getModeDuration(){
        return modeDuration;
    }

    public double getRandomShooting(){
        Random random = new Random();
        randomShooting = random.nextDouble(5, 100);
        return randomShooting;
    }

    public void setNewDuration(){
        Random random = new Random();
        modeDuration = random.nextInt(25, 250);

    }
    public void setNewMovement(){
        Random random = new Random();
        randomMovement = random.nextDouble(0, 10);
    }

    public int getModeTime(){
        if((modeTime+1)%modeDuration == 0){
            setNewDuration();
            setNewMovement();
            modeTime = 0;
        }
        modeTime = modeTime+1;
        return modeTime;
    }

}
