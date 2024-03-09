package Lesson11.livelock;

public class Livelock {

    static class Spoon{
        private Diner owner;
        public Spoon(Diner owner){
            this.owner = owner;
        }

        public Diner getOwner() {
            return owner;
        }

        public synchronized void setOwner(Diner owner) {
            this.owner = owner;
        }

        public synchronized void use(){
            System.out.printf("%s has eaten", owner.name);
        }

    }
    static class Diner{
        private String name;
        private boolean isHungry;
        public Diner(String name){
            this.name = name;
            isHungry = true;
        }

        public String getName() {
            return name;
        }

        public boolean isHungry() {
            return isHungry;
        }

        public void eatWith(Spoon spoon, Diner wife){
            while (isHungry){
                if(spoon.getOwner() != this){
                    try {
                        Thread.sleep(1);
                    }catch (InterruptedException e){
//                        System.out.println(e.getCause());
                        continue;
                    }
                    continue;
                }
                if (wife.isHungry()){
                    System.out.printf("%s: You eat first my darling %s!%n", name, wife.getName());
                    spoon.setOwner(wife);
                    continue;
                }
                spoon.use();
                isHungry = false;
                System.out.printf("%s: I'm stuffed, my darling %s!%n", name, wife.getName());
                spoon.setOwner(wife);
            }
        }
    }

    public static void main(String[] args) {
        final Diner husband = new Diner("Revan");
        final Diner wife = new Diner("Ravana");

        final Spoon s = new Spoon(husband);

        Thread husbandThread = new Thread(() -> husband.eatWith(s, wife));
        husbandThread.start();

        Thread wifeThread = new Thread(() -> wife.eatWith(s, husband));
        wifeThread.start();

    }
}
