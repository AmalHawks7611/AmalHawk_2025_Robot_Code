package frc.robot.commands.AlgeaIntake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.EnabledParts;
import frc.robot.Constants.OI;
import frc.robot.subsystems.AlgeaIntakeSubsystem;

public class AlgeaOuttake extends Command {
    public final AlgeaIntakeSubsystem intakeSubsystem;

    public AlgeaOuttake(AlgeaIntakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize() {
        intakeSubsystem.timer.reset();
        intakeSubsystem.timer.start();
        System.out.println("Algea Outtaking Ininialized");
        OI.IS_ALGEA_INTAKING = true;

    }

    @Override
    public void execute() {
        if(EnabledParts.IS_ALGEA_INTAKE_ENABLED){
            intakeSubsystem.Shoot();
            if(!OI.IS_ALGEA_INTAKING){this.end(false);} 
        }

    }

    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.timer.stop();
        intakeSubsystem.timer.reset();
        intakeSubsystem.leaderMotor.stopMotor();
        OI.IS_ALGEA_INTAKING = false;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
