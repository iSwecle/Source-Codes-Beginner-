import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class InventoryManager {


    static class MyList {
        String name;
        double price;
        int quantity = 0;

        public void Info(String name, double price, int quantity) {

            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public String getName()
        {
            return name;
        }
    }
    public static class removeItem
    {
        public static void remove(ArrayList<MyList>productlist, String namerev)
        {
            for(int i = 0; i<productlist.size(); i++)
            {
                if(productlist.get(i).getName().equalsIgnoreCase(namerev))
                {
                    productlist.remove(i);
                    System.out.println("[Item Removed]");
                }
                else
                {
                    System.out.println("[This item is not in the list]");
                }
            }
        }
    }


    public static void main(String[]arg)
    {
        String name;
        double price;
        int quantity;
        int number = 0;
        char yn;
        Scanner scan = new Scanner(System.in);
        ArrayList<MyList>productlist = new ArrayList<>();



        while(true)
        {
            System.out.println("╔══════════════════════════════════════════════════════╗");
            System.out.println("∥   Welcome to Inventory and Price Management Program  ∥");
            System.out.println("╚══════════════════════════════════════════════════════╝");
            System.out.println("What would you like to do?\n");
            System.out.println("(1)Add Product\n(2)Remove Product\n(3)Show List\n(4)Search Product\n(5)Alerts\n(6)Credits\n(7)Exit");
            System.out.print("Command:");
            char command = scan.next().charAt(0);
            switch(command)
            {

                case'1':

                    while(true)
                    {

                        System.out.println("╔══════════════════════════════════════════════════════╗");
                        System.out.println("∥                     ADD NEW ITEM                     ∥");
                        System.out.println("╚══════════════════════════════════════════════════════╝");
                        System.out.print("New item? (Y/N):");
                        yn = scan.next().charAt(0);

                        if(yn == 'Y' || yn == 'y')
                        {
                            MyList here = new MyList();
                            System.out.println("====ITEM " + ++number + "====");
                            System.out.print("Name:");
                            scan.nextLine();
                            name = scan.nextLine();
                            while(true)
                            {
                                try{
                                    System.out.print("Price:");
                                    price = scan.nextDouble();
                                    System.out.print("Quantity:");
                                    quantity = scan.nextInt();
                                    if(price >= 0 && quantity >=0)
                                    {
                                        here.Info(name, price, quantity);
                                        productlist.add(here);
                                        break;
                                    }
                                    else if(price < 0 || quantity < 0)
                                    {
                                        System.out.println("===[Please insert positive values]===");
                                    }
                                }
                                catch (InputMismatchException e){
                                    System.out.println("===[Invalid Input]===");
                                    scan.nextLine(); /*Double and Integer scanner can not eat string input so
                                    it leaves it in the buffer unconsumed causing an infinite loop of the same error.
                                    That is why I put "scan.nextLine()" here to eat the uneaten input.*/
                                }
                            }





                        }
                        else if(yn == 'N' || yn == 'n')
                        {
                            break;
                        }
                        else
                        {
                            System.out.println("===[Invalid Input]===");
                        }
                    }
                    break;
                case'2':
                    while(true) {

                        System.out.println("╔══════════════════════════════════════════════════════╗");
                        System.out.println("∥                      REMOVE ITEM                     ∥");
                        System.out.println("╚══════════════════════════════════════════════════════╝");
                        System.out.print("Remove an item? (Y/N):");
                        new removeItem();
                        String namerev;

                        yn = scan.next().charAt(0);
                        scan.nextLine();
                        if (yn == 'Y' || yn == 'y') {

                            if(productlist.isEmpty())
                            {
                                System.out.print("[There are no items to remove]\n");
                            }
                            else
                            {
                                System.out.print("Remove:");
                                namerev = scan.nextLine();
                                removeItem.remove(productlist,namerev);
                            }




                        }
                        else if (yn == 'N' || yn == 'n') {
                            break;
                        } else {
                            System.out.println("===[Invalid Input]===");
                        }
                    }
                break;
                case'3':
                    System.out.println("╔══════════════════════════════════════════════════════╗");
                    System.out.println("∥                         LIST                         ∥");
                    System.out.println("╚══════════════════════════════════════════════════════╝");


                    ArrayList<Double>total = new ArrayList<>();
                    if(productlist.isEmpty())
                    {
                        System.out.println("===[No items listed]===");
                    }
                    else
                    {
                        double total_cost = 0;
                        for(MyList output : productlist)
                        {

                            double stock_value;
                            System.out.println("\n=================================");
                            System.out.println("Item: " + output.name);
                            System.out.printf("Price: ₱%.2f", output.price);
                            System.out.println("\nQuantity: " +  output.quantity);
                            stock_value = output.price*output.quantity;
                            System.out.printf("Stock Value: ₱%.2f", stock_value);
                            total.add(stock_value);



                        }
                        for(Double each: total)
                        {
                            total_cost += each;

                        }
                        System.out.println("\n=================================");
                        System.out.printf("OVERALL VALUE: ₱%.2f", total_cost);


                    }

                    System.out.print("\nBack to Main Menu? (Press Enter):");
                    scan.nextLine();
                    scan.nextLine();  //This eats "\n". It came from pressing enter key.
                    break;
                case'4':

                    while(true) {

                        System.out.println("╔══════════════════════════════════════════════════════╗");
                        System.out.println("∥                        SEARCH                        ∥");
                        System.out.println("╚══════════════════════════════════════════════════════╝");
                        System.out.print("Search Item? (Y/N): ");
                        char w = scan.next().charAt(0);
                        //When asked for input, the user has to press enter to submit it adding "\n" to the program.

                        if(w == 'Y' || w == 'y')
                        {
                            System.out.print("Search:");
                            scan.nextLine();
                            /* It took me a while to realize but the user pressing enter key adds "\n"
                            to the program. So I used scan.nextLine() to remove that. */

                            String search = scan.nextLine();
                            boolean itemFound = false;
                            for (MyList output : productlist) {

                                if (search.equalsIgnoreCase(output.name)) {
                                    System.out.println("=================================" + "\nItem: " + output.name);
                                    System.out.printf("Price: ₱ %.2f", output.price);
                                    System.out.println("\nQuantity: " + output.quantity);
                                    itemFound = true;
                                    break;
                                }
                            }
                            if(!itemFound)
                            {
                                System.out.println("===[Item not found]===");
                            }

                        }
                        else if(w == 'N' || w == 'n')
                        {
                            break;
                        }

                    }
                    break;
                case '5':
                    int min = 100;
                    int max = 1000;
                    System.out.println("╔══════════════════════════════════════════════════════╗");
                    System.out.println("∥  ⚠                     ALERTS                     ⚠  ∥");
                    System.out.println("╚══════════════════════════════════════════════════════╝");

                    System.out.println("Low Stock <= " + min + " | High Stock >= " + max + " \n");
                    System.out.println("==================================");

                    for (MyList output : productlist)
                    {
                        if(output.quantity <= min)
                        {
                            System.out.println("[Low  Stock]: Only " + output.quantity + " " + output.name + "(s) left in stock!");
                        }
                        if(output.quantity >= max)
                        {
                            System.out.println("[High Stock]: There are " + output.quantity + " " + output.name + "s! Those are too many!");
                        }

                    }

                    System.out.println("[__________]: -------None-------");
                    System.out.println("[__________]: -------None-------");
                    System.out.println("[__________]: -------None-------");
                    System.out.println("==================================");

                    System.out.print("Back to Main Menu? (Press Enter):");
                    scan.nextLine();
                    scan.nextLine();  //This eats "\n". It came from pressing enter key.
                    break;
                case'6':
                    System.out.println("╔══════════════════════════════════════════════════════╗");
                    System.out.println("∥                       CREDITS                        ∥");
                    System.out.println("╚══════════════════════════════════════════════════════╝");
                    System.out.println("            Developer: Kurt Chim G. Beadoy");
                    System.out.println("                Tester: Angelica Singh    \n");
                    System.out.println("""
                                    This program was made using Jetbrains'
                                   IntelliJ IDEA Community Edition 2024.2.0.1
                            """);
                    System.out.print("Back to Main Menu? (Press Enter):");
                    scan.nextLine();
                    scan.nextLine();  //This eats "\n". It came from pressing enter key.
                    break;
                case'7':
                    System.out.println("Thank you for using this program!");
                    System.out.println("Terminating the program...");
                    System.exit(0);
                    break;
            }

        }


    }


}
