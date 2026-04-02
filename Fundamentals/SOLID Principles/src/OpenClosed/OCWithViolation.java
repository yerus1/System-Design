package OpenClosed;

class AreaCalculator {
    public double calculateArea(String shape,double radius,double length,double breadth){
        if(shape.equals("circle")){
            return Math.PI*radius*radius;
        } else if (shape.equals("rectangle")) {
            return length*breadth;
        }
        return 0;
    }
}

public class OCWithViolation {
    public static void main(String[] args) {
        AreaCalculator areaCalculator=new AreaCalculator();
        System.out.println(areaCalculator.calculateArea("circle",5,0,0));
        System.out.println(areaCalculator.calculateArea("rectangle",0,5,5));
    }
}
