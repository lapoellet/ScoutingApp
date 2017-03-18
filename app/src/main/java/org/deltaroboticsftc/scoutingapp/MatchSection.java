package org.deltaroboticsftc.scoutingapp;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by Luke Poellet on 3/8/2017.
 */

public class MatchSection
{
    private String sectionName;
    private boolean sectionRecourcesFound = false;
    private Context context;

    private LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    private LinearLayout sectionLayout;

    private ArrayList<String> elements;
    private ArrayList<Counter> counters;

    public MatchSection(String sectionNamePass, Context contextPass)
    {
        sectionName = sectionNamePass;
        context = contextPass;

        elements = new ArrayList<>();
        counters = new ArrayList<>();

        sectionLayout = new LinearLayout(context);
        sectionLayout.setOrientation(LinearLayout.VERTICAL);
        sectionLayout.setLayoutParams(params);
        sectionLayout.setBackgroundColor(Color.MAGENTA);

        this.setupSection();
    }

    private void setupSection()
    {
        String sectionElements;
        String sectionElementsSubStringed = null;
        String sectionPull = null;
        boolean doLoopDone = false;

        int ID = context.getResources().getIdentifier(("app" + sectionName + "SectionContent"), "string", context.getPackageName());
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
            if(sectionElementsSubStringed.contains(",") == true)
            {
                sectionPull = sectionElementsSubStringed.substring(0, sectionElementsSubStringed.indexOf(","));
                sectionElementsSubStringed = sectionElementsSubStringed.substring(sectionElementsSubStringed.indexOf("," + 1));
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
        else if(elementType.equals("RadioGroup"))
        {

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
