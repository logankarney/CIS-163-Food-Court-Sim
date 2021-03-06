/**
 * 
 */
package backEnd;

/**
 * @author Roger Ferguson
 *
 */
public class Sim {
	
	public static void main (String[] args) {
			
			Clock clk = new Clock();
			Eatery booth = new Eatery();

			// 		int numOfTicksNextPerson = 20 
			//      int averageBoothTime = 20
			
			//PersonProducer produce = new PersonProducer(booth, 20, 18, 18, new Eatery());	
			//clk.add(produce);
			clk.add(booth);
			
			try {
				clk.run(10000);
			} catch (EmptyQException e) {
				e.printStackTrace();
			}
			
			System.out.println("Through put is: " + booth.getThroughPut() + " people.");
			System.out.println("People that are still in the Q:" + booth.getLeft() + " people.");
			System.out.println ("Max Q length:" + booth.getMaxQlength() + " people.");
		}
	}
