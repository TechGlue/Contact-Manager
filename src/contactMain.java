import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/*
## to do ##
write a sort//done
clear method//done and working//will clear the entire folder as well...
clean up code so its nots all in main class..maybe
take in lower case inputs as well for the menu
make it save and ready input into array on start up//so far it looks like the save file function is working//done
needs a single remove method that wil clear one single charcter--in progress

NOTE THE REMOVE METHOD IS STILL IN PROGRESS.....GOT TIRED WITH CODE ;(
 */

 class contactMain {

    protected ArrayList <contact> array = new ArrayList<>();

    public static void main(String[] args) {
        try {
            contactMain ref = new contactMain();
            ref.onStart();
            ref.displayMenu();
            ref.onEnd();
        } catch (FileNotFoundException e) {
            System.out.println(e +"The contacts.txt file has been removed from please add it back");
        }

    }

    public void displayMenu(){//menu
        Scanner kb = new Scanner(System.in);

        boolean quitTrigger = false;
        String choice;
        System.out.print("\n(づ￣ ³￣)づ  TYPE IN THE WORD LIST TO SHOW COMMANDS FOR THE CONTACT MANAGER  (づ￣ ³￣)づ \n");

        do{

            System.out.println("\n---Chose a menu option---");

            System.out.print("[Menu] Enter an action: " );
            choice = kb.next();
            choice = choice.toLowerCase();

            switch (choice){
                case "add":
                    System.out.println();
                    add();
                    break;
                case "show":
                    System.out.println();
                    display();
                    break;
                case "list":
                    showMe();
                    break;
                case "remove":
                    remove(kb);
                    break;
                case "clear":
                    clear();
                    break;
                case "search":
                    search(kb);
                    break;
                case "sort":
                    selectionSort();
                    System.out.println("The list of contacts have been successfully sorted");
                    break;
                case "exit":
                quitTrigger = true;
                System.out.println("\nExiting contact manager...");
                break;

                default:
                    System.out.println("Invalid choice, please try again");

            }
        }while(!quitTrigger);

    }
    public void remove(Scanner kb){//single remove method needs works got tired so you know what it is
        System.out.print("Please enter the name of contact: ");
        String name = kb.next();

        System.out.print("Please enter last name of the contacts: ");
        String lastName = kb.next();

        System.out.print("Please enter the phone number of the contact: ");
        String phone = kb.next();

        phone = phone.toLowerCase();
        name = name.toLowerCase();
        lastName = lastName.toLowerCase();

        contact newContact = new contact(name,lastName, phone);

        for(int i = 0; i < this.array.size(); i++){
            if(newContact.equals(this.array.get(i))){
                array.remove(i);
                System.out.println("\nContact has been successfully been removed from the list...returning back to menu");
                break;
            }else {
                System.out.println("The contact was not found in the list...returning back to the menu");
            }

        }

    }

    public void display(){//prints all of the contents of the array to the screen
        if(this.array.size() == 0)
            System.out.println("There are no contacts to display...Returning to menu");

        else {
            for (contact contact : this.array) {
                System.out.println(contact + "\n");
            }
            System.out.println("--End of contacts lists--");
        }

    }

    public void showMe(){//showMe is a used for displaying menu commands to the screen
        System.out.println("\n ---Commands for contact manager---");
        System.out.println (

                "1) add (Will add a new contact to the list)\n"+
                "2) clear (Will clear the entire list)\n" +
                "3) list (Displays all the commands in the manager)\n" +
                "4) remove (Will remove a single contact from the list)\n"+//note feature still in progress
                "5) search (Displays all contacts with the passed in first name)\n" +
                "6) show (Will display all the contacts added onto the list)\n" +
                "7) sort (Alphabetizes and sorts your contacts)\n");

    }

    public void search(Scanner kb){//will search contacts list by first name and display results

        System.out.print("Please enter a first name: ");
        String name = kb.next();
        name = name.toLowerCase();

        for(int i =0; i<this.array.size(); i++){
            contact temp = array.get(i);
            if(temp.getName().equals(name)){
                System.out.println(temp);
            }
        }

    }

     public void add (){//will add a new contact to the list
         Scanner input = new Scanner(System.in);

         System.out.print("Please enter a first name: ");
         String name = input.next();
         name = name.toLowerCase();

         System.out.print("Please enter a last name: ");
         String surName = input.next();
         surName = surName.toLowerCase();

         System.out.print("Please enter a phone number: ");
         String phoneNumber = input.next();
         phoneNumber = phoneNumber.toLowerCase();

         contact newContact = new contact(name,surName,phoneNumber);
         array.add(newContact);

         System.out.println("\nContact successfully added");

     }

     public void clear(){//used for clearing the array of all contacts
        if(this.array.size() == 0)
            System.out.println("The list has been cleared of all contacts");

        else {
            Scanner kb = new Scanner(System.in);
            System.out.println("please type in yes to confirm the clear or no to return back to menu.");
            String choice = kb.next();
            choice = choice.toLowerCase();

            if(choice.equals("yes")) {

                try {
                    this.array.clear();
                    FileWriter fwOb = new FileWriter("contacts.txt", false);
                    PrintWriter pwOb = new PrintWriter(fwOb, false);
                    pwOb.flush();
                    pwOb.close();
                    fwOb.close();
                    System.out.println("The list has been cleared of all contacts");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            else
                System.out.println("There have been no changes to the list...returning back to main menu");
        }

     }

     public void onStart() throws FileNotFoundException {//helper method used for saving contacts for next time program is ran
        Scanner newFile = new Scanner(new File("contacts.txt"));

        while(newFile.hasNext()){
            String name = newFile.next();
            String lastName = newFile.next();
            String phoneNumber = newFile.next();

            contact newContact = new contact(name,lastName,phoneNumber);
            this.array.add(newContact);
        }
        newFile.close();

     }

     public void onEnd(){//saves contents of array for next start up
        try {
            PrintStream newFile = new PrintStream("contacts.txt");
            for (contact ref : this.array) {
                newFile.println(ref.getName());
                newFile.println(ref.getSurName());
                newFile.println(ref.getPhoneNumber());
            }
            System.out.println("contacts successfully saved...");
            newFile.flush();
            newFile.close();

        } catch (FileNotFoundException e) {
            System.out.println(e + "File not found exception....");
        }

     }

     public void selectionSort() {//sort
         if (this.array == null || this.array.size() < 2)
             return;

         int start, smallest, cur;
         contact temp;

         for (start = 0; start < this.array.size() - 1; start++) {
             smallest = start;

             for (cur = start + 1; cur < this.array.size(); cur++) {
                 if (this.array.get(cur).compareTo(array.get(smallest)) < 0)
                     smallest = cur;
             }

             temp = array.get(start);
             array.set(start, array.get(smallest));
             array.set(smallest, temp);

         }

     }

}//end of class
