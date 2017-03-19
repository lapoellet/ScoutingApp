package org.deltaroboticsftc.scoutingapp;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Luke Poellet on 3/18/2017.
 */

public class RadioSet extends AppCompatActivity
{

    private String RadioSetName;
    private Context context;

    private LinearLayout.LayoutParams params;

    private int textSubTitleID;
    private int textContentID;

    private LinearLayout layoutRadioSet;
    private TextView textViewTitle;
    private RadioGroup radioButtonGroup;
    private ArrayList<RadioButton> radioButtons;

    //Resources
        private String title = "No Radio Group Title Found";

        private int numberOfSelections = 2;
        private int startSelection = 1;

        private ArrayList<String> selectionsText;

    /*
        Counter is called only once when a new Counter is created.
        Inputs: namePass, contextPass
        Outputs: void
        Author: Luke Poellet, March 3, 2017
     */
    public RadioSet(String namePass, Context contextPass)
    {
        RadioSetName = namePass;
        context = contextPass;
        selectionsText = new ArrayList<>();
        this.getCounterResources();
        this.buildCounter();
        this.reset();
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

        textSubTitleID = context.getResources().getIdentifier(("appStyleSubTitle"), "style", context.getPackageName());
        textContentID = context.getResources().getIdentifier(("appStyleContent"), "style", context.getPackageName());

        ID = context.getResources().getIdentifier(("app" + RadioSetName + "Title"), "string", context.getPackageName());
        if(ID != 0)
        {
            title = context.getResources().getString(ID);
        }

        ID = context.getResources().getIdentifier(("app" + RadioSetName + "NumberOfSelections"), "integer", context.getPackageName());
        if(ID != 0)
        {
            numberOfSelections = context.getResources().getInteger(ID);
        }

        ID = context.getResources().getIdentifier(("app" + RadioSetName + "StartSelection"), "integer", context.getPackageName());
        if(ID != 0)
        {
            startSelection = context.getResources().getInteger(ID);
        }

        for(int loop = 1; loop <= numberOfSelections; loop++)
        {
            ID = context.getResources().getIdentifier(("app" + RadioSetName + "SelectionText_" + loop), "integer", context.getPackageName());
            if(ID != 0)
            {
                selectionsText.add(context.getResources().getString(ID));
            }
            else
            {
                selectionsText.add("No Selection Text Found");
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
        int intVersion = Build.VERSION.SDK_INT;
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutRadioSet = new LinearLayout(context);
        layoutRadioSet.setOrientation(LinearLayout.VERTICAL);
        layoutRadioSet.setLayoutParams(params);

        textViewTitle = new TextView(context);
        textViewTitle.setLayoutParams(params);
        if(intVersion < 23)
        {
            textViewTitle.setTextAppearance(context, textSubTitleID);
        }
        else
        {
            textViewTitle.setTextAppearance(textSubTitleID);
        }
        textViewTitle.setGravity(Gravity.CENTER);
        textViewTitle.setLines(1);
        textViewTitle.setText(title);

        radioButtonGroup = new RadioGroup(context);
        radioButtonGroup.setLayoutParams(params);

        radioButtons = new ArrayList<>();
        for(int loop = 1; loop <= numberOfSelections; loop++)
        {
            radioButtons.add(new RadioButton(context));
            radioButtons.get(loop - 1).setLayoutParams(params);
            //radioButtons.get(loop - 1).setButtonTintList(context.getResources().getColorStateList(R.color.appColorAccent));
            //radioButtons.get(loop - 1).setForegroundTintList(context.getResources().getColorStateList(R.color.appTextContentColor));
            if(intVersion < 23)
            {
                radioButtons.get(loop - 1).setTextAppearance(context, textContentID);
            }
            else
            {
                radioButtons.get(loop - 1).setTextAppearance(textContentID);
            }
            radioButtons.get(loop - 1).setText(selectionsText.get(loop - 1));

            radioButtonGroup.addView(radioButtons.get(loop - 1));
        }

        layoutRadioSet.addView(textViewTitle);
        layoutRadioSet.addView(radioButtonGroup);
    }

    public LinearLayout getRadioSet()
    {
        return layoutRadioSet;
    }

    public void reset()
    {
        radioButtons.get(startSelection - 1).setChecked(true);
    }

}
