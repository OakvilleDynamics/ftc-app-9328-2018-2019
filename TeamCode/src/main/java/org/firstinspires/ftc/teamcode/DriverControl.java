package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Driver Control", group="Driver")
public class DriverControl extends OpMode {
    //Declare ElapsedTime and Motor Constants
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive, rightDrive, lift, liftSwing;

    @Override
    public void init() {
        //Announce that the code has been initialized
        telemetry.addData("Status", "Initialized");
        
        //Hardware Mapping
        leftDrive = hardwareMap.get(DcMotor.class, "leftdrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightdrive");
        lift = hardwareMap.get(DcMotor.class, "lift");
        liftSwing = hardwareMap.get(DcMotor.class, "liftswing");
        
        //Hardware Mapping pt2
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        lift.setDirection(DcMotor.Direction.REVERSE);
        liftSwing.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void start() {
        //Reset ElapsedTime at start
        runtime.reset();
    }

    @Override
    public void loop() {
        //Set Left and Right Drive power to 100% of the Y axis sticks
        double leftDrivePower;
        double rightDrivePower;
        leftDrivePower = gamepad1.left_stick_y;
        rightDrivePower = gamepad1.right_stick_y;
        leftDrive.setPower(leftDrivePower);
        rightDrive.setPower(rightDrivePower);

        //Set Lift Power with Dpad up and down
        lift.setPower(gamepad2.right_stick_y*0.8);

        if (gamepad2.dpad_left) {
            liftSwing.setPower(0.45);
        } else if (gamepad2.dpad_right) {
            liftSwing.setPower(-0.45);
        } else { liftSwing.setPower(0); }
    }

    @Override
    public void stop() {
        //Set everything to 0 so bot doesn't move after game stops
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        lift.setPower(0);
        liftSwing.setPower(0);
    }
}
