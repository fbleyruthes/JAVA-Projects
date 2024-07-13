public class Patient extends Person{
    
    private String helthProblemDescription;
    private String doctorResponsible; // 1Xn (1 doctor for n patients)
    private String nurseResponsible; // 1Xn (1 nurse for n patients)

    public Patient(String name, String SSN, String cell_phone, String age, boolean sex, float weight, float height, String helthProblemDescription) {
        super(name, SSN, cell_phone, age, sex, weight, height);
        this.helthProblemDescription = helthProblemDescription;
    }

    public String complain() {
        return "'It hurts!'";
    }
    
}
