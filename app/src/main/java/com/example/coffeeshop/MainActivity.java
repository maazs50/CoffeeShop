package com.example.coffeeshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    int quantity=1;
    int price=20;
    String name="";

    EditText nametxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    protected void submitOrder(View view){
        display(quantity);
        displayPrice(price*quantity);
        nametxt.setText("");
    }


    /*
    * Changes the quantity text to a number passed
    * @param 1st the number to be displayed
    * */
    private void display(int number){
        TextView quantity_txt=(TextView) findViewById(R.id.quantity_textview);
        quantity_txt.setText(""+number);
    }

    /*
     * Changes the price text to a number passed
     * @param 1st the number to be displayed
     * */
    private void displayPrice(int number){
        TextView price_txt=(TextView) findViewById(R.id.price_textview);
        nametxt=(EditText)findViewById(R.id.nametxt);
        name=nametxt.getText().toString();
        price_txt.setText("Total:₹"+number+"\nThank you "+name+"!");
    }



    /*
     * Increases the quantity by one
     * */
    protected void increment(View view){
       quantity++;
        display(quantity);
    }

    /*
     * Decreases the quantity by one
     * */
    protected void decrement(View view){
        quantity--;
        display(quantity);
    }
}
