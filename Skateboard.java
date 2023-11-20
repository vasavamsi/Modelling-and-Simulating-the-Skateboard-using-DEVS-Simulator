/*     
 *    
 *  Author     : Dev Dipak Patel & Vamsi Krishna Satyanarayana Vasa
 *  Version    : DEVSJAVA 2.7 
 *  Date       : 11-16-2023
 */
package Component.BasicProcessor;

import java.awt.Color;

import GenCol.doubleEnt;
import GenCol.entity;
import model.modeling.message;
import view.modeling.ViewableAtomic;

public class Skateboard extends ViewableAtomic {// ViewableAtomic is used instead
	// of atomic due to its
	// graphics capability
	protected doubleEnt job;
	protected double processing_time;
	
	double actualspeed;
	double desiredspeed;
	double steptime;
	
	public Skateboard() {
		this("skateboard", 10);
	}

	public Skateboard(String name, double Processing_time) {
		super(name);
		addInport("in");
		addInport("stop");
		addInport("start");
		addOutport("out");
	 
		processing_time = Processing_time;
		addTestInput("start", new entity(""));
		addTestInput("stop", new entity(""));
		addTestInput("in",new doubleEnt((double)1));
		addTestInput("in",new doubleEnt((double)2));
		addTestInput("in",new doubleEnt((double)3));
		addTestInput("in",new doubleEnt((double)4));
		addTestInput("in",new doubleEnt((double)5));
		
		
		setBackgroundColor(Color.cyan);
	}

	public void initialize() {
		phase = "idle";
		sigma = INFINITY;
		
		steptime = 1;
		actualspeed=0;
		desiredspeed=0;
		//job = new entity("job");
		super.initialize();
	}

	public void deltext(double e, message x) {
		Continue(e);

//		System.out.println("The elapsed time of the processor is" + e);
//		System.out.println("*****************************************");
//		System.out.println("external-Phase before: "+phase);
		
		if (phaseIs("idle"))
			{for (int i = 0; i < x.getLength(); i++)
				if (messageOnPort(x, "start", i)) {
					
					holdIn("busy", steptime);
					actualspeed=0;

				}
			}
		
		if (phaseIs("busy"))
		{for (int i = 0; i < x.getLength(); i++)
			if (messageOnPort(x, "stop", i)) {
				
				actualspeed=0;
				desiredspeed = 0;
				holdIn("idle", INFINITY);
			}
		}
		
		if (phaseIs("busy"))
		{for (int i = 0; i < x.getLength(); i++)
			if (messageOnPort(x, "in", i)) {
				job = (doubleEnt) x.getValOnPort("in", i);
				desiredspeed = job.getv();
				if (desiredspeed > 161){
					desiredspeed = 161;  // As in the question it was mentioned that maximum allowed speed is 6 miles per hour which is approximately equal to 161 meters per minute
				}
				holdIn("busy", steptime);

			}
		
		}
		
	}

	public void deltint() {

		if (phaseIs("busy")) {
			
				if(actualspeed == desiredspeed)
				{
					holdIn("busy", steptime);
				}
				else if(actualspeed < desiredspeed)
				{
					actualspeed+=1;
					holdIn("busy", steptime);
				}
				else if(actualspeed > desiredspeed)
				{	
					actualspeed-=1;
					holdIn("busy", steptime);
				}
			
			}
	}
	
	public void deltcon(double e, message x) {
		deltint();
		deltext(0, x);
	}

	public message out() {
		message m = new message();
		if (phaseIs("busy")) {
			m.add(makeContent("out", new doubleEnt(actualspeed)));
		}
		return m;
	}

}
