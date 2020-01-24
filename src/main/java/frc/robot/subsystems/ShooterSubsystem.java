/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {
  private final TalonSRX m_shooterMotorTop = new TalonSRX(ShooterConstants.kShooterMotorTopPort);

  public ShooterSubsystem() {
    m_shooterMotorTop.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		//m_shooterMotorTop.enableCurrentLimit(true);
		m_shooterMotorTop.setSensorPhase(true); //Positive velocity corresponds to green light on Talon
		m_shooterMotorTop.setInverted(false);
		m_shooterMotorTop.configNominalOutputForward(0, 10);
		m_shooterMotorTop.configNominalOutputReverse(0, 10);
		m_shooterMotorTop.configPeakOutputForward(1, 10);
		m_shooterMotorTop.configPeakOutputReverse(-1, 10);
		m_shooterMotorTop.configAllowableClosedloopError(0, 0, 10);
		m_shooterMotorTop.config_kF(0, 0.0, 10);
		m_shooterMotorTop.config_kP(0, 1.5, 10);
		m_shooterMotorTop.config_kI(0, 0.02, 10);
		m_shooterMotorTop.config_kD(0, -0.01, 10);
		m_shooterMotorTop.setSelectedSensorPosition(0, 0, 10);
		//m_shooterMotorTop.configPulseWidthPeriod_EdgesPerRot(pulseWidthPeriod_EdgesPerRot, timeoutMs);

    m_shooterMotorTop.set(ControlMode.PercentOutput, 0);
  }
}
