package dk.sdu.mmmi.cbse.collision;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.mockito.Mockito.*;



public class CollisionTest {
    CollisionDetector collisionDetector;
    GameData gameData;
    World world;
    Entity entity1;
    Entity entity2;
        @BeforeEach
        void setUp() {
            collisionDetector = new CollisionDetector();
            gameData = mock(GameData.class);
            world = mock(World.class);
            entity1 = mock(Entity.class);
            entity2 = mock(testEntity.class);
        }
    @Test
    void testShouldHit() {

        Entity entity1 = new Entity();
        Entity entity2 = new Entity();

        entity1.setX(0);
        entity1.setY(0);
        entity1.setRadius(12);

        entity2.setX(0);
        entity2.setY(0);
        entity2.setRadius(12);

        boolean result = collisionDetector.collides(entity1, entity2);
        assertTrue(result, "Expected collision not detected");

    }
    @Test
    void testShouldNotHit() {

        Entity entity1 = new Entity();
        Entity entity2 = new Entity();

        entity1.setX(100);
        entity1.setY(0);
        entity1.setRadius(12);

        entity2.setX(0);
        entity2.setY(100);
        entity2.setRadius(12);

        boolean result = collisionDetector.collides(entity1, entity2);
        assertFalse(result, "Unexpected collision occurred");
    }
}
