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

        when(entity1.getX()).thenReturn(0.0);
        when(entity1.getY()).thenReturn(0.0);
        when(entity1.getRadius()).thenReturn(12f);

        when(entity2.getX()).thenReturn(0.0);
        when(entity2.getY()).thenReturn(0.0);
        when(entity2.getRadius()).thenReturn(12f);

        boolean result = collisionDetector.collides(entity1, entity2);
        assertTrue(result, "Expected collision not detected");

    }
    @Test
    void testShouldNotHit() {


        when(entity1.getX()).thenReturn(0.0);
        when(entity1.getY()).thenReturn(100.0);
        when(entity1.getRadius()).thenReturn(12f);

        when(entity2.getX()).thenReturn(100.0);
        when(entity2.getY()).thenReturn(0.0);
        when(entity2.getRadius()).thenReturn(12f);

        boolean result = collisionDetector.collides(entity1, entity2);
        assertFalse(result, "Unexpected collision occurred");
    }
}
