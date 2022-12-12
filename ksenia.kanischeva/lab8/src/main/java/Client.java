public class Client extends Thread {

    String name;
    private final Reception reception;

    public Client(Reception reception, String name){
        this.name = name;
        this.reception = reception;
    }

    @Override
    public void run() {
        System.out.println("Клиент " + name + " пришел на стрижку");
        reception.add(name);
    }
}
