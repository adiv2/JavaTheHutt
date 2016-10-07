public class JavaTest2 extends JavaTest1
{
	void printname()
	{
		System.out.print(names[n]+" "+lname +" ");
	}
	public static void main (String[] args)
	{
		JavaTest2 t = new JavaTest2();
		t.get();
		t.printname();
	}
}
