package EncounterManager;

public class DamagaDealer extends Player {
    private int intelligence;

    public DamagaDealer(String role, int healthPoints, int baseDamage, int intelligence) {
        super(role, healthPoints, baseDamage);
        this.intelligence = intelligence;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    @Override
    public int dealDamage() {
        if(this.getHealthPoints()>0){
            return getBaseDamage() + intelligence;
        }
        return 0;
    }

    @Override
    public void takeDamage(int damageReceived) {
        if(this.getHealthPoints() > damageReceived){
            this.setHealthPoints(this.getHealthPoints() - damageReceived);
        }else {
            this.setHealthPoints(0);
        }

    }


}
