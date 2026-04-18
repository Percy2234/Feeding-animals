import java.util.ArrayList;

public class Project {
    public static void main(String[] args) {
        Duck duck = new Duck("Hyun", 50, true);
        Kangaroo kangaroo = new Kangaroo("Kim", 70, 3);
        FeedingStation station = new FeedingStation("Seoul Feeding Station", 20);

        station.addAnimal(duck);
        station.addAnimal(kangaroo);

        System.out.println("=== Animals in the Park ===");
        System.out.println();
        station.showAnimals();
        System.out.println("\nChoose an animal to feed:");
        System.out.println("1. Duck");
        System.out.println("2. Kangaroo");
        int choice = In.nextInt();

        if (choice == 1) {
            if (station.getFood() >= 1) {
                station.feedAnimal(1);
                duck.eatFood(1);
                System.out.println("Duck is eating the food. \nDuck Energy increased (+1)");
            } else {
                System.out.println("Not enough food.");
            }
        } else if (choice == 2) {
            if (station.getFood() >= 1) {
                station.feedAnimal(1);
                kangaroo.eatFood(1);
                System.out.println("Kangaroo is eating the food. \nKangaroo Energy increased (+1)");
            } else {
                System.out.println("Not enough food.");
            }
        } else {
            System.out.println("Invalid choice.");
            return;
        }

        System.out.println("\nWould you like to top up more food?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int refillChoice = In.nextInt();

        if (refillChoice == 1) {
            System.out.println("\nHow much food would you like to add?");
            int amount = In.nextInt();
            station.refillFood(amount);
        } else {
            System.out.println("No extra food was added.");
        }

        System.out.println("\n=== Animals After Feeding ===");
        System.out.println();
        station.showAnimals();

        System.out.println("\n=== Animals Moving ===");
        for (Wildlife animal : station.getAnimals()) {
            animal.move();
            System.out.println(animal);
        }

        System.out.println(station);
    }
}

class Wildlife {
    private String name;
    private int energy;

    public Wildlife(String name, int energy) {
        this.name = name;
        this.energy = energy;
    }

    public String getName() {
        return this.name;
    }

    public int getEnergy() {
        return this.energy;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void eatFood(int amount) {
        this.energy = this.energy + amount;
    }

    public void move() {
        this.energy = this.energy - 3;
        System.out.println(this.name + " moves.");
    }

    public String getStatus() {
        return this.name + " has energy " + this.energy + ".";
    }

    public String toString() {
        return getStatus();
    }
}

class Duck extends Wildlife {
    private boolean canSwim;

    public Duck(String name, int energy, boolean canSwim) {
        super(name, energy);
        this.canSwim = canSwim;
    }

    public boolean getCanSwim() {
        return this.canSwim;
    }

    public void setCanSwim(boolean canSwim) {
        this.canSwim = canSwim;
    }

    @Override
    public void move() {
        setEnergy(getEnergy() - 3);
        System.out.println();
        System.out.println(getName() + " is happy. He use Energy to waddles.");
    }

    @Override
    public String getStatus() {
        return getName() + " is a duck. Energy: " + getEnergy() + ", canSwim: " + canSwim;
    }

    public String toString() {
        return getStatus();
    }
}

class Kangaroo extends Wildlife {
    private int jumpHeight;

    public Kangaroo(String name, int energy, int jumpHeight) {
        super(name, energy);
        this.jumpHeight = jumpHeight;
    }

    public int getJumpHeight() {
        return this.jumpHeight;
    }

    public void setJumpHeight(int jumpHeight) {
        this.jumpHeight = jumpHeight;
    }

    @Override
    public void move() {
        setEnergy(getEnergy() - 5);
        this.jumpHeight = this.jumpHeight + 1;
        System.out.println();
        System.out.println(getName() + " is happy. He use Energy to bounce. ");
    }

    @Override
    public String getStatus() {
        return getName() + " is a kangaroo. Energy: " + getEnergy() + ", jumpHeight: " + jumpHeight;
    }

    public String toString() {
        return getStatus();
    }
}

class FeedingStation {
    private String name;
    private int food;
    private ArrayList<Wildlife> animals;

    public FeedingStation(String name, int food) {
        this.name = name;
        this.food = food;
        this.animals = new ArrayList<Wildlife>();
    }

    public String getName() {
        return this.name;
    }

    public int getFood() {
        return this.food;
    }

    public ArrayList<Wildlife> getAnimals() {
        return this.animals;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public void addAnimal(Wildlife animal) {
        this.animals.add(animal);
    }

    public void feedAnimal(int amount) {
        this.food = this.food - amount;
    }

    public void refillFood(int amount) {
        this.food = this.food + amount;
        System.out.println(amount + " food was added.");
    }

    public void showAnimals() {
        for (Wildlife animal : this.animals) {
            System.out.println(animal);
        }
    }

    public int getCount() {
        return this.animals.size();
    }

    public String toString() {
        return "\nFeedingStation: " + this.name + ", food left: " + this.food;

    }
}