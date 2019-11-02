package com.gnm.desktop.core;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

//just validates the digital signature.
//#1       wants publicKey &
//#2              Signature & 
//#3               data you want to compare with signature.
public class Registration{

    private static Boolean isValid=false;
    private static final String publicKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAw2Z65fc+B5hmzjA0zIsM3xoPeLL+VPHRggOcXTnjAFFDZMnLgBQPTKUvRuNXEegVYZOBHMfZEoF52yXroni8rgpIi3P+izmJ70WuV5OVoXOBeE6m4fmc6hGfK1HyCo0/R+tnIX4CPusrQzTuREMZEu19M3k5BV7V4fvk43GDBHVSQBIW+CSMqFT5lgGpmQgKtyMuWNcWHbgd8HLXYcZgOTWTFs2lwpu/8+5m27Rb5ZZ5AIxg7SpqpOvAP6XbpfRVEajwRigDbSe6iIo0TDFoI5qBHHist4HAc8CO5y0MmLEI/dJ3UbFFINqcEIqAkdDn9SrYWyixR8Me+ZdNXR4TZQIDAQAB";          
    //Data for Comparison                                                        #3         
    private static String data=SystemDetails.getSystemDetails();


    public static Boolean verify() {
            try {
                //taking publicKey byts from Base64 String
                byte[] pubKeyDecoded=Base64.getDecoder().decode(publicKey);
                //byte[] pub = fileIO.readFileByts("publicKey.key");

                //building the publicKey from raw byts tooken from Base64 String  #1

                X509EncodedKeySpec keySpec = new X509EncodedKeySpec(pubKeyDecoded);
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                PublicKey pubKey=keyFactory.generatePublic(keySpec);


                //taking signature byts from file 
                byte[] sig=fileIO.readFileByts("sign.sig");

                //Prepare signature for  Validating                               #2
                Signature signature=Signature.getInstance("SHA256withRSA");
                signature.initVerify(pubKey);
                signature.update(data.getBytes());

                //Validate signature
                isValid=signature.verify(sig);

            }catch(Exception e){
                Log.d("Registration.verify exeption", e.getMessage());
            }


        return isValid;

    }
}