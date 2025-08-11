import java.util.Scanner;
public class program1 
{
public static void main(String[] args)
{
System.out.println("enter a number:");
Scanner input=new Scanner(System.in);
int n=input.nextInt();
if(n%2==0)
{
System.out.println("This number is Even");
}
else
System.out.println("This number is Odd");
}
}