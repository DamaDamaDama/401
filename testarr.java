
public class testarr {
	public static void main(String args[]){
		int[] a = new int[5];
		a[1] = 17;
		a[4] = 2;
		try{
		a[5] = 3;
		System.out.println(a[5]);
		}
		catch(ArrayIndexOutOfBoundsException ARIOOBE){
			System.out.println("Doesn't work");
		}
		a = new int[6];
		System.out.println(a[5]);
		System.out.println(a[1]);
	}
}
