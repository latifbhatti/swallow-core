package riscv
import chisel3 . _
import chisel3 . util . _
class alu_control extends Module {
    val io = IO(new Bundle{
        val op_code = Input(UInt(7.W))
        val fn3 = Input(UInt(3.W))
        val fn7 = Input(UInt(7.W))
        val out = Output(UInt(5.W))
    })
    //r type
    when (io.op_code==="b0110011".U){
        when (io.fn7===0.U){
            //add
            when (io.fn3===0.U){
                io.out := 0.U
            }
            //sll
            .elsewhen (io.fn3===1.U){
                io.out := 2.U
            }
            //slt
            .elsewhen (io.fn3===2.U){
                io.out := 4.U
            }
            //sltu
            .elsewhen (io.fn3===3.U){
                io.out := 6.U
            }
            //xor
            .elsewhen (io.fn3===4.U){
                io.out := 9.U
            }
            //srl
            .elsewhen (io.fn3===5.U){
                io.out := 11.U
            }
            //or
            .elsewhen (io.fn3===6.U){
                io.out := 13.U
            }
            //and
            .elsewhen (io.fn3===7.U){
                io.out := 15.U
            }
            .otherwise{
                io.out := 0.U
            }
        }


        .elsewhen (io.fn7===1.U){
            //sub
            when (io.fn3===0.U){
                io.out := 17.U
            }
            //sra
            .elsewhen (io.fn3===5.U){
                io.out := 18.U
            }
            .otherwise{
                io.out := 0.U
            }



        }
        .otherwise{
            io.out := 0.U
        }
    }




    // i type
    .elsewhen (io.op_code==="b0010011".U){
        when (io.fn7===0.U){
            //slli
            when (io.fn3===1.U){
                io.out := 3.U
            }
            //srli
            .elsewhen (io.fn3===5.U){
                io.out := 12.U
            }


            //i type without fn7
            //addi
            .elsewhen (io.fn3===0.U){
                io.out := 1.U
            }
            //slti
            .elsewhen (io.fn3===2.U){
                io.out := 5.U
            }
            //sltui
            .elsewhen (io.fn3===3.U){
                io.out := 7.U
            }
            //xori
            .elsewhen (io.fn3===4.U){
                io.out := 10.U
            }
            //ori
            .elsewhen (io.fn3===6.U){
                io.out := 14.U
            }
            //andi
            .elsewhen (io.fn3===7.U){
                io.out := 16.U
            }


            .otherwise{
                io.out := 0.U
            }
        }


        .elsewhen (io.fn7===1.U){
            //srai
            when (io.fn3===5.U){
                io.out := 19.U
            }
            .otherwise{
                io.out := 0.U
            }
        }
        .otherwise{
            io.out := 0.U
        }

    }

    // i typr jalr
    .elsewhen (io.op_code==="b1100111".U){
        io.out := 25.U
    }
    //i type load
    .elsewhen (io.op_code==="b0000111".U){
         when (io.fn3===2.U){
            io.out := 28.U
         }
         .otherwise{
            io.out := 0.U
         }
    }

    // j typr jal
    .elsewhen (io.op_code==="b1101111".U){
        io.out := 24.U
    }
    .elsewhen(io.op_code==="b0100011".U){
        when (io.fn3===2.U){
            io.out := 27.U
        }
        .otherwise{
            io.out := 0.U
        }

    }

    .otherwise{
        io.out := 0.U
    }
}