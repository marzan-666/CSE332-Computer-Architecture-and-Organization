package assembler;

import java.util.ArrayList;

public class InstructionType {
    ArrayList<String >filelist;
    private String fileName;
    InstructionFetch instruction;
    InstructionList instructionlist;
    WriteToFile write;
    RegistersValue registervalue;
    boolean Rtyperegister=false;
    boolean Ityperegister=false;

    RtypeInstruction rtypeinstruction;

    public InstructionType(String fileName){
        registervalue=new RegistersValue();
        filelist=new ArrayList<String>();;
        instruction=new InstructionFetch(fileName);
        instructionlist=new InstructionList();
        write=new WriteToFile();
        matchingFetchwithList();
        //	write.printInFile();
    }

    public void IsRtype(String temp){
        for(int i=0;i<instructionlist.getRtypeInstruction().size();i++){
            if(temp.equals(instructionlist.getRtypeInstruction().get(i))){
                Rtyperegister=true;
                //System.out.println(1);
                break;
            }else {
                Rtyperegister=false;
            }
        }

    }
    public void IsItype(String token){

        for(int i=0;i<instructionlist.getItypeInstruction().size();i++){
            if(token.equals(instructionlist.getItypeInstruction().get(i))){
                Ityperegister=true;
                // System.out.println(1);
                break;
            }else {
                Ityperegister=false;
            }
        }
    }

    public void matchingFetchwithList(){
        String instructions;
        String temp=null;
        for(int i=0;i<instruction.getInstruction().size();i++){
            instructions=instruction.getInstruction().get(i);


            String token[]=instructions.split(" ", 0);
            try{
                temp=token[1];
            }catch(Exception ex){
                System.out.println("CONSULT THE USERMANUAL (COMPILATION ERROR) ERROR IN LINE :"+(i+1));
            }



            //System.out.println(token[0]);
            IsRtype(token[0]);
            //System.out.println(Rtyperegister);

            IsItype(token[0]);
            //System.out.println(Ityperegister);

            if(Rtyperegister){
                RtypeAssigningType(token[0],temp,i);
            }
            else if(Ityperegister){
                //System.out.println("Itypeasda");
                //System.out.println(temp);
                ItypeAssigningType(token[0],temp,i);
            }
            else {
                //System.out.println("Bhul ase");
                Exceptions ex=new Exceptions(i+"");
                ex.printInstructionException();
                System.out.println(ex.getLineError());

            }
            Rtyperegister=false;
            Ityperegister=false;
        }
    }

    public void ItypeAssigningType(String instruction,String ofset,int line){
        ItypeInstruction itypeinstruction=new ItypeInstruction();
        int counter=0;
        String r2=null;
        String r1 = null;
        for(int i=0;i<instructionlist.getItypeInstruction().size();i++){
            if(instruction.equals(instructionlist.getItypeInstruction().get(i))){

                String token[]=ofset.split(",");
                //	System.out.println(token[0]);
                if(instruction.equals("lw") || (instruction.equals("sw"))){
                    //System.out.println("sw not foint");
                    try{
                        r1=matchingRegister(token[0]);
                        r2=matchingRegister(token[2]);
                    }catch(Exception exe){
                        Exceptions exceptions=new Exceptions(line+"");
                        exceptions.getInstructionException();
                        exceptions.getLineError();
                        //System.out.println("errrrrr");
                    }

                    String Iconstant=null;
                    try{
                        String constant = Integer.toBinaryString(Integer.parseInt(token[1]));
                        while (constant.length() < 2) // ensure that length of word is 4
                            constant = "0" + constant;
                        Iconstant=constant;
                    }catch(Exception ex){
                        Exceptions exception =new Exceptions(line+"");
                        exception.getInstructionException();
                        //System.out.println("catch 00");
                        break;
                    }try{
                        if(r1==null){
                            Exceptions ex =new Exceptions(line+"");
                            ex.printResisterException();
                            //System.out.println("r1_exception");
                            break;
                        }else if(r2==null){
                            Exceptions ex =new Exceptions(line+"");
                            //System.out.println("r2_exception");
                            ex.printResisterException();
                            break;
                        }
                        else{
                            //System.out.println("r3_exception");
                            itypeinstruction.setRs(matchingRegister(token[0]));
                            itypeinstruction.setRt(matchingRegister(token[2]));
                            itypeinstruction.setConstant(Iconstant);
                        }
                        break;
//
                    }catch(Exception ex){
                        Exceptions exe =new Exceptions(line+"");
                        exe.printResisterException();
                        break;
                    }
                }else{
                    //System.out.println(token[1]);
                    //System.out.println(token[2]);
                    r1=matchingRegister(token[0]);
                    r2=matchingRegister(token[1]);
                    //String val=token[2];

                    if(r1==null){
                        Exceptions exception=new Exceptions(""+line);
                        exception.printResisterException();
                        System.out.println(exception.getLineError());
                        break;
                    }else{
                        itypeinstruction.setRs(r1);
                    }
                    if(r2==null){
                        Exceptions exception=new Exceptions(""+line);
                        exception.printResisterException();
                        System.out.println(exception.getLineError());
                        break;
                    }else{
                        itypeinstruction.setRt(r2);
                    }
                    String Iconstant=null;
                    try{
                        String constant = Integer.toBinaryString(Integer.parseInt(token[2]));
                        while (constant.length() < 2) // ensure that length of word is 4
                            constant = "0" + constant;

                        Iconstant=constant;
                    }catch(Exception ex){
                        Exceptions exception =new Exceptions(line+"");
                        exception.getInstructionException();
                        //System.out.println("const_exception");
                        break;
                    }

                    itypeinstruction.setConstant(Iconstant);
                    counter=0;
                    break;
                }

            }else {

                counter=99;
            }

        }
        if(counter==0){

            if(itypeinstruction.getRs()==null||itypeinstruction.getConstant()==null||itypeinstruction.getRt()==null){
                Exceptions exception=new Exceptions(""+line);
                exception.printInstructionException();
                System.out.println(exception.getLineError());
                //	System.out.println("sdsd");

            }else{
                filelist.add(itypeinstruction.getOpcode()+itypeinstruction.getRs()+itypeinstruction.getRt()+itypeinstruction.getConstant());
                write.setInstructionBinary(itypeinstruction.getOpcode()+itypeinstruction.getRs()+itypeinstruction.getRt()+itypeinstruction.getConstant());
                write.print();
            }
        }
        else {

            Exceptions exception=new Exceptions(""+line);
            exception.printInstructionException();
            System.out.println(exception.getLineError());


        }






    }




    public void RtypeAssigningType(String instruction,String ofset,int line){
        rtypeinstruction=new RtypeInstruction(instruction);
        int counter=0;
        for(int i=0;i<instructionlist.getRtypeInstruction().size();i++){
            if(instruction.equals(instructionlist.getRtypeInstruction().get(i))){
                String rtype[]=ofset.split(",");
                if(instruction.equals("sll")||instruction.equals("srl")){
                    rtypeinstruction.definingOpcode(Integer.parseInt(rtype[2]));
                    rtypeinstruction.setRd("00");

                }else{
                    rtypeinstruction.definingOpcode(0);
                    String r3=matchingRegister(rtype[2]);
                    if(r3==null){
                        if(instruction.equals("sll")||instruction.equals("srl")){

                        }
                        Exceptions exception=new Exceptions(""+line);
                        exception.printResisterException();
                        System.out.println(exception.getLineError());
                        break;
                    }else{
                        rtypeinstruction.setRd(r3);
                    }
                }




                String r1=matchingRegister(rtype[0]);
                if(r1==null){
                    Exceptions exception=new Exceptions(""+line);
                    exception.printResisterException();
                    System.out.println(exception.getLineError());
                    break;
                }else{
                    rtypeinstruction.setRs(r1);
                }
                String r2=matchingRegister(rtype[1]);
                if(r2==null){
                    Exceptions exception=new Exceptions(""+line);
                    exception.printResisterException();
                    System.out.println(exception.getLineError());
                    break;
                }else{
                    rtypeinstruction.setRt(r2);
                }

                counter=0;
                break;
            }else
            {
                counter=99;
            }

        }
        if(counter==0){
            filelist.add(rtypeinstruction.getOpcode()+rtypeinstruction.getRs()+rtypeinstruction.getRt()+rtypeinstruction.getRd());
            write.setInstructionBinary(rtypeinstruction.getOpcode()+rtypeinstruction.getRs()+rtypeinstruction.getRt()+rtypeinstruction.getRd());
            write.print();
        }
        else {

            Exceptions exception=new Exceptions(""+line);
            exception.printInstructionException();
            System.out.println(exception.getLineError());


        }

    }



    public ArrayList<String> getFilelist() {
        return filelist;
    }

    public String matchingRegister(String register){
        //System.out.println(register);
        for(int i=0;i<registervalue.getRegisterList().size();i++){
            if(register.equals(registervalue.getRegisterList().get(i).getName())){
                return (registervalue.getRegisterList().get(i).value);
            }
        }
        return null;
    }



}
