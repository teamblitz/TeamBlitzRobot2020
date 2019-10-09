package frc.robot.old;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class RobotMapOld
{
	/*
	 * Motor IDs
	 */
	
	// Drive System Motors
	public static TalonSRX frontLeftMotor = new TalonSRX(2); // 2
	static {
		frontLeftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		frontLeftMotor.setSensorPhase(false);
		frontLeftMotor.setInverted(false);
		frontLeftMotor.configNominalOutputForward(0, 10);
		frontLeftMotor.configNominalOutputReverse(0, 10);
		frontLeftMotor.configPeakOutputForward(1, 10);
		frontLeftMotor.configPeakOutputReverse(-1, 10);
		frontLeftMotor.configAllowableClosedloopError(0, 0, 10);
		frontLeftMotor.config_kF(0, 0.0, 10);
		frontLeftMotor.config_kP(0, 11.0, 10);
		frontLeftMotor.config_kI(0, 0.0, 10);
		frontLeftMotor.config_kD(0, 0.0, 10);
		frontLeftMotor.setSelectedSensorPosition(0, 0, 10);
	}
	public static TalonSRX frontRightMotor = new TalonSRX(1); // 1
	static {
		frontRightMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		frontRightMotor.setSensorPhase(false);
		frontRightMotor.setInverted(false);
		frontRightMotor.configNominalOutputForward(0, 10);
		frontRightMotor.configNominalOutputReverse(0, 10);
		frontRightMotor.configPeakOutputForward(1, 10);
		frontRightMotor.configPeakOutputReverse(-1, 10);
		frontRightMotor.configAllowableClosedloopError(0, 0, 10);
		frontRightMotor.config_kF(0, 0.0, 10);
		frontRightMotor.config_kP(0, 11.0, 10);
		frontRightMotor.config_kI(0, 0.0, 10);
		frontRightMotor.config_kD(0, 0.0, 10);
		frontRightMotor.setSelectedSensorPosition(0, 0, 10);
	}
	
	public static VictorSPX	backRightMotor	= new VictorSPX(5);	// 5 (Slave)
	public static VictorSPX	backLeftMotor	= new VictorSPX(6);	// 6 (Slave)
	
	/*
	 * Joystick IDs
	 */

	// XBox Controller ID
	static Joystick xBoxController = new Joystick(0); // 0

	// XBox Joystick IDs



	// XBox ABXY Button IDs
	public static Button	ButtonA	= new JoystickButton(xBoxController, 1);	// 1
	public static Button	ButtonB	= new JoystickButton(xBoxController, 2);	// 2
	public static Button	ButtonX	= new JoystickButton(xBoxController, 3);	// 3
	public static Button	ButtonY	= new JoystickButton(xBoxController, 4);	// 4

	// XBox Bumper IDs
	public static Button	LBumper	= new JoystickButton(xBoxController, 5);	// 5
	public static Button	RBumper	= new JoystickButton(xBoxController, 6);	// 6

	// XBox Miscellaneous IDs
	public static Button	Back	= new JoystickButton(xBoxController, 7);	// 7
	public static Button	Start	= new JoystickButton(xBoxController, 8);	// 8
	public static double	POV		= xBoxController.getPOV();					// "N/A"

	/*
	 * Other IDs
	 */

	// Limit Switch IDs
	
	// Game Data
	public static String gameData;
}
