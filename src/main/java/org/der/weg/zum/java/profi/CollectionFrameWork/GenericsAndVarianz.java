package org.der.weg.zum.java.profi.CollectionFrameWork;

import java.util.*;

public class GenericsAndVarianz {

    private static class BaseFigure {
        private final String type = "Base";
        public String getType(){return type;}

        public void printDetails(){
            System.out.println(type);
        }
    }

    private static class Circle extends BaseFigure {
        private final String type = "Circle";
        @Override
        public String getType(){return type;}
        public void printDetails(){
            System.out.println(type);
        }
    }

    private static class Rectangle extends BaseFigure {
        private final String type = "Rectangle";
        @Override
        public String getType(){return type;}
        public void printDetails(){
            System.out.println(type);
        }
    }



    public static void main(String[] args) {
        final BaseFigure[] figures = new Circle[10];
        final Set<BaseFigure> set = new HashSet<BaseFigure>();
        //     |   ^                      |          ^
        //     |   |___ Typparameter invariant_______|
        //     |_____Typen Kovariant______|

        //final Set<BaseFigure> set = new HashSet<Circle>(); Kompilierfehler
        //          ^                               ^
        //          |____Typparameter kovarianz_____|

        Object[] objects = new String[]{"Test","Test2"};
        objects[0] = "Some string content";
        objects = new BaseFigure[10];

        final Circle[] circles = {new Circle(),new Circle()};
        final Rectangle[] rectangles = {new Rectangle(),new Rectangle()};
        final BaseFigure[] baseFigures = {new Circle(),new BaseFigure()};

        final List<BaseFigure> list = Arrays.asList(baseFigures);
        printInfo(list);

        final List<BaseFigure> circleList = Arrays.asList(circles);
        printInfo(circleList);

        printInfo(Arrays.asList(rectangles));

        printInfo(circles);
        printInfo(rectangles);
        printInfo(baseFigures);

    }
    public static void printInfo(final BaseFigure baseFigure){
        baseFigure.printDetails();
    }

    public static void printInfo(final BaseFigure[] baseFigures){
       for(BaseFigure baseFigure : baseFigures){
           baseFigure.printDetails();
       }
    }

    public static void printInfo(final List<BaseFigure> baseFigures){
       for(BaseFigure baseFigure : baseFigures){
           baseFigure.printDetails();
       }
    }

    public static <T extends BaseFigure> void copy(List<T> from, List<? super T> to){
        for (final T figure:from) {
            to.add(figure);
        }
    }
}
