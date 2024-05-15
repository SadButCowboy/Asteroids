package dk.sdu.mmmi.cbse.asteroids;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.commonasteroid.Asteroid;
import dk.sdu.mmmi.cbse.commonasteroid.AsteroidSPI;
import dk.sdu.mmmi.cbse.asteroids.AsteroidSplitter;

import java.util.Random;


public class AsteroidControlSystem implements IEntityProcessingService {

    private AsteroidsPlugin asteroidsPlugin = new AsteroidsPlugin();

    private AsteroidSPI asteroidSplitter = new AsteroidSplitter();

    private static double asteroidSpawnCount = 0;

    private double randomDirection;

    private Random random = new Random();

    public double getRandomDirection(){
        Random random = new Random();
        randomDirection = random.nextDouble(0, 360);
        return randomDirection;
    }

    @Override
    public void process(GameData gameData, World world) {
        asteroidSpawnCount = asteroidSpawnCount+1;


        double asteroidSpawnTimer = 150;
        if (asteroidSpawnCount >= asteroidSpawnTimer){
            Entity asteroid = asteroidsPlugin.createAsteroid(gameData);
            world.addEntity(asteroid);
            asteroidSpawnCount = 0;
        }

        for (Entity entity: world.getEntities(Asteroid.class)){
            Asteroid curAste = (Asteroid) entity;
            if (curAste.getHit() && curAste.getTimesSplit() < 3){
                curAste.setTimesSplit(curAste.getTimesSplit() + 1);
                for (int i = 0; i < 2; i++) {
                    Asteroid babyAsteroid = (Asteroid) asteroidsPlugin.createBabyAsteroid(gameData, curAste, curAste.getTimesSplit());
                    //babyAsteroid = new Asteroid();
                    babyAsteroid.setRotation(getRandomDirection());
                    babyAsteroid.setRadius(10/babyAsteroid.getTimesSplit());
                    babyAsteroid.setX(curAste.getX()+i*10-5);
                    babyAsteroid.setY(curAste.getY()+i*10+5);
                    world.addEntity(babyAsteroid);
                    asteroidsPlugin.createBabyAsteroid(gameData, babyAsteroid, babyAsteroid.getTimesSplit());
                }
                curAste.setDead(true);
                continue;
            } else if (curAste.getHit() && curAste.getTimesSplit() >= 3) {
                curAste.setDead(true);
                continue;
            }

            double changeAsteroidX = Math.cos(Math.toRadians(curAste.getRotation()));
            double changeAsteroidY = Math.sin(Math.toRadians(curAste.getRotation()));
            curAste.setX(curAste.getX() + changeAsteroidX);
            curAste.setY(curAste.getY() + changeAsteroidY);
            curAste.setRadius(18);

            if (curAste.getX() < 0) {
                curAste.setX(1);
                curAste.setRotation(curAste.getRotation() + random.nextInt(91) + 90);
            }
            if (curAste.getX() > gameData.getDisplayWidth()) {
                curAste.setX(gameData.getDisplayWidth()-1);
                curAste.setRotation(curAste.getRotation() + random.nextInt(91) + 90);
            }
            if (curAste.getY() > gameData.getDisplayHeight()) {
                curAste.setY(gameData.getDisplayHeight()-1);
                curAste.setRotation(curAste.getRotation() + random.nextInt(91) + 90);
            }
            if (curAste.getY() < 0) {
                curAste.setY(1);
                curAste.setRotation(curAste.getRotation() + random.nextInt(91) + 90);
            }

        }
    }


}
