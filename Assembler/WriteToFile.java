package assembler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class WriteToFile {
    //ArrayList <String> list ;
    private String instructionBinary;
    PrintWriter writer;

    public WriteToFile(){
        //	list=new ArrayList<String>();
        fileInitialize();
        //	printInFile();
    }

    public void fileInitialize(){
        try{
            writer=new PrintWriter("BinaryInstruction", "UTF-8");
        }catch(Exception exception){
            Exceptions ex=new Exceptions(0+"");
            ex.getFileNotFoundException();
        }
    }

    public String getInstructionBinary() {
        return instructionBinary;
    }



    public void setInstructionBinary(String instructionBinary) {
        this.instructionBinary = instructionBinary;
    }



    public void print(){
        //list.add(instructionBinary);
        //System.out.println(list.size());
        System.out.println(instructionBinary);
    }
}
