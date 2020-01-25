/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {
  private final TalonSRX m_shooterMotorTop = new TalonSRX(ShooterConstants.kShooterMotorTopPort);

  public ShooterSubsystem() {
	m_shooterMotorTop.configFactoryDefault();
	m_shooterMotorTop.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);

	// Configure nominal outputs.
	m_shooterMotorTop.configNominalOutputForward(0, 10);
	m_shooterMotorTop.configNominalOutputReverse(0, 10);
	m_shooterMotorTop.configPeakOutputForward(1, 10);
	m_shooterMotorTop.configPeakOutputReverse(-1, 10);

	// Configure neutral mode.
	m_shooterMotorTop.setNeutralMode(NeutralMode.Brake);

	// Configure Velocity closed loop gains in slot1.
	m_shooterMotorTop.config_kF(0, 1023.0/7200.0, 10);
	m_shooterMotorTop.config_kP(0, 0.25, 10);
	m_shooterMotorTop.config_kI(0, 0.001, 10);
	m_shooterMotorTop.config_kD(0, 20, 10);

	m_shooterMotorTop.configPulseWidthPeriod_EdgesPerRot(20, 10);

	// m_shooterMotorTop.configSelectedFeedbackCoefficient(1, 0, 10);
	// m_shooterMotorTop.setStatusFramePeriod(StatusFrame.Status_12_Feedback1, 20, 10);
	// m_shooterMotorTop.setStatusFramePeriod(StatusFrame.Status_13_Base_PIDF0, 20, 10);
	// m_shooterMotorTop.configNeutralDeadband(0.001, 10);
	// m_shooterMotorTop.setSensorPhase(true);
	// m_shooterMotorTop.config_IntegralZone(0, 300, 10);
	// m_shooterMotorTop.configClosedLoopPeakOutput(0, 0.75, 10);
	// m_shooterMotorTop.configAllowableClosedloopError(0, 0, 10);

	// m_shooterMotorTop.enableCurrentLimit(true);
	// m_shooterMotorTop.setSensorPhase(true); //Positive velocity corresponds to green light on Talon
	// m_shooterMotorTop.setInverted(false);
	// m_shooterMotorTop.configAllowableClosedloopError(0, 0, 10);
	// m_shooterMotorTop.setSelectedSensorPosition(0, 0, 10);
	//m_shooterMotorTop.configPulseWidthPeriod_EdgesPerRot(pulseWidthPeriod_EdgesPerRot, timeoutMs);

    m_shooterMotorTop.set(ControlMode.Velocity, 0);
  }

  public void shoot() {
	System.out.println("ShooterSubsytem::shoot");
	m_shooterMotorTop.set(ControlMode.Velocity, 1.0 * 80.0);
  }

  public void stop() {
	System.out.println("ShooterSubsystem::stop");
	m_shooterMotorTop.set(ControlMode.Velocity, 0.0);
  }
}
