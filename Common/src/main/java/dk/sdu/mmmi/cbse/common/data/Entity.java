package dk.sdu.mmmi.cbse.common.data;

import java.io.Serializable;
import java.util.UUID;

public class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();
    
    private double[] polygonCoordinates;
    private double x;
    private double y;
    private double rotation;

    private float radius;

    private boolean isHit = false;

    private boolean dead = false;

    public String getID() {
        return ID.toString();
    }


    public void setPolygonCoordinates(double... coordinates ) {
        this.polygonCoordinates = coordinates;
    }

    public double[] getPolygonCoordinates() {
        return polygonCoordinates;
    }
       

    public void setX(double x) {
        this.x =x;
    }

    public double getX() {
        return x;
    }

    
    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getRotation() {
        return rotation;
    }
    public void setRadius(float radius) {
        this.radius = radius;
    }
    public float getRadius() {
        return this.radius;
    }
    public void setDead(boolean dead){
        this.dead = dead;
    }

    public boolean getDead(){
        return this.dead;
    }

    public void setHit(boolean isHit){
        this.isHit = isHit;
    }

    public boolean getHit(){
        return this.isHit;
    }
}
