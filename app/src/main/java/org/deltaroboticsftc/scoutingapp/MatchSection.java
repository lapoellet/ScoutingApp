package org.deltaroboticsftc.scoutingapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Luke Poellet on 3/8/2017.
 */

public class MatchSection
{
    private String sectionReference;
    private String sectionName;
    private boolean sectionRecourcesFound = false;
    private Context context;

    private LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    
    private LinearLayout sectionLayout;
    
    private TextView sectionTitle; 

    private ArrayList<String> elements;
    private ArrayList<Counter> counters;
    private ArrayList<RadioSet> radioSet;

    public MatchSection(String sectionReferencePass, Context contextPass)
    {
        sectionReference = sectionReferencePass;
        context = contextPass;

        int intversion = Build.VERSION.SDK_INT;
        int textTitleID = context.getResources().getIdentifier(("appStyleTitle"), "style", context.getPackageName());
        int ID = context.getResources().getIdentifier(("app" + sectionReference + "SectionTitle"), "string", context.getPackageName());
        if(ID != 0)
        {
            sectionName = context.getResources().getString(ID);
        }

        elements = new ArrayList<>();
        counters = new ArrayList<>();
        radioSet = new ArrayList<>();
        
        sectionLayout = new LinearLayout(context);
        sectionLayout.setOrientation(LinearLayout.VERTICAL);
        sectionLayout.setLayoutParams(params);
        
        sectionTitle = new TextView(context);
        sectionTitle.setLayoutParams(params);
        if(intversion < 23)
        {
            sectionTitle.setTextAppearance(context, textTitleID);
        }
        else
        {
            sectionTitle.setTextAppearance(textTitleID);
        }
        sectionTitle.setTypeface(null, Typeface.BOLD);
        sectionTitle.setGravity(Gravity.CENTER);
        sectionTitle.setLines(1);
        if(intversion < 23)
        {
            sectionTitle.setBackgroundColor(context.getResources().getColor(R.color.appColorPrimary));
        }
        else
        {
            sectionTitle.setBackgroundColor(context.getResources().getColor(R.color.appColorPrimary, null));
        }
        sectionTitle.setText(sectionName);

        sectionLayout.addView(sectionTitle);

        this.setupSection();
    }

    private void setupSection()
    {
        String sectionElements;
        String sectionElementsSubStringed = null;
        String sectionPull = null;
        boolean doLoopDone = false;

        int ID = context.getResources().getIdentifier(("app" + sectionReference + "SectionContent"), "string", context.getPackageName());
        if(ID != 0)
        {
            sectionRecourcesFound = true;
            sectionElements = context.getResources().getString(ID);
            sectionElementsSubStringed = sectionElements;
        }
        else
        {
            sectionRecourcesFound = false;
            return;
        }

        do
        {
            System.out.println(sectionElementsSubStringed);
            if(sectionElementsSubStringed.contains(",") == true)
            {
                sectionPull = sectionElementsSubStringed.substring(0, sectionElementsSubStringed.indexOf(","));
                sectionElementsSubStringed = sectionElementsSubStringed.substring(sectionElementsSubStringed.indexOf(",") + 1);
                elements.add(sectionPull);
                this.createElement();
            }
            else
            {
                elements.add(sectionElementsSubStringed);
                this.createElement();
                doLoopDone = true;
            }
        }
        while(doLoopDone == false);
    }

    private void createElement()
    {
        String element = elements.get(elements.size() - 1);
        String elementType = null;

        int ID = context.getResources().getIdentifier(("app" + element + "Type"), "string", context.getPackageName());
        if(ID != 0)
        {
            elementType = context.getResources().getString(ID);
        }
        else
        {
            return;
        }

        if(elementType.equals("Counter"))
        {
            counters.add(new Counter(element, context));
            sectionLayout.addView(counters.get(counters.size() - 1).getCounter());
        }
        else if(elementType.equals("RadioSet"))
        {
            radioSet.add(new RadioSet(element, context));
            sectionLayout.addView(radioSet.get(radioSet.size() - 1).getRadioSet());
        }
        else if(elementType.equals("TextArea"))
        {

        }
        else
        {
            elements.remove(elements.size() - 1);
        }
    }

    public boolean wasSectionResourcesFound()
    {
        return sectionRecourcesFound;
    }

    public LinearLayout getSectionLayout()
    {
        return sectionLayout;
    }
}
