abstract class Car{
    Car(){
        System.out.println("Running Car Default COnstructor");
    }
    abstract void start();
    abstract void end();
}

class McLaren extends Car{
    void start(){
        System.out.println("McLaren is on FIREEE");
    }

    void end(){
        System.out.println("Engines OFF");
    }

    void engineSpecs(){
        System.out.println("Power : 740HP");
    }
}

class Porsche extends Car{
    void start(){
        System.out.println("Porsche is on FIREE");
    }

    void end(){
        System.out.println("Engines OFF");
    }

    void engineSpecs(){
        System.out.println("Power : 525HP");
    }
}

class Main{
    public static void main(String[] args) {
        McLaren mclaren = new McLaren();
        Porsche porsche = new Porsche();
        mclaren.start();
        mclaren.engineSpecs();

        porsche.start();
        porsche.engineSpecs();
    }
}