package splitpackage;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class Helloworld implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        System.out.println("IM THE ASTEROID");
    }
}
