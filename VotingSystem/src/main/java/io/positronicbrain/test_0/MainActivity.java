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
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import rx.Completable;

import static java.lang.Boolean.*;
import static org.web3j.tx.Contract.GAS_LIMIT;
import static org.web3j.tx.ManagedTransaction.GAS_PRICE;
//import static java.lang.Boolean.TRUE;

public class MainActivity extends AppCompatActivity {
    String walletPath;
    MetaCoin metacoin;

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
        File file = getApplicationContext().getFilesDir();
        try {
            this.walletPath = WalletUtils.generateNewWalletFile("myWallet", file, FALSE);
            Log.d("Creating wallet: ", walletPath);
        } catch (CipherException | IOException | InvalidAlgorithmParameterException |
                NoSuchAlgorithmException | NoSuchProviderException e) {
            // This block is to catch divide-by-zero error
            Log.e("Error", "cannot create wallet");
        }
    }

    public void deleteWallet(View view) {
        Log.d("Deleting wallet: ", walletPath);
        if (getApplicationContext().deleteFile(this.walletPath)) {
            Log.d("Deleting wallet: ", "done");
        } else {
            Log.d("Deleting wallet: ", "error");
        }
    }

    public void getBalance(View view) {
        File filesDir = getApplicationContext().getFilesDir();
        Web3j web3 = Web3jFactory.build(new HttpService("http://10.0.2.2:8545"));
        try {
            Credentials credentials = WalletUtils.loadCredentials("myWallet", getApplicationContext().getFileStreamPath(this.walletPath).getPath());
            try {
                EthGetBalance balance = web3.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();
                Log.d("Balance", balance.getBalance().toString());
            } catch (InterruptedException | ExecutionException e) {
                Log.e("Error", e.toString());
            }
        } catch (CipherException | IOException e) {
            Log.e("Credentials", "unable to get credentials");
        }

     }

    public void deployMetaCoin(View view) {
        Web3j web3 = Web3jFactory.build(new HttpService("http://10.0.2.2:8545"));
        try {
            Credentials credentials = WalletUtils.loadCredentials("myWallet", getApplicationContext().getFileStreamPath(this.walletPath).getPath());
            Future<MetaCoin> metacoin = MetaCoin.deploy(web3, credentials, GAS_PRICE, new BigInteger("1000000000"), BigInteger.ZERO);
            this.metacoin = metacoin.get();
            String addr = this.metacoin.getContractAddress();
            Log.d("MetaCoin address", addr);
            Uint256 balance = this.metacoin.getBalance(new Address(addr)).get();
            Log.d("Balance", balance.toString());
         } catch(IOException | CipherException | InterruptedException | ExecutionException e) {
            Log.e("Error", e.toString());
        }
    }
}
