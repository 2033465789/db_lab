import java.util.HashSet;

public class Test
{
	public static void main(String[] args)
	{
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < 1000000; i++)
			set.add(i);
		for (Integer i : set)
		{
			System.out.println(i);
		}
	}
}
