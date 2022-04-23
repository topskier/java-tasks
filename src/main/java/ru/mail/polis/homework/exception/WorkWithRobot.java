package ru.mail.polis.homework.exception;

import java.util.HashMap;

public class WorkWithRobot {

    public HashMap<Integer, Robot> getRobots() {
        HashMap<Integer, Robot> robotsMap = new HashMap<>();
        Robot robot1 = new Robot(1);
        Robot robot2 = new Robot(2);
        Robot robot3 = new Robot(3);
        robotsMap.put(robot1.getId(), robot1);
        robotsMap.put(robot2.getId(), robot2);
        robotsMap.put(robot3.getId(), robot3);
        return robotsMap;
    }
}
