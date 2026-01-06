package Project1;
import java.util.Random;
import java.util.Scanner;

class Main {
    static final int HAND_SIZE = 5;
    static final String teamName1 = "ABC";
    static final String teamName2 = "DEF";
    static Random random = new Random();
    static Scanner scanner = new Scanner(System.in);
    static Warrior warrior = new Warrior();
    static Archer archer = new Archer();
    static Bruiser bruiser = new Bruiser();
    static Mage mage = new Mage();
    static int wins1;
    static int wins2;

    public static void main(String[] args) {

        //Создадим игрокам колоды
        Hero[] cardsTeam1 = createCards();
        Hero[] cardsTeam2 = createCards();

        //Выведем на экран
        showTeam(cardsTeam1, wins1, teamName1);
        showTeam(cardsTeam2, wins2, teamName2);


        for (int i = 0; i < HAND_SIZE; i++) {
            //Выбрать карты для дуэли 1-1
            System.out.printf("\nChoose character from team %s and character from team %s", teamName1, teamName2);
            int a = scanner.nextInt();
            int b = scanner.nextInt();
//            duel(cardsTeam1, cardsTeam2, a, b);
            switch (a) {
                case 1:
                    System.out.printf("\n%s from team %s VS ", warrior.getName(), teamName1);
                    break;
                case 2:
                    System.out.printf("\n%s from team %s VS ", archer.getName(), teamName1);
                    break;
                case 3:
                    System.out.printf("\n%s from team %s VS ", bruiser.getName(), teamName1);
                    break;
                case 4:
                    System.out.printf("\n%s from team %s VS ", mage.getName(), teamName1);
                    break;
            }
            switch (b) {
                case 1:
                    System.out.printf("%s from team %s\n", warrior.getName(), teamName2);
                    break;
                case 2:
                    System.out.printf("%s from team %s\n", archer.getName(), teamName2);
                    break;
                case 3:
                    System.out.printf("%s from team %s\n", bruiser.getName(), teamName2);
                    break;
                case 4:
                    System.out.printf("%s from team %s\n", mage.getName(), teamName2);
                    break;
            }
            cardsTeam1 = removeCard(cardsTeam1, a);
            cardsTeam2 = removeCard(cardsTeam2, b);
            duel(a, b);
            showTeam(cardsTeam1, wins1, teamName1);
            showTeam(cardsTeam2, wins2, teamName2);
        }
        if (wins1 > wins2) System.out.println("Game is won by team 1. \nCongrats.");
        else System.out.println("Game is won by team 2. \nCongrats.");
    }

    public static Hero[] createCards() {
        Hero[] cards = new Hero[HAND_SIZE];
        for (int i = 0; i < HAND_SIZE; i++) {
            int a = random.nextInt(1, 5);
            switch (a) {
                case 1:
                    cards[i] = (warrior);
                    break;
                case 2:
                    cards[i] = (archer);
                    break;
                case 3:
                    cards[i] = (bruiser);
                    break;
                case 4:
                    cards[i] = mage;
                    break;
            }
        }
        return cards;
    }

    public static void showTeam(Hero[] cards, int wins, String team) {
        String winsString = "";
        winsString = switch (wins) {
            case 1 -> ("+");
            case 2 -> ("++");
            case 3 -> ("+++");
            case 4 -> ("++++");
            case 5 -> ("+++++");
            default -> winsString;
        };
        System.out.printf("\nTeam %s characters %s\n", team, winsString);
        for (Hero card : cards) {
            System.out.printf("|%d%10s|\t", card.getId(), card.getName());
        }

    }

    public static void duel(int char1, int char2) {
        int hp1 = 0;
        int hp2 = 0;
        int attack1 = 0;
        int attack2 = 0;
        hp1 = getHp(char1, hp1);
        hp2 = getHp(char2, hp2);
        for (int i = 0; i < 100; i++) {
            attack1 = getAttack(char1, attack1);
            attack2 = getAttack(char2, attack2);
            hp1 = hp1 - attack2;
            hp2 = hp2 - attack1;
            System.out.printf("%d (-%d)   -   %d (-%d)\n", hp1, attack2, hp2, attack1);
            if (hp1 <= 0 || hp2 <= 0) break;
        }
        if (hp1 >= hp2) {
            System.out.println("Team 1 won");
            wins1++;
        } else {
            System.out.println("Team 2 won");
            wins2++;
        }
    }

    public static int getHp(int char1, int hp) {
        switch (char1) {
            case 1:
                hp = warrior.getHp();
                break;
            case 2:
                hp = archer.getHp();
                break;
            case 3:
                hp = bruiser.getHp();
                break;
            case 4:
                hp = mage.getHp();
                break;
        }
        return hp;
    }

    public static int getAttack(int char1, int attack) {
        switch (char1) {
            case 1:
                attack = warrior.getAttackWarrior();
                break;
            case 2:
                attack = archer.getAttackArcher();
                break;
            case 3:
                attack = bruiser.getAttackBruiser();
                break;
            case 4:
                attack = mage.getAttackMage();
                break;
        }
        return attack;
    }

    static Hero[] removeCard(Hero[] cards, int card) {
        int index = 0;
        for (int i = 0; i < cards.length; i++) {
            if (cards[i].getId() == card) {
                index = i;
                break;
            } else index = Integer.MAX_VALUE;
        }
        Hero[] remainingCards = new Hero[cards.length - 1];
        for (int i = 0; i < cards.length; i++) {
            if (i != index) {
                int newIndex = i < index ? i : i - 1;
                remainingCards[newIndex] = cards[i];
            }
        }
        return remainingCards;
    }
}

 class Hero {
    static Random random = new Random();
     int id;
     String name;
     int hp;
     int attackWarrior;
     int attackArcher;
     int attackBruiser;
     int attackMage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttackWarrior() {
        return attackWarrior= random.nextInt(1,4);
    }
    public int getAttackArcher() {
        return attackArcher=random.nextInt(3,5);
    }
    public int getAttackBruiser() {
        return attackBruiser= random.nextInt(1,3);
    }
    public int getAttackMage() {
        return attackMage= random.nextInt(3,7);
    }
}

 class Warrior extends Hero {
    public Warrior() {
        setId(1);
        setName("Warrior");
        setHp(50);
    }
}

 class Archer extends Hero {
    public Archer() {
        setId(2);
        setName("Archer");
        setHp(45);
    }
}

 class Bruiser extends Hero {
    public Bruiser() {
        setId(3);
        setName("Bruiser");
        setHp(65);
    }
}

 class Mage extends Hero {
    public Mage() {
        setId(4);
        setName("Mage");
        setHp(40);
    }
}