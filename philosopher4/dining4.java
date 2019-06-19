import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.*;

// Use java threads to simulate the Dining Philosophers Problem
// YOUR NAME HERE. - 15146317 seungjun lee  Programming assignment 2 (from ece.gatech.edu) */

class dining4
{
	
    public static void main(String args[])
    {
        System.out.println("Starting the Dining Philosophers Simulation\n");
        miscsubs.InitializeChecking();
        // Your code here...
        
      
        class p implements Runnable {
        	
        	State[] state = new State[5];
        	Condition[] self = new Condition[5];
        	
        	private String person;
        	private int num;
        	public final Semaphore semaphore = new Semaphore(2);
        	private State hungry;
			private State eating;
			private State thinking;
			
			
        	
        	public p (String person, int num) {
        		
        		this.person = person;
        		this.num = num;
        	
        	
        	}
        	
	         	public void run() {
	         		
	        		while (true) {
	        		thinking();
	        		try {
	        			if (miscsubs.TotalEats == miscsubs.MAX_EATS) {
	        			      System.out.println("Simulation Ends..");       
	        			        miscsubs.LogResults();
	        			        System.exit(0); 
	        				        
	        			}
	        			
	        		
	        			initial();
			        	semaphore.acquire();
	        			take(num);
	        			Chop chop = chops.get(num);
	            		Chop chop1 = chops.get((num+1)%5);
	            		chop.use();
	            		chop1.use();
	        			miscsubs.StartEating(num);
	        			put(num);
	        			semaphore.release();
	        			
	        			 
	        		} catch (InterruptedException e) {
	        			System.out.println("Ex");
	        			e.printStackTrace();
	        		}
	        		}
	        	}
	         
	         	
         	
   
        	
        	public void thinking () {
        		miscsubs.RandomDelay();
        	}
        
        	public synchronized void initial() {
        		for (int i= 0; i<5; i++) {
        			state[i]=thinking;
        		}
        	}
        	public synchronized void take(int i) throws InterruptedException {
        		state[i]=hungry;
        		test(i);
        		if (state[i]!= eating) {
        			
						self[i].wait();
						
        		} 
        		
        		
        		
        	}	
        	public synchronized void put(int i) {
        		state[i]=thinking;
        		Chop chop = chops.get(i);
        		Chop chop1 = chops.get((i+1)%5);
        		chop.unuse();
        		chop1.unuse();
        		
        		miscsubs.DoneEating(i);
        		test((i+4)%5);
        		test((i+1)%5);
        		thinking();
        		
        	}
        	public synchronized void test (int i) {
        		if (state[(i+4)%5]!=eating
    					&&state[i]==hungry 
    					&&state[(i+1)%5]!=eating) {
        			
        			state[i]=eating;
        			self[i].notify();
        		}
        	}
        	

        	
        	public final List<Chop> chops = new ArrayList<>();
      	  
       	 	{
       		chops.add(new Chop(0));
       		chops.add(new Chop(1));
       		chops.add(new Chop(2));
       		chops.add(new Chop(3));
       		chops.add(new Chop(4));
       	
       	 	}	 	
       	 	
       	 	class Chop {
            	Lock lock = new ReentrantLock();
            	private int number;
            	public Chop (int number) {
            		this.number = number;
            	}
            	public void use() {
            		
            		lock.lock();
            		
            	}
            	public void unuse() {

            		lock.unlock(); 
            	}
            
                 
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


