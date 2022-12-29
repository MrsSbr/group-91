package models;

import java.time.LocalTime;

public class HereticNote {
    private final String nameSuspect;
    private final String sufferTool;
    private final LocalTime sufferTime;
    private final boolean isConfession;

    public HereticNote(String nameSuspect, String sufferTool, String sufferTime, String isConfession) {
        this.nameSuspect = nameSuspect;
        this.sufferTool = sufferTool;
        this.sufferTime = LocalTime.parse(sufferTime);
        this.isConfession = Boolean.parseBoolean(isConfession);
    }

    public String getNameSuspect() {
        return nameSuspect;
    }

    public String getSufferTool() {
        return sufferTool;
    }

    public LocalTime getSufferTime() {
        return sufferTime;
    }

    public boolean getIsConfession() {
        return isConfession;
    }

    @Override
    public String toString() {
        String info = "Имя подозреваемого: " + nameSuspect
                + "\nНазвание инструмента для пыток: " + sufferTool
                + "\nВремя пытки" + sufferTime
                + "\nФаталити: ";
        return isConfession ? info + "Нет" : info + "Да";
    }
}
