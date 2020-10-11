package assembler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class InstructionFetch {
    private ArrayList <String> instruction;
    private String fileName;
    private String line;

    public InstructionFetch(String fileName){

        instruction=new ArrayList<String>();
        this.fileName=fileName;
        scanFile();

    }
    public ArrayList<String> getInstruction() {
        return instruction;
    }

    public void scanFile(){
        try{
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) !=null) {
                //reading from file
                if(line.equals("EXIT")){
                    break;
                }
                else{

                    line=line.trim();
                    instruction.add(line);
                }
            }
            bufferedReader.close();
        }
        catch(Exception e){
            System.out.println(e);
            System.out.println("file not found");
        }

    }
}
