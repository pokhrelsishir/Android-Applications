package com.example.justjava;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//}
//

/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

//package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for ");
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);


        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);

        }

//
//        displayMessage(priceMessage);


    }

    public int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int basePrice = 5;

        if (addWhippedCream) {
            basePrice = basePrice + 1;
        }

        if (addChocolate) {
            basePrice = basePrice + 2;
        }
        return quantity * basePrice;
    }

    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate) {

//        priceMessage += "\nAdd Whipped Cream?: " + addWhippedCream;
//        priceMessage += "\nAdd Chocolate?: " + addChocolate;
//        priceMessage += "\nQuantity: " + quantity;
//        priceMessage += "\nTotal: $" + price;
//        priceMessage += "\n" +getString(R.string.thank_you);
//        return priceMessage;


        String priceMessage = getString(R.string.order_summary_name,name);
        priceMessage +="\n" + getString(R.string.order_summary_whipped_cream, addWhippedCream);
        priceMessage +="\n" + getString(R.string.order_summary_chocolate, addChocolate);
        priceMessage +="\n" + getString(R.string.order_summary_quantity, quantity);
        priceMessage +="\n" + getString(R.string.order_summary_price, NumberFormat.getCurrencyInstance().format(price));
        priceMessage +="\n" + getString(R.string.thank_you);
        return priceMessage;




    }

    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "You Cannot have more than 100 Coffee !!!", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        display(quantity);

    }

    public void decrement(View view) {

        if (quantity == 1) {
            Toast.makeText(this, "You Cannot have less than 1 Coffee !!!", Toast.LENGTH_SHORT).show();
            return;
        }


        quantity = quantity - 1;
        display(quantity);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


//    /**
//     * This method displays the given text on the screen.
//     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }


}
