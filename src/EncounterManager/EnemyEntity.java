package EncounterManager;

public class EnemyEntity implements PotencyCalculator {

    private static int ENTITY_ID_COUNTER = 999;

    private int entityID;
    private int healthPoints;
    private int baseDamage;

    public EnemyEntity(int healthPoints, int baseDamage) {
        this.healthPoints = healthPoints;
        this.baseDamage = baseDamage;

        this.entityID = ENTITY_ID_COUNTER++;
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

    @Override
    public int dealDamage() {
        if(this.getHealthPoints()>0){
            return getBaseDamage();
        }
        return 0;
    }

    @Override
    public void takeDamage(int damageReceived) {
        if(this.getHealthPoints() > damageReceived) {
            healthPoints = healthPoints - damageReceived;
        }else{
            this.setHealthPoints(0);
        }
    }
}
