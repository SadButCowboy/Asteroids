import dk.sdu.mmmi.cbse.EnemyControlSystem;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

module Enemy {
    requires Common;
    requires CommonBullet;
    provides IEntityProcessingService with EnemyControlSystem;


}


