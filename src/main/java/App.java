import config.Code;
import config.Values;
import controller.*;
import entities.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.UUID;

public class App {

    static InputStreamReader inputStream = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStream);
    static TransactionController trx = new TransactionController();
    static CustomerController cc = new CustomerController();
    static AccountController ac = new AccountController();
    static WalletAccountController wac = new WalletAccountController();
    static WalletController wc = new WalletController();

    static Long sessAccountNumber = Long.valueOf(0);
    static String sessAccountCif = "";
    static String sessCustomerCif = "";
    static Integer idWallet = 0;
    static String topUp = "T0001";
    static String transfer = "T0002";
    static String tarikTunai = "T0003";
    static String pay = "T0004";

    public static void main(String[] args) {

//        Values.banner();

        boolean isExit = false;
        try {

            do {

                try {
                    System.out.println("====== Mini Bank ======");
                    System.out.println();
                    System.out.println("1. New Customer");
                    System.out.println("2. Customer Login");
                    System.out.println("3. Account Login");
                    System.out.println("4. Exit");
                    System.out.println();
                    System.out.println("=======================");

                    System.out.print("Select Menu > ");
                    int menu = Integer.parseInt(input.readLine().trim());

                    if (menu == 1) {
                        addCustomer();
                    } else if (menu == 2) {
                        loginCustomer();
                    } else if (menu == 3) {
                        login();
                    } else if (menu == 4) {
                        System.exit(1);
                    } else {
                        Values.inputNotValid();
                    }

                } catch (Exception e) {
                    Values.inputNotValid();
                }

            } while (!isExit);
            input.close();

        } catch (Exception e) {
            Values.inputNotValid();
//            e.printStackTrace();
        }

    }


    // add new customer
    private static void addCustomer() {
        try {
            System.out.println();
            System.out.println("============= Register Customer =============");
            boolean kembali = false;
            do {
                System.out.print("First Name       : ");
                String fname = input.readLine().trim();
                if (!Values.isString(fname) || fname.equals("")) {
                    System.out.println("First name must be String and cannot empty!!");
                } else {
                    System.out.print("Last Name        : ");
                    String lname = input.readLine().trim();
                    if (!Values.isString(lname)) {
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
                                    System.out.print("Username         : ");
                                    String username = input.readLine().trim();
                                    if (username.length() < 1) {
                                        System.out.println("Username cannot empty");
                                    } else {
                                        if (cc.isUsedCustomer(username)) {

                                            System.out.println("Username is used");
                                        } else {
                                            System.out.print("Password         : ");
                                            String password = input.readLine().trim();
                                            if (!Values.isNumeric(password) || !Values.validLength(password, 6, 6)) {
                                                System.out.println("Pin must be six number");
                                            } else {
                                                System.out.print("Confirm Password      : ");
                                                String password2 = input.readLine().trim();
                                                if (!password2.matches(password)) {
                                                    System.out.println("Password doesn't match");
                                                } else {
                                                    String bod = yob + "-" + bm + "-" + dob;
                                                    Customer nc = new Customer();
                                                    nc.setFname(fname);
                                                    nc.setLname(lname);
                                                    nc.setbDate(bod);
                                                    nc.setUsername(username);
                                                    nc.setPassword(password);

                                                    cc.addCustomer(nc);
                                                    kembali = true;
                                                }
                                            }
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            } while (!kembali);

        } catch (Exception e) {
            Values.inputNotValid();
//            e.printStackTrace();
        }
    }

    // create new account
    public static void createAccount() {
        try {
            System.out.println();
            System.out.println("======== Register Account ========");
            boolean kembali2 = false;
            do {
                System.out.print("Username      : ");
                String username = input.readLine().trim();
                if (username.length() < 1) {
                    System.out.println("Username cannot empty");
                } else {
                    if (ac.isUsed(username)) {
                        System.out.println("Username is used");
                    } else {
                        System.out.print("PIN           : ");
                        String password = input.readLine().trim();
                        if (!Values.isNumeric(password) || !Values.validLength(password, 6, 6)) {
                            System.out.println("Pin must be six number");
                        } else {
                            System.out.print("Confirm PIN   : ");
                            String password2 = input.readLine().trim();
                            if (!password2.matches(password)) {
                                System.out.println("PIN doesn't match");
                            } else {
                                System.out.print("Balance       : ");
                                String tabungan = input.readLine().trim();
                                if (!Values.isNumeric(tabungan) || Long.valueOf(tabungan) < 50000) {
                                    System.out.println("Please enter valid value!, Minimum balance Rp.50.000");
                                } else {
                                    Customer cs = cc.getNameCif(sessCustomerCif);
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

            } while (!kembali2);

        } catch (Exception e) {
            Values.inputNotValid();
//            e.printStackTrace();
        }
    }

    // login
    static void loginCustomer() {
        try {
            System.out.println();
            System.out.println("======== Login =========");
            System.out.print("Username : ");
            String username = input.readLine();
            System.out.print("Password : ");
            String password = input.readLine();

            Customer acLog = cc.loginCustomer(username, password);
            if (acLog != null) {
                sessCustomerCif = acLog.getCif();
                mainMenuCustomer();
            } else {
                System.out.println("Username or pin not valid");
            }

        } catch (Exception e) {
            Values.inputNotValid();
//            e.printStackTrace();
        }

    }

    // login
    static void login() {
        try {
            System.out.println();
            System.out.println("======== Login =========");
            System.out.print("Username : ");
            String username = input.readLine();
            System.out.print("PIN      : ");
            String password = input.readLine();

            Account acLog = ac.login(username, password);
            if (acLog != null) {
                sessAccountNumber = acLog.getAccountNumber();
                sessAccountCif = acLog.getCif().getCif();
                mainMenu();
            } else {
                System.out.println("Username or pin not valid");
            }

        } catch (Exception e) {
            Values.inputNotValid();
//            e.printStackTrace();
        }

    }

    // Main Menu Customer/
    static void mainMenuCustomer() {

        boolean kembali = false;
        try {
            do {
                System.out.println();
                System.out.println("========= Main Menu =========");
                System.out.println("1. Profile details");
                System.out.println("2. List Account");
                System.out.println("3. Create Account");
                System.out.println("4. Update Password");
                System.out.println("5. Log Out");
                System.out.println("=============================");
                System.out.println();
                System.out.print("Select menu > ");
                String valMenu = input.readLine().trim();
                if (!Values.isNumeric(valMenu)) {
                    System.out.println("Please input number");
                } else {
                    int menu = Integer.parseInt(valMenu);
                    if (menu == 1) {
                        cc.getProfileCustomer(sessCustomerCif);
                    } else if (menu == 2) {
                        cc.listAccount(sessCustomerCif);
                    } else if (menu == 3) {
                        createAccount();
                    } else if (menu == 4) {
                        updatePassword();
                    } else if (menu == 5) {
                        sessCustomerCif = "";
                        kembali = true;
                    }
                }
            } while (!kembali);

        } catch (Exception e) {
            Values.inputNotValid();
//            e.printStackTrace();
        }

    }

    // Main Menu/
    static void mainMenu() {

        boolean kembali = false;
        try {
            do {
                System.out.println();
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
                        ac.getProfileAccount(sessAccountCif, sessAccountNumber);
                    } else if (menu == 2) {
                        trx.transactionReport(sessAccountNumber);
                    } else if (menu == 3) {
                        updatePin();
                    } else if (menu == 4) {
                        if (wac.isAvailableWallet(sessAccountNumber)) {
                            // get wallet list
                            wc.getAllWallet(sessAccountNumber);

                            System.out.print("Select wallet: ");
                            System.out.println();
                            String valNumber = input.readLine().trim();
                            if (!Values.isNumeric(valNumber)) {
                                System.out.println("Please input number");
                            } else {
                                int number = Integer.parseInt(valNumber);
                                List<Wallet> walletsList = wc.getAllWalletId(sessAccountNumber);
                                if (walletsList != null){
                                    int x = 1;
                                    for (Wallet w : walletsList) {
                                        if (number == (x++)) {
                                            idWallet = w.getWallet_id();
                                        }
                                    }

                                    if (idWallet == 0){
                                        System.out.println("Wallet not found");
                                    } else {
                                        wallet(idWallet);
                                    }
                                } else {
                                    System.out.println("Wallet not found");
                                }
                            }

                        } else {
                            System.out.println("You have not wallet yet, do you want to create? (y/n)");
                            String option = input.readLine();
                            if (option.equals("n")) {
                                System.out.println("Thank You..");
                            } else if (option.equals("y")) {

                                createWalletAccount();

                            } else {
                                Values.inputNotValid();
                            }
                        }
                    } else if (menu == 5) {
                        createWalletAccount();
                    } else if (menu == 6) {
                        if (!wac.isAvailableWallet(sessAccountNumber)) {
                            System.out.println("You have not wallet yet.");
                        } else {
                            System.out.println("Are you sure to disactivate your wallet ?");
                            System.out.print("Input your wallet id: ");
                            String valWal = input.readLine().trim();
                            if (!Values.isNumeric(valWal) || valWal.length() < 1) {
                                System.out.println("Please input valid id");
                            } else {
                                Integer walletid = Integer.parseInt(valWal);
                                if (!wac.isRegister(sessAccountNumber, walletid)) {
                                    System.out.println("Wallet not wound..");
                                } else {
                                    wac.unreg(sessAccountNumber, walletid);
                                }
                            }
                        }

                    } else if (menu == 7) {
                        sessAccountCif = "";
                        sessAccountNumber = Long.valueOf(0);
                        kembali = true;
                    }
                }
            } while (!kembali);

        } catch (Exception e) {
            Values.inputNotValid();
            e.printStackTrace();
        }

    }

    // create new wallet
    static void createWalletAccount() {
        boolean kembali3 = false;

        try {

            do {
                System.out.println();
                System.out.println("Select account type : ");
                System.out.println("1. E-Banking");
                System.out.println("2. E-Payment");
                System.out.println("3. Back");
                System.out.println("---------------");
                System.out.print("Select menu :");
                String valPil = input.readLine().trim();
                if (!Values.isNumeric(valPil)) {
                    System.out.println("Please insert number");
                } else {
                    int pilihan = Integer.parseInt(valPil);
                    String type = "e-banking";

                    if (pilihan == 3) {
                        kembali3 = true;
                    } else {
                        if (pilihan == 1) {
                            type = "e-banking";
                        } else if (pilihan == 2) {
                            type = "e-payment";
                        }

                        final String uuid = UUID.randomUUID().toString();
                        String codever = Code.makeCode("", uuid, 5);
                        System.out.println("Captcha    - " + codever);
                        System.out.println("Create wallet account");
                        System.out.print("Description   : ");
                        String description = input.readLine();
                        System.out.print("Insert captcha: ");
                        String verify = input.readLine();
                        if (!verify.equals(codever)) {
                            System.out.println("Verification code doesn't match!!!");
                        } else {
                            wac.addWalletAccount(type, description, sessAccountNumber);
                            kembali3 = true;
                        }
                    }
                }

            } while (!kembali3);

        } catch (Exception e) {
//            e.printStackTrace();
        }

    }

    private static void updatePassword() {
        try {
            System.out.println();
            System.out.println("================ Update Your Password ================");
            boolean kembali2 = false;
            do {
                System.out.print("New Password     : ");
                String password = input.readLine().trim();
                if (!Values.isNumeric(password) || !Values.validLength(password, 6, 6)) {
                    System.out.println("Password must be six number");
                } else {
                    System.out.print("Confirm Password : ");
                    String password2 = input.readLine().trim();
                    if (!password2.matches(password)) {
                        System.out.println("Password doesn't match");
                    } else {
                        cc.updatePassword(password, sessCustomerCif);
                        kembali2 = true;
                    }
                }

            } while (!kembali2);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void updatePin() {
        try {
            System.out.println();
            System.out.println("============= Update Your PIN ==============");
            boolean kembali2 = false;
            do {
                System.out.print("New PIN     : ");
                String password = input.readLine().trim();
                if (!Values.isNumeric(password) || !Values.validLength(password, 6, 6)) {
                    System.out.println("Pin must be six number");
                } else {
                    System.out.print("Confirm PIN : ");
                    String password2 = input.readLine().trim();
                    if (!password2.matches(password)) {
                        System.out.println("PIN doesn't match");
                    } else {
                        ac.updatePin(password, sessAccountNumber);
                        kembali2 = true;
                    }
                }

            } while (!kembali2);

        } catch (Exception e) {
            e.printStackTrace();
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
            System.out.println(wc.getWalletDescType(wid) + " not found.");
        }
    }


    static void walletEbanking() {
        boolean kembali2 = false;
        try {
            System.out.println();
            do {
                wc.getWp(idWallet, sessAccountNumber);

                System.out.println("===== E-Wallet Banking =====");
                System.out.println("1. Transfer");
                System.out.println("2. Cash Withdrawal");
                System.out.println("3. Back");
                System.out.println("============================");
                System.out.print("Select menu > ");
                String valMenu = input.readLine().trim();
                if (!Values.isNumeric(valMenu)) {
                    System.out.println("Please input number");
                } else {
                    int menu = Integer.parseInt(valMenu);
                    if (menu == 1) {
                        transferByAccount();
                    } else if (menu == 2) {
                        tarikTunai();
                    } else if (menu == 3) {
                        idWallet=0;
                        kembali2 = true;
                    }
                }

            } while (!kembali2);

        } catch (Exception e) {
            Values.inputNotValid();
            e.printStackTrace();
        }
    }

    static void walletEpayment() {
        boolean kembali2 = false;
        try {
            System.out.println();
            do {
                wc.getWp(idWallet, sessAccountNumber);

                System.out.println("===== E-Wallet Payment =====");
                System.out.println("1. Top Up");
                System.out.println("2. Transfer");
                System.out.println("3. Cash Withdrawal");
                System.out.println("4. Pay Product");
                System.out.println("5. Back");
                System.out.println("============================");
                System.out.print("Select menu > ");
                String valMenu = input.readLine().trim();
                if (!Values.isNumeric(valMenu)) {
                    System.out.println("Please input number");
                } else {
                    int menu = Integer.parseInt(valMenu);
                    if (menu == 1) {
                        topUp();
                    } else if (menu == 2) {
                        transferByWallet();
                    } else if (menu == 3) {
                        tarikTunaiWallet();
                    } else if (menu == 4) {
                        payProduct();
                    } else if (menu == 5) {
                        idWallet=0;
                        kembali2 = true;
                    }
                }

            } while (!kembali2);

        } catch (Exception e) {
            Values.inputNotValid();
            e.printStackTrace();
        }
    }

    static void transferByAccount() {
        try {
            System.out.println();
            System.out.println("E-Wallet Transfer By Account");
            System.out.print("Destination account number : ");
            String valAcn = input.readLine().trim();
            if (sessAccountNumber.toString().equals(valAcn)) {
                System.out.println("You cannot transfer to your self");
            } else {
                if (!Values.isNumeric(valAcn)) {
                    System.out.println("Please input number");
                } else {
                    Long acnt = Long.parseLong(valAcn);
                    System.out.print("Nominal Transfer       : ");
                    String valNtop = input.readLine().trim();
                    if (!Values.isNumeric(valNtop)) {
                        System.out.println("Please input number");
                    } else {
                        Long ntop = Long.parseLong(valNtop);
                        Integer lastBalance = ac.getLastBalance(sessAccountNumber);
                        if (lastBalance == 0 || lastBalance < 5000 || lastBalance < ntop) {
                            System.out.println("Your balance is not enough");
                        } else {
                            if (ntop < 1000) {
                                System.out.println("Minimum transfer is 1000");
                            } else {

                                TrxEntity trxEntity = new TrxEntity();
                                trxEntity.setAcnDebet(acnt);
                                trxEntity.setAcnCredit(sessAccountNumber);
                                trxEntity.setAmount(ntop);
                                trxEntity.setTrxType(transfer);

                                wac.transfer(trxEntity);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            Values.inputNotValid();
            e.printStackTrace();
        }
    }

    static void tarikTunai() {
        try {
            System.out.println();
            System.out.println("E-Wallet Cash Withdrawal");
            System.out.print("Please insert nominal : ");
            String valNtop = input.readLine().trim();
            if (!Values.isNumeric(valNtop)) {
                System.out.println("Please input number");
            } else {
                Long ntop = Long.parseLong(valNtop);
                Integer lastBalance = ac.getLastBalance(sessAccountNumber);
                if (lastBalance == 0 || lastBalance < 60000 || lastBalance < ntop) {
                    System.out.println("Your balance is not enough");
                } else {
                    if (ntop < 50000) {
                        System.out.println("Cash Withdrawal Minimum 50000");
                    } else {
                        TrxEntity trxEntity = new TrxEntity();
                        trxEntity.setAcnCredit(sessAccountNumber);
                        trxEntity.setAmount(ntop);
                        trxEntity.setTrxType(tarikTunai);
                        wac.tariktunai(trxEntity);
                    }
                }
            }

        } catch (Exception e) {
            Values.inputNotValid();
            e.printStackTrace();
        }
    }

    static void topUp() {

        boolean kembali3 = false;
        try {

            do {
                System.out.println("===== Select Top Up Method =====");

                System.out.println("1. By Rekening");
                int no = 2;
                for (WalletAccount w : wc.getAllWallet(sessAccountNumber, idWallet)) {
                    if (w.getWallet_id().getType().equals("e-banking")) {
                        continue;
                    }
                    System.out.println((no++) + ". By " + w.getWallet_id().getDescription());
                }
                System.out.println("0. Back");
                System.out.println("===============================");
                System.out.print("Select menu >");
                String valMenu = input.readLine().trim();
                if (!Values.isNumeric(valMenu)) {
                    System.out.println("Please input number");
                } else {
                    int menu = Integer.parseInt(valMenu);
                    int x = 2;
                    List<WalletAccount> walletAccounts = wc.getAllWallet(sessAccountNumber, idWallet);
                    if (menu > walletAccounts.size()+1){
                        System.out.println("Wallet not found");
                    } else {
                        for (WalletAccount wd : walletAccounts)
                            if (wd.getWallet_id().getType().equals("e-banking")) {
                                continue;
                            } else if (menu == (x++)) {
                                topUpByWallet(wd.getWallet_id().getWallet_id());
                            }
                        if (menu == 1) {
                            topUpByRek(sessAccountNumber);
                        }
                        if (menu == 0) {
                            kembali3 = true;
                        }
                    }
                }

            } while (!kembali3);

        } catch (Exception e) {
            Values.inputNotValid();
            e.printStackTrace();
        }

    }

    static void topUpByWallet(Integer byWallet) {
        try {
            System.out.println();
            if (wc.getWalletDescType(byWallet).equals("e-banking")) {
                topUpByRek(sessAccountNumber);
            } else {
                System.out.println("Top up you wallet..");
                System.out.print("Insert top up nominal :");
                String valNtop = input.readLine().trim();
                if (!Values.isNumeric(valNtop)) {
                    System.out.println("Please input number");
                } else {
                    Long ntop = Long.parseLong(valNtop);
                    Integer wbal = wc.getLastBalance(byWallet);
                    if (wbal == 0 || wbal < 2000 || wbal < ntop) {
                        System.out.println("Your balance is not enough");
                    } else {
                        if (ntop < 1000) {
                            System.out.println("Minimum top up is 1000");
                        } else {
                            TrxEntity trxEntity = new TrxEntity();
                            trxEntity.setAmount(ntop);
                            trxEntity.setAcnDebet(sessAccountNumber);
                            trxEntity.setTrxType(topUp);
                            wac.topup(trxEntity, idWallet, byWallet);
                        }
                    }
                }
            }

        } catch (Exception e) {
            Values.inputNotValid();
            e.printStackTrace();
        }
    }

    static void topUpByRek(Long accountNumber) {
        try {
            System.out.println();
            System.out.println("Top up you wallet..");
            System.out.print("Insert top up nominal :");
            String valNtop = input.readLine().trim();
            if (!Values.isNumeric(valNtop)) {
                System.out.println("Please input number");
            } else {
                Long ntop = Long.parseLong(valNtop);
                Integer lastBalance = ac.getLastBalance(accountNumber);
                if (lastBalance == 0 || lastBalance < 2000 || lastBalance < ntop) {
                    System.out.println("Your balance is not enough");
                } else {
                    if (ntop < 1000) {
                        System.out.println("Minimum top up is 1000");
                    } else {
                        TrxEntity trxEntity = new TrxEntity();
                        trxEntity.setAmount(ntop);
                        trxEntity.setAcnDebet(accountNumber);
                        trxEntity.setTrxType(topUp);
                        wac.topup(trxEntity, idWallet);
                    }
                }
            }

        } catch (Exception e) {
            Values.inputNotValid();
            e.printStackTrace();
        }
    }

    static void transferByWallet() {
        try {
            TrxEntity trxEntity = new TrxEntity();
            System.out.println();
            System.out.println("E-Wallet Transfer By Wallet");

            System.out.println("1. Transfer To Other Wallet");
            System.out.println("2. Transfer To Other Account");
            int pilihan = Integer.parseInt(input.readLine());

            if (pilihan == 1) {
                System.out.print("Destination Wallet Id : ");
                String valWaid = input.readLine().trim();
                if (!wc.walletValidate(idWallet, valWaid)) {
                    System.out.println("You cannot transfer to your self");
                } else {
                    if (!Values.isNumeric(valWaid)) {
                        System.out.println("Please input number");
                    } else {
                        Integer toWallet = Integer.parseInt(valWaid);
                        System.out.print("Transfer nominal      : ");
                        String valNtop = input.readLine().trim();
                        if (!Values.isNumeric(valNtop)) {
                            System.out.println("Please input number");
                        } else {
                            Long ntop = Long.parseLong(valNtop);
                            Integer wbal = wc.getLastBalance(idWallet);
                            if (wbal == 0 || wbal < 2000 || wbal < ntop) {
                                System.out.println("Your balance is not enough");
                            } else {
                                if (ntop < 1000) {
                                    System.out.println("Minimum transfer is 1000");
                                } else {
                                    trxEntity.setAmount(ntop);
                                    trxEntity.setAcnDebet(sessAccountNumber);
                                    trxEntity.setAcnCredit(sessAccountNumber);
                                    trxEntity.setTrxType(transfer);
                                    wac.transferByWallet(trxEntity, idWallet, toWallet);
                                }
                            }
                        }
                    }
                }
            } else if (pilihan == 2) {
                System.out.print("Destination account number : ");
                String valAcn = input.readLine().trim();
                if (!Values.isNumeric(valAcn)) {
                    System.out.println("Please input number");
                } else {
                    Long acnt = Long.parseLong(valAcn);
                    System.out.print("Transfer nominal           : ");
                    String valNtop = input.readLine().trim();
                    if (!Values.isNumeric(valNtop)) {
                        System.out.println("Please input number");
                    } else {
                        Long ntop = Long.parseLong(valNtop);
                        Integer wbal = wc.getLastBalance(idWallet);
                        if (wbal == 0 || wbal < 2000 || wbal < ntop) {
                            System.out.println("Your balance is not enough");
                        } else {
                            if (ntop < 1000) {
                                System.out.println("Minimum transfer is 1000");
                            } else {
                                trxEntity.setAcnDebet(acnt);
                                trxEntity.setAmount(ntop);
                                trxEntity.setAcnCredit(sessAccountNumber);
                                trxEntity.setTrxType(transfer);
                                wac.transferByWallet(trxEntity, idWallet);
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            Values.inputNotValid();
            e.printStackTrace();
        }
    }

    static void tarikTunaiWallet() {
        try {
            System.out.println();
            System.out.println("E-Wallet Cash Withdrawal Wallet");
            System.out.print("Please insert nominal : ");
            String valNtop = input.readLine().trim();
            if (!Values.isNumeric(valNtop)) {
                System.out.println("Please input number");
            } else {
                Long ntop = Long.parseLong(valNtop);
                Integer wbal = wc.getLastBalance(idWallet);
                if (wbal == 0 || wbal < 62000 || wbal < ntop) {
                    System.out.println("Your balance is not enough");
                } else {
                    if (ntop < 50000) {
                        System.out.println("Cash Withdrawal Minimum 50000");
                    } else {
                        TrxEntity trxEntity = new TrxEntity();
                        trxEntity.setAcnCredit(sessAccountNumber);
                        trxEntity.setAmount(ntop);
                        trxEntity.setTrxType(tarikTunai);
                        wac.tariktunai(trxEntity, idWallet);
                    }
                }
            }

        } catch (Exception e) {
            Values.inputNotValid();
            e.printStackTrace();
        }
    }

    static void payProduct() {
        try {
            TrxEntity trxEntity = new TrxEntity();

            System.out.println("============== Pay Product ==============");
            System.out.print("Insert store code      : ");
            String kodeToko = input.readLine().trim();
            if (!Values.isNumeric(kodeToko)) {
                System.out.println("Input not valid");
            } else {
                System.out.print("Insert payment nominal : ");
                String valNtop = input.readLine().trim();
                if (!Values.isNumeric(valNtop)) {
                    System.out.println("Please input number");
                } else {
                    Long ntop = Long.parseLong(valNtop);
                    Integer wbal = wc.getLastBalance(idWallet);
                    if (wbal == 0 || wbal < 1000 || wbal < ntop) {
                        System.out.println("Your balance is not enough");
                    } else {
                        if (ntop < 1) {
                            System.out.println("Please input nominal");
                        } else {
                            trxEntity.setAmount(ntop);
                            trxEntity.setAcnCredit(sessAccountNumber);
                            trxEntity.setTrxType(pay);
                            wac.transferByWallet(trxEntity, idWallet, Integer.parseInt(kodeToko));
                        }
                    }
                }
            }

        } catch (Exception e) {
            Values.inputNotValid();
            e.printStackTrace();
        }
    }


}
