//This class has the methods that will be used for the menu 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskList {

    //@param taskList - this is the list of tasks
    private List<Task> taskLists;
    
    //creating the list for Task
    public TaskList() {
        taskLists = new ArrayList<Task>();
    }//end TaskList

    //addTask - adds a new task to the list taskList
    //@param addingTask - this is the new task being added
    public void addTask(Task addingTask) {
        taskLists.add(addingTask);
    }//end of addTask

    //upComingDueDate - this method is sorts the due dates in order and displays the Closest Due date of a task
    //@return taskList - returns the list that is sorted by the due date
    public List<Task> upComingDueDate() {
        //sort list by upcoming due date ascending order
        Collections.sort(taskLists);
        return taskLists;
    }//end of UpComingDueDate

    //taskListSize - this method gets the size of the list
    public int taskListSize() {
        return taskLists.size();
    }//end of taskListSize
    
    //isEmpty - check to see if the list is empty
    //@return true or flase depending on the size of the list
    public boolean isEmpty() {
        return taskListSize() == 0;
    }//end of isEmpty

    //isCompleted - this method sets the task completion to completed
    //@param task - This is the task being selected to be marked as completed
    public void isCompleted(Task task) {
        task.setCompletion(true);
    }//end of isCompleted

    //amountCompleted - this method is going to be used to display the amount of tasks completed in the main menu 
    //@return the amount of tasks that are marked as completed
    public int amountCompleted() {
        //@param completedTasks - counter for amount of completed tasks
        int completedTasks = 0;

        //goes through the list and adds 1 to completedTasks for every task that isCompleted = true
        for (Task c : taskLists) {
            //if status of completion is true then add to the list
            if(c.getCompletion().equals(true)) {
                completedTasks++;
            }//end of if 
        }//end of for

        return completedTasks;
    }//end of amountCompleted

    //listInformation - this method returns a list with all the information of tasks inside of the to-do list
    //@return the list with tasks and their information
    public List<Task> listInformation() {
        return taskLists;
    }//end of listinformation


    //removeTask - this method removes a task that the user wants to remove
    public void removeTask(Task task) {
        taskLists.remove(task);
    }//end of removeTask
}//end of TaskList
