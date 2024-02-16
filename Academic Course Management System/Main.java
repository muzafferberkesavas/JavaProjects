import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
//if the code doesn't compile, please try IO/input.txt and chance FILE_PATH's with IO/student.txt , IO/courseEnrollment.txt
//Muzaffer Berke Savaş
interface DAO<T>{
    T getById(int id);
    T deleteById(int id);
    void add(T object);

    void readFile();
    void writeFile();
    ArrayList<T> getAll();
}

interface Assessment{

    String printTasks();
    int fee();
}

class Base implements Assessment{

    public String printTasks(){
        return "";
    }
    public int fee(){
        return 0;
    }
}

class AdditionalTasks extends AssessmentDecorator{

    public AdditionalTasks(Assessment decoratedAssessment, String assessmentType){
        super(decoratedAssessment,assessmentType);
    }
    @Override
    public String printTasks() {
        return super.printTasks()+"AdditionalTasks ";
    }

    @Override
    public int fee() {
        return super.fee() + 5;
    }



}

abstract class AssessmentDecorator implements Assessment{
    protected Assessment decoratedAssessment;

    public String AssessmentType;

    public AssessmentDecorator(Assessment decoratedAssessment, String assessmentType){
        this.decoratedAssessment = decoratedAssessment;
        this.AssessmentType = assessmentType;
    }

    public String printTasks(){
        return decoratedAssessment.printTasks();
    }
    public int fee(){
        return decoratedAssessment.fee();
    }
    public int typefee(){
        return AssessmentType.equals("MultipleChoice") ? 15 : 10;
    }

}

class Analysis extends AssessmentDecorator{
    public Analysis(Assessment decoratedAssessment,String assessmentType){
        super(decoratedAssessment,assessmentType);
    }
    public String printTasks(){
        return super.printTasks() +"Analysis ";
    }
    public int fee(){
        return super.fee() + 10;
    }

}

class QuestionSet extends AssessmentDecorator{
    public QuestionSet(Assessment decoratedAssessment, String assessmentType){
        super(decoratedAssessment, assessmentType);
    }
    public String printTasks(){
        return super.printTasks()+"QuestionSet ";
    }
    public int fee(){
        return super.fee() + 7;
    }
}

class LiteratureReview extends AssessmentDecorator{
    public LiteratureReview(Assessment decoratedAssessment, String assessmentType){
        super(decoratedAssessment, assessmentType);
    }
    public String printTasks(){
        return super.printTasks()+"LiteratureReview ";
    }
    public int fee(){
        return  super.fee() + 15;
    }

}

class Student{
    private int id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String address;

    public Student(int id, String name, String surname, String phoneNumber, String address){
        this.id = id;
        setName(name);
        setSurname(surname);
        setPhoneNumber(phoneNumber);
        setAddress(address);
    }
    public int getId() {
        return id;
    }
    public String getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public String getAddress(){
        return address;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public void setAddress(String address){
        this.address = address;
    }
}

class StudentDao implements DAO<Student>{
    private static final String FILE_PATH = "student.txt";
    private ArrayList<Student> students;

    public StudentDao(){
        students = new ArrayList<Student>();
        readFile();
    }

    @Override
    public Student getById(int id) {
        for (Student student : getAll()){
            if (student.getId() == id){
                return student;
            }
        }
        return null;
    }

    @Override
    public Student deleteById(int id) {
        if (getById(id) != null){
            Student student = getById(id);
            getAll().remove(student);
            writeFile();
            return student;
        }
        return null;
    }

    @Override
    public void add(Student object) {
        if (object != null){
            getAll().add(object);
            writeFile();
        }
    }

    @Override
    public void readFile() {
        try {
            int index = 0;
            Path path1 = Paths.get(FILE_PATH);
            int length = Files.readAllLines(path1).size();
            String[] results = new String[length];
            for (String line : Files.readAllLines(path1)){
                results[index++] = line;
            }
            for (String line : results){
                int id = Integer.parseInt(line.split("\t")[0]);
                String name = line.split("\t")[1].split(" ")[0];
                String surname = line.split("\t")[1].split(" ")[1];
                String phoneNumber = line.split("\t")[2];
                String address = line.split("\t")[3];
                Student student = new Student(id,name,surname,phoneNumber,address);
                add(student);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void writeFile() {
        PrintStream ps0 = null;
        try {
            ps0 = new PrintStream(new FileOutputStream(FILE_PATH,false));
            ps0.print("");
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }finally {
            if (ps0 != null){
                ps0.flush();
                ps0.close();
            }
        }
        if (getAll() != null){
            for (Student student : getAll()){
                PrintStream ps = null;
                try {
                    ps = new PrintStream(new FileOutputStream(FILE_PATH,true));
                    ps.print(student.getId()+"\t"+student.getName()+" "+student.getSurname()+"\t"+student.getPhoneNumber()+"\t"+student.getAddress()+ "\n");
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
    }

    @Override
    public ArrayList<Student> getAll() {
        return students;
    }
}

class Enrollment{
    private int enrollmentId;
    private int studentId;
    private ArrayList<AssessmentDecorator> assessmentDecorators;
    public Enrollment(int enrollmentId, int studentId){
        setEnrollmentId(enrollmentId);
        setStudentId(studentId);
        assessmentDecorators = new ArrayList<AssessmentDecorator>();
    }
    public int getEnrollmentId(){
        return enrollmentId;
    }
    public int getStudentId(){
        return studentId;
    }
    public ArrayList<AssessmentDecorator> getAssessmentDecorator(){
        return assessmentDecorators;
    }
    public void setAssessmentDecorators(AssessmentDecorator assessmentdecorator){
        assessmentDecorators.add(assessmentdecorator);
    }
    public void setEnrollmentId(int enrollmentId){
        this.enrollmentId = enrollmentId;
    }
    public void setStudentId(int studentId){
        this.studentId = studentId;
    }
}


class EnrollmentDao implements DAO<Enrollment>{
    private static final String FILE_PATH = "courseEnrollment.txt";
    private ArrayList<Enrollment> enrollments;

    public EnrollmentDao(){
        enrollments = new ArrayList<Enrollment>();
        readFile();
    }

    @Override
    public Enrollment getById(int id) {
        for (Enrollment enrollment : getAll()){
            if (id == enrollment.getEnrollmentId()){
                return enrollment;
            }
        }
        return null;
    }

    @Override
    public Enrollment deleteById(int id) {
        if (getById(id) != null){
            Enrollment enrollment = getById(id);
            getAll().remove(enrollment);
            writeFile();
            return enrollment;
        }
        return null;
    }

    @Override
    public void add(Enrollment object) {
        if (object != null){
            getAll().add(object);
            writeFile();
        }
    }


    @Override
    public void readFile() {
        try {
            int index = 0;
            Path path1 = Paths.get(FILE_PATH);
            int length = Files.readAllLines(path1).size();
            String[] results = new String[length];
            for (String line : Files.readAllLines(path1)){
                results[index++] = line;
            }
            index = -1;
            for (String line : results){
                try{
                    int enrollment_id = Integer.parseInt(line.split("\t")[0]);
                    int student_id = Integer.parseInt(line.split("\t")[1]);
                    Enrollment enrollment = new Enrollment(enrollment_id,student_id);
                    add(enrollment);
                    index++;
                }catch (Exception e){
                    if (index != -1){
                        String assessmentType = line.split("\t")[0];
                        String[] tasks = line.split("\t")[1].split(" ");
                        switch (tasks.length){
                            case 1:
                                if (tasks[0].equals("LiteratureReview")){
                                    AssessmentDecorator assessment = new LiteratureReview(new Base(),assessmentType);
                                    getAll().get(index).setAssessmentDecorators(assessment);
                                    writeFile();
                                } else if (tasks[0].equals("Analysis")) {
                                    AssessmentDecorator assessment = new Analysis(new Base(),assessmentType);
                                    getAll().get(index).setAssessmentDecorators(assessment);
                                    writeFile();
                                } else if (tasks[0].equals("QuestionSet")) {
                                    AssessmentDecorator assessment = new QuestionSet(new Base(),assessmentType);
                                    getAll().get(index).setAssessmentDecorators(assessment);
                                    writeFile();
                                } else if (tasks[0].equals("AdditionalTasks")) {
                                    AssessmentDecorator assessment = new AdditionalTasks(new Base(), assessmentType);
                                    getAll().get(index).setAssessmentDecorators(assessment);
                                    writeFile();
                                }
                                break;
                            case 2:
                                if (Arrays.asList(tasks).contains("LiteratureReview") && Arrays.asList(tasks).contains("Analysis")){
                                    AssessmentDecorator assessment = new LiteratureReview(new Analysis(new Base(),assessmentType),assessmentType);
                                    getAll().get(index).setAssessmentDecorators(assessment);
                                    writeFile();
                                } else if (Arrays.asList(tasks).contains("LiteratureReview") && Arrays.asList(tasks).contains("QuestionSet")) {
                                    AssessmentDecorator assessment = new LiteratureReview(new QuestionSet(new Base(),assessmentType),assessmentType);
                                    getAll().get(index).setAssessmentDecorators(assessment);
                                    writeFile();
                                } else if (Arrays.asList(tasks).contains("LiteratureReview") && Arrays.asList(tasks).contains("AdditionalTasks")) {
                                    AssessmentDecorator assessment = new LiteratureReview(new AdditionalTasks(new Base(),assessmentType),assessmentType);
                                    getAll().get(index).setAssessmentDecorators(assessment);
                                    writeFile();
                                } else if (Arrays.asList(tasks).contains("Analysis") && Arrays.asList(tasks).contains("QuestionSet")) {
                                    AssessmentDecorator assessment = new Analysis(new QuestionSet(new Base(),assessmentType),assessmentType);
                                    getAll().get(index).setAssessmentDecorators(assessment);
                                    writeFile();
                                } else if (Arrays.asList(tasks).contains("Analysis") && Arrays.asList(tasks).contains("AdditionalTasks")) {
                                    AssessmentDecorator assessment = new Analysis(new AdditionalTasks(new Base(),assessmentType),assessmentType);
                                    getAll().get(index).setAssessmentDecorators(assessment);
                                    writeFile();
                                } else if (Arrays.asList(tasks).contains("QuestionSet") && Arrays.asList(tasks).contains("AdditionalTasks")) {
                                    AssessmentDecorator assessment = new QuestionSet(new AdditionalTasks(new Base(),assessmentType),assessmentType);
                                    getAll().get(index).setAssessmentDecorators(assessment);
                                    writeFile();
                                }
                                break;
                            case 3:
                                if (Arrays.asList(tasks).contains("LiteratureReview") && Arrays.asList(tasks).contains("Analysis") && Arrays.asList(tasks).contains("QuestionSet")){
                                    AssessmentDecorator assessment = new LiteratureReview(new Analysis(new QuestionSet(new Base(),assessmentType),assessmentType),assessmentType);
                                    getAll().get(index).setAssessmentDecorators(assessment);
                                    writeFile();
                                } else if (Arrays.asList(tasks).contains("LiteratureReview") && Arrays.asList(tasks).contains("Analysis") && Arrays.asList(tasks).contains("AdditionalTasks")) {
                                    AssessmentDecorator assessment = new LiteratureReview(new Analysis(new AdditionalTasks(new Base(),assessmentType),assessmentType),assessmentType);
                                    getAll().get(index).setAssessmentDecorators(assessment);
                                    writeFile();
                                } else if (Arrays.asList(tasks).contains("LiteratureReview") && Arrays.asList(tasks).contains("QuestionSet") && Arrays.asList(tasks).contains("AdditionalTasks")) {
                                    AssessmentDecorator assessment = new LiteratureReview(new QuestionSet(new AdditionalTasks(new Base(),assessmentType),assessmentType),assessmentType);
                                    getAll().get(index).setAssessmentDecorators(assessment);
                                    writeFile();
                                } else if (Arrays.asList(tasks).contains("Analysis") && Arrays.asList(tasks).contains("QuestionSet") && Arrays.asList(tasks).contains("AdditionalTasks")) {
                                    AssessmentDecorator assessment = new Analysis(new QuestionSet(new AdditionalTasks(new Base(),assessmentType),assessmentType),assessmentType);
                                    getAll().get(index).setAssessmentDecorators(assessment);
                                    writeFile();
                                }
                                break;
                            case 4:
                                AssessmentDecorator assessment = new LiteratureReview(new Analysis(new QuestionSet(new AdditionalTasks(new Base(),assessmentType),assessmentType),assessmentType),assessmentType);
                                getAll().get(index).setAssessmentDecorators(assessment);
                                writeFile();
                                break;
                        }
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void writeFile() {
        PrintStream ps0 = null;
        try {
            ps0 = new PrintStream(new FileOutputStream(FILE_PATH,false));
            ps0.print("");
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }finally {
            if (ps0 != null){
                ps0.flush();
                ps0.close();
            }
        }
        if (getAll() != null){
            for (Enrollment enrollment : getAll()){
                PrintStream ps = null;
                try {
                    ps = new PrintStream(new FileOutputStream(FILE_PATH,true));
                    ps.print(enrollment.getEnrollmentId()+"\t"+enrollment.getStudentId()+"\n");
                    if (enrollment.getAssessmentDecorator() != null){
                        for (AssessmentDecorator assessment : enrollment.getAssessmentDecorator()){
                            ps.print(assessment.AssessmentType+"\t"+assessment.printTasks()+"\n");
                        }
                    }
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
    }

    @Override
    public ArrayList<Enrollment> getAll() {
        return enrollments;
    }
}

class File{
    public static String[] readFile(String path){
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

    public static void writeFile(String path, String content, boolean append, boolean newLine){
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

    public static void processFile(String[] lines){
        StudentDao studentDao = new StudentDao();
        EnrollmentDao enrollmentDao = new EnrollmentDao();
        for (String line : lines){
            String command = line.split(" ")[0];
            switch (command){
                case "AddStudent":
                    int id = Integer.parseInt(line.split(" ")[1]);
                    String name = line.split(" ")[2];
                    String surname = line.split(" ")[3];
                    String phoneNumber = line.split(" ")[4];
                    String address = "Address: "+line.substring(name.length()+surname.length()+phoneNumber.length()+command.length()+line.split(" ")[1].length()+5);
                    if (studentDao.getById(id) == null){
                        Student student = new Student(id,name,surname,phoneNumber,address);
                        writeFile("output.txt","Student "+student.getId()+" "+student.getName()+" added",true,true);
                        studentDao.add(student);
                    }else{
                        writeFile("output.txt","Error: There is an another student with that id!",true,true);
                    }
                    break;
                case "RemoveStudent":
                    int id0 = Integer.parseInt(line.split(" ")[1]);
                    if (studentDao.getById(id0) != null){
                        writeFile("output.txt","Student "+id0+" "+studentDao.getById(id0).getName()+" removed",true,true);
                        studentDao.deleteById(id0);
                    }else{
                        writeFile("output.txt","Error: No such a student!",true,true);
                    }
                    break;
                case "CreateEnrollment":
                     int enrollment_id = Integer.parseInt(line.split(" ")[1]);
                     int student_id = Integer.parseInt(line.split(" ")[2]);
                     if (enrollmentDao.getById(enrollment_id) == null){
                         Enrollment enrollment = new Enrollment(enrollment_id,student_id);
                         writeFile("output.txt","CourseEnrollment "+enrollment_id+" created",true,true);
                         enrollmentDao.add(enrollment);
                     }else{
                         writeFile("output.txt","Error: There is an another enrollment with that id!",true,true);
                     }
                    break;
                case "AddAssessment":
                    enrollment_id = Integer.parseInt(line.split(" ")[1]);
                    String assessmentType = line.split(" ")[2];
                    String[] tasks = Arrays.copyOfRange(line.split(" "),3,line.split(" ").length);
                    for (Enrollment enrollment : enrollmentDao.getAll()){
                        if (enrollment.getEnrollmentId() == enrollment_id){
                            int index = enrollmentDao.getAll().indexOf(enrollment);
                            writeFile("output.txt",assessmentType+" assessment added to enrollment "+enrollment_id,true,true);
                            switch (tasks.length){
                                case 1:
                                    if (tasks[0].equals("LiteratureReview")){
                                        AssessmentDecorator assessment = new LiteratureReview(new Base(),assessmentType);
                                        enrollmentDao.getAll().get(index).setAssessmentDecorators(assessment);
                                        enrollmentDao.writeFile();
                                    } else if (tasks[0].equals("Analysis")) {
                                        AssessmentDecorator assessment = new Analysis(new Base(),assessmentType);
                                        enrollmentDao.getAll().get(index).setAssessmentDecorators(assessment);
                                        enrollmentDao.writeFile();
                                    } else if (tasks[0].equals("QuestionSet")) {
                                        AssessmentDecorator assessment = new QuestionSet(new Base(),assessmentType);
                                        enrollmentDao.getAll().get(index).setAssessmentDecorators(assessment);
                                        enrollmentDao.writeFile();
                                    } else if (tasks[0].equals("AdditionalTasks")) {
                                        AssessmentDecorator assessment = new AdditionalTasks(new Base(), assessmentType);
                                        enrollmentDao.getAll().get(index).setAssessmentDecorators(assessment);
                                        enrollmentDao.writeFile();
                                    }
                                    break;
                                case 2:
                                    if (Arrays.asList(tasks).contains("LiteratureReview") && Arrays.asList(tasks).contains("Analysis")){
                                        AssessmentDecorator assessment = new LiteratureReview(new Analysis(new Base(),assessmentType),assessmentType);
                                        enrollmentDao.getAll().get(index).setAssessmentDecorators(assessment);
                                        enrollmentDao.writeFile();
                                    } else if (Arrays.asList(tasks).contains("LiteratureReview") && Arrays.asList(tasks).contains("QuestionSet")) {
                                        AssessmentDecorator assessment = new LiteratureReview(new QuestionSet(new Base(),assessmentType),assessmentType);
                                        enrollmentDao.getAll().get(index).setAssessmentDecorators(assessment);
                                        enrollmentDao.writeFile();
                                    } else if (Arrays.asList(tasks).contains("LiteratureReview") && Arrays.asList(tasks).contains("AdditionalTasks")) {
                                        AssessmentDecorator assessment = new LiteratureReview(new AdditionalTasks(new Base(),assessmentType),assessmentType);
                                        enrollmentDao.getAll().get(index).setAssessmentDecorators(assessment);
                                        enrollmentDao.writeFile();
                                    } else if (Arrays.asList(tasks).contains("Analysis") && Arrays.asList(tasks).contains("QuestionSet")) {
                                        AssessmentDecorator assessment = new Analysis(new QuestionSet(new Base(),assessmentType),assessmentType);
                                        enrollmentDao.getAll().get(index).setAssessmentDecorators(assessment);
                                        enrollmentDao.writeFile();
                                    } else if (Arrays.asList(tasks).contains("Analysis") && Arrays.asList(tasks).contains("AdditionalTasks")) {
                                        AssessmentDecorator assessment = new Analysis(new AdditionalTasks(new Base(),assessmentType),assessmentType);
                                        enrollmentDao.getAll().get(index).setAssessmentDecorators(assessment);
                                        enrollmentDao.writeFile();
                                    } else if (Arrays.asList(tasks).contains("QuestionSet") && Arrays.asList(tasks).contains("AdditionalTasks")) {
                                        AssessmentDecorator assessment = new QuestionSet(new AdditionalTasks(new Base(),assessmentType),assessmentType);
                                        enrollmentDao.getAll().get(index).setAssessmentDecorators(assessment);
                                        enrollmentDao.writeFile();
                                    }
                                    break;
                                case 3:
                                    if (Arrays.asList(tasks).contains("LiteratureReview") && Arrays.asList(tasks).contains("Analysis") && Arrays.asList(tasks).contains("QuestionSet")){
                                        AssessmentDecorator assessment = new LiteratureReview(new Analysis(new QuestionSet(new Base(),assessmentType),assessmentType),assessmentType);
                                        enrollmentDao.getAll().get(index).setAssessmentDecorators(assessment);
                                        enrollmentDao.writeFile();
                                    } else if (Arrays.asList(tasks).contains("LiteratureReview") && Arrays.asList(tasks).contains("Analysis") && Arrays.asList(tasks).contains("AdditionalTasks")) {
                                        AssessmentDecorator assessment = new LiteratureReview(new Analysis(new AdditionalTasks(new Base(),assessmentType),assessmentType),assessmentType);
                                        enrollmentDao.getAll().get(index).setAssessmentDecorators(assessment);
                                        enrollmentDao.writeFile();
                                    } else if (Arrays.asList(tasks).contains("LiteratureReview") && Arrays.asList(tasks).contains("QuestionSet") && Arrays.asList(tasks).contains("AdditionalTasks")) {
                                        AssessmentDecorator assessment = new LiteratureReview(new QuestionSet(new AdditionalTasks(new Base(),assessmentType),assessmentType),assessmentType);
                                        enrollmentDao.getAll().get(index).setAssessmentDecorators(assessment);
                                        enrollmentDao.writeFile();
                                    } else if (Arrays.asList(tasks).contains("Analysis") && Arrays.asList(tasks).contains("QuestionSet") && Arrays.asList(tasks).contains("AdditionalTasks")) {
                                        AssessmentDecorator assessment = new Analysis(new QuestionSet(new AdditionalTasks(new Base(),assessmentType),assessmentType),assessmentType);
                                        enrollmentDao.getAll().get(index).setAssessmentDecorators(assessment);
                                        enrollmentDao.writeFile();
                                    }
                                    break;
                                case 4:
                                    AssessmentDecorator assessment = new LiteratureReview(new Analysis(new QuestionSet(new AdditionalTasks(new Base(),assessmentType),assessmentType),assessmentType),assessmentType);
                                    enrollmentDao.getAll().get(index).setAssessmentDecorators(assessment);
                                    enrollmentDao.writeFile();
                                    break;
                            }
                        }
                    }
                    break;
                case "TotalFee":
                    enrollment_id = Integer.parseInt(line.split(" ")[1]);
                    if (enrollmentDao.getById(enrollment_id) != null){
                        if (enrollmentDao.getById(enrollment_id).getAssessmentDecorator() != null){
                            writeFile("output.txt","TotalFee for enrollment "+enrollment_id,true,true);
                            int total_fee = 0;
                            for (AssessmentDecorator assessment : enrollmentDao.getById(enrollment_id).getAssessmentDecorator()){
                                total_fee += assessment.fee()+ assessment.typefee();
                                writeFile("output.txt","\t"+assessment.AssessmentType+" "+assessment.printTasks()+(assessment.fee()+assessment.typefee())+"$",true,true);

                            }
                            writeFile("output.txt","\tTotal: "+total_fee+"$",true,true);
                        }else {
                            writeFile("output.txt",("Total fee for enrollment "+enrollment_id+"\n"+"\tTotal: 0$").toString(),true,true);
                        }
                    }
                    break;
                case "ListStudents":
                    ArrayList<Student> studentArrayList = studentDao.getAll();
                    Comparator<Student> sortedList = Comparator.comparing(Student::getName);
                    Collections.sort(studentArrayList,sortedList);
                    writeFile("output.txt","Student List:",true,true);
                    for (Student student: studentArrayList){
                        writeFile("output.txt",(student.getId()+" "+student.getName()+" "+student.getSurname()+" "+student.getPhoneNumber()+" "+student.getAddress()).toString(),true,true);
                    }
                    break;
            }
        }
    }
}









public class Main {
    public static void main(String[] args) {
        String file_name = args[0];
        File.processFile(File.readFile(file_name));
    }
}