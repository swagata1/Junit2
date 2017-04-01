package com.mindtree.fsmmindtree;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import net.sourceforge.zbar.Symbol;

/**
 * This is a Barcode Scanner Code which is used to scan different types of Barcodes
 */

public class CameraScanner extends Activity {
    private Button scannerButton;
    private TextView contentTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        contentTxt = (TextView) findViewById(R.id.barcode_value);
        scannerButton = (Button) findViewById(R.id.scan_button);

        try {

            scannerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getApplicationContext(), BarcodeScanner.class);
                    intent.putExtra("SCAN_MODES", new int[]{Symbol.EAN13, Symbol.EAN8, Symbol.UPCA, Symbol.UPCE, Symbol.CODE128, Symbol.CODE39});  //This is for different types of barcode

                    startActivityForResult(intent, 0);  // intent and pass request code value
                }
            });

        } catch (ActivityNotFoundException anfe) {
            Log.e("onCreate", "Scanner Not Found", anfe);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {

                String code = data.getStringExtra("Code");
                Toast.makeText(this, "" + code, Toast.LENGTH_LONG).show();

                contentTxt.setText(code);
            } else if (resultCode == RESULT_CANCELED && data != null) {
                Toast toast = Toast.makeText(this, "Scan was Cancelled!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 25, 400);
                toast.show();
            }
        }
    }


}