package dec28;

//import carrental.Customer;
//import carrental.RentalSystem;

import java.util.ArrayList;

class Car{
    String carId,carmodel,companyname;
    boolean isAvailable;
    double basepriceperday;
    public Car(String c_id,String c_model,String c_name,double price){
        this.carId = c_id;
        this.carmodel = c_model;
        this.companyname = c_name;
        this.basepriceperday = price;
        this.isAvailable = true;
    }
    public String getCarId(){
        return carId;
    }
    public String getCarModel(){
        return carmodel;
    }
    public String getCompanyName(){
        return companyname;
    }
    public double getBasepriceperday(){
        return basepriceperday;
    }
    public boolean isAvailable(){
        return isAvailable;
    }
    public void setisAvailable(boolean available){
        isAvailable  = available;
    }

}
class Member{
    String membername,memberId;
    Member(String name,String id){
        this.membername = name;
        this.memberId = id;
    }

    public  String MemberName(){
        return membername;
    }
    public String MemberId(){
        return memberId;
    }
}
class rentalCars{
    ArrayList<Car> totalCars = new ArrayList<>();
    ArrayList<Car> rentedCars = new ArrayList<>();
    ArrayList<Member> totalMembers = new ArrayList<>();
//    rentalCars(){
//        this.totalCars = new ArrayList<>();
//        this.rentedCars = new ArrayList<>();
//        this.totalMembers = new ArrayList<>();
//    }
    public void addCar(Car car){
        totalCars.add(car);
    }
    public void addMembers(Member member){
        totalMembers.add(member);
    }
    public void showCars(){

        if(!totalCars.isEmpty()){
            System.out.println("The list for the available cars is :- ");
            for(Car car : totalCars) {

                if (car.isAvailable()) {
                    System.out.println("Car ID : " + car.getCarId() + "\tCar Model : " + car.getCarModel() +
                            "\tCar company : " + car.getCompanyName() + "\tPer day price : " + car.getBasepriceperday());

                }
            }

        }
        else
            System.out.println("there are no car in the system all car are rented.");
    }





    public void showrentedCars(){

        if(!rentedCars.isEmpty()){
            System.out.println("The list for the available cars is :- ");
            for(Car car : rentedCars) {

                if (!car.isAvailable()) {
                    System.out.println("Car ID : " + car.getCarId() + "\tCar Model : " + car.getCarModel() +
                            "\tCar company : " + car.getCompanyName() + "\tPer day price : " + car.getBasepriceperday());

                }
            }

        }
        else
            System.out.println("you are in side the rented class array");
    }




    public double totalRent(double days, String c_id){
        Car checkCarId  = findCArById(c_id);
        System.out.println(checkCarId.basepriceperday);
        if(checkCarId!=null) {
            System.out.println("enter");
            double ppc = checkCarId.getBasepriceperday();
            return (days * ppc);
//            return 1;
        }
        else
            return 0;

    }
    public void checkOutCar(String carId,String customerId){
        Car checkCarId =findCArById(carId);
        Member checkMemberId = findMemberById(customerId);
        if(checkCarId!=null&&checkMemberId!=null&& checkCarId.isAvailable()){
            checkCarId.setisAvailable(false);
            rentedCars.add(checkCarId);
            totalCars.remove(checkCarId);
            System.out.println("Name of the customer : "+checkMemberId.MemberName()+"\bThe car Name : "+checkCarId.getCompanyName());
        }
        else
            System.out.println("the car and customer are not available");
    }

    private Member findMemberById(String customerId) {
        for(Member member : totalMembers){
            if(member.MemberId().equals(customerId))
                return member;
        }
        return null;
    }

    private Car findCArById(String carId) {
        for(Car car : totalCars){
            if(car.getCarId().equals(carId))
                return car;
        }
        return null;
    }
    private Car findreturnCArById(String carId) {
        for(Car car : rentedCars){
            if(car.getCarId().equals(carId))
                return car;
        }
        return null;
    }
    public void returnCar(String carId){
        Car returnedCar = findreturnCArById(carId);
        if(returnedCar!=null && !returnedCar.isAvailable()){
            returnedCar.setisAvailable(true);

            totalCars.add(returnedCar);
            rentedCars.remove(returnedCar);

            System.out.println("yes, we got the car thankyoou for using us and the returned car name is : "+returnedCar.getCompanyName()+
                    "\tand the car id is : "+returnedCar.getCarId());
        }else {
            System.out.println("Sorry the car id not found or maybe the car is already returned in the system");
        }
    }

}
public class carrentalprogramunderstanding {
    public static void main(String[] args) {
        rentalCars rentalSystem = new rentalCars();

        // Adding some cars
        rentalSystem.addCar(new Car("C001", "ABC Cars", "Swift",90.0));
        rentalSystem.addCar(new Car("C002", "XYZ Rentals", "Desire",60.0));

        // Registering customers
        rentalSystem.addMembers(new Member("Ram","r1"));
        rentalSystem.addMembers(new Member("Shyam", "r2"));

        // Showing available cars
        rentalSystem.showCars();


        // Checking out cars
        rentalSystem.checkOutCar("C001", "r1");
        rentalSystem.checkOutCar("C002", "r2");
        rentalSystem.showCars();
        rentalSystem.showrentedCars();
        // Returning cars
        rentalSystem.returnCar("C001");
        rentalSystem.returnCar("C002");
//
        rentalSystem.showCars();
        System.out.println(rentalSystem.totalRent(3,"C001"));

//        String id = c.getCarId();
    }
}
