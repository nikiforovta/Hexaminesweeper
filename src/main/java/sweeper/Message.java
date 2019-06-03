package sweeper;

public class Message {
    public final static String GREETINGS = "Введите размеры поля и количество мин через пробел. \n" +
            "*количество строк* *количество рядов* *количество мин*";
    public final static String RETARD = "Вы что-то ввели не так, повторите ввод. \n" +
            "*количество строк* *количество рядов* *количество мин*";
    public final static String MINERETARD = "Вы ввели слишком большое количество мин, " +
            "количество мин изменено до максимально возможного.";
    public final static String PLAYED = "Think twice!";
    public final static String BOMBED = "YOU LOSE!";
    public final static String WINNER = "Congratulations!";
    public final static String START = "Welcome!";
}
