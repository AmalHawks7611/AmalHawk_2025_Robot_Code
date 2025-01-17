package frc.robot.commands.AlgeaIntake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OI;
import frc.robot.subsystems.AlgeaIntakeSubsystem;

public class Intake extends Command {
    public final AlgeaIntakeSubsystem intakeSubsystem;

    public Intake(AlgeaIntakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("Algea Intaking Ininialized");
        intakeSubsystem.timer.reset();
        intakeSubsystem.timer.start();
        OI.IS_ALGEA_INTAKING = true;

    }

    @Override
    public void execute() {
        intakeSubsystem.Intake(false);
        if(!OI.IS_ALGEA_INTAKING){this.end(false);}
    }

    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.leaderMotor.stopMotor();
        intakeSubsystem.timer.stop();
        OI.IS_ALGEA_INTAKING = false;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
