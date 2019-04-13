package com.example.coffeeshop;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    int quantity=90;
    String name="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    protected void submitOrder(View view){
        EditText nameTxt=findViewById(R.id.name);
        name=nameTxt.getText().toString();
        CheckBox creamChk=findViewById(R.id.creamed);
        boolean hasWhippedCream = creamChk.isChecked();

        CheckBox chocoChk=findViewById(R.id.chocolate);
        boolean hasChocolate = chocoChk.isChecked();

        CheckBox takeAwayChk=findViewById(R.id.takeAway);
        boolean takeAway = takeAwayChk.isChecked();


        display(quantity);
        int total=calculatePrice(hasWhippedCream,hasChocolate,takeAway);

        String orderSummary=createOrderSummary(hasWhippedCream,hasChocolate,takeAway,total);
        displaySummary(orderSummary);
        sendEmail(orderSummary);

    }

    private void sendEmail(String orderSummary) {
        Intent intent=new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee order for "+name);
        intent.putExtra(Intent.EXTRA_TEXT, "Order summary\n"+orderSummary);
        startActivity(intent);
    }

    /**
     *
     */

    private void displaySummary(String orderSummary) {
        TextView orderSummary_txt=findViewById(R.id.order_summary_txt);
        orderSummary_txt.setText(orderSummary);


    }

    private String createOrderSummary(boolean addWhippedCream, boolean addChocolate,boolean takeAway,int total) {
        String message="Whipped Cream ?"+addWhippedCream;
        message+="\nChocolate ?"+addChocolate;
        message+="\nTake Away? "+takeAway;
       message+= "\nQuantity:"+quantity;
        message+="\nTotal:₹"+total;
        message+="\nThank you "+name+"!";
        return message;
    }

    /**
     * Calculates the price of the order.
     *
     * @param addWhippedCream is whether or not we should include whipped cream topping in the price
     * @param addChocolate    is whether or not we should include whipped cream topping in the price
     * @param takeAway    is whether or not customer wants the coffee to take away
     * @return total price
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate,boolean takeAway) {
        int price=20;
        if (addWhippedCream){
          price=price+5;
        }

        if (addChocolate){
            price=price+7;
        }
        if (takeAway){
            price=price+2;
        }


        return quantity*price;

    }


    /*
    * Changes the quantity text to a number passed
    * @param 1st the number to be displayed
    * */
    private void display(int number) {
        TextView quantity_txt = (TextView) findViewById(R.id.quantity_textview);
        quantity_txt.setText("" + number);
    }



    /*
     * Increases the quantity by one
     * */
    protected void increment(View view){

        if (quantity==99){
            return;
        }
       quantity++;
        display(quantity);
    }

    /*
     * Decreases the quantity by one
     * */
    protected void decrement(View view){
        if (quantity==1){
            return;
        }
        quantity--;
        display(quantity);
    }

}
