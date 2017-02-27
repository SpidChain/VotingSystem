package io.positronicbrain.test_0;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.WalletUtils;

import static java.lang.Boolean.*;
//import static java.lang.Boolean.TRUE;

public class MainActivity extends AppCompatActivity {
    String walletPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d("MyApp", "onCreate");
        File file = getApplicationContext().getFilesDir();
        Log.d(file.getPath(), "pd");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Log.d("MyApp", "onCreateOptionsMenu");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        Log.d("MyApp", "onOptiosItemSelected");
        return super.onOptionsItemSelected(item);
    }

    public void generateWallet(View view) {
        Log.d("ciro","ciro");
        /*
        File file = getApplicationContext().getFilesDir();
        try {
            WalletUtils.generateNewWalletFile("myWallet", file, FALSE);
            Log.d("Creating wallet: ", walletPath);
        } catch (CipherException | IOException | InvalidAlgorithmParameterException |
                NoSuchAlgorithmException | NoSuchProviderException e) {
            // This block is to catch divide-by-zero error
            Log.e("Error", "cannot create wallet");
        }
        */

    }
/*
    public void deleteWallet(View view) {
        File file = new File(walletPath);
        Log.d("Deleting wallet: ", walletPath);
        file.delete();

    }
    */
}