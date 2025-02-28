package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.Elevator;
import frc.robot.Constants.OI;
import frc.robot.subsystems.ElevatorSubsystem;

public class e_level4 extends Command {
    public final ElevatorSubsystem elevatorSubsystem;

    public e_level4(ElevatorSubsystem elevatroSubsystem) {
        this.elevatorSubsystem = elevatroSubsystem;
        addRequirements(elevatorSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("Elevator Is Moving To The Level4");
        OI.IS_PROCESSING = true;

    }

    @Override
    public void execute() {
        if(!OI.IS_PID_ENDED){
            elevatorSubsystem.OcalPID(Elevator.ELEVATOR_SPEED, Elevator.ELEVATOR_L4_VALUE);
        }
        else{
            this.end(false);
        }
    }

    @Override
    public void end(boolean interrupted) {
        elevatorSubsystem.leaderMotor.stopMotor();
        elevatorSubsystem.followerMotor.stopMotor();
        OI.IS_PID_ENDED = true;
        OI.IS_PROCESSING = false;
    }

    @Override
    public boolean isFinished() {
        return OI.IS_PID_ENDED;
    }
}
