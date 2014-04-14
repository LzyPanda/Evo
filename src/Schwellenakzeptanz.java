/**
 * Created by lzypanda on 09.04.14.
 */

import java.lang.Math;
public class Schwellenakzeptanz {


    public static void main(String[] args) {



        double T=10000;
        double TF=0.1;
        int ZMax= 100;
        double Cx= 0.8; //Math.random()*(1-(-1))+(-1);
        double Cy= 0.8; //Math.random()*(1-(-1))+(-1);

        double UrCx= Cx;
        double UrCy= Cy;

        double Crefx=Cx;
        double Crefy=Cy;
        int pruef=0;

        double qualityNew,qualityOld,diffQ,QualityUr;
        for(int i=0; i<100;i++)
        {

            for (int z=0; z<ZMax ;z++)
            {
                pruef++;
                if(Cx<=1 & Cy<=1 & (Cx >= (-1)) & (Cy>= (-1))) {

                    qualityNew = 20 + (Cx*Cx) + (Cy*Cy) - (10 * ((Math.cos(2 * Math.PI * Cx)) +Math.cos(2 * Math.PI * Cy)));
                    qualityOld = 20 + (Crefx*Crefx) + (Crefy*Crefy) - (10 * ((Math.cos(2 * Math.PI * Crefx)) +  Math.cos(2 * Math.PI * Crefy)));

                    if (qualityOld > qualityNew) {
                        Crefx = Cx;
                        Crefy = Cy;
                    }

                    QualityUr = 20 + (UrCx*UrCx) + (UrCy*UrCy) - 10 * ((Math.cos(2 * Math.PI * UrCx)) +  Math.cos(2 * Math.PI * UrCy));
                    diffQ = QualityUr-qualityNew ;
                    if (diffQ > (-T)) {
                        UrCx = Cx;
                        UrCy = Cy;
                    }
                }

                if(Math.random()>=0.5)
                {
                    Cx=Cx+Math.random()*(0.1);
                }
                else{
                    Cx=Cx-Math.random()*(0.1);
                }
                if(Math.random()>=0.5)
                {
                    Cy=Cy+Math.random()*(0.1);
                }
                else{
                    Cy=Cy-Math.random()*(0.1);
                }
                if(Cx>1){
                    Cx=1;
                }
                if(Cy>1){
                    Cy=1;
                }
                if(Cx<(-1)){
                    Cx=-1;
                }
                if(Cy<(-1)){
                    Cy=-1;
                }

            }
            T=T*TF;

        }
        System.out.println("Durchlaeufe: "+pruef);
        System.out.println("X-Wert: " +Crefx);
        System.out.println("Y-wert:" +Crefy);

    }
}
