import dk.sdu.mmmi.cbse.asteroids.AsteroidControlSystem;
import dk.sdu.mmmi.cbse.common.services.*;
import dk.sdu.mmmi.cbse.asteroids.AsteroidsPlugin;

module Asteroids {
    requires Common;
    requires CommonAsteroid;
    provides IGamePluginService with AsteroidsPlugin;
    provides IEntityProcessingService with AsteroidControlSystem;

}