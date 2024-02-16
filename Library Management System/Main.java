import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        String[] inputFile = FileInput.readFile(args[0],true,true);
        String outputFile = args[1];
        int bookid = 1;
        int memberid = 1;
        getTheHistory library = new getTheHistory();
        for (String line : inputFile){
            String command = line.split("\t")[0];
            if (command.equals("addBook")){
                if (bookid < 1000000){
                    char type = (line.split("\t")[1]).charAt(0);
                    library.addBook(bookid,type);
                    FileOutput.writeToFile(outputFile, "Created new book: " + (type == 'P' ? "Printed " : "Handwritten ")+"[id: "+bookid+"]",true,true);
                    bookid++;
                }else {
                    FileOutput.writeToFile(outputFile,"Library is full with books!",true,true);
                }
            } else if (command.equals("addMember")) {
                if (memberid < 1000000){
                    char type = (line.split("\t")[1]).charAt(0);
                    library.addMember(memberid,type);
                    FileOutput.writeToFile(outputFile, "Created new member: " + (type == 'S' ? "Student " : "Academic ")+"[id: "+memberid+"]",true,true);
                    memberid++;
                }else {
                    FileOutput.writeToFile(outputFile,"Library's System is full with members!",true,true);
                }
            } else if (command.equals("borrowBook")) {
                int bookId = Integer.parseInt(line.split("\t")[1]);
                int memberId = Integer.parseInt(line.split("\t")[2]);
                String date = line.split("\t")[3];
                library.borrowBook(bookId,memberId,date,outputFile);
                
            } else if (command.equals("readInLibrary")) {
                int bookId = Integer.parseInt(line.split("\t")[1]);
                int memberId = Integer.parseInt(line.split("\t")[2]);
                String date = line.split("\t")[3];
                library.readInLibrary(bookId,memberId,date,outputFile);
                
            } else if (command.equals("returnBook")) {
                int bookId = Integer.parseInt(line.split("\t")[1]);
                int memberId = Integer.parseInt(line.split("\t")[2]);
                String date = line.split("\t")[3];
                library.returnBook(bookId,memberId,date,outputFile);
                
            } else if (command.equals("getTheHistory")) {
                library.getTheHistory(outputFile);
            } else if (command.equals("extendBook")) {
                int bookId = Integer.parseInt(line.split("\t")[1]);
                int memberId = Integer.parseInt(line.split("\t")[2]);
                String date = line.split("\t")[3];
                library.extendBook(bookId,memberId,date,outputFile);
            }else {
                FileOutput.writeToFile(outputFile,"Invalid command!",true,true);
            }
        }
    }
}

class FileInput {

    public static String[] readFile(String path, boolean discardEmptyLines, boolean trim) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path)); //Gets the content of file to the list.
            if (discardEmptyLines) { //Removes the lines that are empty with respect to trim.
                lines.removeIf(line -> line.trim().equals(""));
            }
            if (trim) { //Trims each line.
                lines.replaceAll(String::trim);
            }
            return lines.toArray(new String[0]);
        } catch (IOException e) { //Returns null if there is no such a file.
            e.printStackTrace();
            return null;
        }
    }
}
class FileOutput {

    public static void writeToFile(String path, String content, boolean append, boolean newLine) {
        PrintStream ps = null;
        try {
            ps = new PrintStream(new FileOutputStream(path, append));
            ps.print(content + (newLine ? "\n" : ""));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) { //Flushes all the content and closes the stream if it has been successfully created.
                ps.flush();
                ps.close();
            }
        }
    }
}

class Library{
    HashMap<Integer,Character> books = new HashMap<Integer,Character>();
    HashMap<Integer,Character> members = new HashMap<Integer,Character>();
    HashMap<Integer, Integer> borrowedbooks = new HashMap<Integer,Integer>();
    HashMap<Integer,Integer> readinlibrarybooks = new HashMap<Integer,Integer>();
    HashMap<Integer,String> borroweddate = new HashMap<Integer,String>();
    HashMap<Integer,String> readinlibrarydate = new HashMap<Integer,String>();
    HashMap<Integer,Integer> extendbook = new HashMap<Integer,Integer>();

}
class Add extends Library{
    public void addBook(int bookId, char type){
        books.put(bookId,type);
    }
    public void addMember(int memberID, char type){
        members.put(memberID,type);
    }
}
class BookTransactions extends Add {
    public void borrowBook(int bookid,int memberid, String date,String outputfile){
        int flag1 = 0;
        int flag2 = 0;
        for (int j : books.keySet()){
            if (j == bookid){
                flag1 = 1;
                break;
            }
        }
        if (flag1 == 1){
            for (int i : borrowedbooks.keySet()){
                if (i == bookid){
                    FileOutput.writeToFile(outputfile,"You cannot borrow this book!",true,true);
                    flag2 = 1;
                    break;
                }
            }
            for (int i : readinlibrarybooks.keySet()){
                if (i == bookid){
                    FileOutput.writeToFile(outputfile,"You cannot borrow this book!",true,true);
                    flag2 = 1;
                    break;
                }
            }
            if (flag2 != 1){
                for (int k : members.keySet()){
                    if (k == memberid){
                        flag2 = 1;
                        break;
                    }
                }
                if (flag2 == 1){
                    char type = books.get(bookid);
                    if (type == 'H'){
                        FileOutput.writeToFile(outputfile,"You cannot borrow this book!",true,true);
                    }else {
                        int bookcounter = 0;
                        if (members.get(memberid) == 'S'){
                            for (int i : borrowedbooks.keySet()){
                                if (borrowedbooks.get(i) == memberid){
                                    bookcounter++;
                                }
                            }
                            if (bookcounter >= 2){
                                FileOutput.writeToFile(outputfile,"You have exceeded the borrowing limit!",true,true);
                            }else {
                                borroweddate.put(bookid,date);
                                borrowedbooks.put(bookid,memberid);
                                FileOutput.writeToFile(outputfile,"The book ["+bookid+"] "+"was borrowed by member ["+memberid+"] at "+date,true,true);
                            }
                        }else {
                            for (int i : borrowedbooks.keySet()){
                                if (borrowedbooks.get(i) == memberid){
                                    bookcounter++;
                                }
                            }
                            if (bookcounter >= 4){
                                FileOutput.writeToFile(outputfile,"You have exceeded the borrowing limit!",true,true);
                            }else {
                                borroweddate.put(bookid,date);
                                borrowedbooks.put(bookid,memberid);
                                FileOutput.writeToFile(outputfile,"The book ["+bookid+"] "+"was borrowed by member ["+memberid+"] at "+date,true,true);
                            }
                        }
                    }
                }else {
                    FileOutput.writeToFile(outputfile,"There is no such a person!",true,true);
                }
            }
        }else {
            FileOutput.writeToFile(outputfile,"There is no such a book!",true,true);
        }

    }
    public void returnBook(int bookid, int memberid, String date, String outputfile){
        int flag1 = 0;
        int flag2 = 0;
        int flag3 = 0;
        for (int j : books.keySet()){
            if (j == bookid){
                flag1 = 1;
                break;
            }
        }
        if (flag1 == 1){
            for (int i : borrowedbooks.keySet()){
                if (i == bookid){
                    flag2 = 1;
                    break;
                }
            }
            for (int i : readinlibrarybooks.keySet()){
                if (i == bookid){
                    flag3 = 1;
                    break;
                }
            }
            if (flag3 == 1){
                for (int i : members.keySet()){
                    if (i == memberid){
                        if (readinlibrarybooks.get(bookid) == memberid){
                            readinlibrarybooks.remove(bookid);
                            readinlibrarydate.remove(bookid);
                            FileOutput.writeToFile(outputfile,"The book ["+bookid+"] was returned by member ["+memberid+"] at "+date+" Fee: 0",true,true);
                        }else {
                            FileOutput.writeToFile(outputfile,"Member wasnot reading this book!",true,true);
                        }
                    }
                }
            } else if (flag2 == 1) {
                for (int i : members.keySet()){
                    if (i == memberid){
                        if (borrowedbooks.get(bookid) == memberid){
                                char type = members.get(memberid);
                                if (type == 'S'){
                                    LocalDate firstdate = LocalDate.parse(borroweddate.get(bookid));
                                    LocalDate lastdate = LocalDate.parse(date);
                                    long fee = ChronoUnit.DAYS.between(firstdate,lastdate);
                                    if (fee > 7){
                                        borrowedbooks.remove(bookid);
                                        borroweddate.remove(bookid);
                                        FileOutput.writeToFile(outputfile,"The book ["+bookid+"] was returned by member ["+memberid+"] at "+date+" Fee: "+(fee-7),true,true);
                                    }else {
                                        borrowedbooks.remove(bookid);
                                        borroweddate.remove(bookid);
                                        FileOutput.writeToFile(outputfile,"The book ["+bookid+"] was returned by member ["+memberid+"] at "+date+" Fee: 0",true,true);
                                    }
                                }else {
                                    LocalDate firstdate = LocalDate.parse(borroweddate.get(bookid));
                                    LocalDate lastdate = LocalDate.parse(date);
                                    long fee = ChronoUnit.DAYS.between(firstdate,lastdate);
                                    if (fee > 14){
                                        borrowedbooks.remove(bookid);
                                        borroweddate.remove(bookid);
                                        FileOutput.writeToFile(outputfile,"The book ["+bookid+"] was returned by member ["+memberid+"] at "+date+" Fee: "+(fee-14),true,true);
                                    }else {
                                        borrowedbooks.remove(bookid);
                                        borroweddate.remove(bookid);
                                        FileOutput.writeToFile(outputfile,"The book ["+bookid+"] was returned by member ["+memberid+"] at "+date+" Fee: 0",true,true);
                                    }
                                }
                        }else {
                            FileOutput.writeToFile(outputfile,"Member wasnot borrowed this book!",true,true);
                        }
                    }
                }

            }else {
             FileOutput.writeToFile(outputfile,"Book wasnot borrowed or read by someone!",true,true);
            }
        }else {
            FileOutput.writeToFile(outputfile,"There is no such a book!",true,true);
        }

    }
    public void extendBook(int bookid, int memberid, String date,String outputfile){
        int flag1 = 0;
        int flag2 = 0;
        for (int i : books.keySet()){
            if (i == bookid){
                flag1 = 1;
                break;
            }
        }
        if (flag1 ==1){
            for (int i : borrowedbooks.keySet()){
             if (i == bookid){
                 flag2 = 1;
                 break;
             }
            }
            if (flag2 == 1){
                for (int i : members.keySet()){
                    if (i == memberid){
                        flag1 = 0;
                        break;
                    }
                }
                if (flag1==0){
                    if (borrowedbooks.get(bookid).equals(memberid)){
                        for (int i : extendbook.keySet()){
                            if (i == bookid){
                                flag2 = 0;
                                break;
                            }
                        }
                        if (flag2 == 0){
                            FileOutput.writeToFile(outputfile,"You cannot extend the deadline!",true,true);
                        }else {
                            extendbook.put(bookid,memberid);
                            if (members.get(memberid) == 'S'){
                                LocalDate firstdate =LocalDate.parse(borroweddate.get(bookid));
                                LocalDate newdate = firstdate.plusDays(14);
                                borroweddate.put(bookid,newdate.toString());
                                FileOutput.writeToFile(outputfile,"The deadline of book ["+bookid+"] was extended by member ["+memberid+"] at "+firstdate.plusDays(7).toString(),true,true);
                                FileOutput.writeToFile(outputfile,"New deadline of book ["+bookid+"] is "+newdate.toString(),true,true);
                            }else {
                                LocalDate firstdate = LocalDate.parse(borroweddate.get(bookid));
                                LocalDate newdate = firstdate.plusDays(21);
                                borroweddate.put(bookid,newdate.toString());
                                FileOutput.writeToFile(outputfile,"The deadline of book ["+bookid+"] was extended by member ["+memberid+"] at "+firstdate.plusDays(14).toString(),true,true);
                                FileOutput.writeToFile(outputfile,"New deadline of book ["+bookid+"] is "+newdate.toString(),true,true);
                            }
                        }
                    }else {
                        FileOutput.writeToFile(outputfile,"Member wasnot borrowed this book!",true,true);
                    }
                }else {
                    FileOutput.writeToFile(outputfile,"There is no such a member!",true,true);
                }
            }else {
                FileOutput.writeToFile(outputfile,"Book wasnot borrowed by a member!",true,true);
            }
        }else {
            FileOutput.writeToFile(outputfile,"There is no such a book!",true,true);
        }
    }
    public void readInLibrary(int bookid, int memberid,String date, String outputfile){
        int flag1 = 0;
        int flag2 = 0;
        for (int j : books.keySet()){
            if (j == bookid){
                flag1 = 1;
                break;
            }
        }
        if (flag1 == 1){
            for (int i : borrowedbooks.keySet()){
                if (i == bookid){
                    FileOutput.writeToFile(outputfile,"You can not read this book!",true,true);
                    flag2 = 1;
                    break;
                }
            }
            for (int i : readinlibrarybooks.keySet()){
                if (i == bookid){
                    FileOutput.writeToFile(outputfile,"You cannot read this book!",true,true);
                    flag2 = 1;
                    break;
                }
            }
            if (flag2 != 1){
                for (int k : members.keySet()){
                    if (k == memberid){
                        flag2 = 1;
                        break;
                    }
                }
                if (flag2 == 1){
                    char type = books.get(bookid);
                    if (type == 'H'){
                        if (members.get(memberid) == 'A'){
                            readinlibrarybooks.put(bookid,memberid);
                            readinlibrarydate.put(bookid,date);
                            FileOutput.writeToFile(outputfile,"The book ["+bookid+"] was read in library by member ["+memberid+"] at "+date,true,true);
                        }else {
                            FileOutput.writeToFile(outputfile,"Students can not read handwritten books!",true,true);
                        }
                    }else {
                        readinlibrarybooks.put(bookid,memberid);
                        readinlibrarydate.put(bookid,date);
                        FileOutput.writeToFile(outputfile,"The book ["+bookid+"] was read in library by member ["+memberid+"] at "+date,true,true);
                    }
                }else {
                    FileOutput.writeToFile(outputfile,"There is no such a member!",true,true);
                }
            }
        }else {
            FileOutput.writeToFile(outputfile,"There is no such a book!",true,true);
        }

    }
}
class getTheHistory extends BookTransactions{
    public void getTheHistory(String outputfile){
        int student = 0;
        int academic = 0;
        int printedbook = 0;
        int handwrittenbook = 0;
        for (int i : members.keySet()){
            if (members.get(i) == 'S'){
                student++;
            }else {
                academic++;
            }
        }
        for (int i : books.keySet()){
            if (books.get(i) == 'P'){
                printedbook++;
            }else {
                handwrittenbook++;
            }
        }
        FileOutput.writeToFile(outputfile,"History of library:\n",true,true);
        FileOutput.writeToFile(outputfile,"Number of students: "+student,true,true);
        for (int i : members.keySet()){
            if (members.get(i) == 'S'){
                FileOutput.writeToFile(outputfile,"Student [id: "+i+"]",true,true);
            }
        }
        FileOutput.writeToFile(outputfile,"",true,true);
        FileOutput.writeToFile(outputfile,"Number of academics: "+academic,true,true);
        for (int i : members.keySet()){
            if (members.get(i) == 'A'){
                FileOutput.writeToFile(outputfile,"Academic [id: "+i+"]",true,true);
            }
        }
        FileOutput.writeToFile(outputfile,"",true,true);
        FileOutput.writeToFile(outputfile,"Number of printed books: "+printedbook,true,true);
        for (int i : books.keySet()){
            if (books.get(i) == 'P'){
                FileOutput.writeToFile(outputfile,"Printed [id: "+i+"]",true,true);
            }
        }
        FileOutput.writeToFile(outputfile,"",true,true);
        FileOutput.writeToFile(outputfile,"Number of handwritten books: "+handwrittenbook,true,true);
        for (int i : books.keySet()){
            if (books.get(i) == 'H'){
                FileOutput.writeToFile(outputfile,"Handwritten [id: "+i+"]",true,true);
            }
        }
        FileOutput.writeToFile(outputfile,"",true,true);
        FileOutput.writeToFile(outputfile,"Number of borrowed books: "+ borrowedbooks.size(),true,true);
        for (int i : borrowedbooks.keySet()){
            FileOutput.writeToFile(outputfile,"The book ["+i+"] was borrowed by member ["+borrowedbooks.get(i)+"] at "+borroweddate.get(i),true,true);
        }
        FileOutput.writeToFile(outputfile,"",true,true);
        FileOutput.writeToFile(outputfile,"Number of books read in library: "+readinlibrarybooks.size(),true,true);
        for (int i : readinlibrarybooks.keySet()){
            FileOutput.writeToFile(outputfile,"The book ["+i+"] was read in library by member ["+readinlibrarybooks.get(i)+"] at "+readinlibrarydate.get(i),true,true);
        }

    }
}













