package frc.robot.subsystems;

import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem
{
	public TalonSRX		leftFront;
	public VictorSPX	leftBack;
	
	double encoderUnitsPerRev;
	
	public TalonSRX		rightFront;
	public VictorSPX	rightBack;

	public DriveSubsystem()
	{
		super("DriveSubsystem");

		leftFront = RobotMap.frontLeftMotor;

	}

	@Override
	protected void initDefaultCommand()
	{
		System.out.println("Drive System Initialized");
	}

	public void enableControl()
	{
		System.out.println("Drive System Enabled");
	}

	public void disableControl()
	{
	}

}
