package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.old.RobotOld;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class SliderCommand extends CommandBase
{
	public enum Direction{
		RETRACT, EXTEND, STOP
	}

	Direction direction;

	public SliderCommand(Direction direction)
	{
		super("SliderCommand");
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
			case RETRACT:
            RobotMap.sliderDoubleSolenoid.set(DoubleSolenoid.Value.kReverse);
            break;
            case EXTEND:
            RobotMap.sliderDoubleSolenoid.set(DoubleSolenoid.Value.kForward);
            break;
            case STOP:
            RobotMap.sliderDoubleSolenoid.set(DoubleSolenoid.Value.kOff);
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
		//retract when disabled
		RobotMap.sliderDoubleSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
}
