
package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.RobotMap;

public class RobotManager {

    public static boolean isCompetitionRobot() {
        DigitalInput competitionRobotDIO = new DigitalInput(RobotMap.COMPETITION_ROBOT_DIO_ID);
        boolean isSet = competitionRobotDIO.get();
        competitionRobotDIO.close();
        return !isSet;
    }

    public static boolean isPracticeRobot() {
        DigitalInput practiceRobotDIO = new DigitalInput(RobotMap.PRACTICE_ROBOT_DIO_ID);
        boolean isSet = practiceRobotDIO.get();
        practiceRobotDIO.close();
        return !isSet;
    }
}