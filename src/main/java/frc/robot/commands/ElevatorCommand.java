package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.old.RobotOld;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class ElevatorCommand extends CommandBase
{
	public enum Direction{
		STAGEZERO, STAGEONE, STAGETWO, STOP
	}

	Direction direction;

	public ElevatorCommand(Direction direction)
	{
		super("ElevatorCommand");
		this.direction = direction;				
	}
	@Override
	protected void initialize()
	{
		// ???
	}

	@Override
	protected void execute()
	{
		switch (direction)
		{
			case STAGEZERO:
			RobotMap.stageOneDoubleSolenoid.set(DoubleSolenoid.Value.kForward);
			RobotMap.stageTwoDoubleSolenoid.set(DoubleSolenoid.Value.kForward);
			break;
            case STAGEONE:
			RobotMap.stageOneDoubleSolenoid.set(DoubleSolenoid.Value.kReverse);
			RobotMap.stageTwoDoubleSolenoid.set(DoubleSolenoid.Value.kForward);
			break;
			case STAGETWO:
			RobotMap.stageOneDoubleSolenoid.set(DoubleSolenoid.Value.kReverse);
			RobotMap.stageTwoDoubleSolenoid.set(DoubleSolenoid.Value.kReverse);
			break;
            case STOP:
            RobotMap.stageOneDoubleSolenoid.set(DoubleSolenoid.Value.kOff);
			RobotMap.stageTwoDoubleSolenoid.set(DoubleSolenoid.Value.kOff);
            break;
		}
	}


	@Override
	protected boolean isFinished()
	{
		return false;
	}

	@Override
	protected void end()
	{
		super.end();
	}

	@Override
	protected void interrupted()
	{
		super.interrupted();
	}
	
	public void enableControl()
	{
	}

	public void disableControl()
	{
		//Set elevator to lowest position when disabled
		RobotMap.stageOneDoubleSolenoid.set(DoubleSolenoid.Value.kForward);
		RobotMap.stageTwoDoubleSolenoid.set(DoubleSolenoid.Value.kForward);	
	}
}
