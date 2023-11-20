/*     
 *    
 *  Author     : Dev Dipak Patel & Vamsi Krishna Satyanarayana Vasa
 *  Version    : DEVSJAVA 2.7 
 *  Date       : 11-16-2023 
 */

package Component.BasicProcessor;


import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import GenCol.doubleEnt;
import GenCol.entity;


import model.modeling.content;
import model.modeling.message;

import view.modeling.ViewableAtomic;



public class SkateboardTransducer extends  ViewableAtomic{

 protected doubleEnt job;
 protected double clock,total_ta,observation_time;
 public Double count=0.00;
 double steptime, actualspeed, desiredspeed, matchcount, totalsumtime, sumtime, prev_desiredspeed;
 double[]desiredspeedarr = {0,0,0,0,0};
 double[]actualspeedarr = {0,0,0,0,0};
 double avgactualspeed=0;
 double avgdesiredspeed=0;
 double avgtime=0;

 public SkateboardTransducer(String  name,double Observation_time){
  super(name);
   addInport("desired_speed_in");
   addInport("actual_speed_in");
   addInport("stop");
   addInport("start");
   
   addOutport("ActualSpeed");
   addOutport("DesiredSpeed");
   addOutport("Avgtime"); 
  
   addTestInput("start", new entity(""));
   addTestInput("stop", new entity(""));

   //Test inputs for actual_speed_in and desired_speed_in
   addTestInput("actual_speed_in",new doubleEnt((double)1));
   addTestInput("actual_speed_in",new doubleEnt((double)2));
   addTestInput("actual_speed_in",new doubleEnt((double)3));
   addTestInput("actual_speed_in",new doubleEnt((double)4));
   addTestInput("actual_speed_in",new doubleEnt((double)5));
   
   addTestInput("desired_speed_in",new doubleEnt((double)1));
   addTestInput("desired_speed_in",new doubleEnt((double)2));
   addTestInput("desired_speed_in",new doubleEnt((double)3));
   addTestInput("desired_speed_in",new doubleEnt((double)4));
   addTestInput("desired_speed_in",new doubleEnt((double)5));
   
  initialize();
  
  setBackgroundColor(Color.green);
 }

 public SkateboardTransducer() {this("SkateboardTransducer", 200);}

 public void initialize() {
		phase = "idle";
		sigma = INFINITY;
		clock = 0;
		total_ta = 0;
		steptime = 1;
		actualspeed=0;
		desiredspeed=0;
		matchcount=0;
		totalsumtime=0;
		sumtime=0;
		prev_desiredspeed = -10000;
		super.initialize();
	}
 
 public void  deltext(double e,message  x){
//	 System.out.println("--------Transduceer elapsed time ="+e);
//	 System.out.println("-------------------------------------");
  clock = clock + e;
  Continue(e);
  
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
	 {
		if (messageOnPort(x, "actual_speed_in", i)) {
			job = (doubleEnt) x.getValOnPort("actual_speed_in", i);
			actualspeed = job.getv();
			holdIn("busy", steptime);
		}
		if (messageOnPort(x, "desired_speed_in", i)) {
		job = (doubleEnt) x.getValOnPort("desired_speed_in", i);
		desiredspeed = job.getv();
		}	
	 }
	}
    }


 public void  deltint(){
  clock = clock + sigma;
  
  int index=(int) ((clock) % 5) ;
  
  if (phaseIs("busy")) {
  desiredspeedarr[index]=desiredspeed;
  actualspeedarr[index]=actualspeed;
  
  if(desiredspeed == actualspeed) {
	  if(prev_desiredspeed != desiredspeed) {
		  totalsumtime+=sumtime;
		  matchcount++;
		  sumtime=0;
		  prev_desiredspeed = desiredspeed;
	  }
	  
  }
  else sumtime +=1;
  
  }
 }

 public  message    out( ){
  message  m = new message();
  
  if( (int) ((clock) % 5) == 0 ) {
	  
	  for(int i=0;i<5;i++) {
		  avgactualspeed += actualspeedarr[i];
		  avgdesiredspeed += desiredspeedarr[i];
	  }
	  
	  avgactualspeed /=5;
	  avgdesiredspeed /=5;
	  if(matchcount != 0)
	  avgtime=totalsumtime/matchcount;
	  
	  
	  
	  
	  
  content  con1 = makeContent("ActualSpeed",new doubleEnt(avgactualspeed));
  content  con2 = makeContent("DesiredSpeed",new doubleEnt(avgdesiredspeed));
  content  con3 = makeContent("Avgtime",new doubleEnt(avgtime));
  m.add(con1);
  m.add(con2);
  m.add(con3);
  avgactualspeed=0;
  avgdesiredspeed=0;
  }
  return m;
 }
 
}

