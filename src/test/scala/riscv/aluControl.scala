package riscv
import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class aluControlTester extends FreeSpec with ChiselScalatestTester{
    "Alu Controller" in {
        test(new alu_control){c=>
        c.io.fn7.poke(20.U)
        c.io.fn3.poke(0.U)
        c.io.op_code.poke("b0110011".U)
        c.clock.step(300)
           
            
        }
    }
}
