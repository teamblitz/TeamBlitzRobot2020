/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class DriveSubsystem extends SubsystemBase {

  /* Master Talons */
  private final WPI_TalonFX m_leftMotor = new WPI_TalonFX(Constants.DriveConstants.kLeftMotorPort);
  private final WPI_TalonFX m_rightMotor = new WPI_TalonFX(Constants.DriveConstants.kRightMotorPort);
  /* Slave Talons */
  private final WPI_TalonFX m_leftSlave = new WPI_TalonFX(Constants.DriveConstants.kLeftSlavePort);
  private final WPI_TalonFX m_rightSlave = new WPI_TalonFX(Constants.DriveConstants.kRightSlavePort);
  
  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotor, m_rightMotor);
  /**
   * Creates a new DriveSubsystem.
   */
  public DriveSubsystem() {
    /* Factory Default all hardware to prevent unexpected behaviour */
    m_leftMotor.configFactoryDefault();
    m_rightMotor.configFactoryDefault();
    m_leftSlave.configFactoryDefault();
    m_rightSlave.configFactoryDefault();

    m_leftMotor.setNeutralMode(NeutralMode.Brake);
    m_rightMotor.setNeutralMode(NeutralMode.Brake);
    m_leftSlave.setNeutralMode(NeutralMode.Brake);
    m_rightSlave.setNeutralMode(NeutralMode.Brake);

    /**
    * Take our extra motor controllers and have them
    * follow the Talons updated in arcadeDrive 
    */
    m_leftSlave.follow(m_leftMotor);
    m_rightSlave.follow(m_rightMotor);

    /**
    * Drive robot forward and make sure all motors spin the correct way.
    * Toggle booleans accordingly.... 
    */
    m_leftMotor.setInverted(false); // <<<<<< Adjust this until robot drives forward when stick is forward
    m_rightMotor.setInverted(true); // <<<<<< Adjust this until robot drives forward when stick is forward
    m_leftSlave.setInverted(InvertType.FollowMaster);
    m_rightSlave.setInverted(InvertType.FollowMaster);

    /* diff drive assumes (by default) that 
      right side must be negative to move forward.
      Change to 'false' so positive/green-LEDs moves robot forward  
    */
    m_drive.setRightSideInverted(false); // do not change this
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param fwd the commanded forward movement
   * @param rot the commanded rotation
   */
  public void arcadeDrive(final double fwd, final double rot) {
    System.out.println("drive");
    m_drive.arcadeDrive(fwd, rot);
  }
}