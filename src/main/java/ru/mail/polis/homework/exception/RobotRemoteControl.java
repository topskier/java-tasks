package ru.mail.polis.homework.exception;

/**
 * Задание: Нужно создать свою мини библиотеку, с удаленным роботом и пультом управления.
 * Каждый класс оценивается отдельно
 * <p>
 * Пункт управления роботами. Через него управляются все роботы
 * <p>
 * 4 тугрика
 */
public class RobotRemoteControl {

    private RobotConnectionManager connectionManager;

    /**
     * Метод должен открыть соединение и отправить робота в указанную точку. При неудаче - повторить действие еще 2 раза,
     * Если это не удалось, то прокинуть эту ошибку на уровень выше.
     * Попытка считается успешной, если соединение открылось и вызвался метод moveRobotTo без исключений.
     */
    public void moveTo(int robotId, int toX, int toY) throws RobotConnectionException {
        byte cnt = 0;
        while (cnt != 2) {
            try {
                connectionManager.getConnection(robotId).moveRobotTo(toX, toY);
                break;
            } catch (RobotConnectionException | NullPointerException e) {
                if (cnt == 2) {
                    throw e;
                }
            }
            cnt++;
        }
    }
}