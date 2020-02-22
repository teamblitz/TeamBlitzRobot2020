/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.FeederSubsystemConstants;

/**
 * Add your docs here.
 */

public class FeederArmSubsystem extends SubsystemBase {
  CANSparkMax m_intakeArm = new CANSparkMax(FeederSubsystemConstants.kSparkMotorPortIntakeArm, MotorType.kBrushless);

  public FeederArmSubsystem() {

    m_intakeArm.restoreFactoryDefaults();

  }

  public void downFeeder() {
    System.out.println("FeederSubsystem::downFeeder");
    m_intakeArm.set(-0.1);
  
  }

  public void upFeeder() {
    System.out.println("FeederSubsystem::upFeeder");
    m_intakeArm.set(0.1);
  }

  public void stopFeeder() {
    System.out.println("FeederSubsystem::stopFeeder");
    m_intakeArm.set(0.0);
  }

}
