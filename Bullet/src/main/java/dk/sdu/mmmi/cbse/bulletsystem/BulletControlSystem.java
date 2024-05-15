package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

    private final double moveSpeed = 2.8;
    @Override
    public void process(GameData gameData, World world) {

        for (Entity bullet : world.getEntities(Bullet.class)) {
            double changeX = Math.cos(Math.toRadians(bullet.getRotation()));
            double changeY = Math.sin(Math.toRadians(bullet.getRotation()));
            bullet.setX(bullet.getX() + changeX*moveSpeed);
            bullet.setY(bullet.getY() + changeY*moveSpeed);

        }
    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Entity gameBullet = new Bullet();
        gameBullet.setPolygonCoordinates(3,4, 8,0, 3,-4, -6,0);
        gameBullet.setX(shooter.getX());
        gameBullet.setY(shooter.getY());
        gameBullet.setRotation(shooter.getRotation());
        return gameBullet;
    }

    private void setShape(Entity entity) {
    }

}
