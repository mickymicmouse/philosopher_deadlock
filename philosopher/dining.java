import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.*;

// Use java threads to simulate the Dining Philosophers Problem
// YOUR NAME HERE. - 15146317 seungjun lee  Programming assignment 2 (from ece.gatech.edu) */

class dining
{
    public static void main(String args[])
    {
        System.out.println("Starting the Dining Philosophers Simulation\n");
        miscsubs.InitializeChecking();
        // Your code here...
     
   
        class p implements Runnable {
        	
        	private String person;
        	private int num;
        	public final Semaphore semaphore = new Semaphore(2);
        	public p (String person, int num) {
        		
        		this.person = person;
        		this.num = num;
        	}
        	
	         	public synchronized void run() {
	         		
	        		for(int i = miscsubs.TotalEats; i <=miscsubs.MAX_EATS; i++) {
	        		thinking();
	        		try {
	        			
	        			if (miscsubs.TotalEats == miscsubs.MAX_EATS) {
	        			      System.out.println("Simulation Ends..");       
	        			        miscsubs.LogResults();
	        			        System.exit(0); 
	        				        
	        			}
	        			if (miscsubs.EatCount[num] == 100) {//if one philosopher eating is more than 100times, stop eating -> sleep thread.. 
	        				System.out.println(person+"  "+num+"  philosopher eating finish");
	        				miscsubs.LogResults();
	        				break;
	        				
	        			}
	        			if(miscsubs.EatCount[num]==100 && miscsubs.StarveCount[num]==17) {
	        				miscsubs.StarveCount[num]=0;
	        				
	        			}
	        			if(miscsubs.EatCount[(num+1)%5]==100 && miscsubs.StarveCount[(num+1)%5]==17)	{
	        				miscsubs.StarveCount[(num+1)%5]=0;
	        				
	        			}
	        			if(miscsubs.EatCount[(num+2)%5]==100 && miscsubs.StarveCount[(num+2)%5]==17) {
	        				miscsubs.StarveCount[(num+2)%5]=0;
	        				
	        			}
	        			if(miscsubs.EatCount[(num+3)%5]==100 && miscsubs.StarveCount[(num+3)%5]==17) {
	        				miscsubs.StarveCount[(num+3)%5]=0;
	        				
	        			}
	        			if(miscsubs.EatCount[(num+4)%5]==100 && miscsubs.StarveCount[(num+4)%5]==17) {
	        				miscsubs.StarveCount[(num+4)%5]=0;
	        				
	        			}
	        			 
	        			if (miscsubs.StarveCount[num]==18) {

	        				miscsubs.DoneEating((num+1)%5);
	        				miscsubs.DoneEating((num+4)%5);
	        				eating(num);	
	        			}
	        			if (miscsubs.StarveCount[(num+1)%5]==18) {

	        				miscsubs.DoneEating((num+2)%5);
	        				miscsubs.DoneEating((num+5)%5);
	        				eating((num+1)%5);	
	        			}
	        			if (miscsubs.StarveCount[(num+2)%5]==18) {

	        				miscsubs.DoneEating((num+3)%5);
	        				miscsubs.DoneEating((num+6)%5);
	        				eating((num+2)%5);	
	        			}
	        			if (miscsubs.StarveCount[(num+3)%5]==18) {

	        				miscsubs.DoneEating((num+4)%5);
	        				miscsubs.DoneEating((num+7)%5);
	        				eating((num+3)%5);	
	        			}
	        			if (miscsubs.StarveCount[(num+4)%5]==18) {

	        				miscsubs.DoneEating((num+5)%5);
	        				miscsubs.DoneEating((num+9)%5);
	        				eating((num+4)%5);	
	        			}
	        			
	        			
	        			if (miscsubs.EatingLog[(num+1)%5]==false 
	        					&&miscsubs.EatingLog[(num+4)%5]==false 
	        					&&miscsubs.EatingLog[num]==false) { //right person, left person, and me
	        			
	        			semaphore.acquire();
	        			eating(num);
	        			semaphore.release();
	        			}
	        		
	        			 
	        		} catch (Exception e) {
	        			System.out.println("Ex");
	        			e.printStackTrace();
	        		}
	        		}
	        	}
	         
	         	
         	
         	public void eating (int i) {
				
         		
         		miscsubs.StartEating(i);
        		miscsubs.RandomDelay();
        		miscsubs.DoneEating(i);
        	
        	}
        	
        	public void thinking () {
        		miscsubs.RandomDelay();
        	}
   
        
        }
        
        
   	 
  
      
        
        
        
        
        
        
        
        
        
        
        
        
        
    
        
       
			
		        Thread t1 = new Thread(new p("A", 0));
		        Thread t2 = new Thread(new p("B", 1));
		        Thread t3 = new Thread(new p("C", 2));
		        Thread t4 = new Thread(new p("D", 3));
		        Thread t5 = new Thread(new p("E", 4));
		       
		      
		        
		        
		        t1.start();
		        t2.start();
		        t3.start();
		        t4.start();
		        t5.start();

			
        
        	

			
        // End of your code
			
        System.out.println("Simulation Ends..");       
        miscsubs.LogResults();

    }
};

