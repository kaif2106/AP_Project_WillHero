package plswrk.willherofx;

import java.util.Comparator;

class ScoreCompare implements Comparator<Object>
{
    @Override
    public int compare(Object p1, Object p2) {
        if(p1 instanceof Player_Date fp && p2 instanceof Player_Date sp){
            return (int)(fp.getScore() - sp.getScore());
        }
        else
            return -1;
    }
}