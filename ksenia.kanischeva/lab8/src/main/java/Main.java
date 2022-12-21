public class Main {
    public static void main(String[] args) {
        Reception reception = new Reception();
        Thread barber = new Barber(reception);
        barber.start();

        for (int i = 0; i < 30; i++) {
            Thread client = new Client(reception, "client" + (i + 1));
            client.start();
        }


    }
}
