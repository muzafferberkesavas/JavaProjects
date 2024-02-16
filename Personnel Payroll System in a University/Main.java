import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) {
        String[] personneltxt = File.readFile(args[0]);
        String[] monitoringtxt = File.readFile(args[1]);
        Data data = new Data();
        data.processData(personneltxt,monitoringtxt);
        data.writeData();
    }
}

class Data{
    public University university;

    public Data(){
        university = new University("Hacettepe");
    }
    public void processData(String[] personneltxt,String[] monitoringtxt){          //This method stores every data in the input files.
        if (personneltxt != null){

            for (String personnelinfo : personneltxt){

                String name = personnelinfo.split("\t")[0].split(" ")[0];
                String surname = personnelinfo.split("\t")[0].split(" ")[1];
                String registrationNumber = personnelinfo.split("\t")[1];
                String position = personnelinfo.split("\t")[2];
                int startingYear = Integer.parseInt(personnelinfo.split("\t")[3]);
                int[] workingHours = null;

                if (monitoringtxt != null){
                    for (String line : monitoringtxt){
                        String registrationNum = line.split("\t")[0];
                        if (registrationNum.equals(registrationNumber)){                        //This code block looks proper registration number in the monitoringtxt
                            int WORKING_HOURS_LENGTH = line.split("\t").length - 1;        //And it stores the working hours into the workingHours array.
                            workingHours = new int[WORKING_HOURS_LENGTH];
                            for (int index = 0; index <WORKING_HOURS_LENGTH; index++){
                                int hour =Integer.parseInt(line.split("\t")[index + 1]);
                                workingHours[index] = hour;
                            }
                        }
                    }
                }


                if (position.equals("FACULTY_MEMBER")){
                    FacultyMember facultyMember = new FacultyMember(name,surname,registrationNumber,position,startingYear,workingHours);            //creates new object
                    facultyMember.totalSalary = facultyMember.baseSalary + facultyMember.ssBenefits(135) + facultyMember.severancePay() + facultyMember.addCourseFee(); //calculates and declares totalSalary
                    university.addPersonnel(facultyMember);  //adding a new object into the Object[] personnels
                } else if (position.equals("RESEARCH_ASSISTANT")) {
                    ResearchAssistant researchAssistant = new ResearchAssistant(name,surname,registrationNumber,position,startingYear,workingHours);
                    researchAssistant.totalSalary = researchAssistant.baseSalary + researchAssistant.ssBenefits(105) + researchAssistant.severancePay();
                    university.addPersonnel(researchAssistant);
                } else if (position.equals("OFFICER")) {
                    Officer officer = new Officer(name,surname,registrationNumber,position,startingYear,workingHours);
                    officer.totalSalary = officer.baseSalary + officer.ssBenefits(65) + officer.severancePay() + officer.overWorkSalary(20,10);
                    university.addPersonnel(officer);
                } else if (position.equals("PARTTIME_EMPLOYEE")) {
                    PartTimeEmployee partTimeEmployee = new PartTimeEmployee(name,surname,registrationNumber,position,startingYear,workingHours);
                    partTimeEmployee.totalSalary = partTimeEmployee.hourOfWork(18,20,10) + partTimeEmployee.severancePay();
                    university.addPersonnel(partTimeEmployee);
                } else if (position.equals("WORKER")) {
                    Worker worker = new Worker(name,surname,registrationNumber,position,startingYear,workingHours);
                    worker.totalSalary = worker.dayOfWork(105) + worker.severancePay() + worker.overWorkSalary(11,10);
                    university.addPersonnel(worker);
                } else if (position.equals("CHIEF")) {
                    Chief chief = new Chief(name,surname,registrationNumber,position,startingYear,workingHours);
                    chief.totalSalary = chief.dayOfWork(125) + chief.severancePay() + chief.overWorkSalary(15,8);
                    university.addPersonnel(chief);
                } else if (position.equals("SECURITY")) {
                    Security security = new Security(name,surname,registrationNumber,position,startingYear,workingHours);
                    security.totalSalary = security.hourOfWork(10,54,30) + security.severancePay() + security.transMoney() + security.foodMoney();
                    university.addPersonnel(security);
                }
            }
        }
    }
    public void writeData(){                                                // It looks every object in the personnel array and decide its class
        for (Object personnel : university.personnels){                 // Then It writes every data into the personnel object
            if (personnel.getClass().equals(FacultyMember.class)){
                File.writeFile(((FacultyMember) personnel).getRegisterNumber(),"Name : "+((FacultyMember) personnel).name,true,true);
                File.writeFile(((FacultyMember) personnel).getRegisterNumber(), "\nSurname : "+((FacultyMember) personnel).surname,true,true);
                File.writeFile(((FacultyMember) personnel).getRegisterNumber(),"\nRegistration Number : "+((FacultyMember) personnel).getRegisterNumber(),true,true);
                File.writeFile(((FacultyMember) personnel).getRegisterNumber(),"\nPosition : "+((FacultyMember) personnel).position,true,true);
                File.writeFile(((FacultyMember) personnel).getRegisterNumber(),"\nYear of Start : "+((FacultyMember) personnel).startingYear,true,true);
                File.writeFile(((FacultyMember) personnel).getRegisterNumber(), "\nTotal Salary : "+((FacultyMember) personnel).totalSalary+".00 TL",true,false);
            } else if (personnel.getClass().equals(ResearchAssistant.class)) {
                File.writeFile(((ResearchAssistant) personnel).getRegisterNumber(),"Name : "+((ResearchAssistant) personnel).name,true,true);
                File.writeFile(((ResearchAssistant) personnel).getRegisterNumber(), "\nSurname : "+((ResearchAssistant) personnel).surname,true,true);
                File.writeFile(((ResearchAssistant) personnel).getRegisterNumber(),"\nRegistration Number : "+((ResearchAssistant) personnel).getRegisterNumber(),true,true);
                File.writeFile(((ResearchAssistant) personnel).getRegisterNumber(),"\nPosition : "+((ResearchAssistant) personnel).position,true,true);
                File.writeFile(((ResearchAssistant) personnel).getRegisterNumber(),"\nYear of Start : "+((ResearchAssistant) personnel).startingYear,true,true);
                File.writeFile(((ResearchAssistant) personnel).getRegisterNumber(), "\nTotal Salary : "+((ResearchAssistant) personnel).totalSalary+".00 TL",true,false);
            } else if (personnel.getClass().equals(Officer.class)) {
                File.writeFile(((Officer) personnel).getRegisterNumber(),"Name : "+((Officer) personnel).name,true,true);
                File.writeFile(((Officer) personnel).getRegisterNumber(), "\nSurname : "+((Officer) personnel).surname,true,true);
                File.writeFile(((Officer) personnel).getRegisterNumber(),"\nRegistration Number : "+((Officer) personnel).getRegisterNumber(),true,true);
                File.writeFile(((Officer) personnel).getRegisterNumber(),"\nPosition : "+((Officer) personnel).position,true,true);
                File.writeFile(((Officer) personnel).getRegisterNumber(),"\nYear of Start : "+((Officer) personnel).startingYear,true,true);
                File.writeFile(((Officer) personnel).getRegisterNumber(), "\nTotal Salary : "+((Officer) personnel).totalSalary+".00 TL",true,false);
            } else if (personnel.getClass().equals(PartTimeEmployee.class)) {
                File.writeFile(((PartTimeEmployee) personnel).getRegisterNumber(),"Name : "+((PartTimeEmployee) personnel).name,true,true);
                File.writeFile(((PartTimeEmployee) personnel).getRegisterNumber(), "\nSurname : "+((PartTimeEmployee) personnel).surname,true,true);
                File.writeFile(((PartTimeEmployee) personnel).getRegisterNumber(),"\nRegistration Number : "+((PartTimeEmployee) personnel).getRegisterNumber(),true,true);
                File.writeFile(((PartTimeEmployee) personnel).getRegisterNumber(),"\nPosition : "+((PartTimeEmployee) personnel).position,true,true);
                File.writeFile(((PartTimeEmployee) personnel).getRegisterNumber(),"\nYear of Start : "+((PartTimeEmployee) personnel).startingYear,true,true);
                File.writeFile(((PartTimeEmployee) personnel).getRegisterNumber(), "\nTotal Salary : "+((PartTimeEmployee) personnel).totalSalary+".00 TL",true,false);
            } else if (personnel.getClass().equals(Worker.class)) {
                File.writeFile(((Worker) personnel).getRegisterNumber(),"Name : "+((Worker) personnel).name,true,true);
                File.writeFile(((Worker) personnel).getRegisterNumber(), "\nSurname : "+((Worker) personnel).surname,true,true);
                File.writeFile(((Worker) personnel).getRegisterNumber(),"\nRegistration Number : "+((Worker) personnel).getRegisterNumber(),true,true);
                File.writeFile(((Worker) personnel).getRegisterNumber(),"\nPosition : "+((Worker) personnel).position,true,true);
                File.writeFile(((Worker) personnel).getRegisterNumber(),"\nYear of Start : "+((Worker) personnel).startingYear,true,true);
                File.writeFile(((Worker) personnel).getRegisterNumber(), "\nTotal Salary : "+((Worker) personnel).totalSalary+".00 TL",true,false);
            } else if (personnel.getClass().equals(Chief.class)) {
                File.writeFile(((Chief) personnel).getRegisterNumber(),"Name : "+((Chief) personnel).name,true,true);
                File.writeFile(((Chief) personnel).getRegisterNumber(), "\nSurname : "+((Chief) personnel).surname,true,true);
                File.writeFile(((Chief) personnel).getRegisterNumber(),"\nRegistration Number : "+((Chief) personnel).getRegisterNumber(),true,true);
                File.writeFile(((Chief) personnel).getRegisterNumber(),"\nPosition : "+((Chief) personnel).position,true,true);
                File.writeFile(((Chief) personnel).getRegisterNumber(),"\nYear of Start : "+((Chief) personnel).startingYear,true,true);
                File.writeFile(((Chief) personnel).getRegisterNumber(), "\nTotal Salary : "+((Chief) personnel).totalSalary+".00 TL",true,false);
            } else if (personnel.getClass().equals(Security.class)) {
                File.writeFile(((Security) personnel).getRegisterNumber(),"Name : "+((Security) personnel).name,true,true);
                File.writeFile(((Security) personnel).getRegisterNumber(), "\nSurname : "+((Security) personnel).surname,true,true);
                File.writeFile(((Security) personnel).getRegisterNumber(),"\nRegistration Number : "+((Security) personnel).getRegisterNumber(),true,true);
                File.writeFile(((Security) personnel).getRegisterNumber(),"\nPosition : "+((Security) personnel).position,true,true);
                File.writeFile(((Security) personnel).getRegisterNumber(),"\nYear of Start : "+((Security) personnel).startingYear,true,true);
                File.writeFile(((Security) personnel).getRegisterNumber(), "\nTotal Salary : "+((Security) personnel).totalSalary+".00 TL",true,false);
            }
        }
    }
}
class File{

    public static String[] readFile(String path){                       //This method reads file and returns String Array.
        try {
            int index = 0;
            Path path1 = Paths.get(path);
            int length = Files.readAllLines(path1).size();
            String[] results = new String[length];
            for (String line : Files.readAllLines(path1)){
                results[index++] = line;
            }
            return results;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static void writeFile(String path, String content, boolean append, boolean newLine){         //This method writes file.
        PrintStream ps = null;
        try {
            ps = new PrintStream(new FileOutputStream(path,append));
            ps.print(content + (newLine ? "\n" : ""));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }finally {
            if (ps != null){
                ps.flush();
                ps.close();
            }
        }
    }

}

class University{
    public String name;
    public Object[] personnels;             //Object[] array stores every personnel. I used Object[] array because there was a lot of personnel class.
    public int count;
    public int sizeofPersonnels;
    public University(String name){
        setName(name);
        personnels = new Object[1];
        count = 0;
        sizeofPersonnels = 1;
    }
    public void addPersonnel(Object personnel){
        if (count == sizeofPersonnels){
            growSize();
        }
        personnels[count++] = personnel;
    }

    public void growSize(){
        Object[] temp;
        temp = new Object[sizeofPersonnels + 1];            //For dynamic array usage. It increases the array size and add old items.
        for (int i = 0; i < sizeofPersonnels; i++){
            temp[i] = personnels[i];
        }
        personnels = temp;
        sizeofPersonnels++;
    }
    public void setName(String name){
        this.name  = name;
    }
}

class Personnel{
    protected String name;
    protected String surname;
    private String registerNumber;
    protected String position;
    protected int startingYear;
    protected int[] workingHours;
    protected final int  currentYear = 2023;
    protected final int baseSalary = 2600;
    protected int totalSalary;
    protected Personnel(String name, String surname, String registerNumber, String position, int startingYear, int[] workingHours){
        this.name = name;
        this.surname = surname;
        setRegisterNumber(registerNumber);
        this.position = position;
        this.startingYear = startingYear;
        this.workingHours = workingHours;
    }
    protected void setRegisterNumber(String registerNumber){
        this.registerNumber = registerNumber;
    }
    public String getRegisterNumber(){
        return registerNumber;
    }
    protected int severancePay(){
        return (currentYear - startingYear) * 16;
    }
    protected int ssBenefits(int percentage){
        return baseSalary * percentage / 100;
    }
    protected int overWorkSalary(int multiplier, int maxHour){
        int monthlyExtra = 0;
        if (workingHours != null){
            for (int hours : workingHours){
                if (hours > 40){
                    int extraHours = hours-40;
                    if (extraHours > maxHour){
                        extraHours = maxHour;
                    }
                    monthlyExtra += multiplier * extraHours;
                }
            }
        }
        return monthlyExtra;
    }
    protected int hourOfWork(int multiplier, int maxHour, int minHour){
        int monthlyWork = 0;
        if (workingHours != null){
            for (int hours : workingHours){
                if (hours >= maxHour){
                    monthlyWork += maxHour * multiplier;
                } else if (hours >= minHour){
                    monthlyWork += hours * multiplier;
                }
            }
        }
        return monthlyWork;
    }
}

class Academician extends Personnel{
    protected Academician(String name, String surname, String registerNumber, String position, int startingYear, int[] workingHours) {
        super(name, surname, registerNumber, position, startingYear,workingHours);
    }

}

class FacultyMember extends Academician{
    protected FacultyMember(String name, String surname, String registerNumber, String position, int startingYear, int[] workingHours) {
        super(name, surname, registerNumber, position, startingYear,workingHours);
    }
    protected int addCourseFee(){
        int monthlyFee = 0;
        if (workingHours != null){
            for (int hours : workingHours){
                if (hours > 40){
                    int extraHours = hours-40;
                    if (extraHours > 8){
                        extraHours = 8;
                    }
                    monthlyFee += extraHours * 20;
                }
            }
        }
        return monthlyFee;
    }
}

class ResearchAssistant extends Academician{
    protected ResearchAssistant(String name, String surname, String registerNumber, String position, int startingYear, int[] workingHours) {    //calls its super
        super(name, surname, registerNumber, position, startingYear,workingHours);
    }
}

class Officer extends Personnel{
    protected Officer(String name, String surname, String registerNumber, String position, int startingYear, int[] workingHours) {      //calls its super
        super(name, surname, registerNumber, position, startingYear,workingHours);
    }
}

class Employee extends Personnel{
    protected Employee(String name, String surname, String registerNumber, String position, int startingYear,int[] workingHours) {      //calls its super
        super(name, surname, registerNumber, position, startingYear,workingHours);
    }
}

class PartTimeEmployee extends Employee{
    protected PartTimeEmployee(String name, String surname, String registerNumber, String position, int startingYear, int[] workingHours) {     //calls its super
        super(name, surname, registerNumber, position, startingYear,workingHours);
    }
}

class FullTimeEmployee extends Employee{
    protected FullTimeEmployee(String name, String surname, String registerNumber, String position, int startingYear, int[] workingHours) {         //calls its super
        super(name, surname, registerNumber, position, startingYear,workingHours);
    }
    protected int dayOfWork(int multiplier){
        if (workingHours != null){
            int weekLength = workingHours.length;
            return weekLength * 5 * multiplier;             // weeks * days * multiplier(which is 105TL for workers and 125TL for chiefs)
        }
        return 0;
    }
}

class Worker extends FullTimeEmployee{

    protected Worker(String name, String surname, String registerNumber, String position, int startingYear, int[] workingHours) {               //calls its super
        super(name, surname, registerNumber, position, startingYear,workingHours);
    }
}

class Chief extends FullTimeEmployee{

    protected Chief(String name, String surname, String registerNumber, String position, int startingYear, int[] workingHours) {                //calls its super
        super(name, surname, registerNumber, position, startingYear,workingHours);
    }
}

class Security extends Personnel{
    protected Security(String name, String surname, String registerNumber, String position, int startingYear, int[] workingHours) {
        super(name, surname, registerNumber, position, startingYear,workingHours);                                                              //calls its super
    }
    protected int transMoney(){
        if (workingHours != null){
             int weekLength= workingHours.length;
             return weekLength * 6 * 5;             // weeks * days * transportation money per day
        }
        return 0;
    }
    protected int foodMoney(){
        if (workingHours != null){
            int weekLength = workingHours.length;
            return weekLength * 6 * 10;             // weeks * days * food money per day
        }
        return 0;
    }
}

