package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Driver Control", group="Driver")
public class DriverControl extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive, rightDrive, rightLift, leftLift;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        leftDrive = hardwareMap.get(DcMotor.class, "leftdrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightdrive");
        leftLift = hardwareMap.get(DcMotor.class, "leftlift");
        rightLift = hardwareMap.get(DcMotor.class, "rightlift");

        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        leftLift.setDirection(DcMotor.Direction.FORWARD);
        rightLift.setDirection(DcMotor.Direction.REVERSE);
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
        if (gamepad1.dpad_up) {
            leftLift.setPower(0.25);
            rightLift.setPower(0.25);
        } else if (gamepad1.dpad_down) {
            leftLift.setPower(-0.25);
            rightLift.setPower(-0.25);
        } else {
            leftLift.setPower(0);
            rightLift.setPower(0);
        }
    }

    @Override
    public void stop() {
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        leftLift.setPower(0);
        rightLift.setPower(0);
    }
}
