package dk.sdu.mmmi.cbse.commonasteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;


public interface AsteroidSPI {
    void createSplittedAsteroid(Entity e, World w);
}

