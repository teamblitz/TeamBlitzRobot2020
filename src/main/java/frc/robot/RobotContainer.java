/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.Constants.OIConstants;
import frc.robot.Constants.FeederSubsystemConstants;
import frc.robot.Constants.FeederWheelsSubsystemConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ColorSensorSubsystem;
import frc.robot.subsystems.ControlPanelControllerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.FeederWheelsSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.RunCommand;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ShooterSubsystem m_shooter = new ShooterSubsystem();
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  private final ColorSensorSubsystem m_colorSensor = new ColorSensorSubsystem();
  private final ControlPanelControllerSubsystem m_cpController = new ControlPanelControllerSubsystem(m_colorSensor);
  private final FeederSubsystem m_intakeArm = new FeederSubsystem();
  private final FeederWheelsSubsystem m_intakeRoller = new FeederWheelsSubsystem();

  // The driver's controller.
  private final XboxController m_driveController = new XboxController(OIConstants.kDriveControllerPort);
  private final Joystick m_auxiliaryController = new Joystick(OIConstants.kDriveControllerPort);
  
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */

  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

  //  m_robotDrive.setDefaultCommand(
  //     new RunCommand(() -> m_robotDrive
  //       .arcadeDrive(m_driveController.getY(GenericHID.Hand.kLeft),
  //                    m_driveController.getX(GenericHID.Hand.kRight)), m_robotDrive));
                     
      m_robotDrive.setDefaultCommand(
      new RunCommand(() -> m_robotDrive
        .tankDrive(m_driveController.getY(GenericHID.Hand.kLeft),
                   m_driveController.getY(GenericHID.Hand.kRight)), m_robotDrive));
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */

    //Hey zoomer, button A starts the motor while button B stops the motor.
  private void configureButtonBindings() {
    
    // ***** CONTROL PANEL SYSTEM *****
    new JoystickButton(m_auxiliaryController, 1)
      .whenPressed(new InstantCommand(m_cpController::spin, m_cpController).beforeStarting(() -> System.out.println("Joystick Button 1 Pressed")));

    new JoystickButton(m_auxiliaryController, 1)
      .whenReleased(new InstantCommand(m_cpController::stop, m_cpController).beforeStarting(() -> System.out.println("Joystick Button 2 Released")));
  
    // ***** FEEDER (IntakeArm) SUBSYSTEM *****
    new JoystickButton(m_auxiliaryController, 2)
      .whenPressed(new InstantCommand(m_intakeArm::runFeeder, m_intakeArm).beforeStarting(() -> System.out.println("Xbox A Button Pressed")));

    new JoystickButton(m_auxiliaryController, 2)
      .whenReleased(new InstantCommand(m_intakeArm::stopFeeder, m_intakeArm).beforeStarting(() -> System.out.println("Xbox A Button Released")));

    // ***** FEEDER (IntakeRoller) WHEELS SUBSYSTEM *****
    new JoystickButton(m_auxiliaryController, 3)
      .whenPressed(new InstantCommand(m_intakeRoller::runFeederWheels, m_intakeRoller).beforeStarting(() -> System.out.println("Xbox B Button Pressed")));

    new JoystickButton(m_auxiliaryController, 3)
      .whenPressed(new InstantCommand(m_intakeRoller::stopFeederWheels, m_intakeRoller).beforeStarting(() -> System.out.println("Xbox B Button Released")));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}

