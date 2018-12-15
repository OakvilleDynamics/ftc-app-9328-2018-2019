package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Driver Control", group="Driver")
public class DriverControl extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive, rightDrive, lift, hangLock;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        leftDrive = hardwareMap.get(DcMotor.class, "leftdrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightdrive");
        lift = hardwareMap.get(DcMotor.class, "lift");
        hangLock = hardwareMap.get(DcMotor.class, "hanglock");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        lift.setDirection(DcMotor.Direction.FORWARD);
        hangLock.setDirection(DcMotor.Direction.FORWARD);
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        double leftDrivePower;
        double rightDrivePower;

        leftDrivePower = gamepad1.left_stick_y*0.75;
        rightDrivePower = gamepad1.right_stick_y*0.75;

        leftDrive.setPower(leftDrivePower);
        rightDrive.setPower(rightDrivePower);

        if (gamepad1.dpad_up) {
            lift.setPower(1);
        } else if (gamepad1.dpad_down) {
            lift.setPower(-1);
        } else { lift.setPower(0); }

        if (gamepad1.a) {
            hangLock.setPower(0.4);
        } else if (gamepad1.b) {
            hangLock.setPower(-0.4);
        } else { hangLock.setPower(0); }
    }

    @Override
    public void stop() {
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        lift.setPower(0);
        hangLock.setPower(0);
    }
}
