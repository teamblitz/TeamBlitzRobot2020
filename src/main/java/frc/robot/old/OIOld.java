package frc.robot.old;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class OIOld
{
	public Joystick	xbox		= RobotMapOld.xBoxController;
	Button			ButtonY		= RobotMapOld.ButtonY;
	Button			ButtonA		= RobotMapOld.ButtonA;
	Button			ButtonStart	= RobotMapOld.Start;
	Button			ButtonBack	= RobotMapOld.Back;
	Button			RBumper		= RobotMapOld.RBumper;
	Button			LBumper		= RobotMapOld.LBumper;
	Button			ButtonB		= RobotMapOld.ButtonB;
	Button			ButtonX		= RobotMapOld.ButtonX;

	public OIOld()
	{

	}

	public double leftYValue(double threshold)
	{ // Gets horizontal left joystick value (how far it is pushed left or right)
		if (Math.abs(xbox.getY()) <= threshold)
		{
			return 0;
		}
		else 
		{
			return xbox.getY();
		}
	}

	public double leftXValue(double threshold)
	{ // Gets vertical left joystick value (how far it is pushed up or down)
		if (Math.abs(xbox.getX()) <= threshold)
		{
			return 0;
		}
		else 
		{
			return xbox.getX();
		}
	}

	public static void OI_Method()
	{
	}
}