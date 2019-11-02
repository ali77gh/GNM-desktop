import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Scanner;


public class KeyGenerator {

    private static KeyPair keyPair=null;
    private static final String data="GNM-CORPS";

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        while (true){
            System.out.print("\n\n Choose: \n");

            System.out.println("1.Generating Keys.\n"+
                           "2.Generating Signature.\n"+
                           "3.Generate Theme ALLLLLLL :)))\n"+
                           "4.quit");
            int option=scanner.nextInt();
        
            switch(option){
                case 1:
                    keyGen();
                    break;
                case 2:
                    signGen();
                    break;
                case 3:
                    keyGen();
                    signGen();
                    break;
                case 4:
                    scanner.close();
                    return;
                default:
                    System.out.println("wrong option!");
                    break;
            }
        }
        
    
    }

    private static void signGen(){
        try {



            FileInputStream fis=new FileInputStream("/home/mmd/projects/gnm_desktop/KeyGenarator/privateKey.key");
            byte[] prvByts=fis.readAllBytes();
            fis.close();

            PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(prvByts);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PrivateKey pvt = kf.generatePrivate(ks);
            


            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(pvt);
            signature.update(data.getBytes());

            byte[] sign=signature.sign();
//            String encodenSign=Base64.getEncoder().encodeToString(sign);
            System.out.println("****************** Sign Generated ****************** \n");

            FileOutputStream fos=new FileOutputStream("/home/mmd/projects/gnm_desktop/src/main/resources/raw/sign.sig");
            fos.write(sign);
            fos.close();
            System.out.println("****************** Signature FILE WRITTEN ******************  \n");


        }catch (Exception e){
            System.out.println("we have exeptions in block signature: "+ e.getMessage());
        }
    }




    private static void keyGen() {
        try {

            KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
            keyGenerator.initialize(2048,new SecureRandom());
            keyPair=keyGenerator.generateKeyPair();
            System.out.println("****************** Keys Generated ******************  \n");
            writeKeyToFile();

        }catch (Exception e){
            System.out.println("we have exeptions in keyGen block :"+ e.getMessage());
        }


    }
    private static void writeKeyToFile(){

            byte[] prv= keyPair.getPrivate().getEncoded();
//            String prvString= Base64.getEncoder().encodeToString(prv);


            byte[] pub=keyPair.getPublic().getEncoded();
//            String pubString=Base64.getEncoder().encodeToString(pub);


            try {
                FileOutputStream fospub = new FileOutputStream("/home/mmd/projects/gnm_desktop/src/main/resources/raw/publicKey.key");
                fospub.write(pub);
                fospub.close();

                FileOutputStream fosprv= new FileOutputStream("/home/mmd/projects/gnm_desktop/KeyGenarator/privateKey.key");
                fosprv.write(prv);
                fosprv.close();

                System.out.println("****************** Key FILES WRITTEN ****************** \n");
            }catch (Exception e){
                System.out.println("we have exeption in writeKeyToFile block: "+ e.getMessage());
            }
    }

}
