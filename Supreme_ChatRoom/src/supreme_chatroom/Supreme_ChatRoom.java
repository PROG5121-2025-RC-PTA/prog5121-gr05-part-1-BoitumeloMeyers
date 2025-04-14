/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package supreme_chatroom;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
/**
 *
 * @author RC_Student_lab
 */
public class Supreme_ChatRoom {
    
    //Declare here so all methods can access it 
    private ArrayList<User> users = new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //Create instance of the app
        Supreme_ChatRoom app = new Supreme_ChatRoom();
        
        
        //Run the application
        //Guiged by chatGPT
        app.run();
        
    }
    
    //Runs the main loop of the application 
    public void run(){
        
       //Store registerd user to the list
        Scanner myInput = new Scanner(System.in);
        
        //Flag to control program Loop
        boolean running = true;
        
        //main Loop
        //Guided by ChatGPT
        while(running){
            //Ask user for choice
            String choice = getChoice(myInput);
            
           switch(choice){
               case "no":
                   registerUser(myInput, users);
                   break;
               case "yes":
                   //Creatte login instance
                   Login login = new Login(users);
                   login.loginStatus();
                   break;
               case "exit":
                   System.out.println("Exiting the program");
                   running = false;
                   //Stop Loop
                   break;
               default:
                   System.out.println("Invalid input. Please enter yes, no, or exit");
                   
           }
        }
    }
    
    //Get user choice
    private String getChoice(Scanner myInput){
        
        //Prompt user for input on whether they have an account
        System.out.println("Do you have an account? (yes/no/exit): ");
        
        //The users input must be in lower case or input the will be invalid
        return myInput.nextLine().toLowerCase();
    }
    
    //Register new user
    private void registerUser(Scanner myInput,ArrayList<User> users){
        
        //Handles users registration
        System.out.println("---Register---");
        
        //validate username
        String userName;
        while(true){
            System.out.println("Enter your desired username (must contain'_' and be 5 characters or less)");
            userName = myInput.nextLine();
            if(checkUsername(userName)){
                break;
                //Break out of the loop if the username is valid
            }
        }
        
        //validate password
        String userPassword;
        while(true){
            System.out.println("Please enter password (8 characters. 1 capital letter, 1 special character)");
            userPassword = myInput.nextLine();
            if (checkPassword(userPassword)){
                break;
                //Braek out of the loop if the password is valid
            }
        }
        //validate cell number
        String cellNumber;
        while(true){
            System.out.println("Please enter your cellphone number(must start with +27, followed by 9 digits");
            cellNumber= myInput.nextLine();
            if(checkCellNumber(cellNumber)){
                break;
                //Break out of the loop if the password id valid
            }
        }
        
        
        //Add user to the list
        users.add(new User(userName, userPassword, cellNumber));
        System.out.println("Registration successful");
    }
    
    //check username
    private boolean checkUsername(String userName){
        
        //Get username
        //Check username
         if(userName.contains("_") && userName.length() <= 5){
             return true;
         }else {
             System.out.println("Invalid username. The username does not meet the quirements of '_' and 5 characters");
             return false;
         }
             
    }
    
    private boolean checkPassword(String userPassword){
       
        //Get password
        //Check if the password meet the minimum requirements
        boolean isValid = userPassword.length() == 8 &&
                          Pattern.compile("[A-Z]").matcher(userPassword).find() &&
                          Pattern.compile("[!@#$%^&*()_+=<>?/{}\\[\\]-]").matcher(userPassword).find();
        if(!isValid){
            System.out.println("Invalid password. The password does not meet the minimum requirements of having 8 characters, with 1 capital letter, and 1 special character");
            
        }
        return isValid;
        
        //Get cellphone number
    } 
    //check cellphone Number
    // must start with +27 and followed by 9 digits
    private boolean checkCellNumber(String cellNumber){
        if (cellNumber.matches("\\+27\\d{9}")){
            return true;
        }else {
            System.out.println("Invalid cellphone Number.  It must be a registed");
            return false;
        }
    }
    
    //class representing user
    static class User{
        String userName;
        String userPassword;
        String cellNumber;
        
        User(String userName, String userPassword, String cellNumber){
            this.userName = userName;
            this.userPassword = userPassword;
            this.cellNumber = cellNumber;
        }
    } 
    
    //Login class
    
    static class Login{
        //class for handling user login
          private ArrayList<User> users;
          
          
          //constructor that recieves the user list
          public Login(ArrayList<User> users){
              this.users = users;
          }
          
          
          public void loginStatus(){
        //Prompt user for login details
        
        Scanner myInput = new Scanner(System.in);
        System.out.println("---Login---");
        System.out.println("Please enter username: ");
        String enteredUserName = myInput.nextLine();
        
         System.out.println("Please enter password: ");
        String enteredUserPassword = myInput.nextLine();
        
        boolean confirm = false;
        
        //Loop through user to verify credentials
        for(User user : users){
            if(user.userName.equals(enteredUserName) && user.userPassword.equals(enteredUserPassword)){
                confirm = true;
                break;
            }
        }
        //Confirm the login attemp
        if(confirm){
            
            System.out.println("Login successful.");
        }else{
            System.out.println("Invalid login information.");
        }
    }
  
    
}
}