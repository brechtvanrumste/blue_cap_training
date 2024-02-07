package org.example;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        try {
            File myObj = new File("measurements.txt");
            Scanner myReader = new Scanner(myObj);
            ArrayList<Car> cars = new ArrayList<Car>();
            int x =1;
            String numberplate = null;
            int speed;
            int start_hour=0;
            int start_minute=0;
            int start_second=0;
            int start_milisecond=0;
            int end_hour=0;
            int end_minute=0;
            int end_second=0;
            int end_milisecond=0;
            float change_time=0;
            while (myReader.hasNextLine()) {
                String data = myReader.next();
                if(x==1){
                    numberplate =data;
                } else if (x==2) {
                    start_hour = Integer.parseInt(data);
                } else if (x==3) {
                    start_minute = Integer.parseInt(data);
                } else if (x==4) {
                    start_second = Integer.parseInt(data);
                } else if (x==5) {
                    start_milisecond = Integer.parseInt(data);
                } else if (x==6) {
                    end_hour= Integer.parseInt(data);
                } else if (x==7) {
                    end_minute = Integer.parseInt(data);
                } else if (x==8) {
                    end_second = Integer.parseInt(data);
                } else if (x==9) {
                    end_milisecond = Integer.parseInt(data);
                    change_time =(end_hour-start_hour)+ (float) (end_minute - start_minute) /60 +
                            (float) (end_second - start_second) /(60*60)+
                            (float) (end_milisecond - start_milisecond) /(60*60*1000);
                    cars.add(new Car(change_time,Math.round(10/change_time),numberplate,start_hour,start_minute,
                            start_second,start_milisecond, end_hour,end_minute,end_second,end_milisecond));
                    x=0;
                }
                x++;
            }
            myReader.close();
            System.out.println("the amount of vehicles are: " + cars.size());
            System.out.println("the amount of vehicles that pass before 9 o'clock: "+amount_before_9(cars));
            // exercise 4
            Scanner scanner = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Enter timestamp for exercise4: ");
            String hour = scanner.next();
            String minute= scanner.next();
            System.out.println("exervise 4a: "+ amount_vehicles_timestamp(cars,Integer.parseInt(hour),Integer.parseInt(minute)));
            System.out.println("exervise 4b: "+ traffic_intensity(cars,Integer.parseInt(hour),Integer.parseInt(minute)));
            Car speed_demon = max_speed(cars);
            System.out.println("the fastest vehicile has lisence plate: " +speed_demon.numberplate);
            System.out.println("the fastest vehicile average speed is : " +speed_demon.speed+ " km/h");
            System.out.println("the fastest vehicile does : " +overtakes(cars,speed_demon)+ " overtakes");
            System.out.println("the percentage of speeding cars is : " +percentage_speeding(cars)*100+ " %");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    static int amount_before_9(ArrayList<Car> cars){
        int amount=0;
        for (Car car : cars){
            if (car.end_hour <9){
                amount++;
            }
        }
        return amount;
    }
    static int amount_vehicles_timestamp(ArrayList<Car> cars, int hour, int minute){
        int amount=0;
        for (Car car : cars){
            if (car.start_hour == hour && car.start_minute ==minute){
                amount++;
            }
        }
        return amount;
    }
    static float traffic_intensity(ArrayList<Car> cars, int hour, int minute){
        int count=0;
        for (Car car : cars){
            if (car.start_hour < hour && car.end_hour > hour){
                count ++;
            } else if (car.start_hour == hour && car.start_minute < minute && car.end_hour == hour && car.end_minute > minute) {
                count ++;
            }else if (car.start_hour < hour && car.end_hour == hour && car.end_minute > minute) {
                count ++;
            } else if (car.start_hour == hour && car.start_minute < minute && car.end_hour > hour) {
                count ++;
            }else if (car.start_hour == hour && car.start_minute == minute) {
                count ++;
            }else if(car.end_hour == hour && car.end_minute == minute) {
                count ++;
            }
        }
        return (float) count /10;
    }
    static  Car max_speed(ArrayList<Car> cars){
        int speed=0;
        Car speedy_car = null;
        for (Car car : cars){
            if (car.speed >speed){
                speed= car.speed;
                speedy_car=car;
            }
        }
        return speedy_car;
    }
    static  int overtakes(ArrayList<Car> cars, Car fast_car){
        int count=0;
        int start_h =fast_car.start_hour;
        int start_m =fast_car.start_minute;
        int start_s = fast_car.start_second;
        int start_ms = fast_car.start_milisecond;
        int end_h = fast_car.end_hour;
        int end_m = fast_car.end_minute;
        int end_s = fast_car.end_second;
        int end_ms = fast_car.end_milisecond;
        for (Car car : cars){
            //here I regret not using some libary to search if a timestamp is before it or not but now I am commited and not eager to change
            if (car.start_hour < start_h && car.end_hour > end_h){
                count ++;
            } else if (car.start_hour == start_h && car.start_minute < start_m && car.end_hour ==end_h && car.end_minute > end_m) {
                count ++;
            } else if (car.start_hour == start_h && car.start_minute < start_m && car.end_hour ==end_h && car.end_minute == end_m && car.end_second > end_s) {
                count ++;
            }else if (car.start_hour == start_h && car.start_minute < start_m && car.end_hour ==end_h && car.end_minute == end_m && car.end_second == end_s && car.end_milisecond >end_ms) {
                count ++;
            }else if (car.start_hour < start_h && car.end_hour == end_h && car.end_minute > end_m) {
                count ++;
            } else if (car.start_hour < start_h && car.end_hour == end_h && car.end_minute == end_m && car.end_second > end_s) {
                count ++;
            }else if (car.start_hour < start_h && car.end_hour == end_h && car.end_minute == end_m && car.end_second == end_s && car.end_milisecond >end_ms) {
                count ++;
            }else if (car.start_hour == start_h && car.start_minute < start_m && car.end_hour > end_h) {
                count ++;
            }else if (car.start_hour == start_h && car.start_minute == start_m && car.start_second < start_s && car.end_hour > end_h) {
                count ++;
            }else if (car.start_hour == start_h && car.start_minute == start_m && car.start_second < start_s && car.end_hour ==end_h && car.end_minute > end_m) {
                count ++;
            }else if (car.start_hour == start_h && car.start_minute == start_m && car.start_second < start_s && car.end_hour == end_h && car.end_second > end_s) {
                count ++;
            }else if (car.start_hour == start_h && car.start_minute == start_m && car.start_second == start_s && car.start_milisecond < start_ms && car.end_hour > end_h) {
                count ++;
            } else if (car.start_hour == start_h && car.start_minute == start_m && car.start_second == start_s && car.start_milisecond < start_ms && car.end_hour ==end_h && car.end_minute > end_m) {
                count ++;
            } else if (car.start_hour == start_h && car.start_minute == start_m && car.start_second == start_s && car.start_milisecond < start_ms && car.end_hour == end_h && car.end_second > end_s) {
                count ++;
            }else if (car.start_hour == start_h && car.start_minute == start_m && car.start_second == start_s && car.start_milisecond < start_ms && car.end_hour == end_h && car.end_second == end_s && car.end_milisecond >end_ms) {
                count ++;
            }
        }
        return count;
    }
    static float percentage_speeding(ArrayList<Car> cars){
        int count =0;
        Car speedy_car = null;
        for (Car car : cars){
            if (car.speed >90){
                count ++;
            }
        }
        return (float) count /(cars.size());
    }



}
class Car{
    //I should also set before all these parameteres private and make getters for them but also to lazy to change
    float time;
    int speed;
    String numberplate;
    int start_hour;
    int start_minute;
    int start_second;
    int start_milisecond;
    int end_hour;
    int end_minute;
    int end_second;
    int end_milisecond;

    public Car(float time, int speed, String numberplate, int start_hour, int start_minute, int start_second,
               int start_milisecond, int end_hour, int end_minute, int end_second, int end_milisecond) {
        this.time = time;
        this.speed = speed;
        this.numberplate = numberplate;
        this.start_hour = start_hour;
        this.start_minute = start_minute;
        this.start_second = start_second;
        this.start_milisecond = start_milisecond;
        this.end_hour = end_hour;
        this.end_minute = end_minute;
        this.end_second = end_second;
        this.end_milisecond = end_milisecond;
    }

}