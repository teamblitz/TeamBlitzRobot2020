/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.Constants.ShooterConstants;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpilibj.controller.PIDController;

public class ShooterSubsystem extends PIDSubsystem {
  private final TalonSRX m_shooterMotorTop = new TalonSRX(ShooterConstants.kShooterMotorTopPort);
  private final Encoder m_shooterEncoderTop =
    new Encoder(ShooterConstants.kEncoderPorts[0], ShooterConstants.kEncoderPorts[1],
      ShooterConstants.kEncoderReversed);
  private final SimpleMotorFeedforward m_shooterFeedforward =
    new SimpleMotorFeedforward(ShooterConstants.kSVolts,
      ShooterConstants.kVVoltSecondsPerRotation);
  /**
   * Creates a new ExampleSubsystem.
   */
  public ShooterSubsystem() {
    super(new PIDController(ShooterConstants.kP, ShooterConstants.kI, ShooterConstants.kD));
    getController().setTolerance(ShooterConstants.kShooterToleranceRPS);
    m_shooterEncoderTop.setDistancePerPulse(ShooterConstants.kEncoderDistancePerPulse);
    setSetpoint(ShooterConstants.kShooterTargetRPS);
  }

  @Override
  public void useOutput(double output, double setpoint) {
    m_shooterMotorTop.set(ControlMode.PercentOutput, output + m_shooterFeedforward.calculate(setpoint));
  }

   @Override
  public double getMeasurement() {
    return m_shooterEncoderTop.getRate();
  }

  public boolean atSetpoint() {
    return m_controller.atSetpoint();
  }

  public void stop() {
    m_shooterMotorTop.set(ControlMode.PercentOutput, 0);
  }

}
