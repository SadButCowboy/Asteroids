package dk.sdu.mmmi.cbse.commonasteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;

public class Asteroid extends Entity {

    private int timesSplit = 0;

    public int getTimesSplit() {
        return timesSplit;
    }

    public void setTimesSplit(int timesSplit) {
        this.timesSplit = timesSplit;
    }
}
