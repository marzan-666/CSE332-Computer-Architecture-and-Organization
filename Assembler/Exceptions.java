package assembler;

public class Exceptions {
    private String lineError;
    private String registerException="REGISTER COULD NOT BE IDENTIFIED";
    private String instructionException="INSTRUCTION NOT FOUND PLEASE REFER THE MANUAL";
    private String fileNotFoundException="FILE NOT FOUND";

    public Exceptions(String lineError){

        this.lineError=lineError;

    }

    public String getLineError() {
        return lineError;
    }

    public void setLineError(String lineError) {
        this.lineError = lineError;
    }

    public String getRegisterException() {
        return registerException;
    }

    public String getInstructionException() {
        return instructionException;
    }

    public String getFileNotFoundException() {
        return fileNotFoundException;
    }

    public void setRegisterException(String registerException) {
        this.registerException = registerException;
    }

    public void setInstructionException(String instructionException) {
        this.instructionException = instructionException;
    }

    public void setFileNotFoundException(String fileNotFoundException) {
        this.fileNotFoundException = fileNotFoundException;
    }
    public void printResisterException(){
        System.out.println(registerException);
    }
    public void printInstructionException(){
        System.out.println(instructionException);

    }
    public void printFileNotFound(){
        System.out.println(fileNotFoundException);
    }
}
