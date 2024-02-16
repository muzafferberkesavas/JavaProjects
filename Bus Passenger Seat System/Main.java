import java.util.Scanner;
//Muzaffer Berke Savaş b2220356044
public class Main{
    public static void main(String[] args){
        Bus bus = new Bus("06 HUBM 06",42);
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("1-Book a seat");
            System.out.println("2-Cancel booking");
            System.out.println("3-Print all passengers");
            System.out.println("4-Print available seats");
            System.out.println("5-Print all seats");
            System.out.println("6-Search passenger");
            System.out.println("7-Exit");
            System.out.print("Enter your choose: ");
            int command = scanner.nextInt();
            if (command == 1){
                try {
                    System.out.print("Enter seat id:");
                    int seatID = scanner.nextInt();
                    System.out.print("Enter name:");
                    String empty = scanner.nextLine();
                    String name = scanner.nextLine();
                    System.out.print("Enter surname:");
                    String surname = scanner.nextLine();
                    System.out.print("Enter gender:");
                    String gender = scanner.nextLine();
                    System.out.print("Enter country code:");
                    String countryCode = scanner.nextLine();
                    System.out.print("Enter code:");
                    String code = scanner.nextLine();
                    System.out.print("Enter number:");
                    int number = scanner.nextInt();
                    System.out.print("Enter type:");
                    String empty2 = scanner.nextLine();
                    String type = scanner.nextLine();
                    if (countryCode.isEmpty()) {
                        Phone phone = new Phone(code, number, type);
                        Passenger p = new Passenger(name, surname, gender, phone);
                        bus.bookSeat(p, seatID);
                    } else {
                        Phone phone = new Phone(countryCode, code, number, type);
                        Passenger p = new Passenger(name, surname, gender, phone);
                        bus.bookSeat(p, seatID);
                    }
                }catch (Exception e){
                    System.out.println("Please enter a valid input!");
                }
            } else if (command == 2) {
                System.out.print("Enter name:");
                String name = scanner.next();
                System.out.print("Enter surname:");
                String surname = scanner.next();
                int i = 0;
                for (Seat seat : bus.getSeats()){
                    if (seat.isStatus() && seat.getP().getName().equals(name) && seat.getP().getSurname().equals(surname)){
                        seat.setStatus(false);
                        seat.setP(null);
                        System.out.println("Book canceled for "+name+" "+surname);
                        i++;
                        break;
                    }
                }
                if (i==0){
                    System.out.println("Passenger not found!");
                }
            } else if (command == 3) {
                bus.printAllPassengers();
            } else if (command == 4) {
                bus.printAllAvailableSeatIDs();
            } else if (command == 5) {
                for (Seat seat : bus.getSeats()){
                    seat.display(seat);
                }
            } else if (command == 6) {
                System.out.print("Enter name:");
                String name = scanner.next();
                System.out.print("Enter surname:");
                String surname = scanner.next();
                bus.search(name,surname);
            } else if (command == 7) {
                scanner.close();
                break;
            }else {
                System.out.println("Invalid choose! Try again!");
            }
        }
    }
}

class Phone{
    public String countryCode;
    public String code;
    public int number;
    public String type;
    public String getCountryCode(){
        return countryCode;
    }
    public String getCode(){
        return code;
    }
    public int getNumber(){
        return number;
    }
    public String getType(){
        return type;
    }
    public void setCountryCode(String countryCode){
        this.countryCode = countryCode;
    }
    public void setCode(String code){
        this.code = code;
    }
    public void setNumber(int number){
        this.number = number;
    }
    public void setType(String type){
        this.type = type;
    }
    public void display(){
        System.out.println(type+" Phone:"+countryCode+" "+code+" "+number);
    }

public Phone(String countryCode,String code,int number,String type){
    setCountryCode(countryCode);
    setCode(code);
    setNumber(number);
    setType(type);
}
public Phone(String code,int number,String type){
    setCountryCode("+90");
    setCode(code);
    setNumber(number);
    setType(type);
}

}
class Passenger{
    public String name;
    public String surname;
    public String gender;
    public Phone phone;

    public String getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }
    public String getGender(){
        return gender;
    }
    public Phone getPhone(){
        return phone;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public void setPhone(Phone phone){
        this.phone = phone;
    }
    public void display(){
        System.out.println(name+" "+surname+" ("+gender+")");
        phone.display();
    }
public Passenger(String name,String surname,String gender,Phone phone){
    setName(name);
    setSurname(surname);
    setGender(gender);
    setPhone(phone);
}
}
class Seat{
    public int seatID;
    public boolean status;
    public Passenger p;

    public int getSeatID(){
        return seatID;
    }
    public boolean isStatus(){
        return status;
    }
    public Passenger getP(){
        return p;
    }
    public void setSeatID(int seatID){
        this.seatID = seatID;
    }
    public void setStatus(boolean status){
        this.status = status;
    }
    public void setP(Passenger p){
        this.p = p;
    }
    public void display(Seat seat){
        if (status){
            System.out.println("Seat: "+seat.getSeatID()+" Status: Reserved");
        }else {
            System.out.println("Seat: "+seat.getSeatID()+" Status: Unreserved");
        }
    }

    public Seat(int seatID,boolean status, Passenger p){
        setSeatID(seatID);
        setStatus(status);
        setP(p);
    }


}
class Bus{
    public String plate;
    public int seatCount;
    public Seat[] seats;
    public String getPlate(){
        return plate;
    }
    public int getSeatCount(){
        return seatCount;
    }
    public Seat[] getSeats(){
        return seats;
    }
    public void setPlate(String plate){
        this.plate = plate;
    }
    public void setSeatCount(int seatCount){
        this.seatCount = seatCount;
    }

    Bus(String plate,int seatCount){
        setPlate(plate);
        setSeatCount(seatCount);
        this.seats = new Seat[seatCount];
        for (int i = 0; i < seatCount; i++){
            seats[i] = new Seat(i+1,false,null);
        }
    }
    public void bookSeat(Passenger p,int seatID){
        if (seatID >= 1 && seatID <= seatCount){
            Seat seat = seats[seatID-1];
            if (!seat.isStatus()){
                seat.setStatus(true);
                seat.setP(p);
            }else {
                System.out.println("The seat is already reserved!");
            }
        }else {
            System.out.println("Invalid seat number!");
        }
    }
    public void printAllPassengers(){
        for (Seat seat : seats){
            if (seat.isStatus()){
                System.out.println("Seat:"+ seat.seatID + " Status: Reserved");
                seat.getP().display();
            }
        }
    }
    public void printAllAvailableSeatIDs(){
        for (Seat seat : seats){
            if (!seat.isStatus()){
                System.out.print(seat.getSeatID()+" ");
            }
        }
        System.out.println();
    }
    public void search(String name, String surname){
        for (Seat seat : seats){
            if (seat.isStatus() && seat.getP().getName().equals(name) && seat.getP().getSurname().equals(surname)){
                seat.getP().display();
                return;
            }
        }
        System.out.println("Passenger not found!");
    }
}