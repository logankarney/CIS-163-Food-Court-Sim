package backEnd;

import backEnd.Person.TypeOfPerson;

public class Cashier extends Eatery{
	
	private int totalTime = 0;
	private int rTotTime = 0;
	private int snTotTime = 0;
	private int ltTotTime = 0;
	
	private int rCount = 0;
	private int snCount = 0;
	private int ltCount = 0;
	
	public void add(Person p) {
		Q.enQ(p);
		if (Q.size() > maxQlength)
			maxQlength = Q.size();
		p.setDestination(null);
	}
	
	public void event(int tick) throws EmptyQException{
		//System.out.println("Eatery Q: " + Q.size());
		if (tick >= timeOfNextEvent) {
			if (person != null) { 			// Notice the delay that takes place here
				if(person.getDestination() != null) {
					person.getDestination().add(person);
				}    // take this person to the next station. 
				totalTime += tick - person.getTickTime();
				if(person.type == TypeOfPerson.REGULAR)
					rTotTime += tick - person.getTickTime();
				else if(person.type == TypeOfPerson.LIMITED_TIME) 
					ltTotTime += tick - person.getTickTime();
				else if(person.type == TypeOfPerson.SPECIAL_NEEDS)
					snTotTime += tick - person.getTickTime();
				person = null;				// I have send the person on. 
				isServicing = false;
			}
			
			if (Q.size() >= 1) {
				person = Q.deQ();		// do not send this person as of yet, make them wait.
				isServicing = true;
				timeOfNextEvent = tick + (int) (person.getCashierTime() + 1);
				completed++;
				if(person.type == TypeOfPerson.REGULAR)
					rCount++;
				else if(person.type == TypeOfPerson.LIMITED_TIME) 
					ltCount++;
				else if(person.type == TypeOfPerson.SPECIAL_NEEDS)
					snCount++;
			}	
		}
			//System.out.println("Cashier Completed: " + completed);
	}
	
	public int getTotTime() {
		return totalTime;
	}
	
	public int getRCount() {
		return rCount;
	}
	
	public int getSNCount() {
		return snCount;
	}
	
	public int getLTCount() {
		return ltCount;
	}
	
	public int getRTime() {
		return rTotTime;
	}
	
	public int getSNTime() {
		return snTotTime;
	}
	
	public int getLTTime() {
		return ltTotTime;
	}
}
