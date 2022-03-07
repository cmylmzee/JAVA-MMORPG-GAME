package EncounterManager;

public class Tank extends Player {
    private int defense;

    public Tank(String role, int healthPoints, int baseDamage, int defense) {
        super(role, healthPoints, baseDamage);
        this.defense = defense;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    @Override
    public int dealDamage() {
        if (this.getHealthPoints()>0){
            return getBaseDamage();
        }
        return 0;
    }

    @Override
    public void takeDamage(int damageReceived) {
        if(getHealthPoints()> (damageReceived - getDefense())){
            this.setHealthPoints(this.getHealthPoints() - (damageReceived - getDefense()));
        }else{
            this.setHealthPoints(0);
        }

    }


}
