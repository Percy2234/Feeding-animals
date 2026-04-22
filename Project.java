import java.util.ArrayList;

public class Project {
    public static void main(String[] args) {
        Duck duck = new Duck("Hyun", 50, true);
        Kangaroo kangaroo = new Kangaroo("Kim", 70, 3);
        FeedingStation station = new FeedingStation(20);

        station.addAnimal(duck);
        station.addAnimal(kangaroo);

        System.out.println("=== Animals in the Park ===");
        station.showAnimals();

        System.out.println("\nChoose an animal to feed:");
        System.out.println("1. Duck");
        System.out.println("2. Kangaroo");
        int choice = In.nextInt();

        if (choice == 1) {
            station.feedAnimal(duck, 1);
        } else if (choice == 2) {
            station.feedAnimal(kangaroo, 1);
        } else {
            System.out.println("Invalid choice.");
            return;
        }

        System.out.println("\nWould you like to top up more food?");
        System.out.println("Your remaining food: " + station.getFood());
        System.out.println("1. Yes");
        System.out.println("2. No");
        int refillChoice = In.nextInt();

        if (refillChoice == 1) {
            System.out.println("\nHow much food would you like to add?");
            int amount = In.nextInt();
            station.refillFood(amount);
        } else if (refillChoice == 2) {
            System.out.println("No extra food was added.");
        } else {
            System.out.println("Invalid choice");
            return;
        }

        System.out.println("\n=== Animals After Feeding ===");
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

    @Override
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
        if (canSwim) {
            System.out.println(getName() + " uses energy to swim.");
        } else {
            System.out.println(getName() + " uses energy to waddle.");
        }
    }

    @Override
    public String getStatus() {
        return getName() + " is a duck. Energy: " + getEnergy() + ", canSwim: " + canSwim;
    }

    @Override
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
        System.out.println(getName() + " uses energy to bounce.");
    }

    @Override
    public String getStatus() {
        return getName() + " is a kangaroo. Energy: " + getEnergy() + ", jumpHeight: " + jumpHeight;
    }

    @Override
    public String toString() {
        return getStatus();
    }
}

class FeedingStation {
    private int food;
    private ArrayList<Wildlife> animals;

    public FeedingStation(int food) {
        this.food = food;
        this.animals = new ArrayList<Wildlife>();
    }

    public int getFood() {
        return this.food;
    }

    public ArrayList<Wildlife> getAnimals() {
        return this.animals;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public void addAnimal(Wildlife animal) {
        this.animals.add(animal);
    }

    public void feedAnimal(Wildlife animal, int amount) {
        if (this.food >= amount) {
            this.food -= amount;
            animal.eatFood(amount);

            System.out.println(animal.getName() + " is eating the food.");
            System.out.println(animal.getName() + "'s energy increased (+" + amount + ")");
        } else {
            System.out.println("Not enough food.");
        }
    }

    public void refillFood(int amount) {
        this.food += amount;
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
        return "\nFeedingStation: food left: " + this.food;
    }
}