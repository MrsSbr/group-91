import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Reception {
    private static final int CAPACITY = 5;
    private boolean isOpen = true;
    private boolean barberJob = false;
    private final Object monitor = new Object();
    private final BlockingQueue<String> queue = new ArrayBlockingQueue<>(CAPACITY, true);

    public void isOpen(boolean state) {
        isOpen = state;
    }

    public void add(String name) {
        if (queue.size() == CAPACITY || !isOpen) {
            System.out.println("Клиент " + name + " уходит");
        } else {
            if (queue.size() < CAPACITY) {
                try {
                    queue.put(name);
                    synchronized (monitor) {
                        if (queue.size() == 1 && !barberJob) {
                            System.out.println("Клиент " + name + " будит барбера");
                            monitor.notifyAll();
                        } else {
                            System.out.println("Клиент " + name + " ожидает");
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String get() throws InterruptedException {
        synchronized (monitor) {
            while (queue.size() == 0) {
                System.out.println("Клиентов нет. Барбер спит\n-----------------------");
                barberJob = false;
                monitor.wait();
            }
            if (barberJob) {
                System.out.println("Барбер идет в приемную за клиентом");
            }
            barberJob = true;
            String name = queue.take();

            System.out.println("Барбер пригласил клиента " + name);
            return name;
        }
    }
}
