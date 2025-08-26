import tester.Tester;
import java.awt.Color;
import javalib.worldimages.*;
import javalib.funworld.*;

//world class 
class MyWorld extends World {
  Number num;

  MyWorld(Number num) {
    this.num = num;
  }

  // draws the current scene
  public WorldScene makeScene() {
    WorldScene ws = new WorldScene(600, 400);
    return ws.placeImageXY(this.num.draw(), 300, 200);

  }

  // creates the final scene
  public WorldScene lastScene() {
    WorldScene ws = new WorldScene(600, 400);
    return ws.placeImageXY(new TextImage("We hit 1!", 30, Color.black), 300, 200);
  }

  // given a right press, progress to next value
  public World onKeyEvent(String key) {

    if (key.equals("right")) {
      return new MyWorld(new Number(this.num.nextValue()));
    }
    else {
      return this;
    }
  }

  // display the current scene, or last scene if we hit 1
  public WorldEnd worldEnds() {
    if (this.num.nextValue() == 1) {
      return new WorldEnd(true, this.lastScene());
    }
    else {
      return new WorldEnd(false, this.makeScene());
    }
  }

  // return current world state given a tick
  public World onTick() {
    return this;
  }
}


//number class 
class Number {
  int value;

  Number(int value) {
    this.value = value;
  }

  
  //compute next value 
  int nextValue() {
    if (this.value % 2 == 0) {
      return this.value / 2;
    }
    else {
      return (this.value * 3) + 1;
    }
  }

  
  //convert current value to a string 
  String convertToString() {
    return Integer.toString(this.value);

  }

  //draw the current value as an image 
  public WorldImage draw() {
    return new TextImage(this.convertToString(), 30, Color.black);
  }

}

class testCollatz {
  Number value1 = new Number(1);
  Number value2 = new Number(2);
  MyWorld num = new MyWorld(this.value1);
  MyWorld num2 = new MyWorld(this.value2);

  
  //test nextValue method 
  boolean testNextValue(Tester t) {
    return t.checkExpect(this.value1.nextValue(), 4) && t.checkExpect(this.value2.nextValue(), 1);

  }

  
  //test convertToString method 
  boolean testConvertToString(Tester t) {
    return t.checkExpect(this.value1.convertToString(), "1")
        && t.checkExpect(this.value2.convertToString(), "2");

  }

  
  //test draw method 
  boolean testDraw(Tester t) {
    return t.checkExpect(this.value1.draw(), new TextImage("1", 30, Color.black))
        && t.checkExpect(this.value2.draw(), new TextImage("2", 30, Color.black));

  }

  //test makeScene method 
  boolean testMakeScene(Tester t) {
    return t.checkExpect(this.num.makeScene(),
        new WorldScene(600, 400).placeImageXY(new TextImage("1", 30, Color.black), 300, 200))
        && t.checkExpect(this.num2.makeScene(),
            new WorldScene(600, 400).placeImageXY(new TextImage("2", 30, Color.black), 300, 200));
  }

  
  //test lastScene method 
  boolean testDrawLastScene(Tester t) {
    return t.checkExpect(this.num.lastScene(),
        new WorldScene(600, 400).placeImageXY(new TextImage("We hit 1!", 30, Color.black), 300,
            200))
        && t.checkExpect(this.num2.lastScene(), new WorldScene(600, 400)
            .placeImageXY(new TextImage("We hit 1!", 30, Color.black), 300, 200));

  }

  
  //test onKeyEvent method 
  boolean testOnKeyEvent(Tester t) {
    return t.checkExpect(this.num.onKeyEvent("right"), new MyWorld(new Number(4)))
        && t.checkExpect(this.num.onKeyEvent("left"), new MyWorld(new Number(1)))
        && t.checkExpect(this.num2.onKeyEvent("right"), new MyWorld(new Number(1)));
  }

  //test worldEnds method 
  boolean testWorldEnds(Tester t) {
    return t.checkExpect(this.num2.worldEnds(),
        new WorldEnd(true,
            new WorldScene(600, 400).placeImageXY(new TextImage("We hit 1!", 30, Color.black), 300,
                200)))
        && t.checkExpect(this.num.worldEnds(), new WorldEnd(false,
            new WorldScene(600, 400).placeImageXY(new TextImage("1", 30, Color.black), 300, 200)));
  }
}
