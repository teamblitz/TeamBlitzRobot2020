package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.RobotManager;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class DriveCommand extends CommandBase
{

	public DriveCommand()
	{
		super("DriveCommand");		
	}

	@Override
	protected void initialize()
	{
		// ???
	}

	@Override
	protected void execute()
	{
		RobotMap.backLeftMotor.follow(RobotMap.frontLeftMotor);
		RobotMap.backRightMotor.follow(RobotMap.frontRightMotor);
	//	RobotMap.frontLeftMotor.set(ControlMode.PercentOutput, -.96 * (Robot.oi.leftYValue(.25) - Robot.oi.leftXValue(.30)));
	//	RobotMap.frontRightMotor.set(ControlMode.PercentOutput, 1 * (Robot.oi.leftYValue(.25) + Robot.oi.leftXValue(.30)));

			/**
			 * Convert inches/sec to units / mp where mp is the 100ms measurement period.
			 * (1440 Units/Rev) / (4 in/Rev) * (10 mp/s) in either direction:
			 * velocity setpoint is in units/100ms
			 */
		
			double maxVelocity; // inches per second
			double turnSpeedReducer;
			double velocityConstant;
			double leftTargetVelocity_UnitsPer100ms;
			double rightTargetVelocity_UnitsPer100ms;
		
			if (RobotManager.isCompetitionRobot()) {
				maxVelocity = 42; // inches per second
				turnSpeedReducer = 4.8;
				velocityConstant = (1440 / (4 * 10) * maxVelocity);
				leftTargetVelocity_UnitsPer100ms = (Robot.oi.leftYValue(.05) - Robot.oi.leftXValue(.05) / turnSpeedReducer) * -velocityConstant;
				rightTargetVelocity_UnitsPer100ms = (Robot.oi.leftYValue(.05) + Robot.oi.leftXValue(.05) / turnSpeedReducer) * velocityConstant;
				RobotMap.frontLeftMotor.set(ControlMode.Velocity, leftTargetVelocity_UnitsPer100ms);
				RobotMap.frontRightMotor.set(ControlMode.Velocity, rightTargetVelocity_UnitsPer100ms);
				
				// Test code:
				// RobotMap.frontRightMotor.set(ControlMode.PercentOutput, Robot.oi.xbox.getY());
				// RobotMap.frontRightMotor.set(ControlMode.Velocity, 0);
			} 
			else if (RobotManager.isPracticeRobot()) {
				// maxVelocity = -42;
				// turnSpeedReducer = 3;
				// double velocityConstantRight = (1440 / (4 * 10) * maxVelocity);
				// double velocityConstantLeft = (1000 / (4 * 10) * maxVelocity);
				// leftTargetVelocity_UnitsPer100ms = (Robot.oi.leftYValue(.25) - Robot.oi.leftXValue(.25) / turnSpeedReducer) * velocityConstantLeft;
				// rightTargetVelocity_UnitsPer100ms = (Robot.oi.leftYValue(.25) + Robot.oi.leftXValue(.25) / turnSpeedReducer) * -velocityConstantRight;
				// RobotMap.frontLeftMotor.set(ControlMode.Velocity, leftTargetVelocity_UnitsPer100ms);
				// RobotMap.frontRightMotor.set(ControlMode.Velocity, rightTargetVelocity_UnitsPer100ms);
		//		RobotMap.frontRightMotor.set(ControlMode.Velocity, 0);
			}
		}

	@Override
	protected boolean isFinished()
	{
		return false;
	}

	@Override
	protected void end()
	{
		super.end();
		System.out.println("Drive System Ended");
	}

	@Override
	protected void interrupted()
	{
		super.interrupted();
		System.out.println("Drive System Interuppted");
	}
	
	public void enableControl()
	{
		// Call "enableControl" in DriveSubsytem.java
		driveSubsystem.enableControl();
	}

	public void disableControl()
	{
		// Call "disableControl" in DriveSubsystem.java
		driveSubsystem.disableControl();
	}
}
