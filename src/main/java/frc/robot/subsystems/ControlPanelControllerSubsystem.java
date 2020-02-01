/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.networktables.NetworkTableEntry;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ControlPanelControllerConstants;

public class ControlPanelControllerSubsystem extends SubsystemBase {

	private final String kShuffleboardTabName = "Control Panel";
	private ShuffleboardTab sbControlPanelTab = Shuffleboard.getTab(kShuffleboardTabName);

	private final VictorSPX m_Motor = new VictorSPX(ControlPanelControllerConstants.kMotorPort);

	private NetworkTableEntry motorVelocity = Shuffleboard.getTab("Controls")
		.add("Controller", m_Motor.getSelectedSensorVelocity())
		.withWidget(BuiltInWidgets.kTextView)
		.getEntry();  
	
	public ControlPanelControllerSubsystem() {
		m_Motor.configFactoryDefault();
		m_Motor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);

		// Configure nominal outputs.
		m_Motor.configNominalOutputForward(0, 10);
		m_Motor.configNominalOutputReverse(0, 10);
		m_Motor.configPeakOutputForward(1, 10);
		m_Motor.configPeakOutputReverse(-1, 10);

		// Configure neutral mode.
		m_Motor.setNeutralMode(NeutralMode.Brake);

		// Configure Velocity closed loop gains in slot1.
		m_Motor.config_kF(0, 1023.0/7200.0, 10);
		m_Motor.config_kP(0, 0.25, 10);
		m_Motor.config_kI(0, 0.001, 10);
		m_Motor.config_kD(0, 20, 10);

		m_Motor.configPulseWidthPeriod_EdgesPerRot(20, 10);

		m_Motor.set(ControlMode.Velocity, 0);
	}

	public void spin() {
		System.out.println("ControlPanelControllerSubsytem::spin");
		m_Motor.set(ControlMode.Velocity, 1.0 * motorVelocity.getDouble(1.0));
	}

	public void stop() {
		System.out.println("ControlPanelControllerSubsytem::stop");
		m_Motor.set(ControlMode.Velocity, 0.0);
	}
}

	