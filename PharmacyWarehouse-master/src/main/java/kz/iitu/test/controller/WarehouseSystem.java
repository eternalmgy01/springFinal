//package kz.iitu.test.controller;
//
//import kz.iitu.test.entity.Medicine;
//import kz.iitu.test.entity.Request;
//import kz.iitu.test.service.MedicineService;
//import kz.iitu.test.service.RequestService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//
//import javax.annotation.PostConstruct;
//import java.util.Scanner;
//@Controller
//public class WarehouseSystem {
//    @Autowired
//    private MedicineService medicineService;
//    @Autowired
//    private RequestService requestService;
//    @Autowired
//    Goal goal = new Goal();
//    Scanner scan = new Scanner(System.in);
//    int menu;
//    Long accepted = goal.getAccepted();
//    Long goals = goal.getGoal();
//    @PostConstruct
//   void showMenu() {
//        System.out.println("\n================SYSTEM=====================");
//        System.out.println("1. Warehouse side");
//        System.out.println("2. Pharmacy side");
//        System.out.println("3. Exit");
//        System.out.println("===========================================\n");
//        menu = scan.nextInt();
//        while (menu != 3) {
//            switch (menu) {
//                case 1: {
//                    showWarehouseMenu();
//                    break;
//                }
//                case 2: {
//                    showPharmacyMenu();
//                    break;
//                }
//                case 3: {
//                    System.out.println("Good Bye!");
//                    break;
//                }
//                default:
//                {
//                    System.out.println("Wrong input.");
//                    break;
//                }
//            }
//        }
//
//    }
//    void showWarehouseMenu(){
//        Long left = goals - accepted;
//        System.out.println("\n==============WAREHOUSE====================");
//        System.out.println("1. Get medicines");
//        System.out.println("2. Set goal");
//        System.out.println("3. List of requests");
//        System.out.println("4. List of medicines");
//        System.out.println("5. Accept requests");
//        System.out.println("6. Back");
//        System.out.println("\nGoal: " + goal.getAccepted() + "/" + goal.getGoal() + "\n");
//
//        System.out.println("===========================================\n");
//        int choice = scan.nextInt();
//        switch (choice) {
//            case 1: {
//                medicineService.getMedicines();
//                break;
//            }
//            case 2: {
//                System.out.println("Current goal is :" + goal.getGoal());
//                System.out.println("Enter the goal: ");
//                Long newgoal = scan.nextLong();
//                goal.setGoal(newgoal);
//                goal.setAccepted(Long.valueOf(0));
//                System.out.println("New goal is: " + goal.getGoal());
//                break;
//            }
//            case 3: {
//                if (requestService.findAll().size() == 0) {
//                    System.out.println("There is no requests for now.");
//                }
//                else {
//                    System.out.println("List of requests: ");
//                    for (Request req: requestService.findAll()) {
//                        System.out.println(req.getId() + " - Name: " + req.getName() + " - Manufacturer: " + req.getManufacturer() +
//                                " - Quantity: " + req.getQuantity());
//                    }
//                }
//                break;
//            }
//            case 4: {
//                if (medicineService.findAll().size() == 0) {
//                    System.out.println("There is no requests for now.");
//                }
//                else {
//                    System.out.println("List of medicines: ");
//                    for (Medicine req: medicineService.findAll()) {
//                        System.out.println(req.getId() + " - Name: " + req.getName() + " - Manufacturer: " + req.getManufacturer() +
//                                " - Quantity: " + req.getQuantity() + " - Price: " + req.getPrice());
//                    }
//                }
//                break;
//            }
//            case 5: {
//                medicineService.acceptAll();
//                break;
//            }
//            case 6: {
//                this.showMenu();
//                break;
//            }
//            default:
//            {
//                System.out.println("Wrong input.");
//            }
//        }
//    }
//    void showPharmacyMenu() {
//        System.out.println("\n================PHARMACY===================");
//        System.out.println("1. Make request");
//        System.out.println("2. List of medicines");
//        System.out.println("3. Back");
//        System.out.println("===========================================\n");
//        int choice = scan.nextInt();
//        switch (choice) {
//            case 1: {
//                System.out.println("Enter the name: ");
//                String name = scan.next();
//                System.out.println("Enter the manufacturer: ");
//                String manufacturer = scan.next();
//                System.out.println("How many drugs do you want to receive: ");
//                Long quantity = scan.nextLong();
//                requestService.makeRequest(name, manufacturer, quantity);
//                break;
//            }
//            case 2: {
//                if (medicineService.findAll().size() == 0) {
//                    System.out.println("The warehouse is empty for now.");
//                }
//                else {
//                    for(Medicine medicine: medicineService.findAll()) {
//                        System.out.println(medicine.getId() + " - " + medicine.getName() + " - " + medicine.getManufacturer() +
//                                " - " + medicine.getQuantity());
//                    }
//                }
//                break;
//            }
//            case 3: {
//                this.showMenu();
//                break;
//            }
//            default:
//            {
//                System.out.println("Wrong input.");
//            }
//        }
//    }
//}
