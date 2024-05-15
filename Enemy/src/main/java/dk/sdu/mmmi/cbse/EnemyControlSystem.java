package dk.sdu.mmmi.cbse;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;


public class EnemyControlSystem implements IEntityProcessingService {

    private static double enemyReload;


  //  private Entity enemy;

    private final double enemySpawnTimer = 500;

    private static double enemySpawnCount = 0;


    private static boolean flag2 = true;

    private Entity createEnemyShip(GameData gameData) {

        Random randomSpawn = new Random();
        double randomSpawnX = randomSpawn.nextDouble(0.1, 4);
        double randomSpawnY = randomSpawn.nextDouble(0.1, 4);

        Entity enemyShip = new Enemy();
        enemyShip.setPolygonCoordinates(-8, 0, -4, 3, -6, 5, 0, 6, 4, 5, -1, 3, 12, 0, -1, -3, 4, -5, 0, -6, -6, -5, -4, -3);
        enemyShip.setX(gameData.getDisplayHeight() / randomSpawnX);
        enemyShip.setY(gameData.getDisplayWidth() / randomSpawnY);
        return enemyShip;
    }



    @Override
    public void process(GameData gameData, World world) {


        Random random = new Random();


        enemySpawnCount = enemySpawnCount + 1;
        if (enemySpawnCount >= enemySpawnTimer) {
            Entity enemy = createEnemyShip(gameData);
            world.addEntity(enemy);
            enemySpawnCount = 0;
        }



        for (Entity entity : world.getEntities(Enemy.class)) {

            Enemy enemy = (Enemy) entity;
            int curEnemyDuration = enemy.getModeDuration();
            int curEnemyTime = enemy.getModeTime();
            double curEnemyMovement = enemy.getRandomMovement();
            double curEnemyShooting = enemy.getRandomShooting();
            enemy.setRadius(6);
            if (enemy.getHit()) {
                enemy.setDead(true);
            }

            if (curEnemyTime <= curEnemyDuration) {
                if (!(curEnemyMovement <= 0.5 || curEnemyMovement >= 9.5)) {
                    double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
                    double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
                    enemy.setX(enemy.getX() + changeX);
                    enemy.setY(enemy.getY() + changeY);
                }
                if (curEnemyMovement <= 3) {
                    enemy.setRotation(enemy.getRotation() - 1.25);
                }
                if (curEnemyMovement >= 7) {
                    enemy.setRotation(enemy.getRotation() + 1.25);
                }
                if (curEnemyShooting <= 50) {

                    enemyReload = enemyReload + 1;
                    if (enemyReload >= curEnemyShooting - 25) {
                        //Logic for enemy shooting
                    }
                }
            }

            if (enemy.getX() < 0) {
                enemy.setX(1);
            }

            if (enemy.getX() > gameData.getDisplayWidth()) {
                enemy.setX(gameData.getDisplayWidth() - 1);
            }

            if (enemy.getY() < 0) {
                enemy.setY(1);
            }

            if (enemy.getY() > gameData.getDisplayHeight()) {
                enemy.setY(gameData.getDisplayHeight() - 1);
            }
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }


}
