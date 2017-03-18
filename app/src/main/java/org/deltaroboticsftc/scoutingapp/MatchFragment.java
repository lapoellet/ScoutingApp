package org.deltaroboticsftc.scoutingapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Luke Poellet on 3/7/2017.
 */

public class MatchFragment extends Fragment
{

    private Context context;
    private Boolean clearData = false;

    private ArrayList<MatchSection> sections;

    public MatchFragment(Context contextPass, Boolean clearDataPass)
    {
        context = contextPass;
        clearData = clearDataPass;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.layout_match, container, false);
        return rootView;
    }

    private void createMatchLayout()
    {
        int ID = context.getResources().getIdentifier("appMatchSections", "string", context.getPackageName());
        if(ID == 0)
        {
            return;
        }
        String matchSections = context.getResources().getString(R.string.appMatchSections);
        String matchSectionSubStringed = matchSections;
        String sectionPull = null;
        boolean doLoopDone = false;

        do
        {
            if(matchSectionSubStringed.contains(",") == true)
            {
                sectionPull = matchSectionSubStringed.substring(0, matchSectionSubStringed.indexOf(","));
                matchSectionSubStringed = matchSectionSubStringed.substring(matchSectionSubStringed.indexOf("," + 1));
                sections.add(new MatchSection(sectionPull, context));
                if(sections.get(sections.size() - 1).wasSectionResourcesFound() == false)
                {
                    sections.remove(sections.size() - 1);
                }
            }
            else
            {
                sections.add(new MatchSection(matchSectionSubStringed, context));
                if(sections.get(sections.size() - 1).wasSectionResourcesFound() == false)
                {
                    sections.remove(sections.size() - 1);
                }
            }
        }
        while(doLoopDone == false);

    }
}
