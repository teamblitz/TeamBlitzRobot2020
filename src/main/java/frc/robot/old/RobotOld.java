/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.old;

import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.commands.CommandBase;
import frc.robot.commands.DriveCommand;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotOld extends TimedRobot
{
	/*
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	
	DriveCommand driveCommand;
	public static OIOld oi;

	Command autonomousCommand;
	SendableChooser<Command> autoChooser;
	
	boolean zerosSet;
	
	@Override
	public void robotInit()
	{
		oi = new OIOld();
		CommandBase.init();
		SmartDashboard.putData(Scheduler.getInstance());

		// CameraServer.getInstance().startAutomaticCapture();
		
		driveCommand = new DriveCommand();
		driveCommand.disableControl();
	}

	/*
	 * This function is run once each time the robot enters autonomous mode.
	 */
	@Override
	public void autonomousInit()
	{
		
	}

	/*
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic()
	{
		Scheduler.getInstance().run();
	}

	/*
	 * This function is called once each time the robot enters teleoperated mode.
	 */
	@Override
	public void teleopInit()
	{
		driveCommand.enableControl();
		driveCommand.start();
	}

	@Override
	public void teleopPeriodic()
	{
		Scheduler.getInstance().run();
		//RobotMap.frontLeftMotor.set(ControlMode.PercentOutput, RobotMap.xBoxController.getX());		
		SmartDashboard.putNumber("Joystick X value", RobotMapOld.xBoxController.getX());
	}

	@Override
	public void testPeriodic()
	{
	}

	void loadGameData()
	{
		RobotMapOld.gameData = DriverStation.getInstance().getGameSpecificMessage();
	}
}
