import tester.Tester;

//interface of a Candidate list 
interface ICandidate {

  
  //hire a candidate 
  ICandidate hire();

  
  //helper function for hire that takes in the last candidate 
  ICandidate hireHelper(Candidate LastCandidate);

}

// empty case 
class MtLoCandidate implements ICandidate {

  MtLoCandidate() {

  }

  // returns an empty list
  public ICandidate hire() {
    return this;
  }

  // get to the end of list, just take last candidate
  public ICandidate hireHelper(Candidate LastCandidate) {
    return new ConsLoCandidate(LastCandidate, new MtLoCandidate());
  }

}

// non-empty case 
class ConsLoCandidate implements ICandidate {
  Candidate first;
  ICandidate rest;

  ConsLoCandidate(Candidate first, ICandidate rest) {
    this.first = first;
    this.rest = rest;
  }

  // hire somebody that meets the criteria or the last candidate
  public ICandidate hire() {
    return this.hireHelper(this.first);
  }

  //if a candidate meets the criteria hire them, else not, recurse on the list 
  public ICandidate hireHelper(Candidate LastCandidate) {
    if (this.first.isGoodCandidate()) {
      return new ConsLoCandidate(this.first, new MtLoCandidate());
    }
    else {
      return this.rest.hireHelper(this.first); //accumulate the last candidate 
    }

  }

}

//class representing a candidate 
class Candidate {
  int teamPlayer;
  int softSkills;
  int workEthic;
  int technicalSkills;

  Candidate(int teamPlayer, int softSkills, int workEthic, int technicalSkills) {
    this.teamPlayer = teamPlayer;
    this.softSkills = softSkills;
    this.workEthic = workEthic;
    this.technicalSkills = technicalSkills;

  }

  // determines if current candidate is a good fit
  boolean isGoodCandidate() {
    return (this.teamPlayer > 80) && (this.softSkills > 75) && (this.workEthic > 70)
        && (this.technicalSkills > 60);
  }

}

class testCandidate {
  
  //example candidates 
  Candidate jim = new Candidate(50, 10, 10, 10);
  Candidate bob = new Candidate(100, 100, 100, 100);
  Candidate sam = new Candidate(90, 85, 100, 65);
  Candidate riley = new Candidate(5, 5, 5, 5);

  //example lists 
  ICandidate base = new MtLoCandidate();
  ICandidate l1 = new ConsLoCandidate(this.jim, this.base);
  ICandidate l2 = new ConsLoCandidate(this.bob, this.l1);
  ICandidate l3 = new ConsLoCandidate(this.sam, this.l2);
  ICandidate l4 = new ConsLoCandidate(this.riley, this.l3);

  
  //testing isGoodCandidate method 
  boolean testIsGoodCandidate(Tester t) {
    return t.checkExpect(this.jim.isGoodCandidate(), false)
        && t.checkExpect(this.bob.isGoodCandidate(), true)
        && t.checkExpect(this.sam.isGoodCandidate(), true);

  }

  
  //testing hire method 
  boolean testHire(Tester t) {
    return t.checkExpect(this.l1.hire(), new ConsLoCandidate(this.jim, this.base))
        && t.checkExpect(this.l2.hire(), new ConsLoCandidate(this.bob, this.base))
        && t.checkExpect(this.l3.hire(), new ConsLoCandidate(this.sam, this.base))
        && t.checkExpect(this.l4.hire(), new ConsLoCandidate(this.sam, this.base));

  }

  
  //testing hireHelper method 
  boolean testHireHelper(Tester t) {
    return t.checkExpect(this.l1.hireHelper(bob), new ConsLoCandidate(this.jim, this.base))
        && t.checkExpect(this.l2.hireHelper(this.sam), new ConsLoCandidate(this.bob, this.base))
        && t.checkExpect(this.l3.hireHelper(this.bob), new ConsLoCandidate(this.sam, this.base));

  }

}