package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.Elevator;
import frc.robot.Constants.OI;
import frc.robot.subsystems.ElevatorSubsystem;

public class e_reefscape extends Command {
    public final ElevatorSubsystem elevatorSubsystem;

    public e_reefscape(ElevatorSubsystem elevatroSubsystem) {
        this.elevatorSubsystem = elevatroSubsystem;
        addRequirements(elevatorSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("Elevator Is Moving To The Reefscape");
        OI.IS_PROCESSING = true;
    }

    @Override
    public void execute() {
        if(!OI.IS_PID_ENDED){
            elevatorSubsystem.OcalPID(OI.ELEVATOR_SPEED, Elevator.ELEVATOR_REEFSCAPE_VALUE);
        }
        else{
            this.end(false);
        }
    }

    @Override
    public void end(boolean interrupted) {
        elevatorSubsystem.leaderMotor.stopMotor();
        elevatorSubsystem.followerMotor.stopMotor();
        OI.IS_PID_ENDED = false;
        OI.IS_PROCESSING = false;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
