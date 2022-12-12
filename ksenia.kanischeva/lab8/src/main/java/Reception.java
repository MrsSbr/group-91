import java.util.ArrayList;
import java.util.List;

public class Reception {
    private static final int CAPACITY = 5;
    private boolean isOpen = true;
    private boolean barberJob = false;
    private final List<String> places = new ArrayList<>();

    public void isOpen(boolean state) {
        isOpen = state;
    }

    public void add(String name) {
        synchronized (this) {
            if (places.size() == CAPACITY || !isOpen) {
                System.out.println("Клиент " + name + " уходит");
            } else if (places.size() < CAPACITY) {
                places.add(name);
                if (places.size() == 1 && !barberJob) {
                    System.out.println("Клиент " + name + " будит барбера");
                    this.notifyAll();
                } else {
                    System.out.println("Клиент " + name + " ожидает");
                }
            }

        }
    }

    public String get() throws InterruptedException {
        synchronized (this) {
            while (places.size() == 0) {
                System.out.println("Клиентов нет. Барбер спит\n-----------------------");
                barberJob = false;
                this.wait();
            }
            if (barberJob){
                System.out.println("Барбер идет в приемную за клиентом");
            }
            barberJob = true;
            String name = places.get(0);
            places.remove(0);

            System.out.println("Барбер пригласил клиента " + name);
            return name;
        }
    }
}
