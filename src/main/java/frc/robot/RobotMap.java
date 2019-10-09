/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
// import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

	// Robot IDs
	public static int COMPETITION_ROBOT_DIO_ID = 0;
	public static int PRACTICE_ROBOT_DIO_ID = 1;

	// Digital output for Nvidia power control
	public static int NVIDIA_POWER_CONTROL_DIO_ID = 2;

	/*
	 * Motor IDs
	 */
	
	// Drive System Motors
	public static TalonSRX frontLeftMotor = new TalonSRX(2); // 2
	static {
		frontLeftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		frontLeftMotor.configPeakCurrentLimit(40);
		//frontLeftMotor.enableCurrentLimit(true);
		frontLeftMotor.setSensorPhase(true); //Positive velocity corresponds to green light on Talon
		frontLeftMotor.setInverted(false);
		frontLeftMotor.configNominalOutputForward(0, 10);
		frontLeftMotor.configNominalOutputReverse(0, 10);
		frontLeftMotor.configPeakOutputForward(1, 10);
		frontLeftMotor.configPeakOutputReverse(-1, 10);
		frontLeftMotor.configAllowableClosedloopError(0, 0, 10);
		frontLeftMotor.config_kF(0, 0.0, 10);
		frontLeftMotor.config_kP(0, 1.5, 10);
		frontLeftMotor.config_kI(0, 0.02, 10);
		frontLeftMotor.config_kD(0, -0.01, 10);
		// Practice Robot Values:
		// frontLeftMotor.config_kF(0, 0.0, 10);
		// frontLeftMotor.config_kP(0, 1.5, 10);
		// frontLeftMotor.config_kI(0, .003, 10);
		// frontLeftMotor.config_kD(0, 0, 10);
		frontLeftMotor.setSelectedSensorPosition(0, 0, 10);
		//frontLeftMotor.configPulseWidthPeriod_EdgesPerRot(pulseWidthPeriod_EdgesPerRot, timeoutMs);

		frontLeftMotor.set(ControlMode.PercentOutput, 0);
	}

	public static TalonSRX frontRightMotor = new TalonSRX(1); // 1
	static {
		frontRightMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		frontRightMotor.configPeakCurrentLimit(40); //Peak current
		//frontRightMotor.enableCurrentLimit(true);
		frontRightMotor.setSensorPhase(true);
		frontRightMotor.setInverted(false);
		frontRightMotor.configNominalOutputForward(0, 10);
		frontRightMotor.configNominalOutputReverse(0, 10);
		frontRightMotor.configPeakOutputForward(1, 10);
		frontRightMotor.configPeakOutputReverse(-1, 10);
		frontRightMotor.configAllowableClosedloopError(0, 0, 10);
		frontRightMotor.config_kF(0, 0.0, 10);
		frontRightMotor.config_kP(0, 1.5, 10);
		frontRightMotor.config_kI(0, 0.02, 10);
		frontRightMotor.config_kD(0, -0.01, 10);
		// Practice Robot Vlaues:
		// frontRightMotor.config_kF(0, 0.0, 10);
		// frontRightMotor.config_kP(0, 1.5, 10);
		// frontRightMotor.config_kI(0, .003, 10);
		// frontRightMotor.config_kD(0, 0, 10);
		frontRightMotor.setSelectedSensorPosition(0, 0, 10);
	
		frontRightMotor.set(ControlMode.PercentOutput, 0);
	}
	
	public static TalonSRX hatchGripperMotor = new TalonSRX(3);
	static {
		hatchGripperMotor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector,
														 LimitSwitchNormal.Disabled,
														 30);
		hatchGripperMotor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector,
														 LimitSwitchNormal.Disabled,
														 30);
														 frontLeftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		hatchGripperMotor.setSensorPhase(false); //Positive velocity corresponds to green light on Talon
		hatchGripperMotor.setInverted(false);
		hatchGripperMotor.configNominalOutputForward(0, 10);
		hatchGripperMotor.configNominalOutputReverse(0, 10);
		hatchGripperMotor.configPeakOutputForward(1, 10);
		hatchGripperMotor.configPeakOutputReverse(-1, 10);
		hatchGripperMotor.configAllowableClosedloopError(0, 0, 10);
		hatchGripperMotor.config_kF(0, 0.0, 10);
		hatchGripperMotor.config_kP(0, 	.09, 10);
		hatchGripperMotor.config_kI(0, .0003, 10);
		hatchGripperMotor.config_kD(0, -10, 10);
		hatchGripperMotor.setSelectedSensorPosition(0, 0, 10);
	}

	public static VictorSPX	backRightMotor	= new VictorSPX(5);	// 5 (Slave)
	static {
		backRightMotor.set(ControlMode.PercentOutput, 0);
	}
	public static VictorSPX	backLeftMotor	= new VictorSPX(6);	// 6 (Slave)
	static {
		backLeftMotor.set(ControlMode.PercentOutput, 0);
	}

	// Pneumatic Solenoids
	public static DoubleSolenoid stageOneDoubleSolenoid = new DoubleSolenoid(2, 3);
	public static DoubleSolenoid stageTwoDoubleSolenoid = new DoubleSolenoid(4,5);
	public static DoubleSolenoid sliderDoubleSolenoid = new DoubleSolenoid(0, 1); 

	// NVidia Power Control Digital Output, setting to false is equivalent to pushing the power button
	public static DigitalOutput nvidiaPowerControlDigitalOutput = new DigitalOutput(NVIDIA_POWER_CONTROL_DIO_ID);
	static{
		nvidiaPowerControlDigitalOutput.set(true); //set to false to turn on
	}
	
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
