/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drugstore;

/**
 *
 * @author 2ndyrGroupB
 */
public class Main {

    public static void main(String[] args) {
        DrugStore ds = new DrugStore();
        ds.addMedicine();
//        ds.displayMed();
        String in;
        String in2;
        while (true) {
            OUTER:
            while (true) {
                in = ds.stringInput("Press 1 if you are a pharmacist\nPress 2 if  you are a customer\n");
                in2 = ds.stringInput("Press 1 to register\nPress 2 to login\n");
                switch (in2) {
                    case "1":
                        if (in.equals("1")) {
                            ds.registerPharmacist();
                            break OUTER;
                        }
                        if (in.equals("2")) {
                            ds.registerCustomer();
                            break OUTER;
                        } else {
                            System.out.println("invalid input!");
                            break;
                        }
                    case "2":
                        Account a = ds.loginAccount();
                        if (a.getUsername() != null) {
                            System.out.println(a);
                            break OUTER;
                        }
                    default:
                        System.out.println("Invalid input!");

                }
            }
            while (true) {
                String in3 = ds.stringInput("Pres 1 for cough\nPress 2 for alergy\nPress 3 for body pain\nPress 4 for headache\n");
                switch (in3) {
                    case "1":
                        ds.displayMed("cough");
                        break;
                    case "2":
                        ds.displayMed("alergy");
                        break;
                    case "3":
                        ds.displayMed("body pain");
                        break;
                    case "4":
                        ds.displayMed("headache");
                        break;
                    default:
                        System.out.println("invalid input!");
                }
            }
        }    
        
    }

}
