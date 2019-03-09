public class testCircle {
    public static void main(String[] args)
    {
        //creates a new object c1 of type Circle
        //the cass circle is supplied with the instruction to create a new object
        //this will use the default constuctor
        Circle c1 = new Circle();
        //calling Circle methods
        //Remember to put the object that you want to use this methods onto firt
        System.out.println("Cricle c1 has radius " + c1.getRadius() + " and an area of " + c1.getArea() );
        //create a new object c2,supply radius paraemters to be used in second constructor
        Circle c2 = new Circle(2.0);
        //Calling methods of Circle for c2
        System.out.println("The circle c2 has radius " + c2.getRadius() + " and area " + c2.getArea());
    }
}
