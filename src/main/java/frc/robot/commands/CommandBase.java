package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public abstract class CommandBase extends Command
{

	public static DriveSubsystem driveSubsystem	= new DriveSubsystem();

	public static void init()
	{
		SmartDashboard.putData(driveSubsystem);
	}
	
	public CommandBase()
	{
		super();
	}

	public CommandBase(String name)
	{
		super(name);
	}
	
}
