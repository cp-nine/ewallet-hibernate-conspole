import config.Values;
import controller.AccountController;
import controller.CustomerController;
import entities.Account;
import entities.Customer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class App {


    static InputStreamReader inputStream = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStream);
    static CustomerController cc = new CustomerController();
    static AccountController ac = new AccountController();
    //    static WalletAccountController wac = new WalletAccountController();
//    static WalletController wc = new WalletController();
    static List<Account> listAccount = new ArrayList<Account>();
    static Integer idWallet = 0;
    static String topUp = "T0001";
    static String transfer = "T0002";
    static String tarikTunai = "T0003";
    static String pay = "T0004";

    public static void main(String[] args) {

        boolean isExit = false;
        try {

            do {

                try {
                    System.out.println("====== Mini Bank ======");
                    System.out.println();
                    System.out.println("1. New Customer");
                    System.out.println("2. Create Account");
                    System.out.println("3. Customer Login");
                    System.out.println("4. Upload Customer");
                    System.out.println();
                    System.out.println("=========================");

                    System.out.print("Select Menu > ");
                    int menu = Integer.parseInt(input.readLine().trim());

                    if (menu == 1) {
                        addCustomer();
                    } else if (menu == 2) {
                        createAccount();
                    } else if (menu == 3) {
//                        login();
                    } else if (menu == 4) {
//                            new ThreadUpload("Upload1").start();

                    } else {
                        System.out.println("Wrong input");
                    }

                } catch (Exception e) {
                    System.out.println("Wrong input");
                }

            } while (!isExit);
            input.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    // add new customer
    private static void addCustomer() {
        try {
            System.out.println("-------------- Register Customer ------------");
            boolean kembali = false;
            do {
                System.out.print("First Name       : ");
                String fname = input.readLine().trim();
                if (!Values.isString(fname) || fname.equals("")) {
                    System.out.println("First name must be String and cannot empty!!");
                } else {
                    System.out.print("Last Name        : ");
                    String lname = input.readLine().trim();
                    if (!Values.isString(lname) || lname.equals("")) {
                        System.out.println("Last name must be words and cannot empty!!");
                    } else {
                        System.out.print("Date of Brith    : ");
                        String dob = input.readLine().trim();
                        if (!Values.isNumeric(dob) || !Values.validLength(dob, 2, 2)) {
                            System.out.println("Date of birth must be two number");
                        } else {
                            System.out.print("Brith Month      : ");
                            String bm = input.readLine().trim();
                            if (!Values.isNumeric(bm) || !Values.validLength(bm, 2, 2)) {
                                System.out.println("Birth month must be two number");
                            } else {
                                System.out.print("Year of Brith    : ");
                                String yob = input.readLine().trim();
                                if (!Values.isNumeric(yob) || !Values.validLength(yob, 4, 4)) {
                                    System.out.println("Date of birth must be four number");
                                } else {
                                    String bod = yob + "-" + bm + "-" + dob;
                                    Customer nc = new Customer();
                                    nc.setFname(fname);
                                    nc.setLname(lname);
                                    nc.setbDate(bod);

                                    cc.addCustomer(nc);
                                    kembali = true;
                                }
                            }
                        }
                    }
                }
            } while (!kembali);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // create new account
    public static void createAccount() {
        try {
            System.out.println("-------------- Register Account ------------");
            boolean kembali2 = false;
            do {
                System.out.print("CIF Number      : ");
                String cif = input.readLine().trim();
                if (!Values.validLength(cif, 8, 8)) {
                    System.out.println("CIF must be 8 character can not empty!!");
                } else {
                    System.out.print("PIN          : ");
                    String password = input.readLine().trim();
                    if (!Values.isNumeric(password) || !Values.validLength(password, 6, 6)) {
                        System.out.println("Pin must be six number");
                    } else {
                        System.out.print("Confirm PIN      : ");
                        String password2 = input.readLine().trim();
                        if (!password2.matches(password)) {
                            System.out.println("PIN doesn't match");
                        } else {
                            System.out.print("Balance        : ");
                            String tabungan = input.readLine().trim();
                            if (!Values.isNumeric(tabungan) || Long.valueOf(tabungan) < 50000) {
                                System.out.println("Please enter valid value!, Minimum balance Rp.50.000");
                            } else {
                                Customer cs = cc.getNameCif(cif);
                                if (cs.equals(null)) {
                                    System.out.println("Customer not found");
                                } else {
                                    cs.getCif();
                                    Account newAccount = new Account();
                                    newAccount.setAccount_name(cs.getFname() + " " + cs.getLname());
                                    newAccount.setPasword(password);
                                    newAccount.setBalance(Long.valueOf(tabungan));
                                    newAccount.setCif(cs);

                                    ac.createAccount(newAccount);
                                    kembali2 = true;
                                }
                            }
                        }
                    }

                }
            } while (!kembali2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
