import java.util.*;
import java.io.*;

public class InitStore {

    // Metoda pentru citirea fisierului de clienti

    public ArrayList<Customer> getCustomers(String filename) {
        ArrayList<Customer> customersObserver = new ArrayList<Customer>();
        File file = new File(filename);

        if (!file.exists())
            return null;

        try {
            Scanner scan = new Scanner(file);
            String first_line = scan.nextLine();

            int nrCustomers = Integer.parseInt(first_line);

            // Citim linie cu linie fiecare client
            for (int i = 0; i < nrCustomers; i++) {
                String line = scan.nextLine();
                String[] splitCustomers = line.split(";");
                if (splitCustomers[2].equals("A"))
                    customersObserver.add(new Customer(splitCustomers[0], Float.parseFloat(splitCustomers[1]), new StrategyA()));
                else if (splitCustomers[2].equals("B"))
                    customersObserver.add(new Customer(splitCustomers[0], Float.parseFloat(splitCustomers[1]), new StrategyB()));
                else
                    customersObserver.add(new Customer(splitCustomers[0], Float.parseFloat(splitCustomers[1]), new StrategyC()));

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return customersObserver;
    }

    // Metoda pentru citirea fisierului cu produse si crearea departamentelor

    public Store getStore(String filename) {
        Store store = null;
        File file = new File(filename);

        if (!file.exists())
            return null;

        try {
            Scanner scan = new Scanner(file);
            String first_line = scan.nextLine();
            store = store.getInstance(first_line);

            for (int j = 0; j < 4; j++) {

                String departmentsLine = scan.nextLine();
                String[] split = departmentsLine.split(";");
                if (split[0].equals("BookDepartment")) {
                      store.ListDepartment.add(new BookDepartment.BookBuilder(split[0])
                                                        .id(Integer.parseInt(split[1]))
                                                        .customersObserver()
                                                        .customersBuyer()
                                                        .listProducts()
                                                        .build());
                }
                if (split[0].equals("MusicDepartment")) {
                    store.ListDepartment.add(new MusicDepartment.MusicBuilder(split[0])
                                                        .id(Integer.parseInt(split[1]))
                                                        .customersObserver()
                                                        .customersBuyer()
                                                        .listProducts()
                                                        .build());
                }
                if (split[0].equals("VideoDepartment")) {
                    store.ListDepartment.add(new VideoDepartment.VideoBuilder(split[0])
                                                        .id(Integer.parseInt(split[1]))
                                                        .customersObserver()
                                                        .customersBuyer()
                                                        .listProducts()
                                                        .build());
                }
                if (split[0].equals("SoftwareDepartment")) {
                    store.ListDepartment.add(new SoftwareDepartment.SoftwareBuilder(split[0])
                                                        .id(Integer.parseInt(split[1]))
                                                        .customersObserver()
                                                        .customersBuyer()
                                                        .listProducts()
                                                        .build());
                }
                String productsNumber = scan.nextLine();
                int numberOfProducts = Integer.parseInt(productsNumber);

                for (int i = 0; i < numberOfProducts; i++) {

                    String product = scan.nextLine();
                    String[] split2 = product.split(";");
                    store.getDepartment(Integer.parseInt(split[1])).addItem(new Item(split2[0], Integer.parseInt(split2[1]), Float.parseFloat(split2[2]), j));
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return store;
    }

    // Metoda pentru preluarea evenimentelor

    public void getEvents(String filename) {

        try {

            PrintWriter writer = new PrintWriter("output.txt");
            File file = new File(filename);

            if (!file.exists())
                return;

            Scanner scan = new Scanner(file);
            String first_line = scan.nextLine();
            Store store = Store.getInstance();
            int nrEvents = Integer.parseInt(first_line);

            for (int i = 0; i < nrEvents; i++) {

                String lineEvents = scan.nextLine();
                String[] parseEvents = lineEvents.split(";");

                switch (parseEvents[0]) {
                    case "addItem": {
                        Customer notifyCustomer = store.findCustomer(parseEvents[3]); // Gasim clientul
                        Item itemAdd = store.findItem(Integer.parseInt(parseEvents[1])); // Gasim produsul si cream un produs nou pe care il vom adauga in shoppingCart/WishList in functie de preferinte
                        Item aux = new Item(itemAdd.getName(), itemAdd.getId(), itemAdd.getPret(), itemAdd.getDepId());
                        Department dep = store.findDeparmentByItem(Integer.parseInt(parseEvents[1]));
                        if (parseEvents[2].equals("ShoppingCart"))
                            notifyCustomer.getCart().add(aux); // Trimitem notificarea si actualizam Observerii
                        else {
                            notifyCustomer.getWishList().add(aux);
                            dep.addObserver(notifyCustomer);
                        }
                        break;
                    }

                    case "delItem": {
                        Customer notifyCustomer = store.findCustomer(parseEvents[3]); // Gasim clientul
                        Item itemRemove = store.findItem(Integer.parseInt(parseEvents[1])); // Gasim produsul si departamentul aferent
                        Department dep = store.findDeparmentByItem(Integer.parseInt(parseEvents[1]));
                        if (parseEvents[2].equals("ShoppingCart"))
                            notifyCustomer.getCart().remove(itemRemove);
                        else {
                            notifyCustomer.getWishList().remove(itemRemove);
                            if (!notifyCustomer.getWishList().findItemByDepartment(dep))
                                dep.removeObserver(notifyCustomer); // In cazul in care clientul nu mai are niciun produs in WishList pentru acel departament il eliminam din Observer
                        }
                        break;
                    }

                    case "addProduct": {  //  Cream notificarea si adaugam produsul departamentului corespunzator
                        Notification notification = new Notification(Integer.parseInt(parseEvents[1]), Integer.parseInt(parseEvents[2]), NotificationType.ADD);
                        Department department = store.findDeparment(Integer.parseInt(parseEvents[1]));
                        Item newItem = new Item(parseEvents[4], Integer.parseInt(parseEvents[2]), Float.parseFloat(parseEvents[3]), department.getId());
                        department.addItem((newItem));
                        department.NotifyAdd(notification);
                        break;
                    }

                    case "modifyProduct": {  // Modificam pretul unui produs
                        Notification notification = new Notification(Integer.parseInt(parseEvents[1]), Integer.parseInt(parseEvents[2]), NotificationType.MODIFY);
                        Item itemModify = store.findItem(Integer.parseInt(parseEvents[2]));
                        itemModify.setPret(Float.parseFloat((parseEvents[3])));
                        Department department = store.findDeparment(Integer.parseInt(parseEvents[1]));
                        department.NotifyPrice(notification, Float.parseFloat(parseEvents[3]));
                        break;
                    }

                    case "delProduct": { // Stergem un produs din magazin si de la toti clientii care il aveau
                        Department department = store.findDeparmentByItem(Integer.parseInt(parseEvents[1]));
                        Notification notification = new Notification(department.getId(), Integer.parseInt(parseEvents[1]), NotificationType.REMOVE);
                        department.NotifyRemove(notification);
                        for (int j = 0; j < department.getCustomersObserver().size(); j++) {
                            Customer notifyCustomer = department.getCustomersObserver().get(j);
                            if (!notifyCustomer.getWishList().findItemByDepartment(department)) {
                                department.removeObserver(notifyCustomer);
                            }
                        }
                        break;
                    }

                    case "getItem": { // Aplicam strategia clientului
                        Customer notifyCustomer = store.findCustomer(parseEvents[1]);
                        Item itemNotify = notifyCustomer.getWishList().executeStrategy();
                        Department dep = store.findDeparmentByItem(itemNotify.getId());
                        if (!notifyCustomer.getWishList().findItemByDepartment(dep))
                            dep.removeObserver(notifyCustomer);
                        writer.println(itemNotify);
                        notifyCustomer.getWishList().remove(itemNotify);
                        notifyCustomer.getCart().add(itemNotify);
                        break;
                    }

                    case "getItems": { // Scriem toate produsele din cos sau din wishlist
                        Customer notifyCustomer = store.findCustomer(parseEvents[2]);
                        if (parseEvents[1].equals("ShoppingCart"))
                            writer.println(notifyCustomer.getCart());
                        else
                            writer.println(notifyCustomer.getWishList());
                        break;
                    }

                    case "getTotal": { // Facem totalul cosului
                        Customer notifyCustomer = store.findCustomer(parseEvents[2]);
                        if (parseEvents[1].equals("ShoppingCart")) {
                            writer.format("%.2f", (Math.round(notifyCustomer.getCart().getTotalPrice() * 100)) / 100.0);
                            writer.println();
                        } else {
                            writer.format("%.2f", (Math.round(notifyCustomer.getWishList().getTotalPrice() * 100)) / 100.0);
                            writer.println();
                        }
                        break;
                    }

                    case "accept": {
                        Customer notifyCustomer = store.findCustomer(parseEvents[2]);
                        store.getDepartment(Integer.parseInt(parseEvents[1])).accept(notifyCustomer.getCart());
                        break;
                    }

                    case "getObservers": {
                        writer.println(store.getDepartment(Integer.parseInt(parseEvents[1])).getObservers());
                        break;
                    }
                    case "getNotifications": {
                        Customer notifyCustomer = store.findCustomer(parseEvents[1]);
                        writer.println(notifyCustomer.getNotifications());
                        break;
                    }
                }
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}