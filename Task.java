//To-Do project
//This is a class that sets and returns the required items for a to-do list
import java.time.LocalDate;

public class Task implements Comparable<Task> {

    //@param taskTitle - this is the title of the task being added to the to-do list
    //@param dateDue - this is the date that the task being added is due
    //@param isComplete - this is the completeion of the task being added - complete or not complete
    private String taskTitle;
    private LocalDate dateDue;
    private boolean isComplete; 
   
    //Constructor 
    //@param t - this is the title of the task being added to the to-do list
    //@param d - this is the date that the task being added is due
    //@param c - this is the completeion of the task being added - wether it is due or not
    public Task(String t, LocalDate d, boolean c) {
        this.taskTitle = t;
        this.dateDue = d;
        this.isComplete = c;
    }//end Task

    //setter for title of task
    //@param title - this sets the title of the task being added
    public void setTaskTitle(String title) {
        this.taskTitle = title;
    }//end of setTaskTitle

    //setter for due date of task being added
    //@param date - this sets the date of the task being added
    public void setDateDue(LocalDate date) {
        this.dateDue = date;
    }//end of setDateDue

    //compareTo 
    //returns less than 0 , 0 , or greater than 0 
    public int compareTo(Task task) {
        return dateDue.compareTo(task.getDateDue());
    }//end compareTo

    //setter for Completion of a task
    //@param completion - this sets the completion of the task - complete or not complete
    public void setCompletion(boolean completion) {
        this.isComplete = completion;
    }//end of setcompletion

    //getter for title of task
    //@return - the title of the task
    public String getTaskTitle() {
        return taskTitle;
    }//end of getTaskTitle 

    //getter for the due date of the task
    //@return- the date the task is due 
    public LocalDate getDateDue() {
        return dateDue;
    }//end of getDateDue

    //getter for the task completion
    //@return - the completion of the task - complete or not complete
    public Boolean getCompletion() {
        return isComplete;
    }//end of getCompletion

    //toString 
    //@return - this toString returns all the information of the tasks such as title, date due, and the completion of the task
    public String toString() {
        String string;

        //if isComplete is false it returns the statement below
        if(isComplete == false) {
            string = "NOT COMPLETED";
        }
        //else isComplete is true and returns the statement below 
        else {
            string = "COMPLETED";
        }

        return dateDue + "   " + taskTitle + "    "+ string;
    }//end of toString
}//end of Task