package dk.sdu.mmmi.cbse.asteroids;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.commonasteroid.Asteroid;

import java.util.Random;

public class AsteroidsPlugin implements IGamePluginService {

    private Entity asteroid;

    public AsteroidsPlugin(){

    }

    public double[] coordiantes(int timesSplit){

        if (timesSplit == 1){
            return new double[]{-14, -14, -14, 14, 14, 14, 14, -14};
        } else if (timesSplit == 2) {
            return new double[]{-8, -8, -8, 8, 8, 8, 8, -8};
        }

        return new double[]{-0, -0, -0, 0, 0, 0, 0, -0};
    }
    @Override
    public void start(GameData gameData, World world) {
        asteroid = createAsteroid(gameData);
        world.addEntity(asteroid);
    }
    Random spawnSide = new Random();


    public Entity createBabyAsteroid(GameData gameData, Entity entity, int timesSplit){
        Asteroid babyAsteroidInstance = new Asteroid();
        babyAsteroidInstance.setTimesSplit(timesSplit);
        babyAsteroidInstance.setPolygonCoordinates(coordiantes(timesSplit));

        return babyAsteroidInstance;

    }


    public void splitAsteroid(Entity entity, World world){
        Asteroid asteroid = (Asteroid) entity;
        asteroid.setTimesSplit(asteroid.getTimesSplit()+1);
    }

    public Entity createAsteroid(GameData gameData) {
        Entity asteroid = new Asteroid();
        asteroid.setPolygonCoordinates(-20, -20, -20, 20, 20, 20, 20, -20);
        Random randY = new Random();
        Random randX = new Random();
        Random rotation = new Random();


        switch (spawnSide.nextInt(1, 4)) {
            case 1 -> {
                // TOP
                asteroid.setY(gameData.getDisplayHeight());
                asteroid.setX(randX.nextDouble(0, gameData.getDisplayWidth()));
                asteroid.setRotation(rotation.nextDouble(1, 179));
            }
            case 2 -> {
                // LEFT
                asteroid.setY(randY.nextDouble(0, gameData.getDisplayHeight()));
                asteroid.setX(0);
                int rotationSide = rotation.nextInt(1, 2);
                if (rotationSide == 2) {
                    asteroid.setRotation(rotation.nextDouble(0, 89));
                } else {
                    asteroid.setRotation(rotation.nextDouble(269, 360));
                }
            }
            case 3 -> {
                // RIGHT
                asteroid.setY(0);
                asteroid.setX(randX.nextDouble(0, gameData.getDisplayWidth()));
                asteroid.setRotation(rotation.nextDouble(91, 269));
            }
            case 4 -> {
                // BOTTOM
                asteroid.setY(randY.nextDouble(0, gameData.getDisplayHeight()));
                asteroid.setX(gameData.getDisplayWidth());
                asteroid.setRotation(rotation.nextDouble(181, 359));
            }
        }

        //asteroidInstance.setX(gameData.getDisplayHeight());
        //asteroidInstance.setY(gameData.getDisplayWidth());

        //double changeY = Math.cos(Math.toRadians((asteroidInstance.getRotation())));
        //double changeX = Math.sin(Math.toRadians((asteroidInstance.getRotation())));

        //asteroidInstance.setY(asteroidInstance.getY() + changeY);
        //asteroidInstance.setX(asteroidInstance.getX() + changeX);

        return asteroid;
    }

    @Override
    public void stop(GameData gameData, World world) {

        world.removeEntity(asteroid);
    }
}
