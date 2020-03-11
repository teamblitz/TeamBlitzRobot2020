
/* 
***** DO NOT USE CODE HERE *****
*/


/*import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 *
 
public class DriveTrain extends SubsystemBase {

    Victor leftMotor = new Victor(RobotMap.DRIVETRAIN_VICTOR_LEFT);
    Victor rightMotor = new Victor(RobotMap.DRIVETRAIN_VICTOR_RIGHT);

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new Command());
    }
	
	public void setMotors(double left, double right) {
    	left = scaleLeft(left);
    	right = scaleRight(right);
    	
    	setMotorsRaw(left, right);
    }
    
    public void setMotorsRaw(double left, double right) {
    	left = safetyTest(left);
    	right = safetyTest(right);
    	
    	leftMotor.set(left);
    	rightMotor.set(right);		
	}
    
    private double safetyTest(double motorValue) {
        motorValue = (motorValue < -1) ? -1 : motorValue;
        motorValue = (motorValue > 1) ? 1 : motorValue;
        
        return motorValue;
    }
    
    private double scaleLeft(double left) {
    	return 1.0 * left;
    }
    
    private double scaleRight(double right) {
    	return 1.0 * right;
    }
}
*/