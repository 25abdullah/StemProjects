import tester.Tester;
import java.awt.Color;
import javalib.worldimages.*;
import javalib.funworld.*;




//world class 
class MyWorld extends World {
Time time;

MyWorld(Time time) {
  this.time = time;
}



//create the scene 
public WorldScene makeScene() {
  WorldScene ws = new WorldScene(600, 400);
  return ws.placeImageXY(this.time.draw(), 300, 200);

}

//given a tick, progress to the next minute 
public World onTick() {
  return new MyWorld (this.time.tickTock());
  
}
  
  
  
}

class Time {
  int hours; // ranges from 0 to 23
  int minutes; // ranges from 0 to 59

  Time(int hours, int minutes) {
    this.hours = hours;
    this.minutes = minutes;
  }

  
  //progress to the next minute 
  public Time tickTock() {
    if (this.minutes < 59) {
      return new Time(this.hours, this.minutes + 1);
    }
    else if ((this.hours == 23) && (this.minutes == 59)) {
      return new Time(0, 0);
    }
    else {
      return new Time(this.hours + 1, 0);
    }

  }

  
  //convert the time into a string 
  public String timeToText() {
    if ((this.hours == 0) && (this.minutes < 10)) {
      return new String("12 : " + "0" + Integer.toString(this.minutes) + " AM");
    }
    else if ((this.hours == 0) && (this.minutes >= 10)) {
      return new String("12 : " +  Integer.toString(this.minutes) + " AM");

    }
    else if ((this.hours < 12) && (this.minutes < 10)) {
      return new String (Integer.toString(this.hours % 12)) + " : " + "0" + 
    Integer.toString(this.minutes) + " AM";
    }
    else if ((this.hours < 12) && (this.minutes >= 10)) {
      return new String (Integer.toString(this.hours % 12)) + " : " + 
          Integer.toString(this.minutes) + " AM";
    }
    else if ((this.hours == 12) && (this.minutes < 10)) {
      return new String (Integer.toString(this.hours )) + " : " + "0" +
          Integer.toString(this.minutes) + " PM";
      
    }
    
    else if ((this.hours == 12) && (this.minutes >= 10)) {
      return new String (Integer.toString(this.hours )) + " : "  +
          Integer.toString(this.minutes) + " PM";
      
    }
    
    else if ((this.hours > 12) && (this.minutes < 10)) {
      return new String (Integer.toString(this.hours % 12)) + " : " + "0" + 
          Integer.toString(this.minutes) + " PM";
      
    }
    else {
      return new String (Integer.toString(this.hours % 12)) + " : " + 
          Integer.toString(this.minutes) + " PM";
      
    }
      
    }
  

  
  //draw the current time 
  public WorldImage draw() {
    return new TextImage(this.timeToText(), 30, Color.black);
  }

      
    }
    


//testTime class 
class testTime {

  Time elevenFiftyNine = new Time(23, 59);
  Time fiveFive = new Time(5, 05);
  Time fiveFiftyNine = new Time(5, 59);
  Time MidNight = new Time(0, 00);
  MyWorld start = new MyWorld(this.elevenFiftyNine);
  
  
  //start the world 
  boolean testBigBang(Tester t) {
   return this.start.bigBang(600, 400, 0.01);
  }

  
  //test tickTock method 
  boolean testTickTock(Tester t) {
    return t.checkExpect(this.elevenFiftyNine.tickTock(), this.MidNight)
        && t.checkExpect(this.fiveFive.tickTock(), new Time(5, 06))
        && t.checkExpect(this.fiveFiftyNine.tickTock(), new Time(6, 00));

  }
  
  
  //test timeToText method 
  boolean testTimeToText(Tester t) {
    return t.checkExpect(this.elevenFiftyNine.timeToText(), "11 : 59 PM")
        && t.checkExpect(this.MidNight.timeToText(), "12 : 00 AM")
        && t.checkExpect(this.fiveFiftyNine.timeToText(), "5 : 59 AM")
        && t.checkExpect(this.fiveFive.timeToText(), "5 : 05 AM");
    }
  
  
  //test draw method 
  boolean testDraw(Tester t) {
    return t.checkExpect(this.elevenFiftyNine.draw(), new TextImage( "11 : 59 PM", 30, Color.black))
        && t.checkExpect(this.MidNight.draw(), new TextImage( "12 : 00 AM", 30, Color.black))
        && t.checkExpect(this.fiveFiftyNine.draw(), new TextImage( "5 : 59 AM", 30, Color.black))
        && t.checkExpect(this.fiveFive.draw(), new TextImage( "5 : 05 AM", 30, Color.black));
  }
  
  
  //test makeScene method 
  boolean testMakeScene(Tester t) {
    return t.checkExpect(this.start.makeScene(), new WorldScene(600, 400).
        placeImageXY(new TextImage( "11 : 59 PM", 30, Color.black), 300, 200));
  }
  
  
  //test onTick method 
  boolean testOnTick(Tester t) {
    return t.checkExpect(this.start.onTick(), new MyWorld(this.MidNight));
  }

}
