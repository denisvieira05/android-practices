package com.example.android.myapplication;

        import android.os.Bundle;
        import android.support.v7.app.ActionBarActivity;
        import android.view.View;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText text = (EditText) findViewById(R.id.name_field);
        String textName = text.getText().toString();


        // Figure out if the user wants whipped cream topping
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        // Figure out if the user wants whipped cream topping
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        // Calculate the price
        int price = calculatePrice(7, hasWhippedCream, hasChocolate);

        // Display the order summary on the screen
        String message = createOrderSummary(price, hasWhippedCream, hasChocolate, textName);
        displayMessage(message);
    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private int calculatePrice(int PricePerCup, boolean addWhippedCream, boolean addChocolate) {
        int price = quantity * PricePerCup;
        if(addWhippedCream){
            price += 1;
        }
        if(addChocolate){
            price += 2;
        }
        return price;
    }

    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String textName){
        String priceMessage = "Name : "+textName+"\n";
        priceMessage += "\nAdd Whipped Cream ? "+ addWhippedCream+"\n";
        priceMessage += "\nAdd chocolate? " + addChocolate;
        priceMessage += "\nQuantity: "+ quantity+"\n";
        priceMessage += "\nTotal: R$"+price+"\n";
        priceMessage += "\nThank You!\n";
        return priceMessage;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void increment(View view) {
        if(quantity == 100){
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity+1;
        displayQuantity(quantity);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void decrement(View view) {
        if(quantity == 1){
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity-1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }



}
