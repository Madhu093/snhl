package com.example.kurapma.snhl.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kurapma.snhl.R;
import com.example.kurapma.snhl.model.quotes.Contents;
import com.example.kurapma.snhl.model.PayPalConfig;
import com.example.kurapma.snhl.model.quotes.QuoteOfTheDay;
import com.example.kurapma.snhl.model.quotes.Quotes;
import com.example.kurapma.snhl.rest.ApiClient;
import com.example.kurapma.snhl.rest.ApiInterface;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kurapma on 1/11/17.
 */

public class DonateActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    private Button paymentButton;
    public EditText editTextAmount;
    private TextView tv_quote, tv_author;
    private String paymentAmount;
    private AdView mAdView;

    //Paypal intent request code to track onActivityResult method
    public static final int PAYPAL_REQUEST_CODE = 123;

    //Paypal Configuration Object
    private static PayPalConfiguration config = new PayPalConfiguration()
            // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
            // or live (ENVIRONMENT_PRODUCTION)
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(PayPalConfig.PAYPAL_CLIENT_ID);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        Intent intent = new Intent(this, PayPalService.class);

        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        startService(intent);

        paymentButton = (Button) findViewById(R.id.payment_button);
        paymentButton.setOnClickListener(this);

        editTextAmount = (EditText) findViewById(R.id.editTextAmount);
        tv_quote = (TextView) findViewById(R.id.quote);
        tv_author = (TextView) findViewById(R.id.tv_author);

        mAdView = (AdView) findViewById(R.id.donate_adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        if (mAdView.getAdSize() != null || mAdView.getAdUnitId() != null) {
            mAdView.loadAd(adRequest);
        }

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<QuoteOfTheDay> call = apiService.getQuoteOfTheDay("http://quotes.rest/qod.json");
        call.enqueue(new Callback<QuoteOfTheDay>() {
            @Override
            public void onResponse(Call<QuoteOfTheDay> call, Response<QuoteOfTheDay> response) {
                try {
                    Contents contents = response.body().getContents();
                    Quotes[] quotes = contents.getQuotes();
                    String quote = quotes[0].getQuote();
                    String author = quotes[0].getAuthor();
                    tv_quote.setText(quote);
                    tv_author.setText(author);
                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<QuoteOfTheDay> call, Throwable t) {
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        if (isNotNullNotEmptyNotWhiteSpaceOnlyByJava(editTextAmount.getText().toString())) {
            getPayment();
        } else {
            showAlertDialog();
        }
    }

    private void getPayment() {

        //Getting the amount from editText
        paymentAmount = editTextAmount.getText().toString();

        //Creating a paypalpayment
        PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(paymentAmount)), "USD", "Simplified Coding Fee",
                PayPalPayment.PAYMENT_INTENT_SALE);

        //Creating Paypal Payment activity intent
        Intent intent = new Intent(this, PaymentActivity.class);

        //putting the paypal configuration to the intent
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        //Puting paypal payment to the intent
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

        //Starting the intent activity for result
        //the request code will be used on the method onActivityResult
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.amount_cannot_be_empty);
        builder.setMessage(R.string.please_enter_amount);
        builder.setPositiveButton(android.R.string.ok, null);
        builder.show();
    }

    public static boolean isNotNullNotEmptyNotWhiteSpaceOnlyByJava(
            final String string) {
        return string != null && !string.isEmpty() && !string.trim().isEmpty();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //If the result is from paypal
        if (requestCode == PAYPAL_REQUEST_CODE) {

            //If the result is OK i.e. user has not canceled the payment
            if (resultCode == Activity.RESULT_OK) {
                //Getting the payment confirmation
                PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                //if confirmation is not null
                if (confirm != null) {
                    try {
                        //Getting the payment details
                        String paymentDetails = confirm.toJSONObject().toString(4);
                        Log.i("paymentExample", paymentDetails);

                        //Starting a new activity for the payment details and also putting the payment details with intent
                        startActivity(new Intent(this, ConfirmationActivity.class)
                                .putExtra("PaymentDetails", paymentDetails)
                                .putExtra("PaymentAmount", paymentAmount));

                    } catch (JSONException e) {
                        Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("paymentExample", "The user canceled.");
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            }
        }
    }
}



/*
{
        "client": {
        "environment": "sandbox",
        "paypal_sdk_version": "2.0.0",
        "platform": "iOS",
        "product_name": "PayPal iOS SDK;"
        },
        "response": {
        "create_time": "2014-02-12T22:29:49Z",
        "id": "PAY-564191241M8701234KL57LXI",
        "intent": "sale",
        "state": "approved"
        },
        "response_type": "payment"
        }

        */
