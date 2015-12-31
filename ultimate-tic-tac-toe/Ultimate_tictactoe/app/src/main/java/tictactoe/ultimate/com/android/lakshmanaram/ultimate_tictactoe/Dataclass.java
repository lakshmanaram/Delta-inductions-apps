package tictactoe.ultimate.com.android.lakshmanaram.ultimate_tictactoe;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by lakshmanaram on 23/7/15.
 */
public class Dataclass {
    private static char value= 'X';
    private static int valueno = 1;
    private static int tobeclickedno = -1;
    private static int clickedno=-1;
    private static ArrayList<ArrayList<Integer> > grid = new ArrayList<>();
    private static ArrayList<ArrayList<Integer> > iscomp = new ArrayList<>();
    private static ArrayList<ArrayList<Integer> > rem = new ArrayList<>();
    private static ArrayList<Integer> viewids = new ArrayList<>();
    public static void addviewid(int viewid){
        viewids.add(viewid);
    }

    public static int fillcomp() {
        ArrayList<Integer> initialtemp = iscomp.get(clickedno/3);
        if(initialtemp.get(clickedno%3)==0)
        {
            int i, j;
            ArrayList<Integer> temp_row0 = new ArrayList<>();
            ArrayList<Integer> temp_row1 = new ArrayList<>();
            ArrayList<Integer> temp_row2 = new ArrayList<>();
            i = (clickedno / 3) * 3;
            ArrayList<Integer> temp = grid.get(i);
            for (j = (clickedno % 3) * 3; j < ((clickedno % 3) * 3) + 3; j++) {
                temp_row0.add(temp.get(j));
            }
            i++;
            temp = grid.get(i);
            for (j = (clickedno % 3) * 3; j < ((clickedno % 3) * 3) + 3; j++) {
                temp_row1.add(temp.get(j));
            }
            i++;
            temp = grid.get(i);
            for (j = (clickedno % 3) * 3; j < ((clickedno % 3) * 3) + 3; j++) {
                temp_row2.add(temp.get(j));
            }
            //Log.d("hel",temp_row0.toString()+"---"+temp_row1.toString()+"---"+temp_row2.toString());
            int tempval = 0;
            if (temp_row0.get(0).equals(temp_row0.get(1)) && temp_row0.get(0).equals(temp_row0.get(2)) && temp_row0.get(2).equals(temp_row0.get(1)) && (!temp_row0.get(0).equals(0))) {
                tempval = temp_row0.get(0);
            } else if (temp_row1.get(0).equals(temp_row1.get(1)) && temp_row1.get(0).equals(temp_row1.get(2)) && temp_row1.get(2).equals(temp_row1.get(1)) && (!temp_row1.get(0).equals(0))) {
                tempval = temp_row1.get(0);
            } else if (temp_row2.get(0).equals(temp_row2.get(1)) && temp_row2.get(0).equals(temp_row2.get(2)) && temp_row2.get(2).equals(temp_row2.get(1)) && (!temp_row1.get(0).equals(0))) {
                tempval = temp_row2.get(0);
            } else if (temp_row1.get(0).equals(temp_row0.get(0)) && temp_row0.get(0).equals(temp_row2.get(0)) && temp_row1.get(0).equals(temp_row2.get(0)) && (!temp_row0.get(0).equals(0))) {
                tempval = temp_row1.get(0);
            } else if (temp_row1.get(1).equals(temp_row0.get(1)) && temp_row0.get(1).equals(temp_row2.get(1)) && temp_row1.get(1).equals(temp_row2.get(1)) && (!temp_row0.get(1).equals(0))) {
                tempval = temp_row1.get(1);
            } else if (temp_row1.get(2).equals(temp_row0.get(2)) && temp_row0.get(2).equals(temp_row2.get(2)) && temp_row1.get(2).equals(temp_row2.get(2)) && (!temp_row0.get(2).equals(0))) {
                tempval = temp_row1.get(2);
            } else if (temp_row0.get(0).equals(temp_row1.get(1)) && temp_row0.get(0).equals(temp_row2.get(2)) && temp_row2.get(2).equals(temp_row1.get(1)) && (!temp_row0.get(0).equals(0))) {
                tempval = temp_row0.get(0);
            } else if (temp_row0.get(2).equals(temp_row1.get(1)) && temp_row0.get(2).equals(temp_row2.get(0)) && temp_row2.get(0).equals(temp_row1.get(1)) && (!temp_row0.get(2).equals(0))) {
                tempval = temp_row0.get(2);
            }
            initialtemp.set(clickedno%3,tempval);
            iscomp.set(clickedno/3,initialtemp);
            if(tempval!=0)
                conquer();
            //Log.d("hel",clickedno/3+"-"+clickedno%3+"-"+tempval);
            return tempval;
    }
        else
            return 0;
    }
    public static void conquer()
    {
        int i = (clickedno/3)*3;
        for(;i<(clickedno/3)*3+3;i++)
        {
            ArrayList<Integer> temp = grid.get(i);
            int j;
            for(j=((clickedno%3)*3);j<((clickedno%3)*3)+3;j++)
            {
                temp.set(j,3);
            }
            grid.set(i,temp);
        }

    }
    public static int iswon()
    {
        ArrayList<Integer> temp_row0 = iscomp.get(0);
        ArrayList<Integer> temp_row1 = iscomp.get(1);
        ArrayList<Integer> temp_row2 = iscomp.get(2);
        if(temp_row0.get(0).equals(temp_row0.get(1))&&temp_row0.get(0).equals(temp_row0.get(2))&&temp_row0.get(2).equals(temp_row0.get(1))&&(!temp_row0.get(0).equals(0)))
        {
            return temp_row0.get(0);
        }
        else if(temp_row1.get(0).equals(temp_row1.get(1))&&temp_row1.get(0).equals(temp_row1.get(2))&&temp_row1.get(2).equals(temp_row1.get(1))&&(!temp_row1.get(0).equals(0)))
        {
            return temp_row1.get(0);
        }
        else if(temp_row2.get(0).equals(temp_row2.get(1))&&temp_row2.get(0).equals(temp_row2.get(2))&&temp_row2.get(2).equals(temp_row2.get(1))&&(!temp_row1.get(0).equals(0)))
        {
            return temp_row2.get(0);
        }
        else if(temp_row1.get(0).equals(temp_row0.get(0))&&temp_row0.get(0).equals(temp_row2.get(0))&&temp_row1.get(0).equals(temp_row2.get(0))&&(!temp_row0.get(0).equals(0)))
        {
            return temp_row1.get(0);
        }
        else if(temp_row1.get(1).equals(temp_row0.get(1))&&temp_row0.get(1).equals(temp_row2.get(1))&&temp_row1.get(1).equals(temp_row2.get(1))&&(!temp_row0.get(1).equals(0)))
        {
            return temp_row1.get(1);
        }
        else if(temp_row1.get(2).equals(temp_row0.get(2))&&temp_row0.get(2).equals(temp_row2.get(2))&&temp_row1.get(2).equals(temp_row2.get(2))&&(!temp_row0.get(2).equals(0)))
        {
            return temp_row1.get(2);
        }
        else if(temp_row0.get(0).equals(temp_row1.get(1))&&temp_row0.get(0).equals(temp_row2.get(2))&&temp_row2.get(2).equals(temp_row1.get(1))&&(!temp_row0.get(0).equals(0)))
    {
        return temp_row0.get(0);
    }
        else if(temp_row0.get(2).equals(temp_row1.get(1))&&temp_row0.get(2).equals(temp_row2.get(0))&&temp_row2.get(0).equals(temp_row1.get(1))&&(!temp_row0.get(2).equals(0)))
        {
            return temp_row0.get(2);
        }
        else
            return 0;

    }
    public static boolean getRem(int clickno){
        ArrayList<Integer> temp_row = iscomp.get(clickno / 3);
        if(temp_row.get(clickno%3)==0)
            return true;
        else
            return false;
    }
    public static int getTobeclickedno() {
        return tobeclickedno;
    }

    public static void setTobeclickedno(int tobeclickedno) {
        Dataclass.tobeclickedno = tobeclickedno;
    }
    public static int giveClickedno(int n)
    {
        int i=0;
        for(i=0;i<viewids.size();i++)
        {
            if(viewids.get(i)==n)
                return i;
        }
        return -1;
    }
    public static void remdec(int clickno){
        ArrayList<Integer> temp_row = rem.get(clickno / 3);
        temp_row.set(clickno%3,temp_row.get(clickno%3)-1);
        rem.set(clickno / 3,temp_row);
    }
    public static char getValue() {
        return value;
    }


    public static int getClickedno(){
        return clickedno;
    }
    public static boolean isgridtilefilled(int clickno){
        Log.d("hel",((clickedno / 3) * 3) + (clickno/3)+"  "+ ((clickedno%3)*3)+" "+(clickno%3));
        ArrayList<Integer> temp_row = grid.get(((clickedno / 3) * 3) + (clickno/3));
        Log.d("hel",temp_row.toString());
        int j = ((clickedno%3)*3)+(clickno%3);
        if(temp_row.get(j).equals(0))
        {
            return true;
        }
        else
        {
            return false;
        }

    }
    public static void setgridtilefilled(int clickno){
        Log.d("hel", clickno + "::::" + valueno);
        ArrayList<Integer> temp_row = grid.get(((clickedno / 3) * 3) + clickno / 3);
        temp_row.set(((clickedno % 3) * 3) + (clickno % 3), valueno);
        grid.set(((clickedno / 3) * 3) + clickno / 3, temp_row);
        remdec(clickno);
        changeValue();
    }
    public static void findclickedno(int n){
        int i=0;
        for(i=0;i<viewids.size();i++)
        {
            if(viewids.get(i)==n)
                clickedno = i;
        }
    }

    public static void initial(){
        int i;
        int j=0;
        value= 'X';
        valueno = 1;
        tobeclickedno = -1;
        clickedno=-1;
        grid.clear();
        rem.clear();
        iscomp.clear();
        for(j=0;j<9;j++) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (i = 0; i < 9; i++)
                temp.add(0);
            grid.add(temp);
        }
        for(j=0;j<3;j++) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (i = 0; i < 3; i++)
                temp.add(0);
            iscomp.add(temp);
        }
        for(j=0;j<3;j++) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (i = 0; i < 3; i++)
                temp.add(9);
            rem.add(temp);
        }

    }
    public static void changeValue() {
        if(Dataclass.value=='X')
        {
            valueno=2;
            Dataclass.value='O';
        }
        else
        {
            valueno=1;
            Dataclass.value='X';
        }
    }
}
