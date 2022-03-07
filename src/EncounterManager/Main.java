package EncounterManager;

public class Main {

    public static void main(String[] args) {
        EncounterManager encounterManager = new EncounterManager();

        DamagaDealer damagaDealer = new DamagaDealer("DamageDealer", 100, 10, 10);
        damagaDealer.getRole();
        Thread islem1 = new Thread(encounterManager);
        islem1.start();

        }
    }

