import config.Values;
import controller.*;
import entities.Account;
import entities.Customer;
import entities.TrxEntity;
import entities.Wallet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class App {

    static InputStreamReader inputStream = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStream);
    static TransactionController trx = new TransactionController();
    static CustomerController cc = new CustomerController();
    static AccountController ac = new AccountController();
    static WalletAccountController wac = new WalletAccountController();
    static WalletController wc = new WalletController();
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
                        login();
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
                    System.out.print("Username      : ");
                    String username = input.readLine().trim();
                    if (username.length() < 1) {
                        System.out.println("Username cannot empty");
                    } else {
                        if (ac.isUsed(username)) {
                            System.out.println("Username is used");
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
                                            newAccount.setAccountName(cs.getFname() + " " + cs.getLname());
                                            newAccount.setUsername(username);
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
                    }

                }
            } while (!kembali2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // login
    static void login() {
        try {
            System.out.println("===== Login ======");
            System.out.print("Username : ");
            String username = input.readLine();
            System.out.print("PIN : ");
            String password = input.readLine();

            Account acLog = ac.login(username, password);
            if (acLog != null) {
                listAccount.add(acLog);
                mainMenu();
            } else {
                System.out.println("Username or pin not valid");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Main Menu/
    static void mainMenu() {

        boolean kembali = false;
        try {
            do {
                Long acNumber = listAccount.get(0).getAccountNumber();
                System.out.println();
//                System.out.println("Welcome " + listAccount.get(0).getAccountName());

                System.out.println("========= Main Menu =========");
                System.out.println("1. Profile details");
                System.out.println("2. Transaction Report");
                System.out.println("3. Update PIN        ");
                System.out.println("4. E-Wallet        ");
                System.out.println("5. Create New E-Wallet ");
                System.out.println("6. Un Reg E-Wallet ");
                System.out.println("7. Log Out");
                System.out.println("=============================");
                System.out.println();
                System.out.print("Select menu > ");
                String valMenu = input.readLine().trim();
                if (!Values.isNumeric(valMenu)) {
                    System.out.println("Please input number");
                } else {
                    int menu = Integer.parseInt(valMenu);
                    if (menu == 1) {
//                        ac.getProfileAccount(listAccount.get(0).getCif());
                    } else if (menu == 2) {
                        trx.transactionReport(acNumber);
                    } else if (menu == 3) {
//                        updatePin();
                    } else if (menu == 4) {
                        if (wac.isAvailableWallet(acNumber)) {
                            System.out.println();
                            wc.getAllWallet(acNumber);

                            System.out.print("Select wallet: ");
                            System.out.println();
                            String valNumber = input.readLine().trim();
                            if (!Values.isNumeric(valNumber)) {
                                System.out.println("Please input number");
                            } else {
                                int number = Integer.parseInt(valNumber);

                                int x = 1;
                                for (Wallet w : wc.getAllWalletId(acNumber)) {
                                    if (number == (x++)) {
                                        idWallet = w.getWallet_id();
                                    }
                                }
//                                 get wallet by wallet id
                                wallet(idWallet);
                            }

                        } else {
                            System.out.println("You have not wallet yet, do you want to create? (y/n)");
                            String option = input.readLine();
                            if (option.equals("n")) {
                                System.out.println("Thank You..");
                            } else if (option.equals("y")) {

//                                createWalletAccount();

                            } else {
                                System.out.println("Wrong input..");
                            }
                        }
                    } else if (menu == 5) {
//                        createWalletAccount();
                    } else if (menu == 6) {
//                        if (wac.isAvailableWallet(acNumber) <= 0) {
                        if (0 <= 0) {
                            System.out.println("You have not wallet yet.");
                        } else {
                            System.out.println("Are you sure to disactivate your wallet ?");
                            System.out.print("Input your wallet id: ");
                            String valWal = input.readLine().trim();
                            if (!Values.isNumeric(valWal) || valWal.length() < 1) {
                                System.out.println("Please input valid id");
                            } else {
                                Integer walletid = Integer.parseInt(valWal);
//                                if (wac.isRegister(acNumber, walletid) < 1) {
                                if (0 <= 0) {
                                    System.out.println("Wallet not wound..");
                                } else {
//                                    wac.unreg(acNumber, walletid);
                                }
                            }
                        }

                    } else if (menu == 7) {
                        System.exit(1);
                    }
                }
            } while (!kembali);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Input not valid..");
        }

    }

    static void wallet(Integer wid) {
        if (wc.getWalletDescType(wid).equals("e-banking")) {
            walletEbanking();
            System.out.println("Ebanking");
        } else if (wc.getWalletDescType(wid).equals("e-payment")) {
            walletEpayment();
            System.out.println("Epayment");
        } else {
            System.out.println(wc.getWalletDescType(wid)+"tidak ketemu");
        }
    }


    static void walletEbanking() {
        boolean kembali2 = false;
        try {

            do {
                Wallet wallet = wc.getWp(idWallet);
                System.out.println("=============== Profiles ===================");
                System.out.println("Wallei Id      : " + wallet.getWallet_id());
                System.out.println("Description    : " + wallet.getDescription());
                System.out.println("Type           : " + wallet.getType());
                System.out.println("Active Balance : " + Values.rupiah(ac.getLastBalance(listAccount.get(0).getAccountNumber())));
                System.out.println("============================================");
                System.out.println();

                System.out.println("===== E-Wallet Banking =====");
                System.out.println("1. Transfer");
                System.out.println("2. Cash Withdrawal");
                System.out.println("3. Back");
                System.out.println("====================");
                System.out.print("Select menu > ");
                String valMenu = input.readLine().trim();
                if (!Values.isNumeric(valMenu)) {
                    System.out.println("Please input number");
                } else {
                    int menu = Integer.parseInt(valMenu);
                    if (menu == 1) {
                        transferByAccount();
                    } else if (menu == 2) {
//                        tarikTunai();
                    } else if (menu == 3) {
                        kembali2 = true;
                    }
                }

            } while (!kembali2);

        } catch (Exception e) {
            System.out.println("Input not valid..");
        }
    }

    static void walletEpayment() {
        boolean kembali2 = false;
        try {

            do {
                Wallet wallet = wc.getWp(idWallet);
                System.out.println("=============== Profiles ===================");
                System.out.println("Wallei Id      : " + wallet.getWallet_id());
                System.out.println("Description    : " + wallet.getDescription());
                System.out.println("Type           : " + wallet.getType());
                System.out.println("Active Balance : " + Values.rupiah(wallet.getActiveBalance()));
                System.out.println("============================================");
                System.out.println();
                System.out.println("===== E-Wallet Payment =====");
                System.out.println("1. Top Up");
                System.out.println("2. Wallet Account");
                System.out.println("3. Change Wallet Account");
                System.out.println("4. Transfer");
                System.out.println("5. Cash Withdrawal");
                System.out.println("6. Pay Product");
                System.out.println("7. Back");
                System.out.println("====================");
                System.out.print("Select menu > ");
                String valMenu = input.readLine().trim();
                if (!Values.isNumeric(valMenu)) {
                    System.out.println("Please input number");
                } else {
                    int menu = Integer.parseInt(valMenu);
                    if (menu == 1) {
//                        topUp();
                    } else if (menu == 2) {
//                        transferByWallet();
//                        wac.getWalletAccountList(idWallet);
                    } else if (menu == 3) {
//                        updateWalletAccount();
                        System.out.println("Change Wallet Account");
                    } else if (menu ==4) {
//                        transferByWallet();
                    } else if (menu == 5) {
//                        tarikTunaiWallet();
                    } else if (menu == 6) {
//                        payProduct();
                    } else if (menu == 7) {
                        kembali2 = true;
                    }
                }

            } while (!kembali2);

        } catch (Exception e) {
            System.out.println("Input not valid..");
        }
    }

    static void transferByAccount() {
        try {
            System.out.println("E-Wallet Transfer By Account");
            System.out.print("Enter the destination account number : ");
            String valAcn = input.readLine().trim();
            if (!Values.isNumeric(valAcn)) {
                System.out.println("Please input number");
            } else {
                Long acnt = Long.parseLong(valAcn);
                System.out.print("Input nominal : ");
                String valNtop = input.readLine().trim();
                if (!Values.isNumeric(valNtop)) {
                    System.out.println("Please input number");
                } else {
                    Long ntop = Long.parseLong(valNtop);
                    Integer lastBalance = ac.getLastBalance(listAccount.get(0).getAccountNumber());
                    if (lastBalance == 0 || lastBalance < 5000 || lastBalance < ntop) {
                        System.out.println("Your balance is not enough");
                    } else {
                        if (ntop < 1000) {
                            System.out.println("Minimum transfer is 1000");
                        } else {

                            TrxEntity trxEntity = new TrxEntity();
                            trxEntity.setAcnDebet(acnt);
                            trxEntity.setAcnCredit(listAccount.get(0).getAccountNumber());
                            trxEntity.setAmount(ntop);
                            trxEntity.setTrxType(transfer);

                            wac.transfer(trxEntity);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}