
package com.ysf.mslh.guideme.hiddenFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ysf.mslh.guideme.R;
import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;

public class CreditCardScannerFragment extends Fragment {
    private static final int SCAN_REQUEST_CODE = 100;
    private TextView resultTextView;


    public CreditCardScannerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_credit_card_scanner, container, false);
        
        Button scanButton = view.findViewById(R.id.buttonScanCard);
        resultTextView = view.findViewById(R.id.textViewResult);

        scanButton.setOnClickListener(v -> startCardScanner());

        return view;
    }

    private void startCardScanner() {
        Intent scanIntent = new Intent(getActivity(), CardIOActivity.class);
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true);
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, true);
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false);
        startActivityForResult(scanIntent, SCAN_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SCAN_REQUEST_CODE) {
            if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
                CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);
                String resultText = "Card Number: " + scanResult.getRedactedCardNumber() + "\n" +
                                  "Expiry: " + scanResult.expiryMonth + "/" + scanResult.expiryYear + "\n" +
                                  "CVV: " + scanResult.cvv;
                resultTextView.setText(resultText);
            }
        }
    }
}
