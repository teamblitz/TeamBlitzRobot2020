/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.FeederWheelsSubsystemConstants;

public class FeederWheelsSubsystem extends SubsystemBase {
  CANSparkMax m_intakeRoller = new CANSparkMax(FeederWheelsSubsystemConstants.kSparkMotorPortIntakeRoller, MotorType.kBrushless);

  public FeederWheelsSubsystem() {

    m_intakeRoller.restoreFactoryDefaults();

  }

  public void runFeederWheels() {
    System.out.println("FeederSubsystem::runFeederWheels");
    m_intakeRoller.set(0.1);
  }

  public void stopFeederWheels() {
    System.out.println("FeederSubsystem::stopFeederWheels");
    m_intakeRoller.set(0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
