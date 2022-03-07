package EncounterManager;

import java.util.Scanner;
import java.util.spi.AbstractResourceBundleProvider;

public class EncounterManager implements Runnable {
    private Tank tank;
    private DamagaDealer damagaDealer;
    private Healer healer;
    private EnemyEntity enemy;
    int counter = 1;

    public EncounterManager() {
        //  The following code is written for Extra Requirement 1

        this.tank = new Tank("Tank", 100, 10, 6);
        this.damagaDealer = new DamagaDealer("Damage Dealer", 100, 10, 7);
        this.healer = new Healer("Healer",  100, 10, 8);
        this.enemy = new EnemyEntity(100, 10);
    }


    public synchronized void tankAttack() throws InterruptedException {
        int damage = this.tank.dealDamage();
        this.enemy.takeDamage(damage);

        System.out.println( this.tank.getRole() + " attacked the enemy (" + damage +
                ")\nEntities’ HP" +
                "\nTank: " + this.tank.getHealthPoints() +
                "\nDamage Dealer: " + this.damagaDealer.getHealthPoints() +
                "\nHealer: " + this.healer.getHealthPoints()  +
                "\nEnemy: " + this.enemy.getHealthPoints() +
                "\n-------------------------------------"
        );

        wait(1000);
    }
    public synchronized void healerHeal() throws InterruptedException {
        int lowestHp = this.tank.getHealthPoints();
        String role = this.tank.getRole();


        if (lowestHp > this.healer.getHealthPoints()){
            lowestHp = this.healer.getHealthPoints();
            role = this.healer.getRole();
        }
        else if(lowestHp > this.damagaDealer.getHealthPoints()){
            lowestHp = damagaDealer.getHealthPoints();
            role = this.damagaDealer.getRole();

        }

        healPlayer(role);
        System.out.println(
                "Entities’ HP\n" +
                "Tank: " + this.tank.getHealthPoints() +
                "\nDamage Dealer: " + this.damagaDealer.getHealthPoints() +
                "\nHealer: " + this.healer.getHealthPoints() +
                "\nEnemy: " + this.enemy.getHealthPoints() +
                        "\n-------------------------------------"
        );
        wait(1000);
    }
    public synchronized void enemyTankAttack() throws InterruptedException {

        int damage = this.enemy.dealDamage();
        int given = damage - this.tank.getDefense();
        if(counter % 4 == 0){
            groupWideAttack();
            System.out.println(  "Enemy attacked the with group " +
                    "\nEntities’ HP" +
                    "\nTank: " + this.tank.getHealthPoints() +
                    "\nDamage Dealer: " + this.damagaDealer.getHealthPoints() +
                    "\nHealer: " + this.healer.getHealthPoints()  +
                    "\nEnemy: " + this.enemy.getHealthPoints() +
                    "\n-------------------------------------"
            );
        }else{
            this.tank.takeDamage(damage);
            counter++;
            System.out.println(  "Enemy attacked the Tank (" + given +
                    ")\nEntities’ HP" +
                    "\nTank: " + this.tank.getHealthPoints() +
                    "\nDamage Dealer: " + this.damagaDealer.getHealthPoints() +
                    "\nHealer: " + this.healer.getHealthPoints()  +
                    "\nEnemy: " + this.enemy.getHealthPoints() +
                    "\n-------------------------------------" + counter

            );
        }





        wait(1000);

    }

    public synchronized void damageDealerAttack() throws InterruptedException {
        int damage = this.damagaDealer.dealDamage();
        this.enemy.takeDamage(damage);

        System.out.println( this.damagaDealer.getRole() + " attacked the enemy " + damage +
                "\nEntities’ HP" +
                "\nTank: " + this.tank.getHealthPoints() +
                "\nDamage Dealer: " + this.damagaDealer.getHealthPoints() +
                "\nHealer: " + this.healer.getHealthPoints()  +
                "\nEnemy: " + this.enemy.getHealthPoints() +
                "\n-------------------------------------"
        );
        wait(500);
    }


    public void registerTank(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Health Points: ");
        int healthPoints = scanner.nextInt();

        System.out.print("Base Damage: ");
        int baseDamage = scanner.nextInt();

        System.out.print("Defense: ");
        int defense = scanner.nextInt();

        this.tank = new Tank("Tank", healthPoints, baseDamage, defense);
    }

    public void registerDamageDealer(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Health Points: ");
        int healthPoints = scanner.nextInt();

        System.out.print("Base Damage: ");
        int baseDamage = scanner.nextInt();

        System.out.print("Intelligence: ");
        int intelligence = scanner.nextInt();

        this.damagaDealer = new DamagaDealer("Damage Dealer", healthPoints, baseDamage, intelligence);
    }

    public void registerHealer(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Health Points: ");
        int healthPoints = scanner.nextInt();

        System.out.print("Base Damage: ");
        int baseDamage = scanner.nextInt();

        System.out.print("Mind: ");
        int mind = scanner.nextInt();

        this.healer = new Healer("Healer", healthPoints, baseDamage, mind);
    }

    public void spawnEnemy(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Health Points: ");
        int healthPoints = scanner.nextInt();

        System.out.print("Base Damage: ");
        int baseDamage = scanner.nextInt();

        this.enemy = new EnemyEntity(healthPoints, baseDamage);
    }

    public boolean enemyIsAlive(){
        return this.enemy.getHealthPoints() > 0;
    }

    public boolean playersAreAlive(){
        return this.tank.getHealthPoints() > 0 || this.damagaDealer.getHealthPoints() > 0 || this.healer.getHealthPoints() > 0;
    }

    public void groupWideAttack(){
        int damage = this.enemy.dealDamage();
        this.tank.takeDamage(damage);
        this.damagaDealer.takeDamage(damage);
        this.healer.takeDamage(damage);

        System.out.println("Enemy attacked all players ("+ damage +" damage attack)");
    }

    public void playerAttack(){
        System.out.println("\nSelect which player will attack: (t)ank, (d)amage dealer, (h)ealer");

        Scanner scanner = new Scanner(System.in);
        System.out.print(">");
        String choice = scanner.nextLine();
        Player p = null;
        int damage = 0;
        if(choice.equals("Tank")){
            damage = this.tank.dealDamage();
            p = this.tank;
        }
        else if(choice.equals("Damage Dealer")){
            damage = this.damagaDealer.dealDamage();
            p = this.damagaDealer;
        }
        else if(choice.equals("Healer")){
            damage = this.healer.dealDamage();
            p = this.healer;
        }
        else {
            return;
        }

        this.enemy.takeDamage(damage);
        System.out.println(p.getRole()+ " attacked the enemy (" + damage + " damage attack)");
    }

    public void enemyAttack(){
        System.out.println("\nSelect which player will be attacked: (t)ank, (d)amage dealer, (h)ealer");

        Scanner scanner = new Scanner(System.in);
        System.out.print(">");
        String choice = scanner.nextLine();
        Player p = null;
        int damage = this.enemy.dealDamage();
        if(choice.equals("t")){
            this.tank.takeDamage(damage);
            p = this.tank;
        }
        else if(choice.equals("d")){
            this.damagaDealer.takeDamage(damage);
            p = this.damagaDealer;
        }
        else if(choice.equals("h")){
            this.healer.takeDamage(damage);
            p = this.healer;
        }
        else {
            return;
        }

        System.out.println("The "+p.getRole()+" took damage from enemy (" + damage + " damage attack)");
    }

    public void healPlayer(String name){

        String choice = name;
        Player p = null;
        int healedValue = this.healer.heal();
        int plus100;

        int hp = 0;
        if(choice.equals("Tank")){
            hp = this.tank.getHealthPoints() + healedValue;
            p = this.tank;
            plus100 = 100 - this.tank.getHealthPoints();
            if(hp > 100){
                System.out.println("The "+p.getRole()+" was healed by "+  plus100 +" HP");
                hp = 100;

            }else{
                System.out.println("The "+p.getRole()+" was healed by "+  healedValue  +" HP");
            }
            this.tank.setHealthPoints(hp);


        }
        else if(choice.equals("Damage Dealer")){
            hp = this.damagaDealer.getHealthPoints() + healedValue;
            p = this.damagaDealer;
            plus100 = 100 - this.damagaDealer.getHealthPoints();
            if(hp > 100){
                System.out.println("The "+p.getRole()+" was healed by "+  plus100 +" HP");
                hp = 100;
            }else{
                System.out.println("The "+p.getRole()+" was healed by "+  healedValue  +" HP");
            }

            this.damagaDealer.setHealthPoints(hp);

        }
        else if(choice.equals("Healer")){
            hp = this.healer.getHealthPoints() + healedValue;
            p = this.healer;
            plus100 = 100 - this.healer.getHealthPoints();
            if(hp > 100){
                System.out.println("The "+p.getRole()+" was healed by "+  plus100 +" HP");
                hp = 100;
            }else{
                System.out.println("The "+p.getRole()+" was healed by "+  healedValue  +" HP");
            }

            this.healer.setHealthPoints(hp);

        }
        else {
            return;
        }



    }

    @Override
    public void run() {
        while (playersAreAlive() && enemyIsAlive()){
            try {
                tankAttack();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                enemyTankAttack();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                healerHeal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                damageDealerAttack();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
