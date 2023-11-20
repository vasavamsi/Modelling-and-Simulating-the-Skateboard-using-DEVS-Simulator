/*     
 *    
 *  Author     : Dev Dipak Patel & Vamsi Krishna Satyanarayana Vasa
 *  Version    : DEVSJAVA 2.7 
 *  Date       : 11-16-2023 
 */
package Component.BasicProcessor;

import java.awt.Color;
import java.lang.*;

import GenCol.*;

import model.modeling.*;
import model.simulation.*;

import view.modeling.ViewableAtomic;
import view.simView.*;

public class SkateboardGenerator extends ViewableAtomic {

    protected double step_time;
    double[] desired_speed_arr = {4,4,4,4,2,2,3,5,1};
    protected int count, index;
    static int c = 0;

    public SkateboardGenerator() {
        this("SkateboardGenerator", 1);
    }

    public SkateboardGenerator(String name, double step_time) {
        super(name);
//        addInport("in");
        addOutport("desired_speed_out");
        addInport("stop");
        addInport("start");
        step_time = 1;

        addTestInput("start", new entity(""));
        addTestInput("stop", new entity(""));
        
        setBackgroundColor(Color.orange);
    }

    public void initialize() {
        holdIn("idle", INFINITY);
        sigma = 0;
        count = 0;
        super.initialize();
    }

    public void deltext(double e, message x) {
        Continue(e);
        if (phaseIs("idle")) {
            for (int i = 0; i < x.getLength(); i++)
                if (messageOnPort(x, "start", i)) {
                    holdIn("busy", step_time);
                }
        }
        if (phaseIs("busy"))
            for (int i = 0; i < x.getLength(); i++)
                if (messageOnPort(x, "stop", i))
                	holdIn("idle", INFINITY);
    }

    public void deltint() {
        if (phaseIs("busy")) {
            count = count + 1;
            holdIn("busy", 1);
        } else
        	passivateIn("idle");
    }

    public message out() {
        message m = new message();
       // content con = makeContent("out", new entity("job" + count + 5));
        index = count%desired_speed_arr.length;
        content con = makeContent("desired_speed_out", new doubleEnt(desired_speed_arr[index]));
        m.add(con);
        return m;
    }}

