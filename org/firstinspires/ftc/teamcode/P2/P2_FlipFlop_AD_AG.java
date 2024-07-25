/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.P2;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="P2_FlipFlop_AD_AG", group="Linear OpMode")

public class P2_FlipFlop_AD_AG extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDriveAD = null;
    private DcMotor rightDriveAD = null;
    DigitalChannel gripperStopAD = null;
    
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftDriveAD  = hardwareMap.get(DcMotor.class, "leftDriveAD");
        rightDriveAD = hardwareMap.get(DcMotor.class, "rightDriveAD");
        gripperStopAD = hardwareMap.get(DigitalChannel.class,"gripperStopAD");
        boolean isRightTurnAD = false;

        leftDriveAD.setDirection(DcMotor.Direction.REVERSE);
        rightDriveAD.setDirection(DcMotor.Direction.FORWARD);
        
        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            leftDriveAD.setPower(-0.5);
            rightDriveAD.setPower(-0.5);
            telemetry.update();
            
            if(gripperStopAD.getState() == false){
                
                leftDriveAD.setPower(0);
                rightDriveAD.setPower(0);
                sleep(100);
                
                if(isRightTurnAD == false){
                    
                    leftDriveAD.setPower(0);
                    rightDriveAD.setPower(-0.5);
                    sleep(900);
                    isRightTurnAD = true;
                    
                }
                else{
                    
                    leftDriveAD.setPower(-0.5);
                    rightDriveAD.setPower(0);
                    sleep(1300);
                    isRightTurnAD = false;
                    
                }
            }
            
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftDriveAD.getPower(), rightDriveAD.getPower());
            telemetry.addData("Gripper Status",gripperStopAD.getState());
            telemetry.addData("Left Trigger Status", gamepad1.left_trigger);
            telemetry.update();
            
            
        }
    }
}
