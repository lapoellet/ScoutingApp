package org.deltaroboticsftc.scoutingapp;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by Luke Poellet on 3/3/2017.
 */

public class Counter extends AppCompatActivity
{

    private String counterName;
    private Context context;
    private int value = 0;

    private LinearLayout.LayoutParams params;
    private LinearLayout.LayoutParams buttonParams;
    private LinearLayout layoutCounter;
    private TextView textViewTitle;
    private TextView textViewValue;
    private LinearLayout layoutButtonBox;
    private Button buttonRemoveModifier;
    private Button buttonAddModifier;

    //Resources
        private String title = "No Counter Title Found";

        private int startValue = 0;
        private int modifier = 1;

        private boolean hasMax = false;
        private int max = 99;

        private boolean hasMin = false;
        private int min = -99;

    /*
        Counter is called only once when a new Counter is created.
        Inputs: namePass, contextPass
        Outputs: void
        Author: Luke Poellet, March 3, 2017
     */
    public Counter(String namePass, Context contextPass)
    {
        counterName = namePass;
        context = contextPass;
        this.getCounterResources();
        this.setValue(startValue);
        this.buildCounter();
    }

    /*
        getCounterResources is called only once.  It pulls all of the Counters information from the Resources file.
        Inputs: void
        Outputs: void
        Author: Luke Poellet, March 3, 2017
     */
    private void getCounterResources()
    {
        int ID;

        ID = context.getResources().getIdentifier(("app" + counterName + "Title"), "string", context.getPackageName());
        if(ID != 0)
        {
            title = context.getResources().getString(ID);
        }

        ID = context.getResources().getIdentifier(("app" + counterName + "StartValue"), "integer", context.getPackageName());
        if(ID != 0)
        {
            startValue = context.getResources().getInteger(ID);
        }

        ID = context.getResources().getIdentifier(("app" + counterName + "Modifier"), "integer", context.getPackageName());
        if(ID != 0)
        {
            modifier = context.getResources().getInteger(ID);
        }

        ID = context.getResources().getIdentifier(("app" + counterName + "HasMax"), "bool", context.getPackageName());
        if(ID != 0)
        {
            hasMax = context.getResources().getBoolean(ID);

            ID = context.getResources().getIdentifier(("app" + counterName + "Max"), "integer", context.getPackageName());
            if(ID != 0)
            {
                max = context.getResources().getInteger(ID);
            }
        }

        ID = context.getResources().getIdentifier(("app" + counterName + "HasMin"), "bool", context.getPackageName());
        if(ID != 0)
        {
            hasMin = context.getResources().getBoolean(ID);

            ID = context.getResources().getIdentifier(("app" + counterName + "Min"), "integer", context.getPackageName());
            if(ID != 0)
            {
                min = context.getResources().getInteger(ID);
            }
        }
    }

    /*
        buildCounter creates the Counter LinearLayout View.
        Inputs: void
        Outputs: void
        Author: Luke Poellet, March 3, 2017
     */
    private void buildCounter()
    {
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        buttonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        buttonParams.weight = 1;

        layoutCounter = new LinearLayout(context);
        layoutCounter.setOrientation(LinearLayout.VERTICAL);
        layoutCounter.setLayoutParams(params);
        layoutCounter.setBackgroundColor(Color.CYAN);

        textViewTitle = new TextView(context);
        textViewTitle.setLayoutParams(params);
        textViewTitle.setGravity(Gravity.CENTER);
        textViewTitle.setLines(1);
        textViewTitle.setText(title);
        textViewTitle.setTextColor(Color.BLACK);
        textViewTitle.setBackgroundColor(Color.BLUE);

        textViewValue = new TextView(context);
        textViewValue.setLayoutParams(params);
        textViewValue.setGravity(Gravity.CENTER);
        textViewValue.setLines(1);
        textViewValue.setText(Integer.toString(value));
        textViewValue.setTextColor(Color.BLACK);

        layoutButtonBox = new LinearLayout(context);
        layoutButtonBox.setLayoutParams(params);
        layoutButtonBox.setOrientation(LinearLayout.HORIZONTAL);
        layoutButtonBox.setWeightSum(2);
        layoutButtonBox.setBackgroundColor(Color.RED);

        buttonRemoveModifier = new Button(context);
        buttonRemoveModifier.setLayoutParams(buttonParams);
        buttonRemoveModifier.setText("-" + modifier);

        buttonAddModifier = new Button(context);
        buttonAddModifier.setLayoutParams(buttonParams);
        buttonAddModifier.setText("+" + modifier);

        layoutButtonBox.addView(buttonRemoveModifier);
        layoutButtonBox.addView(buttonAddModifier);

        layoutCounter.addView(textViewTitle);
        layoutCounter.addView(textViewValue);
        layoutCounter.addView(layoutButtonBox);
    }

    public LinearLayout getCounter()
    {
        return layoutCounter;
    }

    /*
        setValue check to make sure the newValue is within the max and min and then sets the new value.
        Inputs: newValue
        Outputs: Void
        Author: Luke Poellet, March 6, 2017
     */
    public void setValue(int newValue)
    {
        if(hasMax == true && newValue > max)
        {

            value = max;

        }
        else if(hasMin == true && newValue < min)
        {

            value = min;

        }
        else
        {

            value = newValue;

        }
    }

    /*
        addModifier adds the variable "modifier" to the variable "value" and then calls setValue
        Inputs: void
        Outputs: void
        Author: Luke Poellet, March 6, 2017
     */
    public void addModifier()
    {
        this.setValue(value + modifier);
    }

    /*
        removeModifier removes the variable "modifier" to the variable "value" and then calls setValue
        Inputs: void
        Outputs: void
        Author: Luke Poellet, March 6, 2017
     */
    public void removeModifier()
    {
        this.setValue(value - modifier);
    }

}
