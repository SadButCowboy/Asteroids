package dk.sdu.mmmi.cbse.asteroids;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.commonasteroid.Asteroid;
import dk.sdu.mmmi.cbse.commonasteroid.AsteroidSPI;;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.Random;


public class AsteroidControlSystem implements IEntityProcessingService {

    private AsteroidsPlugin asteroidsPlugin = new AsteroidsPlugin();

    private AsteroidSPI asteroidSplitter = new AsteroidSplitter();

    private static double asteroidSpawnCount = 0;

    private static int score;

    private double randomDirection;

    private Random random = new Random();

    public double getRandomDirection(){
        Random random = new Random();
        randomDirection = random.nextDouble(0, 360);
        return randomDirection;
    }

    private void incrementScore(){
        HttpClient client = HttpClient.newHttpClient();
        String apiUrl = "http://localhost:8080/score";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            score = Integer.parseInt(response.body());
            System.out.println("Current Score is: "+score);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                incrementScore();
                continue;
            } else if (curAste.getHit() && curAste.getTimesSplit() >= 3) {
                curAste.setDead(true);
                incrementScore();
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
