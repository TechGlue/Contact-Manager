import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/*
## to do ##
write a sort
clear method//done and working//will clear the entire folder as well...
clean up code so its nots all in main class
take in lower case inputs as well
make it save and ready input into array on start up//so far it looks like the save file function is working
needs a single remove method that wil clear one single charcter


 */

 class contactMain {

    protected ArrayList <contact> array = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        try {
            contactMain ref = new contactMain();
            ref.onStart();
            ref.displayMenu();
            ref.onEnd();
        } catch (FileNotFoundException e) {
            System.out.println(e +"The contacts.txt file has been removed please add it back");
        }

    }

    public void displayMenu(){

        Scanner kb = new Scanner(System.in);

        boolean quitTrigger = false;
        String choice;

        do{
            System.out.println("\n---Chose a menu option---");

            System.out.print("[Menu] Enter an action: " );
            choice = kb.next();

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
                case "clear":
                    clear();
                    break;
                    case "exit":
                    quitTrigger = true;
                    System.out.println("Exiting contact manager...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again");

            }
        }while(!quitTrigger);


    }

    public void display(){
        if(this.array.size() == 0)
            System.out.println("There are no contacts to display...Returning to menu");
        else {
            for (contact contact : this.array) {
                System.out.println(contact + "\n");

            }
            System.out.println("--End of contacts lists--");
        }
    }

    public void showMe(){
        System.out.println("\n ---Commands for contact manager---");
        System.out.println (
                "1) list ( To display all the commands in the manager)\n" +
                "2) show (will display all the contacts added onto the list)\n" +
                "3) clear (will clear the entire list)\n" +
                "4) remove (will remove a single contact from the list)\n" +
                "5) Alphabetize and sort the list");

    }

     public void add (){

         Scanner input = new Scanner(System.in);

         System.out.print("Please enter a first name: ");
         String name = input.next();

         System.out.print("Please enter a last name: ");
         String surName = input.next();

         System.out.print("Please enter a phone number: ");
         String phoneNumber = input.next();

         contact newContact = new contact(name,surName,phoneNumber);//creating a new contact with the collected data

         array.add(newContact);

         System.out.println("\nContact successfully added");
     }

     public void clear(){
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
            else {
                System.out.println("There have been no changes to the list...returning back to main menu");
            }
        }

     }

     public void onStart() throws FileNotFoundException {

        Scanner newFile = new Scanner(new File("contacts.txt"));

        while(newFile.hasNext()){
            String name = newFile.next();
            String lastName = newFile.next();
            String phoneNumber = newFile.next();

            contact newContact = new contact(name,lastName,phoneNumber);
            this.array.add(newContact);
        }
        System.out.println("Succefully passed in old names");//test this method and remove the check when perfect...
        newFile.close();

     }

     public void onEnd(){
        
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



}
