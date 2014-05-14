package Uebung;

/**
 * Created by Simon on 12.05.2014.
 */

import java.util.Arrays;

public class Uebung2 {

    //k=11     a=-1     b=1

    private static Point[] arr;

    public static void main(String[] args) {
//		System.out.println(doubletobin(0));
        createParents();
        arr = selection(arr);
        System.out.println("Selektiert: " + Arrays.toString(arr));

        for (int i=0; i<5; i++) {
            recombination();
            System.out.println("Rekombiniert" + Arrays.toString(arr));
            mutate();
            System.out.println("Mutiert     " + Arrays.toString(arr));
            arr = selection(arr);
            System.out.println("Selektiert  " + Arrays.toString(arr)+"\n");
        }
    }

    private static Point[] createParents() {
        arr = new Point[20];
        for (int i=0; i<20; i++) {
            arr[i] = new Point(Math.random()*2-1, Math.random()*2-1);
        }
        return arr;
    }

    private static Point[] selection(Point[] z) {
        Point[] nz = new Point[10];
        Arrays.sort(z);
        System.arraycopy(z, 0, nz, 0, 10);
        return nz;
    }

    private static void recombination() {
        int k = 0;
        Point[] tmp = new Point[45];
        for(int i=0; i < arr.length; i++) {
            for (int j=i+1; j < arr.length; j++) {
                tmp[k] = cross(i, j);
                k++;
            }
        }
        arr=tmp;
    }

    private static Point cross(int i, int j) {

        int random1 = (int)Math.random()*11;
        int random2 = (int)Math.random()*11;


        String str1 = doubletobin(arr[i].x).substring(0,random1);
        String str2 = doubletobin(arr[i].y).substring(random2,11);
        String str3 = doubletobin(arr[j].x).substring(0,random2);
        String str4 = doubletobin(arr[j].y).substring(random1,11);

        double dbl1 = bintodouble(str1+str4);
        double dbl2 = bintodouble(str2+str3);

        return new Point(dbl1, dbl2);
    }

    private static void mutate() {
        for (int i = 0; i< arr.length; i++) {
            Point p = arr[i];
            String tmpx = doubletobin(p.x);
            String tmpy = doubletobin(p.y);

            int randomx = (int)Math.random()*11;
            int randomy = (int)Math.random()*11;

            if (tmpx.charAt(randomx) == '0')
                tmpx = tmpx.substring(0,randomx)+"1"+tmpx.substring(randomx+1);
            else
                tmpx = tmpx.substring(0,randomx)+"0"+tmpx.substring(randomx+1);

            if (tmpy.charAt(randomy) == '0')
                tmpy = tmpy.substring(0,randomy)+"1"+tmpy.substring(randomy+1);
            else
                tmpy = tmpy.substring(0,randomy)+"0"+tmpy.substring(randomy+1);

            double doublex = bintodouble(tmpx);
            double doubley = bintodouble(tmpy);

            arr[i]= new Point(doublex, doubley);
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

class Point implements Comparable<Point> {
    double x;
    double y;
    double z;
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
        z = 20+x*x+y*y-10*(Math.cos(2*Math.PI*x)+Math.cos(2*Math.PI*y));
    }

    public int compareTo(Point p) {
        return (int)(this.z -p.z);
    }

    public String toString() {
        String strx = Double.toString(x);
        strx = strx.substring(0, strx.indexOf('.')+4);

        String stry = Double.toString(y);
        stry = stry.substring(0, stry.indexOf('.')+4);

        String strz = Double.toString(z);
        strz = strz.substring(0, strz.indexOf('.')+4);


        return "["+strx+"|"+stry+"|"+strz+"]";
    }
}