public class Barber extends Thread {
    private static final int COUNT_CLIENTS = 20;
    private final Reception reception;

    public Barber(Reception reception) {
        this.reception = reception;
    }

    @Override
    public void run() {
        for (int i = 1; i <= COUNT_CLIENTS; i++) {
            try {
                String client = reception.get();
                System.out.println("Барбер стрижет " + client);
                System.out.println("Стрижка завершена\n-----------------------");
                if (i == COUNT_CLIENTS){
                    reception.isOpen(false);
                    System.out.println("Рабочий день окончен. Ждите до завтра!!!");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
