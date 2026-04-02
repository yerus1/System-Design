package OpenClosed;

interface Shape {
    double calculateArea();
}

class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class Rectangle implements Shape {
    private double length;
    private double breadth;

    public Rectangle(double length, double breadth) {
        this.length = length;
        this.breadth = breadth;
    }

    public double calculateArea() {
        return length * breadth;
    }
}

class AreaCalculate {
    public double calculateArea(Shape shape) {
        return shape.calculateArea();
    }
}

public class OCWithOutViolation {
    public static void main(String[] args) {
        Shape rectangle = new Rectangle(5, 5);
        Shape circle = new Circle(5);
        AreaCalculate areaCalculator = new AreaCalculate();
        System.out.println(areaCalculator.calculateArea(circle));
        System.out.println(areaCalculator.calculateArea(rectangle));
    }
}
