import java.util.*;
public class Desserts
{
	String colour;
	int size;
	Desserts(String colour,int size)
	{
		this.size=size;
		this.colour=colour;
	}
	public static void main(String[] args)
	{
		Desserts cupcake = new Desserts("Red",1);
		Desserts cake = new Desserts ("Brown",3);
		System.out.println("Colour of cupcake is "+cupcake.colour+" and its size is "+cupcake.size);
		System.out.println("Colour of cake is "+cake.colour+" and its size is "+cake.size);
	}
}
