package EncounterManager;

public class Healer extends Player {
    private int mind;

    public Healer(String role, int healthPoints, int baseDamage, int mind) {
        super(role, healthPoints, baseDamage);
        this.mind = mind;
    }

    public int getMind() {
        return mind;
    }

    public void setMind(int mind) {
        this.mind = mind;
    }

    public int heal() {
        if (getHealthPoints() > 0) {
            return this.mind + 10;
        }
        else return 0;
    }

    @Override
    public int dealDamage() {
        if( getHealthPoints() > 0 ){
            return getBaseDamage();
        }
        return 0;
    }

    @Override
    public void takeDamage(int damageReceived) {
        if(this.getHealthPoints() > damageReceived){
            this.setHealthPoints(this.getHealthPoints() - damageReceived);
        }else{
            this.setHealthPoints(0);
        }

    }
}
