package riscv
import chisel3 . _
import chisel3 . util . _
class control_unit(val on : Bool =1.B, val off : Bool =0.B) extends Module {
    val io =  IO(new Bundle{
        val op_code =  Input(UInt(7.W))
        val mem_write =  Output(Bool())
        val branch =  Output(Bool())
        val mem_read =  Output(Bool())
        val reg_write =  Output(Bool())
        val mem_to_reg =  Output(Bool())
        val operand_a =  Output(UInt(2.W))
        val operand_b =  Output(Bool())
        val extend_sel =  Output(UInt(2.W))
        val next_pc_selector =Output(UInt(2.W))
    })
    //val op_code=io.control_instruction(7,0)
    //r type
    when(io.op_code==="b0110011".U){
        io.mem_write := off
        io.branch := off
        io.mem_read := off
        io.reg_write := 1.B
        io.mem_to_reg := off
        io.operand_a := 0.U
        io.operand_b := off
        io.extend_sel := 0.U
        io.next_pc_selector := 0.U

    }

    //i_type
    .elsewhen(io.op_code==="b0010011".U){
        io.mem_write := off
        io.branch := off
        io.mem_read := off
        io.reg_write := on
        io.mem_to_reg := off
        io.operand_a := 0.U
        io.operand_b := on
        io.extend_sel := 0.U
        io.next_pc_selector := 0.U

    }
    
    //stype
    .elsewhen(io.op_code==="b0100011".U){
        io.mem_write :=on
        io.branch := off
        io.mem_read := off
        io.reg_write := off
        io.mem_to_reg := off
        io.operand_a := 0.U
        io.operand_b := on
        io.extend_sel := "b11".U
        io.next_pc_selector := 0.U

    }

    //b type
    .elsewhen(io.op_code==="b1100011".U){
        io.mem_write := off
        io.branch := on
        io.mem_read := off
        io.reg_write := off
        io.mem_to_reg := off
        io.operand_a := 0.U
        io.operand_b := off
        io.extend_sel := 0.U
        io.next_pc_selector := "b01".U

    }

    //u type
    .elsewhen(io.op_code==="b0110111".U){
        io.mem_write := off
        io.branch := off
        io.mem_read := off
        io.reg_write := on
        io.mem_to_reg := off
        io.operand_a := 0.U
        io.operand_b := on
        io.extend_sel := 0.U
        io.next_pc_selector := 0.U

    }
    //j type
    .elsewhen(io.op_code==="b1101111".U){
        io.mem_write := off
        io.branch := off
        io.mem_read := off
        io.reg_write := on
        io.mem_to_reg := off
        io.operand_a := 2.U
        io.operand_b := off
        io.extend_sel := 0.U
        io.next_pc_selector := "b10".U

    }

    //jalr
    .elsewhen(io.op_code==="b1100111".U){
        io.mem_write := off
        io.branch := off
        io.mem_read := off
        io.reg_write := on
        io.mem_to_reg := off
        io.operand_a := "b10".U
        io.operand_b := off
        io.extend_sel := 0.U
        io.next_pc_selector := "b11".U

    }

    //load
    .elsewhen(io.op_code==="b0000011".U){
        io.mem_write := off
        io.branch := off
        io.mem_read := on
        io.reg_write := on
        io.mem_to_reg := on
        io.operand_a := 0.U
        io.operand_b := on
        io.extend_sel := "b00".U
        io.next_pc_selector := 0.U

    }
    .otherwise{
        io.mem_write := 0.B
        io.branch := 0.B
        io.mem_read := 0.B
        io.reg_write := 0.B
        io.mem_to_reg := 0.B
        io.operand_a := 0.U
        io.operand_b := 0.B
        io.extend_sel := 0.U
        io.next_pc_selector :=0.U
    }

}