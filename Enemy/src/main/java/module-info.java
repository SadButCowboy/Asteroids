import dk.sdu.mmmi.cbse.EnemyControlSystem;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import splitpackage.Helloworld;

module Enemy {
    requires Common;
    requires CommonBullet;
    provides IEntityProcessingService with EnemyControlSystem;
    provides IPostEntityProcessingService with Helloworld;


}


