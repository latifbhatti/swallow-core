package riscv
import chisel3 . _
import chisel3 . util . _
object alu_op {
    val add = 0.U(6.W)
    val addi = 1.U(6.W)
    val sll = 2.U(6.W)
    val slli = 3.U(6.W)
    val slt = 4.U(6.W)
    val slti = 5.U(6.W)
    val sltu = 6.U(6.W)
    val sltui = 7.U(6.W)
    val bltu = 8.U(6.W)
    val xor = 9.U(6.W)
    val xori = 10.U(6.W)
    val srl = 11.U(6.W)
    val srli = 12.U(6.W)
    val or = 13.U(6.W)
    val ori = 14.U(6.W)
    val and = 15.U(6.W)
    val andi = 16.U(6.W)
    val sub = 17.U(6.W)
    val sra = 18.U(6.W)
    val srai = 19.U(6.W)
    val beq = 20.U(6.W)
    val bne = 21.U(6.W)
    val blt = 22.U(6.W)
    val bgeu = 23.U(6.W)
    val bge = 26.U(6.W)
    val jal = 24.U(6.W)
    val jalr = 25.U(6.W)
    val sw = 27.U(6.W)
    val lw = 28.U(6.W)
    val MUL   = 29.U
    val MULH  = 30.U
    val MULHSU= 31.U
    val MULHU = 32.U
    val DIV   = 33.U
    val DIVU  = 34.U
    val REM   = 35.U
    val REMU  = 36.U

}
import alu_op._
class alu() extends Module {
    val io = IO(new Bundle{
        //val instruction = Input(UInt(32.W))
        val alu =Input(UInt(6.W))
        val a =Input(SInt(32.W))
        val b =Input(SInt(32.W))
        val out = Output(SInt(32.W))
        //val branch = Output(Bool())
    })
    // val product = WireInit(0.S(64.W))
    // product := io.a*io.b
    // val mulhsu = WireInit(0.S(64.W))
    // mulhsu := (io.a * io.b.asUInt).asSInt
    // val mulhu = WireInit(0.S(64.W))
    // mulhu := (io.a.asUInt * io.b.asUInt).asSInt
val product = WireInit(0.S(64.W))
  product := io.a * io.b

  val mulhsu = WireInit(0.S(64.W))
  mulhsu := (io.a * io.b.asUInt).asSInt

  val mulhu = WireInit(0.S(64.W))
  mulhu := (io.a.asUInt * io.b.asUInt).asSInt

  io.out := Mux(io.alu === alu_op.add || io.alu === alu_op.addi, io.a + io.b,
             Mux(io.alu === alu_op.sll || io.alu === alu_op.slli, io.a << io.b(15, 0),
             Mux(io.alu === alu_op.slt || io.alu === alu_op.slti, Mux(io.a < io.b, 1.S, 0.S),
             Mux(io.alu === alu_op.sltu || io.alu === alu_op.sltui, Mux(io.a.asUInt < io.b.asUInt, 1.S, 0.S),
             Mux(io.alu === alu_op.xor || io.alu === alu_op.xori, io.a ^ io.b,
             Mux(io.alu === alu_op.srl || io.alu === alu_op.srli, io.a >> io.b(15, 0),
             Mux(io.alu === alu_op.or || io.alu === alu_op.ori, io.a | io.b,
             Mux(io.alu === alu_op.and || io.alu === alu_op.andi, io.a & io.b,
             Mux(io.alu === alu_op.sub, io.a - io.b,
             Mux(io.alu === alu_op.sra || io.alu === alu_op.srai, io.a >> io.b(15, 0),
             Mux(io.alu === alu_op.sw, io.a + io.b,
             Mux(io.alu === alu_op.MUL, (product(31, 0)).asSInt,
             Mux(io.alu === alu_op.MULH, (product(63, 32)).asSInt,
             Mux(io.alu === alu_op.MULHSU, (mulhsu(63, 32)).asSInt,
             Mux(io.alu === alu_op.MULHU, (mulhu(63, 32)).asSInt,
             0.S
           )))))))))))))))
}