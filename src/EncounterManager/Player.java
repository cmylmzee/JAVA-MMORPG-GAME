package EncounterManager;

public abstract class Player implements PotencyCalculator {

    private static int ENTITY_ID_COUNTER = 1;

    private String role;
    private int entityID;
    private int healthPoints;
    private int baseDamage;

    public Player(String role, int healthPoints, int baseDamage) {
        this.role = role;
        this.healthPoints = healthPoints;
        this.baseDamage = baseDamage;

        this.entityID = ENTITY_ID_COUNTER++;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getEntityID() {
        return entityID;
    }

    public void setEntityID(int entityID) {
        this.entityID = entityID;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }
}
