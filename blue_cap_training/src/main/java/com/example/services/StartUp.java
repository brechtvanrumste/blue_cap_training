package com.example.services;

import com.example.models.Car;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Component
public class StartUp
    implements ApplicationListener<ApplicationReadyEvent> {

        @Override
        public void onApplicationEvent(final ApplicationReadyEvent event) {
            try {
                File myObj = new File("/Users/jf38739/training/blue_cap_training/blue_cap_training/src/main/resources/measurements.txt");
                Scanner myReader = new Scanner(myObj);
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
                        Integer start_total_time = start_milisecond +1000*(start_second + 60*(start_minute+ 60*start_hour));
                        Integer end_total_time = end_milisecond +1000*(end_second + 60*(end_minute+ 60*end_hour));
                        new Car(change_time,numberplate,start_hour,start_minute,
                                start_second,start_milisecond,start_total_time, end_hour,end_minute,end_second,end_milisecond, end_total_time);
                        x=0;
                    }
                    x++;
                }
                myReader.close();

        } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            return;
        }
}
