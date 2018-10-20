package fall2018.csc2017.slidingtiles;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Scanner;

public class Tralebus extends AbstractAccountAuthenticator {
    /*Scanner sc = new Scanner(System.in);
    AccountManager manager = new AccountManager();
    public void creatAccunt() {
        System.out.println("Enter username: ");
        String mUsername = sc.nextLine();
        System.out.println("Enter username: ");
        String mPassword = sc.nextLine();
        final Account account = new Account(mUsername, "user");
        manager.addAccountExplicitly(account, mPassword, null);
    }*/

    @Override
    public Bundle addAccount(AccountAuthenticatorResponse accountAuthenticatorResponse, String s,
                             String s1, String[] strings, Bundle bundle) {
        Bundle myBundle = new Bundle();
        ArrayList name = new ArrayList();
        myBundle.putStringArrayList(AccountManager.KEY_ACCOUNT_NAME, name);
        ArrayList accType = new ArrayList();

        myBundle.putStringArrayList(AccountManager.KEY_ACCOUNT_NAME, accType);
        return myBundle;
    }

    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse,
                                     Account account, Bundle bundle) {

        boolean isTrue = true;
        Bundle myBundle = new Bundle();
        Intent myIntent = new Intent(this, InformationGathering.class )
        myBundle.putBoolean(AccountManager.KEY_BOOLEAN_RESULT, isTrue);
        return myBundle;
    }

    @Override
    public Bundle editProperties(AccountAuthenticatorResponse accountAuthenticatorResponse,
                                 String s) {
        return null;
    }

    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse accountAuthenticatorResponse,
                               Account account, String s, Bundle bundle) {
        return null;
    }

    @Override
    public String getAuthTokenLabel(String s) {
        return null;
    }

    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse accountAuthenticatorResponse,
                              Account account, String[] strings) {
        return null;
    }

    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse,
                                    Account account, String s, Bundle bundle) {
        return null;
    }
}
