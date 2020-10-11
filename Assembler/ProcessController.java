package assembler;

import java.io.IOException;
import java.io.PrintWriter;

public class ProcessController {
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        InstructionType type=new InstructionType("file2.txt");



        try{
            PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
            for(int i=0;i<type.getFilelist().size();i++){
                writer.println(type.getFilelist().get(i));
            }
            writer.close();
        } catch(IOException e) {
            // do something
            System.out.println("file not found");

        }
    }
}
