//Menu - this class is the menu that runs and displays for the user
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class toDoMenu {
    
    public static void main(String[] args) {
        //runs the program
        toDoMenu menu = new toDoMenu();
    }//end of main

    //@param scanner for the inputs
    //@param taskList - list for tasks 
    private Scanner input;
    private TaskList taskList;

    //default constructor
    //@param input - scanner inputs from users
    //@param taskList - creating new list
    //@param mainMenu - menu of the to-do list program
    public toDoMenu() {
        input = new Scanner(System.in);
        taskList = new TaskList();
        this.mainMenu();
    }//end of constructor

    //mainMenu
    private void mainMenu() {

        //@param userSelection - this is the int that the user inputs, used for switch statements, this changes upon the user input
        int userSelection = 0;

        //while - is the userSelection is not 4 then run
        while(userSelection != 4) {
            //@param totalTasks - this gets the size of the whole to-do list , total number of tasks in the list
            //@param totalTasksDone - this gets the total amount of tasks completed
            int totalTasks = taskList.taskListSize();
            int totalTasksDone = taskList.amountCompleted();

            //print statements for the main menu
            System.out.println();
            System.out.println("----------------------------------------------");
            System.out.println("|         WELCOME TO YOUR TO-DO LIST         |");
            System.out.println("----------------------------------------------");   
            System.out.println();
            System.out.println("---------------------------------------------------------------------");
            System.out.println("|   YOU HAVE " + totalTasks + " TASK(S) IN YOUR TO-DO LIST AND " + totalTasksDone + " TASK(S) COMPLETED   |");
            System.out.println("---------------------------------------------------------------------");
            System.out.println();
            System.out.println("PLEASE SELECT AN OPTION FROM THE LIST BELOW");
            System.out.println("-------------------------------------------");
            System.out.println();
            System.out.println("[1] ADD A TASK");
            System.out.println("[2] EDIT A TASK");
            System.out.println("[3] DISPLAY YOUR TASKS");
            System.out.println("[4] QUIT THE PROGRAM");
            System.out.println();
            System.out.print(">  ");
        

            //try - To check for errors 
            try {
                userSelection = Integer.parseInt(input.nextLine());
            }//end of try
            //Throw expection if there is an error when converting to int
            catch(NumberFormatException n) {
                //error message is given by default state in switch statement
            }//end of catch

            //switch - depending on userSelection number entered it will go to another menu
            switch (userSelection) {
                //if user inputs 1 go to Menu that adds a task to the to-do list
                case 1:
                    addTaskMenu();
                    break;
                //if user inputs 2 go to edit menu
                case 2:
                    //if list is empty print statement and return 
                    if(taskList.isEmpty()) {
                        System.out.println();
                        System.out.println("---------------------------------------------------------------------------------");
                        System.out.println("!!THERE ARE NO TASKS IN THE LIST. PLEASE ADD SOME TASKS BEFORE TRYING ANYTHING!!");
                        System.out.println("---------------------------------------------------------------------------------");
                        System.out.println();
                        break;                  
                    }//end of if
                    selectEditTaskMenu();
                    break;
                //if user inputs 3 go to display menu, and if list is empty print statement and return to main menu 
                case 3:
                    //if list is empty print statement and return to main menu
                    if(taskList.isEmpty()) {
                        System.out.println();
                        System.out.println("----------------------------------------------------");
                        System.out.println("!!THERE ARE NO TASKS IN THE LIST. PLEASE ADD SOME!!");
                        System.out.println("----------------------------------------------------");
                        System.out.println();
                        break;
                    }//end of if
                    displayTasksMenu();
                    break;
                //if user inputs 4 exit program
                case 4:
                    exit();
                    break;
                //default print statement if user enters anything other than the 1-4
                default: 
                    System.out.println();
                    System.out.println("--------------------------------------------------");
                    System.out.println("!!PLEASE SELECT A VALID NUMBER THAT IS DISPLAYED!!");
                    System.out.println("--------------------------------------------------");
                    System.out.println();

            }//end of while 
        }//end of while 
        userSelection = 0;
    }//end of mainMenu

    //menu to add tasks 
    private void addTaskMenu() {
        //print statements at start of add task menu
        System.out.println();
        System.out.println("---------------------------------------------------------------"); 
        System.out.println("|    YOU ARE CURRENTLY ADDING A NEW TASK TO THE TO-DO LIST    |");
        System.out.println("---------------------------------------------------------------"); 
        System.out.println();

        //@param completeion when task is added to list competion is set to false
        //@param isDateInvalid is set to true
        //@param dueDate set to null for list
        //@param currentDate - current date from set from system clock
        boolean completion = false;
        boolean isDateInvalid = true;
        LocalDate dueDate = null;
        LocalDate currentDate = LocalDate.now();

        //while isDateInvalid = true run
        while(isDateInvalid) {
            //prints asking for user to input due date
            System.out.println("PLEASE ENTER THE DUE DATE FOR YOUR TASK EX: YYYY-MM-DD");
            System.out.println("------------------------------------------------------");
            System.out.println();
            System.out.print(">  ");

            //@param date - user input
            String date = input.nextLine();
            //@param dateFormat - user input date must be in format of year/month/date
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            
            //try - test code 
            try {
                //converting the string and DateTimeFormatter into LocalDate
                dueDate = LocalDate.parse(date, dateFormat);
                //compareTo returns an int -1, 0, 1
                //if dueDate is before currentDate then print error statement
                if(dueDate.compareTo(currentDate) < 0 ) {
                    System.out.println();
                    System.out.println("---------------------------------------------------");
                    System.out.println("!!THE DATE YOU HAVE ENTERED IS BEFORE TODAYS DATE!!");
                    System.out.println("---------------------------------------------------");
                    System.out.println();
                }//end of if
                //else set to isDateInvalid to false
                else {
                    isDateInvalid = false;
                }//end of else
            }
            //catch - if there is any other error throw expection error and print statement
            catch (Exception error) {
                System.out.println();
                System.out.println("-----------------------------");
                System.out.println("!!Pease enter a Valid Date!!");
                System.out.println("-----------------------------");
                System.out.println();
           }//end of catch
        }//end of while 2
        
        //@param isTitleInvalid - set to true
        //@param tasktitle - setting title to null for list
        boolean isTitleInvalid = true;
        String taskTitle = null;

        //while isTitleInvalid = true, run
        while(isTitleInvalid) {

            //print statement for user inputting title 
            System.out.println();
            System.out.println("PLEASE ENTER A TITLE FOR YOUR TASK: ");
            System.out.println("------------------------------------");
            System.out.println();
            System.out.print(">  ");
            //@param taskTitle set to user input
            taskTitle = input.nextLine();

            //if tasktitle is not empty set isTitleInvalid set to false 
            if(!taskTitle.isEmpty()) {
                isTitleInvalid = false;
            }//end of if 
            //else - if list is empty print statement
            else {
                System.out.println();
                System.out.println("------------------------------------------------------------------------------");
                System.out.println("!!THE TITLE YOU HAVE ENTERED IS NOT A VALID TITLE PLEASE ENTER A VALID TITLE!!");
                System.out.println("------------------------------------------------------------------------------");
            }//end else 
        }//end of while

        System.out.println();
        System.out.println("------------------------------------------------------------");
        System.out.println("THE TASK YOU HAVE ENTERED HAS BEEN ADDED TO YOUR TO-DO LIST");
        System.out.println("------------------------------------------------------------");

        //@param taskCreated - creates the tasks to be added to list 
        Task taskCreated = new Task(taskTitle, dueDate , completion);
        //adding the tasks created to taskList
        taskList.addTask(taskCreated);
    }//end of add menu


    //selectEditTaskMenu - menu for editing menu
    public void selectEditTaskMenu() {
        //creating list for edits
        List<Task> editList = taskList.listInformation();
        //@param userSelection set to 0
        int userSelection = 0;

        //while true 
        while (true) {
            //print statements for edit menu
            System.out.println();
            System.out.println();
            System.out.println("PLEASE ENTER THE NUMBER OF THE TASK YOU WOULD LIKE TO EDIT");
            System.out.println("----------------------------------------------------------");
            
            //for loop - going through list and displaying list
            //list is displayed with number attached starting at 1
            System.out.println();
            System.out.println("---------------------------------------");
            System.out.println("|   LIST OF TASKS THAT YOU CAN EDIT   |");
            System.out.println("---------------------------------------");
            System.out.println("DUE DATE |  TITLE | COMPLETEION STATUS");
            for (int i = 0; i < editList.size(); i++) {
                Task taskEdit = editList.get(i);
                System.out.println("["+ (i+1) + "] " + taskEdit.toString());
            }//end of for 
            System.out.println();
            System.out.print(">  ");
            //try - test userSelection 
            try {
                //convert from string to int
                userSelection = Integer.parseInt(input.nextLine());
            }//end of try
            //catch if it isnt valid int when converting 
            catch(NumberFormatException n) {
                //error message displayed from default in switch statements
            }//end of catch

            //if user selection if greater than 0 and user selection if less than size of list
            //this selects task wanting to be edited 
            if(userSelection > 0 && userSelection <= editList.size()) {
                //subtracts one from list because lists start at 0
                Task taskSelected = editList.get(userSelection - 1);
                editSelectedTaskMenu(taskSelected);
                break;
            }//end if
            //else print statements
            else {
                System.out.println();
                System.out.println("----------------------------------------------------------------------------------");
                System.out.println("!!PLEASE ENTER A VALID NUMBER THAT CORRESPONDS TO THE TASK THAT YOU WANT TO EDIT!!");
                System.out.println("----------------------------------------------------------------------------------");
                System.out.println();
            }//end of else
        }//end of while
    }//end selectEditTaskMenu

    //menu for editing task selected
    private void editSelectedTaskMenu(Task editingTask) {
        //@param userSelection - set userSelection to 0
        int userSelection = 0;

        //while userSelection if not 4 run
        while (userSelection != 4) {
            System.out.println();
            System.out.println("PLEASE SELECT AN OPTION FROM BELOW");
            System.out.println("----------------------------------");
            System.out.println();
            System.out.println("[1] UPDATE THE WHOLE TASK, THE DUE DATE OR TITLE");
            System.out.println("[2] SET A TASK TO COMPLETED");
            System.out.println("[3] REMOVE A TASK");
            System.out.println("[4] GO BACK TO MAIN MENU");
            System.out.print(">  ");
        
            //try - test
            try {
                //userSelection - input converted to int
                userSelection = Integer.parseInt(input.nextLine());
            }//end try
            //catch - throw numberFormatException when user doesnt enter valid number when converting
            catch (NumberFormatException n) {
                //error message displayed by default in switch statement
            }//end catch

            //switch - based on userSelection
            switch(userSelection) {
                //if user input = 1 go to updateinformation
                case 1:
                    updateInformation(editingTask);
                    mainMenu();
                    break;
                //if user input = 2 go to setCompleted
                case 2:
                    setCompleted(editingTask);
                    mainMenu();
                    break;
                //if user input = 3 removeTask
                case 3:
                    removeTask(editingTask);
                    mainMenu();
                    break;
                //if user input = 4 return to mainMenu
                case 4:
                    mainMenu();
                    break;
                //default - return this statement if user input is not valid
                default:
                    System.out.println();
                    System.out.println("-----------------------------------------------------------------");
                    System.out.println("!!YOU DID NOT ENTER A VALID NUMBER. PLEASE ENTER A VALID NUMBER!!");
                    System.out.println("-----------------------------------------------------------------");
                    System.out.println();
                    break;
            }//end switch
        }
        //settinguserselection back to 0 for Menus
        userSelection = 0;
    }//end editSelectedTaskMenu


    //update information menu
    private void updateInformation(Task taskUpdate) {
        
        //date invalid set to true
        boolean isDateInvalid = true;
        LocalDate dueDate = null;
        LocalDate currentDate = LocalDate.now();

        //print statements for menu
        System.out.println();
        System.out.println("--------------------------------");
        System.out.println("YOU ARE CURRENTLY EDITING A TASK");
        System.out.println("--------------------------------");
        System.out.println();
        System.out.println("YOUR CURRENT TITLE IS: " + taskUpdate.getTaskTitle());
        System.out.println();
        System.out.println("!!IF YOU DO NOT WANT TO UPDATE THE TITLE TASK PLEASE HIT ENTER!!");
        System.out.println();
        
        //string input for task title
        String updatedTaskTitle = input.nextLine();
        
        //if task title is not empty update task
        if(!updatedTaskTitle.isEmpty()) {
            taskUpdate.setTaskTitle(updatedTaskTitle);
        }//end if 

        //while is date invalid = true run
        while(isDateInvalid) {
            System.out.println();
            System.out.println("THE CURRENT DUE DATE FOR THE TASK IS: " + taskUpdate.getDateDue());
            System.out.println();
            System.out.println("!!IF YOU WISH NOT TO UPDATE THE DUE DATE HIT ENTER!!");
            System.out.println();
            System.out.print(">  ");
            //string input for dateDue
            String date = input.nextLine();
            //if dateDue is empty go back
            if(date.isEmpty()) {
                break;
            }//end if
            //date format
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            
            //try - test code 
            try {
                //converting the string and DateTimeFormatter into LocalDate
                dueDate = LocalDate.parse(date, dateFormat);
                //compareTo returns an int -1, 0, 1
                //if dueDate is before currentDate then print error statement
                if(dueDate.compareTo(currentDate) < 0 ) {
                    System.out.println();
                    System.out.println("--------------------------------------------------------------------------------");
                    System.out.println("!!THE DUE DATE YOU ENTERED IS BEFORE TODAYS DATE PLEASE ENTER A VALID DUE DATE!!");
                    System.out.println("--------------------------------------------------------------------------------");
                    System.out.println();
                }//end of if
                //else set to isDateInvalid to false
                else {
                    taskUpdate.setDateDue(dueDate);
                    isDateInvalid = false;
                }//end of else
            }
            //catch - if there is any other error throw expection error and print statement
            catch (Exception error) {
                System.out.println();
                System.out.println("-------------------------------");
                System.out.println("!!!PLEASE ENTER A VALID DATE!!!");
                System.out.println("-------------------------------");
                System.out.println();
           }//end of catch
        }//end of while 
    }//end updateInformation
    

    //option for setting completion to complete for tasks
    public void setCompleted(Task completedTask) {
        //set completion to true
        taskList.isCompleted(completedTask);
        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println("!!THE TASK HAS BEEN SET TO COMPLETED!!");
        System.out.println("--------------------------------------");
        System.out.println();
    }//end setCompleted
    
    //option for removing task
    public void removeTask(Task taskRemove) {
        //removes tasks selected
        taskList.removeTask(taskRemove);
        System.out.println();
        System.out.println("-----------------------------------------------");
        System.out.println("!!THE TASK YOU HAVE SELECTED HAS BEEN REMOVED!!");
        System.out.println("-----------------------------------------------");
        System.out.println();
    }//end for removing task

    //menu for displaying task
    public void displayTasksMenu() {
        //@param userSelection - set to 0 
        int userSelection = 0;

        //while user selection if not 3 run
        while(userSelection != 3) {
            //print statement for display menu
            System.out.println();
            System.out.println("PLEASE SELECT AN OPTION FROM THE MENU BELOW: ");
            System.out.println("---------------------------------------------");
            System.out.println();
            System.out.println(" [1] SHOW YOUR TASKS");
            System.out.println(" [2] SORT YOUR TASKS BY UPCOMING DUE DATE AND DISPLAY THE TASKS");
            System.out.println(" [3] GO BACK TO THE MAIN MENU");
            System.out.print(">  ");
            
            //try - test
            try {
                //convert userSelection from string to int
                userSelection = Integer.parseInt(input.nextLine());
            }
            //catch - throw numberformatexception when user doesn't input valid number 
            catch (NumberFormatException n) {
                //error message from default
            }
            //switch - depending userSelection
            switch (userSelection) {
                //if user input = 1 display tasks - will display in order entered
                case 1:
                    displayTasks();
                    mainMenu();
                    break;
                //if user input = 2 display list sorted by nearest due date
                case 2:
                    displayUpcomingDueDate();
                    mainMenu();
                    break;
                //if user input = 3 return to main menu
                case 3:
                    mainMenu();
                    break;
                //default - enter valid number
                default:
                    System.out.println();
                    System.out.println("---------------------------------------------");
                    System.out.println("!!PLEASE ENTER A VALID OPTION FROM THE MENU!!");
                    System.out.println("---------------------------------------------");
            }//end swtich
        }//end while

        //set user selection to 0
        userSelection = 0;
    }//end displayTasksMenu

    //method for displaying tasks
    public void displayTasks() {
        //print statement when printing list
        System.out.println();
        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println("|         YOUR TO-DO LIST           |");
        System.out.println("-------------------------------------");
        System.out.println();
        System.out.println();
        System.out.println("DUE DATE |  TITLE | COMPLETEION STATUS");
        System.out.println();
        //@param tList - create list to display
        List<Task> tList = taskList.listInformation();
        //for loop and display list
        for (Task t : tList) {
            System.out.println(t);
            
        }//end for
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }//end displayTasks

    //method displaying list by upcoming due date
    public void displayUpcomingDueDate() {
        //print statements for list display
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("|         TASK LIST SORTED BY DUE DATE           |");
        System.out.println("--------------------------------------------------");
        System.out.println();
        System.out.println("DUE DATE |  TITLE | COMPLETEION STATUS");
        System.out.println();
        //@param upcomingList - create list for upcoming due dates
        List<Task> upcomingList = taskList.upComingDueDate();
        //for loop to display list
        for(Task d : upcomingList) {
            System.out.println(d);
        }//end for
        
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }//end displayUpcomingDueDate

    //exit option
    public void exit() {
        //print statement for exiting
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("-----------------------------------------------------");
        System.out.println("|   EXITING NOW. THANK YOU FOR USING MY PROGRAM.    |");
        System.out.println("-----------------------------------------------------");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        //exits program
        System.exit(0);
    }//end exit


}//end toDoMenu
