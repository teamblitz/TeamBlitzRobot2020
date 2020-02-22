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
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.ColorSensorSubsystem;
import frc.robot.subsystems.ControlPanelControllerSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FeederArmSubsystem;
import frc.robot.subsystems.FeederWheelsSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // Chasis drive subsystem:
  private DriveSubsystem m_robotDrive;

  // Feeder & shooter subsystm:
  private ShooterSubsystem m_shooter;
  private FeederArmSubsystem m_intakeArm;
  private FeederWheelsSubsystem m_intakeRoller;

  // Control panel subsystem:
  private ColorSensorSubsystem m_colorSensor;
  private ControlPanelControllerSubsystem m_cpController;

  // Controllers:
  private XboxController m_driveController;
  private Joystick m_auxiliaryController;
  
  // Configuration constants:
  private final boolean kUseTankDrive = true;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */

  public RobotContainer() {
    
    configureSubsystems();
    configureButtonBindings();

    if (kUseTankDrive) {
      m_robotDrive.setDefaultCommand(
      new RunCommand(() -> m_robotDrive
        .tankDrive(m_driveController.getY(GenericHID.Hand.kLeft),
                   m_driveController.getY(GenericHID.Hand.kRight)), m_robotDrive));
    }
    else {  // arcadeDrive
      m_robotDrive.setDefaultCommand(
          new RunCommand(() -> m_robotDrive
            .arcadeDrive(m_driveController.getY(GenericHID.Hand.kLeft),
                        m_driveController.getX(GenericHID.Hand.kRight)), m_robotDrive));
    }                     
  }

  private void configureSubsystems() {

    m_robotDrive = new DriveSubsystem();

    m_shooter = new ShooterSubsystem();
    m_intakeArm = new FeederArmSubsystem();
    m_intakeRoller = new FeederWheelsSubsystem();
  
    m_colorSensor = new ColorSensorSubsystem();
    m_cpController = new ControlPanelControllerSubsystem(m_colorSensor);
  
    m_driveController = new XboxController(OIConstants.kDriveControllerPort);
    m_auxiliaryController = new Joystick(OIConstants.kDriveControllerPort);
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // ***** FEEDER ARM SUBSYSTEM *****
    new JoystickButton(m_auxiliaryController, OIConstants.kFeederArmToggleButton)
      .whenPressed(new InstantCommand(m_intakeArm::runFeeder, m_intakeArm).beforeStarting(() -> System.out.println("Joystick Button " + OIConstants.kFeederArmToggleButton + " Pressed")));

    new JoystickButton(m_auxiliaryController, OIConstants.kFeederArmToggleButton)
      .whenReleased(new InstantCommand(m_intakeArm::stopFeeder, m_intakeArm).beforeStarting(() -> System.out.println("Joystick Button " + OIConstants.kFeederArmToggleButton + " Released")));

    // ***** FEEDER WHEELS SUBSYSTEM *****
    new JoystickButton(m_auxiliaryController, OIConstants.kFeederIntakeToggleButton)
      .whenPressed(new InstantCommand(m_intakeRoller::runFeederWheels, m_intakeRoller).beforeStarting(() -> System.out.println("Joystick Button " + OIConstants.kFeederIntakeToggleButton + " Pressed")));

    new JoystickButton(m_auxiliaryController, OIConstants.kFeederIntakeToggleButton)
      .whenPressed(new InstantCommand(m_intakeRoller::stopFeederWheels, m_intakeRoller).beforeStarting(() -> System.out.println("Joystick Button " + OIConstants.kFeederIntakeToggleButton + " Released")));

    // SHOOTER SUBSYSTEM *****
    new JoystickButton(m_auxiliaryController, OIConstants.kShooterToggleButton)
      .whenPressed(new InstantCommand(m_shooter::shoot, m_shooter).beforeStarting(() -> System.out.println("Joystick Button " + OIConstants.kShooterToggleButton + " Pressed")));

    new JoystickButton(m_auxiliaryController, OIConstants.kShooterToggleButton)
      .whenReleased(new InstantCommand(m_shooter::shoot, m_shooter).beforeStarting(() -> System.out.println("Joystick Button " + OIConstants.kShooterToggleButton + "  Released")));
    
    // ***** CONTROL PANEL SYSTEM *****
    new JoystickButton(m_auxiliaryController, OIConstants.kControlPanelSpinToColorButton)
      .whenPressed(new InstantCommand(m_cpController::spin, m_cpController).beforeStarting(() -> System.out.println("Joystick Button " + OIConstants.kControlPanelSpinToColorButton + " Pressed")));

    new JoystickButton(m_auxiliaryController, OIConstants.kControlPanelSpinToColorButton)
      .whenReleased(new InstantCommand(m_cpController::stop, m_cpController).beforeStarting(() -> System.out.println("Joystick Button " + OIConstants.kControlPanelSpinToColorButton + " Released")));  

      new JoystickButton(m_auxiliaryController, OIConstants.kControPanelMultiRotationsButton)
      .whenPressed(new InstantCommand(m_cpController::spin, m_cpController).beforeStarting(() -> System.out.println("Joystick Button " + OIConstants.kControPanelMultiRotationsButton + " Pressed")));

    new JoystickButton(m_auxiliaryController, OIConstants.kControPanelMultiRotationsButton)
      .whenReleased(new InstantCommand(m_cpController::stop, m_cpController).beforeStarting(() -> System.out.println("Joystick Button " + OIConstants.kControPanelMultiRotationsButton + " Released")));  
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

