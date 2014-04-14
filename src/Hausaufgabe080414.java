/**
 * Created by Yusuf Peker & Simon Buttke on 10.04.14.
 */
public class Hausaufgabe080414 {


    public static void main(String[] args) {
        double T=10;
        double TF=0.1;
        int ZMax= 10000;

        double Trel=0.4;
        double Cb= 0.2; //Math.random()*(1-(-1))+(-1);
        double Cx= 0.3; //Math.random()*(1-(-1))+(-1);

        double UrTrel= Trel;
        double UrCb= Cb;
        double UrCx= Cx;

        double CrefTrel= Trel;
        double Crefb=Cb;
        double Crefx=Cx;

        int steps =0; //
        double qualityOld=10;
        double qualityNew,diffQ,QualityUr;

        for(int i=0; i<1000;i++)
        {

            for (int z=0; z<ZMax ;z++)
            {
                steps++;

                qualityNew = quality(Trel,Cb,Cx);
                qualityOld = quality(CrefTrel,Crefb,Crefx);
                //System.out.println("Trel: "+Trel +" Cb: "+Cb+" Cx: "+Cx);
                //System.out.println("Te: "+Te(Trel,Cb,Cx));
                //System.out.println("Fe: "+Fe(Trel,Cx));
                //System.out.println("Vi: "+Vi(Trel,Cx));
                //System.out.println("BestTrel: "+CrefTrel+" BestCb: "+Crefb+" BestCx: "+Crefx);
                //System.out.println("Quality: "+qualityNew);
                //System.out.println("BestQ: "+qualityOld);
                //System.out.println("---------------------------");
                if (qualityOld > qualityNew) {
                    CrefTrel = Trel;
                    Crefb = Cb;
                    Crefx = Cx;
                }

                QualityUr = quality(UrTrel,UrCb,UrCx);
                diffQ = QualityUr-qualityNew ;
                if (diffQ > (-T)) {
                    UrTrel = Trel;
                    UrCb = Cb;
                    UrCx = Cx;
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
                    Cb=Cb+Math.random()*(0.1);
                }
                else{
                    Cb=Cb-Math.random()*(0.1);
                }
                if(Math.random()>=0.5)
                {
                    Trel=Trel+Math.random()*(0.1);
                }
                else{
                    Trel=Trel-Math.random()*(0.1);
                }


            }
            T=T*TF;

        }
        System.out.println("Durchlaeufe: "+ steps);

        System.out.println("Trel-wert:" +CrefTrel);
        System.out.println("Cb-Wert: " +Crefb);
        System.out.println("Cx-wert:" +Crefx);
        System.out.println("Te: "+Te(CrefTrel,Crefb,Crefx)+" Fe: "+Fe(CrefTrel,Crefx)+" Vi: "+Vi(CrefTrel,Crefx));
        System.out.println("BestQ: "+qualityOld);

    }

    public static double  Te(double Trel, double Cb, double Cx)
    {
        double result = Math.pow(Math.E, (-Trel)*1.2)+0.2*Cx-0.8*(Cb*Cb);

        return result;
    }

    public static double Fe(double Trel,  double Cx)
    {
        double result = 1+0.5*Trel-0.7*Math.pow(Cx,3);

        return result;
    }

    public static double Vi(double Trel, double Cx)
    {
        double result = 1-0.3*Trel+2.0*Cx;

        return result;
    }

    public static double quality(double Trel, double Cb, double Cx)
    {

        double DiffTe= 0.5-Te(Trel,Cb,Cx);
        DiffTe= Math.abs(DiffTe);
        double DiffFe= 0.8- Fe(Trel,Cx);
        DiffFe= Math.abs(DiffFe);
        double DiffVi= 0.2- Vi(Trel,Cx);
        DiffVi= Math.abs(DiffVi);
        double quality= DiffTe+DiffFe+DiffVi;
        return quality;
    }
}
