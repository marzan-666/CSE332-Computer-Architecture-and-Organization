package assembler;

public class RtypeInstruction {
    String name;
    String opcode;
    String rs;
    String rt;
    String rd;

    String op;

    public String getOpcode() {
        return opcode;
    }
    public void setopcode(String op) {
        this.op = op;
    }

    public RtypeInstruction(String name){
        this.name=name;

    }


    public void definingOpcode(int shift){
        if(name.equals("add")){
            opcode="0000";
            setopcode("0000");

        }
        if(name.equals("sub")){
            opcode="0001";
            setopcode("0001");
        }
        if(name.equals("and")){
            opcode="0010";
            setopcode("0010");

        }
        if(name.equals("or")){
            opcode="0011";
            setopcode("0011");
        }

        if(name.equals("xor")){
            opcode="0100";
            setopcode("0100");
        }
        if(name.equals("nor")){
            opcode="0101";
            setopcode("0101");
        }
        if(name.equals("sll")){
            opcode="0110";
            setopcode("0110");
        }
        if(name.equals("sll")){
            opcode="0111";
            setopcode("0111");
        }




    }

    public String getRs() {
        return rs;
    }
    public String getRt() {
        return rt;
    }
    public String getRd() {
        return rd;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }
    public void setRt(String rt) {
        this.rt = rt;
    }
    public void setRd(String rd) {
        this.rd = rd;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
