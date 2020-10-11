package assembler;

import java.util.ArrayList;

public class RegistersValue {
    public static RegistersValue register;
    private ArrayList<Register> registerList;

    public RegistersValue(){

        registerList();

    }
    public static synchronized RegistersValue getRegistersValue(){
        if(register==null){
            RegistersValue register=new RegistersValue();

        }
        return register;
    }
    public void registerList(){
        registerList=new ArrayList<Register>();
        String rsubscript="$r";
        String binaryValue;
        for(int i=0;i<4;i++){
            rsubscript="$r";
            rsubscript=rsubscript+i;
            binaryValue=convertBinary(i);
            Register rs=new Register(rsubscript,binaryValue);
            registerList.add(rs);
        }

    }

    public  String  convertBinary(int i) {

        //System.out.println(Integer.toBinaryString(i));
        String binaryValue=Integer.toBinaryString(i);
        if(i<2) {
            binaryValue="00"+binaryValue;
        }

        return binaryValue;

    }
    public ArrayList<Register> getRegisterList() {
        return registerList;
    }
}
