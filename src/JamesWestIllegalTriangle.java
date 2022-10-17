import java.util.Arrays;

public class JamesWestIllegalTriangle
{
	public final static String PASS = "----PASS----";
	public final static String FAIL = "!!!!FAIL!!!!";
	
	public static void main (String[] args)
	{
		System.out.println("""
				+---------------------+
				| VALID TRIANGLE TEST |
				+---------------------+""");
		try
		{
			
			testValid(2, 3, 4);
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
			System.out.println(FAIL);
		}
		System.out.println("""
				+-----------------------+
				| INVALID TRIANGLE TEST |
				+-----------------------+""");
		try
		{
			testValid(1, 1, 5);
			System.out.println(FAIL);
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
			String testErrMessage = String.format(
					"The sum of any two lines must be greater than the other side. %.2f + %.2f is less " +
							"than or equal to %.2f", 1.0, 1.0, 5.0
			);
			System.out.println(e.getMessage().equals(testErrMessage) ? PASS : FAIL);
		}
	}
	
	public static void testValid (double min, double mid, double max) throws Exception
	{
		System.out.println("Triangle with sides " + min + " " + mid + " " + max);
		Triangle t1 = new Triangle(mid, min, max);
		System.out.println("min is " + min);
		System.out.println(t1.getMinSide() == min ? PASS : FAIL);
		System.out.println("mid is " + mid);
		System.out.println(t1.getMidSide() == mid ? PASS : FAIL);
		System.out.println("max is " + max);
		System.out.println(t1.getMaxSide() == max ? PASS : FAIL);
		System.out.println("sorted side values are {2, 3, 4}");
		System.out.println(Arrays.equals(t1.getSortedSideValues(), new double[] { 2, 3, 4 }) ? PASS : FAIL);
	}
}

class Triangle
{
	private final double[] sortedSides;
	
	Triangle (double side1, double side2, double side3) throws IllegalTriangleException
	{
		sortedSides = new double[] {side1, side2, side3};
		Arrays.sort(sortedSides);
		
		if (sortedSides[ 0 ] + sortedSides[ 1 ] <= sortedSides[ 2 ])
			throw new IllegalTriangleException(
					String.format(
							"The sum of any two lines must be greater than the other side. %.2f + %.2f is "
									+ "less than or equal to %.2f",
							sortedSides[ 0 ],
							sortedSides[ 1 ],
							sortedSides[ 2 ]
					)
			);
	}
	
	public double getMinSide ()
	{
		return sortedSides[ 0 ];
	}
	
	public double getMidSide ()
	{
		return sortedSides[ 1 ];
	}
	
	public double getMaxSide ()
	{
		return sortedSides[ 2 ];
	}
	
	public double[] getSortedSideValues ()
	{
		return sortedSides.clone();
	}
}

class IllegalTriangleException extends Exception
{
	IllegalTriangleException (String errMessage)
	{
		super(errMessage);
	}
}