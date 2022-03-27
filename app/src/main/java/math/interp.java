package math;

public class interp {
    public static float lerp(float a, float b, float t)//linear interpolation
    {
        //returns value between a and b, accoarding to t (0.0 ... 1.0)
        return a + (b - a) * t;
    }
    public static float unlerp(float x, float x0, float x1)
    {
        //returns value between a and b, accoarding to t (0.0 ... 1.0)
        return (x-x0) / (x1-x0);
    }
    public static float map(float x, float x0, float x1,float a, float b)
    {
        //remap value x inside  (x0 ... x1) to a different range (a ... b) preserving t
        float t = unlerp(x,x0,x1);
        return lerp(a,b,t);
    }

}
