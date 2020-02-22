/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.FeederSubsystemConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * Add your docs here.
 */

public class FeederSubsystem extends SubsystemBase {
  CANSparkMax m_intakeArm = new CANSparkMax(FeederSubsystemConstants.kSparkMotorPortIntakeArm, MotorType.kBrushless);

  public FeederSubsystem() {

    m_intakeArm.restoreFactoryDefaults();

  }

  public void runFeeder() {
    System.out.println("FeederSubsystem::runFeeder");
    m_intakeArm.set(0.1);
  }

  public void stopFeeder() {
    System.out.println("FeederSubsystem::stopFeeder");
    m_intakeArm.set(0.0);
  }

}
