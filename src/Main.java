import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType;
    public static int[] heroesHealth = {280, 270, 250, 250};
    public static int[] heroesDamage = {10, 15, 20, 0};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Doctor"};
    public static int roundCounter;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameOver()) {
            round();
        }

    }

    public static void round() {
        if (bossHealth > 0) {
            changeDefenceType();
            bossAttack();
            DoctorHealth();

        }
        heroesAttack();
        printStatistics();


    }

    public static void changeDefenceType() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss choose " + bossDefenceType);
    }

    public static void printStatistics() {
        System.out.println("-----------------------------------------------------------");
        System.out.println("Round: " + roundCounter);
        roundCounter++;
        System.out.println("BossHealth: " + bossHealth);
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + " " + heroesDamage[i] + " ");
        }
        System.out.println("-----------------------------------------------------------");
    }

    public static void bossAttack() {
        for (int i = 0; i < heroesAttackType.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void DoctorHealth() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] >= 100) {
                if (heroesHealth[i] < 100) {
                    heroesHealth[i] = 0;

                } else {
                    heroesHealth[i] = heroesHealth[i] + 50;

                    System.out.println("Hp herous " + heroesHealth[i]);
                }
            }

        }

    }

    public static void heroesAttack() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int damage = heroesDamage[i];
                if (heroesAttackType[i] == bossDefenceType) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2;
                    damage = heroesDamage[i] * coeff;
                    System.out.println("Critical damage: " + damage);
                }
                if (bossHealth - damage < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - damage;
                }
            }
        }
    }

    public static boolean isGameOver() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }

        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }

        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

}










