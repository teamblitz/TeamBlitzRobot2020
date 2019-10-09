/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.awt.TextField;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardContainer;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.cameraserver.CameraServer;
import frc.robot.commands.CommandBase;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.ElevatorCommand;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.HatchGrabberCommand;
import frc.robot.commands.SliderCommand;
import frc.robot.commands.ElevatorCommand.Direction;
import frc.robot.subsystems.ExampleSubsystem;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */

 public class Robot extends TimedRobot {
  public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  
  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  
  DriveCommand driveCommand;
  HatchGrabberCommand hatchGrabberCommand;
  ElevatorCommand elevatorCommand;
  SliderCommand sliderCommand;
	public static OI oi;
  
	Command autonomousCommand;
	SendableChooser<Command> autoChooser;
	
	boolean zerosSet;
	
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    CameraServer.getInstance().startAutomaticCapture();
    if (RobotManager.isCompetitionRobot()) {
      System.out.println("Competition Robot Initializing");
      Shuffleboard.getTab("Robot")
                  .add("Which Robot?", "Competition")
                  .withSize(1, 1);
    }
    else if (RobotManager.isPracticeRobot()) {
      System.out.println("Practice Robot Initializing");
      Shuffleboard.getTab("Robot")
        .add("Which Robot?", "Practice")
        .withSize(1, 1);
    }
    


    ShuffleboardTab driveBaseTab = Shuffleboard.getTab("Drivebase");
    driveBaseTab.add("Tank Drive", 9);

    ShuffleboardLayout encoders = driveBaseTab.getLayout("List Layout", "Encoders");
    encoders.add("Left Encoder", RobotMap.frontLeftMotor.getSelectedSensorPosition());
    
    oi = new OI();
		CommandBase.init();
		SmartDashboard.putData(Scheduler.getInstance());
    
    // CameraServer.getInstance().startAutomaticCapture(0);
    // CameraServer.getInstance().startAutomaticCapture(1);
		
    driveCommand = new DriveCommand();
    hatchGrabberCommand = new HatchGrabberCommand();
    hatchGrabberCommand.disableControl();
		driveCommand.disableControl();
    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
    System.out.println("Disable Init");
    elevatorCommand = new ElevatorCommand(ElevatorCommand.Direction.STAGEZERO);
    sliderCommand = new SliderCommand(SliderCommand.Direction.RETRACT);
    elevatorCommand.disableControl();
    sliderCommand.disableControl();
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
    driveCommand.enableControl();
     hatchGrabberCommand.enableControl();
     driveCommand.start();
     hatchGrabberCommand.start();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  
    driveCommand.enableControl();
    hatchGrabberCommand.enableControl();
    driveCommand.start();
    hatchGrabberCommand.start();

  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    //RobotMap.frontLeftMotor.set(ControlMode.PercentOutput, RobotMap.xBoxController.getX());		
    SmartDashboard.putNumber("Joystick X value", RobotMap.xBoxController.getX());
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
