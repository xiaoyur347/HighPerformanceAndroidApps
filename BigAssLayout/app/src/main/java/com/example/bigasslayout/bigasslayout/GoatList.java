package com.example.bigasslayout.bigasslayout;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by demo on 12/22/14.
 */


public class GoatList extends ArrayAdapter<String> {
    private final Activity context;
    private final String xmlSpeed;
    private boolean fibonacciBool;
    private final String[] goatNames;

    private final boolean[] goatTrue;
    private final int[] goatPix;

    private final boolean lotsOfObjects;

    CheckBox rowCheck;

    public GoatList(Activity context, String xmlSpeed, Boolean fibonacciBool, String[] goatNames,
                    int[] goatPix, boolean[] goatTrue, boolean lotsOfObjects) {
        super(context, R.layout.goatrow, goatNames);
        this.context = context;
        this.xmlSpeed = xmlSpeed;
        this.fibonacciBool = fibonacciBool;
        this.goatNames = goatNames;
        this.goatPix = goatPix;
        this.goatTrue = goatTrue;
        this.lotsOfObjects = lotsOfObjects;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView;

        if (xmlSpeed.matches(context.getResources().getString(R.string.slowestXml))) {
            //slow view is goatrow
            rowView = inflater.inflate(R.layout.goatrow, null, true);
        } else if (xmlSpeed.equals(context.getResources().getString(R.string.overdrawXml))) {
            //use view with as much overdraw removed as possible
            rowView = inflater.inflate(R.layout.nooverdrawgoatrow, null, true);
        } else if (xmlSpeed.equals(context.getResources().getString(R.string.removeLLoverdrawXml))) {
            //use view with as much overdraw removed as possible
            rowView = inflater.inflate(R.layout.nooverdrawgoatrow, null, true);
        } else if (xmlSpeed.equals(context.getResources().getString(R.string.RLfastXml))) {
            //use view with as much overdraw removed as possible
            //this also removes one layer of the goat row by using a relative layout
            rowView = inflater.inflate(R.layout.rlfastestgoatrow, null, true);
        } else if (xmlSpeed.matches(context.getResources().getString(R.string.myfast))) {
            //my fast view
            rowView = inflater.inflate(R.layout.my_optimize_goatrow, null, true);
        } else //fastest
        {
            rowView = inflater.inflate(R.layout.fastestgoatrow, null, true);
        }


        //add more objects - defaults to no
        if (!lotsOfObjects) {
            // we'll use the views initialized once at the top.
            TextView rowTxt = (TextView) rowView.findViewById(R.id.textView);
            ImageView rowImg = (ImageView) rowView.findViewById(R.id.imageView);
            rowCheck = (CheckBox) rowView.findViewById(R.id.checkBox);

            if (fibonacciBool) {
                int bigNumber;
                //confusion and delay -take the position, add 4, multiply by another number to (a BIG number)
                //find that member of the fibonacci sequence using recursion (which is slow)
                if (position == 5) {
                    bigNumber = (position + 8) * 3;//will cause a jink scrolling up
                } else if (position == 10) {
                    bigNumber = (position + 3) * 3;//will pause scrolling down
                } else {
                    bigNumber = (position + 4) * 2;
                }
                int fibValue = fibonacci.fib(bigNumber);
                //wasted time!
                rowTxt.setText(goatNames[position] + "\nDelay Fibonacci #: " + fibValue);
            } else {//no crazy slowdown from fibonacci.
                rowTxt.setText(goatNames[position]);
            }

            rowImg.setImageResource(goatPix[position]);
            rowCheck.setChecked(goatTrue[position]);
            rowCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox checkedBox = (CheckBox) v;

                    //this all has to be inverse. If the user checks the box - it WAS unchecked
                    if (!checkedBox.isChecked()) {
                        //button WAS checked. User unchecked it
                        //goat
                        checkedBox.setChecked(true);
                        Toast.makeText(context, "Correct! \nThis is a goat", Toast.LENGTH_SHORT).show();
                    } else {
                        //button was not checked, and user checked it
                        //not a goat
                        checkedBox.setChecked(false);
                        Toast.makeText(context, "Come on, \nThis is a NOT a goat", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else { //lotsOfObjects is true
            //let's create new views every interaction
            //hahahahah
            TextView rowTxt = (TextView) rowView.findViewById(R.id.textView);
            ImageView rowImg = (ImageView) rowView.findViewById(R.id.imageView);
            CheckBox rowCheckWaste = (CheckBox) rowView.findViewById(R.id.checkBox);

            //look a bunch of redundant objects created during rendering.  How silly :)
            if (fibonacciBool) {
                //confusion and delay -take the position, add 4, multiply by another number to (a BIG number)
                //find that member of the fibonacci sequence using recursion (which is slow)
                int bigNumberWaste;
                if (position == 5) {
                    bigNumberWaste = (position + 8) * 3;//will cause a jink scrolling up
                    //}else if (position == 10) {
                    //  bigNumberWaste  = (position+3)*3;//will pause scrolling down
                } else {
                    bigNumberWaste = (position + 4) * 2;
                }
                int fibValue = fibonacci.fib(bigNumberWaste);
                //wasted time!
                rowTxt.setText(goatNames[position] + "\nDelay Fibonacci #: " + fibValue);
            } else {//no crazy slowdown.
                rowTxt.setText(goatNames[position]);
            }

            rowImg.setImageResource((goatPix[position]));
            rowCheckWaste.setChecked(goatTrue[position]);
            rowCheckWaste.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //add a new variable for the checkbox
                    CheckBox checkedBox = (CheckBox) v;

                    //this all has to be inverse. If the user checks the box - it WAS unchecked
                    if (!checkedBox.isChecked()) {
                        //button WAS checked. User unchecked it
                        //goat
                        checkedBox.setChecked(true);
                        Toast.makeText(context, "Correct! \nThis is a goat", Toast.LENGTH_SHORT).show();
                    } else {
                        //button was not checked, and user checked it
                        //not a goat
                        checkedBox.setChecked(false);
                        Toast.makeText(context, "Come on, \nThis is a NOT a goat", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        return rowView;
    }
}

