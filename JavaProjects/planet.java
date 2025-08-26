import tester.Tester;

abstract class CelestialBody { // class representing a celestial body

  String name; // name of the body
  int diameter; // diameter of the body
  long distance; // distance from our sun for planets or distance from orbiting planet for
  // moons (all in kilometers)

  CelestialBody(String name, int diameter, long distance) {
    this.name = name;
    this.diameter = diameter;
    this.distance = distance;
  }

  // effect: mutate the body's diameter with the input
  public void changeDiameter(int diam) {
    this.diameter = this.diameter + diam;

  }

  // effect: mutate the body's distance with the input
  public void changeDistance(int dist) {
    this.distance = this.distance + dist;
  }

}

//class representing a moon 
class Moon extends CelestialBody {

  Moon(String name, int diameter, long distance) {
    super(name, diameter, distance);
  }

}

//interface representing a list of moons 
interface ILoMoon {

  // creates the list of moons as a string with commas in between
  String moonsList();

  // helper for moons list, accumulates the string so far
  String moonsListHelper(String acc);

  // computes the total number of moons in the list
  int totalMoons();

  // finds the largest moon in the list
  Moon largestMoon();

  // helper for finding the largest moon, accumulates the largest moon so far
  // and compares it with the next moon
  Moon largestMoonHelper(Moon acc);

}

//empty list of moons 
class MtLoMoon implements ILoMoon {
  MtLoMoon() {
  }

  // returns an empty string in the case there are no moons
  public String moonsList() {
    return "";
  }

  // return the accumulated list of moons so far
  public String moonsListHelper(String acc) {
    return acc;

  }

  // empty case results in 0 moons
  public int totalMoons() {
    return 0;
  }

  // returns a dummy moon in the case there are no moons
  public Moon largestMoon() {
    return new Moon("No Moon", 0, 0);
  }

  // return the largest accumulated moon
  public Moon largestMoonHelper(Moon acc) {
    return acc;
  }

}

//non-empty list of moons 
class ConsLoMoon implements ILoMoon {
  Moon first;
  ILoMoon rest;

  ConsLoMoon(Moon first, ILoMoon rest) {
    this.first = first;
    this.rest = rest;
  }

  // creates the list of moons as a string with commas in between
  public String moonsList() {
    return this.rest.moonsListHelper(this.first.name);

  }

  // helper for moons list, accumulates the string so far
  public String moonsListHelper(String acc) {
    // return the accumulated list and recurse passing in the next moon's name
    return acc + ", " + this.rest.moonsListHelper(this.first.name);

  }

  // computes the total number of moons in the list
  public int totalMoons() {
    return 1 + this.rest.totalMoons();
  }

  // finds the largest moon in the list
  public Moon largestMoon() {
    return this.rest.largestMoonHelper(first);
  }

  // helper for finding the largest moon, accumulates the largest moon so far
  // and compares it with the next moon
  public Moon largestMoonHelper(Moon acc) {
    if (this.first.diameter > acc.diameter) {
      // if the first of this list has a greater diameter than the
      // accumulator, accumulate it and recurse
      return this.rest.largestMoonHelper(this.first);
    }
    else {
      // if the accumulated value is greater than the first, keep the accumulator and
      // recurse
      return this.rest.largestMoonHelper(acc);
    }

  }

}

//class representing a planet 
class Planet extends CelestialBody {

  ILoMoon moons; // list of moons that orbit planet

  Planet(String name, int diameter, long distance, ILoMoon moons) {
    super(name, diameter, distance);
    this.moons = moons;
  }

  // effect: mutate the list of moons to add the new moon to the list
  public void addMoon(Moon addMoon) {
    this.moons = new ConsLoMoon(addMoon, this.moons);
  }

}

//interface representing a list of planets (a system) 
interface ILoPlanet {

  // computes the total number of planets in the system
  int computeNumberOfPlanetsInSystem();

  // computes the total number of moons in the system
  int computeNumberOfMoonsInSystem();

  // finds the largest moon in the system
  Moon largestMoonInSystem();

  // helper for largestMoonInSystem, accumulates the planet with the biggest moon
  // so far
  Moon largestMoonInSystemHelper(Planet acc);

}

//represents empty list of planets 
class MtLoPlanet implements ILoPlanet {

  MtLoPlanet() {
  }

  // in the empty list, return 0
  public int computeNumberOfPlanetsInSystem() {
    return 0;
  }

  // in the empty list, return 0
  public int computeNumberOfMoonsInSystem() {
    // TODO Auto-generated method stub
    return 0;
  }

  // in the empty list where there is no list of planets, return a dummy moon
  public Moon largestMoonInSystem() {
    return new Moon("No Moon", 0, 0);
  }

  // return the accumulated planet's largest moon
  public Moon largestMoonInSystemHelper(Planet acc) {
    return acc.moons.largestMoon();
  }

}

//representing non-empty list of planets 
class ConsLoPlanet implements ILoPlanet {

  Planet first;
  ILoPlanet rest;

  ConsLoPlanet(Planet first, ILoPlanet rest) {
    this.first = first;
    this.rest = rest;
  }

  // computes the total number of planets in the system
  public int computeNumberOfPlanetsInSystem() {
    return 1 + this.rest.computeNumberOfPlanetsInSystem();
  }

  // computes the total number of moons in the system
  public int computeNumberOfMoonsInSystem() {
    return first.moons.totalMoons() + this.rest.computeNumberOfMoonsInSystem();

  }

  // finds the largest moon in the system
  public Moon largestMoonInSystem() {
    return this.rest.largestMoonInSystemHelper(first);
  }

  // helper function for largestMoonInSystem, accumulates the planet with the
  // largest moon so far
  public Moon largestMoonInSystemHelper(Planet acc) {
    // if the first planet of this list's biggest moon is bigger than accumulator,
    // accumulate the first planet and recurse
    if (this.first.moons.largestMoon().diameter > acc.moons.largestMoon().diameter) {
      return this.rest.largestMoonInSystemHelper(this.first);
    }
    else {
      // if the accumulated value is bigger than the first, keep the accumulator and
      // recurse
      return this.rest.largestMoonInSystemHelper(acc);
    }

  }

}

class testPlanet {
  Planet mercury;
  Planet venus;
  Planet earth;
  Planet mars;
  Planet jupiter;
  Planet saturn;
  Planet uranus;
  Planet neptune;
  ILoMoon moonBase;
  Moon dummyMoon; // dummy moon in situations where an actual moon is not present
  Moon moon; // Earth's moon
  ILoMoon earthMoonList; // Earth moon list
  Moon phobos; // one of 2 of Mars's moons
  ILoMoon phobosList;
  Moon deimos; // the second of Mars's moons
  ILoMoon marsMoonList; // Mars moon list
  Moon ganymede; // Jupiter's largest moon
  ILoMoon ganymedeList;
  Moon callisto; // another Jupiter moon
  ILoMoon callistoList;
  Moon io; // another Jupiter moon
  ILoMoon ioList;
  Moon europa; // another Jupiter moon
  ILoMoon jupiterMoonList; // Jupiter's moon list
  Moon titan; // one of Saturn's moons
  ILoMoon saturnMoonList; // Saturn moon list (just Titan)
  Moon titania; // one of Uranus's moons
  ILoMoon uranusMoonList; // Uranus moon list (just Titania)
  Moon triton; // one of Neptune's moons
  ILoMoon neptuneMoonList; // Neptune moon list (just Triton)
  ILoPlanet planetBase;
  ILoPlanet mercuryList;
  ILoPlanet venusList;
  ILoPlanet earthList;
  ILoPlanet marsList;
  ILoPlanet jupiterList;
  ILoPlanet saturnList;
  ILoPlanet uranusList;
  ILoPlanet solarSystem;

  // initialize the data for testing properties
  void initData() {
    this.moonBase = new MtLoMoon();
    this.planetBase = new MtLoPlanet();
    this.dummyMoon = new Moon("No Moon", 0, 0);
    this.mercury = new Planet("Mercury", 4879, 58000000L, this.moonBase);
    this.venus = new Planet("Venus", 12104, 107850000L, this.moonBase);
    this.moon = new Moon("Moon", 3476, 384400L);
    this.earthMoonList = new ConsLoMoon(this.moon, this.moonBase);
    this.earth = new Planet("Earth", 12756, 150000000L, this.earthMoonList);
    this.phobos = new Moon("Phobos", 27, 6000L);
    this.phobosList = new ConsLoMoon(this.phobos, this.moonBase);
    this.deimos = new Moon("Deimos", 15, 23460L);
    this.marsMoonList = new ConsLoMoon(this.deimos, this.phobosList);
    this.mars = new Planet("Mars", 6779, 238790000L, this.marsMoonList);
    this.ganymede = new Moon("Ganymede", 5268, 1070000L);
    this.ganymedeList = new ConsLoMoon(this.ganymede, this.moonBase);
    this.callisto = new Moon("Callisto", 4821, 1883000L);
    this.callistoList = new ConsLoMoon(this.callisto, this.ganymedeList);
    this.io = new Moon("Io", 3640, 421700L);
    this.ioList = new ConsLoMoon(this.io, this.callistoList);
    this.europa = new Moon("Europa", 3130, 671000L);
    this.jupiterMoonList = new ConsLoMoon(this.europa, this.ioList);
    this.jupiter = new Planet("Jupiter", 139822, 778000000L, this.jupiterMoonList);
    this.titan = new Moon("Titan", 5150, 1221850L);
    this.saturnMoonList = new ConsLoMoon(this.titan, this.moonBase);
    this.saturn = new Planet("Saturn", 120500, 1400000000L, this.saturnMoonList);
    this.titania = new Moon("Titania", 1578, 436000);
    this.uranusMoonList = new ConsLoMoon(this.titania, this.moonBase);
    this.uranus = new Planet("Uranus", 51118, 2870000000L, this.uranusMoonList);
    this.triton = new Moon("Triton", 2700, 354800L);
    this.neptuneMoonList = new ConsLoMoon(this.triton, this.moonBase);
    this.neptune = new Planet("Neptune", 49528, 4500000000L, this.neptuneMoonList);
    this.mercuryList = new ConsLoPlanet(this.mercury, this.planetBase);
    this.venusList = new ConsLoPlanet(this.venus, this.mercuryList);
    this.earthList = new ConsLoPlanet(this.earth, this.venusList);
    this.marsList = new ConsLoPlanet(this.mars, this.earthList);
    this.jupiterList = new ConsLoPlanet(this.jupiter, this.marsList);
    this.saturnList = new ConsLoPlanet(this.saturn, this.jupiterList);
    this.uranusList = new ConsLoPlanet(this.uranus, this.saturnList);
    this.solarSystem = new ConsLoPlanet(this.neptune, this.uranusList);

  }

  // test changeDiameter method
  void testChangeDiameter(Tester t) {
    this.initData();
    this.mercury.changeDiameter(1000);
    this.moon.changeDiameter(500);
    this.earth.changeDiameter(0);
    this.phobos.changeDiameter(421);
    this.triton.changeDiameter(-20);
    t.checkExpect(this.mercury, new Planet("Mercury", 5879, 58000000L, this.moonBase));
    t.checkExpect(this.moon, new Moon("Moon", 3976, 384400L));
    t.checkExpect(this.earth, new Planet("Earth", 12756, 150000000L, this.earthMoonList));
    t.checkExpect(this.phobos, new Moon("Phobos", 448, 6000L));
    t.checkExpect(this.triton, new Moon("Triton", 2680, 354800L));

  }

  // test changeDistance method
  void testChangeDistance(Tester t) {
    this.initData();
    this.deimos.changeDistance(431);
    this.mars.changeDistance(230);
    this.europa.changeDistance(-115);
    this.neptune.changeDistance(-5000000);
    t.checkExpect(this.deimos, new Moon("Deimos", 15, 23891L));
    t.checkExpect(this.mars, new Planet("Mars", 6779, 238790230L, this.marsMoonList));
    t.checkExpect(this.europa, new Moon("Europa", 3130, 670885));
    t.checkExpect(this.neptune, new Planet("Neptune", 49528, 4495000000L, this.neptuneMoonList));

  }

  // test moonsList method
  void testMoonsList(Tester t) {
    this.initData();
    t.checkExpect(this.moonBase.moonsList(), "");
    t.checkExpect(this.earthMoonList.moonsList(), "Moon");
    t.checkExpect(this.marsMoonList.moonsList(), "Deimos, Phobos");
    t.checkExpect(this.jupiterMoonList.moonsList(), "Europa, Io, Callisto, Ganymede");
    t.checkExpect(this.saturnMoonList.moonsList(), "Titan");
    t.checkExpect(this.uranusMoonList.moonsList(), "Titania");
    t.checkExpect(this.neptuneMoonList.moonsList(), "Triton");
  }

  // test moonsListHelper method
  void testMoonsListHelper(Tester t) {
    this.initData();
    t.checkExpect(this.moonBase.moonsListHelper("bob"), "bob");
    t.checkExpect(this.earthMoonList.moonsListHelper("Moon1"), "Moon1, Moon");
  }

  // test totalMoons method
  void testTotalMoons(Tester t) {
    this.initData();
    t.checkExpect(this.moonBase.totalMoons(), 0);
    t.checkExpect(this.earthMoonList.totalMoons(), 1);
    t.checkExpect(this.marsMoonList.totalMoons(), 2);
    t.checkExpect(this.jupiterMoonList.totalMoons(), 4);
    t.checkExpect(this.saturnMoonList.totalMoons(), 1);
    t.checkExpect(this.uranusMoonList.totalMoons(), 1);
    t.checkExpect(this.neptuneMoonList.totalMoons(), 1);
  }

  // test largestMoon method
  void testlargestMoon(Tester t) {
    this.initData();
    t.checkExpect(this.moonBase.largestMoon(), new Moon("No Moon", 0, 0));
    t.checkExpect(this.earthMoonList.largestMoon(), this.moon);
    t.checkExpect(this.marsMoonList.largestMoon(), this.phobos);
    t.checkExpect(this.jupiterMoonList.largestMoon(), this.ganymede);
    t.checkExpect(this.saturnMoonList.largestMoon(), this.titan);
    t.checkExpect(this.uranusMoonList.largestMoon(), this.titania);
    t.checkExpect(this.neptuneMoonList.largestMoon(), this.triton);
  }

  // test largestMoonHelper method
  void testlargestMoonHelper(Tester t) {
    this.initData();
    t.checkExpect(this.moonBase.largestMoonHelper(this.callisto), this.callisto);
    t.checkExpect(this.earthMoonList.largestMoonHelper(this.ganymede), this.ganymede);

  }

  // test addMoon method 
  void testAddMoon(Tester t) {
    this.initData();
    this.earth.addMoon(this.deimos);
    this.neptune.addMoon(this.moon);
    this.venus.addMoon(this.europa);
    t.checkExpect(this.earth,
        new Planet("Earth", 12756, 150000000L, new ConsLoMoon(this.deimos, this.earthMoonList)));
    t.checkExpect(this.neptune,
        new Planet("Neptune", 49528, 4500000000L, new ConsLoMoon(this.moon, this.neptuneMoonList)));
    t.checkExpect(this.venus,
        new Planet("Venus", 12104, 107850000L, new ConsLoMoon(this.europa, this.moonBase)));
  }

  // test computeNumberOfPlanetsInSystem method
  void testComputeNumberOfPlanetsInSystem(Tester t) {
    this.initData();
    t.checkExpect(this.planetBase.computeNumberOfPlanetsInSystem(), 0);
    t.checkExpect(this.mercuryList.computeNumberOfPlanetsInSystem(), 1);
    t.checkExpect(this.venusList.computeNumberOfPlanetsInSystem(), 2);
    t.checkExpect(this.earthList.computeNumberOfPlanetsInSystem(), 3);
    t.checkExpect(this.marsList.computeNumberOfPlanetsInSystem(), 4);
    t.checkExpect(this.jupiterList.computeNumberOfPlanetsInSystem(), 5);
    t.checkExpect(this.saturnList.computeNumberOfPlanetsInSystem(), 6);
    t.checkExpect(this.uranusList.computeNumberOfPlanetsInSystem(), 7);
    t.checkExpect(this.solarSystem.computeNumberOfPlanetsInSystem(), 8);
  }

  // test computeNumberOfMoonsInSystem method
  void testComputeNumberOfMoonsInSystem(Tester t) {
    this.initData();
    t.checkExpect(this.planetBase.computeNumberOfMoonsInSystem(), 0);
    t.checkExpect(this.mercuryList.computeNumberOfMoonsInSystem(), 0);
    t.checkExpect(this.venusList.computeNumberOfMoonsInSystem(), 0);
    t.checkExpect(this.earthList.computeNumberOfMoonsInSystem(), 1);
    t.checkExpect(this.marsList.computeNumberOfMoonsInSystem(), 3);
    t.checkExpect(this.jupiterList.computeNumberOfMoonsInSystem(), 7);
    t.checkExpect(this.saturnList.computeNumberOfMoonsInSystem(), 8);
    t.checkExpect(this.uranusList.computeNumberOfMoonsInSystem(), 9);
    t.checkExpect(this.solarSystem.computeNumberOfMoonsInSystem(), 10);
  }

  // test largestMoonInSystem method
  void testLargestMoonInSystem(Tester t) {
    this.initData();
    t.checkExpect(this.planetBase.largestMoonInSystem(), this.dummyMoon);
    t.checkExpect(this.mercuryList.largestMoonInSystem(), this.dummyMoon);
    t.checkExpect(this.venusList.largestMoonInSystem(), this.dummyMoon);
    t.checkExpect(this.earthList.largestMoonInSystem(), this.moon);
    t.checkExpect(this.marsList.largestMoonInSystem(), this.moon);
    t.checkExpect(this.jupiterList.largestMoonInSystem(), this.ganymede);
    t.checkExpect(this.saturnList.largestMoonInSystem(), this.ganymede);
    t.checkExpect(this.uranusList.largestMoonInSystem(), this.ganymede);
    t.checkExpect(this.solarSystem.largestMoonInSystem(), this.ganymede);
  }

  // test largestMoonInSystemHelper method
  void testLargestMoonInSystemHelper(Tester t) {
    this.initData();
    t.checkExpect(this.planetBase.largestMoonInSystemHelper(this.earth), this.moon);
    t.checkExpect(this.mercuryList.largestMoonInSystemHelper(this.mars), this.phobos);
    t.checkExpect(this.earthList.largestMoonInSystemHelper(this.saturn), this.titan);
  }

}
