import java.util.Scanner;

public class GradeCalculator {

    public static Scanner sc = new Scanner(System.in);

    int id;
    String name;
    int[] marks = new int[5];
    float avgPrec;
    int totmarks;
    char Grade;
    
    public GradeCalculator(){}

    public void calulate(){
        int sum=0;
        for(int i=0;i<5;i++){
            sum += this.marks[i];
        }
        this.totmarks = sum;
        this.avgPrec = sum/5;

        if(this.avgPrec >= 0 && this.avgPrec < 60){this.Grade = 'F';}
        else if(this.avgPrec >= 60 && this.avgPrec < 70){this.Grade = 'D';}
        else if(this.avgPrec >= 70 && this.avgPrec < 80){this.Grade = 'C';}
        else if(this.avgPrec >= 80 && this.avgPrec < 90){this.Grade = 'B';}
        else if(this.avgPrec >= 90 && this.avgPrec <= 100){this.Grade = 'A';}
        else{
            System.err.println("Invalid marks\n");
        }

    }

    public void addData(){
        System.out.println("Enter id of Student");
        this.id = sc.nextInt();
        System.out.println("Enter Name of Student");
        this.name = sc.next();
        System.out.println("Enter marks of 5 subjectsout of 100");
        for(int i=0;i<5;i++){
            this.marks[i] = sc.nextInt();
        }
        
    }
    public void display(){
        System.out.println("Name: "+ this.name);
        System.out.println("Id: "+ this.id);
        System.out.println("Average Percentage: "+ this.avgPrec);
        System.out.println("Total Marks: "+ this.totmarks);
        System.out.println("Grade: "+ this.Grade); 
    }

    public static void main(String[] args){
        int n;
        System.out.println("Enter the number of Students");
        n = sc.nextInt();
        GradeCalculator[] students = new GradeCalculator[n];

        for(int i=0; i<n; i++){
            students[i] = new GradeCalculator();
            students[i].addData();
            students[i].calulate();
        }
        System.out.println("Data Entered\n");
        for(int i=0;i<n;i++){
            students[i].display();
        }   
        System.out.println("\n\nEnd of List!");

    }
    
}
