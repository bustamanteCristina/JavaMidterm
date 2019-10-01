/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drugstore;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author 2ndyrGroupB
 */
public class DrugStore {

    Scanner input = new Scanner(System.in);
    private ArrayList<Medicine> medicines;
    private ArrayList<Account> accounts;

    public DrugStore() {
        medicines = new ArrayList();
        accounts = new ArrayList();
    }

    public ArrayList<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(ArrayList<Medicine> medicines) {
        this.medicines = medicines;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public Account register() {
        String uname = stringInput("Enter username: ");
        String pass = stringInput("Enter password: ");
        String fname = stringInput("Enter first name: ");
        String lname = stringInput("Enter last name: ");
        int age = intInput("Enter age: ");
        return new Account(uname, pass, fname, lname, age);
    }

    public void registerCustomer() {
        Account a = register();
        accounts.add(new Customer(a.getUsername(), a.getPassword(), a.getFname(), a.getLname(), a.getAge()));
    }

    public void registerPharmacist() {
        Account a = register();
        accounts.add(new Pharmacist(a.getUsername(), a.getPassword(), a.getFname(), a.getLname(), a.getAge()));
    }

    public void addMedicine(Medicine m) {
        medicines.add(m);
    }

    public void displayCustomers() {
        for (Account a : accounts) {
            if (a instanceof Customer) {
                System.out.println(a);
            }
        }
    }

    public void displayMedicines() {
        for (Medicine m : medicines) {
            System.out.println(m);
        }
    }

    public String stringInput(String label) {
        System.out.print(label);
        return input.next();
    }

    public int intInput(String label) {
        System.out.print(label);
        return input.nextInt();
    }

    public void displayMed() {
        System.out.println("Medicine Name\t\tGeneric Name\t\tBrand Name\t\tPrice");
        for (Medicine m : medicines) {
            System.out.println(m.getMedName() + "\t\t" + m.getGenericName() + "\t\t" + m.getBrandName() + "\t\t" + m.getPrice());
        }
    }

    public void addMedicine() {
        medicines.add(new Medicine("Benzonatate", 100, "benzonatate", "Tessalon", "cough", 100));
        medicines.add(new Medicine("Mucinex", 100, "guaifenesin", "Robitussin Mucus", "cough", 100));
        medicines.add(new Medicine("Cheratussin AC", 100, "codeine", "Allfen CD", "cough", 100));
        medicines.add(new Medicine("Fioricet", 100, "caffeine", "capacet", "headache", 100));
        medicines.add(new Medicine("Ibuprofen", 100, "Ibuprofen", "Midol", "headache", 100));
        medicines.add(new Medicine("Advil", 100, "\tAdvil", "\tIbuprofen", "headache", 100));
        medicines.add(new Medicine("Naproxen", 100, "naproxen", "Aleve", "body pain", 100));
        medicines.add(new Medicine("Acetminophen", 100, "Acetminophen", "Actamin", "body pain", 100));
        medicines.add(new Medicine("Declofenac", 100, "Declofenac", "Cambia", "body pain", 100));
        medicines.add(new Medicine("Hydroxyzine", 100, "Hydroxyzine", "Vistaril", "alergy", 100));
        medicines.add(new Medicine("Levocetiricine", 100, "Levocetiricine", "Xyzal", "alergy", 100));
        medicines.add(new Medicine("Doxylamine", 100, "Doxylamine", "Usinom", "alergy", 100));
    }

    public void displayMed(String type) {
        for (Medicine m : medicines) {
            if (m.getType().equals(type) && m.getQuantity() != 0) {
                System.out.println(m);
            }
        }
    }

    public void total(Customer c) {
        int total = 0;
        for (Medicine m : c.getPurchasedMed()) {
            System.out.println(m);
            total += m.getPrice() * m.getQuantity();
        }
        System.out.println("Your total is: " + total);
    }
    

    public void inventory() {
        
        for (Medicine m : medicines) {
            double total = 0;
            int num=0;
            for (Account a : accounts) {
                if (a instanceof Customer) {
                    if (!((Customer) a).getPurchasedMed().isEmpty()) {
                        for (Medicine m2: ((Customer) a).getPurchasedMed()) {
                            if (m.getMedName().equals(m2.getMedName())){
                                total += m2.getPrice() * m2.getQuantity();
                                ++num;
                            }
                        }
                    }
                }
            }
            System.out.println(m);
            System.out.println("Total number of item purchased: "+num);
            System.out.println("Total amount: "+total+"\n");
        }
    }

    public void purchase(Customer a) {
        displayMed();
        while (true) {
            String name = this.stringInput("Medicine Name : ");
            int i = Integer.parseInt(searchMed(name));
            if (!"not found!".equals(searchMed(name)) || medicines.get(i).getQuantity() != 0) {
                int quantity = this.intInput("Quantity : ");
                Medicine m = medicines.get(i);
                if (m.getQuantity() >= quantity) {
                    double discount = m.getPrice() * .20;
                    double price = m.getPrice();
                    if (a.getAge() >= 65) {
                        price -= discount;
                        System.out.println("Discount: 20%");
                    }
                    m.setPrice(price);
                    medicines.get(i).setQuantity(m.getQuantity() - quantity);
                    m.setQuantity(quantity);
                    a.addPurchased(m);
                    System.out.println("Total amount: " + m.getPrice() * quantity);
                } else {
                    System.out.println("The available is only " + m.getQuantity());
                }
            } else {
                System.out.println("Medicine not found!");
            }
            String answer = stringInput("Add another Medicine ? y/n");
            if (answer.equalsIgnoreCase("n")) {
                break;
            }
        }
//        total();
    }

    public String searchMed(String name) {
        for (int i = 0; i < medicines.size(); ++i) {
            if (medicines.get(i).getMedName().equalsIgnoreCase(name)) {
                return i + "";
            }
        }
        return "not found!";
    }

    public Account loginAccount() {
        String username = stringInput("Enter username: ");
        String password = stringInput("Enter password: ");
        for (Account a : accounts) {
            if (a.getUsername().equals(username) && a.getPassword().equals(password)) {
                return a;
            }
        }
        return new Account();
    }

}
