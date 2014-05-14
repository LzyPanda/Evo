package Hausaufgaben;

import java.util.Arrays;

/**
 * Created by lzypanda on 14.05.14.
 */

public class Hausaufgabe070512 {
    //k=11     a=-1     b=1

    private static Points[] arr;

    public static void main(String[] args) {
//		System.out.println(doubletobin(0));
        createParents();
        arr = selection(arr);
        System.out.println("Selektiert: " + Arrays.toString(arr));

        for (int i=0; i<5; i++) {
            // Ausgegeben werden in dieser Reihenfolge  Trel,Cb,Cx, X(summe), Z (summe) und Differenz von X und Z
            recombination();
            System.out.println("Rekombiniert" + Arrays.toString(arr));
            mutate();
            System.out.println("Mutiert     " + Arrays.toString(arr));
            arr = selection(arr);
            System.out.println("Selektiert  " + Arrays.toString(arr)+"\n");
        }
    }

    private static Points[] createParents() {
        arr = new Points[20];
        for (int i=0; i<20; i++) {
            arr[i] = new Points(0.4000, 0.2000,0.3000);
        }
        return arr;
    }

    private static Points[] selection(Points[] z) {
        Points[] nz = new Points[10];
        Arrays.sort(z);
        System.arraycopy(z, 0, nz, 0, 10);
        return nz;
    }

    private static void recombination() {
        int k = 0;
        Points[] tmp = new Points[45];
        for(int i=0; i < arr.length; i++) {
            for (int j=i+1; j < arr.length; j++) {
                tmp[k] = cross(i, j);
                k++;
            }
        }
        arr=tmp;
    }

    private static Points cross(int i, int j) {

        int random1 = (int)(Math.random()*11);
        int random2 = (int)(Math.random()*11);
        int random3 = (int)(Math.random()*11);


        String str1 = doubletobin(arr[i].x).substring(0,random1);
        String str2 = doubletobin(arr[i].z).substring(random2,11);
        String str3 = doubletobin(arr[j].y).substring(0,random2);
        String str4 = doubletobin(arr[j].y).substring(random1,11);
        //TODO
        String str5 = doubletobin(arr[i].z).substring(0,random3);
        String str6 = doubletobin(arr[j].x).substring(random3,11);


        double dbl1 = bintodouble(str1+str4);
        double dbl2 = bintodouble(str3+str2);
        //TODO
        double dbl3 = bintodouble(str5+str6);

        return new Points(dbl1, dbl2,dbl3);
    }

    private static void mutate() {
        for (int i = 0; i< arr.length; i++) {
            Points p = arr[i];
            String tmpx = doubletobin(p.x);
            String tmpy = doubletobin(p.y);
            String tmpz = doubletobin(p.z);

            int randomx = (int)(Math.random()*11);
            int randomy = (int)(Math.random()*11);
            int randomz = (int)(Math.random()*11);
            if (tmpx.charAt(randomx) == '0')
                tmpx = tmpx.substring(0,randomx)+"1"+tmpx.substring(randomx+1);
            else
                tmpx = tmpx.substring(0,randomx)+"0"+tmpx.substring(randomx+1);

            if (tmpy.charAt(randomy) == '0')
                tmpy = tmpy.substring(0,randomy)+"1"+tmpy.substring(randomy+1);
            else
                tmpy = tmpy.substring(0,randomy)+"0"+tmpy.substring(randomy+1);

            if (tmpz.charAt(randomz) == '0')
                tmpz = tmpz.substring(0,randomz)+"1"+tmpz.substring(randomz+1);
            else
                tmpz = tmpz.substring(0,randomz)+"0"+tmpz.substring(randomz+1);

            double doublex = bintodouble(tmpx);
            double doubley = bintodouble(tmpy);
            double doublez = bintodouble(tmpz);

            arr[i]= new Points(doublex, doubley, doublez);
        }
    }

    private static double bintodouble(String x) {
        return (double)(Integer.parseInt(x, 2))/2047*2-1;
    }

    private static String doubletobin(double x) {
        String tmp = Integer.toBinaryString((int)((x+1)/2*2047));
        for (int i=tmp.length(); i <= 10; i++)
            tmp = "0"+tmp;
        return tmp;
    }
}

class Points implements Comparable<Points> {
    double x;  //entspricht Trel
    double y;  // entspricht Cb
    double z;  // entspricht Cx
    double XWert;
    double ZWert;
    double Diff;
    public Points(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.XWert= Math.abs(x)+Math.abs(y)+Math.abs(z);

        double tmp1,tmp2,tmp3; // entspricht te, fe und vi
        tmp1 = Math.pow(Math.E,(-x)*1.2)+0.2*z-0.8*Math.pow(y,2.0);
        tmp2 = 1+0.5*x-0.7*Math.pow(z,3.0);
        tmp3 = 1- 0.3*x+2.0*z;
        this.ZWert =  Math.abs(tmp1)+Math.abs(tmp2)+Math.abs(tmp3);
        this.Diff= Math.abs(this.XWert-ZWert);
    }

    public int compareTo(Points p) {
        return (int)(this.Diff -p.Diff);
    }

    public String toString() {
        String strx = Double.toString(x);

        if(strx.length()>4){
            strx = strx.substring(0, strx.indexOf('.')+3);
        }

        String stry = Double.toString(y);
        if(stry.length()>4){
            stry = stry.substring(0, stry.indexOf('.')+3);
        }

        String strz = Double.toString(z);

        if(strz.length()>4){
            strz = strz.substring(0, strz.indexOf('.')+3);
        }

        String strXWert = Double.toString(XWert);
        if(strXWert.length()>4){
            strXWert = strXWert.substring(0, strXWert.indexOf('.')+3);
        }

        String strZWert = Double.toString(ZWert);
        if(strZWert.length()>4){
            strZWert = strZWert.substring(0, strZWert.indexOf('.')+3);
        }


        String strDiff = Double.toString(Diff);
        if(strDiff.length()>4) {
            strDiff = strDiff.substring(0, strDiff.indexOf('.') + 3);
        }

        return "["+strx+"|"+stry+"|"+strz+"|"+strXWert+"|"+strZWert+"|"+strDiff+"]";
    }
}