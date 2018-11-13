package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Driver Control", group="Driver")
public class DriverControl extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive, rightDrive, lift;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        leftDrive = hardwareMap.get(DcMotor.class, "leftdrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightdrive");
        lift = hardwareMap.get(DcMotor.class, "lift");

        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        lift.setDirection(DcMotor.Direction.FORWARD);
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        double leftDrivePower;
        double rightDrivePower;

        leftDrivePower = gamepad1.left_stick_y;
        rightDrivePower = gamepad1.right_stick_y;

        leftDrive.setPower(leftDrivePower);
        rightDrive.setPower(rightDrivePower);

        if (gamepad1.dpad_up && gamepad1.a) {
            lift.setPower(1);
        } else if (gamepad1.dpad_down && gamepad1.a) {
            lift.setPower(-1);
        } else if (gamepad1.dpad_up) {
            lift.setPower(0.2);
        } else if (gamepad1.dpad_down) {
            lift.setPower(-0.2);
        } else { lift.setPower(0); }
    }

    @Override
    public void stop() {
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        lift.setPower(0);
    }
}
