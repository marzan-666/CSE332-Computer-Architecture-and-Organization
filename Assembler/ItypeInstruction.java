package assembler;

public class ItypeInstruction {
    private String opcode;
    private String rs;
    private String rt;
    private String constant;

    public String getOpcode(){
        return opcode;
    }
    public String getRs() {
        return rs;
    }
    public String getRt() {
        return rt;
    }
    public String getConstant() {
        return constant;
    }
    public void setRs(String rs) {
        this.rs = rs;
    }
    public void setRt(String rt) {
        this.rt = rt;
    }
    public void setConstant(String constant) {
        this.constant = constant;
    }
}
