package assembler;

import java.util.ArrayList;

public class InstructionList {
    private static InstructionList instructionlist;
    private ArrayList<String> RtypeInstruction;
    private ArrayList<String> ItypeInstruction;



    public InstructionList(){
        RtypeInstruction=new ArrayList<String>();
        ItypeInstruction=new ArrayList<String>();
        InstructionSet();


    }
    public ArrayList<String> getRtypeInstruction() {
        return RtypeInstruction;
    }
    public ArrayList<String> getItypeInstruction() {
        return ItypeInstruction;
    }

    public static synchronized InstructionList getInstructionList() {
        if (instructionlist == null) {
            instructionlist = new InstructionList();

        }
        return instructionlist;
    }

    public void InstructionSet(){
        //predefined Rtype instrution//

        RtypeInstruction.add("add");
        RtypeInstruction.add("sub");
        RtypeInstruction.add("and");
        RtypeInstruction.add("or");
        RtypeInstruction.add("xor");
        RtypeInstruction.add("nor");
        RtypeInstruction.add("sll");
        RtypeInstruction.add("srl");
        //predefined Itype instrcution
        ItypeInstruction.add("addi");
        ItypeInstruction.add("ori");
        ItypeInstruction.add("andi");
        ItypeInstruction.add("lui");
        ItypeInstruction.add("lw");
        ItypeInstruction.add("sw");
        ItypeInstruction.add("lb");
        ItypeInstruction.add("sb");
    }
}
