package com.gnm.desktop.core;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;

//just validates the digital signature.
//#1       wants publicKey &
//#2              Signature & 
//#3               data you want to compare with signature.
public class Registration{

    private static Boolean isValid=false;
    //Data for Comparison                                                        #3
    private static String data=SystemDetails.getSystemDetails();


    public static Boolean verify() {
            try {
                //taking publicKey byts from file
                byte[] pub = fileIO.readFileByts("publicKey.key");

                //building the publicKey from raw byts tooken from file          #1
                X509EncodedKeySpec keySpec = new X509EncodedKeySpec(pub);
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