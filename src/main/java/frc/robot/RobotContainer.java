/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.ControlPanelControllerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ShooterSubsystem m_shooter = new ShooterSubsystem();
  private final ControlPanelControllerSubsystem m_cpController = new ControlPanelControllerSubsystem();

  // The driver's controller.
  private final XboxController m_driveController = new XboxController(OIConstants.kDriveControllerPort);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */

    //Hey loser, button A starts the motor while button B stops the motor.
  private void configureButtonBindings() {
    
    // ***** SHOOTER *****
    new JoystickButton(m_driveController, Button.kA.value)
      .whenPressed(new InstantCommand(m_shooter::shoot, m_shooter).beforeStarting(() -> System.out.println("Xbox 'A' Pressed")));

    new JoystickButton(m_driveController, Button.kA.value)
      .whenReleased(new InstantCommand(m_shooter::stop, m_shooter).beforeStarting(() -> System.out.println("Xbox 'A' Released")));

    // ***** CONTROL PANEL *****
    new JoystickButton(m_driveController, Button.kX.value)
      .whenPressed(new InstantCommand(m_cpController::spin, m_cpController).beforeStarting(() -> System.out.println("Xbox 'X' Pressed")));

    new JoystickButton(m_driveController, Button.kX.value)
      .whenReleased(new InstantCommand(m_cpController::stop, m_cpController).beforeStarting(() -> System.out.println("Xbox 'X' Released")));

    // ***** DRIVE SYSTEM *****
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

